package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.MatchType.ICE_MATCH;
import static eu.xtrf.cat.MatchType.INTERNAL_75_84;
import static eu.xtrf.cat.MatchType.INTERNAL_85_94;
import static eu.xtrf.cat.MatchType.INTERNAL_95_99;
import static eu.xtrf.cat.MatchType.LEVERAGED_MATCH;
import static eu.xtrf.cat.MatchType.NON_TRANSLATABLE;
import static eu.xtrf.cat.MatchType.NO_MATCH;
import static eu.xtrf.cat.MatchType.PERCENT_75_84;
import static eu.xtrf.cat.MatchType.PERCENT_85_94;
import static eu.xtrf.cat.MatchType.PERCENT_95_99;
import static eu.xtrf.cat.MatchType.REPETITIONS;
import static eu.xtrf.cat.MatchType.TOTAL;
import static eu.xtrf.cat.Unit.CHARACTER;
import static eu.xtrf.cat.Unit.SEGMENT;
import static eu.xtrf.cat.Unit.WORD;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.parser.impl.CATAnalysisParserXTMXLS;

@Test
public class CATAnalysisParserXTMXLSTest extends CATAnalysisParserTest<CATAnalysisParserXTMXLS> {
	
	public CATAnalysisParserXTMXLSTest() {
		super(CATAnalysisParserXTMXLS.class);
	}

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result1 = new CATAnalysis(CATTool.XTM, null);

		result1
			.focusOn(TOTAL)				.set(SEGMENT, 121)		.set(WORD, 769)			.set(CHARACTER, 5385)
			.focusOn(NON_TRANSLATABLE)	.set(SEGMENT, 53)		.set(WORD, 53)			.set(CHARACTER, 173)
			.focusOn(ICE_MATCH)			.set(SEGMENT, 17)		.set(WORD, 62)			.set(CHARACTER, 396)
			.focusOn(LEVERAGED_MATCH)	.set(SEGMENT, 34)		.set(WORD, 236)			.set(CHARACTER, 1779)
			.focusOn(PERCENT_95_99)		.set(SEGMENT, 2)		.set(WORD, 80)			.set(CHARACTER, 631)
			.focusOn(PERCENT_85_94)		.set(SEGMENT, 5)		.set(WORD, 195)			.set(CHARACTER, 1416)
			.focusOn(PERCENT_75_84)		.set(SEGMENT, 9)		.set(WORD, 139)			.set(CHARACTER, 968)
			.focusOn(REPETITIONS)		.set(SEGMENT, 0)		.set(WORD, 0)			.set(CHARACTER, 0)
			.focusOn(INTERNAL_95_99)	.set(SEGMENT, 0)		.set(WORD, 0)			.set(CHARACTER, 0)
			.focusOn(INTERNAL_85_94)	.set(SEGMENT, 0)		.set(WORD, 0)			.set(CHARACTER, 0)
			.focusOn(INTERNAL_75_84)	.set(SEGMENT, 0)		.set(WORD, 0)			.set(CHARACTER, 0)
			.focusOn(NO_MATCH)			.set(SEGMENT, 1)		.set(WORD, 4)			.set(CHARACTER, 22);
		
		return new Object[][] {
				new Object[] {"/xtm/FlatXlsMetrics.xls", result1}
		};
	}
}
