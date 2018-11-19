package eu.xtrf.cat.parser.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michal Krzywucki <krzywucki@gmail.com>
 * @Date 2014-06-09 10:07
 */
abstract  public class CATAnalysisLocalizedSDLTMSParser extends CATAnalysisParserBase {

    static enum LanguageKey {
          TOTAL,
          TOTALS,
          CONTEXT_MATCH,
          REPETITIONS,
          CROSS_FILE_REPETITIONS,
          PERFECT_MATCH,
          NEW,
          REPORT_INTERNAL_FUZZY_MATCH_LEVERAGE,
          CHARS_PER_WORD_STRING,
          YES,
          PERCENT_100,
          LOCKED_SEGMENTS,
          WORDS, 
      	  ADAPTIVE_MT_BASELINE,
      	  ADAPTIVE_MT_WITH_LEARNINGS
      }

    static Map<LanguageKey, String> ENGLISH = new HashMap<LanguageKey, String>() {

        private static final long serialVersionUID = 1L;

        {
            put(LanguageKey.TOTAL, "Total");
            put(LanguageKey.TOTALS, "Totals");
            put(LanguageKey.CONTEXT_MATCH, "Context Match");
            put(LanguageKey.REPETITIONS, "Repetitions");
            put(LanguageKey.CROSS_FILE_REPETITIONS, "Cross-file Repetitions");
            put(LanguageKey.PERFECT_MATCH, "PerfectMatch");
            put(LanguageKey.NEW, "New");
            put(LanguageKey.REPORT_INTERNAL_FUZZY_MATCH_LEVERAGE, "Report Internal Fuzzy Match Leverage:");
            put(LanguageKey.CHARS_PER_WORD_STRING, "Chars/Word");
            put(LanguageKey.YES, "Yes");
            put(LanguageKey.PERCENT_100, "100%");
            put(LanguageKey.LOCKED_SEGMENTS, "Locked");
	        put(LanguageKey.WORDS, "Words");
            put(LanguageKey.ADAPTIVE_MT_BASELINE, "AdaptiveMT Baseline");
	        put(LanguageKey.ADAPTIVE_MT_WITH_LEARNINGS, "AdaptiveMT with Learnings");
        }
    };
    
    static Map<LanguageKey, String> ENGLISH_2017 = new HashMap<LanguageKey, String>(ENGLISH) {

        private static final long serialVersionUID = 1L;

        {
            put(LanguageKey.NEW, "New/AT");
        }
    };

    static Map<LanguageKey, String> GERMAN = new HashMap<LanguageKey, String>() {

    		private static final long serialVersionUID = 1L;
    		{
    			put(LanguageKey.TOTAL, "Gesamt");
    			put(LanguageKey.TOTALS, "Gesamtüberblick");
    			put(LanguageKey.CONTEXT_MATCH, "Kontext-Match");
    			put(LanguageKey.REPETITIONS, "Wiederholungen");
    			put(LanguageKey.CROSS_FILE_REPETITIONS, "Dateiübergreifende Wiederholungen");
    			put(LanguageKey.PERFECT_MATCH, "PerfectMatch");
    			put(LanguageKey.NEW, "Neu");
    			put(LanguageKey.REPORT_INTERNAL_FUZZY_MATCH_LEVERAGE, "Interne Fuzzy-Match-Übereinstimmungen berücksichtigen:");
    			put(LanguageKey.CHARS_PER_WORD_STRING, "Zeichen/Wort");
    			put(LanguageKey.YES, "Ja");
    			put(LanguageKey.PERCENT_100, "100%");
                put(LanguageKey.LOCKED_SEGMENTS, "Gesperrt");
			    put(LanguageKey.WORDS, "Wörter");
	            put(LanguageKey.ADAPTIVE_MT_BASELINE, "AdaptiveMT-Baseline");
		        put(LanguageKey.ADAPTIVE_MT_WITH_LEARNINGS, "AdaptiveMT mit Lerneffekten");			    
    		}
    	};
    	
