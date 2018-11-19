package eu.xtrf.cat.parser;

/**
 * Exception that may be thrown while parsing CAT analysis.
 * 
 * @author Lukasz Wiktor
 */
public class CATAnalysisParserException extends Exception {

	private static final long serialVersionUID = 0x1CE;
	
	public CATAnalysisParserException() {
		super();
	}
	
	public CATAnalysisParserException(String message) {
		super(message);
	}
	
	public CATAnalysisParserException(Throwable cause) {
		super(cause);
	}
	
	public CATAnalysisParserException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
