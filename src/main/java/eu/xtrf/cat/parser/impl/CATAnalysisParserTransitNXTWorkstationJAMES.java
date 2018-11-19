package eu.xtrf.cat.parser.impl;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;


public class CATAnalysisParserTransitNXTWorkstationJAMES extends CATAnalysisParserBase {

	@Override
	public CATTool getCATTool() {
		return CATTool.TRANSIT_NXT;
	}

	@Override
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
		
		if (fileName.endsWith(".htm") || fileName.endsWith(".html"))
			throw new CATAnalysisParserException();

		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(is);
			// xtranslated
			List<?> list = document.selectNodes("/transitstat/stattotal/pretranslated");
			if (list.size() != 1) {
				throw new CATAnalysisParserException();
			}
			int pretranslated = Integer.parseInt(((Element) list.get(0)).getText());
			result.set(Unit.WORD, MatchType.PRETRANSLATED, pretranslated);
			
			list = document.selectNodes("/transitstat/stattotal/parttranslated");
			if (list.size() != 1) {
				throw new CATAnalysisParserException();
			}
			int parttranslated = Integer.parseInt(((Element) list.get(0)).getText());
			result.set(Unit.WORD, MatchType.CHECK_PRETRANSLATION, parttranslated);

			// 100%
			list = document.selectNodes("/transitstat/stattotal/fuzzy100");
			if (list.size() != 1) {
				throw new CATAnalysisParserException();
			}
			result.set(Unit.WORD, MatchType.PERCENT_100, Integer.parseInt(((Element) list.get(0)).getText()));

			// fuzzy
			list = document.selectNodes("/transitstat/stattotal/fuzzy");
			for (Object o : list) {
				Element element = (Element) o;
				Element hi = element.element("fuzzyhi");
				int value = Integer.parseInt(element.element("value").getText());
				if ("99".equals(hi.getText())) {
					result.set(Unit.WORD, MatchType.PERCENT_95_99, value);
				} else if ("94".equals(hi.getText())) {
					result.set(Unit.WORD, MatchType.PERCENT_85_94, value);
				} else if ("84".equals(hi.getText())) {
					result.set(Unit.WORD, MatchType.PERCENT_75_84, value);
				} else if ("74".equals(hi.getText())) {
					result.set(Unit.WORD, MatchType.PERCENT_50_74, value);
				} else {
					throw new CATAnalysisParserException();
				}
			}
			// no match
			list = document.selectNodes("/transitstat/stattotal/not-translated");
			if (list.size() != 1) {
				throw new CATAnalysisParserException();
			}
			result.set(Unit.WORD, MatchType.NOT_TRANSLATED, Integer.parseInt(((Element) list.get(0)).getText()));

			// total
			list = document.selectNodes("/transitstat/stattotal/total");
			if (list.size() != 1) {
				throw new CATAnalysisParserException();
			}
			result.set(Unit.WORD, MatchType.TOTAL,  Integer.parseInt(((Element) list.get(0)).getText()));
		} catch (Throwable t) {
			throw new CATAnalysisParserException();
		}

		result.verifyDataIntegrity();

		return result;
	}
}
