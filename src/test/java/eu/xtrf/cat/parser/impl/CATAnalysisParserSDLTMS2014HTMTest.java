package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import org.testng.annotations.DataProvider;

import java.math.BigDecimal;

import static eu.xtrf.cat.MatchType.INTERNAL_75_84;
import static eu.xtrf.cat.MatchType.LOCKED_SEGMENTS;
import static eu.xtrf.cat.MatchType.NEW;
import static eu.xtrf.cat.MatchType.PERCENT_75_84;
import static eu.xtrf.cat.MatchType.TOTAL;
import static eu.xtrf.cat.Unit.CHARACTER;
import static eu.xtrf.cat.Unit.SEGMENT;
import static eu.xtrf.cat.Unit.TOKEN;
import static eu.xtrf.cat.Unit.WORD;

/**
 * @author Marek Guzowski
 */
public class CATAnalysisParserSDLTMS2014HTMTest extends CATAnalysisParserTest<CATAnalysisParserSDLTMS2011HTM> {

	public CATAnalysisParserSDLTMS2014HTMTest() {
		super(CATAnalysisParserSDLTMS2011HTM.class);
	}

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result
			.focusOn(LOCKED_SEGMENTS)	.set(SEGMENT, 1)	.set(WORD, 2)	.set(CHARACTER, 5)	.set(TOKEN, 0)
			.focusOn(NEW)				.set(SEGMENT, 1)	.set(WORD, 5)	.set(CHARACTER, 16)	.set(TOKEN, 0)
			.focusOn(TOTAL)				.set(SEGMENT, 2)	.set(WORD, 7)	.set(CHARACTER, 21)	.set(TOKEN, 0);
		result.setCharsPerWord(new BigDecimal("3.00"));

		CATAnalysis result2 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result2
				.focusOn(NEW)	.set(SEGMENT, 1).set(WORD, 3).set(CHARACTER, 12).set(TOKEN, 0)
				.focusOn(TOTAL)	.set(SEGMENT, 1).set(WORD, 3).set(CHARACTER, 12).set(TOKEN, 0);
		result2.setCharsPerWord(new BigDecimal("4.00"));

		CATAnalysis result3 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result3
				.focusOn(NEW)	          .set(SEGMENT, 3).set(WORD, 9).set(CHARACTER, 28).set(TOKEN, 0)
				.focusOn(INTERNAL_75_84).set(SEGMENT, 1).set(WORD, 3).set(CHARACTER, 8).set(TOKEN, 0)
				.focusOn(TOTAL)	          .set(SEGMENT, 4).set(WORD, 12).set(CHARACTER, 36).set(TOKEN, 0);
		result3.setCharsPerWord(new BigDecimal("3.00"));

		return new Object[][] {
			new Object[] {"/sdl/lockedSegments/sdltms2014_en_locked.html", result},
			new Object[] {"/sdl/lockedSegments/sdltms2014_de_locked.html", result},
			new Object[] {"/sdl/lockedSegments/sdltms2014_fr_locked.html", result},
			new Object[] {"/sdl/lockedSegments/sdltms2014_es_locked.html", result},
			new Object[] {"/sdl/lockedSegments/sdltms2014_jp_locked.html", result},
			new Object[] {"/sdl/lockedSegments/sdltms2014_zh_locked.html", result},
			new Object[] {"/sdl/sdltms2015_it.html", result2},
			new Object[] {"/sdl/sdltms2015_it.mht", result2},
			new Object[] {"/sdl/sdltms2015_it_locked.html", result3},
			new Object[] {"/sdl/sdltms2015_ru.html", result2},
			new Object[] {"/sdl/sdltms2015_ru.mht", result2},
			new Object[] {"/sdl/sdltms2015_ru_locked.html", result3}
		};
	}
}
