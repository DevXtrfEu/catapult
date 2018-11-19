package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.parser.CATAnalysisParserException;
import eu.xtrf.cat.parser.CATAnalysisParserNotSupportedMultilingualAnalysisException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static eu.xtrf.cat.Unit.CHARACTER;
import static eu.xtrf.cat.Unit.WORD;

@Test
public class CATAnalysisParserPassolo2016_HTMTest extends CATAnalysisParserTest<CATAnalysisParserPassolo2016_HTM> {

	public CATAnalysisParserPassolo2016_HTMTest() {
		super(CATAnalysisParserPassolo2016_HTM.class);
	}	

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result1 = new CATAnalysis(CATTool.PASSOLO, null);
		result1.focusOn(MatchType.UNTRANSLATED)					.set(WORD, 545)			.set(CHARACTER, 3934);
		result1.focusOn(MatchType.UNTRANSLATED_REPETITIONS)		.set(WORD, 17)			.set(CHARACTER, 105);
		result1.focusOn(MatchType.AUTO_TRANSLATED)				.set(WORD, 0)			.set(CHARACTER, 0);
		result1.focusOn(MatchType.PERCENT_95_99)				.set(WORD, 0)	    	.set(CHARACTER, 0);
		result1.focusOn(MatchType.PERCENT_85_94)				.set(WORD, 0)	    	.set(CHARACTER, 0);
		result1.focusOn(MatchType.PERCENT_75_84)				.set(WORD, 0)		    .set(CHARACTER, 0);
		result1.focusOn(MatchType.PERCENT_30_74)				.set(WORD, 0)		    .set(CHARACTER, 0);
		result1.focusOn(MatchType.FOR_REVIEW)					.set(WORD, 0)			.set(CHARACTER, 0);
		result1.focusOn(MatchType.TOTAL)						.set(WORD, 7407 - 6845) .set(CHARACTER, 44389 - 40350);

		CATAnalysis result2 = new CATAnalysis(CATTool.PASSOLO, null);
		result2.focusOn(MatchType.UNTRANSLATED)					.set(WORD, 1351)		.set(CHARACTER, 8811);
		result2.focusOn(MatchType.UNTRANSLATED_REPETITIONS)		.set(WORD, 309)			.set(CHARACTER, 1883);
		result2.focusOn(MatchType.AUTO_TRANSLATED)				.set(WORD, 0)			.set(CHARACTER, 0);
		result2.focusOn(MatchType.PERCENT_95_99)				.set(WORD, 0)	    	.set(CHARACTER, 0);
		result2.focusOn(MatchType.PERCENT_85_94)				.set(WORD, 0)	    	.set(CHARACTER, 0);
		result2.focusOn(MatchType.PERCENT_75_84)				.set(WORD, 0)		    .set(CHARACTER, 0);
		result2.focusOn(MatchType.PERCENT_30_74)				.set(WORD, 0)		    .set(CHARACTER, 0);
		result2.focusOn(MatchType.FOR_REVIEW)					.set(WORD, 30)			.set(CHARACTER, 188);
		result2.focusOn(MatchType.TOTAL)						.set(WORD, 5921 - 4231) .set(CHARACTER, 36311 - 25429);

		CATAnalysis result3 = new CATAnalysis(CATTool.PASSOLO, null);
		result3.focusOn(MatchType.UNTRANSLATED)					.set(WORD, 0)			.set(CHARACTER, 0);
		result3.focusOn(MatchType.UNTRANSLATED_REPETITIONS)		.set(WORD, 0)			.set(CHARACTER, 0);
		result3.focusOn(MatchType.AUTO_TRANSLATED)				.set(WORD, 27222)		.set(CHARACTER, 229084);
		result3.focusOn(MatchType.PERCENT_95_99)				.set(WORD, 701)	    	.set(CHARACTER, 5990);
		result3.focusOn(MatchType.PERCENT_85_94)				.set(WORD, 316)	    	.set(CHARACTER, 2751);
		result3.focusOn(MatchType.PERCENT_75_84)				.set(WORD, 32)		    .set(CHARACTER, 266);
		result3.focusOn(MatchType.PERCENT_30_74)				.set(WORD, 62)		    .set(CHARACTER, 462);
		result3.focusOn(MatchType.FOR_REVIEW)					.set(WORD, 31198)		.set(CHARACTER, 273546);
		result3.focusOn(MatchType.TOTAL)						.set(WORD, 59531)	    .set(CHARACTER, 512099);

