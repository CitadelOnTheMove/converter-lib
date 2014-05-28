package eu.citadel.converter.data.metadata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.antlr.v4.runtime.tree.ParseTree;

import eu.citadel.converter.schema.BasicSchema;

/**
 * Metadata implementation using {@link BasicSchema}.
 * @author Leonardo Dal Zovo
 */
public class BasicMetadata extends BasicSchema implements Metadata {
	/**
	 * Logger for this class
	 */
	private Logger logger = LoggerFactory.getLogger(BasicMetadata.class);

	/**
	 * Text to check for validation of the ParseTree
	 */
	public static final String PARSE_TREE_VALIDATOR_TEXT = "basic_metadata";

	/**
	 * Create an empty BasicMetadata
	 */
	public BasicMetadata() {
		super();
		logger.trace("BasicMetadata() - start");
		logger.trace("BasicMetadata() - end");
	}

	/**
	 * Create a BasicMetadata using the provided text
	 * @param basicMetadata the text used to build the BasicMetadata
	 */
	public BasicMetadata(String basicMetadata) {
		super(basicMetadata);
		logger.trace("BasicMetadata(String) - start");
		logger.trace("BasicMetadata(String) - end");
	}

	/**
	 * Create a BasicMetadata using the provided ParseTree
	 * @param basicMetadata the ParseTree to use
	 */
	public BasicMetadata(ParseTree basicMetadata) {
		super(basicMetadata);
		logger.trace("BasicMetadata(ParseTree) - start");
		logger.trace("BasicMetadata(ParseTree) - end");
	}
	
	/**
	 * Create a BasicMetadata using the provided BasicMetadataObj
	 * @param basicMetadata the BasicMetadataObj to use
	 */
	public BasicMetadata(BasicMetadataObj basicMetadata) {
		super(basicMetadata);
		logger.trace("BasicMetadata(BasicMetadataObj) - start");
		logger.trace("BasicMetadata(BasicMetadataObj) - end");
	}
	
	/**
	 * @see eu.citadel.converter.schema.BasicSchema#getParseTreeValidatorText()
	 */
	@Override
	protected String getParseTreeValidatorText() {
		logger = LoggerFactory.getLogger(BasicMetadata.class); // called in a Constructor, so it's not assigned at this point
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
		return MetadataType.TYPE_BASICMETADATA;
	}
}
