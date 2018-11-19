package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.Unit.CHARACTER;
import static eu.xtrf.cat.Unit.SEGMENT;
import static eu.xtrf.cat.Unit.WORD;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

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


public class CATAnalysisParserXTMXLS extends CATAnalysisParserBase {
	
	private Sheet sheet;
	private Row allRow;
	private Map<String, Short> positions;
	
	@Override
	public CATTool getCATTool() {
		return CATTool.XTM;
	}

	@Override
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
		
		try {
			Workbook wb = WorkbookFactory.create(is);

			sheet = wb.getSheetAt(0);
			allRow = findRowWithText("All");
			initializeMap();
			parse(result);
			result.verifyDataIntegrity();
		} catch (InvalidFormatException | IOException e) {
			throw new CATAnalysisParserException(e);
		}
		return result;
	}

	private void parse(CATAnalysis result) throws CATAnalysisParserException {
		parseBlockLine(result, "total count", MatchType.TOTAL);
		parseBlockLine(result, "non-translatable", MatchType.NON_TRANSLATABLE);
		parseBlockLine(result, "ICE match", MatchType.ICE_MATCH);
		parseBlockLine(result, "leveraged match", MatchType.LEVERAGED_MATCH);
		parseBlockLine(result, "95-99", MatchType.PERCENT_95_99);
		parseBlockLine(result, "85-94", MatchType.PERCENT_85_94);
		parseBlockLine(result, "75-84", MatchType.PERCENT_75_84);
		parseBlockLine(result, "no matching", MatchType.NO_MATCH);
		parseBlockLine(result, "repeat", MatchType.REPETITIONS);

	}

	private Row findRowWithText(String pattern) throws CATAnalysisParserException {
		Iterator<Row> it = sheet.rowIterator();
		while (it.hasNext()) {
			Row currentRow = it.next();
			String value = currentRow.getCell(0).getStringCellValue();
			if (value.contains(pattern)) {
				return currentRow;
			}
		}
		throw new CATAnalysisParserException();
	}

	private void initializeMap() {
		positions = new LinkedHashMap<String, Short>();
		Row row = sheet.getRow(0);
		for (short index = 0; index <= row.getLastCellNum(); index++) {
			Cell cell = row.getCell(index);
			if (cell != null) {
				String currentColumn = row.getCell(index).getStringCellValue();
				positions.put(currentColumn, index);
			}
		}
	}

	/**
	 * Method takes for instance '95-99' and finds suitable columns for them. We are interested in two types of them - segments and words
	 */
	private void parseBlockLine(final CATAnalysis result, final String columnName, final MatchType matchType) throws CATAnalysisParserException {
		boolean found = false;
		
		for (String column : positions.keySet()) {
			if (column.contains(columnName)) {
				found = true;
				
				int index = positions.get(column);
				int value = (int) allRow.getCell(index).getNumericCellValue();

				if (column.contains("Words")) {
					result.add(WORD, matchType, value);
				} else if (column.contains("Segments")) {
					result.add(SEGMENT, matchType, value);
				} else if (column.contains("Characters") && !column.contains("excluding")) {
					result.add(CHARACTER, matchType, value);
				}

			}
		}

		if(!found) {
			throw new CATAnalysisParserException();
		}
	}
}
