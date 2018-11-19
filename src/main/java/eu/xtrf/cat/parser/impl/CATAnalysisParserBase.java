package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.parser.CATAnalysisParser;
import eu.xtrf.cat.parser.CATAnalysisParserException;

import java.io.ByteArrayInputStream;

/**
 * @author Michal Krzywucki <krzywucki@gmail.com>
 * @Date 06.06.14 09:31
 */
public abstract class CATAnalysisParserBase implements CATAnalysisParser {

    @Override
    public CATAnalysis parse(String fileName, byte[] content) throws CATAnalysisParserException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(content, 0, content.length);
        return this.parse(fileName, inputStream);
    }
}
