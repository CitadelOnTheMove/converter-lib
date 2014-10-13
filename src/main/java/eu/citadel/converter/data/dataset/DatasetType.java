package eu.citadel.converter.data.dataset;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Set;

import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.commons.AbstractSingleType;
import eu.citadel.converter.config.Config;

/**
 * The type of a {@link Dataset}.
 * @author Leonardo Dal Zovo
 */
public class DatasetType extends AbstractSingleType {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(DatasetType.class);

	/**
	 * Type CSV
	 */
	public static final String TYPE_CSV = Config.getSupportedDatasetType(0);
	
	/**
	 * Type EXCEL
	 */
	public static final String TYPE_EXCEL = Config.getSupportedDatasetType(1);
	
	/**
	 * Type JSON
	 */
	public static final String TYPE_JSON = Config.getSupportedDatasetType(2);
	
	/**
	 * Allowed types
	 */
	protected static final Set<String> ALLOWED_TYPES = Config.getSupportedDatasetType();
	
	
	/**
	 * Default Constructor
	 */
	public DatasetType() {
		super();
		logger.trace("DatasetType() - start");
		logger.trace("DatasetType() - end");
	}
	
	/**
	 * Constructor with a type
	 * @param type element to be added
	 */
	public DatasetType(String type) {
		super(type);
		logger.trace("DatasetType(String) - start");
		logger.trace("DatasetType(String) - end");
	}
	
	/**
	 * Enable the setting of the types based on the ALLOWED_TYPES.
	 */
	@Override
	protected boolean check(String type) {
		logger.trace("check(String) - start");
		boolean returnboolean = ALLOWED_TYPES.contains(type);
		logger.debug("check(String) - type: {}, return: {}", type, returnboolean);
		logger.trace("check(String) - end");
		return returnboolean;
	}
	
	/**
	 * Return the type of the specified url or null if not detected or not a file.
	 * @param url the url of the file to detect
	 * @return type or null
	 */
	public static String detect(URL url) {
		Logger logger = LoggerFactory.getLogger(DatasetType.class.getName() + ".detect");
		logger.trace("detect(URL) - start");
		
		String mimeType = getMimeType(url);
		String datatypeString = getDatasetTypeFromTikaMediaType(mimeType);
		
		logger.trace("detect(URL) - end");
        return datatypeString;
	}
	
	/**
	 * Return the type of the specified file or null if not detected or not a file.
	 * @param path the path of the file to detect
	 * @return type or null
	 */
	public static String detect(Path path) {
		Logger logger = LoggerFactory.getLogger(DatasetType.class.getName() + ".detect");
		logger.trace("detect(Path) - start");
		
		String mimeType = getMimeType(path);
		String datatypeString = getDatasetTypeFromTikaMediaType(mimeType);
		
		logger.trace("detect(Path) - end");
        return datatypeString;
	}
	
	/**
	 * Return the Mime Type of the specified URL
	 * @param url the url of the file to detect
	 * @return the Mime Type or null
	 */
	public static String getMimeType(URL url) {
		Logger logger = LoggerFactory.getLogger(DatasetType.class.getName() + ".getMimeType");
		logger.trace("getMimeType(URL) - start");
		String type = null;
        try {
            type = new Tika().detect(url);
            logger.trace("getMimeType(URL) - end");
		}
        catch (IOException e) {
			logger.warn("getMimeType(URL) - not detected", e);
        }
        return type;
	}
	
	/**
	 * Return the Mime Type of the specified Path
	 * @param path the path of the file to detect
	 * @return the Mime Type or null
	 */
	public static String getMimeType(Path path) {
		Logger logger = LoggerFactory.getLogger(DatasetType.class.getName() + ".getMimeType");
		logger.trace("getMimeType(Path) - start");
		String type = null;
        try {
            type = new Tika().detect(path.toFile());
            logger.trace("getMimeType(Path) - end");
		}
        catch (IOException e) {
			logger.warn("getMimeType(Path) - not detected", e);
        }
        return type;
	}
	
	/**
	 * Return one of the DatasetType from Tika Media Type or null.
	 * @param tikaDetect Tika Media Type as returned from new Tika().detect
	 * @return the DatasetType String constant
	 */
	private static String getDatasetTypeFromTikaMediaType(String tikaDetect) {
		Logger logger = LoggerFactory.getLogger(DatasetType.class.getName() + ".getDatasetTypeFromTikaMediaType");
		logger.trace("getDatasetTypeFromTikaMediaType(String) - start");
		String tikaDetectFix = tikaDetect == null ? "" : tikaDetect;
		if (tikaDetectFix.contains("text/csv")) {
    		logger.trace("getDatasetTypeFromTikaMediaType(String) - end");
    		logger.debug("getDatasetTypeFromTikaMediaType(String) - return: {}", TYPE_CSV);
    		return TYPE_CSV;
		}
		else if (tikaDetectFix.contains("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") ||
			tikaDetectFix.contains("application/vnd.ms-excel")) {
    		logger.trace("getDatasetTypeFromTikaMediaType(String) - end");
    		logger.debug("getDatasetTypeFromTikaMediaType(String) - return: {}", TYPE_EXCEL);
    		return TYPE_EXCEL;
		}
		else if (tikaDetectFix.contains("application/json")) {
    		logger.trace("getDatasetTypeFromTikaMediaType(String) - end");
    		logger.debug("getDatasetTypeFromTikaMediaType(String) - return: {}", TYPE_JSON);
    		return TYPE_JSON;
		}
		else {
			logger.warn("getDatasetTypeFromTikaMediaType(String) - unsupported type");
			logger.trace("getDatasetTypeFromTikaMediaType(String) - end");
			return null;
	    }
	}
}
