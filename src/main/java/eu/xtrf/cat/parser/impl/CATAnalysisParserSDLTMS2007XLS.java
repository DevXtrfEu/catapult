package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Marek Guzowski
 */
public class CATAnalysisParserSDLTMS2007XLS extends CATAnalysisParserBase{
	@Override
	public CATTool getCATTool() {
		return CATTool.TRADOS;
	}

	@Override
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);

		Sheet sheet;
		try {
			Workbook wb = WorkbookFactory.create(is);
			sheet = wb.getSheetAt(0);
		} catch (InvalidFormatException | IOException e) {
			throw new CATAnalysisParserException(e);
		}


		doParse(result, sheet);
		result.verifyDataIntegrity();
		return result;
	}

	private void doParse(CATAnalysis result, Sheet sheet) throws CATAnalysisParserException {
		Row totalRow = getTotalRow(sheet);
		ColumnIndexes indexes = calculateIndexes(sheet);
		result.set(Unit.WORD, MatchType.TOTAL, getValue(totalRow, indexes.wordCount));
		result.set(Unit.WORD, MatchType.REPETITIONS, getValue(totalRow, indexes.repetitions));
		if (indexes.perfectMatch >=0) {
			result.set(Unit.WORD, MatchType.X_TRANSLATED, getValue(totalRow, indexes.perfectMatch));
		}
		result.set(Unit.WORD, MatchType.PERCENT_100, getValue(totalRow, indexes.percent100));
		result.set(Unit.WORD, MatchType.PERCENT_95_99, getValue(totalRow, indexes.percent95));
		result.set(Unit.WORD, MatchType.PERCENT_85_94, getValue(totalRow, indexes.percent85));
		result.set(Unit.WORD, MatchType.NO_MATCH, getValue(totalRow, indexes.percent0));
	}

	private int getValue(Row totalRow, int index) {
		return Double.valueOf(totalRow.getCell(index).getNumericCellValue()).intValue();
	}

	private ColumnIndexes calculateIndexes(Sheet sheet) throws CATAnalysisParserException {
		Row languagePairRow = getLastRow(sheet, "Language Pair");
		ColumnIndexes indexes = new ColumnIndexes();
		for (Cell cell : languagePairRow) {
			int columnIndex = cell.getColumnIndex();
			switch (cell.getStringCellValue()) {
				case "Word Count":
					indexes.wordCount = columnIndex;
					break;
				case "Repetitions":
					indexes.repetitions = columnIndex;
					break;
				case "PerfectMatch":
					indexes.perfectMatch = columnIndex;
					break;
				case "100%":
					indexes.percent100 = columnIndex;
					break;
				case "99-95%":
					indexes.percent95 = columnIndex;
					break;
				case "94-85%":
					indexes.percent85 = columnIndex;
					break;
				case "84-0%":
					indexes.percent0 = columnIndex;
			}
		}
		return indexes;
	}

	private Row getTotalRow(Sheet sheet) throws CATAnalysisParserException {
		return getLastRow(sheet, "Total");
	}

	private Row getLastRow(Sheet sheet, String expectedValue) throws CATAnalysisParserException {
		int currentRow = sheet.getLastRowNum();
		while (currentRow >=0 ) {
			Row row = sheet.getRow(currentRow);
			for (Cell cell : row) {
				if (cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue().equals(expectedValue)) {
					return row;
				}
			}

			currentRow--;
		}
		throw new CATAnalysisParserException();
	}

	class ColumnIndexes {
		int wordCount;
		int repetitions;
		int perfectMatch = -1; //to distinguish a a file without this column
		int percent100;
		int percent95;
		int percent85;
		int percent0;
	}
}
