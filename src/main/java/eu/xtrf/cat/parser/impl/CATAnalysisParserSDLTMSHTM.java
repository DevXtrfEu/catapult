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
 * CATAnalysisParserSDLTMSHTM.java, created: 03-08-2012.
 */
package eu.xtrf.cat.parser.impl;

import java.io.InputStream;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;

/**
 * Common for SDL TMS 2009 and SDL TMS 2011 html analysis.
 * 
 * @author Dariusz Golab
 */
public abstract class CATAnalysisParserSDLTMSHTM extends CATAnalysisLocalizedSDLTMSParser {
	public static String PERCENT_95_99 = "95% - 99%";
	public static String PERCENT_85_94 = "85% - 94%";
	public static String PERCENT_75_84 = "75% - 84%";
	public static String PERCENT_50_74 = "50% - 74%";
	
	private static final Set<String> optional = ImmutableSet.of(PERCENT_50_74);

	public CATAnalysisParserSDLTMSHTM(Map<LanguageKey, String> languageKeys) {
		super(languageKeys);
	}
	
	public void doParse(CATAnalysis result, String fileName, InputStream is) throws CATAnalysisParserException {		
		Scanner sc = new Scanner(is, "UTF-8");
		sc.useLocale(Locale.US);
		parseBlock(result, sc);
		result.verifyDataIntegrity();
	}

	protected abstract void parseBlock(CATAnalysis result, Scanner sc) throws CATAnalysisParserException;

	public void setData(CATAnalysis result, boolean hasWordsColumn, Scanner sc, String firstCol, MatchType matchType) throws CATAnalysisParserException {
		setData(result, hasWordsColumn, sc, firstCol, matchType, false);
	}
	
	public boolean setDataOptional(CATAnalysis result, boolean hasWordsColumn, Scanner sc, String firstCol, MatchType matchType) throws CATAnalysisParserException {
		return setData(result, hasWordsColumn, sc, firstCol, matchType, true);
	}

	public boolean setData(CATAnalysis result, boolean hasWordsColumn, Scanner sc, String firstCol, MatchType matchType, boolean optional) throws CATAnalysisParserException {
		if (optional && firstCol == null) {
			return false;
		}
		if (findText(sc, firstCol, optional)) {
			result.set(Unit.SEGMENT, matchType, getInt(sc));
			if(hasWordsColumn) {
				result.set(Unit.WORD, matchType, getInt(sc));
			}
			result.set(Unit.CHARACTER, matchType, getInt(sc));
			while (!sc.next().endsWith("</td>")); //empty block in while is deliberately put here!
			result.set(Unit.TOKEN, matchType, getInt(sc));
			return true;
		}
		return false;
	}

	public void modifyData(CATAnalysis result, boolean hasWordsColumn, Scanner sc, String firstCol, MatchType matchType) throws CATAnalysisParserException {
		modifyData(result, hasWordsColumn, sc, firstCol, matchType, 0);
	}

	public void modifyData(CATAnalysis result, boolean hasWordsColumn, Scanner sc, String firstCol, MatchType matchType, int horizon) throws CATAnalysisParserException {
		if (findText(sc, firstCol, false, horizon)) {
			result.add(Unit.SEGMENT, matchType, getInt(sc));
			if(hasWordsColumn) {
				result.add(Unit.WORD, matchType, getInt(sc));
			}
			result.add(Unit.CHARACTER, matchType, getInt(sc));
			sc.next();
			result.set(Unit.TOKEN, matchType, getInt(sc));
		}
	}

	private boolean findText(Scanner sc, String firstCol, boolean optional) throws CATAnalysisParserException {
		return findText(sc, firstCol, optional, 0);
	}

	private boolean findText(Scanner sc, String firstCol, boolean optional, int horizon) throws CATAnalysisParserException {
		if (sc.findWithinHorizon(firstCol, horizon) == null) {
			if (isOptional(firstCol) || optional) {
				return false;
			}
			throw new CATAnalysisParserException();
		}
		sc.findWithinHorizon("</th>", 0);
		return true;
	}

	private int getInt(Scanner sc) {
		sc.findWithinHorizon(">", 0);
		String s = sc.next();
		s = s.substring(0, s.indexOf("<"));
		int result = Integer.parseInt(s);
		return result;
	}

	protected boolean isOptional(String text) {
		return optional.contains(text);
	}
}
