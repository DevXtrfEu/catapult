package eu.xtrf.cat.parser.impl;

import org.testng.annotations.DataProvider;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;

/**
 * @author Michal Krzywucki <krzywucki@gmail.com>
 * @Date 04.06.14 11:52
 */
public class CATAnalysisParserIdiomTest extends CATAnalysisParserTest<CATAnalysisParserIdiom> {

    public CATAnalysisParserIdiomTest() {
        super(CATAnalysisParserIdiom.class);
    }

    @DataProvider(name = "files")
    public Object[][] getFiles() {
        CATAnalysis idiomResult = new CATAnalysis(CATTool.IDIOM, null);

        idiomResult.set(Unit.WORD, MatchType.TOTAL, 1186);
        idiomResult.set(Unit.WORD, MatchType.ICE_MATCH, 0);
        idiomResult.set(Unit.WORD, MatchType.PERCENT_100, 412);
        idiomResult.set(Unit.WORD, MatchType.PERCENT_95_100, 43);
        idiomResult.set(Unit.WORD, MatchType.PERCENT_85_95, 40);
        idiomResult.set(Unit.WORD, MatchType.PERCENT_75_85, 109);
        idiomResult.set(Unit.WORD, MatchType.PERCENT_50_75, 138);
        idiomResult.set(Unit.WORD, MatchType.PERCENT_0_50, 301);
        idiomResult.set(Unit.WORD, MatchType.REPETITIONS, 143);

        return new Object[][]{
            new Object[]{"/idiom/idiom.csv", idiomResult},
        };
    }

}
