package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.Unit.CHARACTER;
import static eu.xtrf.cat.Unit.SEGMENT;
import static eu.xtrf.cat.Unit.WORD;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.parser.CATAnalysisParserException;


public class CATAnalysisParserXTMXLS_Full extends CATAnalysisParserBase {

	@Override
	public CATTool getCATTool() {
		return CATTool.XTM;
	}

	@Override
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);

		try {
			Workbook wb = WorkbookFactory.create(is);

			Sheet sheet = wb.getSheetAt(0);
			CellOffsets cellOffsets = identifyCellOffset(sheet);
			parse(result, sheet, cellOffsets);
			result.verifyDataIntegrity();

		} catch (InvalidFormatException | IOException e) {
			throw new CATAnalysisParserException(e);
		}
		
		return result;
	}

	private void parse(CATAnalysis result, Sheet sheet, CellOffsets cellOffsets) throws CATAnalysisParserException {
		parseBlockLine(result, "Total count", MatchType.TOTAL, sheet, cellOffsets);
		parseBlockLine(result, "Non-translatable", MatchType.NON_TRANSLATABLE, sheet, cellOffsets);
		parseBlockLine(result, "ICE match", MatchType.ICE_MATCH, sheet, cellOffsets);
		parseBlockLine(result, "Leveraged match", MatchType.LEVERAGED_MATCH, sheet, cellOffsets);
		parseBlockLine(result, "95-99 Fuzzy match", MatchType.PERCENT_95_99, sheet, cellOffsets);
		parseBlockLine(result, "85-94 Fuzzy match", MatchType.PERCENT_85_94, sheet, cellOffsets);
		parseBlockLine(result, "75-84 Fuzzy match", MatchType.PERCENT_75_84, sheet, cellOffsets);
		parseBlockLine(result, "95-99 Fuzzy repeat", MatchType.INTERNAL_95_99, sheet, cellOffsets);
		parseBlockLine(result, "85-94 Fuzzy repeat", MatchType.INTERNAL_85_94, sheet, cellOffsets);
		parseBlockLine(result, "75-84 Fuzzy repeat", MatchType.INTERNAL_75_84, sheet, cellOffsets);
		parseBlockLine(result, "No matching", MatchType.NO_MATCH, sheet, cellOffsets);
		parseBlockLine(result, "Repeat", MatchType.REPETITIONS, sheet, cellOffsets);
	}

	private void parseBlockLine(final CATAnalysis result, final String columnName, final MatchType matchType, Sheet sheet, CellOffsets cellOffsets) throws CATAnalysisParserException {
		Row row = findRowByHeader(columnName, sheet, cellOffsets.rowName);
		int segmentsValue = (int) row.getCell(cellOffsets.segments).getNumericCellValue();
		int wordsValue = (int) row.getCell(cellOffsets.words).getNumericCellValue();
		int charactersValue = (int) row.getCell(cellOffsets.characters).getNumericCellValue();
		
		result.add(SEGMENT, matchType, segmentsValue);
		result.add(WORD, matchType, wordsValue);
		result.add(CHARACTER, matchType, charactersValue);
	}

	private Row findRowByHeader(String header, Sheet sheet, int rowName) throws CATAnalysisParserException {
		Iterator<Row> it = sheet.rowIterator();
		while (it.hasNext()) {
			Row currentRow = it.next();
			Cell currentCell = currentRow.getCell(rowName);
			if (currentCell != null) {
				String value = currentCell.getStringCellValue();
				if (value.contains(header)) {
					return currentRow;
				}
			}
		}
		throw new CATAnalysisParserException();
	}

	private CellOffsets identifyCellOffset(Sheet sheet) throws CATAnalysisParserException {
		for (Row row : sheet) {
			CellOffsets results = null;
			for (Cell cell : row) {
				if (cell.getStringCellValue().equals("Initial")) {
					results = new CellOffsets();
					results.rowName = cell.getColumnIndex();
				}
				if (results != null && results.segments == -1 && cell.getStringCellValue().contains("Segments")) {
					results.segments = cell.getColumnIndex();
				}
				if (results != null && results.words == -1 && cell.getStringCellValue().contains("Words")) {
					results.words = cell.getColumnIndex();
				}
				if (results != null && results.characters == -1 && cell.getStringCellValue().contains("Characters")) {
					results.characters = cell.getColumnIndex();
				}
			}
			if (results != null) {
				results.validate();
				return results;
			}
		}
		throw new CATAnalysisParserException();
	}

	private class CellOffsets {
		int rowName = -1;
		int segments = -1;
		int words = -1;
		int characters = -1;

		void validate() throws CATAnalysisParserException {
			if (rowName == -1 || segments == -1 || words == -1 || characters == -1) {
				throw new CATAnalysisParserException();
			}
		}
	}
}
