package eu.xtrf.cat.parser.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;
import eu.xtrf.cat.parser.CATAnalysisParserNotSupportedMultilingualAnalysisException;


public class CATAnalysisParserMemoqTradosCSV extends CATAnalysisParserBase {

	private static final String[] SUPPORTED_ENCODINGS = new String[] {"UTF-16", "UTF-8"};
	private static final String SEPARATOR = "[;\t]";

	private final MatchingContainer matchingContainer = new MatchingContainer();

	public CATAnalysisParserMemoqTradosCSV() {
		matchingContainer.add(new String[] { "Repetitions" }, MatchType.REPETITIONS);
		matchingContainer.add(new String[] { "X-translated", "Context TM" }, MatchType.X_TRANSLATED);
		matchingContainer.add(new String[] { "101%" }, MatchType.PERCENT_101);
		matchingContainer.add(new String[] { "100%", "100% Matches" }, MatchType.PERCENT_100);
		matchingContainer.add(new String[] { "95% - 99%" }, MatchType.PERCENT_95_99);
		matchingContainer.add(new String[] { "85% - 94%" }, MatchType.PERCENT_85_94);
		matchingContainer.add(new String[] { "75% - 84%" }, MatchType.PERCENT_75_84);
		matchingContainer.add(new String[] { "50% - 74%" }, MatchType.PERCENT_50_74);
		matchingContainer.add(new String[] { "No Match", "No match" }, MatchType.NO_MATCH);
		matchingContainer.add(new String[] { "Fragments" }, MatchType.FRAGMENTS);
		matchingContainer.add(new String[] { "Total" }, MatchType.TOTAL);
	}

	@Override
	public CATTool getCATTool() {
		return CATTool.MEMO_Q;
	}

	@Override
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		if (fileName.endsWith(".htm") || fileName.endsWith(".html"))
			throw new CATAnalysisParserException();

