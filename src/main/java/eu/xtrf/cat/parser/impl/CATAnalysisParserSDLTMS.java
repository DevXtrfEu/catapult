package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;

import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

/**
 * Parser for SDL TMS *.html files
 * 
 * @author Lukasz Wiktor
 */
public class CATAnalysisParserSDLTMS extends CATAnalysisParserBase {

	public CATTool getCATTool() {
		return CATTool.SDL_TRADOS;
	}

	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
		Scanner sc = new Scanner(is);
		sc.useLocale(Locale.US);
		
		sc.findWithinHorizon("Total", 200);
		parseLine(result, sc, "Total");
		
		result.verifyDataIntegrity();
		
		return result;
	}

	protected void parseLine(CATAnalysis result, final Scanner sc, final String firstCol) {
		sc.findWithinHorizon(firstCol, 0);
		sc.next();
		result.set(Unit.WORD, MatchType.TOTAL, getSegmentIntValue(sc.next()));
		sc.next();
		result.set(Unit.WORD, MatchType.REPETITIONS, getSegmentIntValue(sc.next()));
		result.set(Unit.WORD, MatchType.PERCENT_100, getSegmentIntValue(sc.next()));
		result.set(Unit.WORD, MatchType.PERCENT_95_99, getSegmentIntValue(sc.next()));
		result.set(Unit.WORD, MatchType.PERCENT_85_94, getSegmentIntValue(sc.next()));
		result.set(Unit.WORD, MatchType.NEW, getSegmentIntValue(sc.next()));
	}

	private Integer getSegmentIntValue(String input) {
		return Integer.valueOf(input.split("class=\"num\">")[1].split("</td>")[0].replace(",", ""));
	}

}
