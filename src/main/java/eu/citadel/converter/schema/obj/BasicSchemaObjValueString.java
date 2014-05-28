package eu.citadel.converter.schema.obj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A String value for BasicSchemaObj.
 * @author Leonardo Dal Zovo
 */
public class BasicSchemaObjValueString extends BasicSchemaObjAbstractValue<String> {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(BasicSchemaObjValueString.class);

	/**
	 * Default constructor
	 */
	public BasicSchemaObjValueString() {
		super();
		logger.trace("BasicSchemaObjValueString() - start");
		logger.trace("BasicSchemaObjValueString() - end");
	}

	/**
	 * Constructor using a String
	 * @param value
	 */
	public BasicSchemaObjValueString(String value) {
		super(value);
		logger.trace("BasicSchemaObjValueString(String) - start");
		logger.trace("BasicSchemaObjValueString(String) - end");
	}

	@Override
	public String toJson() {
		logger.trace("toJson() - start");
		String returnString = "\"" + value.replace("\\", "\\\\").replace("/", "\\/").replace("\"", "\\\"").replace("\b", "\\\\b").replace("\f", "\\\\f").replace("\n", "\\\\n").replace("\r", "\\\\r").replace("\t", "\\\\t") + "\"";
		logger.trace("toJson() - end");
		return returnString;
	}
}
