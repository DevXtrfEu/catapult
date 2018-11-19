package eu.xtrf.cat.parser.impl;

import org.testng.annotations.DataProvider;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;

/**
 * @author Michal Krzywucki <krzywucki@gmail.com>
 * @Date 04.06.14 13:42
 */
public class CATAnalysisParserIdiomWorkbenchTest extends CATAnalysisParserTest<CATAnalysisParserIdiomWorkbench> {

    public CATAnalysisParserIdiomWorkbenchTest() {
        super(CATAnalysisParserIdiomWorkbench.class);
    }

    @DataProvider(name = "files")
    public Object[][] getFiles() {
        CATAnalysis expected = new CATAnalysis(CATTool.IDIOM_WORKBENCH, null);

        expected.set(Unit.WORD, MatchType.TOTAL, 1590);
        expected.set(Unit.WORD, MatchType.ICE_MATCH, 0);
        expected.set(Unit.WORD, MatchType.PERCENT_100, 35);
        expected.set(Unit.WORD, MatchType.PERCENT_95_100, 5);
        expected.set(Unit.WORD, MatchType.PERCENT_85_95, 0);
        expected.set(Unit.WORD, MatchType.PERCENT_75_85, 28);
        expected.set(Unit.WORD, MatchType.PERCENT_50_75, 4);
        expected.set(Unit.WORD, MatchType.NO_MATCH, 1475);
        expected.set(Unit.WORD, MatchType.REPETITIONS, 43);

        return new Object[][]{
                    new Object[]{"/idiom/idiom_workbench.txt", expected},
                };
    }
}
