package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.MatchType.NO_MATCH;
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

@Test
public class CATAnalysisParserSDLTMS2007HTMTest extends CATAnalysisParserTest<CATAnalysisParserSDLTMS2007HTM> {

	public CATAnalysisParserSDLTMS2007HTMTest() {
		super(CATAnalysisParserSDLTMS2007HTM.class);
	}
	
	@DataProvider(name="files")
	public Object[][] getFiles() {		
		CATAnalysis sdltmsResult1 = new CATAnalysis(CATTool.TRADOS, null);

		sdltmsResult1
			.set(WORD, TOTAL,         	35261)
			.set(WORD, REPETITIONS,		206)
			.set(WORD, PERCENT_100,   	33794)
			.set(WORD, PERCENT_95_99,   126)
			.set(WORD, PERCENT_85_94,  	232)
			.set(WORD, NO_MATCH,        903);

		CATAnalysis sdltmsResult2 = new CATAnalysis(CATTool.TRADOS, null);

		sdltmsResult2
			.set(WORD, TOTAL,         	877834)
			.set(WORD, REPETITIONS,		8057)
			.set(WORD, PERCENT_100,   	849803)
			.set(WORD, PERCENT_95_99,   6192)
			.set(WORD, PERCENT_85_94,  	4514)
			.set(WORD, NO_MATCH,        9268);
		
		return new Object[][] {
				new Object[] {"/sdl/OM13X-0M20E0E CS wcounts.htm", sdltmsResult1},
				new Object[] {"/sdl/SMA E11 371 SM13X00-1E11E1E CS wcounts.htm", sdltmsResult2}
		};
	}
}
