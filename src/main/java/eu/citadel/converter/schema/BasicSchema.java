package eu.citadel.converter.schema;

import java.util.Locale;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.exceptions.CustomSubruleSchemaException;
import eu.citadel.converter.localization.MessageKey;
import eu.citadel.converter.localization.Messages;
import eu.citadel.converter.schema.antlr.BasicSchemaLexer;
import eu.citadel.converter.schema.antlr.BasicSchemaParser;
import eu.citadel.converter.schema.obj.BasicSchemaObj;
import eu.citadel.converter.schema.obj.BasicSchemaObjAbstractValue;
import eu.citadel.converter.schema.obj.BasicSchemaObjAttributes;
import eu.citadel.converter.schema.obj.BasicSchemaObjElements;
import eu.citadel.converter.schema.obj.BasicSchemaObjValueNull;
import eu.citadel.converter.schema.visitor.BasicSchemaValidatorVisitor;
import eu.citadel.converter.schema.visitor.BasicSchemaValuesByIdAndTypeVisitor;
import eu.citadel.converter.schema.visitor.BasicSchemaValuesByIdVisitor;
import eu.citadel.converter.schema.visitor.BasicSchemaValuesVisitor;

/**
 * A Schema implementations based on {@link eu.citadel.converter.schema.antlr}.
 * @author Leonardo Dal Zovo
 */
public class BasicSchema implements Schema {
	/**
	 * Logger for this class
	 */
	private Logger logger = LoggerFactory.getLogger(BasicSchema.class);

	/**
	 * Parse tree of the rules
	 */
	protected ParseTree parseTree = null;
	
	/**
	 * Default empty tree in string
	 */
	protected static String EMPTY_PARSE_TREE = "";
	
	/**
	 * Text to check for validation of the ParseTree
	 */
	public static final String PARSE_TREE_VALIDATOR_TEXT = "";
	
	/**
	 * Create an empty BasicSchema
	 */
	public BasicSchema() {
		logger.trace("BasicSchema() - start");
		parseTree = buildBasicSchema(EMPTY_PARSE_TREE);
		validateParseTree();
		logger.debug("BasicSchema() - {}", EMPTY_PARSE_TREE);
		logger.trace("BasicSchema() - end");
	}
	
	/**
	 * Create a BasicSchema using the provided text
	 * @param basicSchema the text used to build the BasicSchema
	 */
	public BasicSchema(String basicSchema) {
		logger.trace("BasicSchema(String) - start");
		parseTree = buildBasicSchema(basicSchema);
		validateParseTree();
		logger.debug("BasicSchema(String) - {}", basicSchema);
		logger.trace("BasicSchema(String) - end");
	}
	
	/**
	 * Create a BasicSchema using the provided ParseTree
	 * @param basicSchema the ParseTree to use
	 */
	public BasicSchema(ParseTree basicSchema) {
		logger.trace("BasicSchema(ParseTree) - start");
		parseTree = basicSchema;
		validateParseTree();
		logger.debug("BasicSchema(ParseTree) - {}", parseTree.getText());
		logger.trace("BasicSchema(ParseTree) - end");
	}
	
	/**
	 * Create a BasicSchema using the provided BasicSchemaObj
	 * @param basicSchemaObj the BasicSchemaObj to use
	 */
	public BasicSchema(BasicSchemaObj basicSchemaObj) {
		this(basicSchemaObj.toJson());
		logger.trace("BasicSchema(BasicSchemaObj) - start");
		logger.trace("BasicSchema(BasicSchemaObj) - end");
	}
	
	/**
	 * Build a ParseTree using the provided text
	 * @param basicSchema the text used to build the BasicSchema
	 * @return a new ParseTree based on the provided text
	 */
	protected ParseTree buildBasicSchema(String basicSchema) {
		logger.trace("buildBasicSchema(String) - start");
		ANTLRInputStream input = new ANTLRInputStream(basicSchema);
		BasicSchemaLexer lexer = new BasicSchemaLexer(input) {
			public void recover(RecognitionException e) {
				throw new RuntimeException(e);
			}
		};
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		BasicSchemaParser parser = new BasicSchemaParser(tokens);
		parser.setErrorHandler(new BailErrorStrategy());
		ParseTree returnParseTree = parser.rules();
		logger.trace("buildBasicSchema(String) - end");
		return returnParseTree;
	}
	
	/**
	 * Get a new BasicSchemaObjAbstractValue of the current object searching by id and type.
	 * @param id the id to use as a filter
	 * @param type the type to use as a filter
	 * @return a new BasicSchemaObjAbstractValue object
	 */
	public BasicSchemaObjAbstractValue<?> getValuesByIdAndType(Object id, String type) {
		logger.trace("getValuesByIdAndType(Object, String) - start");
		BasicSchemaValuesByIdAndTypeVisitor visitor = new BasicSchemaValuesByIdAndTypeVisitor(id, type);
		visitor.visit(parseTree);
		BasicSchemaObjAbstractValue<?> returnBasicSchemaObjAbstractValue = visitor.getValues();
		logger.trace("getValuesByIdAndType(Object, String) - end");
		return returnBasicSchemaObjAbstractValue;
	}
	
