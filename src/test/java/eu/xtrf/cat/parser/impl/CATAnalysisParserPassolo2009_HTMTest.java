package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.Unit.WORD;
import static eu.xtrf.cat.Unit.CHARACTER;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;

/**
 * CATAnalysisParserPassolo2009_HTM has been replaced by CATAnalysisParserPassolo2016_HTM, which should give the same
 * results for Passolo2009 format as previous parser.
 */
@Test
public class CATAnalysisParserPassolo2009_HTMTest extends CATAnalysisParserTest<CATAnalysisParserPassolo2016_HTM> {

	public CATAnalysisParserPassolo2009_HTMTest() {
		super(CATAnalysisParserPassolo2016_HTM.class);
	}	

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result1 = new CATAnalysis(CATTool.PASSOLO, null);
		
		result1.focusOn(MatchType.UNTRANSLATED)					.set(WORD, 5)			.set(CHARACTER, 31);
		result1.focusOn(MatchType.UNTRANSLATED_REPETITIONS)		.set(WORD, 0)			.set(CHARACTER, 0);
		result1.focusOn(MatchType.AUTO_TRANSLATED)				.set(WORD, 0)			.set(CHARACTER, 0);
		result1.focusOn(MatchType.FOR_REVIEW)					.set(WORD, 0)			.set(CHARACTER, 0);
		result1.focusOn(MatchType.TOTAL)						.set(WORD, 6977 - 6972)	.set(CHARACTER, 43113 - 43082);
		
		CATAnalysis result2 = new CATAnalysis(CATTool.PASSOLO, null);
		
		result2.focusOn(MatchType.UNTRANSLATED)					.set(WORD, 54)			.set(CHARACTER, 392);
		result2.focusOn(MatchType.UNTRANSLATED_REPETITIONS)		.set(WORD, 0)			.set(CHARACTER, 0);
		result2.focusOn(MatchType.AUTO_TRANSLATED)				.set(WORD, 0)			.set(CHARACTER, 0);
		result2.focusOn(MatchType.FOR_REVIEW)					.set(WORD, 8)			.set(CHARACTER, 47);
		result2.focusOn(MatchType.TOTAL)						.set(WORD, 1147 - 1085)	.set(CHARACTER, 7764 - 7325);
		
		CATAnalysis result3 = new CATAnalysis(CATTool.PASSOLO, null);
		
		result3.focusOn(MatchType.UNTRANSLATED)					.set(WORD, 12)			.set(CHARACTER, 85);
		result3.focusOn(MatchType.UNTRANSLATED_REPETITIONS)		.set(WORD, 7)			.set(CHARACTER, 57);
		result3.focusOn(MatchType.AUTO_TRANSLATED)				.set(WORD, 0)			.set(CHARACTER, 0);
		result3.focusOn(MatchType.FOR_REVIEW)					.set(WORD, 0)			.set(CHARACTER, 0);
		result3.focusOn(MatchType.TOTAL)						.set(WORD, 4284 - 4265)	.set(CHARACTER, 38860 - 38718);
		
		CATAnalysis result4 = new CATAnalysis(CATTool.PASSOLO, null);
		
		result4.focusOn(MatchType.UNTRANSLATED)					.set(WORD, 595)			.set(CHARACTER, 3753);
		result4.focusOn(MatchType.UNTRANSLATED_REPETITIONS)		.set(WORD, 8)			.set(CHARACTER, 55);
		result4.focusOn(MatchType.AUTO_TRANSLATED)				.set(WORD, 0)			.set(CHARACTER, 0);
		result4.focusOn(MatchType.FOR_REVIEW)					.set(WORD, 1)			.set(CHARACTER, 7);
		result4.focusOn(MatchType.TOTAL)						.set(WORD, 6491 - 5887)	.set(CHARACTER, 41640 - 37825);
		
		CATAnalysis result5 = new CATAnalysis(CATTool.PASSOLO, null);
		
		result5.focusOn(MatchType.UNTRANSLATED)					.set(WORD, 313)			.set(CHARACTER, 2024);
		result5.focusOn(MatchType.UNTRANSLATED_REPETITIONS)		.set(WORD, 21)			.set(CHARACTER, 165);
		result5.focusOn(MatchType.AUTO_TRANSLATED)				.set(WORD, 0)			.set(CHARACTER, 0);
		result5.focusOn(MatchType.FOR_REVIEW)					.set(WORD, 0)			.set(CHARACTER, 0);
		result5.focusOn(MatchType.TOTAL)						.set(WORD, 9560 - 9226)	.set(CHARACTER, 60696 - 58507);
		
		CATAnalysis result6 = new CATAnalysis(CATTool.PASSOLO, null);
		
		result6.focusOn(MatchType.UNTRANSLATED)					.set(WORD, 97)			.set(CHARACTER, 690);
		result6.focusOn(MatchType.UNTRANSLATED_REPETITIONS)		.set(WORD, 7)			.set(CHARACTER, 51);
		result6.focusOn(MatchType.AUTO_TRANSLATED)				.set(WORD, 3)			.set(CHARACTER, 21);
		result6.focusOn(MatchType.FOR_REVIEW)					.set(WORD, 304)			.set(CHARACTER, 1840);
		result6.focusOn(MatchType.TOTAL)						.set(WORD, 3069 - 2658)	.set(CHARACTER, 19693 - 17091);
		
		CATAnalysis result_joined_1 = new CATAnalysis(CATTool.PASSOLO, null);
		