		for (String encoding : SUPPORTED_ENCODINGS) {
			CATAnalysis analysis = tryParse(is, fileName, encoding);
			if (analysis != null) {
				if (isMultilingual(is, encoding)) {
					throw new CATAnalysisParserNotSupportedMultilingualAnalysisException();
				}
				return analysis;
			}
			try {
				is.reset();
			} catch (IOException e) {
				throw new CATAnalysisParserException(e);
			}
		}
		throw new CATAnalysisParserException();
	}

	private boolean isMultilingual(InputStream is, String charsetName) throws CATAnalysisParserException {
		try {
			is.reset();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, charsetName));
			br.readLine(); // skip header first line
			br.readLine(); // skip header second line
			Set<String> langCombinations = new HashSet<>();
			String line;
			while ((line = br.readLine()) != null) {
				String[] cols = line.split(SEPARATOR);
				String fileDescription = cols[0];
				if (fileDescription.matches(".+ \\| .+>.+")) { // eg "some-file.txt | en>pl"
					String langCombination = fileDescription.substring(fileDescription.lastIndexOf( " ") + 1);
					langCombinations.add(langCombination);
				}
			}
			return langCombinations.size() > 1;
		} catch (IOException e) {
			throw new CATAnalysisParserException(e);
		}
	}

	private CATAnalysis tryParse(InputStream is, String fileName, String charsetName) throws CATAnalysisParserException {
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
		try {
			Map<MatchType, Integer> enumToIndex = new HashMap<>();

			BufferedReader br = new BufferedReader(new InputStreamReader(is, charsetName));
			String[] header = br.readLine().split(SEPARATOR);
			for (int index = 0; index < header.length; index++) {
				MatchType e = matchingContainer.getEnum(header[index]);
				if (e == null) {
					continue;
				}
				enumToIndex.put(e, index);
			}
			if (enumToIndex.size() < 7)
				throw new DataFormatException();

			String[] matchUnitHeader = br.readLine().split(SEPARATOR);

			boolean includeCharacters = includeCharacters(enumToIndex, matchUnitHeader);

			int charsPerWordCol = getCharsPerWordCol(matchUnitHeader);
			List<BigDecimal> charsPerWordList = new ArrayList<>();

			String line;
			while ((line = br.readLine()) != null) {
				String[] cols = line.split(SEPARATOR);
				if (charsPerWordCol > -1) {
					String charsPerWordValue = cols[charsPerWordCol].replace(',', '.');
					BigDecimal charsPerWord = new BigDecimal(charsPerWordValue);
					charsPerWordList.add(charsPerWord);
				}
				for (MatchType e : enumToIndex.keySet()) {
					int startIndex = enumToIndex.get(e);
					CellOffsets offsets = getCellOffsets(startIndex, enumToIndex, matchUnitHeader);
					if (offsets.segments >= 0) {
						result.add(Unit.SEGMENT, e, Integer.parseInt(cols[enumToIndex.get(e)+ offsets.segments]));
					}
					if (offsets.words >= 0) {
						result.add(Unit.WORD, e, Integer.parseInt(cols[enumToIndex.get(e) + offsets.words]));
					}
					if (offsets.characters >= 0 && includeCharacters) {
						result.add(Unit.CHARACTER, e, Integer.parseInt(cols[enumToIndex.get(e) + offsets.characters]));
					}
					if (offsets.tokens >= 0) {
						result.add(Unit.TOKEN, e, Integer.parseInt(cols[enumToIndex.get(e) + offsets.tokens]));
					}
				}
			}
			result.verifyDataIntegrity();
			if (charsPerWordCol > -1) {
				BigDecimal charsPerWord = calculateCharsPerWord(charsPerWordList);
				result.setCharsPerWord(charsPerWord);
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	private int getCharsPerWordCol(String[] matchUnitHeader) {
		List<String> supportedHeaders = Arrays.asList("Chars/Word", "Char/Word");
		for (int col = 0; col < matchUnitHeader.length; col++) {
			String candidate = matchUnitHeader[col];
			if (supportedHeaders.contains(candidate)) {
				return col;
			}
		}
		return -1;
	}

	private boolean includeCharacters(Map<MatchType, Integer> enumToIndex, String[] matchUnitHeader) {
		// Characters will be included in result if and only if every Unit contains column 'Characters'.
		List<CellOffsets> offsetsList = enumToIndex.keySet().stream()
				.map(e -> getCellOffsets(enumToIndex.get(e), enumToIndex, matchUnitHeader))
				.collect(Collectors.toList());
		return offsetsList.stream()
				.allMatch(offsets -> offsets.characters > -1);
	}

	private CellOffsets getCellOffsets(int startIndex, Map<MatchType, Integer> enumToIndex, String[] matchUnitHeader) {
		int offset = 0;
		CellOffsets offsets = new CellOffsets();
		do {
			switch (matchUnitHeader[startIndex+offset].trim()) {
				case "Segments":
					offsets.segments = offset;
					break;
				case "Words":
					offsets.words = offset;
					break;
				case "Characters":
					offsets.characters = offset;
					break;
				case "Placeables":
					offsets.tokens = offset;
					break;
			}
			offset++;
		}
		while(!enumToIndex.values().contains(startIndex + offset) && startIndex + offset < matchUnitHeader.length);
		return offsets;
	}

	private BigDecimal calculateCharsPerWord(List<BigDecimal> charsPerWordList) {
		BigDecimal divisor = BigDecimal.valueOf(charsPerWordList.size());
		BigDecimal charsPerWord = charsPerWordList.stream()
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO)
				.divide(divisor, 2, RoundingMode.HALF_UP)
				.stripTrailingZeros();
		return charsPerWord;
	}

}

/*  
 * Class learns the mapping column names-index by add method, and is able to tell index by its names 
 */
class MatchingContainer {

	class MatchingItem {

		public MatchingItem(Set<String> key, MatchType value) {
			super();
			this.key = key;
			this.value = value;
		}

		public boolean doMatch(String k) {
			return this.key.contains(k);
		}

		private Set<String> key;
		private MatchType value;
	}

	private List<MatchingItem> matchingItems = new LinkedList<>();

	public MatchType getEnum(String columnName) {
		for (MatchingItem item : matchingItems) {
			if (item.doMatch(columnName))
				return item.value;
		}
		return null;
	}

	public void add(String[] columns, MatchType e) {
		matchingItems.add(new MatchingItem(new HashSet<>(Arrays.asList(columns)), e));
	}
}

class CellOffsets {
	int segments = -1;
	int words = -1;
	int characters = -1;
	int tokens = -1;
}