package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.Unit.WORD;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;

@Test
public class CATAnalysisParserTransitNXTWorkstationJAMESTest extends CATAnalysisParserTest<CATAnalysisParserTransitNXTWorkstationJAMES> {

	public CATAnalysisParserTransitNXTWorkstationJAMESTest() {
		super(CATAnalysisParserTransitNXTWorkstationJAMES.class);
	}	

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result1 = new CATAnalysis(CATTool.TRANSIT_NXT, null);
		
		result1.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 89);
		result1.focusOn(MatchType.CHECK_PRETRANSLATION)		.set(WORD, 16);
		result1.focusOn(MatchType.PERCENT_100)				.set(WORD, 90);
		result1.focusOn(MatchType.PERCENT_95_99)			.set(WORD, 18);
		result1.focusOn(MatchType.PERCENT_85_94)			.set(WORD, 13);
		result1.focusOn(MatchType.PERCENT_75_84)			.set(WORD, 31);
		result1.focusOn(MatchType.PERCENT_50_74)			.set(WORD, 27);
		result1.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 1049);
		result1.focusOn(MatchType.TOTAL)					.set(WORD, 1333);
				
		return new Object[][] {
			new Object[] {"/transit/an Transit_transit.james", result1}
		};
	}
}
