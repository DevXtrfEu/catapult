/*
 * XTRF Translation Management System Ltd.
 * 
 * 		http://www.xtrf.eu
 * 
 * Copyright (c) 2005-2012 Radzisz Communications and EUTECert
 * 
 * 		http://www.eutecert.eu
 * 		http://www.radzisz.com
 * 
 * All rights reserved.
 * 
 * CATAnalysisParserMemoQ.java, created: 09-11-2012.
 */
package eu.xtrf.cat.parser.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

import com.google.common.collect.ImmutableList;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;
import eu.xtrf.cat.parser.impl.CATAnalysisMemoqConfigurations.LanguageKey;

import static eu.xtrf.cat.parser.impl.CATAnalysisMemoqConfigurations.ALL_LANGUAGE_KEYS;
import static eu.xtrf.cat.parser.impl.CATAnalysisMemoqConfigurations.SUPPORTED_DELIMITERS;
import static eu.xtrf.cat.parser.impl.CATAnalysisMemoqConfigurations.SUPPORTED_ENCODINGS;

/**
 * Analysis of memoQ metrics in CSV format.
 * 
 * @author Daniel Bochniak
 */
public class CATAnalysisParserMemoQ extends CATAnalysisParserBase {
	private static Logger logger = Logger.getLogger(CATAnalysisParserMemoQ.class);
		
	private String delim;
	private String charset;
	private Map<LanguageKey, String> languageKeys;
	
	public CATAnalysisParserMemoQ() {
		
	}
	
	CATAnalysisParserMemoQ(Map<LanguageKey, String> languageKeys, String delim, String charset) {
		this.languageKeys = languageKeys;
		this.delim = delim;
		this.charset = charset;
	}

	public CATTool getCATTool() {
		return CATTool.MEMO_Q;
	}
	
	private Collection<CATAnalysisParserMemoQ> createWordStats() {
		ImmutableList.Builder<CATAnalysisParserMemoQ> builder = ImmutableList.builder();
		for (Map<LanguageKey, String> languageKeys : ALL_LANGUAGE_KEYS) {
			for (String delimiter : SUPPORTED_DELIMITERS) {
				for (String encoding : SUPPORTED_ENCODINGS) {
					builder.add(new CATAnalysisParserMemoQ(languageKeys, delimiter, encoding));
				}
			}
		}
		return builder.build();
	}
	
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		byte[] data;
		try {
			data = IOUtils.toByteArray(is);
		} catch (IOException e) {
			throw new CATAnalysisParserException("Failed to read cat analysis file: " + fileName, e);
		}
		
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		
		for (CATAnalysisParserMemoQ wordStat : createWordStats()) {
			try {
				bis.reset();
				
				CATAnalysis catAnalysisResult = wordStat.tryToParse(fileName, bis);
				if (catAnalysisResult.getCATTool() != null) {
					if(logger.isDebugEnabled()) {
						logger.debug(wordStat.languageKeys.get(LanguageKey.ALL) + " " + wordStat.delim + " " + wordStat.charset);
					}
					return catAnalysisResult;
				}
			} catch (Exception e) {
				continue;
				// ignore, invalid metrics for the word stat were supplied
			}
		}
		
		throw new CATAnalysisParserException();
	}
	
	private CATAnalysis tryToParse(String fileName, InputStream is) throws CATAnalysisParserException {
		CATAnalysis finalResult = new CATAnalysis(getCATTool(), fileName);
					
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is, charset));
			boolean atLeastOneFound = false;
			while (findLine(br, get(LanguageKey.ANALYSIS), false) != null) {
				// for each analysis (it may be for a different file, a set of files, different TM settings etc)
				CATAnalysis curResult = new CATAnalysis(getCATTool(), fileName);
				
				parseBlockLine(curResult, br, get(LanguageKey.ALL), MatchType.TOTAL);
				parseBlockLine(curResult, br, get(LanguageKey.REPETITION), MatchType.REPETITIONS);
				parseBlockLine(curResult, br, "101%", MatchType.PERCENT_101);
				parseBlockLine(curResult, br, "100%", MatchType.PERCENT_100);
				parseBlockLine(curResult, br, "95%-99%", MatchType.PERCENT_95_99);
				parseBlockLine(curResult, br, "85%-94%", MatchType.PERCENT_85_94);
				parseBlockLine(curResult, br, "75%-84%", MatchType.PERCENT_75_84);
				parseBlockLine(curResult, br, "50%-74%", MatchType.PERCENT_50_74);
				parseBlockLine(curResult, br, get(LanguageKey.FRAGMENTS), MatchType.FRAGMENTS);
				parseBlockLine(curResult, br, get(LanguageKey.NO_MATCH), MatchType.NO_MATCH);
	
				
				// verify data integrity
				curResult.verifyDataIntegrity();
	
				if (curResult.get(Unit.SEGMENT, MatchType.TOTAL).compareTo(finalResult.get(Unit.SEGMENT, MatchType.TOTAL)) == 1) {
					// we are interested in the analysis of maximum values
					// (this should be an analysis for all the files, not the individual analysis)
					finalResult = new CATAnalysis(curResult);
				}
	
				atLeastOneFound = true;
			}
			return atLeastOneFound ? finalResult : null;
		}
		catch (IOException e) {
			throw new CATAnalysisParserException("Error reading analysis file", e);
		} 
	}
	
	private String get(LanguageKey key) throws CATAnalysisParserException {
		String value = languageKeys.get(key);
		if (value == null) {
			throw new CATAnalysisParserException("No value defined for language key: " + key.name());
		}
		return value;
	}

	private void parseBlockLine(CATAnalysis curResult, BufferedReader br, String value, MatchType matchType) throws IOException, CATAnalysisParserException  {
		String line = findLine(br, value, true);
		if (line == null) {
			return;
		}
		String[] items = line.split(Pattern.quote(delim));
		if (items.length < 3) {
			throw new CATAnalysisParserException();
		}

		curResult.add(Unit.SEGMENT, matchType, new BigDecimal(items[1]));
		curResult.add(Unit.WORD, matchType, new BigDecimal(items[2]));
		curResult.add(Unit.CHARACTER, matchType, new BigDecimal(items[3]));
	}

	private String findLine(BufferedReader br, String value, boolean prefix) throws IOException {
		String line;
		int linesLimitToRead = 50;
		int linesRead = 0;
		while ((line = br.readLine()) != null) {
			if (prefix) {
				if (line.startsWith(value)) {
					return line;
				}
			} else {
				if (line.equals(value)) {
					return line;
				}
			}
			linesRead++;
			if (linesRead > linesLimitToRead) {
				return null;
			}
		}
		return null;
	}

}
