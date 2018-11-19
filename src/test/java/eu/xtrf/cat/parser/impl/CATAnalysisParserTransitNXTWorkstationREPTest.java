package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.Unit.WORD;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.parser.impl.CATAnalysisParserTransitNXTWorkstationREP;

@Test
public class CATAnalysisParserTransitNXTWorkstationREPTest extends CATAnalysisParserTest<CATAnalysisParserTransitNXTWorkstationREP> {

	public CATAnalysisParserTransitNXTWorkstationREPTest() {
		super(CATAnalysisParserTransitNXTWorkstationREP.class);
	}	

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result1 = new CATAnalysis(CATTool.TRANSIT_NXT, null);
		
		result1.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 71);
		result1.focusOn(MatchType.CHECK_PRETRANSLATION)		.set(WORD, 7);
		result1.focusOn(MatchType.PERCENT_100)				.set(WORD, 9);
		result1.focusOn(MatchType.PERCENT_95_99)			.set(WORD, 0);
		result1.focusOn(MatchType.PERCENT_85_94)			.set(WORD, 31);
		result1.focusOn(MatchType.PERCENT_75_84)			.set(WORD, 47);
		result1.focusOn(MatchType.PERCENT_50_74)			.set(WORD, 4);
		result1.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 83);
		result1.focusOn(MatchType.TOTAL)					.set(WORD, 252);
		
		CATAnalysis result2 = new CATAnalysis(CATTool.TRANSIT_NXT, null);
		
		result2.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 71);
		result2.focusOn(MatchType.CHECK_PRETRANSLATION)		.set(WORD, 7);
		result2.focusOn(MatchType.PERCENT_100)				.set(WORD, 9);
		result2.focusOn(MatchType.PERCENT_95_99)			.set(WORD, 0);
		result2.focusOn(MatchType.PERCENT_85_94)			.set(WORD, 31);
		result2.focusOn(MatchType.PERCENT_75_84)			.set(WORD, 47);
		result2.focusOn(MatchType.PERCENT_50_74)			.set(WORD, 4);
		result2.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 83);
		result2.focusOn(MatchType.TOTAL)					.set(WORD, 252);
		
		CATAnalysis result3 = new CATAnalysis(CATTool.TRANSIT_NXT, null);
		
		result3.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 35952);
		result3.focusOn(MatchType.CHECK_PRETRANSLATION)		.set(WORD, 183);
		result3.focusOn(MatchType.PERCENT_100)				.set(WORD, 447);
		result3.focusOn(MatchType.PERCENT_95_99)			.set(WORD, 389);
		result3.focusOn(MatchType.PERCENT_85_94)			.set(WORD, 502);
		result3.focusOn(MatchType.PERCENT_75_84)			.set(WORD, 335);
		result3.focusOn(MatchType.PERCENT_50_74)			.set(WORD, 176);
		result3.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 1098);
		result3.focusOn(MatchType.TOTAL)					.set(WORD, 39082);
		
		CATAnalysis result4 = new CATAnalysis(CATTool.TRANSIT_NXT, null);
		
		result4.focusOn(MatchType.REPETITIONS)				.set(WORD, 447);
		result4.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 35952);
		result4.focusOn(MatchType.CHECK_PRETRANSLATION)		.set(WORD, 183);
		result4.focusOn(MatchType.PERCENT_100)				.set(WORD, 0);
		result4.focusOn(MatchType.PERCENT_95_99)			.set(WORD, 389);
		result4.focusOn(MatchType.PERCENT_85_94)			.set(WORD, 502);
		result4.focusOn(MatchType.PERCENT_75_84)			.set(WORD, 335);
		result4.focusOn(MatchType.PERCENT_50_74)			.set(WORD, 176);
		result4.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 1098);
		result4.focusOn(MatchType.TOTAL)					.set(WORD, 39082);
		
		return new Object[][] {
			new Object[] {"/transit/01.rep", result1},
			new Object[] {"/transit/02.rep", result2},
			new Object[] {"/transit/03.rep", result3},
			new Object[] {"/transit/04.rep", result4}
		};
	}
}
