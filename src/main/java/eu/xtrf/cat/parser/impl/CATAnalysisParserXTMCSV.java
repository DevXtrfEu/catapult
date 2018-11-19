package eu.xtrf.cat.parser.impl;

import static eu.xtrf.cat.MatchType.ICE_MATCH;
import static eu.xtrf.cat.MatchType.INTERNAL_75_84;
import static eu.xtrf.cat.MatchType.INTERNAL_85_94;
import static eu.xtrf.cat.MatchType.INTERNAL_95_99;
import static eu.xtrf.cat.MatchType.LEVERAGED_MATCH;
import static eu.xtrf.cat.MatchType.NON_TRANSLATABLE;
import static eu.xtrf.cat.MatchType.NO_MATCH;
import static eu.xtrf.cat.MatchType.PERCENT_75_84;
import static eu.xtrf.cat.MatchType.PERCENT_85_94;
import static eu.xtrf.cat.MatchType.PERCENT_95_99;
import static eu.xtrf.cat.MatchType.REPETITIONS;
import static eu.xtrf.cat.MatchType.TOTAL;
import static eu.xtrf.cat.Unit.CHARACTER;
import static eu.xtrf.cat.Unit.SEGMENT;
import static eu.xtrf.cat.Unit.WORD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipInputStream;

import com.google.common.collect.ImmutableMap;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.parser.CATAnalysisParserException;


/**
 * Parser for analyzes in CSV format generated by XTM  
 * 
 * @author Lukasz Wiktor
 *
 */

public class CATAnalysisParserXTMCSV extends CATAnalysisParserBase {

	private final String delim = "[;,]";

	
	public CATTool getCATTool() {
		return CATTool.XTM;
	}	

	ImmutableMap<String, MatchType> map = ImmutableMap.<String, MatchType> builder()
			.put("Total count", TOTAL)
			.put("Non-translatable", NON_TRANSLATABLE)
			.put("ICE match", ICE_MATCH)
			.put("Leveraged match", LEVERAGED_MATCH)
			.put("95-99% Fuzzy match", PERCENT_95_99)
			.put("85-94% Fuzzy match", PERCENT_85_94)
			.put("75-84% Fuzzy match", PERCENT_75_84)
			.put("Repeat", REPETITIONS)
			.put("95-99% Fuzzy repeat", INTERNAL_95_99)
			.put("85-94% Fuzzy repeat", INTERNAL_85_94)
			.put("75-84% Fuzzy repeat", INTERNAL_75_84)
			.put("No matching", NO_MATCH)
			.build();

	@Override
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
		
		if (fileName.endsWith(".zip")) {
			is = getUnzippedInputStream(is);	
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		int count = 0;
		int charactersIndex = 4;
		
		String line;
		try {
			while ((line = br.readLine()) != null) {
				String[] items = line.replaceAll("\"", "").split(delim);

				if (line == null || line.replaceAll(delim, "").trim().isEmpty()) {
					break;
				}

				if(items[0].equals("Initial")) {
					for(int i=0; i<items.length; i++) {
						if(items[i].equals("Characters")) {
							charactersIndex = i;
							break;
						}
					}
				}
				
				items[0] = items[0].replace("Modified ICE match", "ICE match");

				MatchType matchType = map.get(items[0]);
				if (matchType != null) {
					result.add(SEGMENT, matchType,  Integer.valueOf(items[1]));
					result.add(WORD, matchType,  Integer.valueOf(items[2]));
					result.add(CHARACTER, matchType,  Integer.valueOf(items[charactersIndex]));
					count++;
				}
			}
		} catch (NumberFormatException | IOException e) {
			throw new CATAnalysisParserException(e);
		}

		if (count != 12) {
			throw new CATAnalysisParserException("Incorrect number of match types");
		}

		result.verifyDataIntegrity();

		return result;
	}

	private InputStream getUnzippedInputStream(InputStream is) throws CATAnalysisParserException {
		try {
			ZipInputStream zis = new ZipInputStream(is);
			zis.getNextEntry();
			return zis;
		} catch (IOException e) {
			throw new CATAnalysisParserException("Cannot unzip analysis file.", e);
		}
	}
}