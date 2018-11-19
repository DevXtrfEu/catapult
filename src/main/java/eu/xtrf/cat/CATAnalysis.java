package eu.xtrf.cat;

import eu.xtrf.cat.parser.CATAnalysisParserException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * Represents result of parsed CAT analysis. Contains count of segments, words or characters for whole analysis as well as for particular
 * files included in this analysis.
 * 
 * @author Lukasz Wiktor
 */
public class CATAnalysis implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TOTAL_FILE_NAME = ""; // empty string as fileName means total

	private final CATTool catTool;
	private final String fileName;
	private final Date creationDate;

	private BigDecimal charsPerWord;
	
	private final Map<ResultKey, BigDecimal> values = new HashMap<ResultKey, BigDecimal>() {

		/**
		 * Returns zero when map does not contain value for given key
		 * 
		 * @return zero when map does not contain value for given key
		 */
		@Override
		public BigDecimal get(Object key) {
			BigDecimal value = super.get(key);
			return value != null ? value : BigDecimal.ZERO;
		}

		private static final long serialVersionUID = 0xfeed;
	};

	public CATAnalysis(CATTool catTool) {
		this(catTool, null);
	}

	public CATAnalysis(CATTool catTool, String fileName) {
		assertNotNull(catTool, "catTool");
		this.catTool = catTool;
		this.fileName = fileName;
		this.creationDate = new Date();
	}

	/**
	 * Copy constructor
	 */
	public CATAnalysis(CATAnalysis result) {
		this(result, result.getFileName());
	}

	/**
	 * Copy constructor, changing source file name
	 */
	public CATAnalysis(CATAnalysis result, String fileName) {
		this(result.catTool, fileName);
		this.values.putAll(result.values);
	}

	public CATTool getCATTool() {
		return catTool;
	}


	public String getFileName() {
		return fileName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @return total value for specified match type and unit
	 */
	public BigDecimal get(Unit unit, MatchType matchType) {
		return values.get(key(unit, matchType));
	}

	/**
	 * Sets total value for specified match type and unit.
	 */
	public CATAnalysis set(Unit unit, MatchType matchType, BigDecimal value) {
		validateMatchType(matchType);
		values.put(key(unit, matchType), value);
		return this;
	}

	/**
	 * Sets total value for specified match type and unit.
	 */
	public CATAnalysis set(Unit unit, MatchType matchType, int value) {
		return set(unit, matchType, new BigDecimal(value));
	}

	/**
	 * @return value for specified match yype, unit and fileName
	 */
	public BigDecimal get(Unit unit, MatchType matchType, String fileName) {
		return values.get(key(unit, matchType, fileName));
	}

	/**
	 * Sets value for specified match type, unit and fileName.
	 * 
	 * @return object on which this method was invoked (to enable method chaining)
	 */
	public CATAnalysis set(Unit unit, MatchType matchType, String fileName, BigDecimal value) {
		validateMatchType(matchType);
		values.put(key(unit, matchType, fileName), value);
		return this;
	}

	/**
	 * Sets value for specified match type, unit and fileName.
	 * 
	 * @return object on which this method was invoked (to enable method chaining)
	 */
	public CATAnalysis set(Unit unit, MatchType matchType, String fileName, int value) {
		return set(unit, matchType, fileName, new BigDecimal(value));
	}

	/**
	 * Adds given value to value already stored for specified unit and match type.
	 * 
	 * @return object on which this method was invoked (to enable method chaining)
	 */
	public CATAnalysis add(Unit unit, MatchType matchType, BigDecimal value) {
		return set(unit, matchType, get(unit, matchType).add(value));
	}

	/**
	 * Adds given value to value already stored for specified unit and match type.
	 * 
	 * @return object on which this method was invoked (to enable method chaining)
	 */
	public CATAnalysis add(Unit unit, MatchType matchType, int value) {
		return set(unit, matchType, get(unit, matchType).add(new BigDecimal(value)));
	}
	
	/**
	 * @return set of file names included in analysis (note that not every analysis contains values for particular files)
	 */
	public Set<String> getFileNames() {
		Set<String> fileNames = new HashSet<String>();
		for (ResultKey key : values.keySet()) {
			fileNames.add(key.fileName);
		}
		fileNames.remove(TOTAL_FILE_NAME);
		return fileNames;
	}

	/**
	 * @return total values for specified unit and all match types within declared catGritType
	 */
	public Map<MatchType, BigDecimal> get(Unit unit) {
		return get(unit, TOTAL_FILE_NAME);
	}

	/**
	 * @return values for specified unit, fileName and all match types within declared catGritType
	 */
	public Map<MatchType, BigDecimal> get(Unit unit, String fileName) {
		Map<MatchType, BigDecimal> result = new HashMap<MatchType, BigDecimal>();
		for (MatchType matchType : catTool.getMatchTypes()) {
			result.put(matchType, values.get(key(unit, matchType, fileName)));
		}
		return result;
	}

	/**
	 * Facilitates setting total values in several units for the same match type.
	 * 
	 * For example: <code>
	 * result.focusOn(REPETITIONS).set(SEGMENT, 20).set(WORD, 80).set(CHARACTER, 400)
	 * </code>
	 */
	public UnitValueSetter focusOn(MatchType matchType) {
		return new UnitValueSetter(matchType);
	}

	/**
	 * Facilitates setting values in several units for the same match type and fileName.
	 */
	public UnitValueSetter focusOn(MatchType matchType, String fileName) {
		return new UnitValueSetter(matchType, fileName);
	}

	/**
	 * Checks whether values included in this result are integral. Compares total value extracted from analysis and summed up from all other
	 * match types.
	 * 
	 * @throws CATAnalysisParserException
	 */
	public void verifyDataIntegrity() throws CATAnalysisParserException {
		if (values.isEmpty()) {
			throw new CATAnalysisParserException("Analysis is empty");
		}
		// TOKEN's total is sum of values from different columns (Recognized Tokens, Fragment words)
		// We parse only Recognized Tokens, so we can't check data integrity for TOKEN
		for (Unit unit : Arrays.asList(Unit.SEGMENT, Unit.WORD, Unit.CHARACTER)) { 
			BigDecimal extractedTotal = get(unit, MatchType.TOTAL);
			BigDecimal computedTotal = countTotal(unit);
			if (!extractedTotal.equals(computedTotal)) {
				throw new CATAnalysisParserException("Extracted total (" + extractedTotal + ") does not equal computed total ("
						+ computedTotal + "). Unit: " + unit);
			}
		}
	}

    public boolean isDataIntegral(){
        try {
            verifyDataIntegrity();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Aggregates this and other analysis into a new one.
     */
    public CATAnalysis aggregate(CATAnalysis other) {
    	if (getCATTool() != other.getCATTool()) {
    		throw new RuntimeException("Cannot aggregate result from different cat tools, this=" + getCATTool() + ", other=" + other.getCATTool());
    	}
    	CATAnalysis result = new CATAnalysis(getCATTool());
    	for (Unit unit: Unit.values()) {
    		for (MatchType matchType: getCATTool().getMatchTypes()) {
    			result.add(unit, matchType, get(unit, matchType));
    			result.add(unit, matchType, other.get(unit, matchType));
    		}
    	}
    	return result;
    }

	private BigDecimal countTotal(Unit unit) {
		BigDecimal total = BigDecimal.ZERO;
		for (MatchType matchType : catTool.getMatchTypes()) {
			BigDecimal value = get(unit, matchType);
			total = total.add(value);
		}
		return total;
	}

	private void validateMatchType(MatchType matchType) {
		if (matchType == null || (matchType != MatchType.TOTAL && !catTool.getMatchTypes().contains(matchType))) {
			throw new IllegalArgumentException("Match type " + matchType + " is not valid for CAT tool: " + catTool);
		}
	}

	private static void assertNotNull(Object parameter, String parameterName) {
		if (parameter == null) {
			throw new IllegalArgumentException("parameter " + parameterName + " cannot be null");
		}
	}

	private static class ResultKey implements Serializable {

		private static final long serialVersionUID = 1L;

		private Unit unit;
		private MatchType matchType;
		private String fileName;

		public ResultKey(Unit unit, MatchType matchType, String fileName) {
			assertNotNull(unit, "unit");
			assertNotNull(matchType, "matchType");
			assertNotNull(fileName, "fileName");
			this.unit = unit;
			this.matchType = matchType;
			this.fileName = fileName;
		}

		@Override
		public int hashCode() {
			return unit.hashCode() ^ matchType.hashCode() ^ fileName.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof ResultKey) {
				ResultKey other = (ResultKey) obj;
				return matchType.equals(other.matchType) && unit.equals(other.unit) && fileName.equals(other.fileName);
			}
			return false;
		}
	}

	private static ResultKey key(Unit unit, MatchType matchType, String fileName) {
		return new ResultKey(unit, matchType, fileName);
	}

	private static ResultKey key(Unit unit, MatchType matchType) {
		return new ResultKey(unit, matchType, TOTAL_FILE_NAME);
	}

	/**
	 * Enables easier creation of CATAnalysisResult.
	 * 
	 * @author Lukasz Wiktor
	 */
	public class UnitValueSetter {

		MatchType matchType;
		String fileName;

		public UnitValueSetter(MatchType matchType) {
			this(matchType, TOTAL_FILE_NAME);
		}

		public UnitValueSetter(MatchType matchType, String fileName) {
			this.matchType = matchType;
			this.fileName = fileName;
		}

		public UnitValueSetter set(Unit unit, BigDecimal value) {
			CATAnalysis.this.set(unit, matchType, fileName, value);
			return this;
		}

		public UnitValueSetter set(Unit unit, int value) {
			CATAnalysis.this.set(unit, matchType, fileName, value);
			return this;
		}

		public UnitValueSetter focusOn(MatchType matchType) {
			return new UnitValueSetter(matchType);
		}

		public UnitValueSetter focusOn(MatchType matchType, String fileName) {
			return new UnitValueSetter(matchType, fileName);
		}
	}

	public BigDecimal getCharsPerWord() {
		return charsPerWord;
	}

	public void setCharsPerWord(BigDecimal charsPerWord) {
		this.charsPerWord = charsPerWord;
	}
}
