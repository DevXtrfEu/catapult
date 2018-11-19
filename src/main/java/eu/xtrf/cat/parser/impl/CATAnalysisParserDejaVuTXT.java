package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author Marek Guzowski
 */
public class CATAnalysisParserDejaVuTXT extends CATAnalysisParserBase {

	private final DecimalFormat decimalFormat;

	public CATAnalysisParserDejaVuTXT() {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(',');
		decimalFormat = new DecimalFormat("0,00", symbols);
		decimalFormat.setParseBigDecimal(true);
	}

	@Override
	public CATTool getCATTool() {
		return CATTool.DEJA_VU;
	}

	@Override
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);

		Scanner sc = new Scanner(is, "UTF-8");
		sc.useLocale(Locale.US);

		try {
			parseBlock(result, sc);
			return result;
		} catch (ParseException e) {
			throw new CATAnalysisParserException(e);
		}

	}

	private void parseBlock(CATAnalysis result, Scanner sc) throws CATAnalysisParserException, ParseException {
		sc.findWithinHorizon("Chars/word:",0);
		//originally analysis uses , as decimal separator instead of ., therefore need to parse manually
		result.setCharsPerWord((BigDecimal) decimalFormat.parse(sc.next()));

		parseBlockLine(result, sc, "Duplicates", MatchType.DUPLICATES);
		parseBlockLine(result, sc, "Guaranteed Matches", MatchType.GUARANTEED);
		parseBlockLine(result, sc, "Exact Matches", MatchType.EXACT);
		parseBlockLine(result, sc, "95% - 99%", MatchType.PERCENT_95_99);
		parseBlockLine(result, sc, "85% - 94%", MatchType.PERCENT_85_94);
		parseBlockLine(result, sc, "75% - 84%", MatchType.PERCENT_75_84);
		parseBlockLine(result, sc, "50% - 74%", MatchType.PERCENT_50_74);
		parseBlockLine(result, sc, "No Match", MatchType.NO_MATCH);

		parseBlockLine(result, sc, "Total", MatchType.TOTAL);


		result.verifyDataIntegrity();
	}

	private void parseBlockLine(CATAnalysis result, final Scanner sc, final String firstCol,
								final MatchType matchType) {
		sc.findWithinHorizon(firstCol, 0);
		result.add(Unit.SEGMENT, matchType, new BigDecimal(sc.nextInt()));
		result.add(Unit.WORD, matchType, new BigDecimal(sc.nextInt()));
		result.add(Unit.CHARACTER, matchType, new BigDecimal(sc.nextInt()));
		sc.next();
	}
}
