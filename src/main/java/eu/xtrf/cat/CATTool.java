package eu.xtrf.cat;

import com.radzisz.xtrf.model.DisplayableEnum;
import com.radzisz.xtrf.utils.messageResources.LocalizationUtils;

import java.util.*;

import static eu.xtrf.cat.MatchType.*;

/**
 * Set of TM Savings types. Each type includes a list of {@link eu.xtrf.cat.MatchType match types}.
 * 
 * @author Lukasz Wiktor
 */
public enum CATTool implements DisplayableEnum{
    FORTIS(
            groupFuzzyMatch(
                    PRETRANSLATED,
                    PARTIALLY_TRANSLATED),
            groupNoMatch(
                    NOT_TRANSLATED)),

	TRADOS(
		groupContextMatch(
			X_TRANSLATED),
		groupFullMatch(
			REPETITIONS,
			PERCENT_100),
		groupFuzzyMatch(
			PERCENT_95_99,
			PERCENT_85_94,
			PERCENT_75_84,
			PERCENT_50_74),
		groupNoMatch(
			NO_MATCH)),

	SDL_TRADOS(
		groupContextMatch(
            LOCKED_SEGMENTS,
			PERFECT_MATCH,
			CONTEXT_MATCH),
		groupFullMatch(
			REPETITIONS,
		    CROSS_FILE_REPETITIONS, // since 2011 SP2
		    PERCENT_100),
		groupFuzzyMatch(
			PERCENT_95_99,
			PERCENT_85_94,
			PERCENT_75_84,
			PERCENT_50_74,
			INTERNAL_95_99,
			INTERNAL_85_94,
			INTERNAL_75_84,
			INTERNAL_50_74),
		groupNoMatch(
			NEW,
			ADAPTIVE_MT_BASELINE,
			ADAPTIVE_MT_WITH_LEARNINGS)),
			
	DEJA_VU(
		groupContextMatch(
			GUARANTEED,
			LOCKED),
		groupFullMatch(
			DUPLICATES,
			EXACT),
		groupFuzzyMatch(
			PERCENT_95_99,
			
			PERCENT_85_94,
			PERCENT_75_84,
			PERCENT_50_74),
		groupNoMatch(
			NO_MATCH)),

	DEJA_VU_DETAILED( // TODO consult if it's needed
		groupContextMatch(
			GUARANTEED),
		groupFullMatch(
			DUPLICATES,
			EXACT),
		groupFuzzyMatch(
			PERCENT_99,
			PERCENT_98,
			PERCENT_97,
			PERCENT_96,
			PERCENT_95,
			PERCENT_90_94,
			PERCENT_80_89,
			PERCENT_70_79,
			PERCENT_60_69,
			PERCENT_50_59,
			PERCENT_40_49,
			PERCENT_30_39,
			PERCENT_20_29,
			PERCENT_10_19,
			PERCENT_0_9),
		groupNoMatch(
			NO_MATCH)),

    //Target Locale,Asset,Total,ICE Match,100%,100-95%,95-85%,85-75%,75-50%,50-0%,Repetition,Cost Estimate (USD),MT Fuzzy Words
	IDIOM(
		groupContextMatch(
			ICE_MATCH),
		groupFullMatch(
			REPETITIONS),
		groupFuzzyMatch(
            PERCENT_100,
			PERCENT_95_100,
			PERCENT_85_95,
			PERCENT_75_85,
			PERCENT_50_75,
			PERCENT_0_50)),

    //Asset    Total      ICE     100%  100-95%   95-85%   85-75%   75-50%    50-0%   Repetition

    IDIOM_WORKBENCH(
            groupContextMatch(ICE_MATCH),
            groupFullMatch(REPETITIONS),
            groupFuzzyMatch(
                    PERCENT_100,
           			PERCENT_95_100,
           			PERCENT_85_95,
           			PERCENT_75_85,
           			PERCENT_50_75),
            groupNoMatch(
            		NO_MATCH)
    ),

	SDL_WORLD_SERVER(
		groupContextMatch(
				ICE_MATCH),
		groupFullMatch(
				REPETITIONS),
		groupFuzzyMatch(
				PERCENT_100,
				PERCENT_95_100,
				PERCENT_85_95,
				PERCENT_75_85,
				PERCENT_50_75,
				PERCENT_0_50)),

	LOGOPORT(
		groupContextMatch(
			CONTEXT_MATCH,
			TRANSLATED),
		groupFullMatch(
			REPETITIONS,
			PERCENT_100),
		groupFuzzyMatch(
			FORMAT_CHANGE,
			PERCENT_95_99,
			PERCENT_85_94,
			PERCENT_75_84,
            PERCENT_50_74),
		groupNoMatch(
			NO_MATCH)),
		
	MEMO_Q(
		groupContextMatch(
			X_TRANSLATED,
			PERCENT_101),
		groupFullMatch(
			REPETITIONS,
			PERCENT_100),
		groupFuzzyMatch(
			PERCENT_95_99,
			PERCENT_85_94,
			PERCENT_75_84,
			PERCENT_50_74),
		groupNoMatch(
			FRAGMENTS,
			NO_MATCH)),
		
	MULTITRANS(
		groupFullMatch(
			PERCENT_100,
			REPETITIONS),
		groupFuzzyMatch(
			PERCENT_85_99,
			PERCENT_75_84,
			INTERNAL_75_99),
		groupNoMatch(
			NEW)),
		
