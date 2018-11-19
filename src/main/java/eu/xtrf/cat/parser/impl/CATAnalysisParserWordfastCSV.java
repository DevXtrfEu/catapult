package eu.xtrf.cat.parser.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;

public class CATAnalysisParserWordfastCSV extends CATAnalysisParserBase {
	
	private final int COLUMN_SIZE = 42;
	
	private ImmutableMap<String, MatchType> matchTypes = ImmutableMap.<String, MatchType> builder()
			.put("Total", MatchType.TOTAL)
			.put("100% Matches", MatchType.PERCENT_100)
			.put("\"95%-99%\"", MatchType.PERCENT_95_99)
			.put("\"85%-94%\"", MatchType.PERCENT_85_94)
			.put("\"75%-84%\"", MatchType.PERCENT_75_84)
			.put("Repetitions", MatchType.REPETITIONS)
			.put("\"50%-74%\"", MatchType.PERCENT_50_74)
			.put("No Match", MatchType.NO_MATCH)
			.build();
	
	@Override
	public CATTool getCATTool() {
		return CATTool.WORDFAST;
	}

	@Override
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		try {
			CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
			
			Map<Integer, String> positions = new HashMap<Integer, String>();
			List<String> ommitedCategories = Arrays.asList("Golden", "Leveraged");
			String delimiter = ",";
	
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String headLine = br.readLine();
			if (headLine == null) {
				return null;
			}
			String[] head = headLine.split(delimiter, -1);
	
			if (head.length != COLUMN_SIZE) {
				return null;
			}
	
			for (int i = 0; i < head.length; i++) {
				if (!head[i].isEmpty()) {
					positions.put(i, head[i]);
				}
			}
			br.readLine();
			String currentLine = null;
			while ((currentLine = br.readLine()) != null && !currentLine.isEmpty()) {
				String[] values = currentLine.split(delimiter);
				for (int item : positions.keySet()) {
					String categoryName = positions.get(item);
					if (!ommitedCategories.contains(categoryName)) {
						MatchType matchType = matchTypes.get(categoryName);
						result.add(Unit.SEGMENT, matchType, new BigDecimal(values[item]));
						result.add(Unit.WORD, matchType, new BigDecimal(values[item + 1]));
					}
				}
			}
			
			result.verifyDataIntegrity();
			
			return result;
		}
		catch(IOException e) {
			throw new CATAnalysisParserException("I/O error", e);
		}
	}
}
