package eu.citadel.converter.schema.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.schema.antlr.BasicSchemaParser.AttributeContext;
import eu.citadel.converter.schema.antlr.BasicSchemaParser.ElementContext;
import eu.citadel.converter.schema.obj.BasicSchemaObjAbstractValue;

/**
 * Visitor building a {@link BasicSchemaObjAbstractValue}.
 * @author Leonardo Dal Zovo
 */
public class BasicSchemaValuesByIdAndTypeVisitor extends BasicSchemaValuesAbstractVisitor {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(BasicSchemaValuesByIdAndTypeVisitor.class);

	/**
	 * The BasicSchemaObjAbstractValue value after calling visit(ParseTree)
	 */
	protected BasicSchemaObjAbstractValue<?> res = null;
	
	/**
	 * The id to match
	 */
	protected Object id = null;
	
	/**
	 * The type to match
	 */
	protected String type = null;
	
	/**
	 * Constructor 
	 * @param id the id to search
	 * @param type the type to search in
	 */
	public BasicSchemaValuesByIdAndTypeVisitor(Object id, String type) {
		logger.trace("BasicSchemaValuesByIdAndTypeVisitor(Object, String) - start");
		this.id = id;
		this.type = type;
		logger.trace("BasicSchemaValuesByIdAndTypeVisitor(Object, String) - end");
	}

	@Override
	public Void visitElement(ElementContext ctx) {
		logger.trace("visitElement(ElementContext) - start");

		// Id matches the search
		Object currentId = inspectValue(ctx.id().value()).getValue();
		if ((currentId != null && currentId.equals(id)) || (currentId == null && id == null) ) {
			
			// Looking for the attribute with the provided type
			for (AttributeContext attrib : ctx.attribute()) {
				if (attrib != null && !attrib.isEmpty() &&
					fixTextField(attrib.TEXT().getText()).equalsIgnoreCase(type)) {
					res = inspectValue(attrib.value()); // found the attribute with the "type" key
					break;
				}
			}
		}
		Void returnVoid = super.visitElement(ctx);
		logger.trace("visitElement(ElementContext) - end");
		return returnVoid;
	}
	
	/**
	 * Return the BasicSchemaObjAbstractValue value of the current object, searching by id and type.
	 * This method is working as expected after calling visit(ParseTree), otherwise return null.
	 * @return a new BasicSchemaObjAbstractValue object
	 */
	public BasicSchemaObjAbstractValue<?> getValues() {
		logger.trace("getValues() - start");
		logger.trace("getValues() - end");
		return res;
	}
}
