package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.Unit.WORD;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.parser.impl.CATAnalysisParserTransit;

@Test
public class CATAnalysisParserTransitTest extends CATAnalysisParserTest<CATAnalysisParserTransit> {

	public CATAnalysisParserTransitTest() {
		super(CATAnalysisParserTransit.class);
	}	

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result1 = new CATAnalysis(CATTool.TRANSIT, null);
		
		result1.focusOn(MatchType.REPETITIONS)				.set(WORD, 159);
		result1.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 3551);
		result1.focusOn(MatchType.PARTIALLY_TRANSLATED)		.set(WORD, 103);
		result1.focusOn(MatchType.PERCENT_95_100)			.set(WORD, 158);
		result1.focusOn(MatchType.PERCENT_85_94)			.set(WORD, 563);
		result1.focusOn(MatchType.PERCENT_75_84)			.set(WORD, 140);
		result1.focusOn(MatchType.PERCENT_50_74)			.set(WORD, 107);
		result1.focusOn(MatchType.REMAINING_NOT_TRANSLATED)	.set(WORD, 28);
		result1.focusOn(MatchType.TOTAL)					.set(WORD, 4809);
		
		CATAnalysis result2 = new CATAnalysis(CATTool.TRANSIT, null);
		
		result2.focusOn(MatchType.REPETITIONS)				.set(WORD, 0);
		result2.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 185);
		result2.focusOn(MatchType.PARTIALLY_TRANSLATED)		.set(WORD, 25);
		result2.focusOn(MatchType.PERCENT_95_100)			.set(WORD, 5);
		result2.focusOn(MatchType.PERCENT_85_94)			.set(WORD, 10);
		result2.focusOn(MatchType.PERCENT_75_84)			.set(WORD, 3);
		result2.focusOn(MatchType.PERCENT_50_74)			.set(WORD, 2);
		result2.focusOn(MatchType.REMAINING_NOT_TRANSLATED)	.set(WORD, 37);
		result2.focusOn(MatchType.TOTAL)					.set(WORD, 267);
		
		CATAnalysis result3 = new CATAnalysis(CATTool.TRANSIT, null);
		
		result3.focusOn(MatchType.REPETITIONS)				.set(WORD, 38);
		result3.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 3485);
		result3.focusOn(MatchType.PARTIALLY_TRANSLATED)		.set(WORD, 35);
		result3.focusOn(MatchType.PERCENT_95_100)			.set(WORD, 89);
		result3.focusOn(MatchType.PERCENT_85_94)			.set(WORD, 49);
		result3.focusOn(MatchType.PERCENT_75_84)			.set(WORD, 81);
		result3.focusOn(MatchType.PERCENT_50_74)			.set(WORD, 152);
		result3.focusOn(MatchType.REMAINING_NOT_TRANSLATED)	.set(WORD, 3105);
		result3.focusOn(MatchType.TOTAL)					.set(WORD, 7034);
		
		return new Object[][] {
			new Object[] {"/transit/transit1.rtf", result1},
			new Object[] {"/transit/transit2.rtf", result2},
			new Object[] {"/transit/transit3.rtf", result3}
		};
	}
}
