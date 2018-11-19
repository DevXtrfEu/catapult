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
 * CATAnalysisParserMemoQHTMLTest.java, created: 12-11-2012.
 */
package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.MatchType.NO_MATCH;
import static eu.xtrf.cat.MatchType.PERCENT_100;
import static eu.xtrf.cat.MatchType.PERCENT_101;
import static eu.xtrf.cat.MatchType.PERCENT_50_74;
import static eu.xtrf.cat.MatchType.PERCENT_75_84;
import static eu.xtrf.cat.MatchType.PERCENT_85_94;
import static eu.xtrf.cat.MatchType.PERCENT_95_99;
import static eu.xtrf.cat.MatchType.REPETITIONS;
import static eu.xtrf.cat.MatchType.TOTAL;
import static eu.xtrf.cat.MatchType.X_TRANSLATED;
import static eu.xtrf.cat.Unit.CHARACTER;
import static eu.xtrf.cat.Unit.SEGMENT;
import static eu.xtrf.cat.Unit.WORD;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.parser.impl.CATAnalysisParserMemoQHTML;

/**
 * Test for analysis of memoQ metrics in HTML format.
 * 
 * @author Daniel Bochniak
 */
@Test
public class CATAnalysisParserMemoQHTMLTest extends CATAnalysisParserTest<CATAnalysisParserMemoQHTML> {		
	public CATAnalysisParserMemoQHTMLTest() {
		super(CATAnalysisParserMemoQHTML.class);
	}
	
	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis memoqResult1 = new CATAnalysis(CATTool.MEMO_Q, null);
		memoqResult1.set(WORD, TOTAL, 		  1725).set(SEGMENT, TOTAL, 	   256).set(CHARACTER, TOTAL, 	      10040);
		memoqResult1.set(WORD, X_TRANSLATED,     0).set(SEGMENT, X_TRANSLATED, 	 0).set(CHARACTER, X_TRANSLATED,      0);
		memoqResult1.set(WORD, REPETITIONS,    406).set(SEGMENT, REPETITIONS, 	72).set(CHARACTER, REPETITIONS,    2597);
		memoqResult1.set(WORD, PERCENT_101,    181).set(SEGMENT, PERCENT_101, 	26).set(CHARACTER, PERCENT_101,    1059);
		memoqResult1.set(WORD, PERCENT_100,    322).set(SEGMENT, PERCENT_100, 	57).set(CHARACTER, PERCENT_100,    2092);
		memoqResult1.set(WORD, PERCENT_95_99,  100).set(SEGMENT, PERCENT_95_99, 29).set(CHARACTER, PERCENT_95_99,   441);
		memoqResult1.set(WORD, PERCENT_85_94, 	55).set(SEGMENT, PERCENT_85_94,  4).set(CHARACTER, PERCENT_85_94,   301);
		memoqResult1.set(WORD, PERCENT_75_84,  113).set(SEGMENT, PERCENT_75_84, 16).set(CHARACTER, PERCENT_75_84,   611);
		memoqResult1.set(WORD, PERCENT_50_74,  261).set(SEGMENT, PERCENT_50_74, 33).set(CHARACTER, PERCENT_50_74,  1547);
		memoqResult1.set(WORD, NO_MATCH, 	   287).set(SEGMENT, NO_MATCH, 	    19).set(CHARACTER, NO_MATCH, 	   1392);
		
		CATAnalysis memoqResult2 = new CATAnalysis(CATTool.MEMO_Q, null);
		memoqResult2
			.focusOn(TOTAL)			.set(WORD, 2337)	.set(SEGMENT, 727)	.set(CHARACTER, 13683)
			.focusOn(X_TRANSLATED)	.set(WORD, 0)		.set(SEGMENT, 0)	.set(CHARACTER, 0)
			.focusOn(REPETITIONS)	.set(WORD, 0)		.set(SEGMENT, 0)	.set(CHARACTER, 0)
			.focusOn(PERCENT_101)	.set(WORD, 0)		.set(SEGMENT, 0)	.set(CHARACTER, 0)
			.focusOn(PERCENT_100)	.set(WORD, 0)		.set(SEGMENT, 0)	.set(CHARACTER, 0)
			.focusOn(PERCENT_95_99)	.set(WORD, 1)		.set(SEGMENT, 1)	.set(CHARACTER, 7)
			.focusOn(PERCENT_85_94)	.set(WORD, 30)		.set(SEGMENT, 12)	.set(CHARACTER, 241)
			.focusOn(PERCENT_75_84)	.set(WORD, 607)		.set(SEGMENT, 184)	.set(CHARACTER, 3441)
			.focusOn(PERCENT_50_74)	.set(WORD, 1588)	.set(SEGMENT, 466)	.set(CHARACTER, 8914)
			.focusOn(NO_MATCH)		.set(WORD, 111)		.set(SEGMENT, 64)	.set(CHARACTER, 1080);
		
