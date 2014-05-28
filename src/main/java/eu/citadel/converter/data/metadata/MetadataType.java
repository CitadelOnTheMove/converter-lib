package eu.citadel.converter.data.metadata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import com.google.common.collect.Sets;

import eu.citadel.converter.commons.AbstractSingleType;

/**
 * The type of a {@link Metadata}.
 * @author Leonardo Dal Zovo
 */
public class MetadataType extends AbstractSingleType {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(MetadataType.class);

	/**
	 * Type CSV
	 */
	public static final String TYPE_BASICMETADATA = "BASIC_METADATA";
	
	
	/**
	 * Allowed types
	 */
	protected static final Set<String> ALLOWED_TYPES = Sets.newHashSet(TYPE_BASICMETADATA);
	
	
	/**
	 * Default Constructor
	 */
	public MetadataType() {
		super();
		logger.trace("MetadataType() - start");
		logger.trace("MetadataType() - end");
	}
	
	/**
	 * Constructor with a type
	 * @param type element to be added
	 */
	public MetadataType(String type) {
		super();
		logger.trace("MetadataType(String) - start");
		logger.trace("MetadataType(String) - end");
	}
	
	/**
	 * Enable the setting of the types based on the ALLOWED_TYPES.
	 */
	@Override
	protected boolean check(String type) {
		logger.trace("check(String) - start - {}", type);
		boolean returnboolean = ALLOWED_TYPES.contains(type);
		logger.debug("check(String) - type: {}, return: {}", type, returnboolean);
		logger.trace("check(String) - end");
		return returnboolean;
	}
}
