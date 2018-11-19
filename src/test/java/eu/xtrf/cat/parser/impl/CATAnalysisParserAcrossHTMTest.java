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
 * CATAnalysisParserAcrossHTMTest.java, created: 12-11-2012.
 */
package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.MatchType.CONTEXT_MATCH;
import static eu.xtrf.cat.MatchType.LOCKED;
import static eu.xtrf.cat.MatchType.NO_MATCH;
import static eu.xtrf.cat.MatchType.PERCENT_100;
import static eu.xtrf.cat.MatchType.PERCENT_50_59;
import static eu.xtrf.cat.MatchType.PERCENT_60_69;
import static eu.xtrf.cat.MatchType.PERCENT_70_79;
import static eu.xtrf.cat.MatchType.PERCENT_80_89;
import static eu.xtrf.cat.MatchType.PERCENT_90_99;
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
 * Tests CATAnalysisParserAcrossHTM
 * 
 * @author Daniel Bochniak
 */
@Test
public class CATAnalysisParserAcrossHTMTest extends CATAnalysisParserTest<CATAnalysisParserAcrossHTM> {		
	
	public CATAnalysisParserAcrossHTMTest() {
		super(CATAnalysisParserAcrossHTM.class);
	}	
	
	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result1 = new CATAnalysis(CATTool.ACROSS, null);
		result1
			.focusOn(TOTAL)			.set(SEGMENT, 7034)	.set(WORD, 50021)	.set(CHARACTER, 316612)
			.focusOn(REPETITIONS)	.set(SEGMENT, 3310)	.set(WORD, 20428)	.set(CHARACTER, 123376)
			.focusOn(CONTEXT_MATCH)	.set(SEGMENT, 1084)	.set(WORD, 9051)	.set(CHARACTER, 60017)
			.focusOn(PERCENT_100)	.set(SEGMENT, 1677)	.set(WORD, 8120)	.set(CHARACTER, 53318)
			.focusOn(PERCENT_90_99)	.set(SEGMENT, 155)	.set(WORD, 816)		.set(CHARACTER, 5001)
			.focusOn(PERCENT_80_89)	.set(SEGMENT, 58)	.set(WORD, 699)		.set(CHARACTER, 4698)
			.focusOn(PERCENT_70_79)	.set(SEGMENT, 41)	.set(WORD, 442)		.set(CHARACTER, 3084)
			.focusOn(PERCENT_60_69)	.set(SEGMENT, 67)	.set(WORD, 755)		.set(CHARACTER, 5022)
			.focusOn(PERCENT_50_59)	.set(SEGMENT, 171)	.set(WORD, 1897)	.set(CHARACTER, 12263)
			.focusOn(NO_MATCH)		.set(SEGMENT, 471)	.set(WORD, 7813)	.set(CHARACTER, 49833);
			
		CATAnalysis result2 = new CATAnalysis(CATTool.ACROSS, null);
		result2
			.focusOn(TOTAL)			.set(SEGMENT, 14088 - 10570).set(WORD, 166132 - 124603)	.set(CHARACTER, 1059400 - 794566)
			.focusOn(REPETITIONS)	.set(SEGMENT, 1577)			.set(WORD, 16580)			.set(CHARACTER, 105412)
			.focusOn(CONTEXT_MATCH)	.set(SEGMENT, 819)			.set(WORD, 6613)			.set(CHARACTER, 42866)
			.focusOn(PERCENT_100)	.set(SEGMENT, 286)			.set(WORD, 2674)			.set(CHARACTER, 17877)
			.focusOn(PERCENT_90_99)	.set(SEGMENT, 29)			.set(WORD, 319)				.set(CHARACTER, 2102)
			.focusOn(PERCENT_80_89)	.set(SEGMENT, 40)			.set(WORD, 598)				.set(CHARACTER, 4115)
			.focusOn(PERCENT_70_79)	.set(SEGMENT, 43)			.set(WORD, 558)				.set(CHARACTER, 3894)
			.focusOn(PERCENT_60_69)	.set(SEGMENT, 77)			.set(WORD, 1052)			.set(CHARACTER, 7515)
			.focusOn(PERCENT_50_59)	.set(SEGMENT, 191)			.set(WORD, 2796)			.set(CHARACTER, 17795)
			.focusOn(NO_MATCH)		.set(SEGMENT, 456)			.set(WORD, 10339)			.set(CHARACTER, 63258);
		
		CATAnalysis result3 = new CATAnalysis(CATTool.ACROSS, null);
		result3
			.focusOn(TOTAL)			.set(SEGMENT, 475)	.set(WORD, 2492)	.set(CHARACTER, 19577)
			.focusOn(LOCKED)		.set(SEGMENT, 51)	.set(WORD, 64)		.set(CHARACTER, 375)
			.focusOn(REPETITIONS)	.set(SEGMENT, 85)	.set(WORD, 273)		.set(CHARACTER, 2308)
			.focusOn(CONTEXT_MATCH)	.set(SEGMENT, 41)	.set(WORD, 498)		.set(CHARACTER, 3604)
			.focusOn(PERCENT_100)	.set(SEGMENT, 58)	.set(WORD, 292)		.set(CHARACTER, 2294)
			.focusOn(NO_MATCH)		.set(SEGMENT, 240)	.set(WORD, 1365)	.set(CHARACTER, 10996);
				
		return new Object[][] {
				new Object[] {"/across/across1.htm", result1},
				new Object[] {"/across/across2.htm", result2},
				new Object[] {"/across/across3.htm", result3}
		};
	}
}