		CATAnalysis memoqResult3 = new CATAnalysis(CATTool.MEMO_Q, null);
		memoqResult3
			.focusOn(TOTAL)			.set(WORD, 1486)	.set(SEGMENT, 196)	.set(CHARACTER, 8243)
			.focusOn(X_TRANSLATED)	.set(WORD, 0)		.set(SEGMENT, 0)	.set(CHARACTER, 0)
			.focusOn(REPETITIONS)	.set(WORD, 44)		.set(SEGMENT, 15)	.set(CHARACTER, 208)
			.focusOn(PERCENT_101)	.set(WORD, 65)		.set(SEGMENT, 17)	.set(CHARACTER, 325)
			.focusOn(PERCENT_100)	.set(WORD, 148)		.set(SEGMENT, 57)	.set(CHARACTER, 890)
			.focusOn(PERCENT_95_99)	.set(WORD, 31)		.set(SEGMENT, 10)	.set(CHARACTER, 139)
			.focusOn(PERCENT_85_94)	.set(WORD, 13)		.set(SEGMENT, 2)	.set(CHARACTER, 83)
			.focusOn(PERCENT_75_84)	.set(WORD, 29)		.set(SEGMENT, 7)	.set(CHARACTER, 187)
			.focusOn(PERCENT_50_74)	.set(WORD, 556)		.set(SEGMENT, 54)	.set(CHARACTER, 3133)
			.focusOn(NO_MATCH)		.set(WORD, 600)		.set(SEGMENT, 34)	.set(CHARACTER, 3278);
		
		CATAnalysis memoqResult4 = new CATAnalysis(CATTool.MEMO_Q, null);
		memoqResult4
			.focusOn(TOTAL)			.set(WORD, 5432)	.set(SEGMENT, 547)		.set(CHARACTER, 37774)
			.focusOn(X_TRANSLATED)	.set(WORD, 0)		.set(SEGMENT, 0)		.set(CHARACTER, 0)
			.focusOn(REPETITIONS)	.set(WORD, 0)		.set(SEGMENT, 0)		.set(CHARACTER, 0)
			.focusOn(PERCENT_101)	.set(WORD, 5432)	.set(SEGMENT, 547)		.set(CHARACTER, 37774)
			.focusOn(PERCENT_100)	.set(WORD, 0)		.set(SEGMENT, 0)		.set(CHARACTER, 0)
			.focusOn(PERCENT_95_99)	.set(WORD, 0)		.set(SEGMENT, 0)		.set(CHARACTER, 0)
			.focusOn(PERCENT_85_94)	.set(WORD, 0)		.set(SEGMENT, 0)		.set(CHARACTER, 0)
			.focusOn(PERCENT_75_84)	.set(WORD, 0)		.set(SEGMENT, 0)		.set(CHARACTER, 0)
			.focusOn(PERCENT_50_74)	.set(WORD, 0)		.set(SEGMENT, 0)		.set(CHARACTER, 0)
			.focusOn(NO_MATCH)		.set(WORD, 0)		.set(SEGMENT, 0)		.set(CHARACTER, 0);
		
		CATAnalysis memoqResult5 = new CATAnalysis(CATTool.MEMO_Q, null);
		memoqResult5
			.focusOn(TOTAL)			.set(WORD, 53150)	.set(SEGMENT, 4233)		.set(CHARACTER, 342906)
			.focusOn(X_TRANSLATED)	.set(WORD, 0)		.set(SEGMENT, 0)		.set(CHARACTER, 0)
			.focusOn(REPETITIONS)	.set(WORD, 7550)	.set(SEGMENT, 970)		.set(CHARACTER, 49091)
			.focusOn(PERCENT_101)	.set(WORD, 52)		.set(SEGMENT, 8)		.set(CHARACTER, 285)
			.focusOn(PERCENT_100)	.set(WORD, 928)		.set(SEGMENT, 78)		.set(CHARACTER, 6132)
			.focusOn(PERCENT_95_99)	.set(WORD, 824)		.set(SEGMENT, 87)		.set(CHARACTER, 5072)
			.focusOn(PERCENT_85_94)	.set(WORD, 2257)	.set(SEGMENT, 115)		.set(CHARACTER, 14559)
			.focusOn(PERCENT_75_84)	.set(WORD, 5471)	.set(SEGMENT, 338)		.set(CHARACTER, 34796)
			.focusOn(PERCENT_50_74)	.set(WORD, 10231)	.set(SEGMENT, 869)		.set(CHARACTER, 66537)
			.focusOn(NO_MATCH)		.set(WORD, 25837)	.set(SEGMENT, 1768)		.set(CHARACTER, 166434);

