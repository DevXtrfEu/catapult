package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;

import java.io.InputStream;
import java.util.Scanner;

/**
 * @author Michal Krzywucki <krzywucki@gmail.com>
 * @Date 04.06.14 13:32
 */
public class CATAnalysisParserIdiomWorkbench extends CATAnalysisParserBase {


    @Override
    public CATTool getCATTool() {
        return CATTool.IDIOM_WORKBENCH;
    }

    @Override
    public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
        Scanner scanner = new Scanner(is, "UTF-8");
        scanner.findWithinHorizon("Total:", 0);

        CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
        result.set(Unit.WORD, MatchType.TOTAL, scanner.nextInt());
        result.set(Unit.WORD, MatchType.ICE_MATCH, scanner.nextInt());
        result.set(Unit.WORD, MatchType.PERCENT_100, scanner.nextInt());
        result.set(Unit.WORD, MatchType.PERCENT_95_100, scanner.nextInt());
        result.set(Unit.WORD, MatchType.PERCENT_85_95, scanner.nextInt());
        result.set(Unit.WORD, MatchType.PERCENT_75_85, scanner.nextInt());
        result.set(Unit.WORD, MatchType.PERCENT_50_75, scanner.nextInt());
        result.set(Unit.WORD, MatchType.NO_MATCH, scanner.nextInt());
        result.set(Unit.WORD, MatchType.REPETITIONS, scanner.nextInt());

        result.verifyDataIntegrity();
        return result;
    }
}
