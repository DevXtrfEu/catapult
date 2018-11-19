package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.MatchType.ADAPTIVE_MT_BASELINE;
import static eu.xtrf.cat.MatchType.ADAPTIVE_MT_WITH_LEARNINGS;
import static eu.xtrf.cat.MatchType.CONTEXT_MATCH;
import static eu.xtrf.cat.MatchType.CROSS_FILE_REPETITIONS;
import static eu.xtrf.cat.MatchType.INTERNAL_50_74;
import static eu.xtrf.cat.MatchType.INTERNAL_75_84;
import static eu.xtrf.cat.MatchType.INTERNAL_85_94;
import static eu.xtrf.cat.MatchType.INTERNAL_95_99;
import static eu.xtrf.cat.MatchType.NEW;
import static eu.xtrf.cat.MatchType.PERCENT_100;
import static eu.xtrf.cat.MatchType.PERCENT_50_74;
import static eu.xtrf.cat.MatchType.PERCENT_75_84;
import static eu.xtrf.cat.MatchType.PERCENT_85_94;
import static eu.xtrf.cat.MatchType.PERCENT_95_99;
import static eu.xtrf.cat.MatchType.PERFECT_MATCH;
import static eu.xtrf.cat.MatchType.REPETITIONS;
import static eu.xtrf.cat.MatchType.TOTAL;
import static eu.xtrf.cat.Unit.CHARACTER;
import static eu.xtrf.cat.Unit.SEGMENT;
import static eu.xtrf.cat.Unit.TOKEN;
import static eu.xtrf.cat.Unit.WORD;

import java.math.BigDecimal;

import org.testng.annotations.DataProvider;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;

public class CATAnalysisParserSDLTMS2017XLSXTest extends CATAnalysisParserTest<CATAnalysisParserSDLTMSXLSX> {

	public CATAnalysisParserSDLTMS2017XLSXTest() {
		super(CATAnalysisParserSDLTMSXLSX.class);
	}

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result1 = new CATAnalysis(CATTool.SDL_TRADOS, null);

		result1
            .focusOn(PERFECT_MATCH)				.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(CONTEXT_MATCH)				.set(SEGMENT, 32)	.set(WORD, 383)		.set(CHARACTER, 2029)	.set(TOKEN, 17)
			.focusOn(REPETITIONS)				.set(SEGMENT, 271)	.set(WORD, 703)		.set(CHARACTER, 5738)	.set(TOKEN, 322)
			.focusOn(CROSS_FILE_REPETITIONS)	.set(SEGMENT, 84)	.set(WORD, 609)		.set(CHARACTER, 4753)	.set(TOKEN, 56)
			.focusOn(PERCENT_100)				.set(SEGMENT, 155)	.set(WORD, 656)		.set(CHARACTER, 4334)	.set(TOKEN, 72)
			.focusOn(PERCENT_95_99)				.set(SEGMENT, 226)	.set(WORD, 478)		.set(CHARACTER, 13577)	.set(TOKEN, 247)
			.focusOn(PERCENT_85_94)				.set(SEGMENT, 21)	.set(WORD, 149)		.set(CHARACTER, 783)	.set(TOKEN, 26)
			.focusOn(PERCENT_75_84)				.set(SEGMENT, 27)	.set(WORD, 137)		.set(CHARACTER, 731)	.set(TOKEN, 84)
			.focusOn(PERCENT_50_74)				.set(SEGMENT, 44)	.set(WORD, 209)		.set(CHARACTER, 1190)	.set(TOKEN, 130)
			.focusOn(NEW)						.set(SEGMENT, 766)	.set(WORD, 8052)	.set(CHARACTER, 45986)	.set(TOKEN, 983)
			.focusOn(ADAPTIVE_MT_BASELINE)		.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(ADAPTIVE_MT_WITH_LEARNINGS).set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(TOTAL)						.set(SEGMENT, 1626)	.set(WORD, 11376)	.set(CHARACTER, 79121)	.set(TOKEN, 1937);
		result1.setCharsPerWord(new BigDecimal("6.96"));

		CATAnalysis result2 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result2
			.focusOn(PERFECT_MATCH)				.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(CONTEXT_MATCH)				.set(SEGMENT, 1)	.set(WORD, 1)		.set(CHARACTER, 6)		.set(TOKEN, 1)
			.focusOn(REPETITIONS)				.set(SEGMENT, 1)	.set(WORD, 19)		.set(CHARACTER, 106)	.set(TOKEN, 2)
			.focusOn(CROSS_FILE_REPETITIONS)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_100)				.set(SEGMENT, 11)	.set(WORD, 11)		.set(CHARACTER, 25)		.set(TOKEN, 11)
			.focusOn(PERCENT_95_99)				.set(SEGMENT, 1)	.set(WORD, 1)		.set(CHARACTER, 2)		.set(TOKEN, 1)
			.focusOn(PERCENT_85_94)				.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_75_84)				.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_50_74)				.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(NEW)						.set(SEGMENT, 19)	.set(WORD, 367)		.set(CHARACTER, 1991)	.set(TOKEN, 92)
			.focusOn(ADAPTIVE_MT_BASELINE)		.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(ADAPTIVE_MT_WITH_LEARNINGS).set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(TOTAL)						.set(SEGMENT, 33)	.set(WORD, 399)		.set(CHARACTER, 2130)	.set(TOKEN, 107);
		result2.setCharsPerWord(new BigDecimal("5.34"));
		
		CATAnalysis result3 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result3
			.focusOn(PERFECT_MATCH)				.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(CONTEXT_MATCH)				.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(REPETITIONS)				.set(SEGMENT, 14463).set(WORD, 27576)	.set(CHARACTER, 184098)	.set(TOKEN, 10922)
			.focusOn(CROSS_FILE_REPETITIONS)	.set(SEGMENT, 61793).set(WORD, 108941)	.set(CHARACTER, 689305)	.set(TOKEN, 55565)
			.focusOn(PERCENT_100)				.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_95_99)				.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_85_94)				.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_75_84)				.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_50_74)				.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(INTERNAL_95_99)			.set(SEGMENT, 261)	.set(WORD, 1170)	.set(CHARACTER, 5946)	.set(TOKEN, 680)
			.focusOn(INTERNAL_85_94)			.set(SEGMENT, 479)	.set(WORD, 2357)	.set(CHARACTER, 12317)	.set(TOKEN, 1334)
			.focusOn(INTERNAL_75_84)			.set(SEGMENT, 934)	.set(WORD, 3453)	.set(CHARACTER, 19326)	.set(TOKEN, 1281)
			.focusOn(INTERNAL_50_74)			.set(SEGMENT, 295)	.set(WORD, 1043)	.set(CHARACTER, 5763)	.set(TOKEN, 265)
			.focusOn(NEW)						.set(SEGMENT, 3598)	.set(WORD, 16010)	.set(CHARACTER, 104321)	.set(TOKEN, 0)
			.focusOn(ADAPTIVE_MT_BASELINE)		.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(ADAPTIVE_MT_WITH_LEARNINGS).set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(TOTAL)						.set(SEGMENT, 81823).set(WORD, 160550)	.set(CHARACTER, 1021076).set(TOKEN, 72566);
		result3.setCharsPerWord(new BigDecimal("6.36"));

		return new Object[][] {
			new Object[] {"/sdl/sdltms2017_3.xlsx", result1},
			new Object[] {"/sdl/sdltms2017_4.xlsx", result2},
			new Object[] {"/sdl/sdltms2017_fuzzyMatches.xlsx", result3}
		};
	}
}