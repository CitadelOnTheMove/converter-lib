package eu.citadel.converter.schema.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.schema.antlr.BasicSchemaBaseVisitor;
import eu.citadel.converter.schema.antlr.BasicSchemaParser.EmptyRulesContext;
import eu.citadel.converter.schema.antlr.BasicSchemaParser.FullRulesContext;

/**
 * Visitor validating the {@link eu.citadel.converter.schema.BasicSchema} based on a required string
 * @author Leonardo Dal Zovo
 */
public class BasicSchemaValidatorVisitor extends BasicSchemaBaseVisitor<Void> {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(BasicSchemaValidatorVisitor.class);

	/**
	 * Proper value representing the validity of the BasicSchema after calling visit(ParseTree)
	 */
	protected Boolean valid = null;
	
	/**
	 * The rules Text to match
	 */
	protected String rulesText = null;
	
	/**
	 * Constructor 
	 * @param rulesText the value of the rule to match
	 */
	public BasicSchemaValidatorVisitor(String rulesText) {
		logger.trace("BasicSchemaValidatorVisitor(String) - start");

		this.rulesText = rulesText;

		logger.trace("BasicSchemaValidatorVisitor(String) - end");
	}
	
	@Override
	public Void visitFullRules(FullRulesContext ctx) {
		logger.trace("visitFullRules(FullRulesContext) - start");

		if (removeStartingEndingDoubleQuotes(ctx.TEXT().getText()).equalsIgnoreCase(rulesText)) {
			valid = true;
		}
		else {
			valid = false;
		}
		Void returnVoid = super.visitFullRules(ctx);
		logger.trace("visitFullRules(FullRulesContext) - end");
		return returnVoid;
	}

	@Override
	public Void visitEmptyRules(EmptyRulesContext ctx) {
		logger.trace("visitEmptyRules(EmptyRulesContext) - start");
		valid = true;
		Void returnVoid = super.visitEmptyRules(ctx);
		logger.trace("visitEmptyRules(EmptyRulesContext) - end");
		return returnVoid;
	}
	
	/**
	 * This method is working as expected after calling visit(ParseTree), otherwise return null.
	 * @return the value representing the validity of the BasicSchema after calling visit(ParseTree) or null
	 */
	public boolean isValid() {
		logger.trace("isValid() - start");
		logger.debug("isValid() - return: {}", valid);
		logger.trace("isValid() - end");
		return valid;
	}
	
	/**
	 * As TEXT token includes a starting and ending double quote characters,
	 * it return the text without them.
	 * @param text the TEXT token to clean from starting and ending double quote characters
	 * @return the cleaned text
	 */
	private String removeStartingEndingDoubleQuotes(String text) {
		logger.trace("removeStartingEndingDoubleQuotes(String) - start");
		String returnString = text.substring(1, text.length() - 1);
		logger.trace("removeStartingEndingDoubleQuotes(String) - end");
		return returnString;
	}
}
