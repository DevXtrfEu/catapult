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
import static eu.xtrf.cat.Unit.TOKEN;
import static eu.xtrf.cat.Unit.WORD;

import java.math.BigDecimal;

import eu.xtrf.cat.parser.CATAnalysisParserException;
import eu.xtrf.cat.parser.CATAnalysisParserNotSupportedMultilingualAnalysisException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;

@Test
public class CATAnalysisParserMemoqTradosCSVTest extends CATAnalysisParserTest<CATAnalysisParserMemoqTradosCSV> {

	public CATAnalysisParserMemoqTradosCSVTest() {
		super(CATAnalysisParserMemoqTradosCSV.class);
	}

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result1 = new CATAnalysis(CATTool.MEMO_Q, null);
		result1
			.focusOn(TOTAL)		.set(WORD, 541)		.set(SEGMENT, 60)
			.focusOn(NO_MATCH)	.set(WORD, 541)		.set(SEGMENT, 60);
		result1.setCharsPerWord(new BigDecimal("6.2"));

		CATAnalysis result2 = new CATAnalysis(CATTool.MEMO_Q, null);
		result2
			.focusOn(TOTAL)			.set(WORD, 1694)	.set(SEGMENT, 199)	.set(TOKEN, 2)
			.focusOn(REPETITIONS)	.set(WORD, 8)		.set(SEGMENT, 8)	.set(TOKEN, 0)
			.focusOn(PERCENT_95_99)	.set(WORD, 0)		.set(SEGMENT, 21)	.set(TOKEN, 0)
			.focusOn(PERCENT_75_84)	.set(WORD, 16)		.set(SEGMENT, 6)	.set(TOKEN, 0)
			.focusOn(PERCENT_50_74)	.set(WORD, 72)		.set(SEGMENT, 17)	.set(TOKEN, 2)
			.focusOn(NO_MATCH)		.set(WORD, 1598)	.set(SEGMENT, 147)	.set(TOKEN, 0);
		result2.setCharsPerWord(new BigDecimal("5.62"));

		CATAnalysis result3 = new CATAnalysis(CATTool.MEMO_Q, null);
		result3
			.focusOn(TOTAL)		.set(WORD, 541)		.set(SEGMENT, 60)	.set(CHARACTER, 3352)
			.focusOn(NO_MATCH)	.set(WORD, 541)		.set(SEGMENT, 60)	.set(CHARACTER, 3352);
		result3.setCharsPerWord(new BigDecimal("6.2"));

		CATAnalysis result4 = new CATAnalysis(CATTool.MEMO_Q, null);
		result4
			.focusOn(TOTAL)			.set(WORD, 38)	.set(SEGMENT, 70)	.set(TOKEN, 79)
			.focusOn(REPETITIONS)	.set(WORD, 7)	.set(SEGMENT, 10)	.set(TOKEN, 16)
			.focusOn(PERCENT_100)	.set(WORD, 4)	.set(SEGMENT, 3)	.set(TOKEN, 0)
			.focusOn(PERCENT_95_99)	.set(WORD, 3)	.set(SEGMENT, 49)	.set(TOKEN, 49)
			.focusOn(PERCENT_50_74)	.set(WORD, 17)	.set(SEGMENT, 5)	.set(TOKEN, 8)
			.focusOn(NO_MATCH)		.set(WORD, 7)	.set(SEGMENT, 3)	.set(TOKEN, 6);
		result4.setCharsPerWord(new BigDecimal("5.82"));

		CATAnalysis result5 = new CATAnalysis(CATTool.MEMO_Q, null);
		result5
			.focusOn(TOTAL)			.set(WORD, 38)	.set(SEGMENT, 70)	.set(CHARACTER, 221)
			.focusOn(REPETITIONS)	.set(WORD, 7)	.set(SEGMENT, 10)	.set(CHARACTER, 25)
			.focusOn(PERCENT_100)	.set(WORD, 4)	.set(SEGMENT, 3)	.set(CHARACTER, 25)
			.focusOn(PERCENT_95_99)	.set(WORD, 3)	.set(SEGMENT, 49)	.set(CHARACTER, 29)
			.focusOn(PERCENT_50_74)	.set(WORD, 17)	.set(SEGMENT, 5)	.set(CHARACTER, 105)
			.focusOn(NO_MATCH)		.set(WORD, 7)	.set(SEGMENT, 3)	.set(CHARACTER, 37);
		result5.setCharsPerWord(new BigDecimal("5.82"));

		CATAnalysis result6 = new CATAnalysis(CATTool.MEMO_Q, null);
		result6
			.focusOn(TOTAL)			.set(WORD, 557)		.set(SEGMENT, 43)	.set(CHARACTER, 4277)
			.focusOn(PERCENT_101)	.set(WORD, 557)		.set(SEGMENT, 43)	.set(CHARACTER, 4277);
		result6.setCharsPerWord(new BigDecimal("7.68"));

		CATAnalysis result7 = new CATAnalysis(CATTool.MEMO_Q, null);
		result7
			.focusOn(TOTAL)			.set(WORD, 361)		.set(SEGMENT, 108)
			.focusOn(PERCENT_101)	.set(WORD, 0)		.set(SEGMENT, 0)
			.focusOn(PERCENT_95_99)	.set(WORD, 52)		.set(SEGMENT, 16)
			.focusOn(PERCENT_85_94)	.set(WORD, 21)		.set(SEGMENT, 7)
			.focusOn(PERCENT_75_84)	.set(WORD, 19)		.set(SEGMENT, 6)
			.focusOn(PERCENT_50_74)	.set(WORD, 222)		.set(SEGMENT, 66)
			.focusOn(NO_MATCH)		.set(WORD, 47)		.set(SEGMENT, 13);
		result7.setCharsPerWord(new BigDecimal("16.99"));

