package eu.citadel.converter.schema.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.schema.antlr.BasicSchemaParser.AttributeContext;
import eu.citadel.converter.schema.antlr.BasicSchemaParser.ElementContext;
import eu.citadel.converter.schema.obj.BasicSchemaObjAttributes;

/**
 * Visitor building a {@link BasicSchemaObjAttributes}.
 * @author Leonardo Dal Zovo
 */
public class BasicSchemaValuesByIdVisitor extends BasicSchemaValuesAbstractVisitor {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(BasicSchemaValuesByIdVisitor.class);

	/**
	 * The BasicSchemaObjAttributes value after calling visit(ParseTree)
	 */
	protected BasicSchemaObjAttributes res = null;
	
	/**
	 * The id to match
	 */
	protected Object id = null;
	
	/**
	 * Constructor 
	 * @param id the id to search
	 */
	public BasicSchemaValuesByIdVisitor(Object id) {
		logger.trace("BasicSchemaValuesByIdVisitor(Object) - start");
		this.id = id;
		logger.trace("BasicSchemaValuesByIdVisitor(Object) - end");
	}

	@Override
	public Void visitElement(ElementContext ctx) {
		logger.trace("visitElement(ElementContext) - start");
		
		// Id matches the search
		Object currentId = inspectValue(ctx.id().value()).getValue();
		if ((currentId != null && currentId.equals(id)) || (currentId == null && id == null) ) {
			
			// Visit all attributes with values
			res = new BasicSchemaObjAttributes();
			for (AttributeContext attrib : ctx.attribute()) {
				if (attrib != null && !attrib.isEmpty()) {
					res.put(
						fixTextField(attrib.TEXT().getText()),
						inspectValue(attrib.value())
					);
				}
			}
		}
		Void returnVoid = super.visitElement(ctx);
		logger.trace("visitElement(ElementContext) - end");
		return returnVoid;
	}
	
	/**
	 * Return the BasicSchemaObjAttributes value of the current object, searching by id.
	 * This method is working as expected after calling visit(ParseTree), otherwise return null.
	 * @return a new BasicSchemaObjAttributes object
	 */
	public BasicSchemaObjAttributes getValues() {
		logger.trace("getValues() - start");
		logger.trace("getValues() - end");
		return res;
	}
}
