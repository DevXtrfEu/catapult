package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.MatchType.PERCENT_100;
import static eu.xtrf.cat.MatchType.PERCENT_50_74;
import static eu.xtrf.cat.MatchType.PERCENT_75_84;
import static eu.xtrf.cat.MatchType.PERCENT_85_94;
import static eu.xtrf.cat.MatchType.PERCENT_95_99;
import static eu.xtrf.cat.MatchType.TOTAL;
import static eu.xtrf.cat.MatchType.REPLICATED;
import static eu.xtrf.cat.MatchType.UNTRANSLATED;
import static eu.xtrf.cat.Unit.CHARACTER;
import static eu.xtrf.cat.Unit.SEGMENT;
import static eu.xtrf.cat.Unit.WORD;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.parser.impl.CATAnalysisParserSDLX;

@Test
public class CATAnalysisParserSDLXTest extends CATAnalysisParserTest<CATAnalysisParserSDLX> {

	public CATAnalysisParserSDLXTest() {
		super(CATAnalysisParserSDLX.class);
	}

	@DataProvider(name = "files")
	public Object[][] getFiles() {
		CATAnalysis trados1Result = new CATAnalysis(CATTool.SDLX, null);

		trados1Result
			.focusOn(REPLICATED)	.set(SEGMENT, 8)	.set(WORD, 48)		.set(CHARACTER, 304)
			.focusOn(PERCENT_100)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)
			.focusOn(PERCENT_95_99)	.set(SEGMENT, 119)	.set(WORD, 1209)	.set(CHARACTER, 6910)
			.focusOn(PERCENT_85_94)	.set(SEGMENT, 52)	.set(WORD, 597)		.set(CHARACTER, 3759)
			.focusOn(PERCENT_75_84)	.set(SEGMENT, 119)	.set(WORD, 1069)	.set(CHARACTER, 6776)
			.focusOn(PERCENT_50_74)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)
			.focusOn(UNTRANSLATED)	.set(SEGMENT, 862)	.set(WORD, 8290)	.set(CHARACTER, 51812)
			.focusOn(TOTAL)			.set(SEGMENT, 1160)	.set(WORD, 11213)	.set(CHARACTER, 69561);

		return new Object[][] {
			new Object[] { "/sdlx/sdlx-1.log", trados1Result }
		};
	}

}
