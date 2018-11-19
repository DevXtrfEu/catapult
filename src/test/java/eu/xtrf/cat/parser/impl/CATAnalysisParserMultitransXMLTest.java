package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.Unit.SEGMENT;
import static eu.xtrf.cat.Unit.WORD;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;

@Test
public class CATAnalysisParserMultitransXMLTest extends CATAnalysisParserTest<CATAnalysisParserMultitransXML> {

	public CATAnalysisParserMultitransXMLTest() {
		super(CATAnalysisParserMultitransXML.class);
	}	

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result1 = new CATAnalysis(CATTool.MULTITRANS, null);
		
		result1
			.focusOn(MatchType.PERCENT_100)					.set(WORD, 520 + 5)		.set(SEGMENT, 43 + 3)
			.focusOn(MatchType.PERCENT_75_84)				.set(WORD, 8)			.set(SEGMENT, 2)
			.focusOn(MatchType.NEW)							.set(WORD, 92)			.set(SEGMENT, 6)
			.focusOn(MatchType.TOTAL)						.set(WORD, 625)			.set(SEGMENT, 54);
				
		return new Object[][] {
			new Object[] {"/multitrans/Statistics.xml", result1}
		};
	}
}
