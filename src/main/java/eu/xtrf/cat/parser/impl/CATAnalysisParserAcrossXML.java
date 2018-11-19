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
 * CATAnalysisParserAcrossXML.java, created: 12-11-2012.
 */
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

/**
 * Analysis of Across XML metrics.
 * 
 * @author Daniel Bochniak
 */
public class CATAnalysisParserAcrossXML extends CATAnalysisParserBase {

	public CATTool getCATTool() {
		return CATTool.ACROSS;
	}

	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
		
		Scanner sc = new Scanner(is, "UTF-16");
		sc.useLocale(Locale.US);
		parseBlock(result, sc);
		
		return result;
	}

	private void parseBlock(CATAnalysis result, Scanner sc) throws CATAnalysisParserException {
		setData(result, sc, "(total)", MatchType.TOTAL);
		setData(result, sc, "Locked", MatchType.LOCKED);
		
		sc.findWithinHorizon("Hidden", 0);
		sc.findWithinHorizon("sentences=\"", 0);
		result.add(Unit.SEGMENT, MatchType.TOTAL, getValue(sc).negate());
		sc.findWithinHorizon("words=\"", 0);
		result.add(Unit.WORD, MatchType.TOTAL, getValue(sc).negate());
		sc.findWithinHorizon("chars=\"", 0);
		result.add(Unit.CHARACTER, MatchType.TOTAL, getValue(sc).negate());
		
		setData(result, sc, "Repetitions", MatchType.REPETITIONS);
		setData(result, sc, "Context match", MatchType.CONTEXT_MATCH);
		
		setData(result, sc, "100%", MatchType.PERCENT_100);
		modifyData(result, sc, "External", MatchType.PERCENT_100);
		
		setData(result, sc, "95% - 99%", MatchType.PERCENT_95_99);
		modifyData(result, sc, "External", MatchType.PERCENT_95_99);
		
		setData(result, sc, "75% - 94%", MatchType.PERCENT_75_94);
		modifyData(result, sc, "External", MatchType.PERCENT_75_94);
				
		setData(result, sc, "No match", MatchType.NO_MATCH);
		
		result.verifyDataIntegrity();
	}
	
	private void setData(CATAnalysis result, Scanner sc, String firstCol, MatchType matchType){
		sc.findWithinHorizon(firstCol, 0);
		sc.findWithinHorizon("sentences=\"", 0);
		result.set(Unit.SEGMENT, matchType, getValue(sc));
		sc.findWithinHorizon("words=\"", 0);
		result.set(Unit.WORD, matchType, getValue(sc));
		sc.findWithinHorizon("chars=\"", 0);
		result.set(Unit.CHARACTER, matchType, getValue(sc));
	}
	
	private void modifyData(CATAnalysis result, Scanner sc, String firstCol, MatchType matchType){
		sc.findWithinHorizon(firstCol, 0);
		sc.findWithinHorizon("sentences=\"", 0);
		result.add(Unit.SEGMENT, matchType, getValue(sc));
		sc.findWithinHorizon("words=\"", 0);
		result.add(Unit.WORD, matchType, getValue(sc));
		sc.findWithinHorizon("chars=\"", 0);
		result.add(Unit.CHARACTER, matchType, getValue(sc));
	}

	private BigDecimal getValue(Scanner sc) {
		sc.findWithinHorizon("<TD class=analysis-cell>", 0);
		String s = sc.next();
		s = s.substring(0, s.indexOf("\""));
		return new BigDecimal(s);
	}
}
