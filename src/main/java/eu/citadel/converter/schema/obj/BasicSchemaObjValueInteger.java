package eu.citadel.converter.schema.obj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An Integer value for BasicSchemaObj.
 * @author Leonardo Dal Zovo
 */
public class BasicSchemaObjValueInteger extends BasicSchemaObjAbstractValue<Integer> {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(BasicSchemaObjValueInteger.class);

	/**
	 * Default constructor
	 */
	public BasicSchemaObjValueInteger() {
		super();
		logger.trace("BasicSchemaObjValueInteger() - start");
		logger.trace("BasicSchemaObjValueInteger() - end");
	}

	/**
	 * Constructor using an Integer
	 * @param value
	 */
	public BasicSchemaObjValueInteger(Integer value) {
		super(value);
		logger.trace("BasicSchemaObjValueInteger(Integer) - start");
		logger.trace("BasicSchemaObjValueInteger(Integer) - end");
	}

	@Override
	public String toJson() {
		logger.trace("toJson() - start");
		String returnString = value.toString();
		logger.trace("toJson() - end");
		return returnString;
	}
}
