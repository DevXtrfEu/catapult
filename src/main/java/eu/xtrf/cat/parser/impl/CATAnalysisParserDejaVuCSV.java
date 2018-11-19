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


public class CATAnalysisParserDejaVuCSV extends CATAnalysisParserBase {

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

	private void parseBlock(final CATAnalysis result, final Scanner sc) throws CATAnalysisParserException {
		parseBlockLine(result, sc, "Duplicates,", MatchType.DUPLICATES);		
		parseBlockLine(result, sc, "Guaranteed Matches,", MatchType.GUARANTEED);
		parseBlockLine(result, sc, "Exact Matches,", MatchType.EXACT);
		parseBlockLine(result, sc, "95% - 99%,", MatchType.PERCENT_95_99);
		parseBlockLine(result, sc, "85% - 94%,", MatchType.PERCENT_85_94);
		parseBlockLine(result, sc, "75% - 84%,", MatchType.PERCENT_75_84);
		parseBlockLine(result, sc, "50% - 74%,", MatchType.PERCENT_50_74);
		parseBlockLine(result, sc, "No Match,", MatchType.NO_MATCH);
		
		if(sc.findWithinHorizon("Locked,", 0) != null) {
			String[] data = sc.next().split(",");
			
			result.add(Unit.SEGMENT, MatchType.LOCKED, new BigDecimal(data[0]));
			result.add(Unit.WORD, MatchType.LOCKED, new BigDecimal(data[1]));
			result.add(Unit.CHARACTER, MatchType.LOCKED, new BigDecimal(data[2]));
		}
		
		parseBlockLine(result, sc, "Total,", MatchType.TOTAL);
		
		result.verifyDataIntegrity();
	}
	
	private void parseBlockLine(final CATAnalysis result, final Scanner sc, final String firstCol, final MatchType matchType){
		sc.findWithinHorizon(firstCol, 0);
		
		String[] data = sc.next().split(",");
		
		result.add(Unit.SEGMENT, matchType, new BigDecimal(data[0]));
		result.add(Unit.WORD, matchType, new BigDecimal(data[1]));
		result.add(Unit.CHARACTER, matchType, new BigDecimal(data[2]));	
	}
}
