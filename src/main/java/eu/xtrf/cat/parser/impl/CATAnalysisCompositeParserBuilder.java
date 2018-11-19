package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.parser.CATAnalysisParser;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author Michal Krzywucki <krzywucki@gmail.com>
 * @Date 2014-06-05 14:07
 */
public class CATAnalysisCompositeParserBuilder {

    private List<CATAnalysisParser> parsers = new LinkedList<CATAnalysisParser>();

    private Optional<Long> maybeTimeout;

    public CATAnalysisCompositeParserBuilder(Optional<Long> maybeTimeout) {
        this.maybeTimeout = maybeTimeout;
    }

    public CATAnalysisCompositeParserBuilder with(CATAnalysisParser parser){
        parsers.add(parser);
        return this;
    }

    public CATAnalysisCompositeParser build(){
        return new CATAnalysisCompositeParser(parsers, maybeTimeout);
    }
}
