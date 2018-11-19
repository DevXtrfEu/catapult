package eu.xtrf.cat.parser.impl;

import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.parser.CATAnalysisParserException;

/**
 * Analysis of SDL TMS 2009 HTM metrics.
 */
public class CATAnalysisParserSDLTMS2009HTM extends CATAnalysisParserSDLTMSHTM {
	
	public CATAnalysisParserSDLTMS2009HTM() {
		super(CATAnalysisLocalizedSDLTMSParser.ENGLISH);
	}
	
	public CATAnalysisParserSDLTMS2009HTM(Map<LanguageKey, String> languageKeys) {
		super(languageKeys);
	}

	@Override
	public CATTool getCATTool() {
		return CATTool.SDL_TRADOS;
	}
	
	@Override
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
		
		doParse(result, fileName, is);
				
		return result;
	}
	
	@Override
	protected void parseBlock(CATAnalysis result, Scanner sc) throws CATAnalysisParserException {
		sc.findWithinHorizon(getLocalization(LanguageKey.REPORT_INTERNAL_FUZZY_MATCH_LEVERAGE) + "</td>", 0);
		sc.next();
		String s = sc.next();
		boolean fuzzyMatch = s.contains(getLocalization(LanguageKey.YES));
		
		sc.findWithinHorizon(getLocalization(LanguageKey.TOTALS), 0);
		setData(result, true, sc, getLocalization(LanguageKey.PERFECT_MATCH), MatchType.PERFECT_MATCH);

		if(!fuzzyMatch){
			setData(result, true, sc, getLocalization(LanguageKey.REPETITIONS), MatchType.REPETITIONS);
		}
		
		modifyData(result, true, sc, getLocalization(LanguageKey.CONTEXT_MATCH), MatchType.CONTEXT_MATCH, 1000);
		setData(result, true, sc, getLocalization(LanguageKey.PERCENT_100), MatchType.PERCENT_100);
		setData(result, true, sc, PERCENT_95_99, MatchType.PERCENT_95_99);
		setData(result, true, sc, PERCENT_85_94, MatchType.PERCENT_85_94);
		setData(result, true, sc, PERCENT_75_84, MatchType.PERCENT_75_84);
		setData(result, true, sc, PERCENT_50_74, MatchType.PERCENT_50_74);

		if (fuzzyMatch) {
			setData(result, true, sc, getLocalization(LanguageKey.REPETITIONS), MatchType.REPETITIONS);
			modifyData(result, true, sc, PERCENT_95_99, MatchType.INTERNAL_95_99);
			modifyData(result, true, sc, PERCENT_85_94, MatchType.INTERNAL_85_94);
			modifyData(result, true, sc, PERCENT_75_84, MatchType.INTERNAL_75_84);
			modifyData(result, true, sc, PERCENT_50_74, MatchType.INTERNAL_50_74);
		}

		setData(result, true, sc, getLocalization(LanguageKey.NEW), MatchType.NEW);
		setData(result, true, sc, "<th class=\"Total\">" + getLocalization(LanguageKey.TOTAL), MatchType.TOTAL);
	}
}
