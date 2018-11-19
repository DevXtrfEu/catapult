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
 * CATAnalysisParserMemoQTest.java, created: 09-11-2012.
 */
package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.MatchType.FRAGMENTS;
import static eu.xtrf.cat.MatchType.NO_MATCH;
import static eu.xtrf.cat.MatchType.PERCENT_100;
import static eu.xtrf.cat.MatchType.PERCENT_101;
import static eu.xtrf.cat.MatchType.PERCENT_50_74;
import static eu.xtrf.cat.MatchType.PERCENT_75_84;
import static eu.xtrf.cat.MatchType.PERCENT_85_94;
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
import eu.xtrf.cat.parser.impl.CATAnalysisParserMemoQ;


/**
 * Test for analysis of memoQ metrics in CSV format.
 * 
 * @author Daniel Bochniak
 */
@Test
public class CATAnalysisParserMemoQTest extends CATAnalysisParserTest<CATAnalysisParserMemoQ> {		
	public CATAnalysisParserMemoQTest() {
		super(CATAnalysisParserMemoQ.class);
	}
	
	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis memoqResult1 = new CATAnalysis(CATTool.MEMO_Q, null);
		memoqResult1.set(WORD, TOTAL, 		  1474).set(SEGMENT, TOTAL, 	   170).set(CHARACTER, TOTAL, 	      8898);
		memoqResult1.set(WORD, REPETITIONS,     56).set(SEGMENT, REPETITIONS, 	11).set(CHARACTER, REPETITIONS,    311);
		memoqResult1.set(WORD, PERCENT_101,    131).set(SEGMENT, PERCENT_101, 	17).set(CHARACTER, PERCENT_101,    886);
		memoqResult1.set(WORD, PERCENT_100,      0).set(SEGMENT, PERCENT_100, 	 0).set(CHARACTER, PERCENT_100, 	 0);
		memoqResult1.set(WORD, PERCENT_95_99, 	 8).set(SEGMENT, PERCENT_95_99,  1).set(CHARACTER, PERCENT_95_99,   38);
		memoqResult1.set(WORD, PERCENT_85_94, 	29).set(SEGMENT, PERCENT_85_94,  2).set(CHARACTER, PERCENT_85_94,  148);
		memoqResult1.set(WORD, PERCENT_75_84, 	44).set(SEGMENT, PERCENT_75_84,  5).set(CHARACTER, PERCENT_75_84,  236);
		memoqResult1.set(WORD, PERCENT_50_74, 	91).set(SEGMENT, PERCENT_50_74, 21).set(CHARACTER, PERCENT_50_74,  544);
		memoqResult1.set(WORD, FRAGMENTS, 		 0).set(SEGMENT, FRAGMENTS, 	 0).set(CHARACTER, FRAGMENTS, 	     0);
		memoqResult1.set(WORD, NO_MATCH, 	  1115).set(SEGMENT, NO_MATCH, 	   113).set(CHARACTER, NO_MATCH, 	  6735);

		CATAnalysis memoqResult2 = new CATAnalysis(CATTool.MEMO_Q, null);
		memoqResult2.set(WORD, TOTAL, 		  461).set(SEGMENT, TOTAL, 		   38).set(CHARACTER, TOTAL, 		  2338);
		memoqResult2.set(WORD, REPETITIONS, 	1).set(SEGMENT, REPETITIONS, 	1).set(CHARACTER, REPETITIONS, 	    10);
		memoqResult2.set(WORD, PERCENT_101, 	0).set(SEGMENT, PERCENT_101, 	0).set(CHARACTER, PERCENT_101, 	     0);
		memoqResult2.set(WORD, PERCENT_100, 	0).set(SEGMENT, PERCENT_100, 	0).set(CHARACTER, PERCENT_100, 	     0);
		memoqResult2.set(WORD, PERCENT_95_99, 	0).set(SEGMENT, PERCENT_95_99, 	0).set(CHARACTER, PERCENT_95_99, 	 0);
		memoqResult2.set(WORD, PERCENT_85_94, 	0).set(SEGMENT, PERCENT_85_94, 	0).set(CHARACTER, PERCENT_85_94, 	 0);
		memoqResult2.set(WORD, PERCENT_75_84, 	0).set(SEGMENT, PERCENT_75_84, 	0).set(CHARACTER, PERCENT_75_84, 	 0);
		memoqResult2.set(WORD, PERCENT_50_74,  11).set(SEGMENT, PERCENT_50_74,  5).set(CHARACTER, PERCENT_50_74,    73);
		memoqResult2.set(WORD, FRAGMENTS, 		0).set(SEGMENT, FRAGMENTS, 		0).set(CHARACTER, FRAGMENTS, 		 0);
		memoqResult2.set(WORD, NO_MATCH, 	  449).set(SEGMENT, NO_MATCH, 	   32).set(CHARACTER, NO_MATCH, 	  2255);

		CATAnalysis memoqResult3 = new CATAnalysis(CATTool.MEMO_Q, null);
		memoqResult3
			.focusOn(TOTAL)			.set(WORD, 5432)	.set(SEGMENT, 547)	.set(CHARACTER, 37774)
			.focusOn(REPETITIONS)	.set(WORD, 0)		.set(SEGMENT, 0)	.set(CHARACTER, 0)
			.focusOn(PERCENT_101)	.set(WORD, 5432)	.set(SEGMENT, 547)	.set(CHARACTER, 37774)
			.focusOn(PERCENT_100)	.set(WORD, 0)		.set(SEGMENT, 0)	.set(CHARACTER, 0)
			.focusOn(PERCENT_95_99)	.set(WORD, 0)		.set(SEGMENT, 0)	.set(CHARACTER, 0)
			.focusOn(PERCENT_85_94)	.set(WORD, 0)		.set(SEGMENT, 0)	.set(CHARACTER, 0)
			.focusOn(PERCENT_75_84)	.set(WORD, 0)		.set(SEGMENT, 0)	.set(CHARACTER, 0)
			.focusOn(PERCENT_50_74)	.set(WORD, 0)		.set(SEGMENT, 0)	.set(CHARACTER, 0)
			.focusOn(FRAGMENTS)		.set(WORD, 0)		.set(SEGMENT, 0)	.set(CHARACTER, 0)
			.focusOn(NO_MATCH)		.set(WORD, 0)		.set(SEGMENT, 0)	.set(CHARACTER, 0);
		
