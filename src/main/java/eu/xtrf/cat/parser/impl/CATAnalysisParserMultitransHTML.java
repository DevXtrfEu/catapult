package eu.xtrf.cat.parser.impl;

import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;

public class CATAnalysisParserMultitransHTML extends CATAnalysisParserBase {

	@Override
	public CATTool getCATTool() {
		return CATTool.MULTITRANS;
	}

	@Override
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
		
		Scanner sc = new Scanner(is, "UTF-16");
		sc.useLocale(Locale.US);
		
		parseBlock(result, sc);
		
		return result;
	}

	private void parseBlock(CATAnalysis result, Scanner sc) throws CATAnalysisParserException {
		sc.findWithinHorizon("Analysis Summary", 0);
		sc.findWithinHorizon("100% segment matches", 0);
		int segments100 = 0;
		int words100 = 0;
		sc.findWithinHorizon("From TextBase", 0);
		segments100 += getInt(sc, false);
		words100 += getInt(sc, false);

		sc.findWithinHorizon("From TermBase", 0);
		segments100 += getInt(sc, false);
		words100 += getInt(sc, false);

		result.set(Unit.SEGMENT, MatchType.PERCENT_100, segments100);
		result.set(Unit.WORD, MatchType.PERCENT_100, words100);

		sc.findWithinHorizon("85-99 % segment matches", 0);
		int segments85 = getInt(sc, false);
		int words85 = getInt(sc, false);
		// TODO probably some work here, because XTRF doesn't have range 85-99
		result.set(Unit.SEGMENT, MatchType.PERCENT_85_99, segments85);
		result.set(Unit.WORD, MatchType.PERCENT_85_99, words85);

		sc.findWithinHorizon("75-84 % segment matches", 0);
		result.set(Unit.SEGMENT, MatchType.PERCENT_75_84, getInt(sc, false));
		result.set(Unit.WORD, MatchType.PERCENT_75_84, getInt(sc, false));

		sc.findWithinHorizon("Internal repetitions", 0);
		sc.findWithinHorizon("Exact match", 0);
		result.set(Unit.SEGMENT, MatchType.REPETITIONS, getInt(sc, false));
		result.set(Unit.WORD, MatchType.REPETITIONS, getInt(sc, false));
		
		sc.findWithinHorizon("75-99% match", 0);
		result.set(Unit.SEGMENT, MatchType.INTERNAL_75_99, getInt(sc, false));
		result.set(Unit.WORD, MatchType.INTERNAL_75_99, getInt(sc, false));

		sc.findWithinHorizon("New segments", 0);
		result.set(Unit.SEGMENT, MatchType.NEW, getInt(sc, false));
		result.set(Unit.WORD, MatchType.NEW, getInt(sc, false));

		sc.findWithinHorizon("Total", 0);
		result.set(Unit.SEGMENT, MatchType.TOTAL, getInt(sc, true));
		result.set(Unit.WORD, MatchType.TOTAL, getInt(sc, true));

		result.verifyDataIntegrity();
	}

	private int getInt(Scanner sc, boolean total) {
		if (total) {
			sc.findWithinHorizon("<span class=\"FieldName\">", 0);
		} else {
			sc.findWithinHorizon("<span class=\"FieldValue\">", 0);
		}
		String s = sc.next();
		s = s.substring(0, s.indexOf("<"));
		int result = Integer.parseInt(s);
		return result;
	}
}