		CATAnalysis result4 = new CATAnalysis(CATTool.PASSOLO, null);
		result4.focusOn(MatchType.UNTRANSLATED)					.set(WORD, 1613)		.set(CHARACTER, 14277);
		result4.focusOn(MatchType.UNTRANSLATED_REPETITIONS)		.set(WORD, 149)			.set(CHARACTER, 1220);
		result4.focusOn(MatchType.AUTO_TRANSLATED)				.set(WORD, 25990)		.set(CHARACTER, 218883);
		result4.focusOn(MatchType.PERCENT_95_99)				.set(WORD, 869)	    	.set(CHARACTER, 7688);
		result4.focusOn(MatchType.PERCENT_85_94)				.set(WORD, 408)	    	.set(CHARACTER, 3309);
		result4.focusOn(MatchType.PERCENT_75_84)				.set(WORD, 141)		    .set(CHARACTER, 1217);
		result4.focusOn(MatchType.PERCENT_30_74)				.set(WORD, 281)		    .set(CHARACTER, 2275);
		result4.focusOn(MatchType.FOR_REVIEW)					.set(WORD, 30866)		.set(CHARACTER, 271344);
		result4.focusOn(MatchType.TOTAL)						.set(WORD, 60317)	    .set(CHARACTER, 520213);

		CATAnalysis result5 = new CATAnalysis(CATTool.PASSOLO, null);
		result5.focusOn(MatchType.UNTRANSLATED)					.set(WORD, 4896)		.set(CHARACTER, 30897);
		result5.focusOn(MatchType.UNTRANSLATED_REPETITIONS)		.set(WORD, 708)			.set(CHARACTER, 4239);
		result5.focusOn(MatchType.AUTO_TRANSLATED)				.set(WORD, 24)			.set(CHARACTER, 125);
		result5.focusOn(MatchType.PERCENT_95_99)				.set(WORD, 2)	    	.set(CHARACTER, 15);
		result5.focusOn(MatchType.PERCENT_85_94)				.set(WORD, 3)	    	.set(CHARACTER, 20);
		result5.focusOn(MatchType.PERCENT_75_84)				.set(WORD, 3)		    .set(CHARACTER, 21);
		result5.focusOn(MatchType.PERCENT_30_74)				.set(WORD, 0)		    .set(CHARACTER, 0);
		result5.focusOn(MatchType.FOR_REVIEW)					.set(WORD, 0)	 		.set(CHARACTER, 0);
		result5.focusOn(MatchType.TOTAL)						.set(WORD, 5636)	    .set(CHARACTER, 35317);

		CATAnalysis result6 = new CATAnalysis(CATTool.PASSOLO, null);
		result6.focusOn(MatchType.UNTRANSLATED)					.set(WORD, 993)			.set(CHARACTER, 7847);
		result6.focusOn(MatchType.UNTRANSLATED_REPETITIONS)		.set(WORD, 81)			.set(CHARACTER, 651);
		result6.focusOn(MatchType.AUTO_TRANSLATED)				.set(WORD, 0)			.set(CHARACTER, 0);
		result6.focusOn(MatchType.PERCENT_95_99)				.set(WORD, 0)	    	.set(CHARACTER, 0);
		result6.focusOn(MatchType.PERCENT_85_94)				.set(WORD, 0)	    	.set(CHARACTER, 0);
		result6.focusOn(MatchType.PERCENT_75_84)				.set(WORD, 0)		    .set(CHARACTER, 0);
		result6.focusOn(MatchType.PERCENT_30_74)				.set(WORD, 0)		    .set(CHARACTER, 0);
		result6.focusOn(MatchType.FOR_REVIEW)					.set(WORD, 0)	 		.set(CHARACTER, 0);
		result6.focusOn(MatchType.TOTAL)						.set(WORD, 1074)	    .set(CHARACTER, 8498);

		CATAnalysis result7 = new CATAnalysis(CATTool.PASSOLO, null);
		result7.focusOn(MatchType.UNTRANSLATED)					.set(WORD, 993)			.set(CHARACTER, 7847);
		result7.focusOn(MatchType.UNTRANSLATED_REPETITIONS)		.set(WORD, 81)			.set(CHARACTER, 651);
		result7.focusOn(MatchType.AUTO_TRANSLATED)				.set(WORD, 0)			.set(CHARACTER, 0);
		result7.focusOn(MatchType.PERCENT_95_99)				.set(WORD, 0)	    	.set(CHARACTER, 0);
		result7.focusOn(MatchType.PERCENT_85_94)				.set(WORD, 0)	    	.set(CHARACTER, 0);
		result7.focusOn(MatchType.PERCENT_75_84)				.set(WORD, 0)		    .set(CHARACTER, 0);
		result7.focusOn(MatchType.PERCENT_30_74)				.set(WORD, 0)		    .set(CHARACTER, 0);
		result7.focusOn(MatchType.FOR_REVIEW)					.set(WORD, 0)	 		.set(CHARACTER, 0);
		result7.focusOn(MatchType.TOTAL)						.set(WORD, 1074)	    .set(CHARACTER, 8498);

