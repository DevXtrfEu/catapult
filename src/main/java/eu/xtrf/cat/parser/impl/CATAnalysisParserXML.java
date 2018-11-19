package eu.xtrf.cat.parser.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import eu.xtrf.cat.parser.CATAnalysisParserException;

import static com.google.common.base.MoreObjects.firstNonNull;

public class CATAnalysisParserXML extends CATAnalysisParserBase {
	
	private ErrorHandler errorHandler = new XMLParserErrorHandler();
	
	@Override
	public CATTool getCATTool() {
		return CATTool.XTM;
	}

	public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
		CATAnalysis result = new CATAnalysis(getCATTool(), fileName);
		
		InputSource iss = new InputSource(is);

		XMLParserContentHandler contentHandler = new XMLParserContentHandler(result);
		
		try {
			XMLReader parser = XMLReaderFactory.createXMLReader();
			parser.setContentHandler(contentHandler);
			parser.setErrorHandler(errorHandler);
			parser.setFeature("http://xml.org/sax/features/namespaces", false);
			parser.setFeature("http://xml.org/sax/features/namespace-prefixes", false);
			parser.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

			parser.parse(iss);
		} catch (IOException e) {
			throw new CATAnalysisParserException();
		} catch (SAXException e) {
			throw new CATAnalysisParserException();
		}

		if (!contentHandler.isXMLFormatRecognized()) {
			throw new CATAnalysisParserException();
		}
		
		result.verifyDataIntegrity();
		
		return result;
	}
}

class XMLParserErrorHandler implements ErrorHandler {

	private static final Logger logger = Logger.getLogger(XMLParserErrorHandler.class.getName());

	public void error(SAXParseException exception) throws SAXException {
		logger.debug("Error occured in: line " + exception.getLineNumber() + ", column: " + exception.getColumnNumber() + "\n"
				+ exception.getMessage());
		throw new SAXException("Error while parsing XML file");
	}

	public void fatalError(SAXParseException exception) throws SAXException {
		logger.debug("Fatal error occured in: line " + exception.getLineNumber() + ", column: " + exception.getColumnNumber() + "\n"
				+ exception.getMessage());
		throw new SAXException("Fatal error while parsing XML file");
	}

	public void warning(SAXParseException exception) throws SAXException {
		logger.debug("Warning occured in: line " + exception.getLineNumber() + ", column: " + exception.getColumnNumber() + "\n"
				+ exception.getMessage());
		throw new SAXException("Warning while parsing XML file");
	}

}

class XMLParserContentHandler implements ContentHandler {

	/*
	 * WordsTotal = TotalWordCount WordsXTransalted = ExactMatchedWordCount WordsRepetition = RepetitionMatchedWordCount
	 * Words100 = LeveragedMatchedWordCount Words9599 = RepetitionMatchedWordCount(95-99) + FuzzyMatchedWordCount(95-99)
	 * Words8594 = RepetitionMatchedWordCount(85-94) + FuzzyMatchedWordCount(85-94) Words7584 =
	 * RepetitionMatchedWordCount(70-84) + FuzzyMatchedWordCount(70-84) Words5074 = 0 WordsNoMatch = RealWordCount
	 */

	private static final Map<MatchTypeKey, MatchType> matchTypes = ImmutableMap.<MatchTypeKey, MatchType>builder()
		.put(MatchTypeKey.of("TotalWordCount"), MatchType.TOTAL)
		.put(MatchTypeKey.of("ExactMatchedWordCount"), MatchType.ICE_MATCH)
		.put(MatchTypeKey.of("LeveragedMatchedWordCount"), MatchType.LEVERAGED_MATCH)
		.put(MatchTypeKey.of("FuzzyMatchedWordCount", "95-99"), MatchType.INTERNAL_95_99)
		.put(MatchTypeKey.of("FuzzyMatchedWordCount", "85-94"), MatchType.INTERNAL_85_94)
		.put(MatchTypeKey.of("FuzzyMatchedWordCount", "75-84"), MatchType.INTERNAL_75_84)
		.put(MatchTypeKey.of("FuzzyMatchedWordCount", "70-84"), MatchType.INTERNAL_75_84)
		.put(MatchTypeKey.of("RepetitionMatchedWordCount"), MatchType.REPETITIONS)
		.put(MatchTypeKey.of("RepetitionMatchedWordCount", "95-99"), MatchType.PERCENT_95_99)
		.put(MatchTypeKey.of("RepetitionMatchedWordCount", "85-94"), MatchType.PERCENT_85_94)
		.put(MatchTypeKey.of("RepetitionMatchedWordCount", "75-84"), MatchType.PERCENT_75_84)
		.put(MatchTypeKey.of("RepetitionMatchedWordCount", "70-84"), MatchType.PERCENT_75_84)
		.put(MatchTypeKey.of("RealWordCount"), MatchType.NO_MATCH)
		.put(MatchTypeKey.of("x-RealWordCount"), MatchType.NO_MATCH)
		.put(MatchTypeKey.of("AlphanumericOnlyTextUnitWordCount"), MatchType.NO_MATCH)
		.put(MatchTypeKey.of("MeasurementOnlyTextUnitWordCount"), MatchType.NO_MATCH)
		.put(MatchTypeKey.of("NumericOnlyTextUnitWordCount"), MatchType.NO_MATCH)
		.put(MatchTypeKey.of("PunctuationOnlyTextUnitWordCount"), MatchType.NO_MATCH)
		.build();

	private boolean XMLFormatRecognized = false;

	private CATAnalysis result;

	public XMLParserContentHandler(CATAnalysis result) {
		this.result = result;
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
	}

	public void endDocument() throws SAXException {
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
	}

	public void endPrefixMapping(String prefix) throws SAXException {
	}

	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
	}

	public void processingInstruction(String target, String data) throws SAXException {
	}

	public void setDocumentLocator(Locator locator) {
	}

	public void skippedEntity(String name) throws SAXException {
	}

	public void startDocument() throws SAXException {
	}

	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		if ("metrics:metrics".equals(qName)) {
			XMLFormatRecognized = true;
		}
		MatchTypeKey matchTypeKey = MatchTypeKey.from(atts);
		if (matchTypeKey != null) {
			MatchType matchType = matchTypes.get(matchTypeKey);
			if (matchType != null) {
				BigDecimal value = firstNonNull(result.get(Unit.WORD, matchType), BigDecimal.ZERO)
					.add(BigDecimal.valueOf(Integer.valueOf(atts.getValue("", "value"))));
				result.set(Unit.WORD, matchType, value);
			}
		}
	}

	public void startPrefixMapping(String prefix, String uri) throws SAXException {
	}

	public boolean isXMLFormatRecognized() {
		return XMLFormatRecognized;
	}

	private static final class MatchTypeKey {
		private final String type;
		private final String category;

		private MatchTypeKey(String type, String category) {
			this.type = type;
			this.category = category;
		}

		private static MatchTypeKey of(String type) {
			return new MatchTypeKey(type, null);
		}

		private static MatchTypeKey of(String type, String category) {
			return new MatchTypeKey(type, category);
		}

		private static MatchTypeKey from(Attributes atts) {
			String type = atts.getValue("", "type");
			if (type == null) {
				return null;
			}
			return new MatchTypeKey(type, atts.getValue("", "category"));
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			MatchTypeKey that = (MatchTypeKey) o;

			if (category != null ? !category.equals(that.category) : that.category != null) return false;
			if (!type.equals(that.type)) return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = type.hashCode();
			result = 31 * result + (category != null ? category.hashCode() : 0);
			return result;
		}
	}
}
