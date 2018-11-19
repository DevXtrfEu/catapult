package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;
import eu.xtrf.cat.parser.CATAnalysisParserNotSupportedMultilingualAnalysisException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.regex.Pattern;


public class CATAnalysisParserPassolo2016_HTM extends CATAnalysisParserBase {

	private static Logger logger = LoggerFactory.getLogger(CATAnalysisParserPassolo2016_HTM.class);

	private static final List<String> SUPPORTED_DESCRIPTIONS = Arrays.asList(
			"total", "to be translated", "untranslated", "untranslated repetitions",
			"auto-translated", "auto-translated (100 %)", "pre-translated", "pre-translated (100 %)",
			"95% - 99%", "85% - 94%", "75% - 84%", "30% - 74%",
			"for review", "translated and validated", "change of text length");

	private final Pattern HTML_REGEX = Pattern.compile("<html>");
	private final Pattern BLOCK_REGEX = Pattern.compile("<h2>.+?</h2>.+?Statistics.+?<table.+?/table>", Pattern.DOTALL);
	private final Pattern HEADER_REGEX = Pattern.compile("<h2>.+?</h2>");
	private final Pattern MULTILANGUAGE_HEADER_REGEX = Pattern.compile("<h2>.+[ ][(].+[)]</h2>");
	private final Pattern STATISTICS_REGEX = Pattern.compile("Statistics");
	private final Pattern COLUMN_VALUE_REGEX = Pattern.compile("<td.+?</td>");
	private final Pattern HTML_TAG_REGEX = Pattern.compile("<[^<>]+>");
	private final Pattern NON_DIGIT_CHARACTERS_REGEX = Pattern.compile("[\\D]");
	private final Pattern ANY_LENGTH_WHITESPACE = Pattern.compile("\\s+");
	private final Pattern PERCENTS_IN_BRACKETS = Pattern.compile("\\(.+%\\)");

	private Map<String, Integer> descriptionsToWords = new HashMap<>();
	private Map<String, Integer> descriptionsToCharacters = new HashMap<>();

	@Override
	public CATTool getCATTool() {
		return CATTool.PASSOLO;
	}

