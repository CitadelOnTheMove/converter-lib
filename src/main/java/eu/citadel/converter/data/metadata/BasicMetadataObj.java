package eu.citadel.converter.data.metadata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.schema.obj.BasicSchemaObj;
import eu.citadel.converter.schema.obj.BasicSchemaObjElements;

/**
 * BasicMetadata Object using {@link BasicSchemaObj}.
 * @author Leonardo Dal Zovo
 */
public class BasicMetadataObj extends BasicSchemaObj {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(BasicMetadataObj.class);

	/**
	 * Default constructor
	 */
	public BasicMetadataObj() {
		this(null);
		logger.trace("BasicMetadataObj() - start");
		logger.trace("BasicMetadataObj() - end");
	}

	/**
	 * Constructor with a parameter
	 * @param elements
	 */
	public BasicMetadataObj(BasicSchemaObjElements elements) {
		super(BasicMetadata.PARSE_TREE_VALIDATOR_TEXT, elements);
		logger.trace("BasicMetadataObj(BasicSchemaObjElements) - start");
		logger.trace("BasicMetadataObj(BasicSchemaObjElements) - end");
	}
}