	/**
	 * Get a new BasicSchemaObjAttributes of the current object searching by id.
	 * @param id the id to use as a filter
	 * @return a new BasicSchemaObjAttributes object
	 */
	public BasicSchemaObjAttributes getValuesById(Object id) {
		logger.trace("getValuesById(Object) - start");
		BasicSchemaValuesByIdVisitor visitor = new BasicSchemaValuesByIdVisitor(id);
		visitor.visit(parseTree);
		BasicSchemaObjAttributes returnBasicSchemaObjAttributes = visitor.getValues();
		logger.trace("getValuesById(Object) - end");
		return returnBasicSchemaObjAttributes;
	}
	
	/**
	 * Get a new BasicSchemaObjElements of the current object.
	 * @return the map of all id with a map of types with values
	 */
	public BasicSchemaObjElements getValues() {
		logger.trace("getValues() - start");
		BasicSchemaValuesVisitor visitor = new BasicSchemaValuesVisitor();
		visitor.visit(parseTree);
		BasicSchemaObjElements returnBasicSchemaObjElements = visitor.getValues();
		logger.trace("getValues() - end");
		return returnBasicSchemaObjElements;
	}
	
	/**
	 * Check if the ParseTree is correct for this specific class.
	 * If getParseTreeValidatorText() return null or empty string, the ParseTree is always good.
	 */
	protected void validateParseTree() {
		logger = LoggerFactory.getLogger(BasicSchema.class); // called in a Constructor, so it's not assigned at this point
		logger.trace("validateParseTree() - start");
		if (getParseTreeValidatorText() == null || !"".equalsIgnoreCase(getParseTreeValidatorText())) {
			BasicSchemaValidatorVisitor validator = new BasicSchemaValidatorVisitor(getParseTreeValidatorText());
			validator.visit(parseTree);
			if (!validator.isValid()) {
				logger.error("validateParseTree() - invalid");
				throw new RuntimeException(new CustomSubruleSchemaException(
					MessageKey.EXCEPTION_CUSTOMSUBRULESCHEMA_EXPLAINED, this.getClass().toString()));
			}
		}
		logger.trace("validateParseTree() - end");
	}
	
	/**
	 * Return the PARSE_TREE_VALIDATOR_TEXT field from the actual class.
	 * This method have to be overridden in order to work properly:
	 * changing the value of the static field is not enough.
	 * @return the static string of the actual class
	 */
	protected String getParseTreeValidatorText() {
		logger.trace("getParseTreeValidatorText() - start");
		logger.trace("getParseTreeValidatorText() - end");
		return PARSE_TREE_VALIDATOR_TEXT;
	}
	
	/**
	 * @see eu.citadel.converter.schema.Schema#getType()
	 */
	public String getType() {
		logger.trace("getType() - start");
		logger.trace("getType() - end");
		return SchemaType.TYPE_BASICSCHEMA;
	}

	@Override
	public String toString() {
		logger.trace("toString() - start");
		String returnString = parseTree.getText();
		logger.trace("toString() - end");
		return returnString;
	}
	
	/**
	 * Return the name of the BasicSchema if provided.
	 * @return the name or null
	 */
	public String getName() {
		logger.trace("getName() - start");
		String ret = null;
		BasicSchemaObjAbstractValue<?> nameVal = getValuesByIdAndType(null, BasicSchemaUtils.NAME);
		if (nameVal != null && !(nameVal instanceof BasicSchemaObjValueNull)) {
			ret = Messages.getString(nameVal.getValue().toString());
			logger.debug("getName() - return: {}", ret);
		}
		else {
			logger.debug("getName() - null");
		}
		logger.trace("getName() - end");
		return ret;
	}
	
	/**
	 * Return the localized name of the BasicSchema if provided.
	 * @param locale the locale to use to get the name
	 * @return the name or null
	 */
	public String getName(Locale locale) {
		logger.trace("getName(Locale) - start");
		String ret = null;
		BasicSchemaObjAbstractValue<?> nameVal = getValuesByIdAndType(null, BasicSchemaUtils.NAME);
		if (nameVal != null && !(nameVal instanceof BasicSchemaObjValueNull)) {
			ret = Messages.getString(nameVal.getValue().toString(), locale);
			logger.debug("getName(Locale) - return: {}", ret);
		}
		else {
			logger.debug("getName(Locale) - null");
		}
		logger.trace("getName(Locale) - end");
		return ret;
	}
}
