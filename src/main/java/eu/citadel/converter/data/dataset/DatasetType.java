package eu.citadel.converter.data.dataset;

import java.io.IOException;
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
	 * Return the type of the specified file or null if not detected or not a file.
	 * @param path the path of the file to detect
	 * @return type or null
	 */
	public static String detect(Path path) {
		Logger logger = LoggerFactory.getLogger(DatasetType.class.getName() + ".detect");
		logger.trace("detect(Path) - start");
		String type = null;
        try {
            type = new Tika().detect(path.toFile());
		}
        catch (IOException e) {
			logger.warn("detect(Path) - not detected", e);
        }
        switch (type) {
        	case "text/csv":
        		logger.trace("detect(Path) - end");
        		logger.debug("detect(Path) - return: {}", TYPE_CSV);
        		return TYPE_CSV;
        	
        	case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
        	case "application/vnd.ms-excel":
        		logger.trace("detect(Path) - end");
        		logger.debug("detect(Path) - return: {}", TYPE_EXCEL);
        		return TYPE_EXCEL;
        		
        	case "application/json":
        		logger.trace("detect(Path) - end");
        		logger.debug("detect(Path) - return: {}", TYPE_JSON);
        		return TYPE_JSON;
        		
    		default:
    			logger.warn("detect(Path) - unsupported type");
    			logger.trace("detect(Path) - end");
    			return null;
        }
	}
}