        static Map<LanguageKey, String> GERMAN_2017 = new HashMap<LanguageKey, String>(GERMAN) {

            private static final long serialVersionUID = 1L;

            {
                put(LanguageKey.NEW, "Neu/MÜ");
            }
        };

    	static Map<LanguageKey, String> FRENCH = new HashMap<LanguageKey, String>() {

    		private static final long serialVersionUID = 1L;
    		{
    			put(LanguageKey.TOTAL, "Total");
    			put(LanguageKey.TOTALS, "Totaux");
    			put(LanguageKey.CONTEXT_MATCH, "Corres. Cont");
    			put(LanguageKey.REPETITIONS, "Répétitions");
    			put(LanguageKey.CROSS_FILE_REPETITIONS, "Répétitions entre fichiers");
    			put(LanguageKey.PERFECT_MATCH, "PerfectMatch");
    			put(LanguageKey.NEW, "Nouveau");
    			put(LanguageKey.REPORT_INTERNAL_FUZZY_MATCH_LEVERAGE, "Indiquer les niveaux de recyclage d'analogies internes :");
    			put(LanguageKey.CHARS_PER_WORD_STRING, "Caractères/mot ");
    			put(LanguageKey.YES, "Oui");
    			put(LanguageKey.PERCENT_100, "100 %");
			    put(LanguageKey.WORDS, "Mots");
	            put(LanguageKey.ADAPTIVE_MT_BASELINE, "Référence AdaptiveMT");
		        put(LanguageKey.ADAPTIVE_MT_WITH_LEARNINGS, "AdaptiveMT avec apprentissages");			
    		}
    	};

	static Map<LanguageKey, String> FRENCH_2014 = new HashMap<LanguageKey, String>(FRENCH) {
		private static final long serialVersionUID = 1L;
		{
			put(LanguageKey.CONTEXT_MATCH, "Correspondance de contexte");
			put(LanguageKey.LOCKED_SEGMENTS, "Verrouillé");
			put(LanguageKey.PERCENT_100, "100%");
		}
	};
	
    static Map<LanguageKey, String> FRENCH_2017 = new HashMap<LanguageKey, String>(FRENCH_2014) {

        private static final long serialVersionUID = 1L;

        {
            put(LanguageKey.NEW, "Nouveau/AT");
        }
    };

    	static Map<LanguageKey, String> SPANISH = new HashMap<LanguageKey, String>() {

    		private static final long serialVersionUID = 1L;
    		{
    			put(LanguageKey.TOTAL, "Total");
    			put(LanguageKey.TOTALS, "Totales");
    			put(LanguageKey.CONTEXT_MATCH, "Coincidencia de contexto");
    			put(LanguageKey.REPETITIONS, "Repeticiones");
    			put(LanguageKey.CROSS_FILE_REPETITIONS, "Repeticiones entre archivos");
    			put(LanguageKey.PERFECT_MATCH, "PerfectMatch");
    			put(LanguageKey.NEW, "Nuevos");
    			put(LanguageKey.REPORT_INTERNAL_FUZZY_MATCH_LEVERAGE, "Informar del aprovechamiento de coincidencias parciales internas:");
    			put(LanguageKey.CHARS_PER_WORD_STRING, "Caracteres/palabra");
    			put(LanguageKey.YES, "Si");
    			put(LanguageKey.PERCENT_100, "100%");
                put(LanguageKey.LOCKED_SEGMENTS, "Bloqueado");
			    put(LanguageKey.WORDS, "Palabras");
	            put(LanguageKey.ADAPTIVE_MT_BASELINE, "Motor básico de AdaptiveMT");
		        put(LanguageKey.ADAPTIVE_MT_WITH_LEARNINGS, "AdaptiveMT con aprendizaje");
    		}
    	};
    	
        static Map<LanguageKey, String> SPANISH_2017 = new HashMap<LanguageKey, String>(SPANISH) {

            private static final long serialVersionUID = 1L;

            {
                put(LanguageKey.NEW, "Nuevas/AT");
            }
        };

