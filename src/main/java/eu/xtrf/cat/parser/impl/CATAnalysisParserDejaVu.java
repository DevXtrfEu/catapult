package eu.xtrf.cat.parser.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;

public class CATAnalysisParserDejaVu extends CATAnalysisParserBase {

	private Map<MatchType, String> localization;
	
	public CATAnalysisParserDejaVu() {
		
	}
	
	CATAnalysisParserDejaVu(Map<MatchType, String> localization) {
		this.localization = new HashMap<>(localization);
	}
	
	@Override
	public CATTool getCATTool() {
		return CATTool.DEJA_VU_DETAILED;
	}

	@Override
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		byte[] data;
		try {
			data = IOUtils.toByteArray(is);
		} catch (IOException e) {
			throw new CATAnalysisParserException("Failed to read cat analysis file: " + fileName, e);
		}
		
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);

		for (CATAnalysisParserDejaVu parser : createWordStats()) {
			try {
				bis.reset();
				
				Scanner sc = new Scanner(bis, "UTF-8");
				sc.useLocale(Locale.US);
		
				parser.parseBlock(result, sc);
								
				return result;
			} catch (Exception e) {
				continue;
				// ignore, invalid metrics for the word stat were supplied
			}
		}
		
		throw new CATAnalysisParserException();
	}

	private CATAnalysisParserDejaVu[] createWordStats() {
		return new CATAnalysisParserDejaVu[] {
			new CATAnalysisParserDejaVu(getLocalizationEn()),
			new CATAnalysisParserDejaVu(getLocalizationEs())
		};
	}
	
	private void parseBlock(CATAnalysis result, Scanner sc) throws CATAnalysisParserException {
		parseBlockLine(result, sc, localization.get(MatchType.GUARANTEED), MatchType.GUARANTEED);
		parseBlockLine(result, sc, localization.get(MatchType.EXACT), MatchType.EXACT);
		parseBlockLine(result, sc, localization.get(MatchType.DUPLICATES), MatchType.DUPLICATES);
		parseBlockLine(result, sc, "99%", MatchType.PERCENT_99);
		parseBlockLine(result, sc, "98%", MatchType.PERCENT_98);
		parseBlockLine(result, sc, "97%", MatchType.PERCENT_97);
		parseBlockLine(result, sc, "96%", MatchType.PERCENT_96);
		parseBlockLine(result, sc, "95%", MatchType.PERCENT_95);
		parseBlockLine(result, sc, "90%-94%", MatchType.PERCENT_90_94);
		parseBlockLine(result, sc, "80%-89%", MatchType.PERCENT_80_89);
		parseBlockLine(result, sc, "70%-79%", MatchType.PERCENT_70_79);
		parseBlockLine(result, sc, "60%-69%", MatchType.PERCENT_60_69);
		parseBlockLine(result, sc, "50%-59%", MatchType.PERCENT_50_59);
		parseBlockLine(result, sc, "40%-49%", MatchType.PERCENT_40_49);
		parseBlockLine(result, sc, "30%-39%", MatchType.PERCENT_30_39);
		parseBlockLine(result, sc, "20%-29%", MatchType.PERCENT_20_29);
		parseBlockLine(result, sc, "10%-19%", MatchType.PERCENT_10_19);
		parseBlockLine(result, sc, "0%-9%", MatchType.PERCENT_0_9);
		parseBlockLine(result, sc, localization.get(MatchType.NO_MATCH), MatchType.NO_MATCH);
		parseBlockLine(result, sc, "Total:", MatchType.TOTAL);

		result.verifyDataIntegrity();
	}

	private void parseBlockLine(final CATAnalysis result, final Scanner sc, final String firstCol, final MatchType matchType) {
		sc.findWithinHorizon(firstCol, 0);
		
		result.add(Unit.SEGMENT, matchType, sc.nextBigDecimal());
		result.add(Unit.WORD, matchType, sc.nextBigDecimal());
		result.add(Unit.CHARACTER, matchType, sc.nextBigDecimal());
	}
	
	private Map<MatchType, String> getLocalizationEn() {
		Map<MatchType, String> localization_en = new HashMap<>();
		
		localization_en.put(MatchType.DUPLICATES, "Duplicate");
		localization_en.put(MatchType.GUARANTEED, "Guaranteed");
		localization_en.put(MatchType.EXACT, "Exact");
		localization_en.put(MatchType.NO_MATCH, "No Match");
		
		return localization_en;
	}
	
	private Map<MatchType, String> getLocalizationEs() {
		Map<MatchType, String> localization_en = new HashMap<>();

		localization_en.put(MatchType.DUPLICATES, "Duplicada\\(s\\)");
		localization_en.put(MatchType.GUARANTEED, "Garantizada\\(s\\)");
		localization_en.put(MatchType.EXACT, "Exacta\\(s\\)");
		localization_en.put(MatchType.NO_MATCH, "Sin correspondencia");

		return localization_en;
	}
}
