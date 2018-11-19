package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.Unit.CHARACTER;
import static eu.xtrf.cat.Unit.SEGMENT;
import static eu.xtrf.cat.Unit.WORD;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.parser.CATAnalysisParserException;

public class CATAnalysisParserSDLX extends CATAnalysisParserBase {

	@Override
	public CATTool getCATTool() {
		return CATTool.SDLX;
	}

	@Override
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
		
		Scanner sc = new Scanner(is, "UTF-8");
		sc.useLocale(Locale.US);
		parseBlock(result, sc);
		
		return result;
	}

	protected void parseBlock(final CATAnalysis result, final Scanner sc) throws CATAnalysisParserException {
		sc.findWithinHorizon("Batch Statistics :", 0);
		parseBlockLine(result, sc, "Replicated :", MatchType.REPLICATED);
		parseBlockLine(result, sc, "100% matched :", MatchType.PERCENT_100);
		parseBlockLine(result, sc, "95% to 99% matched :", MatchType.PERCENT_95_99);
		parseBlockLine(result, sc, "85% to 94% matched :", MatchType.PERCENT_85_94);
		parseBlockLine(result, sc, "75% to 84% matched :", MatchType.PERCENT_75_84);
		parseBlockLine(result, sc, "50% to 74% matched :", MatchType.PERCENT_50_74);
		parseBlockLine(result, sc, "Untranslated :", MatchType.UNTRANSLATED);
		parseBlockLine(result, sc, "Totals :", MatchType.TOTAL);

		result.verifyDataIntegrity();
	}

	protected void parseBlockLine(final CATAnalysis result, final Scanner sc, final String firstCol, final MatchType index) throws CATAnalysisParserException {
		sc.findWithinHorizon(firstCol, 0);
		if (sc.hasNextInt()) {
			result.set(SEGMENT, index, new BigDecimal(sc.nextInt()));
		} else {
			sc.next(" *-");
			result.set(WORD, index, 0);
		}

		sc.next();
		sc.next();

		if (sc.hasNextInt()) {
			result.set(WORD, index, new BigDecimal(sc.nextInt()));
		} else {
			sc.next(" *-");
			result.set(WORD, index, 0);
		}

		sc.skip("( *\\( *[0-9]+\\.[0-9]+%\\) *)|( *)");

		if (sc.hasNextInt()) {
			result.set(CHARACTER, index, new BigDecimal(sc.nextInt()));
		} else {
			sc.next(" *-");
			result.set(CHARACTER, index, 0);
		}
		
		sc.next();
	
	}
}
