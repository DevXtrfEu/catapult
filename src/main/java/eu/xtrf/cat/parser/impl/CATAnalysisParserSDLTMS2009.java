package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.parser.CATAnalysisParserException;

import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

import static eu.xtrf.cat.MatchType.*;
import static eu.xtrf.cat.Unit.WORD;

/**
 * Analysis of SDL TMS 2009 metrics.
 */
public class CATAnalysisParserSDLTMS2009 extends CATAnalysisParserBase {

	public CATTool getCATTool() {
		return CATTool.SDL_TRADOS;
	}
	
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
		Scanner sc = new Scanner(is);
		sc.useLocale(Locale.US);
		
		sc.findWithinHorizon("Word Count", 200);
		sc.findWithinHorizon("Total", 200);
		parseLine(result, sc, "Total");
		
		result.verifyDataIntegrity();
		
		return result;
	}

	private void parseLine(CATAnalysis result, final Scanner sc, final String firstCol) {
		sc.findWithinHorizon(firstCol, 0);
		sc.next();
		result.set(WORD, TOTAL, getSegmentIntValue(sc.next()));
		String temp = sc.next();
		result.set(WORD, REPETITIONS, getSegmentIntValue(temp));
		if (temp.contains("</tr>")) {
			String[] numbers = temp.replace(",", "").split("</td><td>");
			result.set(WORD, PERCENT_100, Integer.valueOf(numbers[1]));
			result.set(WORD, PERCENT_95_99, Integer.valueOf(numbers[2]));
			result.set(WORD, PERCENT_85_94, Integer.valueOf(numbers[3]));
			result.set(WORD, NEW, Integer.valueOf(numbers[4].split("</td>")[0]));
		} else {
			result.set(WORD, PERCENT_100, getSegmentIntValue(sc.next()));
			result.set(WORD, PERCENT_95_99, getSegmentIntValue(sc.next()));
			result.set(WORD, PERCENT_85_94, getSegmentIntValue(sc.next()));
			result.set(WORD, NEW, getSegmentIntValue(sc.next()));
		}
	}

	private Integer getSegmentIntValue(String input) {
		return Integer.valueOf(input.split("class=\"num\">")[1].split("</td>")[0].replace(",", ""));
	}

}
