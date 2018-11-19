package eu.xtrf.cat;

import com.radzisz.xtrf.model.DisplayableEnum;
import com.radzisz.xtrf.utils.messageResources.LocalizationUtils;

import java.util.EnumSet;
import java.util.Set;

/**
 * Set of all possible match types occurring in analyses from different CAT tools. Some of them are common for several CAT tools, another are
 * specific for single CAT tool.
 * 
 * @author Lukasz Wiktor
 */
public enum MatchType implements DisplayableEnum{

    //Fortis specific categories
    NOT_TRANSLATED,


	// Common
	TOTAL,
	X_TRANSLATED,   // MemoQ, Trados (XTranslated is also known as Context TM)
	CONTEXT_MATCH,  // SDL Trados, Logoport, Across
	ICE_MATCH,      // Idiom, XTM
	REPETITIONS,    // Idiom, Logoport, MemoQ, Multitrans, SDL Trados, Trados, XTM
	NO_MATCH,       // Deja Vu (Detailed), Logport, MemoQ, Trados, XTM
	PERCENT_100,    // Logoport, MemoQ, Multitrans, SDL Trados, SDLX, Trados
	PERCENT_95_99,  // Deja Vu, Across, Logoport, MemoQ, SDL Trados, SDLX, Trados, XTM
	PERCENT_95_100, // Idiom, Transit
	PERCENT_85_94,  // Deja Vu, Logoport, MemoQ, SDL Trados, SDLX, Trados, Transit, XTM
	PERCENT_75_84,  // Deja Vu, Logoport, MemoQ, Multitrans, SDL Trados, SDLX, Trados, Transit, XTM
	PERCENT_50_74,  // Deja Vu, MemoQ, SDL Trados, SDLX, Trados, Transit
	UNTRANSLATED,   // Passolo, SDLX 
	PERCENT_80_89,  // DejaVu, Across
	PERCENT_70_79,  // DejaVu, Across
	PERCENT_60_69,  // DejaVu, Across
	PERCENT_50_59,  // DejaVu, Across
    LOCKED_SEGMENTS, //Trados 2014 specific
	
	// SDL Trados-specific
	PERFECT_MATCH,
	CROSS_FILE_REPETITIONS,
	NEW,
	INTERNAL_95_99,
	INTERNAL_85_94,
	INTERNAL_75_84,
	INTERNAL_50_74,
	ADAPTIVE_MT_BASELINE,
	ADAPTIVE_MT_WITH_LEARNINGS,
	
	// MemoQ-specific
	PERCENT_101,
	FRAGMENTS,

	// DejaVu-specific
	GUARANTEED,
	EXACT,
	DUPLICATES,
	PERCENT_99,
	PERCENT_98,
	PERCENT_97,
	PERCENT_96,
	PERCENT_95,
	PERCENT_90_94,
	PERCENT_40_49,
	PERCENT_30_39,
	PERCENT_20_29,
	PERCENT_10_19,
	PERCENT_0_9,

	// Idiom-specific
	PERCENT_85_95,
	PERCENT_75_85,
	PERCENT_50_75,
	PERCENT_0_50,

	// Logoport-specific
	TRANSLATED,
	FORMAT_CHANGE,

	// Transit-specific
	PRETRANSLATED,
	PARTIALLY_TRANSLATED,
	REMAINING_NOT_TRANSLATED,
	CHECK_PRETRANSLATION,
	TRANSLATED_PARAGRAPH,
	TRANSLATED_STRUCTURE,
	PERCENT_70_99,
	PERCENT_75_99,
	PERCENT_71_89,

	// SDLX-specific
	REPLICATED,

	// Passolo-specific
	UNTRANSLATED_REPETITIONS,
	AUTO_TRANSLATED,
	FOR_REVIEW,
	PERCENT_30_74,

	// MultiTrans-specific
	PERCENT_85_99,
	INTERNAL_75_99,

	// XTM-specific
	NON_TRANSLATABLE,
	LEVERAGED_MATCH,

	// Across-specific
	LOCKED,
	PERCENT_90_99,
	PERCENT_75_94,

	UNKNOWN;

	private static final Set<MatchType> fuzzy = EnumSet.of(
			PERCENT_0_9, PERCENT_0_50, PERCENT_10_19, PERCENT_20_29, PERCENT_30_39, PERCENT_40_49,
			PERCENT_50_59, PERCENT_50_74, PERCENT_50_75, PERCENT_60_69, PERCENT_70_79, PERCENT_75_84,
			PERCENT_75_85, PERCENT_75_94, PERCENT_80_89, PERCENT_85_94, PERCENT_85_95, PERCENT_85_99,
			PERCENT_90_94, PERCENT_90_99, PERCENT_95, PERCENT_95_99, PERCENT_95_100, PERCENT_96,
			PERCENT_97, PERCENT_98, PERCENT_99, PERCENT_70_99,PERCENT_71_89);

	public boolean isFuzzy(MatchType matchType) {
		return fuzzy.contains(matchType);
	}

	@Override
	public String getDisplayName() {
		return LocalizationUtils.getLocalizedEnumName(this);
	}

	@Override
	public String getEnumName() {
		return this.name();
	}
}
