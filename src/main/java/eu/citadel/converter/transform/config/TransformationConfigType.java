package eu.citadel.converter.transform.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import com.google.common.collect.Sets;

import eu.citadel.converter.commons.AbstractSingleType;

/**
 * The type of a {@link TransformationConfig}
 * @author Leonardo Dal Zovo
 */
public class TransformationConfigType extends AbstractSingleType {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(TransformationConfigType.class);

	/**
	 * Type CSV
	 */
	public static final String TYPE_BASICTRANSFORMATIONCONFIG = "BASIC_TRANSFORMATIONCONFIG";
	
	
	/**
	 * Allowed types
	 */
	protected static final Set<String> ALLOWED_TYPES = Sets.newHashSet(TYPE_BASICTRANSFORMATIONCONFIG);
	
	
	/**
	 * Default Constructor
	 */
	public TransformationConfigType() {
		super();
		logger.trace("TransformationConfigType() - start");
		logger.trace("TransformationConfigType() - end");
	}
	
	/**
	 * Constructor with a type
	 * @param type element to be added
	 */
	public TransformationConfigType(String type) {
		super();
		logger.trace("TransformationConfigType(String) - start");
		logger.trace("TransformationConfigType(String) - end");
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
}