		CATAnalysis memoqResult6 = new CATAnalysis(CATTool.MEMO_Q, null);
		memoqResult6
			.focusOn(TOTAL)			.set(WORD, 567)		.set(SEGMENT, 169)		.set(CHARACTER, 4498)
			.focusOn(X_TRANSLATED)	.set(WORD, 0)		.set(SEGMENT, 0)		.set(CHARACTER, 0)
			.focusOn(REPETITIONS)	.set(WORD, 0)		.set(SEGMENT, 0)		.set(CHARACTER, 0)
			.focusOn(PERCENT_101)	.set(WORD, 464)		.set(SEGMENT, 131)		.set(CHARACTER, 3630)
			.focusOn(PERCENT_100)	.set(WORD, 41)		.set(SEGMENT, 19)		.set(CHARACTER, 344)
			.focusOn(PERCENT_95_99)	.set(WORD, 22)		.set(SEGMENT, 11)		.set(CHARACTER, 184)
			.focusOn(PERCENT_85_94)	.set(WORD, 7)		.set(SEGMENT, 2)		.set(CHARACTER, 91)
			.focusOn(PERCENT_75_84)	.set(WORD, 10)		.set(SEGMENT, 1)		.set(CHARACTER, 69)
			.focusOn(PERCENT_50_74)	.set(WORD, 10)		.set(SEGMENT, 1)		.set(CHARACTER, 74)
			.focusOn(NO_MATCH)		.set(WORD, 13)		.set(SEGMENT, 4)		.set(CHARACTER, 106);

		CATAnalysis memoqResult7 = new CATAnalysis(CATTool.MEMO_Q, null);
		memoqResult7
				.focusOn(TOTAL)			.set(WORD, 1638)	.set(SEGMENT, 228)		.set(CHARACTER, 8188)
				.focusOn(X_TRANSLATED)	.set(WORD, 0)		.set(SEGMENT, 0)		.set(CHARACTER, 0)
				.focusOn(REPETITIONS)	.set(WORD, 0)		.set(SEGMENT, 0)		.set(CHARACTER, 0)
				.focusOn(PERCENT_101)	.set(WORD, 1638)	.set(SEGMENT, 228)		.set(CHARACTER, 8188)
				.focusOn(PERCENT_100)	.set(WORD, 0)		.set(SEGMENT, 0)		.set(CHARACTER, 0)
				.focusOn(PERCENT_95_99)	.set(WORD, 0)		.set(SEGMENT, 0)		.set(CHARACTER, 0)
				.focusOn(PERCENT_85_94)	.set(WORD, 0)		.set(SEGMENT, 0)		.set(CHARACTER, 0)
				.focusOn(PERCENT_75_84)	.set(WORD, 0)		.set(SEGMENT, 0)		.set(CHARACTER, 0)
				.focusOn(PERCENT_50_74)	.set(WORD, 0)		.set(SEGMENT, 0)		.set(CHARACTER, 0)
				.focusOn(NO_MATCH)		.set(WORD, 0)		.set(SEGMENT, 0)		.set(CHARACTER, 0);
		
        CATAnalysis memoqResult8 = new CATAnalysis(CATTool.MEMO_Q, null);
        memoqResult8
                .focusOn(TOTAL)         .set(WORD, 294)     .set(SEGMENT, 16)       .set(CHARACTER, 342)
                .focusOn(X_TRANSLATED)  .set(WORD, 0)       .set(SEGMENT, 0)        .set(CHARACTER, 0)
                .focusOn(REPETITIONS)   .set(WORD, 0)       .set(SEGMENT, 0)        .set(CHARACTER, 0)
                .focusOn(PERCENT_101)   .set(WORD, 0)       .set(SEGMENT, 0)        .set(CHARACTER, 0)
                .focusOn(PERCENT_100)   .set(WORD, 0)       .set(SEGMENT, 0)        .set(CHARACTER, 0)
                .focusOn(PERCENT_95_99) .set(WORD, 0)       .set(SEGMENT, 0)        .set(CHARACTER, 0)
                .focusOn(PERCENT_85_94) .set(WORD, 0)       .set(SEGMENT, 0)        .set(CHARACTER, 0)
                .focusOn(PERCENT_75_84) .set(WORD, 0)       .set(SEGMENT, 0)        .set(CHARACTER, 0)
                .focusOn(PERCENT_50_74) .set(WORD, 0)       .set(SEGMENT, 0)        .set(CHARACTER, 0)
                .focusOn(NO_MATCH)      .set(WORD, 294)     .set(SEGMENT, 16)       .set(CHARACTER, 342);
        
		return new Object[][] {
				new Object[] {"/memoq/memoq.html", memoqResult1},
				new Object[] {"/memoq/Analysis-2013_2084_La Redoute-2013-04-30.12.19.html", memoqResult2},
				new Object[] {"/memoq/Analysis-cNH_2013_2159-2013-05-07.09.42.html", memoqResult3},
				new Object[] {"/memoq/MemoQ HTML reflecting displayed results.html", memoqResult4},
				new Object[] {"/memoq/memoq-pl.html", memoqResult5},
				new Object[] {"/memoq/DE_Analyse-elumatec ECI Version 2.3.0_AP4111-AP4114_EN+FR+CZ+NL - Ben-2015-06-24.15.32.html", memoqResult6},
				new Object[] {"/memoq/EN_Analysis-elumatec ECI Version 2.3.0_AP4111-AP4114_EN+FR+CZ+NL - Ben-2015-06-24.17.04.html", memoqResult6},
				new Object[] {"/memoq/Analysis-2016_1116 - Our Source Payroll Job Aids (US and Brazil)-2016-06-06.20.00.html", memoqResult7},
                new Object[] {"/memoq/Analysis-2018-05-16.07.30-YITT-2018-0129-JA_EN-US-1.html", memoqResult8}

		};
	}
}
