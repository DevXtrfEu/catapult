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
 * CATAnalysisParserMemoQHTML.java, created: 12-11-2012.
 */
package eu.xtrf.cat.parser.impl;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;
import eu.xtrf.cat.parser.impl.CATAnalysisMemoqConfigurations.LanguageKey;


/**
 * Analysis of memoQ metrics in HTML format.
 * 
 * @author Daniel Bochniak
 */
public class CATAnalysisParserMemoQHTML extends CATAnalysisParserBase {
	
	private static Logger logger = Logger.getLogger(CATAnalysisParserMemoQHTML.class);
	
	private Map<LanguageKey, String> languageKeys;
	
	private  Map<MatchType, String> matchTypeLabelsRequired = new HashMap<>();
    private  Map<MatchType, String> matchTypeLabelsOptional = new HashMap<>();
	
	public CATAnalysisParserMemoQHTML() {
		
	}
	
	CATAnalysisParserMemoQHTML(Map<LanguageKey, String> languageKeys) {
		this.languageKeys = languageKeys;
		matchTypeLabelsRequired.put(MatchType.TOTAL, languageKeys.get(LanguageKey.ALL));
        matchTypeLabelsRequired.put(MatchType.REPETITIONS, languageKeys.get(LanguageKey.REPETITION));
        matchTypeLabelsRequired.put(MatchType.PERCENT_101, "101%");
        matchTypeLabelsRequired.put(MatchType.PERCENT_100, "100%");
        matchTypeLabelsRequired.put(MatchType.PERCENT_95_99, "95%-99%");
        matchTypeLabelsRequired.put(MatchType.PERCENT_85_94, "85%-94%");
        matchTypeLabelsRequired.put(MatchType.PERCENT_75_84, "75%-84%");
        matchTypeLabelsRequired.put(MatchType.PERCENT_50_74, "50%-74%");
        matchTypeLabelsRequired.put(MatchType.NO_MATCH, languageKeys.get(LanguageKey.NO_MATCH));
        matchTypeLabelsOptional.put(MatchType.X_TRANSLATED, "X-translated");
	}
	
	public CATTool getCATTool() {
		return CATTool.MEMO_Q;
	}
	
