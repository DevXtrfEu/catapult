package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import org.testng.annotations.DataProvider;

import java.math.BigDecimal;

import static eu.xtrf.cat.Unit.CHARACTER;
import static eu.xtrf.cat.Unit.SEGMENT;
import static eu.xtrf.cat.Unit.WORD;

public class CATAnalysisParserDejaVuTXTTest extends CATAnalysisParserTest<CATAnalysisParserDejaVuTXT>{

	public CATAnalysisParserDejaVuTXTTest() {
		super(CATAnalysisParserDejaVuTXT.class);
	}

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result = new CATAnalysis(CATTool.DEJA_VU, null);
		result.focusOn(MatchType.DUPLICATES)	.set(SEGMENT, 1)	.set(WORD, 1)	.set(CHARACTER, 11);
		result.focusOn(MatchType.GUARANTEED)	.set(SEGMENT, 0)	.set(WORD, 0)	.set(CHARACTER, 0);
		result.focusOn(MatchType.EXACT)			.set(SEGMENT, 84)	.set(WORD, 322)	.set(CHARACTER, 2681);
		result.focusOn(MatchType.PERCENT_95_99)	.set(SEGMENT, 19)	.set(WORD, 318)	.set(CHARACTER, 2506);
		result.focusOn(MatchType.PERCENT_85_94)	.set(SEGMENT, 9)	.set(WORD, 127)	.set(CHARACTER, 1041);
		result.focusOn(MatchType.PERCENT_75_84)	.set(SEGMENT, 9)	.set(WORD, 135)	.set(CHARACTER, 1037);
		result.focusOn(MatchType.PERCENT_50_74)	.set(SEGMENT, 20)	.set(WORD, 347)	.set(CHARACTER, 2692);
		result.focusOn(MatchType.NO_MATCH)		.set(SEGMENT, 147)	.set(WORD, 2694).set(CHARACTER, 20680);
		result.focusOn(MatchType.TOTAL)			.set(SEGMENT, 289)	.set(WORD, 3944).set(CHARACTER, 30648);

		result.setCharsPerWord(new BigDecimal("7.77"));

		return new Object[][]{
			new Object[]{"/dejavu/Test project_all files_TM analysis.txt", result},
			new Object[]{"/dejavu/Test project_each file_TM analysis.txt", result}
		};
	}
}