    	static Map<LanguageKey, String> JAPANESE = new HashMap<LanguageKey, String>() {

    		private static final long serialVersionUID = 1L;
    		{
    			put(LanguageKey.TOTAL, "合計");
    			put(LanguageKey.TOTALS, "総計");
    			put(LanguageKey.CONTEXT_MATCH, "コンテキスト一致");
    			put(LanguageKey.REPETITIONS, "繰り返し");
    			put(LanguageKey.CROSS_FILE_REPETITIONS, "クロスファイルの繰り返し");
    			put(LanguageKey.PERFECT_MATCH, "完全一致");
    			put(LanguageKey.NEW, "新規作成");
    			put(LanguageKey.REPORT_INTERNAL_FUZZY_MATCH_LEVERAGE, "内部あいまい一致の活用のレポート:");
    			put(LanguageKey.CHARS_PER_WORD_STRING, "文字数/単語数");
    			put(LanguageKey.YES, "はい");
    			put(LanguageKey.PERCENT_100, "100 ％");
			    put(LanguageKey.WORDS, "単語数");
	            put(LanguageKey.ADAPTIVE_MT_BASELINE, "AdaptiveMT のベースライン");
		        put(LanguageKey.ADAPTIVE_MT_WITH_LEARNINGS, "学習機能を搭載した AdaptiveMT");
    		}
    	};

	static Map<LanguageKey, String> JAPANESE_2014 = new HashMap<LanguageKey, String>(JAPANESE) {
		private static final long serialVersionUID = 1L;
		{
			put(LanguageKey.CROSS_FILE_REPETITIONS, "ファイル間の繰り返し");
			put(LanguageKey.PERCENT_100, "100%");
			put(LanguageKey.LOCKED_SEGMENTS, "ロック済み");
		}
	};
	
    static Map<LanguageKey, String> JAPANESE_2017 = new HashMap<LanguageKey, String>(JAPANESE_2014) {

        private static final long serialVersionUID = 1L;

        {
            put(LanguageKey.NEW, "新規/AT");
            put(LanguageKey.REPORT_INTERNAL_FUZZY_MATCH_LEVERAGE, "内部あいまい一致の活用内容をレポートする:");
        }
    };
	
    static Map<LanguageKey, String> CHINESE = new HashMap<LanguageKey, String>() {

    	private static final long serialVersionUID = 1L;
    	{
    		put(LanguageKey.TOTAL, "总计");
    		put(LanguageKey.TOTALS, "总计");
    		put(LanguageKey.CONTEXT_MATCH, "上下文匹配");
    		put(LanguageKey.REPETITIONS, "重复");
    		put(LanguageKey.CROSS_FILE_REPETITIONS, "交叉文件重复");
    		put(LanguageKey.PERFECT_MATCH, "PerfectMatch");
    		put(LanguageKey.NEW, "新建");
    		put(LanguageKey.REPORT_INTERNAL_FUZZY_MATCH_LEVERAGE, "报告内部模糊匹配利用情况:");
    		put(LanguageKey.CHARS_PER_WORD_STRING, "字符/单词");
    		put(LanguageKey.YES, "是");
    		put(LanguageKey.PERCENT_100, "100%");
			put(LanguageKey.WORDS, "字数");
	        put(LanguageKey.ADAPTIVE_MT_BASELINE, "AdaptiveMT 基准");
		    put(LanguageKey.ADAPTIVE_MT_WITH_LEARNINGS, "含学习的 AdaptiveMT");
    	}
    };

	static Map<LanguageKey, String> CHINESE_2014 = new HashMap<LanguageKey, String>(CHINESE) {
		private static final long serialVersionUID = 1L;
		{
			put(LanguageKey.REPORT_INTERNAL_FUZZY_MATCH_LEVERAGE, "报告内部模糊匹配利用情况：");
			put(LanguageKey.NEW, "新字");
			put(LanguageKey.LOCKED_SEGMENTS, "已锁定");
		}
	};
	
