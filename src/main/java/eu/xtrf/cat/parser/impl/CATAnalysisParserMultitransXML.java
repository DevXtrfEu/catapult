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


public class CATAnalysisParserMultitransXML extends CATAnalysisParserBase {

	@Override
	public CATTool getCATTool() {
		return CATTool.MULTITRANS;
	}

	@Override
	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
		
		if (fileName.endsWith(".htm") || fileName.endsWith(".html"))
			throw new CATAnalysisParserException();
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(is);
			
			result.set(Unit.SEGMENT, MatchType.PERCENT_100, extractValue(document, "/summary/analysisSummary/exactSegmentMatch/numSegmentMatch"));
			result.set(Unit.WORD, MatchType.PERCENT_100, extractValue(document, "/summary/analysisSummary/exactSegmentMatch/numWordMatch"));
			
			result.set(Unit.SEGMENT, MatchType.PERCENT_85_99, extractValue(document, "/summary/analysisSummary/fuzzySegmentMatch[@percentMatch='85-99']/numSegmentMatch"));
			result.set(Unit.WORD, MatchType.PERCENT_85_99, extractValue(document, "/summary/analysisSummary/fuzzySegmentMatch[@percentMatch='85-99']/numWordMatch"));
						
			result.set(Unit.SEGMENT, MatchType.PERCENT_75_84, extractValue(document, "/summary/analysisSummary/fuzzySegmentMatch[@percentMatch='75-84']/numSegmentMatch"));
			result.set(Unit.WORD, MatchType.PERCENT_75_84, extractValue(document, "/summary/analysisSummary/fuzzySegmentMatch[@percentMatch='75-84']/numWordMatch"));
						
			result.set(Unit.SEGMENT, MatchType.REPETITIONS, extractValue(document, "/summary/analysisSummary/internalRepetitionsExact/numSegmentMatch")
					+ extractValue(document, "/summary/analysisSummary/internalRepetitionsFuzzy/numSegmentMatch"));
			result.set(Unit.WORD, MatchType.REPETITIONS, extractValue(document, "/summary/analysisSummary/internalRepetitionsExact/numWordMatch")
					+ extractValue(document, "/summary/analysisSummary/internalRepetitionsFuzzy/numWordMatch"));
						
			result.set(Unit.SEGMENT, MatchType.NEW, extractValue(document, "/summary/analysisSummary/newSegments/numSegmentMatch"));
			result.set(Unit.WORD, MatchType.NEW, extractValue(document, "/summary/analysisSummary/newSegments/numWordMatch"));
						
			result.set(Unit.SEGMENT, MatchType.TOTAL, extractValue(document, "/summary/analysisSummary/totalAnalysisSummaryMatch/numSegmentMatch"));
			result.set(Unit.WORD, MatchType.TOTAL, extractValue(document, "/summary/analysisSummary/totalAnalysisSummaryMatch/numWordMatch"));
			
		} catch (Throwable t) {
			throw new CATAnalysisParserException();
		}

		result.verifyDataIntegrity();

		return result;
	}

	private int extractValue(Document document, String xpath) throws CATAnalysisParserException {
		List<?> list = document.selectNodes(xpath);
		if (list.size() != 1) {
			throw new CATAnalysisParserException();
		}
		return Integer.parseInt(((Element) list.get(0)).getText());
	}
}