	PASSOLO(
		groupContextMatch(
			AUTO_TRANSLATED),
		groupFullMatch(
			UNTRANSLATED_REPETITIONS),
		groupFuzzyMatch(
			PERCENT_95_99,
			PERCENT_85_94,
			PERCENT_75_84,
			PERCENT_30_74,
			FOR_REVIEW),
		groupNoMatch(
			UNTRANSLATED)),
		
	SDLX(
		groupFullMatch(
			REPLICATED,
			PERCENT_100),
		groupFuzzyMatch(
			PERCENT_95_99,
			PERCENT_85_94,
			PERCENT_75_84,
			PERCENT_50_74),
		groupNoMatch(
			UNTRANSLATED)),
				
	TRANSIT(
		groupContextMatch(
			REPETITIONS,
			PRETRANSLATED),
		groupFuzzyMatch(
			PARTIALLY_TRANSLATED,
			PERCENT_95_100,
				PERCENT_85_94,
			PERCENT_75_84,
			PERCENT_50_74),
		groupNoMatch(
			REMAINING_NOT_TRANSLATED)), 
	
	TRANSIT_NXT(
		groupContextMatch(
			REPETITIONS,
			PRETRANSLATED,
			CHECK_PRETRANSLATION,
			TRANSLATED_PARAGRAPH,
			TRANSLATED_STRUCTURE,
			PERCENT_100,
			TRANSLATED),
		groupFuzzyMatch(
			PERCENT_75_99,
			PERCENT_70_99,
			PERCENT_71_89,
			PERCENT_90_99,
			PERCENT_95_99,
			PERCENT_85_94,
			PERCENT_75_84,
			PERCENT_50_74),
		groupNoMatch(
			NOT_TRANSLATED)),
			
	XTM(
		groupContextMatch(
			ICE_MATCH,
			NON_TRANSLATABLE),
		groupFullMatch(
			LEVERAGED_MATCH,
			REPETITIONS),
		groupFuzzyMatch(
			PERCENT_95_99,
			PERCENT_85_94,
			PERCENT_75_84,
			INTERNAL_95_99,
			INTERNAL_85_94,
			INTERNAL_75_84
		),
		groupNoMatch(
			NO_MATCH)),
	
	WORDFAST(
		groupFullMatch(
			REPETITIONS,
			PERCENT_100),
		groupFuzzyMatch(
			PERCENT_95_99,
			PERCENT_85_94,
			PERCENT_75_84,
			PERCENT_50_74
		),
		groupNoMatch(
			NO_MATCH)
	),
			
	ACROSS(
		groupContextMatch(
			CONTEXT_MATCH,
			LOCKED),
		groupFullMatch(
			REPETITIONS,
			PERCENT_100),
		groupFuzzyMatch(
			PERCENT_95_99,
			PERCENT_90_99, //only in HTML
			PERCENT_80_89,
			PERCENT_75_94, //only in XML
			PERCENT_70_79,
			PERCENT_60_69,
			PERCENT_50_59),
		groupNoMatch(
			NO_MATCH)
	);
	
	private Map<MatchTypeGroup, List<MatchType>> matchTypesByGroup;
	
	private List<MatchType> matchTypes;
	
	private CATTool(TypeEntry ... entries) {
		matchTypes = new ArrayList<MatchType>();
		matchTypesByGroup = new HashMap<MatchTypeGroup, List<MatchType>>();
		
		// inlitilize map with empty list for all possible match types
		for (MatchTypeGroup group : MatchTypeGroup.values()) {
			matchTypesByGroup.put(group, new ArrayList<MatchType>());
		}
		
		for (TypeEntry entry : entries) {
			matchTypes.addAll(entry.matchTypes);
			matchTypesByGroup.get(entry.group).addAll(entry.matchTypes);
		}
	}
	
	public List<MatchType> getMatchTypes() {
		return matchTypes;
	}
	
	public List<MatchType> getMatchTypesByGroup(MatchTypeGroup group) {
		return Collections.unmodifiableList(matchTypesByGroup.get(group));
	}

	@Override
	public String getDisplayName() {
		return LocalizationUtils.getLocalizedEnumName(this);
	}

	@Override
	public String getEnumName() {
		return this.name();
	}


	private static class TypeEntry {
		private List<MatchType> matchTypes;
		private MatchTypeGroup group;
		
		public TypeEntry(MatchTypeGroup group, MatchType ... matchTypes) {
			this.matchTypes = Arrays.asList(matchTypes);
			this.group = group;
		}
	}

	private static TypeEntry groupContextMatch(MatchType ... matchTypes) {
		return new TypeEntry(MatchTypeGroup.CONTEXT_MATCH, matchTypes);
	}
	
	private static TypeEntry groupFullMatch(MatchType ... matchTypes) {
		return new TypeEntry(MatchTypeGroup.FULL_MATCH, matchTypes);
	}
	
	private static TypeEntry groupFuzzyMatch(MatchType ... matchTypes) {
		return new TypeEntry(MatchTypeGroup.FUZZY_MATCH, matchTypes);
	}
	
	private static TypeEntry groupNoMatch(MatchType ... matchTypes) {
		return new TypeEntry(MatchTypeGroup.NO_MATCH, matchTypes);
	}

	public boolean isInMatchTypeGroup(MatchTypeGroup group, MatchType matchType) {
		return matchTypesByGroup.get(group) != null && matchTypesByGroup.get(group).contains(matchType);
	}
}
