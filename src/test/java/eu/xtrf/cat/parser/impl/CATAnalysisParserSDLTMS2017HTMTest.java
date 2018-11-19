package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.MatchType.NEW;
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
import eu.xtrf.cat.MatchType;

public class CATAnalysisParserSDLTMS2017HTMTest extends CATAnalysisParserTest<CATAnalysisParserSDLTMS2011HTM> {

	public CATAnalysisParserSDLTMS2017HTMTest() {
		super(CATAnalysisParserSDLTMS2011HTM.class);
	}

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result1 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result1
			.focusOn(NEW)				.set(SEGMENT, 4)	.set(WORD, 91)	.set(CHARACTER, 484)	.set(TOKEN, 2)
			.focusOn(TOTAL)				.set(SEGMENT, 4)	.set(WORD, 91)	.set(CHARACTER, 484)	.set(TOKEN, 2);
		result1.setCharsPerWord(new BigDecimal("5.32"));

		CATAnalysis result2 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result2
				.focusOn(MatchType.CONTEXT_MATCH)	.set(SEGMENT, 2).set(WORD, 3).set(CHARACTER, 9).set(TOKEN, 0)
				.focusOn(MatchType.PERCENT_100)	.set(SEGMENT, 2).set(WORD, 2).set(CHARACTER, 4).set(TOKEN, 0)
				.focusOn(TOTAL)	.set(SEGMENT, 4).set(WORD, 5).set(CHARACTER, 13).set(TOKEN, 0);
		result2.setCharsPerWord(new BigDecimal("2.60"));
		
		CATAnalysis result3 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result3
			.focusOn(REPETITIONS)		.set(SEGMENT, 1440)	.set(WORD, 0)	.set(CHARACTER, 37530)	.set(TOKEN, 0)
			.focusOn(NEW)				.set(SEGMENT, 16)	.set(WORD, 0)	.set(CHARACTER, 417)	.set(TOKEN, 0)
			.focusOn(TOTAL)				.set(SEGMENT, 1456)	.set(WORD, 0)	.set(CHARACTER, 37947)	.set(TOKEN, 0);


		return new Object[][] {
			new Object[] {"/sdl/sdltms2017_en.html", result1},
			new Object[] {"/sdl/sdltms2017_it.html", result1},
			new Object[] {"/sdl/sdltms2017_zh.html", result1},
			new Object[] {"/sdl/sdltms2017_ru.html", result1},
			new Object[] {"/sdl/sdltms2017_ja.html", result1},
			new Object[] {"/sdl/sdltms2017_fr.html", result1},
			new Object[] {"/sdl/sdltms2017_de.html", result2},
			new Object[] {"/sdl/sdltms2017_es.html", result2},
			new Object[] {"/sdl/sdltms2017_5.html", result3},
		};
	}
}