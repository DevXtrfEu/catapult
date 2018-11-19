package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.MatchType.CONTEXT_MATCH;
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

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;

@Test
public class CATAnalysisParserSDLTMS2009HTMTest extends CATAnalysisParserTest<CATAnalysisParserSDLTMS2009HTM>{

	
	public CATAnalysisParserSDLTMS2009HTMTest() {
		super(CATAnalysisParserSDLTMS2009HTM.class);
	}
	
	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result1 = new CATAnalysis(CATTool.SDL_TRADOS, null);

		result1
			.focusOn(PERFECT_MATCH)	.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(REPETITIONS)	.set(SEGMENT, 3)	.set(WORD, 3)	.set(CHARACTER, 54)		.set(TOKEN, 2)
			.focusOn(CONTEXT_MATCH)	.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_100)	.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_95_99)	.set(SEGMENT, 3)	.set(WORD, 3)	.set(CHARACTER, 70)		.set(TOKEN, 3)
			.focusOn(PERCENT_85_94)	.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_75_84)	.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_50_74)	.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(NEW)			.set(SEGMENT, 101)	.set(WORD, 683)	.set(CHARACTER, 4359)	.set(TOKEN, 95)
			.focusOn(TOTAL)			.set(SEGMENT, 107)	.set(WORD, 689)	.set(CHARACTER, 4483)	.set(TOKEN, 100);
			
		CATAnalysis result2 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		
		result2
			.focusOn(PERFECT_MATCH)	.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(REPETITIONS)	.set(SEGMENT, 3)	.set(WORD, 3)	.set(CHARACTER, 54)		.set(TOKEN, 2)
			.focusOn(CONTEXT_MATCH)	.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_100)	.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_95_99)	.set(SEGMENT, 3)	.set(WORD, 3)	.set(CHARACTER, 70)		.set(TOKEN, 3)
			.focusOn(PERCENT_85_94)	.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_75_84)	.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_50_74)	.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(NEW)			.set(SEGMENT, 101)	.set(WORD, 683)	.set(CHARACTER, 4359)	.set(TOKEN, 95)
			.focusOn(TOTAL)			.set(SEGMENT, 107)	.set(WORD, 689)	.set(CHARACTER, 4483)	.set(TOKEN, 100);
		
		return new Object[][] {
				new Object[] {"/sdl/sdltms2009_1.htm", result1},
				new Object[] {"/sdl/sdltms2009_2.mht", result2}
		};
	}
}
