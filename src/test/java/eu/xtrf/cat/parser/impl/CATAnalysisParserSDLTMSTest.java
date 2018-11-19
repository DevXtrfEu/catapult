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
import eu.xtrf.cat.parser.impl.CATAnalysisParserSDLTMS;

/**
 * Test for {@link CATAnalysisParserSDLTMS}.
 * 
 * @author Lukasz Wiktor
 */
@Test
public class CATAnalysisParserSDLTMSTest extends CATAnalysisParserTest<CATAnalysisParserSDLTMS>{

	public CATAnalysisParserSDLTMSTest() {
		super(CATAnalysisParserSDLTMS.class);
	}

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis sdltmsResult = new CATAnalysis(CATTool.SDL_TRADOS, null);

		sdltmsResult
			.set(WORD, TOTAL,         71298)
			.set(WORD, REPETITIONS,    2321)
			.set(WORD, PERCENT_100,   47480)
			.set(WORD, PERCENT_95_99,  2794)
			.set(WORD, PERCENT_85_94,  4536)
			.set(WORD, NEW,           14167);


		return new Object[][] {
				new Object[] {"/sdl/sdltms.html", sdltmsResult},
		};
	}
}