    static Map<LanguageKey, String> CHINESE_2017 = new HashMap<LanguageKey, String>(CHINESE_2014) {

        private static final long serialVersionUID = 1L;

        {
            put(LanguageKey.NEW, "新建/AT");
        }
    };

	static Map<LanguageKey, String> ITALIAN = new HashMap<LanguageKey, String>() {

		private static final long serialVersionUID = 1L;

		{
			put(LanguageKey.TOTAL, "Totale");
			put(LanguageKey.TOTALS, "Totali");
			put(LanguageKey.CONTEXT_MATCH, "Context Match");
			put(LanguageKey.REPETITIONS, "Ripetizioni");
			put(LanguageKey.CROSS_FILE_REPETITIONS, "Ripetizioni nei file");
			put(LanguageKey.PERFECT_MATCH, "PerfectMatch");
			put(LanguageKey.NEW, "Nuovo");
			put(LanguageKey.REPORT_INTERNAL_FUZZY_MATCH_LEVERAGE, "Includi leverage dei fuzzy match interni:");
			put(LanguageKey.CHARS_PER_WORD_STRING, "Caratteri/parola");
			put(LanguageKey.YES, "Sì");
			put(LanguageKey.PERCENT_100, "100%");
			put(LanguageKey.WORDS, "Parole");
			put(LanguageKey.LOCKED_SEGMENTS, "Bloccati");
            put(LanguageKey.ADAPTIVE_MT_BASELINE, "Baseline AdaptiveMT");
	        put(LanguageKey.ADAPTIVE_MT_WITH_LEARNINGS, "AdaptiveMT con apprendimenti");
		}
	};
	
    static Map<LanguageKey, String> ITALIAN_2017 = new HashMap<LanguageKey, String>(ITALIAN) {

        private static final long serialVersionUID = 1L;

        {
            put(LanguageKey.NEW, "Nuovo/AT");
        }
    };

	static Map<LanguageKey, String> RUSSIAN = new HashMap<LanguageKey, String>() {

		private static final long serialVersionUID = 1L;

		{
			put(LanguageKey.TOTAL, "Итого");
			put(LanguageKey.TOTALS, "Всего");
			put(LanguageKey.CONTEXT_MATCH, "Контекстное совпадение");
			put(LanguageKey.REPETITIONS, "Повторы");
			put(LanguageKey.CROSS_FILE_REPETITIONS, "Повторы между файлами");
			put(LanguageKey.PERFECT_MATCH, "PerfectMatch");
			put(LanguageKey.NEW, "Создать");
			put(LanguageKey.REPORT_INTERNAL_FUZZY_MATCH_LEVERAGE, "Учитывать неточные совпадения между файлами:");
			put(LanguageKey.CHARS_PER_WORD_STRING, "Симв./слово");
			put(LanguageKey.YES, "Да");
			put(LanguageKey.PERCENT_100, "100%");
			put(LanguageKey.WORDS, "Слова");
			put(LanguageKey.LOCKED_SEGMENTS, "Заблокировано");
            put(LanguageKey.ADAPTIVE_MT_BASELINE, "Базовые показатели AdaptiveMT");
	        put(LanguageKey.ADAPTIVE_MT_WITH_LEARNINGS, "AdaptiveMT с функцией обучения");
		}
	};
	
    static Map<LanguageKey, String> RUSSIAN_2017 = new HashMap<LanguageKey, String>(RUSSIAN) {

        private static final long serialVersionUID = 1L;

        {
        	put(LanguageKey.PERFECT_MATCH, "Совпадения PerfectMatch");
        	put(LanguageKey.CONTEXT_MATCH, "Контекстные совпадения");
            put(LanguageKey.NEW, "Новые ед./МП");
        }
    };

	protected Map<LanguageKey, String> languageKeys;

    public CATAnalysisLocalizedSDLTMSParser(Map<LanguageKey, String> languageKeys) {
        this.languageKeys = languageKeys;
    }

    protected String getLocalization(LanguageKey languageKey) {
        return languageKeys.get(languageKey);
    }


}
