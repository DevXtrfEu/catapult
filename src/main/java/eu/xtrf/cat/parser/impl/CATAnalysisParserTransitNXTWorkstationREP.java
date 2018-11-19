package eu.xtrf.cat.parser.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;


public class CATAnalysisParserTransitNXTWorkstationREP extends CATAnalysisParserBase {

	@Override
	public CATTool getCATTool() {
		return CATTool.TRANSIT_NXT;
	}

	@Override
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		boolean internalRepetitions = false;
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				if (line.contains("Internal repetitions")) {
					internalRepetitions = true;
					continue;
				}

				String[] split = line.split("\t");
				if (split.length == (internalRepetitions ? 11 : 10) && "Totals".equals(split[0].trim())) {
					int index = 1;
					if (internalRepetitions) {
						result.add(Unit.WORD, MatchType.REPETITIONS, new BigDecimal(split[index++]));
					}
					result.add(Unit.WORD, MatchType.PRETRANSLATED, new BigDecimal(split[index++]));
					result.add(Unit.WORD, MatchType.CHECK_PRETRANSLATION, new BigDecimal(split[index++]));
					result.add(Unit.WORD, MatchType.PERCENT_100, new BigDecimal(split[index++]));
					result.add(Unit.WORD, MatchType.PERCENT_95_99, new BigDecimal(split[index++]));
					result.add(Unit.WORD, MatchType.PERCENT_85_94, new BigDecimal(split[index++]));
					result.add(Unit.WORD, MatchType.PERCENT_75_84, new BigDecimal(split[index++]));
					result.add(Unit.WORD, MatchType.PERCENT_50_74, new BigDecimal(split[index++]));
					result.add(Unit.WORD, MatchType.NOT_TRANSLATED, new BigDecimal(split[index++]));
					result.add(Unit.WORD, MatchType.TOTAL, new BigDecimal(split[index++]));

					result.verifyDataIntegrity();

					return result;
				}
			}
		} catch (IOException e) {
			throw new CATAnalysisParserException("Error reading file", e);
		}
		
		throw new CATAnalysisParserException();
	}
}
