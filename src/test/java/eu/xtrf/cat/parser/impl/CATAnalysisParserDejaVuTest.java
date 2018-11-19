package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.Unit.SEGMENT;
import static eu.xtrf.cat.Unit.WORD;
import static eu.xtrf.cat.Unit.CHARACTER;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.parser.impl.CATAnalysisParserDejaVu;

@Test
public class CATAnalysisParserDejaVuTest extends CATAnalysisParserTest<CATAnalysisParserDejaVu> {

	public CATAnalysisParserDejaVuTest() {
		super(CATAnalysisParserDejaVu.class);
	}	

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result = new CATAnalysis(CATTool.DEJA_VU_DETAILED, null);
		
		result.focusOn(MatchType.GUARANTEED)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0);
		result.focusOn(MatchType.EXACT)			.set(SEGMENT, 479)	.set(WORD, 5253)	.set(CHARACTER, 31820);
		result.focusOn(MatchType.DUPLICATES)	.set(SEGMENT, 1876)	.set(WORD, 5940)	.set(CHARACTER, 35564);
		result.focusOn(MatchType.PERCENT_99)	.set(SEGMENT, 166)	.set(WORD, 116)		.set(CHARACTER, 1065);
		result.focusOn(MatchType.PERCENT_98)	.set(SEGMENT, 38)	.set(WORD, 190)		.set(CHARACTER, 1140);
		result.focusOn(MatchType.PERCENT_97)	.set(SEGMENT, 39)	.set(WORD, 154)		.set(CHARACTER, 1234);
		result.focusOn(MatchType.PERCENT_96)	.set(SEGMENT, 4)	.set(WORD, 100)		.set(CHARACTER, 499);
		result.focusOn(MatchType.PERCENT_95)	.set(SEGMENT, 4)	.set(WORD, 44)		.set(CHARACTER, 235);
		result.focusOn(MatchType.PERCENT_90_94)	.set(SEGMENT, 50)	.set(WORD, 240)		.set(CHARACTER, 1450);
		result.focusOn(MatchType.PERCENT_80_89)	.set(SEGMENT, 30)	.set(WORD, 88)		.set(CHARACTER, 572);
		result.focusOn(MatchType.PERCENT_70_79)	.set(SEGMENT, 6)	.set(WORD, 64)		.set(CHARACTER, 344);
		result.focusOn(MatchType.PERCENT_60_69)	.set(SEGMENT, 9)	.set(WORD, 60)		.set(CHARACTER, 389);
		result.focusOn(MatchType.PERCENT_50_59)	.set(SEGMENT, 10)	.set(WORD, 104)		.set(CHARACTER, 571);
		result.focusOn(MatchType.PERCENT_40_49)	.set(SEGMENT, 15)	.set(WORD, 164)		.set(CHARACTER, 939);
		result.focusOn(MatchType.PERCENT_30_39)	.set(SEGMENT, 44)	.set(WORD, 581)		.set(CHARACTER, 3314);
		result.focusOn(MatchType.PERCENT_20_29)	.set(SEGMENT, 174)	.set(WORD, 2855)	.set(CHARACTER, 15498);
		result.focusOn(MatchType.PERCENT_10_19)	.set(SEGMENT, 763)	.set(WORD, 16353)	.set(CHARACTER, 88859);
		result.focusOn(MatchType.PERCENT_0_9)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0);
		result.focusOn(MatchType.NO_MATCH)		.set(SEGMENT, 3152)	.set(WORD, 29739)	.set(CHARACTER, 163147);
		result.focusOn(MatchType.TOTAL)			.set(SEGMENT, 6859)	.set(WORD, 62045)	.set(CHARACTER, 346640);
		
		CATAnalysis result_es = new CATAnalysis(CATTool.DEJA_VU_DETAILED, null);
		
		result_es.focusOn(MatchType.GUARANTEED)		.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0);
		result_es.focusOn(MatchType.EXACT)			.set(SEGMENT, 34)	.set(WORD, 152)		.set(CHARACTER, 1003);
		result_es.focusOn(MatchType.DUPLICATES)		.set(SEGMENT, 4)	.set(WORD, 16)		.set(CHARACTER, 104);
		result_es.focusOn(MatchType.PERCENT_99)		.set(SEGMENT, 25)	.set(WORD, 85)		.set(CHARACTER, 578);
		result_es.focusOn(MatchType.PERCENT_98)		.set(SEGMENT, 20)	.set(WORD, 47)		.set(CHARACTER, 388);
		result_es.focusOn(MatchType.PERCENT_97)		.set(SEGMENT, 2)	.set(WORD, 24)		.set(CHARACTER, 159);
		result_es.focusOn(MatchType.PERCENT_96)		.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0);
		result_es.focusOn(MatchType.PERCENT_95)		.set(SEGMENT, 3)	.set(WORD, 23)		.set(CHARACTER, 148);
		result_es.focusOn(MatchType.PERCENT_90_94)	.set(SEGMENT, 8)	.set(WORD, 62)		.set(CHARACTER, 387);
		result_es.focusOn(MatchType.PERCENT_80_89)	.set(SEGMENT, 11)	.set(WORD, 47)		.set(CHARACTER, 360);
		result_es.focusOn(MatchType.PERCENT_70_79)	.set(SEGMENT, 6)	.set(WORD, 28)		.set(CHARACTER, 186);
		result_es.focusOn(MatchType.PERCENT_60_69)	.set(SEGMENT, 10)	.set(WORD, 55)		.set(CHARACTER, 381);
		result_es.focusOn(MatchType.PERCENT_50_59)	.set(SEGMENT, 3)	.set(WORD, 12)		.set(CHARACTER, 92);
		result_es.focusOn(MatchType.PERCENT_40_49)	.set(SEGMENT, 2)	.set(WORD, 17)		.set(CHARACTER, 110);
		result_es.focusOn(MatchType.PERCENT_30_39)	.set(SEGMENT, 4)	.set(WORD, 28)		.set(CHARACTER, 201);
		result_es.focusOn(MatchType.PERCENT_20_29)	.set(SEGMENT, 1)	.set(WORD, 6)		.set(CHARACTER, 31);
		result_es.focusOn(MatchType.PERCENT_10_19)	.set(SEGMENT, 1)	.set(WORD, 5)		.set(CHARACTER, 38);
		result_es.focusOn(MatchType.PERCENT_0_9)	.set(SEGMENT, 0)	.set(WORD, 0)		.set(CHARACTER, 0);
		result_es.focusOn(MatchType.NO_MATCH)		.set(SEGMENT, 25)	.set(WORD, 44)		.set(CHARACTER, 341);
		result_es.focusOn(MatchType.TOTAL)			.set(SEGMENT, 159)	.set(WORD, 651)		.set(CHARACTER, 4507);
		
		return new Object[][] {
				new Object[] {"/dejavu/dejavu.log", result},
				new Object[] {"/dejavu/dejavues.log", result_es}
		};
	}
}
