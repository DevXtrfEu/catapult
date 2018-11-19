package eu.xtrf.cat.parser.impl;

import java.math.BigDecimal;

import eu.xtrf.cat.parser.CATAnalysisParserException;
import eu.xtrf.cat.parser.CATAnalysisParserNotSupportedMultilingualAnalysisException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;

public class CATAnalysisParserLogoportMemsourceTest extends CATAnalysisParserTest<CATAnalysisParserLogoportMemsource> {

    public CATAnalysisParserLogoportMemsourceTest() {
        super(CATAnalysisParserLogoportMemsource.class);
    }

    @DataProvider(name = "files")
    public Object[][] getFiles() {
        CATAnalysis logoportExpected = new CATAnalysis(CATTool.LOGOPORT, null);

        logoportExpected.add(Unit.WORD, MatchType.TRANSLATED, BigDecimal.valueOf(0));
        logoportExpected.add(Unit.WORD, MatchType.CONTEXT_MATCH, BigDecimal.valueOf(2));
        logoportExpected.add(Unit.WORD, MatchType.REPETITIONS, BigDecimal.valueOf(630));
        logoportExpected.add(Unit.WORD, MatchType.FORMAT_CHANGE, new BigDecimal(48));
        logoportExpected.add(Unit.WORD, MatchType.PERCENT_100, BigDecimal.valueOf(3453));
        logoportExpected.add(Unit.WORD, MatchType.PERCENT_95_99, BigDecimal.valueOf(1091));
        logoportExpected.add(Unit.WORD, MatchType.PERCENT_85_94, BigDecimal.valueOf(1793));
        logoportExpected.add(Unit.WORD, MatchType.PERCENT_75_84, BigDecimal.valueOf(1068));
        logoportExpected.add(Unit.WORD, MatchType.PERCENT_50_74, BigDecimal.valueOf(1999));
        logoportExpected.add(Unit.WORD, MatchType.NO_MATCH, BigDecimal.valueOf(2432));
        logoportExpected.add(Unit.WORD, MatchType.TOTAL, BigDecimal.valueOf(12516));

        logoportExpected.add(Unit.SEGMENT, MatchType.TRANSLATED, BigDecimal.valueOf(0));
        logoportExpected.add(Unit.SEGMENT, MatchType.CONTEXT_MATCH, BigDecimal.valueOf(1));
        logoportExpected.add(Unit.SEGMENT, MatchType.REPETITIONS, BigDecimal.valueOf(156));
        logoportExpected.add(Unit.SEGMENT, MatchType.FORMAT_CHANGE, new BigDecimal(4));
        logoportExpected.add(Unit.SEGMENT, MatchType.PERCENT_100, BigDecimal.valueOf(814));
        logoportExpected.add(Unit.SEGMENT, MatchType.PERCENT_95_99, BigDecimal.valueOf(93));
        logoportExpected.add(Unit.SEGMENT, MatchType.PERCENT_85_94, BigDecimal.valueOf(184));
        logoportExpected.add(Unit.SEGMENT, MatchType.PERCENT_75_84, BigDecimal.valueOf(118));
        logoportExpected.add(Unit.SEGMENT, MatchType.PERCENT_50_74, BigDecimal.valueOf(261));
        logoportExpected.add(Unit.SEGMENT, MatchType.NO_MATCH, BigDecimal.valueOf(330));
        logoportExpected.add(Unit.SEGMENT, MatchType.TOTAL, BigDecimal.valueOf(1961));
		logoportExpected.setCharsPerWord(new BigDecimal("4.65"));


        CATAnalysis memSourceExpected1 = new CATAnalysis(CATTool.LOGOPORT, null);
        memSourceExpected1.add(Unit.WORD, MatchType.TRANSLATED, BigDecimal.valueOf(0));
        memSourceExpected1.add(Unit.WORD, MatchType.CONTEXT_MATCH, BigDecimal.valueOf(0));
        memSourceExpected1.add(Unit.WORD, MatchType.REPETITIONS, BigDecimal.valueOf(832));
        memSourceExpected1.add(Unit.WORD, MatchType.FORMAT_CHANGE, new BigDecimal(0));
        memSourceExpected1.add(Unit.WORD, MatchType.PERCENT_100, BigDecimal.valueOf(0));
        memSourceExpected1.add(Unit.WORD, MatchType.PERCENT_95_99, BigDecimal.valueOf(0));
        memSourceExpected1.add(Unit.WORD, MatchType.PERCENT_85_94, BigDecimal.valueOf(0));
        memSourceExpected1.add(Unit.WORD, MatchType.PERCENT_75_84, BigDecimal.valueOf(0));
        memSourceExpected1.add(Unit.WORD, MatchType.PERCENT_50_74, BigDecimal.valueOf(0));
        memSourceExpected1.add(Unit.WORD, MatchType.NO_MATCH, BigDecimal.valueOf(9981));
        memSourceExpected1.add(Unit.WORD, MatchType.TOTAL, BigDecimal.valueOf(10813));

        memSourceExpected1.add(Unit.SEGMENT, MatchType.TRANSLATED, BigDecimal.valueOf(0));
        memSourceExpected1.add(Unit.SEGMENT, MatchType.CONTEXT_MATCH, BigDecimal.valueOf(0));
        memSourceExpected1.add(Unit.SEGMENT, MatchType.REPETITIONS, BigDecimal.valueOf(113));
        memSourceExpected1.add(Unit.SEGMENT, MatchType.FORMAT_CHANGE, new BigDecimal(0));
        memSourceExpected1.add(Unit.SEGMENT, MatchType.PERCENT_100, BigDecimal.valueOf(0));
        memSourceExpected1.add(Unit.SEGMENT, MatchType.PERCENT_95_99, BigDecimal.valueOf(0));
        memSourceExpected1.add(Unit.SEGMENT, MatchType.PERCENT_85_94, BigDecimal.valueOf(0));
        memSourceExpected1.add(Unit.SEGMENT, MatchType.PERCENT_75_84, BigDecimal.valueOf(0));
        memSourceExpected1.add(Unit.SEGMENT, MatchType.PERCENT_50_74, BigDecimal.valueOf(0));
        memSourceExpected1.add(Unit.SEGMENT, MatchType.NO_MATCH, BigDecimal.valueOf(419));
        memSourceExpected1.add(Unit.SEGMENT, MatchType.TOTAL, BigDecimal.valueOf(532));
		memSourceExpected1.setCharsPerWord(new BigDecimal("6.76"));

        CATAnalysis memSourceExpected2 = new CATAnalysis(CATTool.LOGOPORT, null);
        memSourceExpected2.add(Unit.WORD, MatchType.TRANSLATED, BigDecimal.valueOf(0));
        memSourceExpected2.add(Unit.WORD, MatchType.CONTEXT_MATCH, BigDecimal.valueOf(0));
        memSourceExpected2.add(Unit.WORD, MatchType.REPETITIONS, BigDecimal.valueOf(0));
        memSourceExpected2.add(Unit.WORD, MatchType.FORMAT_CHANGE, new BigDecimal(0));
        memSourceExpected2.add(Unit.WORD, MatchType.PERCENT_100, BigDecimal.valueOf(1));
        memSourceExpected2.add(Unit.WORD, MatchType.PERCENT_95_99, BigDecimal.valueOf(1));
        memSourceExpected2.add(Unit.WORD, MatchType.PERCENT_85_94, BigDecimal.valueOf(0));
        memSourceExpected2.add(Unit.WORD, MatchType.PERCENT_75_84, BigDecimal.valueOf(0));
        memSourceExpected2.add(Unit.WORD, MatchType.PERCENT_50_74, BigDecimal.valueOf(0));
        memSourceExpected2.add(Unit.WORD, MatchType.NO_MATCH, BigDecimal.valueOf(10));
        memSourceExpected2.add(Unit.WORD, MatchType.TOTAL, BigDecimal.valueOf(12));

        memSourceExpected2.add(Unit.SEGMENT, MatchType.TRANSLATED, BigDecimal.valueOf(0));
        memSourceExpected2.add(Unit.SEGMENT, MatchType.CONTEXT_MATCH, BigDecimal.valueOf(0));
        memSourceExpected2.add(Unit.SEGMENT, MatchType.REPETITIONS, BigDecimal.valueOf(0));
        memSourceExpected2.add(Unit.SEGMENT, MatchType.FORMAT_CHANGE, new BigDecimal(0));
        memSourceExpected2.add(Unit.SEGMENT, MatchType.PERCENT_100, BigDecimal.valueOf(1));
        memSourceExpected2.add(Unit.SEGMENT, MatchType.PERCENT_95_99, BigDecimal.valueOf(1));
        memSourceExpected2.add(Unit.SEGMENT, MatchType.PERCENT_85_94, BigDecimal.valueOf(0));
        memSourceExpected2.add(Unit.SEGMENT, MatchType.PERCENT_75_84, BigDecimal.valueOf(0));
        memSourceExpected2.add(Unit.SEGMENT, MatchType.PERCENT_50_74, BigDecimal.valueOf(0));
        memSourceExpected2.add(Unit.SEGMENT, MatchType.NO_MATCH, BigDecimal.valueOf(3));
        memSourceExpected2.add(Unit.SEGMENT, MatchType.TOTAL, BigDecimal.valueOf(5));
        memSourceExpected2.setCharsPerWord(new BigDecimal("3.33"));

        return new Object[][]{
                           new Object[]{"/logoport/logoport.log", logoportExpected},
                           new Object[]{"/logoport/memsource-monolingual.log", memSourceExpected1},
                           new Object[]{"/logoport/124448_Memsource+Analysis+LOG.log", memSourceExpected2},
                       };
    }

    @Test(expectedExceptions = CATAnalysisParserNotSupportedMultilingualAnalysisException.class)
    public void multilingualAnalysisTest1() throws CATAnalysisParserException {
        getResult("/logoport/memsource-multilingual.log");
    }

    @Test(expectedExceptions = CATAnalysisParserNotSupportedMultilingualAnalysisException.class)
    public void multilingualAnalysisTest2() throws CATAnalysisParserException {
        getResult("/logoport/124445_Memsource+Analysis+Multilingual+LOG.log");
    }
}