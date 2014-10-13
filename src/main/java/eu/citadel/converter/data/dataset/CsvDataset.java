package eu.citadel.converter.data.dataset;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import com.google.common.collect.Lists;

import eu.citadel.converter.exceptions.DatasetException;
import eu.citadel.converter.localization.MessageKey;

/**
 * Dataset in CSV format.
 * @author Leonardo Dal Zovo
 */
public class CsvDataset extends Dataset {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(CsvDataset.class);

	/**
	 * Content of CSV
	 */
	protected List<List<String>> content = null;
	
	/**
	 * CSV Type
	 * @see eu.citadel.converter.data.dataset.CsvType
	 */
	protected CsvType csvType = null;
	
	/**
	 * Charset of CSV
	 */
	protected Charset charset = null;
	
	
	/**
	 * Create an empty CsvDataset
	 */
	public CsvDataset() {
		super();
		logger.trace("CsvDataset() - start");
		logger.trace("CsvDataset() - end");
	}

	/**
	 * Create a CsvDataset using the provided path
	 * @param path
	 */
	public CsvDataset(Path path) {
		super(path);
		logger.trace("CsvDataset(Path) - start");
		logger.trace("CsvDataset(Path) - end");
	}
	
	/**
	 * Create a CsvDataset using the provided content
	 * @param content
	 */
	public CsvDataset(List<List<String>> content) {
		logger.trace("CsvDataset(List<List<String>>) - start");
		this.content = content;
		status.add(DatasetStatus.STATUS_CONTENT);
		logger.trace("CsvDataset(List<List<String>>) - content");
		logger.trace("CsvDataset(List<List<String>>) - end");
	}

	/**
	 * Create a CsvDataset using the provided url
	 * @param url
	 */
	public CsvDataset(URL url) {
		super(url);
		logger.trace("CsvDataset(URL) - start");
		logger.trace("CsvDataset(URL) - end");
	}
	

	/**
	 * Get the current CsvType
	 * @return the CsvType
	 */
	public CsvType getCsvType() {
		logger.trace("getCsvType() - start");
		logger.trace("getCsvType() - end");
		return csvType;
	}

	/**
	 * Set the new CsvType
	 * @param csvType the CsvType to set
	 */
	public void setCsvType(CsvType csvType) {
		logger.trace("setCsvType(CsvType) - start");
		this.csvType = csvType;
		logger.trace("setCsvType(CsvType) - end");
	}
	
	/**
	 * Get the current Charset
	 * @return the Charset
	 */
	public Charset getCharset() {
		logger.trace("getCharset() - start");
		logger.trace("getCharset() - end");
		return charset;
	}

	/**
	 * Set the new Charset
	 * @param charset the Charset to set
	 */
	public void setCharset(Charset charset) {
		logger.trace("setCharset(Charset) - start");
		this.charset = charset;
		logger.trace("setCharset(Charset) - end");
	}
	
	/**
	 * @see eu.citadel.converter.data.dataset.Dataset#getType()
	 */
	public String getType() {
		logger.trace("getType() - start");
		logger.trace("getType() - end");
		return DatasetType.TYPE_CSV;
	}
	
	/**
	 * Get the first row of the CsvDataset.
	 * @return a list containing the values of the first row or an empty list if not present
	 * @throws IOException
	 */
	public List<String> getFirstRow() throws IOException {
		logger.trace("getFirstRow() - start");
		if (status.contains(DatasetStatus.STATUS_CONTENT)) {
			logger.trace("getFirstRow() - {}", DatasetStatus.STATUS_CONTENT);
			List<String> returnList = content.size() > 0 ? content.get(0) : new ArrayList<String>();
			logger.debug("getFirstRow() - return: {}", returnList);
			logger.trace("getFirstRow() - end");
			return returnList;
		}
		else if (status.contains(DatasetStatus.STATUS_PATH) || status.contains(DatasetStatus.STATUS_TEMPPATH)) {
			logger.trace("getFirstRow() - {}", DatasetStatus.STATUS_PATH + " or " + DatasetStatus.STATUS_TEMPPATH);
			List<List<String>> content = new CsvDatasetContentBuilder()
				.setPath(status.contains(DatasetStatus.STATUS_PATH) ? path : tempPath)
				.setCsvType(csvType)
				.setLines(1)
				.setCharset(null)
				.build();
			List<String> returnList = content.isEmpty() ? new ArrayList<String>() : content.get(0);
			logger.debug("getFirstRow() - return: {}", returnList);
			logger.trace("getFirstRow() - end");
			return returnList;
		}
		else {
			List<String> returnList = Lists.newArrayList();
			logger.debug("getFirstRow() - return: {}", returnList);
			logger.trace("getFirstRow() - end");
			return returnList;
		}
	}
	
	/**
	 * Return the content of CSV, based on the status in order of importance:
	 * CONTENT: the content already present.
	 * PATH: as obtained after the last call to build()
	 * TEMPPATH: as obtained after the last call to build()
	 * @return the content of CSV
	 * @throws IOException
	 */
	public List<List<String>> getContent() throws IOException {
		logger.trace("getContent() - start");
		if (status.contains(DatasetStatus.STATUS_CONTENT)) {
			logger.trace("getContent() - {}", DatasetStatus.STATUS_CONTENT);
			logger.trace("getContent() - end");
			return content;
		}
		else if (status.contains(DatasetStatus.STATUS_PATH)) {
			logger.trace("getContent() - {}", DatasetStatus.STATUS_PATH);
			logger.trace("getContent() - end");
			return content;
		}
		else if (status.contains(DatasetStatus.STATUS_TEMPPATH)) {
			logger.trace("getContent() - {}", DatasetStatus.STATUS_TEMPPATH);
			logger.trace("getContent() - end");
			return content;
		}
		else {
			logger.trace("getContent() - end");
			return content;
		}
	}
	
