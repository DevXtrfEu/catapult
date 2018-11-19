package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.MatchType.ICE_MATCH;
import static eu.xtrf.cat.MatchType.INTERNAL_75_84;
import static eu.xtrf.cat.MatchType.INTERNAL_85_94;
import static eu.xtrf.cat.MatchType.INTERNAL_95_99;
import static eu.xtrf.cat.MatchType.LEVERAGED_MATCH;
import static eu.xtrf.cat.MatchType.NO_MATCH;
import static eu.xtrf.cat.MatchType.PERCENT_75_84;
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
public class CATAnalysisParserXMLTest extends CATAnalysisParserTest<CATAnalysisParserXML> {

	public CATAnalysisParserXMLTest() {
		super(CATAnalysisParserXML.class);
	}
	
	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result1 = new CATAnalysis(CATTool.XTM, null);

		result1
			.focusOn(TOTAL)				.set(WORD, 196)
			.focusOn(REPETITIONS)		.set(WORD, 8)
			.focusOn(LEVERAGED_MATCH)	.set(WORD, 94)
			.focusOn(PERCENT_95_99)		.set(WORD, 0)
			.focusOn(PERCENT_85_94)		.set(WORD, 0)
			.focusOn(PERCENT_75_84)		.set(WORD, 9)
			.focusOn(INTERNAL_95_99)	.set(WORD, 69)
			.focusOn(INTERNAL_85_94)	.set(WORD, 7)
			.focusOn(NO_MATCH)			.set(WORD, 9);

		CATAnalysis result2 = new CATAnalysis(CATTool.XTM, null);

		result2
			.focusOn(TOTAL)				.set(WORD, 501)
			.focusOn(ICE_MATCH)			.set(WORD, 0)
			.focusOn(REPETITIONS)		.set(WORD, 20)
			.focusOn(LEVERAGED_MATCH)	.set(WORD, 0)
			.focusOn(PERCENT_95_99)		.set(WORD, 0)
			.focusOn(PERCENT_85_94)		.set(WORD, 0)
			.focusOn(PERCENT_75_84)		.set(WORD, 0)
			.focusOn(INTERNAL_95_99)	.set(WORD, 7)
			.focusOn(INTERNAL_85_94)	.set(WORD, 13)
			.focusOn(INTERNAL_75_84)	.set(WORD, 75)
			.focusOn(NO_MATCH)			.set(WORD, 386);
		
		return new Object[][] {
				new Object[] {"/xtm/xml.xlf", result1},
				new Object[] {"/xtm/xtm.xml", result2}
		};
	}

}
