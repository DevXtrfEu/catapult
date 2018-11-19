package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;


public class CATAnalysisParserTransitNXTWorkstationProgressReportHTML extends CATAnalysisParserBase {

	@Override
	public CATTool getCATTool() {
		return CATTool.TRANSIT_NXT;
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

	private void parseBlock(CATAnalysis result, Scanner sc) throws CATAnalysisParserException {
		sc.findWithinHorizon("<td>Totals</td>", 0);

		sc.findWithinHorizon("<td>", 0);
		result.add(Unit.WORD, MatchType.NOT_TRANSLATED, getValue(sc));

		sc.findWithinHorizon("<td>", 0);
		result.add(Unit.WORD, MatchType.CHECK_PRETRANSLATION, getValue(sc));

		sc.findWithinHorizon("<td>", 0);
		result.add(Unit.WORD, MatchType.TRANSLATED, getValue(sc));

		sc.findWithinHorizon("<td>", 0);
		result.add(Unit.WORD, MatchType.TOTAL, getValue(sc));
	}

	private BigDecimal getValue(Scanner sc) {
		String s = sc.next();
		s = s.substring(0, s.indexOf("<"));
		return new BigDecimal(s);
	}
}
