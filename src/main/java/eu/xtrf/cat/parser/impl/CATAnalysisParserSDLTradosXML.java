package eu.xtrf.cat.parser.impl;

import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;

/**
 * Parser for analyzes in XML format generated by SDL Trados 2009 or 2011
 * 
 * @author Lukasz Wiktor
 * 
 */
public class CATAnalysisParserSDLTradosXML extends CATAnalysisParserBase {

	private static String SEGMENTS = "segments=\"";
	private static String WORDS = "words=\"";
	private static String CHARACTERS = "characters=\"";
	private static String TOKENS = "placeables=\"";

	@Override
	public CATTool getCATTool() {
		return CATTool.SDL_TRADOS;
	}
	
	@Override
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);

		Scanner sc = new Scanner(is, "UTF-8");
		sc.useLocale(Locale.US);
		parseBlock(result, sc);

		return result;
	}

	private void parseBlock(CATAnalysis result, Scanner sc) throws CATAnalysisParserException {
		sc.findWithinHorizon("reportInternalFuzzyLeverage", 0);
		String s = sc.next();
		boolean fuzzyMatch = s.contains("yes");
		sc.findWithinHorizon("<batchTotal>", 0);

		setData(result, sc, "perfect", MatchType.PERFECT_MATCH);
		setData(result, sc, "inContextExact", MatchType.CONTEXT_MATCH);
		setData(result, sc, "exact", MatchType.PERCENT_100);

		setOptionalData(result, sc, "locked", MatchType.LOCKED_SEGMENTS);
		setOptionalData(result, sc, "crossFileRepeated", MatchType.CROSS_FILE_REPETITIONS);

		setData(result, sc, "repeated", MatchType.REPETITIONS);
		setData(result, sc, "total", MatchType.TOTAL);
		setData(result, sc, "new", MatchType.NEW);
		
		setOptionalData(result, sc, "newBaseline", MatchType.ADAPTIVE_MT_BASELINE);
		setOptionalData(result, sc, "newLearnings", MatchType.ADAPTIVE_MT_WITH_LEARNINGS);
		
		setData(result, sc, "fuzzy min=\"50\" max=\"74\"", MatchType.PERCENT_50_74);
		setData(result, sc, "fuzzy min=\"75\" max=\"84\"", MatchType.PERCENT_75_84);
		setData(result, sc, "fuzzy min=\"85\" max=\"94\"", MatchType.PERCENT_85_94);
		setData(result, sc, "fuzzy min=\"95\" max=\"99\"", MatchType.PERCENT_95_99);

		if (fuzzyMatch) {
			setData(result, sc, "internalFuzzy min=\"50\" max=\"74\"", MatchType.INTERNAL_50_74);
			setData(result, sc, "internalFuzzy min=\"75\" max=\"84\"", MatchType.INTERNAL_75_84);
			setData(result, sc, "internalFuzzy min=\"85\" max=\"94\"", MatchType.INTERNAL_85_94);
			setData(result, sc, "internalFuzzy min=\"95\" max=\"99\"", MatchType.INTERNAL_95_99);
		}

		result.verifyDataIntegrity();
	}

	protected void setData(CATAnalysis result, Scanner sc, String firstCol, MatchType index) {
		sc.findWithinHorizon(firstCol, 0);
		sc.findWithinHorizon(SEGMENTS, 0);
		result.set(Unit.SEGMENT, index, getInt(sc));
		if (sc.findWithinHorizon(WORDS, 0) != null) {
			//words are not present when analyzing japanese
			result.set(Unit.WORD, index, getInt(sc));
		}
		sc.findWithinHorizon(CHARACTERS, 0);
		result.set(Unit.CHARACTER, index, getInt(sc));
		sc.findWithinHorizon(TOKENS, 0);
		result.set(Unit.TOKEN, index, getInt(sc));
	}

	private int getInt(Scanner sc) {
		String s = sc.next();
		s = s.substring(0, s.indexOf("\""));
		int result = Integer.parseInt(s);
		return result;
	}

	/**
	 * Sets optional data. Override if needed.
	 */
	private void setOptionalData(CATAnalysis result, Scanner sc, String firstCol, MatchType index) {
		if (sc.findWithinHorizon(firstCol, 0) != null) {
			sc.findWithinHorizon(SEGMENTS, 0);
			result.set(Unit.SEGMENT, index, getInt(sc));
			if (sc.findWithinHorizon(WORDS, 0) != null) {
				//words are not present when analyzing japanese
				result.set(Unit.WORD, index, getInt(sc));
			}
			sc.findWithinHorizon(CHARACTERS, 0);
			result.set(Unit.CHARACTER, index, getInt(sc));
			sc.findWithinHorizon(TOKENS, 0);
			result.set(Unit.TOKEN, index, getInt(sc));
		}
	}
}