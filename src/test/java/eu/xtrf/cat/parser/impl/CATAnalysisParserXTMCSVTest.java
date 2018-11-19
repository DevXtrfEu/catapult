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
import eu.xtrf.cat.parser.impl.CATAnalysisParserXTMCSV;

/**
 * Test for {@link CATAnalysisParserXTMCSV}.
 * 
 * @author Lukasz Wiktor
 */
@Test
public class CATAnalysisParserXTMCSVTest extends CATAnalysisParserTest<CATAnalysisParserXTMCSV> {

	public CATAnalysisParserXTMCSVTest() {
		super(CATAnalysisParserXTMCSV.class);
	}
	
	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result1 = new CATAnalysis(CATTool.XTM, null);

		result1
			.focusOn(          TOTAL).set(SEGMENT, 1026).set(WORD, 5776).set(CHARACTER, 30486)
			.focusOn(      ICE_MATCH).set(SEGMENT,   24).set(WORD,  186).set(CHARACTER,   971)
			.focusOn(LEVERAGED_MATCH).set(SEGMENT,    7).set(WORD,   51).set(CHARACTER,   271)
			.focusOn(  PERCENT_95_99).set(SEGMENT,    0).set(WORD,    0).set(CHARACTER,     0)
			.focusOn(  PERCENT_85_94).set(SEGMENT,    2).set(WORD,   16).set(CHARACTER,    85)
			.focusOn(  PERCENT_75_84).set(SEGMENT,    1).set(WORD,    8).set(CHARACTER,    45)
			.focusOn(    REPETITIONS).set(SEGMENT,  190).set(WORD,  237).set(CHARACTER,  1637)
			.focusOn( INTERNAL_95_99).set(SEGMENT,    0).set(WORD,    0).set(CHARACTER,     0)
			.focusOn( INTERNAL_85_94).set(SEGMENT,    0).set(WORD,    0).set(CHARACTER,     0)
			.focusOn( INTERNAL_75_84).set(SEGMENT,   10).set(WORD,   36).set(CHARACTER,   240)
			.focusOn(       NO_MATCH).set(SEGMENT,  792).set(WORD, 5242).set(CHARACTER, 27237);
				
		CATAnalysis result2 = new CATAnalysis(CATTool.XTM, null);

		result2
			.focusOn(          TOTAL).set(SEGMENT, 281).set(WORD, 1614).set(CHARACTER, 8512)
			.focusOn(      ICE_MATCH).set(SEGMENT,  24).set(WORD,  186).set(CHARACTER,  971)
			.focusOn(LEVERAGED_MATCH).set(SEGMENT,   7).set(WORD,   51).set(CHARACTER,  271)
			.focusOn(  PERCENT_95_99).set(SEGMENT,   0).set(WORD,    0).set(CHARACTER,    0)
			.focusOn(  PERCENT_85_94).set(SEGMENT,   2).set(WORD,   16).set(CHARACTER,   85)
			.focusOn(  PERCENT_75_84).set(SEGMENT,   1).set(WORD,    8).set(CHARACTER,   45)
			.focusOn(    REPETITIONS).set(SEGMENT,  39).set(WORD,   49).set(CHARACTER,  347)
			.focusOn( INTERNAL_95_99).set(SEGMENT,   0).set(WORD,    0).set(CHARACTER,    0)
			.focusOn( INTERNAL_85_94).set(SEGMENT,   0).set(WORD,    0).set(CHARACTER,    0)
			.focusOn( INTERNAL_75_84).set(SEGMENT,   2).set(WORD,    8).set(CHARACTER,   54)
			.focusOn(       NO_MATCH).set(SEGMENT, 206).set(WORD, 1296).set(CHARACTER, 6739);		
		
		CATAnalysis result3 = new CATAnalysis(CATTool.XTM, null);
		
		result3
			.focusOn(TOTAL)				.set(SEGMENT, 121	)	.set(WORD, 769)			.set(CHARACTER, 5385)
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
		
