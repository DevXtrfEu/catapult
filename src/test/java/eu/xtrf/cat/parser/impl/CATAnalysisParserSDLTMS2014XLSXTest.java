package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static eu.xtrf.cat.MatchType.*;
import static eu.xtrf.cat.Unit.*;

@Test
public class CATAnalysisParserSDLTMS2014XLSXTest extends CATAnalysisParserTest<CATAnalysisParserSDLTMSXLSX> {

	public CATAnalysisParserSDLTMS2014XLSXTest() {
		super(CATAnalysisParserSDLTMSXLSX.class);
	}

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result1 = new CATAnalysis(CATTool.SDL_TRADOS, null);

		result1
            .focusOn(LOCKED_SEGMENTS)		.set(SEGMENT, 1517)	.set(WORD, 7821)	.set(CHARACTER, 54996)	.set(TOKEN, 546)
			.focusOn(CONTEXT_MATCH)			.set(SEGMENT, 3)	.set(WORD, 12)		.set(CHARACTER, 66)		.set(TOKEN, 0)
			.focusOn(PERCENT_100)			.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_95_99)			.set(SEGMENT, 415)	.set(WORD, 2967)	.set(CHARACTER, 21791)	.set(TOKEN, 106)
			.focusOn(PERCENT_85_94)			.set(SEGMENT, 104)	.set(WORD, 641)		.set(CHARACTER, 4853)	.set(TOKEN, 25)
			.focusOn(PERCENT_75_84)			.set(SEGMENT, 104)	.set(WORD, 578)		.set(CHARACTER, 3967)	.set(TOKEN, 47)
			.focusOn(PERCENT_50_74)			.set(SEGMENT, 1012)	.set(WORD, 5046)	.set(CHARACTER, 36394)	.set(TOKEN, 336)
			.focusOn(REPETITIONS)			.set(SEGMENT, 343)	.set(WORD, 2009)	.set(CHARACTER, 14255)	.set(TOKEN, 115)
			.focusOn(CROSS_FILE_REPETITIONS).set(SEGMENT, 107)	.set(WORD, 745)		.set(CHARACTER, 5881)	.set(TOKEN, 1)
			.focusOn(NEW)					.set(SEGMENT, 1518)	.set(WORD, 14403)	.set(CHARACTER, 100540)	.set(TOKEN, 484)
			.focusOn(TOTAL)					.set(SEGMENT, 5123)	.set(WORD, 34222)	.set(CHARACTER, 242743)	.set(TOKEN, 1660);
		result1.setCharsPerWord(new BigDecimal("7.09"));

		CATAnalysis result2 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result2
			.focusOn(LOCKED_SEGMENTS)		.set(SEGMENT, 1501)	.set(WORD, 5048)	.set(CHARACTER, 29646)	.set(TOKEN, 435)
			.focusOn(PERFECT_MATCH)			.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(CONTEXT_MATCH)			.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(REPETITIONS)			.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(CROSS_FILE_REPETITIONS).set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_100)			.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(PERCENT_95_99)			.set(SEGMENT, 16)	.set(WORD, 42)		.set(CHARACTER, 226)	.set(TOKEN, 1)
			.focusOn(PERCENT_85_94)			.set(SEGMENT, 39)	.set(WORD, 243)		.set(CHARACTER, 1511)	.set(TOKEN, 10)
			.focusOn(PERCENT_75_84)			.set(SEGMENT, 32)	.set(WORD, 156)		.set(CHARACTER, 956)	.set(TOKEN, 9)
			.focusOn(PERCENT_50_74)			.set(SEGMENT, 17)	.set(WORD, 101)		.set(CHARACTER, 611)	.set(TOKEN, 10)
			.focusOn(NEW)					.set(SEGMENT, 239)	.set(WORD, 1500)	.set(CHARACTER, 7810)	.set(TOKEN, 100)
			.focusOn(TOTAL)					.set(SEGMENT, 1844)	.set(WORD, 7090)	.set(CHARACTER, 40760)	.set(TOKEN, 565);


		return new Object[][] {
			new Object[] {"/sdl/2014-06-122-de-fr_mit_Sperrung.xlsx", result1},
			new Object[] {"/sdl/lockedSegments/sdltms2014_en_locked.xlsx", result2}
		};
	}
}

