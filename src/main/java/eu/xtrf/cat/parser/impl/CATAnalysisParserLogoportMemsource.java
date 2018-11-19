package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;
import eu.xtrf.cat.parser.CATAnalysisParserNotSupportedMultilingualAnalysisException;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author Michal Krzywucki <krzywucki@gmail.com>
 * @Date 2014-06-04 15:06
 */
public class CATAnalysisParserLogoportMemsource extends CATAnalysisParserBase {

    @Override
    public CATTool getCATTool() {
        return CATTool.LOGOPORT;
    }

    @Override
    public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
        CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
        Scanner sc = new Scanner(is, "UTF-8");
        sc.useLocale(Locale.US);

        sc.findWithinHorizon("Total:", 0);
        sc.findWithinHorizon("Match Types", 0);
        parseBlockLine(result, sc, "Translated", MatchType.TRANSLATED);
        parseBlockLine(result, sc, "Context Match", MatchType.CONTEXT_MATCH);
        parseBlockLine(result, sc, "Repetitions", MatchType.REPETITIONS);
        parseBlockLine(result, sc, "Format Change", MatchType.FORMAT_CHANGE);
        parseBlockLine(result, sc, "100%", MatchType.PERCENT_100);
        parseBlockLine(result, sc, "95% - 99% ", MatchType.PERCENT_95_99);
        parseBlockLine(result, sc, "85% - 94% ", MatchType.PERCENT_85_94);
        parseBlockLine(result, sc, "75% - 84% ", MatchType.PERCENT_75_84);
        parseBlockLine(result, sc, "50% - 74% ", MatchType.PERCENT_50_74);
        parseBlockLine(result, sc, "No Match", MatchType.NO_MATCH);
        parseBlockLine(result, sc, "Total", MatchType.TOTAL);

		sc.findWithinHorizon("Chars/word",0);
		result.setCharsPerWord(sc.nextBigDecimal());

        result.verifyDataIntegrity();

        if (sc.findWithinHorizon("Total:", 0) != null) {
            throw new CATAnalysisParserNotSupportedMultilingualAnalysisException();
        }

        return result;
    }

    private void parseBlockLine(CATAnalysis result, final Scanner sc, final String firstCol,
                                final MatchType matchType) {
    		sc.findWithinHorizon(firstCol, 0);
            result.add(Unit.SEGMENT, matchType, new BigDecimal(sc.nextInt()));
            result.add(Unit.WORD, matchType, new BigDecimal(sc.nextInt()));
    		sc.next();
    }
}