		CATAnalysis result8 = new CATAnalysis(CATTool.MEMO_Q, null);
		result8
				.focusOn(TOTAL)			.set(WORD, 41718)		.set(SEGMENT, 4255)		.set(TOKEN, 2465)
				.focusOn(REPETITIONS)	.set(WORD, 78)          .set(SEGMENT, 12)		.set(TOKEN, 0)
				.focusOn(X_TRANSLATED)	.set(WORD, 3)			.set(SEGMENT, 3)		.set(TOKEN, 0)
				.focusOn(PERCENT_100)	.set(WORD, 17)			.set(SEGMENT, 15)		.set(TOKEN, 0)
				.focusOn(PERCENT_95_99)	.set(WORD, 84)			.set(SEGMENT, 52)		.set(TOKEN, 52)
				.focusOn(PERCENT_85_94)	.set(WORD, 14796)		.set(SEGMENT, 1479)		.set(TOKEN, 166)
				.focusOn(PERCENT_75_84)	.set(WORD, 2410)		.set(SEGMENT, 162)		.set(TOKEN, 169)
				.focusOn(PERCENT_50_74)	.set(WORD, 6072)		.set(SEGMENT, 1114)		.set(TOKEN, 1184)
				.focusOn(NO_MATCH)		.set(WORD, 18258)		.set(SEGMENT, 1418)		.set(TOKEN, 894);
		result8.setCharsPerWord(new BigDecimal("6.2"));

		CATAnalysis result9 = new CATAnalysis(CATTool.MEMO_Q, null);
		result9
				.focusOn(X_TRANSLATED)	.set(WORD, 0)			.set(SEGMENT, 0)		.set(TOKEN, 0)
				.focusOn(REPETITIONS)	.set(WORD, 0)           .set(SEGMENT, 0)		.set(TOKEN, 0)
				.focusOn(PERCENT_100)	.set(WORD, 3)			.set(SEGMENT, 3)		.set(TOKEN, 0)
				.focusOn(PERCENT_95_99)	.set(WORD, 1)			.set(SEGMENT, 1)		.set(TOKEN, 0)
				.focusOn(PERCENT_85_94)	.set(WORD, 0)		    .set(SEGMENT, 0)		.set(TOKEN, 0)
				.focusOn(PERCENT_75_84)	.set(WORD, 0)		    .set(SEGMENT, 0)		.set(TOKEN, 0)
				.focusOn(PERCENT_50_74)	.set(WORD, 0)	    	.set(SEGMENT, 0)		.set(TOKEN, 0)
				.focusOn(NO_MATCH)		.set(WORD, 20)		    .set(SEGMENT, 9)		.set(TOKEN, 0)
				.focusOn(TOTAL)			.set(WORD, 24)		    .set(SEGMENT, 13)    	.set(TOKEN, 0);
		result9.setCharsPerWord(new BigDecimal("3.33"));

		CATAnalysis result10 = new CATAnalysis(CATTool.MEMO_Q, null);
		result10
				.focusOn(X_TRANSLATED)	.set(WORD, 0)			.set(SEGMENT, 0)		.set(CHARACTER, 0)
				.focusOn(REPETITIONS)	.set(WORD, 0)           .set(SEGMENT, 0)		.set(CHARACTER, 0)
				.focusOn(PERCENT_100)	.set(WORD, 3)			.set(SEGMENT, 3)		.set(CHARACTER, 12)
				.focusOn(PERCENT_95_99)	.set(WORD, 1)			.set(SEGMENT, 1)		.set(CHARACTER, 4)
				.focusOn(PERCENT_85_94)	.set(WORD, 0)		    .set(SEGMENT, 0)		.set(CHARACTER, 0)
				.focusOn(PERCENT_75_84)	.set(WORD, 0)		    .set(SEGMENT, 0)		.set(CHARACTER, 0)
				.focusOn(PERCENT_50_74)	.set(WORD, 0)	    	.set(SEGMENT, 0)		.set(CHARACTER, 0)
				.focusOn(NO_MATCH)		.set(WORD, 20)		    .set(SEGMENT, 9)		.set(CHARACTER, 64)
				.focusOn(TOTAL)			.set(WORD, 24)		    .set(SEGMENT, 13)    	.set(CHARACTER, 80);
		result10.setCharsPerWord(new BigDecimal("3.33"));


		return new Object[][] {
				new Object[] {"/memoq_trados/trados.csv", result1},
				new Object[] {"/memoq_trados/memoq_api_trados.csv", result2},
				new Object[] {"/memoq_trados/trados_csv_memoq.csv", result3},
				new Object[] {"/memoq_trados/memoq_trados_compatible.csv", result4},
				new Object[] {"/memoq_trados/memoq_trados_compatible_2.csv", result5},
				new Object[] {"/memoq_trados/memoq_trados_compatible_3.csv", result6},
				new Object[] {"/memoq_trados/tradosUtf8.csv", result7},
				new Object[] {"/memoq_trados/memoq_trados_context_tm.csv", result8},
				new Object[] {"/memsource/memsource_analysis_monolingual.csv", result9},
				new Object[] {"/memsource/memsource_analysis_monolingual_with_chars.csv", result10}
		};
	}

	@Test(expectedExceptions = CATAnalysisParserNotSupportedMultilingualAnalysisException.class)
	public void multilingualAnalysisTest1() throws CATAnalysisParserException {
		getResult("/memsource/memsource_analysis_multilingual.csv");
	}

	@Test(expectedExceptions = CATAnalysisParserNotSupportedMultilingualAnalysisException.class)
	public void multilingualAnalysisTest2() throws CATAnalysisParserException {
		getResult("/memsource/memsource_analysis_multilingual_with_chars.csv");
	}


}
