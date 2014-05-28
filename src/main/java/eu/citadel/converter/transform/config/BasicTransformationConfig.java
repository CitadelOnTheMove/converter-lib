package eu.citadel.converter.transform.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.antlr.v4.runtime.tree.ParseTree;

import eu.citadel.converter.schema.BasicSchema;

/**
 * Metadata implementation using {@link BasicSchema}.
 * @author Leonardo Dal Zovo
 */
public class BasicTransformationConfig extends BasicSchema implements TransformationConfig {
	/**
	 * Logger for this class
	 */
	private Logger logger = LoggerFactory.getLogger(BasicTransformationConfig.class);
	
	/**
	 * Text to check for validation of the ParseTree
	 */
	public static final String PARSE_TREE_VALIDATOR_TEXT = "basic_transformationconfig";

	/**
	 * Create an empty BasicTransformationConfig
	 */
	public BasicTransformationConfig() {
		super();
		logger.trace("BasicTransformationConfig() - start");
		logger.trace("BasicTransformationConfig() - end");
	}

	/**
	 * Create a BasicTransformationConfig using the provided text
	 * @param basicTransformationConfig the text used to build the BasicTransformationConfig
	 */
	public BasicTransformationConfig(String basicTransformationConfig) {
		super(basicTransformationConfig);
		logger.trace("BasicTransformationConfig(String) - start");
		logger.trace("BasicTransformationConfig(String) - end");
	}

	/**
	 * Create a BasicTransformationConfig using the provided ParseTree
	 * @param basicTransformationConfig the ParseTree to use
	 */
	public BasicTransformationConfig(ParseTree basicTransformationConfig) {
		super(basicTransformationConfig);
		logger.trace("BasicTransformationConfig(ParseTree) - start");
		logger.trace("BasicTransformationConfig(ParseTree) - end");
	}
	
	/**
	 * Create a BasicTransformationConfig using the provided BasicTransformationConfigObj
	 * @param basicTransformationConfig the BasicTransformationConfigObj to use
	 */
	public BasicTransformationConfig(BasicTransformationConfigObj basicTransformationConfig) {
		super(basicTransformationConfig);
		logger.trace("BasicTransformationConfig(BasicTransformationConfigObj) - start");
		logger.trace("BasicTransformationConfig(BasicTransformationConfigObj) - end");
	}
	
	/**
	 * @see eu.citadel.converter.schema.BasicSchema#getParseTreeValidatorText()
	 */
	@Override
	protected String getParseTreeValidatorText() {
		logger = LoggerFactory.getLogger(BasicTransformationConfig.class); // called in a Constructor, so it's not assigned at this point
		logger.trace("getParseTreeValidatorText() - start");
		logger.trace("getParseTreeValidatorText() - end");
		return PARSE_TREE_VALIDATOR_TEXT;
	}

	/**
	 * @see eu.citadel.converter.data.metadata.Metadata#getType()
	 */
	public String getType() {
		logger.trace("getType() - start");
		logger.trace("getType() - end");
		return TransformationConfigType.TYPE_BASICTRANSFORMATIONCONFIG;
	}
}
