package eu.xtrf.cat.parser;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;

import java.io.InputStream;

/**
 * Interface of CATAnalysisParser
 * 
 * @author Lukasz Wiktor
 * 
 */
public interface CATAnalysisParser {

	/**
	 * @return CAT Tool of which this parser recognize analysis.
	 */
	CATTool getCATTool();

	/**
	 * Parses analysis included in given input stream and extracts values in particular match types.
	 * 
	 * @param fileName
	 *            name of a file containing CAT analysis
	 * @param is
	 *            input stream
	 * 
	 * @return values extracted from CAT analysis
	 * 
	 * @throws CATAnalysisParserException
	 */
	CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException;


    CATAnalysis parse(String fileName, byte[] content) throws CATAnalysisParserException;

}
