/*
 * XTRF Translation Management System Ltd.
 * 
 * 		http://www.xtrf.eu
 * 
 * Copyright (c) 2005-2012 Radzisz Communications and EUTECert
 * 
 * 		http://www.eutecert.eu
 * 		http://www.radzisz.com
 * 
 * All rights reserved.
 * 
 * CATAnalysisParserSDLTMS2011HTM.java, created: 03-08-2012.
 */
package eu.xtrf.cat.parser.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.parser.CATAnalysisParserException;

/**
 * Analysis of SDL TMS 2011 HTM metrics.
 * 
 * @author Dariusz Golab
 */
public class CATAnalysisParserSDLTMS2011HTM extends CATAnalysisParserSDLTMSHTM {

	private static final Set<String> optional = ImmutableSet.of(PERCENT_75_84);

	public CATAnalysisParserSDLTMS2011HTM() {
		super(CATAnalysisLocalizedSDLTMSParser.ENGLISH);
	}
	
	public CATAnalysisParserSDLTMS2011HTM(Map<LanguageKey, String> languageKeys) {
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
		boolean hasWordsColumn = hasWordsColumn(sc);
		boolean lockedSet = setDataOptional(result, hasWordsColumn, sc, getLocalization(LanguageKey.LOCKED_SEGMENTS), MatchType.LOCKED_SEGMENTS);
		if (!lockedSet) {
			setData(result, hasWordsColumn, sc, getLocalization(LanguageKey.PERFECT_MATCH), MatchType.PERFECT_MATCH);
			if(hasWordsColumn) {
				setCharsPerWord(result, sc);
			}
		}
		else {
			if(hasWordsColumn) {
				setCharsPerWord(result, sc);
			}	
			setData(result, hasWordsColumn, sc, getLocalization(LanguageKey.PERFECT_MATCH), MatchType.PERFECT_MATCH);
		}
		setData(result, hasWordsColumn, sc, getLocalization(LanguageKey.CONTEXT_MATCH), MatchType.CONTEXT_MATCH);
		setData(result, hasWordsColumn, sc, getLocalization(LanguageKey.REPETITIONS), MatchType.REPETITIONS);
		setData(result, hasWordsColumn, sc, getLocalization(LanguageKey.CROSS_FILE_REPETITIONS), MatchType.CROSS_FILE_REPETITIONS);

		setData(result, hasWordsColumn, sc, getLocalization(LanguageKey.PERCENT_100), MatchType.PERCENT_100);
		setData(result, hasWordsColumn, sc, PERCENT_95_99, MatchType.PERCENT_95_99);
		setData(result, hasWordsColumn, sc, PERCENT_85_94, MatchType.PERCENT_85_94);
		setData(result, hasWordsColumn, sc, PERCENT_75_84, MatchType.PERCENT_75_84);
		setData(result, hasWordsColumn, sc, PERCENT_50_74, MatchType.PERCENT_50_74);

		if (fuzzyMatch) {
			setData(result, hasWordsColumn, sc, PERCENT_95_99, MatchType.INTERNAL_95_99);
			setData(result, hasWordsColumn, sc, PERCENT_85_94, MatchType.INTERNAL_85_94);
			setData(result, hasWordsColumn, sc, PERCENT_75_84, MatchType.INTERNAL_75_84);
			setData(result, hasWordsColumn, sc, PERCENT_50_74, MatchType.INTERNAL_50_74);
		}

		setData(result, hasWordsColumn, sc, getLocalization(LanguageKey.NEW), MatchType.NEW);
		
		setDataOptional(result, hasWordsColumn, sc, getLocalization(LanguageKey.ADAPTIVE_MT_BASELINE), MatchType.ADAPTIVE_MT_BASELINE);
		setDataOptional(result, hasWordsColumn, sc, getLocalization(LanguageKey.ADAPTIVE_MT_WITH_LEARNINGS), MatchType.ADAPTIVE_MT_WITH_LEARNINGS);
		
		setData(result, hasWordsColumn, sc, "<th class=\"Total\">" + getLocalization(LanguageKey.TOTAL), MatchType.TOTAL);
	}
	
	private boolean hasWordsColumn(Scanner sc) {
		return sc.findWithinHorizon("<th class=\"Unit\">" + getLocalization(LanguageKey.WORDS) + "</th>", 0) != null;
	}

	private void setCharsPerWord(CATAnalysis result, Scanner sc) {
		sc.findWithinHorizon(getLocalization(LanguageKey.CHARS_PER_WORD_STRING).trim(), 0);
		String value = sc.next();
		value = value.replace(' ',' ').trim() //char 160 (nbsp) -> char 32 (space)
			.replace("</th>", "")
			.substring(1); //eg ' :5.09</th>' -> '5.09'
		result.setCharsPerWord(new BigDecimal(value));
	}

	@Override
	protected boolean isOptional(String text) {
		return super.isOptional(text) || optional.contains(text) || getLocalization(LanguageKey.CROSS_FILE_REPETITIONS).equals(text);
	}
}
