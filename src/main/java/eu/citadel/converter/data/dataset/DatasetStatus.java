package eu.citadel.converter.data.dataset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Sets;

/**
 * The status of a {@link Dataset}.
 * @author Leonardo Dal Zovo
 */
public class DatasetStatus {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(DatasetStatus.class);

	/**
	 * Status of the Dataset
	 */
	protected Set<String> status = new HashSet<String>();
	
	
	/**
	 * Status URL: Dataset from a url
	 */
	public static final String STATUS_URL = "URL";
	
	/**
	 * Status PATH: Dataset from a path
	 */
	public static final String STATUS_PATH = "PATH";
	
	/**
	 * Status CONTENT: Dataset from content
	 */
	public static final String STATUS_CONTENT = "CONTENT";
	
	/**
	 * Status CONTENTCHANGED: Content of the Dataset is changed
	 */
	public static final String STATUS_CONTENTCHANGED = "CONTENTCHANGED";
	
	/**
	 * Status PATH: Dataset from a url saved in a temporary path
	 */
	public static final String STATUS_TEMPPATH = "TEMPPATH";
	
	/**
	 * Allowed status
	 */
	protected static final Set<String> allowedStatus = Sets.newHashSet(
		STATUS_URL, STATUS_PATH, STATUS_CONTENT, STATUS_CONTENTCHANGED, STATUS_TEMPPATH);
	
	/**
	 * Default Constructor
	 */
	public DatasetStatus() {
		logger.trace("DatasetStatus() - start");
		logger.trace("DatasetStatus() - end");
	}
	
	/**
	 * Constructor with a status
	 * @param status element to be added
	 */
	public DatasetStatus(String status) {
		this();
		logger.trace("DatasetStatus(String) - start");
		add(status);
		logger.debug("DatasetStatus(String) - {}", status);
		logger.trace("DatasetStatus(String) - end");
	}

	/**
	 * Add the specified status if it is not already present and it is one of the allowed.
	 * @param status element to be added
	 * @return true if it did not already contain the specified status
	 */
	public boolean add(String status) {
		logger.trace("add(String) - start");
		boolean returnboolean = allowedStatus.contains(status) ? this.status.add(status) : false;
		logger.debug("add(String) - return: {}", returnboolean);
		logger.trace("add(String) - end");
		return returnboolean;
	}
	
	/**
	 * Removes the specified element if it is present. 
	 * @param status element to be removed, if present
	 * @return true if it contained the specified element
	 */
	public boolean remove(String status) {
		logger.trace("remove(String) - start");
		boolean returnboolean = this.status.remove(status);
		logger.debug("remove(String) - return: {}", returnboolean);
		logger.trace("remove(String) - end");
		return returnboolean;
	}
	
	/**
	 * Returns true if it contains the specified element.
	 * @param status element whose presence is to be tested
	 * @return true if it contains the specified element
	 */
	public boolean contains(String status) {
		logger.trace("contains(String) - start");
		boolean returnboolean = this.status.contains(status);
		logger.debug("contains(String) - return: {}", returnboolean);
		logger.trace("contains(String) - end");
		return returnboolean;
	}
}
