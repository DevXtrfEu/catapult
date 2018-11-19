package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.parser.CATAnalysisParserException;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

import static eu.xtrf.cat.Unit.SEGMENT;
import static eu.xtrf.cat.Unit.WORD;

/**
 * Parser for Trados *.log files
 * 
 * @author Lukasz Wiktor
 */
public class CATAnalysisParserTrados extends CATAnalysisParserBase {
	
	public CATTool getCATTool() {
		return CATTool.TRADOS;
	}
	
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
		Scanner sc = new Scanner(is, "UTF-8");
		sc.useLocale(Locale.US);
		
		sc.findWithinHorizon("Analyse Total", 0);
		sc.findWithinHorizon("File processing Total", 0);
		sc.findWithinHorizon("Match Types", 200);
		parseLine(result, sc, "(XTranslated|Context TM)", MatchType.X_TRANSLATED);
		parseLine(result, sc, "Repetitions", MatchType.REPETITIONS);
		parseLine(result, sc, "100%", MatchType.PERCENT_100);
		parseLine(result, sc, "95% - 99% ", MatchType.PERCENT_95_99);
		parseLine(result, sc, "85% - 94% ", MatchType.PERCENT_85_94);
		parseLine(result, sc, "75% - 84% ", MatchType.PERCENT_75_84);
		parseLine(result, sc, "50% - 74% ", MatchType.PERCENT_50_74);
		parseLine(result, sc, "No Match", MatchType.NO_MATCH);
		parseLine(result, sc, "Total", MatchType.TOTAL);
		
		result.verifyDataIntegrity();
		
		return result;
	}
	
	private void parseLine(CATAnalysis result, final Scanner sc, final String firstCol, final MatchType matchType) {
		sc.findWithinHorizon(firstCol, 0);
		result.set(SEGMENT, matchType, new BigDecimal(sc.nextInt()));
		result.set(WORD, matchType, new BigDecimal(sc.nextInt()));
		sc.next();
		sc.next();
	}

}
