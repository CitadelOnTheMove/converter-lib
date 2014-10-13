package eu.citadel.converter.schema.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.schema.antlr.BasicSchemaParser.AttributeContext;
import eu.citadel.converter.schema.antlr.BasicSchemaParser.ElementContext;
import eu.citadel.converter.schema.antlr.BasicSchemaParser.EmptyRulesContext;
import eu.citadel.converter.schema.antlr.BasicSchemaParser.FullRulesContext;
import eu.citadel.converter.schema.obj.BasicSchemaObjElements;
import eu.citadel.converter.schema.obj.BasicSchemaObjAttributes;

/**
 * Visitor building a {@link BasicSchemaObjElements}.
 * @author Leonardo Dal Zovo
 */
public class BasicSchemaValuesVisitor extends BasicSchemaValuesAbstractVisitor {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(BasicSchemaValuesVisitor.class);

	/**
	 * The BasicSchemaObjElements value after calling visit(ParseTree)
	 */
	protected BasicSchemaObjElements res = null;
	
	/**
	 * Constructor 
	 */
	public BasicSchemaValuesVisitor() {
		logger.trace("BasicSchemaValuesVisitor() - start");
		logger.trace("BasicSchemaValuesVisitor() - end");
	}
	
	@Override
	public Void visitFullRules(FullRulesContext ctx) {
		logger.trace("visitFullRules(FullRulesContext) - start");
		res = new BasicSchemaObjElements();
		Void returnVoid = super.visitFullRules(ctx);
		logger.trace("visitFullRules(FullRulesContext) - end");
		return returnVoid;
	}
	
	@Override
	public Void visitEmptyRules(EmptyRulesContext ctx) {
		logger.trace("visitEmptyRules(EmptyRulesContext) - start");
		res = new BasicSchemaObjElements();
		Void returnVoid = super.visitEmptyRules(ctx);
		logger.trace("visitEmptyRules(EmptyRulesContext) - end");
		return returnVoid;
	}
	
	@Override
	public Void visitElement(ElementContext ctx) {
		logger.trace("visitElement(ElementContext) - start");

		BasicSchemaObjAttributes attributes = new BasicSchemaObjAttributes();
		
		// Visit all attributes with values
		for (AttributeContext attrib : ctx.attribute()) {
			if (attrib != null && !attrib.isEmpty()) {
				attributes.put(
					fixTextField(attrib.TEXT().getText()),
					inspectValue(attrib.value())
				);
			}
		}
		res.put(inspectValue(ctx.id().value()), attributes);
		Void returnVoid = super.visitElement(ctx);
		logger.trace("visitElement(ElementContext) - end");
		return returnVoid;
	}
	
	/**
	 * Return the BasicSchemaObjElements value of the current object.
	 * This method is working as expected after calling visit(ParseTree), otherwise return null.
	 * @return a new BasicSchemaObjAttributes object
	 */
	public BasicSchemaObjElements getValues() {
		logger.trace("getValues() - start");
		logger.trace("getValues() - end");
		return res;
	}
}
