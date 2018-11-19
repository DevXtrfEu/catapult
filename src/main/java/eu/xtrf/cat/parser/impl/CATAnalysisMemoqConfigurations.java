package eu.xtrf.cat.parser.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;

public class CATAnalysisMemoqConfigurations {

	static enum LanguageKey {
		ANALYSIS,
		ALL,
		REPETITION,
		FRAGMENTS,
		NO_MATCH;
	}

    static List<MatchType> matchTypes = Arrays.asList(
            MatchType.TOTAL,
            MatchType.REPETITIONS,
            MatchType.PERCENT_101,
            MatchType.PERCENT_100,
            MatchType.PERCENT_95_99, 
            MatchType.PERCENT_85_94,
            MatchType.PERCENT_75_84,
            MatchType.PERCENT_50_74,
            MatchType.NO_MATCH
    );
	
	static Map<LanguageKey, String> LANGUAGE_KEYS_ENGLISH = new HashMap<LanguageKey, String>() {

		private static final long serialVersionUID = 1L;
		{
			put(LanguageKey.ANALYSIS, "Analysis");
			put(LanguageKey.ALL, "All");
			put(LanguageKey.REPETITION, "Repetition");
			put(LanguageKey.FRAGMENTS, "Fragments");
			put(LanguageKey.NO_MATCH, "No match");
		}
	};

	static Map<LanguageKey, String> LANGUAGE_KEYS_GERMAN = new HashMap<LanguageKey, String>() {

		private static final long serialVersionUID = 1L;
		{
			put(LanguageKey.ANALYSIS, "Analyse"); // <=================== TODO
			put(LanguageKey.ALL, "Alle");
			put(LanguageKey.REPETITION, "Wiederholung");
			put(LanguageKey.FRAGMENTS, "Fragmente");
			put(LanguageKey.NO_MATCH, "Kein Treffer");
		}
	};

	static Map<LanguageKey, String> LANGUAGE_KEYS_POLISH_6_0 = new HashMap<LanguageKey, String>() {

		private static final long serialVersionUID = 1L;
		{
			put(LanguageKey.ANALYSIS, "Analiza");
			put(LanguageKey.ALL, "Razem");
			put(LanguageKey.REPETITION, "Powtórzenie");
			put(LanguageKey.FRAGMENTS, "Fragmenty");
			put(LanguageKey.NO_MATCH, "Brak trafienia");
		}
	};

	static Map<LanguageKey, String> LANGUAGE_KEYS_POLISH_2013 = new HashMap<LanguageKey, String>() {

		private static final long serialVersionUID = 1L;
		{
			put(LanguageKey.ANALYSIS, "Analiza");
			put(LanguageKey.ALL, "Razem");
			put(LanguageKey.REPETITION, "Powtórzenia");
			put(LanguageKey.FRAGMENTS, "Fragmenty");
			put(LanguageKey.NO_MATCH, "Brak dopasowania");
		}
	};
	
	static Map<LanguageKey, String> LANGUAGE_KEYS_SPANISH = new HashMap<LanguageKey, String>() {

		private static final long serialVersionUID = 1L;
		{
			put(LanguageKey.ANALYSIS, "Análisis");
			put(LanguageKey.ALL, "Todos");
			put(LanguageKey.REPETITION, "Pretraducido basado en documentos");
			put(LanguageKey.FRAGMENTS, "Fragmentos");
			put(LanguageKey.NO_MATCH, "Sin coincidencias");
		}
	};

    static Map<Unit, String> UNIT_KEYS_ENGLISH = new HashMap<Unit, String>() {
        
        private static final long serialVersionUID = 1L;
        {
            put(Unit.SEGMENT, "Segments");
            put(Unit.WORD, "Source words");
            put(Unit.CHARACTER, "Source chars");
            //put(Unit.TOKEN, "Source tags");  //  apparently not used right now
        }
    };

    static Map<Unit, String> UNIT_KEYS_POLISH = new HashMap<Unit, String>() {
        
        private static final long serialVersionUID = 1L;
        {
            put(Unit.SEGMENT, "Segmenty");
            put(Unit.WORD, "Słowa w tekście źródłowym");
            put(Unit.CHARACTER, "Znaki w tekście źródłowym");
            //put(Unit.TOKEN, "Source tags");  // apparently not used right now
        }
    };

    static Map<Unit, String> UNIT_KEYS_GERMAN = new HashMap<Unit, String>() {
        
        private static final long serialVersionUID = 1L;
        {
            put(Unit.SEGMENT, "Segmente");
            put(Unit.WORD, "Ausgangswörter");
            put(Unit.CHARACTER, "Ausgangszeichen");
            //put(Unit.TOKEN, "Ausgangstext-Tags");  // apparently not used right now
        }
    };

	static Collection<Map<LanguageKey, String>> ALL_LANGUAGE_KEYS = Arrays.asList(
		LANGUAGE_KEYS_ENGLISH,
		LANGUAGE_KEYS_GERMAN,
		LANGUAGE_KEYS_POLISH_6_0,
		LANGUAGE_KEYS_POLISH_2013,
		LANGUAGE_KEYS_SPANISH
	);

	static Collection<String> SUPPORTED_DELIMITERS = Arrays.asList(",",";","\t");
	static Collection<String> SUPPORTED_ENCODINGS = Arrays.asList("UTF-8", "UTF-16");
	
    static Collection<Map<Unit, String>> SUPPORTED_UNIT_KEYS = Arrays.asList(
        UNIT_KEYS_ENGLISH,
        UNIT_KEYS_POLISH,
        UNIT_KEYS_GERMAN
    ); 
}
