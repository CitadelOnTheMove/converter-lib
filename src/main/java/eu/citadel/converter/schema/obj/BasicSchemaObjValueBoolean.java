package eu.citadel.converter.schema.obj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Boolean value for BasicSchemaObj.
 * @author Leonardo Dal Zovo
 */
public class BasicSchemaObjValueBoolean extends BasicSchemaObjAbstractValue<Boolean> {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(BasicSchemaObjValueBoolean.class);

	/**
	 * Default constructor
	 */
	public BasicSchemaObjValueBoolean() {
		super();
		logger.trace("BasicSchemaObjValueBoolean() - start");
		logger.trace("BasicSchemaObjValueBoolean() - end");
	}

	/**
	 * Constructor using a Boolean
	 * @param value
	 */
	public BasicSchemaObjValueBoolean(Boolean value) {
		super(value);
		logger.trace("BasicSchemaObjValueBoolean(Boolean) - start");
		logger.trace("BasicSchemaObjValueBoolean(Boolean) - end");
	}

	@Override
	public String toJson() {
		logger.trace("toJson() - start");
		String returnString = value.toString();
		logger.trace("toJson() - end");
		return returnString;
	}	
}
