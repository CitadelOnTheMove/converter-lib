package eu.citadel.converter.data.datatype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.schema.obj.BasicSchemaObj;
import eu.citadel.converter.schema.obj.BasicSchemaObjElements;

/**
 * BasicDatatype Object using {@link BasicSchemaObj}.
 * @author Leonardo Dal Zovo
 */
public class BasicDatatypeObj extends BasicSchemaObj {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(BasicDatatypeObj.class);

	/**
	 * Default constructor
	 */
	public BasicDatatypeObj() {
		this(null);
		logger.trace("BasicDatatypeObj() - start");
		logger.trace("BasicDatatypeObj() - end");
	}

	/**
	 * Constructor with a parameter
	 * @param elements
	 */
	public BasicDatatypeObj(BasicSchemaObjElements elements) {
		super(BasicDatatype.PARSE_TREE_VALIDATOR_TEXT, elements);
		logger.trace("BasicDatatypeObj(BasicSchemaObjElements) - start");
		logger.trace("BasicDatatypeObj(BasicSchemaObjElements) - end");
	}
}
