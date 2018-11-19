package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.MatchType.CONTEXT_MATCH;
import static eu.xtrf.cat.MatchType.CROSS_FILE_REPETITIONS;
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

import java.math.BigDecimal;

@Test
public class CATAnalysisParserSDLTMS2011HTMTest extends CATAnalysisParserTest<CATAnalysisParserSDLTMS2011HTM>{

	public CATAnalysisParserSDLTMS2011HTMTest() {
		super(CATAnalysisParserSDLTMS2011HTM.class);
	}

	@DataProvider(name="files")
	public Object[][] getFiles() {
		
		CATAnalysis result1 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		
		result1
			.focusOn(PERFECT_MATCH)			.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(CONTEXT_MATCH)			.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(REPETITIONS)			.set(SEGMENT, 176)	.set(WORD, 434)	.set(CHARACTER, 2062)	.set(TOKEN, 95)
			.focusOn(CROSS_FILE_REPETITIONS).set(SEGMENT, 23)	.set(WORD, 47)	.set(CHARACTER, 302)	.set(TOKEN, 20)
			.focusOn(PERCENT_100)			.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_95_99)			.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_85_94)			.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_75_84)			.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_50_74)			.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(NEW)					.set(SEGMENT, 290)	.set(WORD, 1998).set(CHARACTER, 10253)	.set(TOKEN, 314)
			.focusOn(TOTAL)					.set(SEGMENT, 489)	.set(WORD, 2479).set(CHARACTER, 12617)	.set(TOKEN, 429);
		result1.setCharsPerWord(new BigDecimal("5.09"));
		/*result6
			.set(SEGMENT, TOTAL, "DC30582B.DOC.sdlxliff", 420)
			.set(SEGMENT, TOTAL, "DC31096.doc.sdlxliff", 69);*/
	
		CATAnalysis result2 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		
		result2
			.focusOn(REPETITIONS)			.set(SEGMENT, 4056)	.set(WORD, 32188)	.set(CHARACTER, 171410)	.set(TOKEN, 3228)
			.focusOn(CROSS_FILE_REPETITIONS).set(SEGMENT, 77)	.set(WORD, 16)		.set(CHARACTER, 83)		.set(TOKEN, 74)
			.focusOn(NEW)					.set(SEGMENT, 3018)	.set(WORD, 31758)	.set(CHARACTER, 173032)	.set(TOKEN, 2466)
			.focusOn(TOTAL)					.set(SEGMENT, 7151)	.set(WORD, 63962)	.set(CHARACTER, 344525)	.set(TOKEN, 5768);
		result2.setCharsPerWord(new BigDecimal("5.39"));

		CATAnalysis result3 = new CATAnalysis(CATTool.SDL_TRADOS, null);

		result3
			.focusOn(NEW)					.set(SEGMENT, 4)	.set(WORD, 78)		.set(CHARACTER, 290)	.set(TOKEN, 0)
			.focusOn(TOTAL)					.set(SEGMENT, 4)	.set(WORD, 78)		.set(CHARACTER, 290)	.set(TOKEN, 0);
		result3.setCharsPerWord(new BigDecimal("3.72"));

		return new Object[][] {
				new Object[] {"/sdl/sdltms2011.html", result1},
				new Object[] {"/sdl/sdltms2011_en.html", result2},
				new Object[] {"/sdl/sdltms2011_de.html", result2},
				new Object[] {"/sdl/sdltms2011_fr.html", result2},
				new Object[] {"/sdl/sdltms2011_sp.html", result2},
				new Object[] {"/sdl/sdltms2011_jp.html", result2},
				new Object[] {"/sdl/sdltms2011_zh.html", result2},
				new Object[] {"/sdl/sdltms2011_multipleFiles.html", result3}

		};
	}

	
}
