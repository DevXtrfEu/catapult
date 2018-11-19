package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.Unit.CHARACTER;
import static eu.xtrf.cat.Unit.SEGMENT;
import static eu.xtrf.cat.Unit.WORD;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.parser.impl.CATAnalysisParserDejaVuHTM;

@Test
public class CATAnalysisParserDejaVuHTMTest extends CATAnalysisParserTest<CATAnalysisParserDejaVuHTM> {

	public CATAnalysisParserDejaVuHTMTest() {
		super(CATAnalysisParserDejaVuHTM.class);
	}
	
	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result = new CATAnalysis(CATTool.DEJA_VU, null);
		result.focusOn(MatchType.DUPLICATES)		.set(SEGMENT, 1)	.set(WORD, 1)		.set(CHARACTER, 6);
		result.focusOn(MatchType.GUARANTEED)		.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0);
		result.focusOn(MatchType.EXACT)			.set(SEGMENT, 132)	.set(WORD, 580)		.set(CHARACTER, 4337);
		result.focusOn(MatchType.PERCENT_95_99)	.set(SEGMENT, 5)	.set(WORD, 18)		.set(CHARACTER, 121);
		result.focusOn(MatchType.PERCENT_85_94)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0);
		result.focusOn(MatchType.PERCENT_75_84)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0);
		result.focusOn(MatchType.PERCENT_50_74)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0);
		result.focusOn(MatchType.NO_MATCH)		.set(SEGMENT, 42)	.set(WORD, 83)		.set(CHARACTER, 541);
		result.focusOn(MatchType.TOTAL)			.set(SEGMENT, 180)	.set(WORD, 682)		.set(CHARACTER, 5005);

		CATAnalysis result2 = new CATAnalysis(CATTool.DEJA_VU, null);
		result2
			.focusOn(MatchType.DUPLICATES)		.set(SEGMENT, 504)	.set(WORD, 933)		.set(CHARACTER, 5839)
			.focusOn(MatchType.GUARANTEED)		.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)
			.focusOn(MatchType.EXACT)			.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)
			.focusOn(MatchType.PERCENT_95_99)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)
			.focusOn(MatchType.PERCENT_85_94)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)
			.focusOn(MatchType.PERCENT_75_84)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)
			.focusOn(MatchType.PERCENT_50_74)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0)
			.focusOn(MatchType.NO_MATCH)		.set(SEGMENT, 3031)	.set(WORD, 28972)	.set(CHARACTER, 175127)
			.focusOn(MatchType.LOCKED)			.set(SEGMENT, 448)	.set(WORD, 1527)	.set(CHARACTER, 12001)
			.focusOn(MatchType.TOTAL)			.set(SEGMENT, 3983)	.set(WORD, 31432)	.set(CHARACTER, 192967);
						
		return new Object[][] {
				new Object[] {"/dejavu/dejavu2.html", result},
				new Object[] {"/dejavu/dejavu-withLocked.html", result2}
		};
	}
}
