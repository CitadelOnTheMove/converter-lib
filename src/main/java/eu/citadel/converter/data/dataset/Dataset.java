package eu.citadel.converter.data.dataset;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.exceptions.DatasetException;
import eu.citadel.converter.localization.MessageKey;

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
	 * Save the file in the specified path.
	 * @param path the path to save to
	 * @param overwrite if the file can be overwritten or not
	 */
	public abstract void saveAs(Path path, boolean overwrite) throws DatasetException, IOException;
	
	/**
	 * Save the provided content in the specified file.
	 * @param file the file to save into
	 * @param content the content of the file
	 * @throws IOException
	 */
	protected void save(File file, String content) throws IOException {
		logger.trace("save(File, String) - start");
		com.google.common.io.Files.write(content.getBytes(), file);
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
	 * Return the internal state object for the current url, path, tempPath or contentChanged if available.
	 * @param datasetStatus the status from {@link eu.citadel.converter.data.dataset.DatasetStatus}
	 * @return the object
	 * @throws DatasetException if the state is not available or if asking for the content
	 */
	public final Object getInternalStateObject(String datasetStatus) throws DatasetException {
		logger.trace("getInternalStateObject() - start");
		Object returnValue = null;
		switch (datasetStatus == null ? "" : datasetStatus) {
			case DatasetStatus.STATUS_PATH:
				if (status.contains(datasetStatus)) {
					returnValue =  path;
				}
				break;
				
			case DatasetStatus.STATUS_URL:
				if (status.contains(datasetStatus)) {
					returnValue =  url;
				}
				break;
				
			case DatasetStatus.STATUS_TEMPPATH:
				if (status.contains(datasetStatus)) {
					returnValue =  tempPath;
				}
				break;
				
			case DatasetStatus.STATUS_CONTENTCHANGED:
				returnValue = status.contains(datasetStatus);
				break;
				
			case DatasetStatus.STATUS_CONTENT:
				break;
				
			default:
				break;
		}
		
		if (returnValue == null) {
			logger.trace("getInternalStateObject() - not available");
			throw new DatasetException(MessageKey.EXCEPTION_DATASET_INTERNAL_STATE_NOT_AVAILABLE, datasetStatus);
		}
		
		logger.trace("getInternalStateObject() - end");
		return returnValue;
	}
	
	/**
	 * Update the changed status of the Dataset
	 */
	protected void changeContent() {
		logger.trace("changeContent() - start");
		status.add(DatasetStatus.STATUS_CONTENTCHANGED);
		logger.trace("changeContent() - end");
	}
	
	/**
	 * Create a temporary file with the specified prefix and postfix, the name will be completed with random chars in the middle.
	 * @param prefix the name of the file
	 * @param postfix the extension of the file
	 * @throws IOException
	 */
	protected void createTempFile(String prefix, String postfix) throws IOException {
		logger.trace("createTempFile(String, String) - start");
		if (status.contains(DatasetStatus.STATUS_TEMPPATH) && !tempPath.toFile().exists()) {
			logger.warn("createTempFile(String, String) - file not found {}", tempPath);
			tempPath = null;
			status.remove(DatasetStatus.STATUS_TEMPPATH);
		}
		if (!status.contains(DatasetStatus.STATUS_TEMPPATH)) {
			InputStream is = url.openStream();
			byte[] bytes = IOUtils.toByteArray(is);
			tempPath = java.nio.file.Files.createTempFile(prefix, postfix);
			java.nio.file.Files.write(tempPath, bytes);
			logger.trace("createTempFile(String, String) - created {}", tempPath);
			status.add(DatasetStatus.STATUS_TEMPPATH);
		}
		logger.trace("createTempFile(String, String) - end");
	}
}