		CATAnalysis memoqResult4 = new CATAnalysis(CATTool.MEMO_Q, null);
		memoqResult4
			.focusOn(TOTAL)			.set(WORD, 27955)	.set(SEGMENT, 3399)	.set(CHARACTER, 194206)
			.focusOn(REPETITIONS)	.set(WORD, 5575)	.set(SEGMENT, 1074)	.set(CHARACTER, 45626)
			.focusOn(PERCENT_101)	.set(WORD, 6)		.set(SEGMENT, 6)	.set(CHARACTER, 6)
			.focusOn(PERCENT_100)	.set(WORD, 107)		.set(SEGMENT, 31)	.set(CHARACTER, 701)
			.focusOn(PERCENT_95_99)	.set(WORD, 143)		.set(SEGMENT, 61)	.set(CHARACTER, 1033)
			.focusOn(PERCENT_85_94)	.set(WORD, 76)		.set(SEGMENT, 8)	.set(CHARACTER, 508)
			.focusOn(PERCENT_75_84)	.set(WORD, 375)		.set(SEGMENT, 117)	.set(CHARACTER, 2564)
			.focusOn(PERCENT_50_74)	.set(WORD, 3786)	.set(SEGMENT, 568)	.set(CHARACTER, 25264)
			.focusOn(FRAGMENTS)		.set(WORD, 0)		.set(SEGMENT, 0)	.set(CHARACTER, 0)
			.focusOn(NO_MATCH)		.set(WORD, 17887)	.set(SEGMENT, 1534)	.set(CHARACTER, 118504);
		
		CATAnalysis memoqResult5 = new CATAnalysis(CATTool.MEMO_Q, null);
		memoqResult5
			.focusOn(TOTAL)			.set(WORD, 7)	.set(SEGMENT, 2)	.set(CHARACTER, 21)
			.focusOn(REPETITIONS)	.set(WORD, 0)	.set(SEGMENT, 0)	.set(CHARACTER, 0)
			.focusOn(PERCENT_101)	.set(WORD, 0)	.set(SEGMENT, 0)	.set(CHARACTER, 0)
			.focusOn(PERCENT_100)	.set(WORD, 0)	.set(SEGMENT, 0)	.set(CHARACTER, 0)
			.focusOn(PERCENT_95_99)	.set(WORD, 0)	.set(SEGMENT, 0)	.set(CHARACTER, 0)
			.focusOn(PERCENT_85_94)	.set(WORD, 0)	.set(SEGMENT, 0)	.set(CHARACTER, 0)
			.focusOn(PERCENT_75_84)	.set(WORD, 0)	.set(SEGMENT, 0)	.set(CHARACTER, 0)
			.focusOn(PERCENT_50_74)	.set(WORD, 0)	.set(SEGMENT, 0)	.set(CHARACTER, 0)
			.focusOn(FRAGMENTS)		.set(WORD, 0)	.set(SEGMENT, 0)	.set(CHARACTER, 0)
			.focusOn(NO_MATCH)		.set(WORD, 7)	.set(SEGMENT, 2)	.set(CHARACTER, 21);

		CATAnalysis memoqResult6 = new CATAnalysis(CATTool.MEMO_Q, null);
		memoqResult6
			.focusOn(TOTAL).set(WORD, 442).set(SEGMENT, 92).set(CHARACTER, 2380)
			.focusOn(REPETITIONS).set(WORD, 25).set(SEGMENT, 7).set(CHARACTER, 145)
			.focusOn(PERCENT_101).set(WORD, 0).set(SEGMENT, 0).set(CHARACTER, 0)
			.focusOn(PERCENT_100).set(WORD, 0).set(SEGMENT, 0).set(CHARACTER, 0)
			.focusOn(PERCENT_95_99).set(WORD, 0).set(SEGMENT, 0).set(CHARACTER, 0)
			.focusOn(PERCENT_85_94).set(WORD, 0).set(SEGMENT, 0).set(CHARACTER, 0)
			.focusOn(PERCENT_75_84).set(WORD, 0).set(SEGMENT, 0).set(CHARACTER, 0)
			.focusOn(PERCENT_50_74).set(WORD, 0).set(SEGMENT, 0).set(CHARACTER, 0)
			.focusOn(FRAGMENTS).set(WORD, 0).set(SEGMENT, 0).set(CHARACTER, 0)
			.focusOn(NO_MATCH).set(WORD, 417).set(SEGMENT, 85).set(CHARACTER, 2235);
		
		return new Object[][] {
				new Object[] {"/memoq/memoq.log", memoqResult1},
				new Object[] {"/memoq/memoqge.csv", memoqResult2},
				new Object[] {"/memoq/MemoQ CVS displayed results.csv", memoqResult3},
				new Object[] {"/memoq/memoq_analyse1.csv", memoqResult4},
				new Object[] {"/memoq/memoq-2013-pl.csv", memoqResult5},
				new Object[] {"/memoq/memoq-spanish.csv", memoqResult2},
				new Object[] {"/memoq/memoq-tabs.csv", memoqResult6}
		};
	}
}
