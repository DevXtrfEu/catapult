package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.parser.CATAnalysisParserException;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

import static eu.xtrf.cat.Unit.*;

/**
 * @author Michal Krzywucki <krzywucki@gmail.com>
 * @Date 03.06.14 15:26
 */
public class CATAnalysisParserFortis extends CATAnalysisParserBase {

    @Override
    public CATTool getCATTool() {
        return CATTool.FORTIS;
    }

    @Override
    public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
        CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
        Scanner sc = new Scanner(is, "UTF-8");
        sc.useLocale(Locale.US);

        sc.findWithinHorizon("Summary Statistics", 0);

        parseLine(result, sc, "Pretranslated", MatchType.PRETRANSLATED);
        parseLine(result, sc, "Part. Translated", MatchType.PARTIALLY_TRANSLATED);
        parseLine(result, sc, "Not Translated", MatchType.NOT_TRANSLATED);
        parseLine(result, sc, "Total", MatchType.TOTAL);

        result.verifyDataIntegrity();

        return result;

    }

    protected void parseLine(CATAnalysis result, Scanner sc, String firstCol, MatchType matchType) {
        sc.findWithinHorizon(firstCol, 0);
        result.set(SEGMENT, matchType, new BigDecimal(sc.nextInt()));
        result.set(WORD, matchType, new BigDecimal(sc.nextInt()));
        result.set(CHARACTER, matchType, new BigDecimal(sc.nextInt()));
        sc.next();
    }
}
