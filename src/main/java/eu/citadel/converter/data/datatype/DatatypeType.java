package eu.citadel.converter.data.datatype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import com.google.common.collect.Sets;

import eu.citadel.converter.commons.AbstractSingleType;

/**
 * The type of a {@link Datatype}.
 * @author Leonardo Dal Zovo
 */
public class DatatypeType extends AbstractSingleType {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(DatatypeType.class);

	/**
	 * Type CSV
	 */
	public static final String TYPE_BASICDATATYPE = "BASIC_DATATYPE";
	
	
	/**
	 * Allowed types
	 */
	protected static final Set<String> ALLOWED_TYPES = Sets.newHashSet(TYPE_BASICDATATYPE);
	
	
	/**
	 * Default Constructor
	 */
	public DatatypeType() {
		super();
		logger.trace("DatatypeType() - start");
		logger.trace("DatatypeType() - end");
	}
	
	/**
	 * Constructor with a type
	 * @param type element to be added
	 */
	public DatatypeType(String type) {
		super();
		logger.trace("DatatypeType(String) - start");
		logger.trace("DatatypeType(String) - end");
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
