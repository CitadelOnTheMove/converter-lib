package eu.citadel.converter.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An abstract type characterized by a single string value.
 * Subclasses can override {@link #check(String)} to allow only a range of values.
 * @author Leonardo Dal Zovo
 */
public abstract class AbstractSingleType implements Type {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(AbstractSingleType.class);

	/**
	 * String value of the type
	 */
	protected String type = new String();
	
	/**
	 * Default Constructor
	 */
	public AbstractSingleType() {
		logger.trace("AbstractSingleType() - start");
		logger.trace("AbstractSingleType() - end");
	}
	
	/**
	 * Constructor with a type
	 * @param type element to be added
	 */
	public AbstractSingleType(String type) {
		this();
		logger.trace("AbstractSingleType(String) - start");
		set(type);
		logger.trace("AbstractSingleType(String) - end");
	}

	/**
	 * Add the specified type only if the internally-executed check(type) method returns true.
	 * @param type element to be added
	 * @return true if the specified status is allowed
	 */
	public boolean set(String type) {
		logger.trace("set(String) - start");
		if (check(type)) {
			this.type = type;
			logger.debug("set(String) - {}", type);
			logger.trace("set(String) - end");
			return true;
		}
		else {
			logger.trace("set(String) - end");
			return true;
		}
	}
	
	/**
	 * Enable/disable the allowance of the specified type.
	 * @param type element to be added
	 * @return true if the specified status is allowed
	 */
	protected boolean check(String type) {
		logger.trace("check(String) - start");
		logger.debug("check(String) - return: true");
		logger.trace("check(String) - end");
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return type;
	}
}
