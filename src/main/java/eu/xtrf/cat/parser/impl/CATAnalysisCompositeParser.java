package eu.xtrf.cat.parser.impl;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Sets;
import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.parser.CATAnalysisParser;
import eu.xtrf.cat.parser.CATAnalysisParserException;
import eu.xtrf.cat.parser.CATAnalysisParserNotSupportedMultilingualAnalysisException;
import eu.xtrf.cat.parser.CATAnalysisParserTimeoutException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Composite parser that provides "choose the right one" parser functionality. It iterates over the injected
 * parser list trying to parse the file one after another the first to successfully parse the content wins.
 *
 * @author Michal Krzywucki <krzywucki@gmail.com>
 * @Date 2014-06-05 11:22
 */
public class CATAnalysisCompositeParser extends CATAnalysisParserBase {

    public static final int MAX_ANALYSIS_SIZE = 1024 * 1024 * 10; //10 MByte
    private static final Collection<String> KNOWN_ANALYSIS_FILE_EXTENSIONS =
            Sets.newHashSet("csv", "htm", "html", "james", "log", "mht", "mhtml","rep" ,"rtf", "txt", "xlf", "xls", "xlsx", "xml", "zip");

    public static CATAnalysisCompositeParser newInstance(){
        return newInstance(Optional.empty());
    }

