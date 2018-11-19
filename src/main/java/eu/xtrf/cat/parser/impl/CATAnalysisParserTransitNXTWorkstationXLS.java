package eu.xtrf.cat.parser.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.DataFormatException;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;

public class CATAnalysisParserTransitNXTWorkstationXLS extends CATAnalysisParserBase {

	@Override
	public CATTool getCATTool() {
		return CATTool.TRANSIT_NXT;
	}

	final List<MatchingItem> matchingItems = new LinkedList<CATAnalysisParserTransitNXTWorkstationXLS.MatchingItem>(Arrays.asList(
			new MatchingItem(new HashSet<String>(Arrays.asList("Pretranslated", "Pretradotto", "Vorübersetzt")), MatchType.PRETRANSLATED),
			new MatchingItem(new HashSet<String>(Arrays.asList("Check pretranslation", "Pretradotto parzialmente", "Vorübersetzung prüfen")), MatchType.CHECK_PRETRANSLATION),
			new MatchingItem(new HashSet<String>(Arrays.asList("100", "Corrispondenza al 100")), MatchType.PERCENT_100),
			new MatchingItem(new HashSet<String>(Arrays.asList("Fuzzy 99 - 75")), MatchType.PERCENT_75_99),
			new MatchingItem(new HashSet<String>(Arrays.asList("Fuzzy 99 - 95")), MatchType.PERCENT_95_99),
			new MatchingItem(new HashSet<String>(Arrays.asList("Fuzzy 99 - 90")), MatchType.PERCENT_90_99),
			new MatchingItem(new HashSet<String>(Arrays.asList("Fuzzy 94 - 85")), MatchType.PERCENT_85_94),
			new MatchingItem(new HashSet<String>(Arrays.asList("Fuzzy 84 - 75")), MatchType.PERCENT_75_84),
			new MatchingItem(new HashSet<String>(Arrays.asList("Fuzzy 74 - 50")), MatchType.PERCENT_50_74),
			new MatchingItem(new HashSet<String>(Arrays.asList("Fuzzy 99 - 70")), MatchType.PERCENT_70_99),
			new MatchingItem(new HashSet<String>(Arrays.asList("Fuzzy 89 - 71")), MatchType.PERCENT_71_89),
			new MatchingItem(new HashSet<String>(Arrays.asList("Translated (paragraph context)", "Tradotto (contesto paragrafo)")), MatchType.TRANSLATED_PARAGRAPH),
			new MatchingItem(new HashSet<String>(Arrays.asList("Translated (structure context)", "Tradotto (contesto struttura)")), MatchType.TRANSLATED_STRUCTURE),
			new MatchingItem(new HashSet<String>(Arrays.asList("Internal repetitions", "Ripetizioni interne")), MatchType.REPETITIONS),
			new MatchingItem(new HashSet<String>(Arrays.asList("Units not translated", "Unità non tradotte", "Nicht übersetzte Einheiten")), MatchType.NOT_TRANSLATED),
			new MatchingItem(new HashSet<String>(Arrays.asList("Total", "Totale", "Gesamt")), MatchType.TOTAL)));

    final List<MatchType> checkForTotalAdjustmentMatchTypes = new LinkedList<>(Arrays.asList(MatchType.TRANSLATED_PARAGRAPH, MatchType.TRANSLATED_STRUCTURE));

	final MatchingItem totalItem = new MatchingItem(new HashSet<String>(Arrays.asList("Totals", "Summe")), null);
	final MatchingItem fileItem = new MatchingItem(new HashSet<String>(Arrays.asList("File", "Datei")), null);
	final MatchingItem repetitionsItem = new MatchingItem(new HashSet<String>(Arrays.asList("Internal repetitions found (reduced by limit)", "Gefundene Int. Repetitions (reduziert durch den Grenzwert)")), null);
    final MatchingItem expandedTotalItem = new MatchingItem(new HashSet<String>(Arrays.asList("Totals with expansion factor","Summe mit Dehnungsfaktor")), null);

    boolean hadSeparateRepetitions = false;
	boolean wasOdd = false; //rounding problems for expansion factor equal to 0.5 . Not enough examples to do it right. Trying to guess the correction factor
	BigDecimal bigSum = BigDecimal.ZERO;
	BigDecimal repetitions = BigDecimal.ZERO;
	BigDecimal lastSum = BigDecimal.ZERO;
    BigDecimal oldPretranslated = BigDecimal.ZERO.subtract(BigDecimal.ONE);
    boolean hasMissingPretranslated = false;

	class MatchingItem {

		public MatchingItem(Set<String> key, MatchType value) {
			super();
			this.key = key;
			this.value = value;
		}

		public boolean doMatch(String k) {
			return this.key.contains(k);
		}

		private Set<String> key;
		private MatchType value;
	}

	private MatchType columnNameToEnum(String key) {
		for (MatchingItem item : matchingItems) {
			if (item.doMatch(key))
				return item.value;
		}
		return null;
	}

