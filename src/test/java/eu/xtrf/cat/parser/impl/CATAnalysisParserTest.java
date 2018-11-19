package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParser;
import eu.xtrf.cat.parser.CATAnalysisParserException;
import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.InputStream;

import static eu.xtrf.cat.MatchType.TOTAL;
import static org.testng.Assert.fail;

public abstract class CATAnalysisParserTest<P extends CATAnalysisParser> {

	private CATTool catTool;

	public CATAnalysisParserTest(Class<P> parserClass) {
		try {
			P parser = parserClass.newInstance();
			this.catTool = parser.getCATTool();
		} catch (Exception e) {
			fail("Cannot instantiate parser. ", e);
		}
	}

	public CATAnalysis getResult(String fileName, boolean failOnException) {
		try {
			CATAnalysis catAnalysis = getResult(fileName);
			return catAnalysis;
		} catch (CATAnalysisParserException e) {
			if (failOnException) fail("Parsing error", e);
		}
		return null;
	}

	public CATAnalysis getResult(String fileName) throws CATAnalysisParserException {
		InputStream is = null;
		try {
			is = getStreamToTest(fileName);
			CATAnalysisCompositeParser parser = CATAnalysisCompositeParser.newInstance();
			CATAnalysis catAnalysis = parser.parse(fileName, is);
			Assert.assertEquals(catAnalysis.getCATTool(), this.catTool);
			return catAnalysis;
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

	private InputStream getStreamToTest(String fileName) {
		return CATAnalysisParserTest.class.getResourceAsStream(fileName);
	}

	@Test(dataProvider = "files")
	public void testParser(String fileName, CATAnalysis expectedResult) {
		CATAnalysis result = getResult(fileName, true);
		assertEquals(result, expectedResult);
	}

	public void assertEquals(CATAnalysis actual, CATAnalysis expected) {
		Assert.assertEquals(actual.getCATTool(), expected.getCATTool(), "CATGridType");
		for (Unit unit : Unit.values()) {
			for (MatchType matchType : expected.getCATTool().getMatchTypes()) {
				Assert.assertEquals(actual.get(unit, matchType), expected.get(unit, matchType), matchType.toString() + " " + unit.toString());
			}
			Assert.assertEquals(actual.get(unit, TOTAL), expected.get(unit, TOTAL), TOTAL.toString() + " " + unit.toString());
		}
		Assert.assertEquals(actual.getFileNames(), expected.getFileNames());
		if (expected.getCharsPerWord() != null) {
			Assert.assertEquals(actual.getCharsPerWord(), expected.getCharsPerWord());
		}
	}
}
