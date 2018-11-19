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
 * CATAnalysisParserAcrossXMLTest.java, created: 12-11-2012.
 */
package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.MatchType.NO_MATCH;
import static eu.xtrf.cat.MatchType.PERCENT_100;
import static eu.xtrf.cat.MatchType.PERCENT_75_94;
import static eu.xtrf.cat.MatchType.PERCENT_95_99;
import static eu.xtrf.cat.MatchType.REPETITIONS;
import static eu.xtrf.cat.MatchType.TOTAL;
import static eu.xtrf.cat.Unit.CHARACTER;
import static eu.xtrf.cat.Unit.SEGMENT;
import static eu.xtrf.cat.Unit.WORD;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;

/**
 * Tests CATAnalysisParserAcrossXML
 * 
 * @author Daniel Bochniak
 */
@Test
public class CATAnalysisParserAcrossXMLTest extends CATAnalysisParserTest<CATAnalysisParserAcrossXML> {		
	
	public CATAnalysisParserAcrossXMLTest() {
		super(CATAnalysisParserAcrossXML.class);
	}

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result1 = new CATAnalysis(CATTool.ACROSS, null);
		result1
			.focusOn(TOTAL)			.set(SEGMENT, 53)	.set(WORD, 283)	.set(CHARACTER, 1886)
			.focusOn(REPETITIONS)	.set(SEGMENT, 8)	.set(WORD, 100)	.set(CHARACTER, 652)
			.focusOn(PERCENT_100)	.set(SEGMENT, 9)	.set(WORD, 24)	.set(CHARACTER, 152)
			.focusOn(PERCENT_95_99)	.set(SEGMENT, 1)	.set(WORD, 1)	.set(CHARACTER, 3)
			.focusOn(PERCENT_75_94)	.set(SEGMENT, 2)	.set(WORD, 3)	.set(CHARACTER, 23)
			.focusOn(NO_MATCH)		.set(SEGMENT, 33)	.set(WORD, 155)	.set(CHARACTER, 1056);
				
		return new Object[][] {
				new Object[] {"/across/across4.xml", result1}
		};
	}
}
