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
 * CATAnalysisParserAcrossHTM.java, created: 12-11-2012.
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
 * Analysis of Across HTM metrics.
 * 
 * @author Daniel Bochniak
 */
public class CATAnalysisParserAcrossHTM extends CATAnalysisParserBase {

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
		parseBlockLine(result, sc, "(Brutto)", MatchType.TOTAL);
		parseBlockLine(result, sc, "Gesperrt", MatchType.LOCKED);
		
		sc.findWithinHorizon("Versteckt", 0);
		result.add(Unit.SEGMENT, MatchType.TOTAL, getValue(sc).negate());
		result.add(Unit.WORD, MatchType.TOTAL, getValue(sc).negate());
		result.add(Unit.CHARACTER, MatchType.TOTAL, getValue(sc).negate());
		
		parseBlockLine(result, sc, "in den Dokumenten", MatchType.REPETITIONS);
		parseBlockLine(result, sc, "Kontext-Match", MatchType.CONTEXT_MATCH);
		parseBlockLine(result, sc, "100%", MatchType.PERCENT_100);
		parseBlockLine(result, sc, "90% - 99%", MatchType.PERCENT_90_99);
		parseBlockLine(result, sc, "80% - 89%", MatchType.PERCENT_80_89);
		parseBlockLine(result, sc, "70% - 79%", MatchType.PERCENT_70_79);
		parseBlockLine(result, sc, "60% - 69%", MatchType.PERCENT_60_69);
		parseBlockLine(result, sc, "50% - 59%", MatchType.PERCENT_50_59);
		parseBlockLine(result, sc, "Kein Match", MatchType.NO_MATCH);
		
		result.verifyDataIntegrity();
	}

	private BigDecimal getValue(Scanner sc) {
		sc.findWithinHorizon("<TD class=analysis-cell>", 0);
		String s = sc.next();
		s = s.substring(0, s.indexOf("<"));
		return new BigDecimal(s);
	}

	private void parseBlockLine(CATAnalysis result, Scanner sc, String firstCol, MatchType matchType) {
		if (sc.findWithinHorizon(firstCol, 0) != null) {
			result.add(Unit.SEGMENT, matchType, getValue(sc));
			result.add(Unit.WORD, matchType, getValue(sc));
			result.add(Unit.CHARACTER, matchType, getValue(sc));
		}
	}
}
