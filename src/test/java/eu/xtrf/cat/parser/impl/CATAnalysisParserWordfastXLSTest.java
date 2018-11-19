package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.Unit.SEGMENT;
import static eu.xtrf.cat.Unit.WORD;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.parser.impl.CATAnalysisParserWordfastXLS;

@Test
public class CATAnalysisParserWordfastXLSTest extends CATAnalysisParserTest<CATAnalysisParserWordfastXLS> {

	public CATAnalysisParserWordfastXLSTest() {
		super(CATAnalysisParserWordfastXLS.class);
	}
	
	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result2 = new CATAnalysis(CATTool.WORDFAST, null);
		
		result2.focusOn(MatchType.REPETITIONS)		.set(SEGMENT, 0)	.set(WORD, 0);
		result2.focusOn(MatchType.PERCENT_100)		.set(SEGMENT, 0)	.set(WORD, 0);
		result2.focusOn(MatchType.PERCENT_95_99)	.set(SEGMENT, 0)	.set(WORD, 0);
		result2.focusOn(MatchType.PERCENT_85_94)	.set(SEGMENT, 0)	.set(WORD, 0);
		result2.focusOn(MatchType.PERCENT_75_84)	.set(SEGMENT, 0)	.set(WORD, 0);
		result2.focusOn(MatchType.PERCENT_50_74)	.set(SEGMENT, 0)	.set(WORD, 0);
		result2.focusOn(MatchType.NO_MATCH)			.set(SEGMENT, 16)	.set(WORD, 269);
		result2.focusOn(MatchType.TOTAL)			.set(SEGMENT, 16)	.set(WORD, 269);

		CATAnalysis result4 = new CATAnalysis(CATTool.WORDFAST, null);
		
		result4.focusOn(MatchType.REPETITIONS)		.set(SEGMENT, 7)	.set(WORD, 24);
		result4.focusOn(MatchType.PERCENT_100)		.set(SEGMENT, 522)	.set(WORD, 6841);
		result4.focusOn(MatchType.PERCENT_95_99)	.set(SEGMENT, 12)	.set(WORD, 71);
		result4.focusOn(MatchType.PERCENT_85_94)	.set(SEGMENT, 9)	.set(WORD, 297);
		result4.focusOn(MatchType.PERCENT_75_84)	.set(SEGMENT, 8)	.set(WORD, 210);
		result4.focusOn(MatchType.PERCENT_50_74)	.set(SEGMENT, 9)	.set(WORD, 101);
		result4.focusOn(MatchType.NO_MATCH)			.set(SEGMENT, 31)	.set(WORD, 521);
		result4.focusOn(MatchType.TOTAL)			.set(SEGMENT, 598)	.set(WORD, 8065);
		
		return new Object[][] {
			new Object[] {"/wordfast/Example1_XLS.xls", result2},
			new Object[] {"/wordfast/Example4_XLS.xls", result4}
		};
	}
}
