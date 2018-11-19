package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.Unit.SEGMENT;
import static eu.xtrf.cat.Unit.WORD;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.parser.impl.CATAnalysisParserWordfastCSV;

@Test
public class CATAnalysisParserWordfastCSVTest extends CATAnalysisParserTest<CATAnalysisParserWordfastCSV> {

	public CATAnalysisParserWordfastCSVTest() {
		super(CATAnalysisParserWordfastCSV.class);
	}	

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result2 = new CATAnalysis(CATTool.WORDFAST, null);
		
		result2.focusOn(MatchType.REPETITIONS)		.set(SEGMENT, 0)	.set(WORD, 0);
		result2.focusOn(MatchType.PERCENT_100)		.set(SEGMENT, 64)	.set(WORD, 919);
		result2.focusOn(MatchType.PERCENT_95_99)	.set(SEGMENT, 0)	.set(WORD, 0);
		result2.focusOn(MatchType.PERCENT_85_94)	.set(SEGMENT, 0)	.set(WORD, 0);
		result2.focusOn(MatchType.PERCENT_75_84)	.set(SEGMENT, 0)	.set(WORD, 0);
		result2.focusOn(MatchType.PERCENT_50_74)	.set(SEGMENT, 0)	.set(WORD, 0);
		result2.focusOn(MatchType.NO_MATCH)			.set(SEGMENT, 0)	.set(WORD, 0);
		result2.focusOn(MatchType.TOTAL)			.set(SEGMENT, 64)	.set(WORD, 919);

		CATAnalysis result5 = new CATAnalysis(CATTool.WORDFAST, null);
		
		result5.focusOn(MatchType.REPETITIONS)		.set(SEGMENT, 22)	.set(WORD, 90);
		result5.focusOn(MatchType.PERCENT_100)		.set(SEGMENT, 25)	.set(WORD, 89);
		result5.focusOn(MatchType.PERCENT_95_99)	.set(SEGMENT, 9)	.set(WORD, 18);
		result5.focusOn(MatchType.PERCENT_85_94)	.set(SEGMENT, 2)	.set(WORD, 17);
		result5.focusOn(MatchType.PERCENT_75_84)	.set(SEGMENT, 2)	.set(WORD, 3);
		result5.focusOn(MatchType.PERCENT_50_74)	.set(SEGMENT, 20)	.set(WORD, 124);
		result5.focusOn(MatchType.NO_MATCH)			.set(SEGMENT, 518)	.set(WORD, 7724);
		result5.focusOn(MatchType.TOTAL)			.set(SEGMENT, 598)	.set(WORD, 8065);
		
		return new Object[][] {
			new Object[] {"/wordfast/Example2_CSV.csv", result2},
			new Object[] {"/wordfast/Example5_CSV.csv", result5}
		};
	}
}
