package eu.citadel.converter.schema.obj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Null value for BasicSchemaObj.
 * @author Leonardo Dal Zovo
 */
public class BasicSchemaObjValueNull extends BasicSchemaObjAbstractValue<Void> {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(BasicSchemaObjValueNull.class);

	/**
	 * Default constructor
	 */
	public BasicSchemaObjValueNull() {
		super();
		logger.trace("BasicSchemaObjValueNull() - start");
		logger.trace("BasicSchemaObjValueNull() - end");
	}

	@Override
	public String toJson() {
		logger.trace("toJson() - start");
		logger.trace("toJson() - end");
		return "null";
	}
}