		result_joined_1.focusOn(MatchType.UNTRANSLATED)					
			.set(WORD, 5 + 17 + 368 + 207)			
			.set(CHARACTER, 31 + 117 + 2286 + 1337);
		result_joined_1.focusOn(MatchType.UNTRANSLATED_REPETITIONS)		
			.set(WORD, 0 + 0 + 292 + 19)			
			.set(CHARACTER, 0 + 0 + 1970 + 135);
		result_joined_1.focusOn(MatchType.AUTO_TRANSLATED)				
			.set(WORD, 0 + 0 + 12 + 0)			
			.set(CHARACTER, 0 + 0 + 61 + 0);
		result_joined_1.focusOn(MatchType.FOR_REVIEW)					
			.set(WORD, 0 + 0 + 31 + 53)			
			.set(CHARACTER, 0 + 0 + 111 + 333);
		result_joined_1.focusOn(MatchType.TOTAL)						
			.set(WORD, (6977 - 6972) + (3479 - 3462) + (6758 - 6055) + (9250 - 8971))	
			.set(CHARACTER, (43113 - 43082) + (28660 - 28543) + (44420 - 39992) + (58631 - 56826));
		
		CATAnalysis result_joined_2 = new CATAnalysis(CATTool.PASSOLO, null);
		
		result_joined_2.focusOn(MatchType.UNTRANSLATED)					
			.set(WORD, 54 + 12 + 595 + 313 + 97)			
			.set(CHARACTER, 392 + 85 + 3753 + 2024 + 690);
		result_joined_2.focusOn(MatchType.UNTRANSLATED_REPETITIONS)		
			.set(WORD, 0 + 7 + 8 + 21 + 7)			
			.set(CHARACTER, 0 + 57 + 55 + 165 + 51);
		result_joined_2.focusOn(MatchType.AUTO_TRANSLATED)				
			.set(WORD, 0 + 0 + 0 + 0 + 3)			
			.set(CHARACTER, 0 + 0 + 0 + 0 + 21);
		result_joined_2.focusOn(MatchType.FOR_REVIEW)					
			.set(WORD, 8 + 0 + 1 + 0 + 304)			
			.set(CHARACTER, 47 + 0 + 7 + 0 + 1840);
		result_joined_2.focusOn(MatchType.TOTAL)						
			.set(WORD, (1147 - 1085) + (4284 - 4265) + (6491 - 5887) + (9560 - 9226) + (3069 - 2658))	
			.set(CHARACTER, (7764 - 7325) + (38860 - 38718) + (41640 - 37825) + (60696 - 58507) + (19693 - 17091));
		
		CATAnalysis result_koPassolo = new CATAnalysis(CATTool.PASSOLO, null);
		
		result_koPassolo.focusOn(MatchType.UNTRANSLATED)				.set(WORD, 16)				.set(CHARACTER, 102);
		result_koPassolo.focusOn(MatchType.UNTRANSLATED_REPETITIONS)	.set(WORD, 0)				.set(CHARACTER, 0);
		result_koPassolo.focusOn(MatchType.AUTO_TRANSLATED)				.set(WORD, 0)				.set(CHARACTER, 0);
		result_koPassolo.focusOn(MatchType.FOR_REVIEW)					.set(WORD, 0)				.set(CHARACTER, 0);
		result_koPassolo.focusOn(MatchType.TOTAL)						.set(WORD, 12603 - 12587)	.set(CHARACTER, 87689 - 87587);

		CATAnalysis passolo2011v1 = new CATAnalysis(CATTool.PASSOLO, null);

		passolo2011v1.focusOn(MatchType.UNTRANSLATED)				.set(WORD, 473)				.set(CHARACTER, 2665);
		passolo2011v1.focusOn(MatchType.UNTRANSLATED_REPETITIONS)	.set(WORD, 17)				.set(CHARACTER, 101);
		passolo2011v1.focusOn(MatchType.AUTO_TRANSLATED)			.set(WORD, 26)				.set(CHARACTER, 164);
		passolo2011v1.focusOn(MatchType.FOR_REVIEW)					.set(WORD, 0)				.set(CHARACTER, 0);
		passolo2011v1.focusOn(MatchType.TOTAL)						.set(WORD, 516)				.set(CHARACTER, 2930);

		CATAnalysis passolo2011v2 = new CATAnalysis(CATTool.PASSOLO, null);

		passolo2011v2.focusOn(MatchType.UNTRANSLATED)				.set(WORD, 597)				.set(CHARACTER, 3771);
		passolo2011v2.focusOn(MatchType.UNTRANSLATED_REPETITIONS)	.set(WORD, 311)				.set(CHARACTER, 2105);
		passolo2011v2.focusOn(MatchType.AUTO_TRANSLATED)			.set(WORD, 12)				.set(CHARACTER, 61);
		passolo2011v2.focusOn(MatchType.FOR_REVIEW)					.set(WORD, 84)				.set(CHARACTER, 444);
		passolo2011v2.focusOn(MatchType.TOTAL)						.set(WORD, 1004)			.set(CHARACTER, 6381);
		
		return new Object[][] {
			new Object[] {"/passolo/passolo2009_1.htm", result1},
			new Object[] {"/passolo/passolo2009_2.htm", result2},
			new Object[] {"/passolo/passolo2009_3.htm", result3},
			new Object[] {"/passolo/passolo2009_4.htm", result4},
			new Object[] {"/passolo/passolo2009_5.htm", result5},
			new Object[] {"/passolo/passolo2009_6.htm", result6},
			new Object[] {"/passolo/passolo2009_joined_1.htm", result_joined_1},
			new Object[] {"/passolo/passolo2009_joined_2.htm", result_joined_2},
			new Object[] {"/passolo/koPassolo_analysis.html", result_koPassolo},
			new Object[] {"/passolo/analyysi_Passolo_2011.htm", passolo2011v1 },
			new Object[] {"/passolo/XTRF_Supported_Passolo.htm", passolo2011v2 }
		};
	}
	
}
