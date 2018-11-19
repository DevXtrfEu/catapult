package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.parser.CATAnalysisParserException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static eu.xtrf.cat.Unit.CHARACTER;
import static eu.xtrf.cat.Unit.SEGMENT;
import static eu.xtrf.cat.Unit.TOKEN;
import static eu.xtrf.cat.Unit.WORD;

public class CATAnalysisParserSDLTMSXLSXTest extends CATAnalysisParserTest<CATAnalysisParserSDLTMSXLSX> {

	public CATAnalysisParserSDLTMSXLSXTest() {
		super(CATAnalysisParserSDLTMSXLSX.class);
	}

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result
			.focusOn(MatchType.LOCKED_SEGMENTS)			.set(SEGMENT, 1501)	.set(WORD, 5048)	.set(CHARACTER, 29646)	.set(TOKEN, 435)
			.focusOn(MatchType.PERFECT_MATCH)			.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(MatchType.CONTEXT_MATCH)			.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(MatchType.REPETITIONS)				.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(MatchType.CROSS_FILE_REPETITIONS)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(MatchType.PERCENT_100)				.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)		.set(TOKEN, 0)
			.focusOn(MatchType.PERCENT_95_99)			.set(SEGMENT, 16)	.set(WORD, 42)		.set(CHARACTER, 226)	.set(TOKEN, 1)
			.focusOn(MatchType.PERCENT_85_94)			.set(SEGMENT, 39)	.set(WORD, 243)		.set(CHARACTER, 1511)	.set(TOKEN, 10)
			.focusOn(MatchType.PERCENT_75_84)			.set(SEGMENT, 32)	.set(WORD, 156)		.set(CHARACTER, 956)	.set(TOKEN, 9)
			.focusOn(MatchType.PERCENT_50_74)			.set(SEGMENT, 17)	.set(WORD, 101)		.set(CHARACTER, 611)	.set(TOKEN, 10)
			.focusOn(MatchType.NEW)						.set(SEGMENT, 239)	.set(WORD, 1500)	.set(CHARACTER, 7810)	.set(TOKEN, 100)
			.focusOn(MatchType.TOTAL)					.set(SEGMENT, 1844)	.set(WORD, 7090)	.set(CHARACTER, 40760)	.set(TOKEN, 565);

		return new Object[][] {
			new Object[] {"/sdl/lockedSegments/sdltms2014_en_locked.xlsx", result}
		};
	}
}