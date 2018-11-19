package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.MatchType.CONTEXT_MATCH;
import static eu.xtrf.cat.MatchType.CROSS_FILE_REPETITIONS;
import static eu.xtrf.cat.MatchType.INTERNAL_50_74;
import static eu.xtrf.cat.MatchType.INTERNAL_75_84;
import static eu.xtrf.cat.MatchType.INTERNAL_85_94;
import static eu.xtrf.cat.MatchType.INTERNAL_95_99;
import static eu.xtrf.cat.MatchType.LOCKED_SEGMENTS;
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

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.parser.impl.CATAnalysisParserSDLTradosXML;

/**
 * Test for {@link CATAnalysisParserSDLTradosXML}.
 * 
 * @author Lukasz Wiktor
 */
@Test
public class CATAnalysisParserSDLTradosXMLTest extends CATAnalysisParserTest<CATAnalysisParserSDLTradosXML> {
	
	public CATAnalysisParserSDLTradosXMLTest() {
		super(CATAnalysisParserSDLTradosXML.class);
	}
	
	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result1 = new CATAnalysis(CATTool.SDL_TRADOS, null);

		result1
			.focusOn(PERFECT_MATCH)	.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(CONTEXT_MATCH)	.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_100)	.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(REPETITIONS)	.set(SEGMENT, 3)	.set(WORD, 3)	.set(CHARACTER, 54)		.set(TOKEN, 2)
			.focusOn(TOTAL)			.set(SEGMENT, 107)	.set(WORD, 689)	.set(CHARACTER, 4483)	.set(TOKEN, 100)
			.focusOn(NEW)			.set(SEGMENT, 101)	.set(WORD, 683)	.set(CHARACTER, 4359)	.set(TOKEN, 95)
			.focusOn(PERCENT_50_74)	.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_75_84)	.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_85_94)	.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_95_99)	.set(SEGMENT, 3)	.set(WORD, 3)	.set(CHARACTER, 70)		.set(TOKEN, 3);
		
		CATAnalysis result2 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		
		result2
			.focusOn(PERFECT_MATCH)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(CONTEXT_MATCH)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_100)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(REPETITIONS)	.set(SEGMENT, 10)	.set(WORD, 118)		.set(CHARACTER, 636)	.set(TOKEN, 21)
			.focusOn(TOTAL)			.set(SEGMENT, 145)	.set(WORD, 1283)	.set(CHARACTER, 7198)	.set(TOKEN, 329)
			.focusOn(NEW)			.set(SEGMENT, 130)	.set(WORD, 1144)	.set(CHARACTER, 6441)	.set(TOKEN, 297)
			.focusOn(PERCENT_50_74)	.set(SEGMENT, 2)	.set(WORD, 12)		.set(CHARACTER, 59)		.set(TOKEN, 4)
			.focusOn(PERCENT_75_84)	.set(SEGMENT, 1)	.set(WORD, 3)		.set(CHARACTER, 21)		.set(TOKEN, 0)
			.focusOn(PERCENT_85_94)	.set(SEGMENT, 1)	.set(WORD, 2)		.set(CHARACTER, 21)		.set(TOKEN, 5)
			.focusOn(PERCENT_95_99)	.set(SEGMENT, 1)	.set(WORD, 4)		.set(CHARACTER, 20)		.set(TOKEN, 2);
				
		CATAnalysis result3 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		
		result3
			.focusOn(PERFECT_MATCH)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(CONTEXT_MATCH)	.set(SEGMENT, 786)	.set(WORD, 5494)	.set(CHARACTER, 30923)	.set(TOKEN, 350)
			.focusOn(PERCENT_100)	.set(SEGMENT, 374)	.set(WORD, 1660)	.set(CHARACTER, 10225)	.set(TOKEN, 103)
			.focusOn(REPETITIONS)	.set(SEGMENT, 97)	.set(WORD, 636)		.set(CHARACTER, 3674)	.set(TOKEN, 78)
			.focusOn(TOTAL)			.set(SEGMENT, 2444)	.set(WORD, 19476)	.set(CHARACTER, 109676)	.set(TOKEN, 2108)
			.focusOn(NEW)			.set(SEGMENT, 832)	.set(WORD, 7726)	.set(CHARACTER, 42385)	.set(TOKEN, 770)
			.focusOn(PERCENT_50_74)	.set(SEGMENT, 20)	.set(WORD, 214)		.set(CHARACTER, 1170)	.set(TOKEN, 61)
			.focusOn(PERCENT_75_84)	.set(SEGMENT, 55)	.set(WORD, 425)		.set(CHARACTER, 2379)	.set(TOKEN, 100)
			.focusOn(PERCENT_85_94)	.set(SEGMENT, 109)	.set(WORD, 1241)	.set(CHARACTER, 7185)	.set(TOKEN, 445)
			.focusOn(PERCENT_95_99)	.set(SEGMENT, 171)	.set(WORD, 2080)	.set(CHARACTER, 11735)	.set(TOKEN, 201);
				
		CATAnalysis result4 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		
		result4
			.focusOn(PERFECT_MATCH)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(CONTEXT_MATCH)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_100)	.set(SEGMENT, 13)	.set(WORD, 13)		.set(CHARACTER, 26)		.set(TOKEN, 13)
			.focusOn(REPETITIONS)	.set(SEGMENT, 103)	.set(WORD, 259)		.set(CHARACTER, 1236)	.set(TOKEN, 51)
			.focusOn(TOTAL)			.set(SEGMENT, 397)	.set(WORD, 2875)	.set(CHARACTER, 15252)	.set(TOKEN, 262)
			.focusOn(NEW)			.set(SEGMENT, 256)	.set(WORD, 2483)	.set(CHARACTER, 13312)	.set(TOKEN, 169)
			.focusOn(PERCENT_50_74)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_75_84)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_85_94)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_95_99)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(INTERNAL_50_74).set(SEGMENT, 6)	.set(WORD, 38)		.set(CHARACTER, 203)	.set(TOKEN, 14)
			.focusOn(INTERNAL_75_84).set(SEGMENT, 12)	.set(WORD, 56)		.set(CHARACTER, 332)	.set(TOKEN, 9)
			.focusOn(INTERNAL_85_94).set(SEGMENT, 4)	.set(WORD, 17)		.set(CHARACTER, 89)		.set(TOKEN, 6)
			.focusOn(INTERNAL_95_99).set(SEGMENT, 3)	.set(WORD, 9)		.set(CHARACTER, 54)		.set(TOKEN, 0);
				
		CATAnalysis result5 = new CATAnalysis(CATTool.SDL_TRADOS, null);

		result5
			.focusOn(PERFECT_MATCH)			.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(CONTEXT_MATCH)			.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_100)			.set(SEGMENT, 1)	.set(WORD, 1)		.set(CHARACTER, 7)		.set(TOKEN, 0)
			.focusOn(CROSS_FILE_REPETITIONS).set(SEGMENT, 5)	.set(WORD, 79)		.set(CHARACTER, 417)	.set(TOKEN, 0)
			.focusOn(REPETITIONS)			.set(SEGMENT, 665)	.set(WORD, 3227)	.set(CHARACTER, 17160)	.set(TOKEN, 467)
			.focusOn(TOTAL)					.set(SEGMENT, 1795)	.set(WORD, 15561)	.set(CHARACTER, 82369)	.set(TOKEN, 3008)
			.focusOn(NEW)					.set(SEGMENT, 1121)	.set(WORD, 12245)	.set(CHARACTER, 64732)	.set(TOKEN, 2541)
			.focusOn(PERCENT_50_74)			.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_75_84)			.set(SEGMENT, 2)	.set(WORD, 8)		.set(CHARACTER, 50)		.set(TOKEN, 0)
			.focusOn(PERCENT_85_94)			.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_95_99)			.set(SEGMENT, 1)	.set(WORD, 1)		.set(CHARACTER, 3)		.set(TOKEN, 0);
			
		CATAnalysis result6 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		
		result6
			.focusOn(PERFECT_MATCH)			.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(CONTEXT_MATCH)			.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_100)			.set(SEGMENT, 1112)	.set(WORD, 3328)	.set(CHARACTER, 14323)	.set(TOKEN, 456)
			.focusOn(CROSS_FILE_REPETITIONS).set(SEGMENT, 10)	.set(WORD, 35)		.set(CHARACTER, 154)	.set(TOKEN, 14)
			.focusOn(REPETITIONS)			.set(SEGMENT, 260)	.set(WORD, 648)		.set(CHARACTER, 3323)	.set(TOKEN, 843)
			.focusOn(TOTAL)					.set(SEGMENT, 2139)	.set(WORD, 7856)	.set(CHARACTER, 37061)	.set(TOKEN, 3621)
			.focusOn(NEW)					.set(SEGMENT, 415)	.set(WORD, 1583)	.set(CHARACTER, 7766)	.set(TOKEN, 1214)
			.focusOn(PERCENT_50_74)			.set(SEGMENT, 42)	.set(WORD, 200)		.set(CHARACTER, 998)	.set(TOKEN, 240)
			.focusOn(PERCENT_75_84)			.set(SEGMENT, 107)	.set(WORD, 601)		.set(CHARACTER, 2903)	.set(TOKEN, 369)
			.focusOn(PERCENT_85_94)			.set(SEGMENT, 54)	.set(WORD, 486)		.set(CHARACTER, 2465)	.set(TOKEN, 174)
			.focusOn(PERCENT_95_99)			.set(SEGMENT, 139)	.set(WORD, 975)		.set(CHARACTER, 5129)	.set(TOKEN, 311);

		CATAnalysis result7 = new CATAnalysis(CATTool.SDL_TRADOS, null);

		result7
			.focusOn(PERFECT_MATCH)			.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(CONTEXT_MATCH)			.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_100)			.set(SEGMENT, 83)	.set(WORD, 333)	.set(CHARACTER, 2030)	.set(TOKEN, 98)
			.focusOn(LOCKED_SEGMENTS)		.set(SEGMENT, 94)	.set(WORD, 426)	.set(CHARACTER, 2541)	.set(TOKEN, 89)
			.focusOn(CROSS_FILE_REPETITIONS).set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(REPETITIONS)			.set(SEGMENT, 63)	.set(WORD, 177)	.set(CHARACTER, 958)	.set(TOKEN, 118)
			.focusOn(TOTAL)					.set(SEGMENT, 640)	.set(WORD, 4978).set(CHARACTER, 31948)	.set(TOKEN, 606)
			.focusOn(NEW)					.set(SEGMENT, 303)	.set(WORD, 2976).set(CHARACTER, 19683)	.set(TOKEN, 135)
			.focusOn(PERCENT_50_74)			.set(SEGMENT, 3)	.set(WORD, 23)	.set(CHARACTER, 146)	.set(TOKEN, 8)
			.focusOn(PERCENT_75_84)			.set(SEGMENT, 16)	.set(WORD, 146)	.set(CHARACTER, 736)	.set(TOKEN, 58)
			.focusOn(PERCENT_85_94)			.set(SEGMENT, 21)	.set(WORD, 361)	.set(CHARACTER, 2415)	.set(TOKEN, 18)
			.focusOn(PERCENT_95_99)			.set(SEGMENT, 35)	.set(WORD, 303)	.set(CHARACTER, 2166)	.set(TOKEN, 38)
			.focusOn(INTERNAL_50_74)		.set(SEGMENT, 3)	.set(WORD, 27)	.set(CHARACTER, 160)	.set(TOKEN, 4)
			.focusOn(INTERNAL_75_84)		.set(SEGMENT, 3)	.set(WORD, 37)	.set(CHARACTER, 189)	.set(TOKEN, 11)
			.focusOn(INTERNAL_85_94)		.set(SEGMENT, 13)	.set(WORD, 101)	.set(CHARACTER, 593)	.set(TOKEN, 16)
			.focusOn(INTERNAL_95_99)		.set(SEGMENT, 3)	.set(WORD, 68)	.set(CHARACTER, 331)	.set(TOKEN, 13);

		CATAnalysis result8 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result8
				.focusOn(PERFECT_MATCH)		.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
				.focusOn(CONTEXT_MATCH)		.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
				.focusOn(PERCENT_100)			.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)	    .set(TOKEN, 0)
				.focusOn(LOCKED_SEGMENTS)		.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)	    .set(TOKEN, 0)
				.focusOn(CROSS_FILE_REPETITIONS).set(SEGMENT, 0).set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
				.focusOn(REPETITIONS)			.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)	    .set(TOKEN, 0)
				.focusOn(TOTAL)					.set(SEGMENT, 1)	.set(WORD, 3)  .set(CHARACTER, 12)	.set(TOKEN, 0)
				.focusOn(NEW)					.set(SEGMENT, 1)	.set(WORD, 3)  .set(CHARACTER, 12)	.set(TOKEN, 0)
				.focusOn(PERCENT_50_74)		.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)	.set(TOKEN, 0)
				.focusOn(PERCENT_75_84)		.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)	.set(TOKEN, 0)
				.focusOn(PERCENT_85_94)		.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)	.set(TOKEN, 0)
				.focusOn(PERCENT_95_99)		.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)	.set(TOKEN, 0)
				.focusOn(INTERNAL_50_74)		.set(SEGMENT, 0).set(WORD, 0)	.set(CHARACTER, 0)	.set(TOKEN, 0)
				.focusOn(INTERNAL_75_84)		.set(SEGMENT, 0).set(WORD, 0)	.set(CHARACTER, 0)	.set(TOKEN, 0)
				.focusOn(INTERNAL_85_94)		.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)	.set(TOKEN, 0)
				.focusOn(INTERNAL_95_99)		.set(SEGMENT, 0).set(WORD, 0)	.set(CHARACTER, 0)	.set(TOKEN, 0);

		CATAnalysis result9 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result9
				.focusOn(REPETITIONS)	.set(SEGMENT, 3607)	.set(CHARACTER, 20836)	.set(TOKEN, 2953)
				.focusOn(TOTAL)			.set(SEGMENT, 5573)	.set(CHARACTER, 78039)	.set(TOKEN, 4595)
				.focusOn(NEW)			.set(SEGMENT, 1966)	.set(CHARACTER, 57203)	.set(TOKEN, 1642);
		
		CATAnalysis result10 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result10
			.focusOn(PERFECT_MATCH)			.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(CONTEXT_MATCH)			.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_100)			.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(LOCKED_SEGMENTS)		.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)	    .set(TOKEN, 0)
			.focusOn(CROSS_FILE_REPETITIONS).set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(REPETITIONS)			.set(SEGMENT, 7)	.set(WORD, 7)		.set(CHARACTER, 43)		.set(TOKEN, 9)
			.focusOn(TOTAL)					.set(SEGMENT, 145)	.set(WORD, 1515)	.set(CHARACTER, 8453)	.set(TOKEN, 142)
			.focusOn(NEW)					.set(SEGMENT, 138)	.set(WORD, 1508)	.set(CHARACTER, 8410)	.set(TOKEN, 133)
			.focusOn(MatchType.ADAPTIVE_MT_BASELINE).set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)	.set(TOKEN, 0)
			.focusOn(MatchType.ADAPTIVE_MT_WITH_LEARNINGS).set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)	.set(TOKEN, 0)
			.focusOn(PERCENT_50_74)			.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_75_84)			.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_85_94)			.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_95_99)			.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0);
		
		CATAnalysis result11 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result11
			.focusOn(PERFECT_MATCH)			.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(CONTEXT_MATCH)			.set(SEGMENT, 32)	.set(WORD, 383)		.set(CHARACTER, 2029)	.set(TOKEN, 17)
			.focusOn(PERCENT_100)			.set(SEGMENT, 155)	.set(WORD, 656)		.set(CHARACTER, 4334)	.set(TOKEN, 72)
			.focusOn(LOCKED_SEGMENTS)		.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)	    .set(TOKEN, 0)
			.focusOn(CROSS_FILE_REPETITIONS).set(SEGMENT, 84)	.set(WORD, 609)		.set(CHARACTER, 4753)	.set(TOKEN, 56)
			.focusOn(REPETITIONS)			.set(SEGMENT, 271)	.set(WORD, 703)		.set(CHARACTER, 5738)	.set(TOKEN, 322)
			.focusOn(TOTAL)					.set(SEGMENT, 1626)	.set(WORD, 11376)	.set(CHARACTER, 79121)	.set(TOKEN, 1937)
			.focusOn(NEW)					.set(SEGMENT, 766)	.set(WORD, 8052)	.set(CHARACTER, 45986)	.set(TOKEN, 983)
			.focusOn(MatchType.ADAPTIVE_MT_BASELINE).set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)	.set(TOKEN, 0)
			.focusOn(MatchType.ADAPTIVE_MT_WITH_LEARNINGS).set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)	.set(TOKEN, 0)
			.focusOn(PERCENT_50_74)			.set(SEGMENT, 44)	.set(WORD, 209)		.set(CHARACTER, 1190)		.set(TOKEN, 130)
			.focusOn(PERCENT_75_84)			.set(SEGMENT, 27)	.set(WORD, 137)		.set(CHARACTER, 731)		.set(TOKEN, 84)
			.focusOn(PERCENT_85_94)			.set(SEGMENT, 21)	.set(WORD, 149)		.set(CHARACTER, 783)		.set(TOKEN, 26)
			.focusOn(PERCENT_95_99)			.set(SEGMENT, 226)	.set(WORD, 478)		.set(CHARACTER, 13577)		.set(TOKEN, 247);

		return new Object[][] {
				new Object[] {"/sdl/sdltms2009_3.xml", result1},
				new Object[] {"/sdl/sdltms2009_4.xml", result2},
				new Object[] {"/sdl/sdltms2009_5.xml", result3},
				new Object[] {"/sdl/trados2009fuzzy2.xml", result4},
				new Object[] {"/sdl/sdltms2011_1.xml", result5},
				new Object[] {"/sdl/sdltms2011_2.xml", result6},
				new Object[] {"/sdl/sdltms2014_lockedsegments.xml", result7},
				new Object[] {"/sdl/sdltms2015_ru.xml", result8},
				new Object[] {"/sdl/sdltms2015_it.xml", result8},
				new Object[] {"/sdl/Analyze Files ja-JP_en-US.xml", result9},
				new Object[] {"/sdl/sdltms2017_1.xml", result10},
				new Object[] {"/sdl/sdltms2017_2.xml", result11}
		};
	}

}
