package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.MatchType.NO_MATCH;
import static eu.xtrf.cat.MatchType.PERCENT_100;
import static eu.xtrf.cat.MatchType.PERCENT_50_74;
import static eu.xtrf.cat.MatchType.PERCENT_75_84;
import static eu.xtrf.cat.MatchType.PERCENT_85_94;
import static eu.xtrf.cat.MatchType.PERCENT_95_99;
import static eu.xtrf.cat.MatchType.REPETITIONS;
import static eu.xtrf.cat.MatchType.TOTAL;
import static eu.xtrf.cat.MatchType.X_TRANSLATED;
import static eu.xtrf.cat.Unit.SEGMENT;
import static eu.xtrf.cat.Unit.WORD;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.parser.impl.CATAnalysisParserTrados;

/**
 * Test for {@link CATAnalysisParserTrados}.
 * 
 * @author Lukasz Wiktor
 */
@Test
public class CATAnalysisParserTradosTest extends CATAnalysisParserTest<CATAnalysisParserTrados> {
	
	public CATAnalysisParserTradosTest() {
		super(CATAnalysisParserTrados.class);
	}
	
	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis trados1Result = new CATAnalysis(CATTool.TRADOS, null);

		trados1Result
			.set(SEGMENT, X_TRANSLATED,  43).set(WORD, X_TRANSLATED,  128)
			.set(SEGMENT, REPETITIONS,    2).set(WORD, REPETITIONS,    11)
			.set(SEGMENT, PERCENT_100,   36).set(WORD, PERCENT_100,    77)
			.set(SEGMENT, PERCENT_95_99,  5).set(WORD, PERCENT_95_99,  30)
			.set(SEGMENT, PERCENT_85_94,  6).set(WORD, PERCENT_85_94,  74)
			.set(SEGMENT, PERCENT_75_84,  1).set(WORD, PERCENT_75_84,  29)
			.set(SEGMENT, PERCENT_50_74,  3).set(WORD, PERCENT_50_74,  10)
			.set(SEGMENT, NO_MATCH,      43).set(WORD, NO_MATCH,      512)
			.set(SEGMENT, TOTAL,        139).set(WORD, TOTAL,         871);

		CATAnalysis trados2Result = new CATAnalysis(CATTool.TRADOS, null);
		
		trados2Result
			.set(SEGMENT, X_TRANSLATED,  87).set(WORD, X_TRANSLATED,  345)
			.set(SEGMENT, REPETITIONS,   31).set(WORD, REPETITIONS,   200)
			.set(SEGMENT, PERCENT_100,  255).set(WORD, PERCENT_100,  1632)
			.set(SEGMENT, PERCENT_95_99,  6).set(WORD, PERCENT_95_99,  88)
			.set(SEGMENT, PERCENT_85_94, 28).set(WORD, PERCENT_85_94, 274)
			.set(SEGMENT, PERCENT_75_84, 13).set(WORD, PERCENT_75_84, 133)
			.set(SEGMENT, PERCENT_50_74,  3).set(WORD, PERCENT_50_74,  61)
			.set(SEGMENT, NO_MATCH,      27).set(WORD, NO_MATCH,      255)
			.set(SEGMENT, TOTAL,        450).set(WORD, TOTAL,        2988);
		
		CATAnalysis trados7_1Result = new CATAnalysis(CATTool.TRADOS, null);

		trados7_1Result
			.set(SEGMENT, X_TRANSLATED,   0).set(WORD, X_TRANSLATED,    0)
			.set(SEGMENT, REPETITIONS,   24).set(WORD, REPETITIONS,   335)
			.set(SEGMENT, PERCENT_100,   18).set(WORD, PERCENT_100,    45)
			.set(SEGMENT, PERCENT_95_99,  9).set(WORD, PERCENT_95_99,  58)
			.set(SEGMENT, PERCENT_85_94,  4).set(WORD, PERCENT_85_94,  21)
			.set(SEGMENT, PERCENT_75_84,  7).set(WORD, PERCENT_75_84,  54)
			.set(SEGMENT, PERCENT_50_74, 11).set(WORD, PERCENT_50_74,  52)
			.set(SEGMENT, NO_MATCH,     369).set(WORD, NO_MATCH,     7827)
			.set(SEGMENT, TOTAL,        442).set(WORD, TOTAL,        8392);
		
		CATAnalysis trados_2Result = new CATAnalysis(CATTool.TRADOS, null);
		
		trados_2Result
			.set(SEGMENT, X_TRANSLATED,   0).set(WORD, X_TRANSLATED,    0)
			.set(SEGMENT, REPETITIONS,   31).set(WORD, REPETITIONS,    95)
			.set(SEGMENT, PERCENT_100,   20).set(WORD, PERCENT_100,    20)
			.set(SEGMENT, PERCENT_95_99,  3).set(WORD, PERCENT_95_99,   4)
			.set(SEGMENT, PERCENT_85_94,  2).set(WORD, PERCENT_85_94,   2)
			.set(SEGMENT, PERCENT_75_84,  1).set(WORD, PERCENT_75_84,   1)
			.set(SEGMENT, PERCENT_50_74,  0).set(WORD, PERCENT_50_74,   0)
			.set(SEGMENT, NO_MATCH,     115).set(WORD, NO_MATCH,      323)
			.set(SEGMENT, TOTAL,        172).set(WORD, TOTAL,         445);

		return new Object[][] {
				new Object[] {"/trados/trados-1.log", trados1Result},
				new Object[] {"/trados/trados-2.log", trados2Result},
				new Object[] {"/trados/trados7-1.log", trados7_1Result},
				new Object[] {"/trados/trados7-2.log", trados_2Result}
		};
	}
	
}