		CATAnalysis result8 = new CATAnalysis(CATTool.PASSOLO, null);
		result8.focusOn(MatchType.UNTRANSLATED)					.set(WORD, 1479)		.set(CHARACTER, 11584);
		result8.focusOn(MatchType.UNTRANSLATED_REPETITIONS)		.set(WORD, 111)			.set(CHARACTER, 898);
		result8.focusOn(MatchType.AUTO_TRANSLATED)				.set(WORD, 64)			.set(CHARACTER, 440);
		result8.focusOn(MatchType.PERCENT_95_99)				.set(WORD, 135)	    	.set(CHARACTER, 869);
		result8.focusOn(MatchType.PERCENT_85_94)				.set(WORD, 35)	    	.set(CHARACTER, 225);
		result8.focusOn(MatchType.PERCENT_75_84)				.set(WORD, 38)		    .set(CHARACTER, 320);
		result8.focusOn(MatchType.PERCENT_30_74)				.set(WORD, 0)		    .set(CHARACTER, 0);
		result8.focusOn(MatchType.FOR_REVIEW)					.set(WORD, 11)	 		.set(CHARACTER, 108);
		result8.focusOn(MatchType.TOTAL)						.set(WORD, 1996 - 123) 	.set(CHARACTER, 15353 - 909);

		CATAnalysis result9 = new CATAnalysis(CATTool.PASSOLO, null);
		result9.focusOn(MatchType.UNTRANSLATED)					.set(WORD, 1479)		.set(CHARACTER, 11584);
		result9.focusOn(MatchType.UNTRANSLATED_REPETITIONS)		.set(WORD, 111)			.set(CHARACTER, 898);
		result9.focusOn(MatchType.AUTO_TRANSLATED)				.set(WORD, 64)			.set(CHARACTER, 440);
		result9.focusOn(MatchType.PERCENT_95_99)				.set(WORD, 135)	    	.set(CHARACTER, 869);
		result9.focusOn(MatchType.PERCENT_85_94)				.set(WORD, 35)	    	.set(CHARACTER, 225);
		result9.focusOn(MatchType.PERCENT_75_84)				.set(WORD, 38)		    .set(CHARACTER, 320);
		result9.focusOn(MatchType.PERCENT_30_74)				.set(WORD, 0)		    .set(CHARACTER, 0);
		result9.focusOn(MatchType.FOR_REVIEW)					.set(WORD, 11)	 		.set(CHARACTER, 108);
		result9.focusOn(MatchType.TOTAL)						.set(WORD, 1996 - 123)	.set(CHARACTER, 15353 - 909);

		return new Object[][] {
				new Object[] {"/passolo2016/Passolo2015_Wordcount_de.htm", result1},
				new Object[] {"/passolo2016/Passolo2016_Wordcount_es_1File.htm", result2},
				new Object[] {"/passolo2016/Passolo2016_Wordcount_es_multipleFiles.htm", result3},
				new Object[] {"/passolo2016/Passolo2016_Wordcount_es_SumReport.htm", result4},
				new Object[] {"/passolo2016/Sample Passolo WordCount_single.htm", result5},
				new Object[] {"/passolo2016/passolo 2018 analiza 1.htm", result6},
				new Object[] {"/passolo2016/passolo 2018 analiza 2.htm", result7},
				new Object[] {"/passolo2016/passolo 2018 analiza 3.htm", result8},
				new Object[] {"/passolo2016/passolo 2018 analiza 4.htm", result9}
		};
	}

	@Test(expectedExceptions = CATAnalysisParserNotSupportedMultilingualAnalysisException.class)
	public void multilingualAnalysisTest() throws CATAnalysisParserException {
		getResult("/passolo2016/Sample Passolo WordCount_multi.htm");
	}

	@Test
	public void headersParsingTest() {
		CATAnalysisParserPassolo2016_HTM parser = new CATAnalysisParserPassolo2016_HTM();
		Assert.assertTrue(parser.isMultiLanguageHeader("<h2>Finnish (Finland)</h2>"));
		Assert.assertTrue(parser.isMultiLanguageHeader("<h2>French (France)</h2>"));
		Assert.assertTrue(parser.isMultiLanguageHeader("<h2>German (Germany)</h2>"));

		Assert.assertFalse(parser.isMultiLanguageHeader("<h2>Common_Constants_IECInvDataBackupRestoreType_de (Spanish (Spain)) - X:/Projects/SEW-EURODRIVE_GmbH/173251_SAT_2537___Passolo-Projekt_MOVISUITE___ES__pt-BR__ZH/0_incoming_files_forTranslation/12.04_3. Paket/Common_Constants_IECInvDataBackupRestoreType_de.xml</h2>"));
		Assert.assertFalse(parser.isMultiLanguageHeader("<h2>DeviceError_SafetyError_PrmStructurErr_de (Spanish (Spain)) - X:/Projects/SEW-EURODRIVE_GmbH/173251_SAT_2537___Passolo-Projekt_MOVISUITE___ES__pt-BR__ZH/0_incoming_files_forTranslation/12.04_3. Paket/DeviceError_SafetyError_PrmStructurErr_de.xml</h2>"));
	}
	
}
