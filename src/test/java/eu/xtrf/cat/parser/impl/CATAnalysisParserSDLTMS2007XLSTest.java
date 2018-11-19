package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import org.testng.annotations.DataProvider;

import static eu.xtrf.cat.MatchType.NO_MATCH;
import static eu.xtrf.cat.MatchType.PERCENT_100;
import static eu.xtrf.cat.MatchType.PERCENT_85_94;
import static eu.xtrf.cat.MatchType.PERCENT_95_99;
import static eu.xtrf.cat.MatchType.REPETITIONS;
import static eu.xtrf.cat.MatchType.TOTAL;
import static eu.xtrf.cat.Unit.WORD;

public class CATAnalysisParserSDLTMS2007XLSTest extends CATAnalysisParserTest<CATAnalysisParserSDLTMS2007XLS> {

	public CATAnalysisParserSDLTMS2007XLSTest() {
		super(CATAnalysisParserSDLTMS2007XLS.class);
	}

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis sdltmsResult1 = new CATAnalysis(CATTool.TRADOS, null);

		sdltmsResult1
			.set(WORD, TOTAL,         	61666)
			.set(WORD, REPETITIONS,		251)
			.set(WORD, PERCENT_100,   	58252)
			.set(WORD, PERCENT_95_99,   264)
			.set(WORD, PERCENT_85_94,  	1070)
			.set(WORD, NO_MATCH,        1829);

		CATAnalysis sdltmsResult2 = new CATAnalysis(CATTool.TRADOS, null);

		sdltmsResult2
			.set(WORD, TOTAL,         	2730)
			.set(WORD, REPETITIONS,		0)
			.set(WORD, PERCENT_100,   	2700)
			.set(WORD, PERCENT_95_99,   4)
			.set(WORD, PERCENT_85_94,  	0)
			.set(WORD, NO_MATCH,        26);

		return new Object[][] {
			new Object[] {"/sdl/OM14E-0V37E1E FR.xls", sdltmsResult1},
			new Object[] {"/sdl/OM14E-0V37E1E FR xtra.xls", sdltmsResult2}
		};
	}
}
