package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.parser.CATAnalysisParserException;
import eu.xtrf.cat.parser.CATAnalysisParserNotSupportedMultilingualAnalysisException;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static eu.xtrf.cat.Unit.WORD;

/**
 * This parser is very similar to
 */
public class CATAnalysisParserSDLWorldServer extends CATAnalysisParserBase {

    @Override
    public CATTool getCATTool() {
        return CATTool.SDL_WORLD_SERVER;
    }

    @Override
    public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
        CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
        Scanner scanner = new Scanner(is, "UTF-8");
        scanner.nextLine(); // skip first line - this is header
        Set<String> targetLocales = new HashSet<String>();
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            String[] rowValues = nextLine.split("\\,");

            if ("\"Grand Total\"".equals(rowValues[1])) {
                //Match type naming follows idiom csv header
                result.set(WORD, MatchType.TOTAL, Integer.parseInt(rowValues[2]));
                result.set(WORD, MatchType.ICE_MATCH, Integer.parseInt(rowValues[3]));
                result.set(WORD, MatchType.PERCENT_100, Integer.parseInt(rowValues[4]));
                result.set(WORD, MatchType.PERCENT_95_100, Integer.parseInt(rowValues[5]));
                result.set(WORD, MatchType.PERCENT_85_95, Integer.parseInt(rowValues[6]));
                result.set(WORD, MatchType.PERCENT_75_85, Integer.parseInt(rowValues[7]));
                result.set(WORD, MatchType.PERCENT_50_75, Integer.parseInt(rowValues[8]));
                result.set(WORD, MatchType.PERCENT_0_50, Integer.parseInt(rowValues[9]));
                result.set(WORD, MatchType.REPETITIONS, Integer.parseInt(rowValues[10]));
            } else {
                targetLocales.add(rowValues[0]);
            }

        }
        result.verifyDataIntegrity();
        if (targetLocales.size() > 1) {
            throw new CATAnalysisParserNotSupportedMultilingualAnalysisException();
        }
        return result;
    }
}