	private Collection<CATAnalysisParserMemoQHTML> createWordStats() {
		return FluentIterable.from(CATAnalysisMemoqConfigurations.ALL_LANGUAGE_KEYS)
			.transform(new Function<Map<LanguageKey, String>,CATAnalysisParserMemoQHTML>() {
				@Override
				public CATAnalysisParserMemoQHTML apply(Map<LanguageKey, String> languageKeys) {
					return new CATAnalysisParserMemoQHTML(languageKeys);
				}
			}).toList();
	};
	
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		byte[] data;
		try {
			data = IOUtils.toByteArray(is);
		} catch (IOException e) {
			throw new CATAnalysisParserException("Failed to read cat analysis file: " + fileName, e);
		}
		
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		
		for (CATAnalysisParserMemoQHTML wordStat : createWordStats()) {
			try {
				bis.reset();
		        Optional<Map<Unit, Integer>> unitMap = wordStat.findUnitMap(fileName, bis);
				
                bis.reset();
				CATAnalysis catAnalysisResult = wordStat.tryToParse(fileName, bis, unitMap);
				if (catAnalysisResult.getCATTool() != null) {
					if(logger.isDebugEnabled()) {
						logger.debug(wordStat.languageKeys.get(LanguageKey.ALL));
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
	
	/**
	 * @return optional mapping between Units and indexes of columns in html table where values 
	 *         for corresponding Units are to be found  
	 */
	private Optional<Map<Unit, Integer>> findUnitMap(String fileName, InputStream is) {
	    try {
    	    Document doc = Jsoup.parse(is, "UTF-8", "");
    	    Elements tables = doc.select("table");
    	    Optional<Element> maybeTable = tables.stream().filter(elem -> tableMatchLanguageKeys(elem)).findFirst();
    	    if( maybeTable.isPresent()) {
    	        Element header = maybeTable.get().select("tr").get(0);
    	        return scanUnitKeys(header);
    	    }
	    } catch(IOException ex) {
	        logger.error("Failed to scan html for file: " + fileName, ex);
	    }
    	return Optional.empty();
	}
	
    /**
    *
    * @param table - jsoup table element 
    * @return true if html table element contains in first column our MatchType
    *         strings in proper order (as defined in CATAnalysisMemoqConfigurations.matchTypes) 
    */
    private boolean tableMatchLanguageKeys(Element table) {
       Elements rows = table.select("tr");
       Iterator<MatchType> matchTypeIter = CATAnalysisMemoqConfigurations.matchTypes.iterator();
       Optional<MatchType> maybeMatchType = Optional.of(matchTypeIter.next());
       for(Element row : rows ) {
           if (maybeMatchType.isPresent() &&
                   row.select("td").get(0).text().toUpperCase().startsWith(matchTypeLabelsRequired.get(maybeMatchType.get()).toUpperCase())) {
               maybeMatchType = matchTypeIter.hasNext() ? Optional.of(matchTypeIter.next()) : Optional.empty();
           }
       }
       return matchTypeIter.hasNext() ? false : true;
    }
	
	private Optional<Map<Unit, Integer>> scanUnitKeys(Element header) {
	    return CATAnalysisMemoqConfigurations.SUPPORTED_UNIT_KEYS.stream()
    	        .map(keyMap -> rowMatchUnitKeys(header, keyMap))
    	        .filter(op -> op.isPresent())
    	        .findFirst()
    	        .orElse(Optional.empty());
	}
	
    private Optional<Map<Unit, Integer>> rowMatchUnitKeys(Element header, Map<Unit, String> unitKeyMap) {
        Elements cols = header.select("td");
        Map<Unit, Integer> result = new HashMap<>();
        Set<Unit> units = new HashSet<>(unitKeyMap.keySet());
        for(int i = 1; i < cols.size(); i++) {
            final int index = i;
            Optional<Entry<Unit,String>> maybeEntry = unitKeyMap.entrySet().stream()
                                                        .filter(e -> cols.get(index).text().equalsIgnoreCase(e.getValue()))
                                                        .findFirst();
            if (maybeEntry.isPresent()) {
                result.put(maybeEntry.get().getKey(), index);
                units.remove(maybeEntry.get().getKey());
            }
        }
        return units.isEmpty() ? Optional.of(result) : Optional.empty();
    }
    
    private CATAnalysis tryToParse(String fileName, InputStream is, Optional<Map<Unit, Integer>> unitMap) throws CATAnalysisParserException {
        CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
    
        if (unitMap.isPresent()) {
            parseHtmlTable(result, is, unitMap.get());
        } else {
            Scanner sc = new Scanner(is, "UTF-8");
            sc.useLocale(Locale.US);
            sc.useDelimiter("<");
            parseBlock(result, sc);
        }
        return result;
    }

	private void parseBlock(CATAnalysis result, Scanner sc) throws CATAnalysisParserException {
		try {
			sc.findWithinHorizon(get(LanguageKey.ANALYSIS), 0);
			
			sc.findWithinHorizon(get(LanguageKey.ALL), 0);
			readValues(sc, result, MatchType.TOTAL);

			if(sc.findWithinHorizon("X-translated", 0) != null) {
				readValues(sc, result, MatchType.X_TRANSLATED);
			}	

			sc.findWithinHorizon(get(LanguageKey.REPETITION), 0);
			readValues(sc, result, MatchType.REPETITIONS);

			sc.findWithinHorizon("101%", 0);
			readValues(sc, result, MatchType.PERCENT_101);

			sc.findWithinHorizon("100%", 0);
			readValues(sc, result, MatchType.PERCENT_100);

			sc.findWithinHorizon("95%-99%", 0);
			readValues(sc, result, MatchType.PERCENT_95_99);

			sc.findWithinHorizon("85%-94%", 0);
			readValues(sc, result, MatchType.PERCENT_85_94);

			sc.findWithinHorizon("75%-84%", 0);
			readValues(sc, result, MatchType.PERCENT_75_84);

			sc.findWithinHorizon("50%-74%", 0);
			readValues(sc, result, MatchType.PERCENT_50_74);

			sc.findWithinHorizon(get(LanguageKey.NO_MATCH), 0);
			readValues(sc, result, MatchType.NO_MATCH);

			result.verifyDataIntegrity();
		} catch (Exception e) {
			throw new CATAnalysisParserException();
		}
	}

	private int getInt(Scanner sc) {
		sc.findWithinHorizon("</td>\\s*<td>", 0); 
		//in new memoq analysis it can be of format "1638 (0.0)" while old ones may contain "1638"
		String nextInt = sc.next().split(" ")[0];
		return Integer.parseInt(nextInt);
	}
	
	private void readValues(Scanner sc, CATAnalysis result, MatchType matchType) {
		result.set(Unit.SEGMENT,	matchType, 	getInt(sc));
		result.set(Unit.WORD, 		matchType, 	getInt(sc));
		result.set(Unit.CHARACTER, 	matchType, 	getInt(sc));
	}
	
	private String get(LanguageKey key) throws CATAnalysisParserException  {
		String value = languageKeys.get(key);
		if (value == null) {
			throw new CATAnalysisParserException("No value defined for language key: " + key.name());
		}
		return value;
	}

    private void parseHtmlTable(CATAnalysis result, InputStream is, Map<Unit, Integer> unitMap) throws CATAnalysisParserException {
        Document doc; 
        try {
            doc = Jsoup.parse(is, "UTF-8", "");
        } catch(IOException ex) {
            throw new CATAnalysisParserException();
        }
        Elements tables = doc.select("table");
        Element table = tables.stream().filter(elem -> tableMatchLanguageKeys(elem)).findFirst().orElseThrow(CATAnalysisParserException::new);
        Elements rows = table.select("tr");
        Iterator<MatchType> matchTypeIter = CATAnalysisMemoqConfigurations.matchTypes.iterator();
        Optional<MatchType> maybeMatchType = Optional.of(matchTypeIter.next());
        for(Element row : rows ) {
            if (maybeMatchType.isPresent() &&
                    row.select("td").get(0).text().toUpperCase().startsWith(matchTypeLabelsRequired.get(maybeMatchType.get()).toUpperCase())) {
                readHtmlRowValues(row, result, maybeMatchType.get(), unitMap);
                maybeMatchType = matchTypeIter.hasNext() ? Optional.of(matchTypeIter.next()) : Optional.empty();
            } else {
                Optional<Entry<MatchType, String>> maybeEntry = matchTypeLabelsOptional.entrySet().stream()
                                                            .filter( e -> row.select("td").get(0).text().toUpperCase().startsWith(e.getValue().toUpperCase()))
                                                            .findFirst();
                if (maybeEntry.isPresent()) {
                    readHtmlRowValues(row, result, maybeEntry.get().getKey(), unitMap);
                }
            }
        }
        if(maybeMatchType.isPresent()) {
            logger.warn("No row found for MatchType: " + maybeMatchType.get().toString());
            throw new CATAnalysisParserException();
        }
        result.verifyDataIntegrity();
    }

    private void readHtmlRowValues(Element row, CATAnalysis result, MatchType matchType, Map<Unit, Integer> unitMap) {
        result.set(Unit.SEGMENT,    matchType,  getHtmlCellInt(row, unitMap.get(Unit.SEGMENT)));
        result.set(Unit.WORD,       matchType,  getHtmlCellInt(row, unitMap.get(Unit.WORD)));
        result.set(Unit.CHARACTER,  matchType,  getHtmlCellInt(row, unitMap.get(Unit.CHARACTER)));
    }
    
    private int getHtmlCellInt(Element row, int cellIndex) {
        //in new memoq analysis it can be of format "1638 (0.0)" while old ones may contain "1638"
        String value = row.select("td").get(cellIndex).text().split(" ")[0];
        return Integer.parseInt(value);
    }
    
}
