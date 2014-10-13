package eu.citadel.converter.schema;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;

/**
 * The type of a {@link Schema}.
 * @author Leonardo Dal Zovo
 */
public class SchemaType {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(SchemaType.class);

	/**
	 * Type of the Dataset
	 */
	protected String type = new String();
	
	/**
	 * Type CSV
	 */
	public static final String TYPE_BASICSCHEMA = "BASIC_SCHEMA";
	
	
	/**
	 * Allowed types
	 */
	protected static final Set<String> allowedTypes = Sets.newHashSet(TYPE_BASICSCHEMA);
	
	
	/**
	 * Default Constructor
	 */
	public SchemaType() {
		logger.trace("SchemaType() - start");
		logger.trace("SchemaType() - end");
	}
	
	/**
	 * Constructor with a type
	 * @param type element to be added
	 */
	public SchemaType(String type) {
		this();
		logger.trace("SchemaType(String) - start");
		add(type);
		logger.trace("SchemaType(String) - end");
	}

	/**
	 * Add the specified type if it is one of the allowed
	 * @param type element to be added
	 * @return true if the specified status is allowed
	 */
	public boolean add(String type) {
		logger.trace("add(String) - start");
		if (allowedTypes.contains(type)) {
			this.type = type;
			logger.debug("add(String) - {}, return: {}", type, true);
			logger.trace("add(String) - end");
			return true;
		}
		logger.debug("add(String) - {}, return: {}", type, false);
		logger.trace("add(String) - end");
		return false;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		logger.trace("toString() - start");
		logger.trace("toString() - end");
		return type;
	}
}
