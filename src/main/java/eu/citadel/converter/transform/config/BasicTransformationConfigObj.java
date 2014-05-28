package eu.citadel.converter.transform.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.schema.obj.BasicSchemaObj;
import eu.citadel.converter.schema.obj.BasicSchemaObjElements;

/**
 * BasicTransformationConfig Object using {@link BasicSchemaObj}.
 * @author Leonardo Dal Zovo
 */
public class BasicTransformationConfigObj extends BasicSchemaObj {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(BasicTransformationConfigObj.class);

	/**
	 * Default constructor
	 */
	public BasicTransformationConfigObj() {
		this(null);
		logger.trace("BasicTransformationConfigObj() - start");
		logger.trace("BasicTransformationConfigObj() - end");
	}

	/**
	 * Constructor with a parameter
	 * @param elements
	 */
	public BasicTransformationConfigObj(BasicSchemaObjElements elements) {
		super(BasicTransformationConfig.PARSE_TREE_VALIDATOR_TEXT, elements);
		logger.trace("BasicTransformationConfigObj(BasicSchemaObjElements) - start");
		logger.trace("BasicTransformationConfigObj(BasicSchemaObjElements) - end");
	}
}
