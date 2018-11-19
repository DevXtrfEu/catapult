package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import org.testng.annotations.DataProvider;

import static eu.xtrf.cat.MatchType.NO_MATCH;
import static eu.xtrf.cat.MatchType.PERCENT_100;
import static eu.xtrf.cat.MatchType.PERCENT_50_74;
import static eu.xtrf.cat.MatchType.PERCENT_75_84;
import static eu.xtrf.cat.MatchType.PERCENT_85_94;
import static eu.xtrf.cat.MatchType.PERCENT_95_99;
import static eu.xtrf.cat.MatchType.REPETITIONS;
import static eu.xtrf.cat.MatchType.TOTAL;
import static eu.xtrf.cat.Unit.WORD;


public class CATAnalysisParserSDLTradosMHTMLTest extends CATAnalysisParserTest<CATAnalysisParserSDLTradosMHTML>{

	public CATAnalysisParserSDLTradosMHTMLTest() {
		super(CATAnalysisParserSDLTradosMHTML.class);
	}

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis sdltmsResult1 = new CATAnalysis(CATTool.TRADOS, null);

		sdltmsResult1
			.set(WORD, TOTAL,         	1598)
			.set(WORD, REPETITIONS, 	388)
			.set(WORD, PERCENT_100,   	312)
			.set(WORD, PERCENT_95_99,   761)
			.set(WORD, PERCENT_85_94,  	46)
			.set(WORD, PERCENT_75_84,  	39)
			.set(WORD, PERCENT_50_74,  	36)
			.set(WORD, NO_MATCH,        16);

		return new Object[][] {
			new Object[] {"/sdl/ManualTaskAnalysis_6051525_CS-CZ.mhtml", sdltmsResult1},
		};
	}
}