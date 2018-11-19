package eu.xtrf.cat;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;

import org.testng.annotations.Test;


public class CATAnalysisTest {

	@Test(expectedExceptions = RuntimeException.class)
	public void should_throw_exception_when_aggregating_different_cat_tools() {
		CATAnalysis analysis1 = new CATAnalysis(CATTool.TRADOS);
		CATAnalysis analysis2 = new CATAnalysis(CATTool.MEMO_Q);
		
		analysis1.aggregate(analysis2);
	}
	
	@Test
	public void should_aggregate_analysis() {
		CATAnalysis analysis1 = new CATAnalysis(CATTool.TRADOS);
		analysis1.set(Unit.WORD, MatchType.PERCENT_95_99, new BigDecimal(1));
		analysis1.set(Unit.WORD, MatchType.NO_MATCH, new BigDecimal(2));
		analysis1.set(Unit.CHARACTER, MatchType.X_TRANSLATED, new BigDecimal(3));
		CATAnalysis analysis2 = new CATAnalysis(CATTool.TRADOS);
		analysis2.set(Unit.WORD, MatchType.PERCENT_95_99, new BigDecimal(40));
		analysis2.set(Unit.WORD, MatchType.NO_MATCH, new BigDecimal(50));
		analysis2.set(Unit.SEGMENT, MatchType.X_TRANSLATED, new BigDecimal(60));
		
		CATAnalysis result = analysis1.aggregate(analysis2);
		
		assertEquals(result.get(Unit.WORD, MatchType.PERCENT_95_99), new BigDecimal(41));
		assertEquals(result.get(Unit.WORD, MatchType.NO_MATCH), new BigDecimal(52));
		assertEquals(result.get(Unit.CHARACTER, MatchType.X_TRANSLATED), new BigDecimal(3));
		assertEquals(result.get(Unit.SEGMENT, MatchType.X_TRANSLATED), new BigDecimal(60));
	}
	
}
