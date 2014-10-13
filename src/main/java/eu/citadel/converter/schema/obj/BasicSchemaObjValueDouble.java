package eu.citadel.converter.schema.obj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Double value for BasicSchemaObj.
 * @author Leonardo Dal Zovo
 */
public class BasicSchemaObjValueDouble extends BasicSchemaObjAbstractValue<Double> {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(BasicSchemaObjValueDouble.class);

	/**
	 * Default constructor
	 */
	public BasicSchemaObjValueDouble() {
		super();
		logger.trace("BasicSchemaObjValueDouble() - start");
		logger.trace("BasicSchemaObjValueDouble() - end");
	}

	/**
	 * Constructor using a Double
	 * @param value
	 */
	public BasicSchemaObjValueDouble(Double value) {
		super(value);
		logger.trace("BasicSchemaObjValueDouble(Double) - start");
		logger.trace("BasicSchemaObjValueDouble(Double) - end");
	}

	@Override
	public String toJson() {
		logger.trace("toJson() - start");
		String returnString = value.toString();
		logger.trace("toJson() - end");
		return returnString;
	}
}
