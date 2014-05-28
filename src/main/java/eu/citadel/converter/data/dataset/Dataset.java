package eu.citadel.converter.data.dataset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

import com.google.common.io.Files;

import eu.citadel.converter.exceptions.DatasetException;

/**
 * Dataset with basic features.
 * @author Leonardo Dal Zovo
 */
public abstract class Dataset {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(Dataset.class);

	/**
	 * URL of the Dataset
	 */
	protected URL url = null;
	
	/**
	 * Path of the Dataset
	 */
	protected Path path = null;
	
	/**
	 * Temporary Path of the Dataset
	 */
	protected Path tempPath = null;
	
	/**
	 * Status of the Dataset
	 * @see eu.citadel.converter.data.dataset.DatasetStatus
	 */
	protected DatasetStatus status = new DatasetStatus();
	
	/**
	 * Type of the Dataset
	 * @see eu.citadel.converter.data.dataset.DatasetType
	 */
	protected DatasetType type = new DatasetType();
	
	/**
	 * Create an empty Dataset
	 */
	public Dataset() {
		logger.trace("Dataset() - start");
		logger.trace("Dataset() - end");
	}
	
	/**
	 * Create a Dataset using the provided url
	 * @param url
	 */
	public Dataset(URL url) {
		logger.trace("Dataset(URL) - start");
		this.url = url;
		status.add(DatasetStatus.STATUS_URL);
		logger.debug("Dataset(URL) - {}", url);
		logger.trace("Dataset(URL) - end");
	}
	
	/**
	 * Create a Dataset using the provided path
	 * @param path
	 */
	public Dataset(Path path) {
		logger.trace("Dataset(Path) - start");
		this.path = path;
		status.add(DatasetStatus.STATUS_PATH);
		logger.debug("Dataset(Path) - {}", path);
		logger.trace("Dataset(Path) - end");
	}
	
	/**
	 * Save the
	 * @param path
	 * @param overwrite
	 */
	public abstract void saveAs(Path path, boolean overwrite) throws DatasetException, IOException ;
	
	/**
	 * 
	 * @param file
	 * @throws IOException
	 */
	protected void save(File file, String content) throws IOException {
		logger.trace("save(File, String) - start");
		Files.write(content.getBytes(), file);
		logger.debug("save(File, String) - saved content to {}", file);
		logger.trace("save(File, String) - end");
	}
	
	/**
	 * Return the current type of Dataset
	 * @return the String representation of the type of the Dataset
	 */
	public String getType() {
		logger.trace("getType() - start");
		String returnString = type.toString();
		logger.trace("getType() - end");
		return returnString;
	}
	
	/**
	 * Update the changed status of the Dataset
	 */
	protected void changeContent() {
		logger.trace("changeContent() - start");
		status.add(DatasetStatus.STATUS_CONTENTCHANGED);
		logger.trace("changeContent() - end");
	}
}
