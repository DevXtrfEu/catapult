package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.Unit.CHARACTER;
import static eu.xtrf.cat.Unit.SEGMENT;
import static eu.xtrf.cat.Unit.WORD;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.parser.impl.CATAnalysisParserDejaVuCSV;

@Test
public class CATAnalysisParserDejaVuCSVTest extends CATAnalysisParserTest<CATAnalysisParserDejaVuCSV> {

	public CATAnalysisParserDejaVuCSVTest() {
		super(CATAnalysisParserDejaVuCSV.class);
	}

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result3 = new CATAnalysis(CATTool.DEJA_VU, null);
		result3.focusOn(MatchType.DUPLICATES)		.set(SEGMENT, 1)	.set(WORD, 1)		.set(CHARACTER, 6);
		result3.focusOn(MatchType.GUARANTEED)		.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0);
		result3.focusOn(MatchType.EXACT)			.set(SEGMENT, 132)	.set(WORD, 580)		.set(CHARACTER, 4337);
		result3.focusOn(MatchType.PERCENT_95_99)	.set(SEGMENT, 5)	.set(WORD, 18)		.set(CHARACTER, 121);
		result3.focusOn(MatchType.PERCENT_85_94)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0);
		result3.focusOn(MatchType.PERCENT_75_84)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0);
		result3.focusOn(MatchType.PERCENT_50_74)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0);
		result3.focusOn(MatchType.NO_MATCH)		.set(SEGMENT, 42)	.set(WORD, 83)		.set(CHARACTER, 541);
		result3.focusOn(MatchType.TOTAL)			.set(SEGMENT, 180)	.set(WORD, 682)		.set(CHARACTER, 5005);
		
		CATAnalysis result4 = new CATAnalysis(CATTool.DEJA_VU, null);
		result4.focusOn(MatchType.DUPLICATES)		.set(SEGMENT, 23)	.set(WORD, 161)		.set(CHARACTER, 199);
		result4.focusOn(MatchType.GUARANTEED)		.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0);
		result4.focusOn(MatchType.EXACT)			.set(SEGMENT, 5)	.set(WORD, 12)		.set(CHARACTER, 12);
		result4.focusOn(MatchType.PERCENT_95_99)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0);
		result4.focusOn(MatchType.PERCENT_85_94)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0);
		result4.focusOn(MatchType.PERCENT_75_84)	.set(SEGMENT, 2)	.set(WORD, 9)		.set(CHARACTER, 11);
		result4.focusOn(MatchType.PERCENT_50_74)	.set(SEGMENT, 10)	.set(WORD, 50)		.set(CHARACTER, 53);
		result4.focusOn(MatchType.NO_MATCH)		.set(SEGMENT, 264)	.set(WORD, 3990)	.set(CHARACTER, 5609);
		result4.focusOn(MatchType.LOCKED)			.set(SEGMENT, 7371)	.set(WORD, 41605)	.set(CHARACTER, 292698);
		result4.focusOn(MatchType.TOTAL)			.set(SEGMENT, 7675)	.set(WORD, 45827)	.set(CHARACTER, 298582);
				
		return new Object[][] {
				new Object[] {"/dejavu/dejavu3.csv", result3},
				new Object[] {"/dejavu/dejavu4.csv", result4}
		};
	}
}
