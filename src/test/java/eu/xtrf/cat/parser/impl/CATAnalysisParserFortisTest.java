package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.Unit.CHARACTER;
import static eu.xtrf.cat.Unit.SEGMENT;
import static eu.xtrf.cat.Unit.WORD;

import org.testng.annotations.DataProvider;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;

/**
 * @author Michal Krzywucki <krzywucki@gmail.com>
 * @Date 03.06.14 15:58
 */
public class CATAnalysisParserFortisTest extends CATAnalysisParserTest<CATAnalysisParserFortis> {

    public CATAnalysisParserFortisTest() {
        super(CATAnalysisParserFortis.class);
    }

    @DataProvider(name = "files")
    public Object[][] getFiles() {
        CATAnalysis fortisRuResult = new CATAnalysis(CATTool.FORTIS, null);
        CATAnalysis fortisImportResult = new CATAnalysis(CATTool.FORTIS, null);

        fortisRuResult
                .set(SEGMENT, MatchType.PARTIALLY_TRANSLATED, 44)
                .set(WORD, MatchType.PARTIALLY_TRANSLATED, 998)
                .set(CHARACTER, MatchType.PARTIALLY_TRANSLATED, 4934);
        fortisRuResult
                .set(SEGMENT, MatchType.PRETRANSLATED, 263)
                .set(WORD, MatchType.PRETRANSLATED, 4224)
                .set(CHARACTER, MatchType.PRETRANSLATED, 22417);
        fortisRuResult
                .set(SEGMENT, MatchType.NOT_TRANSLATED, 172)
                .set(WORD, MatchType.NOT_TRANSLATED, 4170)
                .set(CHARACTER, MatchType.NOT_TRANSLATED, 20701);
        fortisRuResult
                .set(SEGMENT, MatchType.TOTAL, 479)
                .set(WORD, MatchType.TOTAL, 9392)
                .set(CHARACTER, MatchType.TOTAL, 48052);



        fortisImportResult
                .set(SEGMENT, MatchType.PRETRANSLATED, 960)
                .set(WORD, MatchType.PRETRANSLATED, 12451)
                .set(CHARACTER, MatchType.PRETRANSLATED, 66898);


        fortisImportResult
                .set(SEGMENT, MatchType.PARTIALLY_TRANSLATED, 38)
                .set(WORD, MatchType.PARTIALLY_TRANSLATED, 181)
                .set(CHARACTER, MatchType.PARTIALLY_TRANSLATED, 806);

        fortisImportResult
                .set(SEGMENT, MatchType.NOT_TRANSLATED, 246)
                .set(WORD, MatchType.NOT_TRANSLATED, 4285)
                .set(CHARACTER, MatchType.NOT_TRANSLATED, 23964);
        fortisImportResult
                .set(SEGMENT, MatchType.TOTAL, 1244)
                .set(WORD, MatchType.TOTAL, 16917)
                .set(CHARACTER, MatchType.TOTAL, 91668);


        return new Object[][]{
                new Object[]{"/fortis/Fortis ru.log", fortisRuResult},
                new Object[]{"/fortis/Fortis import.log", fortisImportResult}
        };
    }
}
