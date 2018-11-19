package eu.xtrf.cat.parser;

public class CATAnalysisParserTimeoutException extends CATAnalysisParserException {
    public CATAnalysisParserTimeoutException() {
        super();
    }

    public CATAnalysisParserTimeoutException(String message) {
        super(message);
    }

    public CATAnalysisParserTimeoutException(Throwable cause) {
        super(cause);
    }

    public CATAnalysisParserTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