	@Override
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);

		if (!fileName.endsWith(".xls"))
			throw new CATAnalysisParserException();

        Map<Integer, MatchType> columnToEnum = new HashMap<Integer, MatchType>();
        Map<MatchType, BigDecimal> totalValuesMap = new HashMap<>();
        BigDecimal expandedTotalAdjustmentValue = BigDecimal.ZERO;

		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(is);
			List<?> rows = document.selectNodes("/*[name()='Workbook']/*[name()='Worksheet'][1]/*[name()='Table']/*[name()='Row']");
			for (Object o : rows) {
                Element row = (Element) o;
                List<?> cells = row.elements("Cell");
                if (cells.size() < 7) {
                    continue;
                }
                Element firstCell = (Element) cells.get(0);
                Element firstCellData = firstCell.element("Data");
				String s = null;
				if (firstCellData != null) s = firstCellData.getText();

                if (firstCellData != null && fileItem.doMatch(firstCellData.getText())) {
                    for (int column = 1; column < cells.size(); column++) {
                        MatchType type = columnNameToEnum(extractStringFromCell((Element) cells.get(column)));
                        if (type != null)
                            columnToEnum.put(column, type);
                    }
                }
                String ss = null;
                if (firstCellData != null)
                    ss = firstCellData.getText();

                if (firstCellData != null && repetitionsItem.doMatch(firstCellData.getText())) {
                    result = new CATAnalysis(getCATTool(), fileName);
                    BigDecimal value = extractValueFromCell((Element) cells.get(cells.size() - 1));
					repetitions = value;
                    result.add(Unit.WORD, MatchType.REPETITIONS, value);
                    result.add(Unit.WORD, MatchType.TOTAL, value);
                    hadSeparateRepetitions = true;
                    if ((value.compareTo(BigDecimal.ZERO) > 0) && (bigSum.subtract(value).intValue() % 2 == 1)) {
                        wasOdd = true;
                    }
                }

                if (firstCellData != null && (
                        totalItem.doMatch(firstCellData.getText())
                        || expandedTotalItem.doMatch(firstCellData.getText())
                ) ) {

                    if (totalItem.doMatch(firstCellData.getText())||(hadSeparateRepetitions && expandedTotalItem.doMatch(firstCellData.getText()))){

                        if (columnToEnum.size() == 0)
                            throw new DataFormatException();
                        for (int column = 1; column < cells.size(); column++) {
                            MatchType col2enum = columnToEnum.get(column);
                            if (col2enum != null) {
                                BigDecimal value = extractValueFromCell((Element) cells.get(column));

                                if (checkForTotalAdjustmentMatchTypes.contains(col2enum)) {
                                    if (totalItem.doMatch(firstCellData.getText())) {
                                        totalValuesMap.put(col2enum, value);
                                    } else if (expandedTotalItem.doMatch(firstCellData.getText())) {
                                        BigDecimal totalValue = totalValuesMap.getOrDefault(col2enum, BigDecimal.ZERO);
                                        if ((totalValue.compareTo(BigDecimal.ZERO) > 0) && (value.compareTo(BigDecimal.ZERO) == 0)) {
                                            // we gonna adjust our odd rounding condition below
                                            expandedTotalAdjustmentValue = expandedTotalAdjustmentValue.add(totalValue);
                                        }
                                    }
                                }

                                if (!hadSeparateRepetitions) bigSum = value;//used for rounding detection
								lastSum = value;

                                if (col2enum.compareTo(MatchType.PRETRANSLATED)==0) {//used for missing pretranslated detection on last line
                                    if (oldPretranslated.compareTo(BigDecimal.ZERO)<0) oldPretranslated = value;

                                    if ((value.compareTo(BigDecimal.ZERO)==0)&&(oldPretranslated.compareTo(BigDecimal.ZERO)>0))
                                        hasMissingPretranslated = true;
                                }

                                result.add(Unit.WORD, col2enum, value);
                            }
                        }
                    }
                }
            }
//rounding problems for expansion factor equal to 0.5 . Not enough examples to do it right.
// Sometimes the applied factor is 0.5 sometimes 0.25. Trying to guess the correction for rounding

            if (!(lastSum.subtract(bigSum).compareTo(BigDecimal.ONE) > 0)) {
                if (!hasMissingPretranslated
                        && (bigSum.subtract(repetitions).subtract(expandedTotalAdjustmentValue).subtract(lastSum).abs().compareTo(BigDecimal.ONE) > 0)) {
                    if (wasOdd)
                        result.add(Unit.WORD, MatchType.TOTAL, BigDecimal.ONE);
				}
            }

            result.verifyDataIntegrity();
            return result;


		} catch (Exception e) {
			throw new CATAnalysisParserException("Parser error", e);
		}

	}

	private BigDecimal extractValueFromCell(Element cell) throws DataFormatException {
		Element cellData = cell.element("Data");
		if (cellData == null) {
			throw new DataFormatException();
		}
		return new BigDecimal(cellData.getText());
	}

	private String extractStringFromCell(Element cell) throws DataFormatException {
		Element cellData = cell.element("Data");
		if (cellData == null) {
			throw new DataFormatException();
		}
		return cellData.getText();
	}
}
