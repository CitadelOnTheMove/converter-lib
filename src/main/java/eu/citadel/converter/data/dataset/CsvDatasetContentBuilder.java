package eu.citadel.converter.data.dataset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;

import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;

import com.google.common.collect.Lists;

import eu.citadel.converter.io.IOUtils;

/**
 * Builder to read or get content of a {@link CsvDataset}.
 * @author Leonardo Dal Zovo
 */
public class CsvDatasetContentBuilder {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(CsvDatasetContentBuilder.class);

	/**
	 * Path: default to null. Mandatory field, use CsvDatasetContentBuilder.setPath
	 */
	private Path path = null;
	
	/**
	 * Logical lines to load: default all.
	 */
	private int lines = 0;
	
	/**
	 * Charset: default to null.
	 */
	private Charset loadCharset = null;
	
	/**
	 * CsvType: default to CsvType.CSV_DEFAULT_TYPE.
	 */
	private CsvType csvType = CsvType.CSV_DEFAULT_TYPE;
	
	/**
	 * Set the path to use to load the File. It's mandatory to set the path to get some content.
	 * @param path to use to load the File
	 * @return this
	 */
	public CsvDatasetContentBuilder setPath(Path path) {
		logger.trace("setPath(Path) - start");
		this.path = path;
		logger.trace("setPath(Path) - {}", path);
		logger.trace("setPath(Path) - end");
		return this;
	}
	
	/**
	 * Set the logic lines of code to load.
	 * The logical line is defined as the full row of the csv dataset that can contains new line.
	 * @param lines the number of logical lines of the file to consider, if o then all
	 * @return this
	 */
	public CsvDatasetContentBuilder setLines(int lines) {
		logger.trace("setLines(int) - start");
		this.lines = lines;
		logger.trace("setLines(int) - {}", lines);
		logger.trace("setLines(int) - end");
		return this;
	}
	
	/**
	 * Set the charset to use for decoding.
	 * @param loadCharset the charset to use for decoding
	 * @return this
	 */
	public CsvDatasetContentBuilder setCharset(Charset loadCharset) {
		logger.trace("setCharset(Charset) - start");
		this.loadCharset = loadCharset;
		logger.trace("setCharset(Charset) - {}", loadCharset);
		logger.trace("setCharset(Charset) - end");
		return this;
	}
	
	/**
	 * Set the CsvType to use for decoding.
	 * @param csvType the CsvType to use for decoding
	 * @return this
	 */
	public CsvDatasetContentBuilder setCsvType(CsvType csvType) {
		logger.trace("setCsvType(CsvType) - start");
		this.csvType = csvType;
		logger.trace("setCsvType(CsvType) - {}", csvType);
		logger.trace("setCsvType(CsvType) - end");
		return this;
	}
	
	/**
	 * Return the content of the CSV. If the path or csvType is null the result is an empty content.
	 * @return the content of the CSV
	 * @throws IOException
	 */
	public List<List<String>> build() throws IOException {
		logger.trace("build() - start");
		List<List<String>> preview = Lists.newArrayList();
		if (path == null || csvType == null) {
			logger.debug("build() - {}", preview);
			logger.trace("build() - end");
			return preview;
		}
		try (
			BufferedReader reader = IOUtils.getBufferedReader(path, loadCharset);
			ICsvListReader listReader = new CsvListReader(reader, csvType.toCsvPreference());
		) {
			logger.debug("build() - read");
		    List<String> columnList = null;
		    while ((columnList = listReader.read()) != null) {
		    	preview.add(columnList);
		    	if (lines != 0 && listReader.getRowNumber() > lines) {
		    		break;
		    	}
		    }
			logger.trace("build() - end");
		    return preview;
		}
	}
	
	/**
	 * Return the stream of the CSV. If the path or csvType is null the result is null.
	 * Remember to close the Reader after using it.
	 * @return the stream of the CSV
	 * @throws IOException
	 */
	public ICsvListReader stream() throws IOException {
		logger.trace("stream() - start");
		if (path == null || csvType == null) {
			logger.debug("stream() - return: null");
			logger.trace("stream() - end");
			return null;
		}
		BufferedReader reader = IOUtils.getBufferedReader(path, loadCharset);
		ICsvListReader returnICsvListReader = new CsvListReader(reader, csvType.toCsvPreference());
		logger.debug("stream() - return: reader");
		logger.trace("stream() - end");
		return returnICsvListReader;
	}
}
