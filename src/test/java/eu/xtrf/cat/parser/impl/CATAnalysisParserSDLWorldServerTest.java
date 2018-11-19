/*
 * XTRF Translation Management System Ltd.
 * 
 * 		http://www.xtrf.eu
 * 
 * Copyright (c) 2005-2012 Radzisz Communications and EUTECert
 * 
 * 		http://www.eutecert.eu
 * 		http://www.radzisz.com
 * 
 * All rights reserved.
 * 
 * CATAnalysisParserAcrossHTMTest.java, created: 12-11-2012.
 */
package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;
import eu.xtrf.cat.parser.CATAnalysisParserNotSupportedMultilingualAnalysisException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static eu.xtrf.cat.MatchType.*;
import static eu.xtrf.cat.Unit.*;

@Test
public class CATAnalysisParserSDLWorldServerTest extends CATAnalysisParserTest<CATAnalysisParserSDLWorldServer> {

	public CATAnalysisParserSDLWorldServerTest() {
		super(CATAnalysisParserSDLWorldServer.class);
	}	
	
	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis expectedResult1 = new CATAnalysis(CATTool.SDL_WORLD_SERVER, null);
		expectedResult1.set(Unit.WORD, MatchType.TOTAL, 595);
		expectedResult1.set(Unit.WORD, MatchType.ICE_MATCH, 14);
		expectedResult1.set(Unit.WORD, MatchType.PERCENT_100, 52);
		expectedResult1.set(Unit.WORD, MatchType.PERCENT_95_100, 29);
		expectedResult1.set(Unit.WORD, MatchType.PERCENT_85_95, 62);
		expectedResult1.set(Unit.WORD, MatchType.PERCENT_75_85, 51);
		expectedResult1.set(Unit.WORD, MatchType.PERCENT_50_75, 33);
		expectedResult1.set(Unit.WORD, MatchType.PERCENT_0_50, 314);
		expectedResult1.set(Unit.WORD, MatchType.REPETITIONS, 40);

		CATAnalysis expectedResult2 = new CATAnalysis(CATTool.SDL_WORLD_SERVER, null);
		expectedResult2.set(Unit.WORD, MatchType.TOTAL, 5269);
		expectedResult2.set(Unit.WORD, MatchType.ICE_MATCH, 62);
		expectedResult2.set(Unit.WORD, MatchType.PERCENT_100, 234);
		expectedResult2.set(Unit.WORD, MatchType.PERCENT_95_100, 13);
		expectedResult2.set(Unit.WORD, MatchType.PERCENT_85_95, 6);
		expectedResult2.set(Unit.WORD, MatchType.PERCENT_75_85, 13);
		expectedResult2.set(Unit.WORD, MatchType.PERCENT_50_75, 95);
		expectedResult2.set(Unit.WORD, MatchType.PERCENT_0_50, 4538);
		expectedResult2.set(Unit.WORD, MatchType.REPETITIONS, 308);

		return new Object[][] {
				new Object[] {"/sdl_worldserver/Sample 1 lang 1 file scope_info.csv", expectedResult1},
				new Object[] {"/sdl_worldserver/Sample 1 lang Several files scope_info.csv", expectedResult2}
		};
	}

	@Test(expectedExceptions = CATAnalysisParserNotSupportedMultilingualAnalysisException.class)
	public void multilingualAnalysisTest1() throws CATAnalysisParserException {
		getResult("/sdl_worldserver/Sample 3 langs scope_info.csv");
	}

	@Test(expectedExceptions = CATAnalysisParserNotSupportedMultilingualAnalysisException.class)
	public void multilingualAnalysisTest2() throws CATAnalysisParserException {
		getResult("/sdl_worldserver/Sample 5 langs scope_info.csv");
	}

	@Test(expectedExceptions = CATAnalysisParserNotSupportedMultilingualAnalysisException.class)
	public void multilingualAnalysisTest3() throws CATAnalysisParserException {
		getResult("/sdl_worldserver/Sample Multilingual_scope.csv");
	}
}
