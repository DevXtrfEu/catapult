package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.MatchType.NEW;
import static eu.xtrf.cat.MatchType.PERCENT_100;
import static eu.xtrf.cat.MatchType.PERCENT_85_94;
import static eu.xtrf.cat.MatchType.PERCENT_95_99;
import static eu.xtrf.cat.MatchType.REPETITIONS;
import static eu.xtrf.cat.MatchType.TOTAL;
import static eu.xtrf.cat.Unit.WORD;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.parser.impl.CATAnalysisParserSDLTMS2009;

@Test
public class CATAnalysisParserSDLTMS2009Test extends CATAnalysisParserTest<CATAnalysisParserSDLTMS2009> {

	public CATAnalysisParserSDLTMS2009Test() {
		super(CATAnalysisParserSDLTMS2009.class);
	}
	
	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis sdltmsResult = new CATAnalysis(CATTool.SDL_TRADOS, null);

		sdltmsResult
			.set(WORD, TOTAL,         44953)
			.set(WORD, REPETITIONS,     233)
			.set(WORD, PERCENT_100,   38317)
			.set(WORD, PERCENT_95_99,   943)
			.set(WORD, PERCENT_85_94,  1029)
			.set(WORD, NEW,            4431);


		return new Object[][] {
				new Object[] {"/sdl/sdltms2009.htm", sdltmsResult},
		};
	}

}
