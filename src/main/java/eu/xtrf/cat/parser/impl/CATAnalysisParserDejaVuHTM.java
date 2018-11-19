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

public class CATAnalysisParserDejaVuHTM extends CATAnalysisParserBase {
	
	@Override
	public CATTool getCATTool() {
		return CATTool.DEJA_VU;
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
		setData(result, sc, "Duplicates", MatchType.DUPLICATES);
		setData(result, sc, "Guaranteed Matches", MatchType.GUARANTEED);
		setData(result, sc, "Exact Matches", MatchType.EXACT);
		setData(result, sc, "95% - 99%", MatchType.PERCENT_95_99);
		setData(result, sc, "85% - 94%", MatchType.PERCENT_85_94);
		setData(result, sc, "75% - 84%", MatchType.PERCENT_75_84);
		setData(result, sc, "50% - 74%", MatchType.PERCENT_50_74);
		setData(result, sc, "No Match", MatchType.NO_MATCH);
		setData(result, sc, "Locked", MatchType.LOCKED, false);
		setData(result, sc, "Total", MatchType.TOTAL);
		
		result.verifyDataIntegrity();
	}

	private void setData(CATAnalysis result, Scanner sc, String firstCol, MatchType matchType) throws CATAnalysisParserException {
		setData(result, sc, firstCol, matchType, true);
	}

	private void setData(CATAnalysis result, Scanner sc, String firstCol, MatchType matchType, boolean required) throws CATAnalysisParserException {
		if (sc.findWithinHorizon(firstCol, 0) != null) {
			sc.findWithinHorizon(">", 0);
			result.add(Unit.SEGMENT, matchType, getValue(sc));
			result.add(Unit.WORD, matchType, getValue(sc));
			result.add(Unit.CHARACTER, matchType, getValue(sc));
		}
		else if (required) {
			throw new CATAnalysisParserException();
		}
	}
	
	private BigDecimal getValue(Scanner sc) {
		sc.findWithinHorizon(">", 0);
		String s = sc.next();
		s = s.substring(0, s.indexOf("<"));
		return new BigDecimal(s);
	}
}
