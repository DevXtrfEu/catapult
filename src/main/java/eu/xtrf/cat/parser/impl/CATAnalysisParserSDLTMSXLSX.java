package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;


public class CATAnalysisParserSDLTMSXLSX extends CATAnalysisLocalizedSDLTMSParser {
	
	private Sheet sheet;
	private int currentRow;
	private boolean hasWordsColumn = false;
	
	@Override
	public CATTool getCATTool() {
		return CATTool.SDL_TRADOS;
	}
	
	public CATAnalysisParserSDLTMSXLSX() {
		super(ENGLISH);
	}
	
	public CATAnalysisParserSDLTMSXLSX(Map<LanguageKey, String> languageKeys) {
		super(languageKeys);
	}
	
	@Override
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
		
		try {
			Workbook wb = WorkbookFactory.create(is);

			sheet = wb.getSheetAt(0);

			parse(result);
		} catch (InvalidFormatException | IOException e) {
			throw new CATAnalysisParserException(e);
		}

		return result;
	}

	private void parse(CATAnalysis result) throws CATAnalysisParserException {
		boolean fuzzyMatch = isFuzzyMatch(sheet);

		int totalRow = findRowWithText(sheet, getLocalization(LanguageKey.TOTALS));
		parseCharsPerWord(totalRow + 3, result);
		if(result.getCharsPerWord() == null) { // fix for additional empty row
			parseCharsPerWord(totalRow + 4, result);
		}

		currentRow = totalRow + 1;

		checkIfHasWordsColumn();

		parseItem(result, MatchType.LOCKED_SEGMENTS, getLocalization(LanguageKey.LOCKED_SEGMENTS));
		parseItem(result, MatchType.PERFECT_MATCH, getLocalization(LanguageKey.PERFECT_MATCH));
		parseItem(result, MatchType.CONTEXT_MATCH, getLocalization(LanguageKey.CONTEXT_MATCH));


		parseItem(result, MatchType.REPETITIONS, getLocalization(LanguageKey.REPETITIONS));
		parseItem(result, MatchType.CROSS_FILE_REPETITIONS, getLocalization(LanguageKey.CROSS_FILE_REPETITIONS));

		parseItem(result, MatchType.PERCENT_100, "1.0");
		parseItem(result, MatchType.PERCENT_95_99, "95% - 99%");
		parseItem(result, MatchType.PERCENT_85_94, "85% - 94%");
		parseItem(result, MatchType.PERCENT_75_84, "75% - 84%");
		parseItem(result, MatchType.PERCENT_50_74, "50% - 74%");

		if (fuzzyMatch) {
			currentRow = currentRow + 1;

			parseItem(result, MatchType.REPETITIONS, getLocalization(LanguageKey.REPETITIONS));
			parseItem(result, MatchType.INTERNAL_95_99, "95% - 99%");
			parseItem(result, MatchType.INTERNAL_85_94, "85% - 94%");
			parseItem(result, MatchType.INTERNAL_75_84, "75% - 84%");
			parseItem(result, MatchType.INTERNAL_50_74, "50% - 74%");
		}

		parseItem(result, MatchType.NEW, getLocalization(LanguageKey.NEW));
		
		parseItem(result, MatchType.ADAPTIVE_MT_BASELINE, getLocalization(LanguageKey.ADAPTIVE_MT_BASELINE));
		parseItem(result, MatchType.ADAPTIVE_MT_WITH_LEARNINGS, getLocalization(LanguageKey.ADAPTIVE_MT_WITH_LEARNINGS));
		
		parseItem(result, MatchType.TOTAL, getLocalization(LanguageKey.TOTAL));
		
		result.verifyDataIntegrity();
	}

	private void checkIfHasWordsColumn() {
		Cell maybeWordsColumn = sheet.getRow(currentRow).getCell(3);
		if (maybeWordsColumn.toString().equals(getLocalization(LanguageKey.WORDS))) {
			hasWordsColumn = true;
		}
		++currentRow;
	}

	private void parseCharsPerWord(int rowNumber, CATAnalysis result) throws CATAnalysisParserException {
		Row charsPerWordRow = sheet.getRow(rowNumber);
		Cell charsPerWordCell = charsPerWordRow.getCell(0);
		
		String charsPerWordCellValue = charsPerWordCell.getStringCellValue();
		
		if (!charsPerWordCellValue.startsWith(getLocalization(LanguageKey.CHARS_PER_WORD_STRING) + ":")) {
			// in some languages (e.g. Japanese) this cell may be empty
			return;
		}

		String charsPerWordValue = charsPerWordCellValue.replaceFirst(getLocalization(LanguageKey.CHARS_PER_WORD_STRING) + ":", "").replace(",", ".");
		BigDecimal charsPerWord = new BigDecimal(charsPerWordValue);
		result.setCharsPerWord(charsPerWord);
	}

	private boolean isFuzzyMatch(Sheet sheet) throws CATAnalysisParserException {
		int fuzzyMatchRowNumber = findRowWithText(sheet, getLocalization(LanguageKey.REPORT_INTERNAL_FUZZY_MATCH_LEVERAGE));
		Row fuzzyMatchRow = sheet.getRow(fuzzyMatchRowNumber);

		Cell fuzzyMatchCellValue = fuzzyMatchRow.getCell(1);

		return fuzzyMatchCellValue.getStringCellValue().equals(getLocalization(LanguageKey.YES));
	}

	private int findRowWithText(Sheet sheet, String text) throws CATAnalysisParserException {
		Iterator<Row> it = sheet.rowIterator();

		while (it.hasNext()) {
			Row row = (Row) it.next();

			if (row.getCell(0).getStringCellValue().equals(text)) {
                return row.getRowNum();
			}
		}

		throw new CATAnalysisParserException("Unable to find: " + text + " within xls sheet: " + sheet.getSheetName());
	}

	private void parseItem(CATAnalysis result, MatchType category, String description) {
		Row itemRow = sheet.getRow(currentRow);

		Cell categoryCell = itemRow.getCell(1);
		
		if(categoryCell.toString().isEmpty()) {	// fix for additional empty row
			currentRow++;
			parseItem(result, category, description);
		}
		
		if (categoryCell.toString().equals(description)) {
			result.add(Unit.SEGMENT, category, Double.valueOf(itemRow.getCell(2).getNumericCellValue()).intValue());
			
			if (hasWordsColumn) {
				result.add(Unit.WORD, category, Double.valueOf(itemRow.getCell(3).getNumericCellValue()).intValue());
				result.add(Unit.CHARACTER, category, Double.valueOf(itemRow.getCell(4).getNumericCellValue()).intValue());
				result.add(Unit.TOKEN, category, Double.valueOf(itemRow.getCell(6).getNumericCellValue()).intValue());
			} else {
				result.add(Unit.CHARACTER, category, Double.valueOf(itemRow.getCell(3).getNumericCellValue()).intValue());
				result.add(Unit.TOKEN, category, Double.valueOf(itemRow.getCell(5).getNumericCellValue()).intValue());
			}
			
			currentRow++;
		}
	}
}