	/**
	 * Build the content of CSV, based on the status:
	 * CONTENT: content already there, return false
	 * PATH: if path and csvType are provided build the content and return true otherwise false
	 * URL: if url and csvType are provide, store the file locally and then build the content and return true, otherwise false
	 * TEMPPATH: if tempPath and csvType are provided build the content and return true otherwise false
	 * other: false.
	 * The result of the last successful call can be obtained calling getContent().
	 * @return a boolean
	 * @throws IOException
	 */
	public boolean buildContent() throws IOException {
		logger.trace("buildContent() - start");
		if (status.contains(DatasetStatus.STATUS_CONTENT)) {
			logger.trace("buildContent() - {}", DatasetStatus.STATUS_CONTENT);
			logger.debug("buildContent() - return: false");
			logger.trace("buildContent() - end");
			return false;
		}
		else if (status.contains(DatasetStatus.STATUS_PATH)) {
			logger.trace("buildContent() - {}", DatasetStatus.STATUS_PATH);
			if (path == null || csvType == null) {
				logger.debug("buildContent() - return: false");
				logger.trace("buildContent() - end");
				return false;
			}
			else {
				content = new CsvDatasetContentBuilder()
					.setPath(path)
					.setCsvType(csvType)
					.setCharset(charset)
					.build();
				logger.debug("buildContent() - return: true");
				logger.trace("buildContent() - end");
				return true;
			}
		}
		else if (status.contains(DatasetStatus.STATUS_URL) || status.contains(DatasetStatus.STATUS_TEMPPATH)) {
			logger.trace("buildContent() - {}", DatasetStatus.STATUS_URL + " or " + DatasetStatus.STATUS_TEMPPATH);
			if (url == null || csvType == null) {
				logger.debug("buildContent() - return: false");
				logger.trace("buildContent() - end");
				return false;
			}
			else {
				createTempFile("converter", ".csv.tmp");
				content = new CsvDatasetContentBuilder()
					.setPath(tempPath)
					.setCsvType(csvType)
					.setCharset(charset)
					.build();
				logger.debug("buildContent() - return: true");
				logger.trace("buildContent() - end");
				return true;
			}
		}
		else {
			logger.debug("buildContent() - return: false");
			logger.trace("buildContent() - end");
			return false;
		}
	}
	
	/**
	 * Return the stream to read the content of CSV, based on the status:
	 * PATH: if path and csvType are provided return the stream otherwise false
	 * URL: if url and csvType are provide store the file locally and then return the stream otherwise false
	 * TEMPPATH: if tempPath and csvType are provided return the stream otherwise false
	 * others: return null.
	 * @return a stream or null
	 * @throws IOException
	 */
	public ICsvListReader getStream() throws IOException {
		logger.trace("getStream() - start");
		if (status.contains(DatasetStatus.STATUS_PATH)) {
			logger.trace("getStream() - {}", DatasetStatus.STATUS_PATH);
			if (path == null || csvType == null) {
				logger.debug("getStream() - return: null");
				logger.trace("getStream() - end");
				return null;
			}
			else {
				ICsvListReader returnICsvListReader = new CsvDatasetContentBuilder().setPath(path).setCsvType(csvType).setCharset(charset).stream();
				logger.debug("getStream() - return: a stream");
				logger.trace("getStream() - end");
				return returnICsvListReader;
			}
		}
		else if (status.contains(DatasetStatus.STATUS_URL) || status.contains(DatasetStatus.STATUS_TEMPPATH)) {
			logger.trace("getStream() - {}", DatasetStatus.STATUS_URL + " or " + DatasetStatus.STATUS_TEMPPATH);
			if (tempPath == null || csvType == null) {
				logger.debug("getStream() - return: null");
				logger.trace("getStream() - end");
				return null;
			}
			else {
				createTempFile("converter", ".csv.tmp");
				ICsvListReader returnICsvListReader = new CsvDatasetContentBuilder().setPath(tempPath).setCsvType(csvType).setCharset(charset).stream();
				logger.debug("getStream() - return: a stream");
				logger.trace("getStream() - end");
				return returnICsvListReader;
			}
		}
		else if (status.contains(DatasetStatus.STATUS_CONTENT)) {
			logger.trace("getStream() - {}", DatasetStatus.STATUS_CONTENT);
			logger.debug("getStream() - return: null");
			logger.trace("getStream() - end");
			return null;
		}
		else {
			logger.debug("getStream() - return: null");
			logger.trace("getStream() - end");
			return null;
		}
	}

	@Override
	public void saveAs(Path path, boolean overwrite) throws DatasetException, IOException {
		logger.trace("saveAs(Path, boolean) - start");
		logger.debug("saveAs(Path, boolean) - {}, {}", path, overwrite);
		
		List<List<String>> content = getContent();
		
		if (content == null || getContent().size() == 0) {
			logger.error("saveAs(Path, boolean) - no content");
			throw new DatasetException(MessageKey.EXCEPTION_DATASET_SAVE_NO_CONTENT, path);
		}

		ICsvListWriter listWriter = null;
        try {
			listWriter = new CsvListWriter(new FileWriter(path.toString()), CsvPreference.STANDARD_PREFERENCE);

			final CellProcessor[] processors = new CellProcessor[content.get(0).size()];
			boolean firstRow = true;
			for (List<String> row : content) {
				if (firstRow) {
					listWriter.writeHeader(row.toArray(new String[0]));
				}
				else {
					listWriter.write(row, processors);
				}
			}
		}
		finally {
			if (listWriter != null) {
				listWriter.close();
			}
		}
        
        logger.trace("saveAs(Path, boolean) - end");
	}
}
