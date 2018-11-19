package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.parser.CATAnalysisParser;
import eu.xtrf.cat.parser.CATAnalysisParserException;

import java.io.InputStream;
import java.util.Scanner;

import static eu.xtrf.cat.Unit.WORD;

/**
 * @author Michal Krzywucki <krzywucki@gmail.com>
 * @Date 04.06.14 11:38
 */
public class CATAnalysisParserIdiom extends CATAnalysisParserBase {

    @Override
    public CATTool getCATTool() {
        return CATTool.IDIOM;
    }

    @Override
    public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
        CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
        Scanner scanner = new Scanner(is, "UTF-8");
        scanner.findWithinHorizon("\"\",\" Total\",", 0);

        String nextLine = scanner.nextLine();
        String[] rowValues = nextLine.split("\\,");

        //Match type naming follows idiom csv header
        result.set(WORD, MatchType.TOTAL, Integer.parseInt(rowValues[0]));
        result.set(WORD, MatchType.ICE_MATCH, Integer.parseInt(rowValues[1]));
        result.set(WORD, MatchType.PERCENT_100, Integer.parseInt(rowValues[2]));
        result.set(WORD, MatchType.PERCENT_95_100, Integer.parseInt(rowValues[3]));
        result.set(WORD, MatchType.PERCENT_85_95, Integer.parseInt(rowValues[4]));
        result.set(WORD, MatchType.PERCENT_75_85, Integer.parseInt(rowValues[5]));
        result.set(WORD, MatchType.PERCENT_50_75, Integer.parseInt(rowValues[6]));
        result.set(WORD, MatchType.PERCENT_0_50, Integer.parseInt(rowValues[7]));
        result.set(WORD, MatchType.REPETITIONS, Integer.parseInt(rowValues[8]));

        result.verifyDataIntegrity();
        return result;
    }
}
