package eu.citadel.converter.data.dataset;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;

import eu.citadel.converter.exceptions.DatasetException;
import eu.citadel.converter.localization.MessageKey;

/**
 * Dataset in Json format.
 * @author Leonardo Dal Zovo
 */
public class JsonDataset extends Dataset {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(JsonDataset.class);

	/**
	 * Content of JSON
	 */
	protected String content = null;
	

	/**
	 * Create an empty JsonDataset
	 */
	public JsonDataset() {
		super();
		logger.trace("JsonDataset() - start");
		logger.trace("JsonDataset() - end");
	}

	/**
	 * Create a JsonDataset using the provided path
	 * @param path
	 */
	public JsonDataset(Path path) {
		super(path);
		logger.trace("JsonDataset(Path) - start");
		logger.trace("JsonDataset(Path) - end");
	}

	/**
	 * Create a JsonDataset using the provided url
	 * @param url
	 */
	public JsonDataset(URL url) {
		super(url);
		logger.trace("JsonDataset(URL) - start");
		logger.trace("JsonDataset(URL) - end");
	}
	
	/**
	 * Create a JsonDataset using the provided json
	 * @param content
	 */
	public JsonDataset(String content) {
		logger.trace("JsonDataset(String) - start");
		this.content = content;
		status.add(DatasetStatus.STATUS_CONTENT);
		logger.debug("JsonDataset(String) - content");
		logger.trace("JsonDataset(String) - end");
	}

	
	/**
	 * @see eu.citadel.converter.data.dataset.Dataset#getType()
	 */
	public String getType() {
		logger.trace("getType() - start");
		logger.trace("getType() - end");
		return DatasetType.TYPE_EXCEL;
	}
	
	/**
	 * Return the content of JSON, based on the status in order of importance:
	 * CONTENT: the content already present.
	 * others: null
	 * @return the content of CSV
	 * @throws IOException
	 */
	public String getContent() throws IOException {
		logger.trace("getContent() - start");
		if (status.contains(DatasetStatus.STATUS_CONTENT)) {
			logger.trace("getContent() - {}", DatasetStatus.STATUS_CONTENT);
			logger.debug("getContent() - content");
			logger.trace("getContent() - end");
			return content;
		}
		logger.trace("getContent() - end");
		return null;
	}

	@Override
	public void saveAs(Path path, boolean overwrite) throws DatasetException, IOException {
		logger.trace("saveAs(Path, boolean) - start");
		logger.debug("saveAs(Path, boolean) - {}, {}", path, overwrite);
		if (getContent() == null) {
			logger.error("saveAs(Path, boolean) - no content");
			throw new DatasetException(MessageKey.EXCEPTION_DATASET_SAVE_NO_CONTENT, path);
		}
		Files.write(getContent(), path.toFile(), StandardCharsets.UTF_8);
		logger.trace("saveAs(Path, boolean) - end");
	}
}