	@Override
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		descriptionsToWords.clear();
		descriptionsToCharacters.clear();

		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);

		Scanner sc = new Scanner(is, "UTF-8");
		sc.useLocale(Locale.US);
		if (sc.findWithinHorizon(HTML_REGEX, 0) == null) {
			throw new CATAnalysisParserException("Input is not in html format.");
		}
		parse(sc);
		logParsingResult();
		mapToCatAnalysis(result);
		return result;
	}

	private void parse(Scanner sc) throws CATAnalysisParserException {
		for (String description: SUPPORTED_DESCRIPTIONS) {
			descriptionsToWords.put(description, 0);
			descriptionsToCharacters.put(description, 0);
		}

		int statisticsCounter = 0;
		int descriptionsCounter = 0;
		int multiLanguageHeadersCounter = 0;

		while (true) {
			String statistics = sc.findWithinHorizon(BLOCK_REGEX, 0);
			if (statistics == null) {
				break;
			}
			logger.trace("found statistics:\n %s", new Object[] {statistics});
			statisticsCounter++;
			Scanner scanner = new Scanner(statistics);
			String header = scanner.findWithinHorizon(HEADER_REGEX, 0);
			if (isMultiLanguageHeader(header)) {
				multiLanguageHeadersCounter++;
			}
			scanner.findWithinHorizon(STATISTICS_REGEX, 0);
			while (true) {
				String col1 = scanner.findWithinHorizon(COLUMN_VALUE_REGEX, 0);
				String col2 = scanner.findWithinHorizon(COLUMN_VALUE_REGEX, 0);
				String col3 = scanner.findWithinHorizon(COLUMN_VALUE_REGEX, 0);
				String col4 = scanner.findWithinHorizon(COLUMN_VALUE_REGEX, 0);
				String col5 = scanner.findWithinHorizon(COLUMN_VALUE_REGEX, 0);

				if (col1 == null || col2 == null || col3 == null || col4 == null || col5 == null) {
					break;
				}

				logger.trace("parsing row: %-80s %-15s %-15s %-15s %-15s", new Object[] {col1, col2, col3, col4, col5});

				descriptionsCounter++;

				String description = removeHtmlTags(col1).toLowerCase();
				int words = Integer.parseInt(extractNumber(col3));
				int characters = Integer.parseInt(extractNumber(col5));

				descriptionsToWords.merge(description, words, Integer::sum);
				descriptionsToCharacters.merge(description, characters, Integer::sum);
			}
		}

		if (statisticsCounter == 0 || descriptionsCounter == 0) {
			throw new CATAnalysisParserException();
		}
		if (multiLanguageHeadersCounter > 1) {
			throw new CATAnalysisParserNotSupportedMultilingualAnalysisException();
		}
	}

	private String removeHtmlTags(String input) {
		return input.replaceAll(HTML_TAG_REGEX.pattern(), "");
	}

	private String extractNumber(String input0) {
		String input1 = removeAllWhitespaces(input0);
		String input2 = removePercentsInBrackets(input1);
		String result = removeNonDigitCharacters(input2);
		return result;
	}

	private String removeAllWhitespaces(String input) {
		return input.replaceAll(ANY_LENGTH_WHITESPACE.pattern(), "");
	}

	private String removePercentsInBrackets(String input) {
		return input.replaceAll(PERCENTS_IN_BRACKETS.pattern(), "");
	}

	private String removeNonDigitCharacters(String input) {
		return input.replaceAll(NON_DIGIT_CHARACTERS_REGEX.pattern(), "");
	}

	public boolean isMultiLanguageHeader(String header) {
		return header.matches(MULTILANGUAGE_HEADER_REGEX.pattern());
	}

	private void logParsingResult() {
		if (logger.isTraceEnabled()) {
			StringWriter buffer = new StringWriter();
			PrintWriter bufferWriter = new PrintWriter(buffer);

			bufferWriter.println("reading result:");
			for (String description : SUPPORTED_DESCRIPTIONS) {
				Integer words = descriptionsToWords.get(description);
				Integer characters = descriptionsToCharacters.get(description);
				bufferWriter.printf("%-30s -> %6d %6d\n", description, words, characters);
			}

			logger.trace("", buffer.toString());
		}
	}

	private void mapToCatAnalysis(CATAnalysis result) throws CATAnalysisParserException {
		int toBeTranslatedWords = descriptionsToWords.get("to be translated");
		int untranslatedWords = descriptionsToWords.get("untranslated");
		int untranslatedRepetitionsWords = descriptionsToWords.get("untranslated repetitions");
		int autoTranslatedWords = descriptionsToWords.get("auto-translated");
		int autoTranslated100PercentWords = descriptionsToWords.get("auto-translated (100 %)");
		int preTranslatedWords = descriptionsToWords.get("pre-translated");
		int preTranslated100PercentWords = descriptionsToWords.get("pre-translated (100 %)");
		int _95_99_words = descriptionsToWords.get("95% - 99%");
		int _85_94_words = descriptionsToWords.get("85% - 94%");
		int _75_84_words = descriptionsToWords.get("75% - 84%");
		int _30_74_words = descriptionsToWords.get("30% - 74%");
		int forReviewWords = descriptionsToWords.get("for review");
		int translatedAndValidatedWords = descriptionsToWords.get("translated and validated");

		int toBeTranslatedCharacters = descriptionsToCharacters.get("to be translated");
		int untranslatedCharacters = descriptionsToCharacters.get("untranslated");
		int untranslatedRepetitionsCharacters = descriptionsToCharacters.get("untranslated repetitions");
		int autoTranslatedCharacters = descriptionsToCharacters.get("auto-translated");
		int autoTranslated100PercentCharacters = descriptionsToCharacters.get("auto-translated (100 %)");
		int preTranslatedCharacters = descriptionsToCharacters.get("pre-translated");
		int preTranslated100PercentCharacters = descriptionsToCharacters.get("pre-translated (100 %)");
		int _95_99_characters = descriptionsToCharacters.get("95% - 99%");
		int _85_94_characters = descriptionsToCharacters.get("85% - 94%");
		int _75_84_characters = descriptionsToCharacters.get("75% - 84%");
		int _30_74_characters = descriptionsToCharacters.get("30% - 74%");
		int forReviewCharacters = descriptionsToCharacters.get("for review");
		int translatedAndValidatedCharacters = descriptionsToCharacters.get("translated and validated");

		// verify integrity of data
		int totalWords = toBeTranslatedWords - translatedAndValidatedWords;
		int matchWords = untranslatedWords + untranslatedRepetitionsWords + autoTranslatedWords +
				autoTranslated100PercentWords + preTranslatedWords + preTranslated100PercentWords +
				_95_99_words + _85_94_words + _75_84_words + _30_74_words + forReviewWords;
		if (totalWords != matchWords) {
			throw new CATAnalysisParserException();
		}

		int totalCharacters = toBeTranslatedCharacters - translatedAndValidatedCharacters;
		int matchCharacters = untranslatedCharacters + untranslatedRepetitionsCharacters + autoTranslatedCharacters +
				autoTranslated100PercentCharacters + preTranslatedCharacters + preTranslated100PercentCharacters +
				_95_99_characters + _85_94_characters + _75_84_characters + _30_74_characters + forReviewCharacters;
		if (totalCharacters != matchCharacters) {
			throw new CATAnalysisParserException();
		}

		if (totalWords == 0 && totalCharacters == 0) {
			throw new CATAnalysisParserException();
		}

		result.add(Unit.WORD, MatchType.TOTAL, totalWords);
		result.add(Unit.WORD, MatchType.UNTRANSLATED, untranslatedWords);
		result.add(Unit.WORD, MatchType.UNTRANSLATED_REPETITIONS, untranslatedRepetitionsWords);
		result.add(Unit.WORD, MatchType.AUTO_TRANSLATED, autoTranslatedWords);
		result.add(Unit.WORD, MatchType.AUTO_TRANSLATED, autoTranslated100PercentWords);
		result.add(Unit.WORD, MatchType.AUTO_TRANSLATED, preTranslatedWords);
		result.add(Unit.WORD, MatchType.AUTO_TRANSLATED, preTranslated100PercentWords);
		result.add(Unit.WORD, MatchType.PERCENT_95_99, _95_99_words);
		result.add(Unit.WORD, MatchType.PERCENT_85_94, _85_94_words);
		result.add(Unit.WORD, MatchType.PERCENT_75_84, _75_84_words);
		result.add(Unit.WORD, MatchType.PERCENT_30_74, _30_74_words);
		result.add(Unit.WORD, MatchType.FOR_REVIEW, forReviewWords);

		result.add(Unit.CHARACTER, MatchType.TOTAL, totalCharacters);
		result.add(Unit.CHARACTER, MatchType.UNTRANSLATED, untranslatedCharacters);
		result.add(Unit.CHARACTER, MatchType.UNTRANSLATED_REPETITIONS, untranslatedRepetitionsCharacters);
		result.add(Unit.CHARACTER, MatchType.AUTO_TRANSLATED, autoTranslatedCharacters);
		result.add(Unit.CHARACTER, MatchType.AUTO_TRANSLATED, autoTranslated100PercentCharacters);
		result.add(Unit.CHARACTER, MatchType.AUTO_TRANSLATED, preTranslatedCharacters);
		result.add(Unit.CHARACTER, MatchType.AUTO_TRANSLATED, preTranslated100PercentCharacters);
		result.add(Unit.CHARACTER, MatchType.PERCENT_95_99, _95_99_characters);
		result.add(Unit.CHARACTER, MatchType.PERCENT_85_94, _85_94_characters);
		result.add(Unit.CHARACTER, MatchType.PERCENT_75_84, _75_84_characters);
		result.add(Unit.CHARACTER, MatchType.PERCENT_30_74, _30_74_characters);
		result.add(Unit.CHARACTER, MatchType.FOR_REVIEW, forReviewCharacters);
	}

}
