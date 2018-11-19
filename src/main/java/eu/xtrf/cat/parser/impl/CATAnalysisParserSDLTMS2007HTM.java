package eu.xtrf.cat.parser.impl;

import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;

public class CATAnalysisParserSDLTMS2007HTM extends CATAnalysisParserBase {
	
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
		
		return result;
	}

	private void parseBlock(CATAnalysis result, Scanner sc) throws CATAnalysisParserException {
		String perfectMatch = sc.findWithinHorizon("PerfectMatch", 0);
		
		sc.findWithinHorizon("Total</div>", 0);

		sc.findWithinHorizon("</div>", 0);
		sc.findWithinHorizon("</div>", 0);

		sc.findWithinHorizon(">", 0);
		result.set(Unit.WORD, MatchType.TOTAL, getInt(sc));

		sc.findWithinHorizon(">", 0);
		result.set(Unit.WORD, MatchType.REPETITIONS, getInt(sc));

		if(perfectMatch != null) {
			sc.findWithinHorizon(">", 0);
			result.set(Unit.WORD, MatchType.X_TRANSLATED, getInt(sc));
		}
		
		sc.findWithinHorizon(">", 0);
		result.set(Unit.WORD, MatchType.PERCENT_100, getInt(sc));

		sc.findWithinHorizon(">", 0);
		result.set(Unit.WORD, MatchType.PERCENT_95_99, getInt(sc));

		sc.findWithinHorizon(">", 0);
		result.set(Unit.WORD, MatchType.PERCENT_85_94, getInt(sc));

		sc.findWithinHorizon(">", 0);
		result.set(Unit.WORD, MatchType.NO_MATCH, getInt(sc));

		result.verifyDataIntegrity();
	}

	private int getInt(Scanner sc) {
		String s = sc.next();
		s = s.substring(0, s.indexOf("<"));
		int result = Integer.parseInt(s);
		return result;
	}
}
