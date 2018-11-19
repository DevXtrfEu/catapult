package eu.xtrf.cat.parser.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;

public class CATAnalysisParserWordfastXLS extends CATAnalysisParserBase {

	private Sheet sheet;
	private Row baseRow;
	private Map<String, Short> positions;

	@Override
	public CATTool getCATTool() {
		return CATTool.WORDFAST;
	}

	@Override
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {

		try {
			CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
			
			Workbook wb = WorkbookFactory.create(is);
			sheet = wb.getSheetAt(0);
			baseRow = sheet.getRow(0);

			initializeMap();
			parse(result);
			
			result.verifyDataIntegrity();
			
			return result;

		} catch (InvalidFormatException| IOException e) {
			throw new CATAnalysisParserException("Invalid format", e);
		} 
	}

	private void parse(CATAnalysis result) {
		parseBlockLine(result, "Total", MatchType.TOTAL);
		parseBlockLine(result,"100% Matches", MatchType.PERCENT_100);
		parseBlockLine(result,"\"95%-99%\"", MatchType.PERCENT_95_99);
		parseBlockLine(result,"\"85%-94%\"", MatchType.PERCENT_85_94);
		parseBlockLine(result,"\"75%-84%\"", MatchType.PERCENT_75_84);
		parseBlockLine(result,"\"50%-74%\"", MatchType.PERCENT_50_74);
		parseBlockLine(result,"No Match", MatchType.NO_MATCH);
		parseBlockLine(result,"Repetitions", MatchType.REPETITIONS);
	}

	private void parseBlockLine(final CATAnalysis result, final String columnName, final MatchType matchType) {
		int segmentsValue = 0;
		int wordsValue = 0;
		int columnIndex = positions.get(columnName);
		for (int rowIndex = 2; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row currentRow = sheet.getRow(rowIndex);
			segmentsValue += (int) currentRow.getCell(columnIndex).getNumericCellValue();
			wordsValue += (int) currentRow.getCell(columnIndex + 1).getNumericCellValue();
		}
		result.add(Unit.SEGMENT, matchType, new BigDecimal(segmentsValue));
		result.add(Unit.WORD, matchType, new BigDecimal(wordsValue));
	}

	private void initializeMap() {
		positions = new LinkedHashMap<String, Short>();
		for (short index = 0; index <= baseRow.getLastCellNum(); index++) {
			Cell cell = baseRow.getCell(index);
			if (cell != null) {
				String currentColumn = baseRow.getCell(index).getStringCellValue();
				positions.put(currentColumn, index);
			}
		}
	}
}
