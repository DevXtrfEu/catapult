package eu.xtrf.cat.parser.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;


public class CATAnalysisParserTransit extends CATAnalysisParserBase {

	@Override
	public CATTool getCATTool() {
		return CATTool.TRANSIT;
	}

	@Override
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
		
		Scanner scanner = new Scanner(is, "UTF-8");
		scanner.useLocale(Locale.US);

		// Pretranslated, Partially Translated, 100-95%, 94-85%, 84-75%,
		// 74-50%,
		// Remaining not translated units, Total

		BigDecimal[] minedNumbersWithRepetition = mineForDigitsAfterString(scanner, "Totals not reduced by  repetitions");
		BigDecimal[] minedNumbersWithoutRepetition = mineForDigitsAfterString(scanner, "Totals reduced by repetitions");
		result.add(Unit.WORD, MatchType.PRETRANSLATED, minedNumbersWithoutRepetition[0]);
		result.add(Unit.WORD, MatchType.PARTIALLY_TRANSLATED, minedNumbersWithoutRepetition[1]);
		result.add(Unit.WORD, MatchType.PERCENT_95_100, minedNumbersWithoutRepetition[2]);
		result.add(Unit.WORD, MatchType.PERCENT_85_94, minedNumbersWithoutRepetition[3]);
		result.add(Unit.WORD, MatchType.PERCENT_75_84, minedNumbersWithoutRepetition[4]);
		result.add(Unit.WORD, MatchType.PERCENT_50_74, minedNumbersWithoutRepetition[5]);
		result.add(Unit.WORD, MatchType.REMAINING_NOT_TRANSLATED, minedNumbersWithoutRepetition[6]);
		result.add(Unit.WORD, MatchType.REPETITIONS, minedNumbersWithRepetition[7].subtract(minedNumbersWithoutRepetition[7]));
		result.add(Unit.WORD, MatchType.TOTAL, minedNumbersWithRepetition[7]);

		result.verifyDataIntegrity();

		scanner.close();

		return result;
	}

	private BigDecimal[] mineForDigitsAfterString(Scanner scanner, String stringToFind) {
		BigDecimal[] minedNumbers = new BigDecimal[8];
		int minedNumbersCount = 0;

		scanner.findWithinHorizon(stringToFind, 0);
		while (minedNumbersCount < 8) {
			String dataString = scanner.nextLine();
			// remove all markups and brackets
			dataString = dataString.replaceAll("\\\\[\\d\\w]*", "").replaceAll("[\\{\\}]", "").trim();
			String[] numbers = dataString.split("\\s+");
			for (int i = 0; i < numbers.length && minedNumbersCount < 8; i++, minedNumbersCount++) {
				minedNumbers[minedNumbersCount] = new BigDecimal(numbers[i]);
			}
		}

		return minedNumbers;
	}
}
