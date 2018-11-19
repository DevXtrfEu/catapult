package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.MatchType.ICE_MATCH;
import static eu.xtrf.cat.MatchType.INTERNAL_75_84;
import static eu.xtrf.cat.MatchType.INTERNAL_85_94;
import static eu.xtrf.cat.MatchType.INTERNAL_95_99;
import static eu.xtrf.cat.MatchType.LEVERAGED_MATCH;
import static eu.xtrf.cat.MatchType.NON_TRANSLATABLE;
import static eu.xtrf.cat.MatchType.NO_MATCH;
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
import eu.xtrf.cat.parser.impl.CATAnalysisParserXTMXLS_Full;

@Test
public class CATAnalysisParserXTMXLS_FullTest extends CATAnalysisParserTest<CATAnalysisParserXTMXLS_Full> {
	
	public CATAnalysisParserXTMXLS_FullTest() {
		super(CATAnalysisParserXTMXLS_Full.class);
	}
	
	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result1 = new CATAnalysis(CATTool.XTM, null);

		result1
			.focusOn(TOTAL)				.set(SEGMENT, 786)		.set(WORD, 16349)		.set(CHARACTER, 93127)
			.focusOn(ICE_MATCH)			.set(SEGMENT, 0)		.set(WORD, 0)			.set(CHARACTER, 0)
			.focusOn(LEVERAGED_MATCH)	.set(SEGMENT, 0)		.set(WORD, 0)			.set(CHARACTER, 0)
			.focusOn(PERCENT_95_99)		.set(SEGMENT, 0)		.set(WORD, 0)			.set(CHARACTER, 0)
			.focusOn(PERCENT_85_94)		.set(SEGMENT, 0)		.set(WORD, 0)			.set(CHARACTER, 0)
			.focusOn(PERCENT_75_84)		.set(SEGMENT, 0)		.set(WORD, 0)			.set(CHARACTER, 0)
			.focusOn(REPETITIONS)		.set(SEGMENT, 7)		.set(WORD, 31)			.set(CHARACTER, 186)
			.focusOn(INTERNAL_95_99)	.set(SEGMENT, 5)		.set(WORD, 35)			.set(CHARACTER, 228)
			.focusOn(INTERNAL_85_94)	.set(SEGMENT, 5)		.set(WORD, 39)			.set(CHARACTER, 244)
			.focusOn(INTERNAL_75_84)	.set(SEGMENT, 3)		.set(WORD, 25)			.set(CHARACTER, 131)
			.focusOn(NO_MATCH)			.set(SEGMENT, 766)		.set(WORD, 16219)		.set(CHARACTER, 92338);
		
		CATAnalysis result2 = new CATAnalysis(CATTool.XTM, null);

		result2
			.focusOn(TOTAL)				.set(SEGMENT, 121)		.set(WORD, 769)			.set(CHARACTER, 5385)
			.focusOn(NON_TRANSLATABLE)	.set(SEGMENT, 53)		.set(WORD, 53)			.set(CHARACTER, 173)
			.focusOn(ICE_MATCH)			.set(SEGMENT, 17)		.set(WORD, 62)			.set(CHARACTER, 396)
			.focusOn(LEVERAGED_MATCH)	.set(SEGMENT, 34)		.set(WORD, 236)			.set(CHARACTER, 1779)
			.focusOn(PERCENT_95_99)		.set(SEGMENT, 2)		.set(WORD, 80)			.set(CHARACTER, 631)
			.focusOn(PERCENT_85_94)		.set(SEGMENT, 5)		.set(WORD, 195)			.set(CHARACTER, 1416)
			.focusOn(PERCENT_75_84)		.set(SEGMENT, 9)		.set(WORD, 139)			.set(CHARACTER, 968)
			.focusOn(REPETITIONS)		.set(SEGMENT, 0)		.set(WORD, 0)			.set(CHARACTER, 0)
			.focusOn(INTERNAL_95_99)	.set(SEGMENT, 0)		.set(WORD, 0)			.set(CHARACTER, 0)
			.focusOn(INTERNAL_85_94)	.set(SEGMENT, 0)		.set(WORD, 0)			.set(CHARACTER, 0)
			.focusOn(INTERNAL_75_84)	.set(SEGMENT, 0)		.set(WORD, 0)			.set(CHARACTER, 0)
			.focusOn(NO_MATCH)			.set(SEGMENT, 1)		.set(WORD, 4)			.set(CHARACTER, 22);

		CATAnalysis result3 = new CATAnalysis(CATTool.XTM, null);

		result3
			.focusOn(TOTAL)				.set(SEGMENT, 2373)		.set(WORD, 13208)		.set(CHARACTER, 85620)
			.focusOn(NON_TRANSLATABLE)	.set(SEGMENT, 15)		.set(WORD, 34)			.set(CHARACTER, 310)
			.focusOn(ICE_MATCH)			.set(SEGMENT, 0)		.set(WORD, 0)			.set(CHARACTER, 0)
			.focusOn(LEVERAGED_MATCH)	.set(SEGMENT, 332)		.set(WORD, 1048)		.set(CHARACTER, 6791)
			.focusOn(PERCENT_95_99)		.set(SEGMENT, 2)		.set(WORD, 10)			.set(CHARACTER, 61)
			.focusOn(PERCENT_85_94)		.set(SEGMENT, 9)		.set(WORD, 34)			.set(CHARACTER, 380)
			.focusOn(PERCENT_75_84)		.set(SEGMENT, 369)		.set(WORD, 3038)		.set(CHARACTER, 19476)
			.focusOn(REPETITIONS)		.set(SEGMENT, 435)		.set(WORD, 2012)		.set(CHARACTER, 13577)
			.focusOn(INTERNAL_95_99)	.set(SEGMENT, 0)		.set(WORD, 0)			.set(CHARACTER, 0)
			.focusOn(INTERNAL_85_94)	.set(SEGMENT, 767)		.set(WORD, 4671)		.set(CHARACTER, 29344)
			.focusOn(INTERNAL_75_84)	.set(SEGMENT, 119)		.set(WORD, 638)			.set(CHARACTER, 4212)
			.focusOn(NO_MATCH)			.set(SEGMENT, 325)		.set(WORD, 1723)		.set(CHARACTER, 11469);
		
		return new Object[][] {
				new Object[] {"/xtm/full-xls-XTMmetrics.xls", result1},
				new Object[] {"/xtm/XLSMetrics.xls", result2},
				new Object[] {"/xtm/full-xls2.xls", result3}
		};
	}
}