    public static CATAnalysisCompositeParser newInstance(Optional<Long> maybeTimeout){
        CATAnalysisCompositeParserBuilder rootParserBuilder = new CATAnalysisCompositeParserBuilder(maybeTimeout);

        CATAnalysisCompositeParserBuilder sdltmsXlsxBuilder = new CATAnalysisCompositeParserBuilder(maybeTimeout);
        sdltmsXlsxBuilder
        	.with(new CATAnalysisParserSDLTMSXLSX(CATAnalysisLocalizedSDLTMSParser.ENGLISH))
        	.with(new CATAnalysisParserSDLTMSXLSX(CATAnalysisLocalizedSDLTMSParser.ENGLISH_2017))
        	.with(new CATAnalysisParserSDLTMSXLSX(CATAnalysisLocalizedSDLTMSParser.GERMAN))
        	.with(new CATAnalysisParserSDLTMSXLSX(CATAnalysisLocalizedSDLTMSParser.GERMAN_2017))
        	.with(new CATAnalysisParserSDLTMSXLSX(CATAnalysisLocalizedSDLTMSParser.FRENCH))
        	.with(new CATAnalysisParserSDLTMSXLSX(CATAnalysisLocalizedSDLTMSParser.FRENCH_2014))
        	.with(new CATAnalysisParserSDLTMSXLSX(CATAnalysisLocalizedSDLTMSParser.FRENCH_2017))
        	.with(new CATAnalysisParserSDLTMSXLSX(CATAnalysisLocalizedSDLTMSParser.SPANISH))
        	.with(new CATAnalysisParserSDLTMSXLSX(CATAnalysisLocalizedSDLTMSParser.SPANISH_2017))
        	.with(new CATAnalysisParserSDLTMSXLSX(CATAnalysisLocalizedSDLTMSParser.JAPANESE))
        	.with(new CATAnalysisParserSDLTMSXLSX(CATAnalysisLocalizedSDLTMSParser.JAPANESE_2014))
        	.with(new CATAnalysisParserSDLTMSXLSX(CATAnalysisLocalizedSDLTMSParser.JAPANESE_2017))
        	.with(new CATAnalysisParserSDLTMSXLSX(CATAnalysisLocalizedSDLTMSParser.CHINESE))
        	.with(new CATAnalysisParserSDLTMSXLSX(CATAnalysisLocalizedSDLTMSParser.CHINESE_2014))
        	.with(new CATAnalysisParserSDLTMSXLSX(CATAnalysisLocalizedSDLTMSParser.CHINESE_2017))
        	.with(new CATAnalysisParserSDLTMSXLSX(CATAnalysisLocalizedSDLTMSParser.ITALIAN_2017));

         CATAnalysisCompositeParserBuilder sdlTrados2009HTMBuilder = new CATAnalysisCompositeParserBuilder(maybeTimeout);
         sdlTrados2009HTMBuilder
         	.with(new CATAnalysisParserSDLTMS2009HTM(CATAnalysisLocalizedSDLTMSParser.ENGLISH))
            .with(new CATAnalysisParserSDLTMS2009HTM(CATAnalysisLocalizedSDLTMSParser.FRENCH))
            .with(new CATAnalysisParserSDLTMS2009HTM(CATAnalysisLocalizedSDLTMSParser.GERMAN))
            .with(new CATAnalysisParserSDLTMS2009HTM(CATAnalysisLocalizedSDLTMSParser.SPANISH))
            .with(new CATAnalysisParserSDLTMS2009HTM(CATAnalysisLocalizedSDLTMSParser.CHINESE))
            .with(new CATAnalysisParserSDLTMS2009HTM(CATAnalysisLocalizedSDLTMSParser.JAPANESE));

         CATAnalysisCompositeParserBuilder sdlTrados2011HTMBuilder = new CATAnalysisCompositeParserBuilder(maybeTimeout);
         sdlTrados2011HTMBuilder
         	.with(new CATAnalysisParserSDLTMS2011HTM(CATAnalysisLocalizedSDLTMSParser.ENGLISH))
         	.with(new CATAnalysisParserSDLTMS2011HTM(CATAnalysisLocalizedSDLTMSParser.ENGLISH_2017))
            .with(new CATAnalysisParserSDLTMS2011HTM(CATAnalysisLocalizedSDLTMSParser.FRENCH))
            .with(new CATAnalysisParserSDLTMS2011HTM(CATAnalysisLocalizedSDLTMSParser.FRENCH_2017))
            .with(new CATAnalysisParserSDLTMS2011HTM(CATAnalysisLocalizedSDLTMSParser.GERMAN))
            .with(new CATAnalysisParserSDLTMS2011HTM(CATAnalysisLocalizedSDLTMSParser.GERMAN_2017))
            .with(new CATAnalysisParserSDLTMS2011HTM(CATAnalysisLocalizedSDLTMSParser.SPANISH))
            .with(new CATAnalysisParserSDLTMS2011HTM(CATAnalysisLocalizedSDLTMSParser.SPANISH_2017))
            .with(new CATAnalysisParserSDLTMS2011HTM(CATAnalysisLocalizedSDLTMSParser.CHINESE))
            .with(new CATAnalysisParserSDLTMS2011HTM(CATAnalysisLocalizedSDLTMSParser.CHINESE_2017))
            .with(new CATAnalysisParserSDLTMS2011HTM(CATAnalysisLocalizedSDLTMSParser.JAPANESE))
            .with(new CATAnalysisParserSDLTMS2011HTM(CATAnalysisLocalizedSDLTMSParser.JAPANESE_2017))
		 	.with(new CATAnalysisParserSDLTMS2011HTM(CATAnalysisLocalizedSDLTMSParser.FRENCH_2014))
		 	.with(new CATAnalysisParserSDLTMS2011HTM(CATAnalysisLocalizedSDLTMSParser.FRENCH_2017))
			.with(new CATAnalysisParserSDLTMS2011HTM(CATAnalysisLocalizedSDLTMSParser.CHINESE_2014))
			.with(new CATAnalysisParserSDLTMS2011HTM(CATAnalysisLocalizedSDLTMSParser.CHINESE_2017))
			.with(new CATAnalysisParserSDLTMS2011HTM(CATAnalysisLocalizedSDLTMSParser.JAPANESE_2014))
			.with(new CATAnalysisParserSDLTMS2011HTM(CATAnalysisLocalizedSDLTMSParser.ITALIAN))
			.with(new CATAnalysisParserSDLTMS2011HTM(CATAnalysisLocalizedSDLTMSParser.ITALIAN_2017))
			.with(new CATAnalysisParserSDLTMS2011HTM(CATAnalysisLocalizedSDLTMSParser.RUSSIAN))
			.with(new CATAnalysisParserSDLTMS2011HTM(CATAnalysisLocalizedSDLTMSParser.RUSSIAN_2017));

         
        return rootParserBuilder
        		.with(new CATAnalysisParserAcrossHTM())
                .with(new CATAnalysisParserAcrossXML())
                .with(new CATAnalysisParserDejaVu())
                .with(new CATAnalysisParserDejaVuCSV())
                .with(new CATAnalysisParserDejaVuHTM())
                .with(new CATAnalysisParserDejaVuTXT())
                .with(new CATAnalysisParserFortis())
                .with(new CATAnalysisParserIdiom())
                .with(new CATAnalysisParserIdiomWorkbench())
                .with(new CATAnalysisParserLogoportMemsource())
                .with(new CATAnalysisParserMemoQ())
                .with(new CATAnalysisParserMemoQHTML())
                .with(new CATAnalysisParserMemoqTradosCSV())
                .with(new CATAnalysisParserMultitransHTML())
                .with(new CATAnalysisParserMultitransXML())
                .with(new CATAnalysisParserPassolo2016_HTM())
                .with(new CATAnalysisParserSDLTMS2007HTM())
                .with(new CATAnalysisParserSDLTMS2007XLS())
                .with(new CATAnalysisParserSDLTMS())
                .with(new CATAnalysisParserSDLTMS2009())
                .with(new CATAnalysisParserSDLTradosXML())
                .with(new CATAnalysisParserSDLTradosMHTML())
                .with(new CATAnalysisParserSDLWorldServer())
                .with(new CATAnalysisParserSDLX())
                .with(new CATAnalysisParserTrados())
                .with(new CATAnalysisParserTransit())
                .with(new CATAnalysisParserTransitNXTWorkstationImportReportHTML())
                .with(new CATAnalysisParserTransitNXTWorkstationJAMES())
                .with(new CATAnalysisParserTransitNXTWorkstationProgressReportHTML())
                .with(new CATAnalysisParserTransitNXTWorkstationREP())
                .with(new CATAnalysisParserTransitNXTWorkstationXLS())
                .with(new CATAnalysisParserWordfastCSV())
                .with(new CATAnalysisParserWordfastXLS())
                .with(new CATAnalysisParserXML())
                .with(new CATAnalysisParserXTMCSV())
                .with(new CATAnalysisParserXTMXLS_Full())
                .with(new CATAnalysisParserXTMXLS())
                .with(sdlTrados2009HTMBuilder.build())
                .with(sdlTrados2011HTMBuilder.build())
                .with(sdltmsXlsxBuilder.build())
                .build();
    }

