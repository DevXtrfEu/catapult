package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;

import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author Marek Guzowski
 */
public class CATAnalysisParserSDLTradosMHTML extends CATAnalysisParserBase {
	@Override
	public CATTool getCATTool() {
		return CATTool.TRADOS;
	}

	@Override
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);

		Scanner sc = new Scanner(is, "UTF-8");
		sc.useLocale(Locale.US);
		parseBlock(result, sc);

		result.verifyDataIntegrity();
		return result;
	}

	private void parseBlock(CATAnalysis result, Scanner sc) {
		sc.findWithinHorizon("Total NOT weigthed", 0);
		fillMatchType(result, sc, MatchType.X_TRANSLATED);
		fillMatchType(result, sc, MatchType.REPETITIONS);
		fillMatchType(result, sc, MatchType.PERCENT_100);
		fillMatchType(result, sc, MatchType.PERCENT_95_99);
		fillMatchType(result, sc, MatchType.PERCENT_85_94);
		fillMatchType(result, sc, MatchType.PERCENT_75_84);
		fillMatchType(result, sc, MatchType.PERCENT_50_74);
		fillMatchType(result, sc, MatchType.NO_MATCH);
		fillMatchType(result, sc, MatchType.TOTAL);
	}

	private void fillMatchType(CATAnalysis result, Scanner sc, MatchType type) {
		sc.findWithinHorizon("<DIV", 50);
		sc.findWithinHorizon(">", 50);
		result.set(Unit.WORD, type, Integer.valueOf(sc.next().split("<")[0]));
	}
}
