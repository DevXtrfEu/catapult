package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.Unit.WORD;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;

@Test
public class CATAnalysisParserTransitNXTWorkstationXLSTest extends CATAnalysisParserTest<CATAnalysisParserTransitNXTWorkstationXLS> {

	public CATAnalysisParserTransitNXTWorkstationXLSTest() {
		super(CATAnalysisParserTransitNXTWorkstationXLS.class);
	}

	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result = new CATAnalysis(CATTool.TRANSIT_NXT, null);

		result.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 89);
		result.focusOn(MatchType.CHECK_PRETRANSLATION)	.set(WORD, 16);
		result.focusOn(MatchType.PERCENT_100)			.set(WORD, 90);
		result.focusOn(MatchType.PERCENT_95_99)			.set(WORD, 18);
		result.focusOn(MatchType.PERCENT_85_94)			.set(WORD, 13);
		result.focusOn(MatchType.PERCENT_75_84)			.set(WORD, 31);
		result.focusOn(MatchType.PERCENT_50_74)			.set(WORD, 27);
		result.focusOn(MatchType.NOT_TRANSLATED)		.set(WORD, 1049);
		result.focusOn(MatchType.TOTAL)					.set(WORD, 1333);

		CATAnalysis result2 = new CATAnalysis(CATTool.TRANSIT_NXT, null);

		result2.focusOn(MatchType.REPETITIONS)				.set(WORD, 4);
		result2.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 1);
		result2.focusOn(MatchType.TRANSLATED_PARAGRAPH)		.set(WORD, 0);
		result2.focusOn(MatchType.TRANSLATED_STRUCTURE)		.set(WORD, 0);
		result2.focusOn(MatchType.CHECK_PRETRANSLATION)		.set(WORD, 0);
		result2.focusOn(MatchType.PERCENT_100)				.set(WORD, 1);
		result2.focusOn(MatchType.PERCENT_75_99)			.set(WORD, 27);
		result2.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 1803);
		result2.focusOn(MatchType.TOTAL)					.set(WORD, 1836);

		CATAnalysis result1 = new CATAnalysis(CATTool.TRANSIT_NXT, null);

		result1.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 673);
		result1.focusOn(MatchType.CHECK_PRETRANSLATION)		.set(WORD, 10);
		result1.focusOn(MatchType.PERCENT_100)				.set(WORD, 4);
		result1.focusOn(MatchType.PERCENT_95_99)			.set(WORD, 48);
		result1.focusOn(MatchType.PERCENT_85_94)			.set(WORD, 1);
		result1.focusOn(MatchType.PERCENT_75_84)			.set(WORD, 63);
		result1.focusOn(MatchType.PERCENT_50_74)			.set(WORD, 42);
		result1.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 490);
		result1.focusOn(MatchType.TOTAL)					.set(WORD, 1331);

		CATAnalysis result3 = new CATAnalysis(CATTool.TRANSIT_NXT, null);

		result3.focusOn(MatchType.REPETITIONS)				.set(WORD, 268);
		result3.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 5);
		result3.focusOn(MatchType.CHECK_PRETRANSLATION)		.set(WORD, 0);
		result3.focusOn(MatchType.PERCENT_100)				.set(WORD, 0);
		result3.focusOn(MatchType.PERCENT_95_99)			.set(WORD, 3);
		result3.focusOn(MatchType.PERCENT_85_94)			.set(WORD, 0);
		result3.focusOn(MatchType.PERCENT_75_84)			.set(WORD, 0);
		result3.focusOn(MatchType.PERCENT_50_74)			.set(WORD, 2);
		result3.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 242);
		result3.focusOn(MatchType.TOTAL)					.set(WORD, 520);

		CATAnalysis result4 = new CATAnalysis(CATTool.TRANSIT_NXT, null);

		result4.focusOn(MatchType.REPETITIONS)				.set(WORD, 92);
		result4.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 70);
		result4.focusOn(MatchType.CHECK_PRETRANSLATION)		.set(WORD, 0);
		result4.focusOn(MatchType.PERCENT_100)				.set(WORD, 0);
		result4.focusOn(MatchType.PERCENT_95_99)			.set(WORD, 49);
		result4.focusOn(MatchType.PERCENT_85_94)			.set(WORD, 4);
		result4.focusOn(MatchType.PERCENT_75_84)			.set(WORD, 59);
		result4.focusOn(MatchType.PERCENT_50_74)			.set(WORD, 8);
		result4.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 523);
		result4.focusOn(MatchType.TOTAL)					.set(WORD, 805);

		CATAnalysis result5 = new CATAnalysis(CATTool.TRANSIT_NXT, null);

		result5.focusOn(MatchType.REPETITIONS)				.set(WORD, 86);
		result5.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 4315);
		result5.focusOn(MatchType.CHECK_PRETRANSLATION)		.set(WORD, 371);
		result5.focusOn(MatchType.PERCENT_100)				.set(WORD, 25);
		result5.focusOn(MatchType.PERCENT_95_99)			.set(WORD, 467);
		result5.focusOn(MatchType.PERCENT_85_94)			.set(WORD, 662);
		result5.focusOn(MatchType.PERCENT_75_84)			.set(WORD, 336);
		result5.focusOn(MatchType.PERCENT_50_74)			.set(WORD, 141);
		result5.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 3546);
		result5.focusOn(MatchType.TOTAL)					.set(WORD, 9949);

		CATAnalysis result6 = new CATAnalysis(CATTool.TRANSIT_NXT, null);

		result6.focusOn(MatchType.REPETITIONS)				.set(WORD, 17695);
		result6.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 0);
		result6.focusOn(MatchType.CHECK_PRETRANSLATION)		.set(WORD, 0);
		result6.focusOn(MatchType.PERCENT_100)				.set(WORD, 1495);
		result6.focusOn(MatchType.PERCENT_95_99)			.set(WORD, 947);
		result6.focusOn(MatchType.PERCENT_85_94)			.set(WORD, 1651);
		result6.focusOn(MatchType.PERCENT_75_84)			.set(WORD, 1904);
		result6.focusOn(MatchType.PERCENT_50_74)			.set(WORD, 816);
		result6.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 58773);
		result6.focusOn(MatchType.TOTAL)					.set(WORD, 83281);

		CATAnalysis result7 = new CATAnalysis(CATTool.TRANSIT_NXT, null);

		result7.focusOn(MatchType.REPETITIONS)				.set(WORD, 14);
		result7.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 335);
		result7.focusOn(MatchType.CHECK_PRETRANSLATION)		.set(WORD, 16);
		result7.focusOn(MatchType.PERCENT_100)				.set(WORD, 0);
		result7.focusOn(MatchType.PERCENT_95_99)			.set(WORD, 18);
		result7.focusOn(MatchType.PERCENT_85_94)			.set(WORD, 0);
		result7.focusOn(MatchType.PERCENT_75_84)			.set(WORD, 5);
		result7.focusOn(MatchType.PERCENT_50_74)			.set(WORD, 64);
		result7.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 27);
		result7.focusOn(MatchType.TOTAL)					.set(WORD, 479);

		CATAnalysis result8 = new CATAnalysis(CATTool.TRANSIT_NXT, null);

		result8.focusOn(MatchType.REPETITIONS)				.set(WORD, 196);
		result8.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 1477);
		result8.focusOn(MatchType.CHECK_PRETRANSLATION)		.set(WORD, 12);
		result8.focusOn(MatchType.PERCENT_100)				.set(WORD, 132);
		result8.focusOn(MatchType.PERCENT_95_99)			.set(WORD, 245);
		result8.focusOn(MatchType.PERCENT_85_94)			.set(WORD, 736);
		result8.focusOn(MatchType.PERCENT_75_84)			.set(WORD, 424);
		result8.focusOn(MatchType.PERCENT_50_74)			.set(WORD, 208);
		result8.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 4237);
		result8.focusOn(MatchType.TOTAL)					.set(WORD, 7667);

		CATAnalysis result9 = new CATAnalysis(CATTool.TRANSIT_NXT, null);

		result9.focusOn(MatchType.REPETITIONS)				.set(WORD, 196);
		result9.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 739);
		result9.focusOn(MatchType.CHECK_PRETRANSLATION)		.set(WORD, 3);
		result9.focusOn(MatchType.PERCENT_100)				.set(WORD, 33);
		result9.focusOn(MatchType.PERCENT_95_99)			.set(WORD, 61);
		result9.focusOn(MatchType.PERCENT_85_94)			.set(WORD, 184);
		result9.focusOn(MatchType.PERCENT_75_84)			.set(WORD, 106);
		result9.focusOn(MatchType.PERCENT_50_74)			.set(WORD, 52);
		result9.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 2119);
		result9.focusOn(MatchType.TOTAL)					.set(WORD, 3493); //in the file the sum does not match. Off by one (rounding error).

		CATAnalysis result10 = new CATAnalysis(CATTool.TRANSIT_NXT, null);

		result10.focusOn(MatchType.REPETITIONS)				.set(WORD, 196);
		result10.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 1477);
		result10.focusOn(MatchType.CHECK_PRETRANSLATION)	.set(WORD, 24);
		result10.focusOn(MatchType.PERCENT_100)				.set(WORD, 264);
		result10.focusOn(MatchType.PERCENT_95_99)			.set(WORD, 490);
		result10.focusOn(MatchType.PERCENT_85_94)			.set(WORD, 1472);
		result10.focusOn(MatchType.PERCENT_75_84)			.set(WORD, 848);
		result10.focusOn(MatchType.PERCENT_50_74)			.set(WORD, 416);
		result10.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 8474);
		result10.focusOn(MatchType.TOTAL)					.set(WORD, 13465+196);

		CATAnalysis result11 = new CATAnalysis(CATTool.TRANSIT_NXT, null);

		result11.focusOn(MatchType.REPETITIONS)				.set(WORD, 1254);
		result11.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 0);
		result11.focusOn(MatchType.CHECK_PRETRANSLATION)	.set(WORD, 0);
		result11.focusOn(MatchType.PERCENT_100)				.set(WORD, 0);
		result11.focusOn(MatchType.PERCENT_70_99)			.set(WORD, 886);
		result11.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 2355);
		result11.focusOn(MatchType.TOTAL)					.set(WORD, 3241+1254);


		CATAnalysis result12 = new CATAnalysis(CATTool.TRANSIT_NXT, null);

        result12.focusOn(MatchType.REPETITIONS)				.set(WORD, 1231);
        result12.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 0);
        result12.focusOn(MatchType.CHECK_PRETRANSLATION)	.set(WORD, 0);
        result12.focusOn(MatchType.PERCENT_100)				.set(WORD, 23);
        result12.focusOn(MatchType.PERCENT_70_99)			.set(WORD, 886);
        result12.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 2424);
        result12.focusOn(MatchType.TOTAL)					.set(WORD, 3333+1231);

		CATAnalysis result13 = new CATAnalysis(CATTool.TRANSIT_NXT, null);

        result13.focusOn(MatchType.REPETITIONS)				.set(WORD, 196);
        result13.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 1477);
        result13.focusOn(MatchType.CHECK_PRETRANSLATION)		.set(WORD, 12);
        result13.focusOn(MatchType.PERCENT_100)				.set(WORD, 132);
        result13.focusOn(MatchType.PERCENT_95_99)			.set(WORD, 245);
        result13.focusOn(MatchType.PERCENT_85_94)			.set(WORD, 736);
        result13.focusOn(MatchType.PERCENT_75_84)			.set(WORD, 424);
        result13.focusOn(MatchType.PERCENT_50_74)			.set(WORD, 208);
        result13.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 4237);
        result13.focusOn(MatchType.TOTAL)					.set(WORD, 7667);

        CATAnalysis result14 = new CATAnalysis(CATTool.TRANSIT_NXT, null);

        result14.focusOn(MatchType.REPETITIONS)				.set(WORD, 108);
        result14.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 350);
        result14.focusOn(MatchType.CHECK_PRETRANSLATION)		.set(WORD, 0);
        result14.focusOn(MatchType.PERCENT_100)				.set(WORD, 11);
        result14.focusOn(MatchType.PERCENT_90_99)			.set(WORD, 0);
        result14.focusOn(MatchType.PERCENT_71_89)			.set(WORD, 20);
        result14.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 15);
        result14.focusOn(MatchType.TOTAL)					.set(WORD, 396+108);

        CATAnalysis result15 = new CATAnalysis(CATTool.TRANSIT_NXT, null);

        result15.focusOn(MatchType.REPETITIONS)				.set(WORD, 17695);
        result15.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 0);
        result15.focusOn(MatchType.CHECK_PRETRANSLATION)		.set(WORD, 0);
        result15.focusOn(MatchType.PERCENT_100)				.set(WORD, 1495);
        result15.focusOn(MatchType.PERCENT_95_99)			.set(WORD, 947);
        result15.focusOn(MatchType.PERCENT_85_94)			.set(WORD, 1651);
        result15.focusOn(MatchType.PERCENT_75_84)			.set(WORD, 1904);
        result15.focusOn(MatchType.PERCENT_50_74)			.set(WORD, 816);
        result15.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 58773);
        result15.focusOn(MatchType.TOTAL)					.set(WORD, 65586+17695);

        CATAnalysis result16 = new CATAnalysis(CATTool.TRANSIT_NXT, null);

        result16.focusOn(MatchType.REPETITIONS)				.set(WORD, 14);
        result16.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 335);
        result16.focusOn(MatchType.CHECK_PRETRANSLATION)		.set(WORD, 16);
        result16.focusOn(MatchType.PERCENT_100)				.set(WORD, 0);
        result16.focusOn(MatchType.PERCENT_95_99)			.set(WORD, 18);
        result16.focusOn(MatchType.PERCENT_85_94)			.set(WORD, 0);
        result16.focusOn(MatchType.PERCENT_75_84)			.set(WORD, 5);
        result16.focusOn(MatchType.PERCENT_50_74)			.set(WORD, 64);
        result16.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 27);
        result16.focusOn(MatchType.TOTAL)					.set(WORD, 465+14);

        CATAnalysis result17 = new CATAnalysis(CATTool.TRANSIT_NXT, null);
        result17.focusOn(MatchType.REPETITIONS)				.set(WORD, 8);
        result17.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 0);
        result17.focusOn(MatchType.CHECK_PRETRANSLATION)		.set(WORD, 90);
        result17.focusOn(MatchType.PERCENT_100)				.set(WORD, 33);
        result17.focusOn(MatchType.PERCENT_95_99)			.set(WORD, 172);
        result17.focusOn(MatchType.PERCENT_85_94)			.set(WORD, 495);
        result17.focusOn(MatchType.PERCENT_75_84)			.set(WORD, 145);
        result17.focusOn(MatchType.PERCENT_50_74)			.set(WORD, 86);
        result17.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 1901);
        result17.focusOn(MatchType.TOTAL)					.set(WORD, 2922+8);

        CATAnalysis result18 = new CATAnalysis(CATTool.TRANSIT_NXT, null);

        result18.focusOn(MatchType.REPETITIONS)				.set(WORD, 4);
        result18.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 0);
        result18.focusOn(MatchType.CHECK_PRETRANSLATION)		.set(WORD, 44);
        result18.focusOn(MatchType.PERCENT_100)				.set(WORD, 405);
        result18.focusOn(MatchType.PERCENT_70_99)			.set(WORD, 561);
        result18.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 346);
        result18.focusOn(MatchType.TOTAL)					.set(WORD, 1356+4);

        CATAnalysis result19 = new CATAnalysis(CATTool.TRANSIT_NXT, null);

        result19.focusOn(MatchType.REPETITIONS)				.set(WORD, 6);
        result19.focusOn(MatchType.PRETRANSLATED)			.set(WORD, 0);
        result19.focusOn(MatchType.CHECK_PRETRANSLATION)		.set(WORD, 46);
        result19.focusOn(MatchType.PERCENT_100)				.set(WORD, 481);
        result19.focusOn(MatchType.PERCENT_70_99)			.set(WORD, 559);
        result19.focusOn(MatchType.NOT_TRANSLATED)			.set(WORD, 377);
        result19.focusOn(MatchType.TOTAL)					.set(WORD, 1463+6);

        CATAnalysis result20 = new CATAnalysis(CATTool.TRANSIT_NXT, null);

        result20.focusOn(MatchType.REPETITIONS).set(WORD, 0);
        result20.focusOn(MatchType.PRETRANSLATED).set(WORD, 168);
        result20.focusOn(MatchType.CHECK_PRETRANSLATION).set(WORD, 0);
        result20.focusOn(MatchType.PERCENT_100).set(WORD, 225);
        result20.focusOn(MatchType.PERCENT_70_99).set(WORD, 56);
        result20.focusOn(MatchType.NOT_TRANSLATED).set(WORD, 64);
        result20.focusOn(MatchType.TOTAL).set(WORD, 513);

        CATAnalysis result21 = new CATAnalysis(CATTool.TRANSIT_NXT, null);

        result21.focusOn(MatchType.REPETITIONS).set(WORD, 0);
        result21.focusOn(MatchType.PRETRANSLATED).set(WORD, 49);
        result21.focusOn(MatchType.CHECK_PRETRANSLATION).set(WORD, 0);
        result21.focusOn(MatchType.PERCENT_100).set(WORD, 29);
        result21.focusOn(MatchType.PERCENT_70_99).set(WORD, 43);
        result21.focusOn(MatchType.NOT_TRANSLATED).set(WORD, 117);
        result21.focusOn(MatchType.TOTAL).set(WORD, 238);

        CATAnalysis result22 = new CATAnalysis(CATTool.TRANSIT_NXT, null);

        result22.focusOn(MatchType.REPETITIONS).set(WORD, 21);
        result22.focusOn(MatchType.PRETRANSLATED).set(WORD, 7);
        result22.focusOn(MatchType.CHECK_PRETRANSLATION).set(WORD, 0);
        result22.focusOn(MatchType.PERCENT_100).set(WORD, 0);
        result22.focusOn(MatchType.PERCENT_70_99).set(WORD, 2);
        result22.focusOn(MatchType.NOT_TRANSLATED).set(WORD, 50);
        result22.focusOn(MatchType.TOTAL).set(WORD, 80);

        CATAnalysis result23 = new CATAnalysis(CATTool.TRANSIT_NXT, null);

        result23.focusOn(MatchType.REPETITIONS).set(WORD, 243);
        result23.focusOn(MatchType.PRETRANSLATED).set(WORD, 565);
        result23.focusOn(MatchType.CHECK_PRETRANSLATION).set(WORD, 0);
        result23.focusOn(MatchType.PERCENT_100).set(WORD, 20);
        result23.focusOn(MatchType.PERCENT_70_99).set(WORD, 117);
        result23.focusOn(MatchType.NOT_TRANSLATED).set(WORD, 1829);
        result23.focusOn(MatchType.TOTAL).set(WORD, 2774);

        return new Object[][] {
			new Object[] {"/transit/transit.xls", result},
			new Object[] {"/transit/transit2.xls", result2},
			new Object[] {"/transit/1.transit.xls", result1},
			new Object[] {"/transit/transit3.xls", result3},
			new Object[] {"/transit/4.transit.xls", result4},
			new Object[] {"/transit/5.transit.xls", result5},
			new Object[] {"/transit/Transit NXT analysis-en.xls", result6},
			new Object[] {"/transit/Transit NXT - DE-LT2.xls", result7},
			new Object[] {"/transit/Transit NXT analysis-1.xls", result8},
			new Object[] {"/transit/Expansionfactor0.5.xls", result9},
			new Object[] {"/transit/Expansionfactor2.0.xls", result10},
			new Object[] {"/transit/lesscolumns.xls", result11},
			new Object[] {"/transit/lesscolumns2.xls", result12},
			new Object[] {"/transit/separaterepetitionscolumn.xls", result13},
            new Object[] {"/transit/repetitionsanalysisDE.xls", result14},
            new Object[] {"/transit/DE-LT.xls", result15},
            new Object[] {"/transit/DE-LT2.xls", result16},
            new Object[] {"/transit/DE-SV.xls", result17},
            new Object[] {"/transit/EN-LT.xls", result18},
            new Object[] { "/transit/EN-LV.xls", result19 },
            new Object[] { "/transit/transit_odd_rounding_ok.xls", result20 },
            new Object[] { "/transit/transit_odd_rounding_misfire.xls", result21 },
            new Object[] { "/transit/log_7607.xls", result22 },
            new Object[] { "/transit/log_7417.xls", result23 }
		};
	}
}