    private List<CATAnalysisParser> parsers;
    private Optional<Long> maybeTimeout;

    public CATAnalysisCompositeParser() {}

    public CATAnalysisCompositeParser(List<CATAnalysisParser> parsers, Optional<Long> maybeTimeout) {
        this.parsers = Collections.unmodifiableList(parsers);
        this.maybeTimeout = maybeTimeout;
    }

    public void setParsers(List<CATAnalysisParser> parsers) {
        this.parsers = Collections.unmodifiableList(parsers);
    }

    /**
     * Composite helper does not handle a particular tool type.
     * @return
     */
    @Override
    public CATTool getCATTool() {
        return null;
    }

    @Override
    public CATAnalysis parse(String fileName, InputStream is) throws CATAnalysisParserException {
    	return parse(this.parsers, fileName, is);
    }
    
    public CATAnalysis parse(final CATTool catTool, String fileName, InputStream is) throws CATAnalysisParserException {
        checkExtension(fileName);
        checkFileSizeLimit(is);
        List<CATAnalysisParser> thisCatToolParsers = FluentIterable.from(parsers).filter(new Predicate<CATAnalysisParser>() {

			@Override
			public boolean apply(CATAnalysisParser parser) {
				return parser.getCATTool() == catTool;
			}
		}).toList();
    	
    	return parse(thisCatToolParsers, fileName, is);
    }

    private void checkExtension(String fileName) throws CATAnalysisParserException {
        String extension = FilenameUtils.getExtension(fileName);
        if (extension != null && !extension.isEmpty()
                && !KNOWN_ANALYSIS_FILE_EXTENSIONS.contains(extension)) {
            throw new CATAnalysisParserException("Unknown CAT Analysis file extension: "+ extension);
        }
    }

    private void checkFileSizeLimit(InputStream is) throws CATAnalysisParserException {
        try {
            if (is.available() > MAX_ANALYSIS_SIZE) {
                throw new CATAnalysisParserException("File to big to be processed by CAT parsers, size limit is 1 MByte ");
            }
        } catch (IOException e) {
            throw new CATAnalysisParserException(e);
        }
    }

    public CATAnalysis parse(List<CATAnalysisParser> parsers, String fileName, InputStream is) throws CATAnalysisParserException {
        checkFileSizeLimit(is);
        try {
            byte[] content = IOUtils.toByteArray(is);
            Long start = System.currentTimeMillis();
            for (CATAnalysisParser parser : parsers) {
                if (maybeTimeout.isPresent()) {
                    Long timeout = maybeTimeout.get();
                    long duration = System.currentTimeMillis() - start;
                    if (duration > timeout) {
                        throw new CATAnalysisParserTimeoutException("Could not parse file in time < " + timeout + " ms. Spent " + duration + " ms.");
                    }
                }
                try {
                    CATAnalysis analysis = parser.parse(fileName, content);
                    if (analysis.isDataIntegral()) {
                        return analysis;
                    }
                } catch (CATAnalysisParserNotSupportedMultilingualAnalysisException ex) {
                    throw ex;
                } catch (Exception ex){
                    //ignore the exception and try another parser.
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new CATAnalysisParserException("Unable to find an appropriate parser for " + fileName);
    }
}