		CATAnalysis result4 = new CATAnalysis(CATTool.XTM, null);
		
		result4
			.focusOn(TOTAL)				.set(SEGMENT, 450)		.set(WORD, 2016)		.set(CHARACTER, 12678)
			.focusOn(NON_TRANSLATABLE)	.set(SEGMENT, 150)		.set(WORD, 163)			.set(CHARACTER, 375)
			.focusOn(ICE_MATCH)			.set(SEGMENT, 0)		.set(WORD, 0)			.set(CHARACTER, 0)
			.focusOn(LEVERAGED_MATCH)	.set(SEGMENT, 126)		.set(WORD, 781)			.set(CHARACTER, 5222)
			.focusOn(PERCENT_95_99)		.set(SEGMENT, 0)		.set(WORD, 0)			.set(CHARACTER, 0)
			.focusOn(PERCENT_85_94)		.set(SEGMENT, 0)		.set(WORD, 0)			.set(CHARACTER, 0)
			.focusOn(PERCENT_75_84)		.set(SEGMENT, 0)		.set(WORD, 0)			.set(CHARACTER, 0)
			.focusOn(REPETITIONS)		.set(SEGMENT, 76)		.set(WORD, 213)			.set(CHARACTER, 1430)
			.focusOn(INTERNAL_95_99)	.set(SEGMENT, 4)		.set(WORD, 71)			.set(CHARACTER, 709)
			.focusOn(INTERNAL_85_94)	.set(SEGMENT, 10)		.set(WORD, 107)			.set(CHARACTER, 548)
			.focusOn(INTERNAL_75_84)	.set(SEGMENT, 19)		.set(WORD, 172)			.set(CHARACTER, 1115)
			.focusOn(NO_MATCH)			.set(SEGMENT, 65)		.set(WORD, 509)			.set(CHARACTER, 3279);

		CATAnalysis result5 = new CATAnalysis(CATTool.XTM, null);
		result5
			.focusOn(TOTAL)				.set(SEGMENT, 105)		.set(WORD, 536)			.set(CHARACTER, 3407)
			.focusOn(ICE_MATCH)			.set(SEGMENT, 18)		.set(WORD, 29)			.set(CHARACTER, 153)
			.focusOn(LEVERAGED_MATCH)	.set(SEGMENT, 50)		.set(WORD, 109)			.set(CHARACTER, 713)
			.focusOn(PERCENT_95_99)		.set(SEGMENT, 9)		.set(WORD, 134)			.set(CHARACTER, 873)
			.focusOn(PERCENT_85_94)		.set(SEGMENT, 4)		.set(WORD, 32)			.set(CHARACTER, 190)
			.focusOn(PERCENT_75_84)		.set(SEGMENT, 5)		.set(WORD, 29)			.set(CHARACTER, 205)
			.focusOn(REPETITIONS)		.set(SEGMENT, 0)		.set(WORD, 0)			.set(CHARACTER, 0)
			.focusOn(INTERNAL_95_99)	.set(SEGMENT, 0)		.set(WORD, 0)			.set(CHARACTER, 0)
			.focusOn(INTERNAL_85_94)	.set(SEGMENT, 0)		.set(WORD, 0)			.set(CHARACTER, 0)
			.focusOn(INTERNAL_75_84)	.set(SEGMENT, 2)		.set(WORD, 8)			.set(CHARACTER, 67)
			.focusOn(NO_MATCH)			.set(SEGMENT, 17)		.set(WORD, 195)			.set(CHARACTER, 1206);
		
		return new Object[][] {
				new Object[] {"/xtm/xtm-1.csv", result1},
				new Object[] {"/xtm/xtm-1.csv.zip", result1},
				new Object[] {"/xtm/xtm-2.csv", result2},
				new Object[] {"/xtm/xtm-2.csv.zip", result2},
				new Object[] {"/xtm/CsvMetrics.csv", result3},
				new Object[] {"/xtm/xtm.csv", result4},
				new Object[] {"/xtm/xtm-semicolon.csv", result5}
		};
	}
	

}
