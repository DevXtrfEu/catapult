package eu.xtrf.cat.parser.impl;


import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static eu.xtrf.cat.Unit.WORD;

@Test
public class CATAnalysisParserTransitNXTWorkstationProgressReportHTMLTest extends CATAnalysisParserTest<CATAnalysisParserTransitNXTWorkstationProgressReportHTML> {

	public CATAnalysisParserTransitNXTWorkstationProgressReportHTMLTest() {
		super(CATAnalysisParserTransitNXTWorkstationProgressReportHTML.class);
	}	

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result1 = new CATAnalysis(CATTool.TRANSIT_NXT, null);
		
		result1.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 549);
		result1.focusOn(MatchType.CHECK_PRETRANSLATION)		.set(WORD, 364);
		result1.focusOn(MatchType.TRANSLATED)		    	.set(WORD, 35901);
		result1.focusOn(MatchType.TOTAL)					.set(WORD, 36814);

		return new Object[][] {
			new Object[] {"/transit/progress-report.html", result1},
		};
	}
	
}
