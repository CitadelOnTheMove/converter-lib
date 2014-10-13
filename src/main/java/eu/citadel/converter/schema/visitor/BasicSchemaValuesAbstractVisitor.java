package eu.citadel.converter.schema.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import eu.citadel.converter.schema.antlr.BasicSchemaBaseVisitor;
import eu.citadel.converter.schema.antlr.BasicSchemaParser.BooleanValueContext;
import eu.citadel.converter.schema.antlr.BasicSchemaParser.ListValueContext;
import eu.citadel.converter.schema.antlr.BasicSchemaParser.NullValueContext;
import eu.citadel.converter.schema.antlr.BasicSchemaParser.NumberValueContext;
import eu.citadel.converter.schema.antlr.BasicSchemaParser.ObjectValueContext;
import eu.citadel.converter.schema.antlr.BasicSchemaParser.OptionContext;
import eu.citadel.converter.schema.antlr.BasicSchemaParser.TextValueContext;
import eu.citadel.converter.schema.antlr.BasicSchemaParser.ValueContext;
import eu.citadel.converter.schema.obj.BasicSchemaObjAbstractValue;
import eu.citadel.converter.schema.obj.BasicSchemaObjValueBoolean;
import eu.citadel.converter.schema.obj.BasicSchemaObjValueDouble;
import eu.citadel.converter.schema.obj.BasicSchemaObjValueInteger;
import eu.citadel.converter.schema.obj.BasicSchemaObjValueList;
import eu.citadel.converter.schema.obj.BasicSchemaObjValueNull;
import eu.citadel.converter.schema.obj.BasicSchemaObjValueObject;
import eu.citadel.converter.schema.obj.BasicSchemaObjValueString;

/**
 * Abstract visitor with basic features.
 * @author Leonardo Dal Zovo
 */
public abstract class BasicSchemaValuesAbstractVisitor extends BasicSchemaBaseVisitor<Void> {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(BasicSchemaValuesAbstractVisitor.class);

	/**
	 * Inspect all the possible values and return the corresponding BasicSchemaObjValue.
	 * @param value the ValueContext to inspect
	 * @return the BasicSchemaObjValue representing the value
	 */
	protected BasicSchemaObjAbstractValue<?> inspectValue(ValueContext value) {
		logger.trace("inspectValue(ValueContext) - start");
		BasicSchemaObjAbstractValue<?> ret = null;
		if (value instanceof TextValueContext) {
			ret = new BasicSchemaObjValueString(
				fixTextField(((TextValueContext) value).TEXT().getText())
			);
		}
		else if (value instanceof NumberValueContext) {
			String number = ((NumberValueContext) value).NUMBER().getText();
			if (number.contains(".")) {
				ret = new BasicSchemaObjValueDouble(new Double(number));
			}
			else {
				ret = new BasicSchemaObjValueInteger(new BigDecimal(number).intValue());
			}
		}
		else if (value instanceof BooleanValueContext) {
			switch (((BooleanValueContext) value).BOOLEAN().getText()) {
				case "true":
					ret = new BasicSchemaObjValueBoolean(true);
					break;
					
				case "false":
					ret = new BasicSchemaObjValueBoolean(false);
					break;
				
				default:
					logger.warn("inspectValue(ValueContext) - boolean error");
					break;
			}
		}
		else if (value instanceof NullValueContext) {
			ret = new BasicSchemaObjValueNull();
		}
		else if (value instanceof ListValueContext) {
			List<BasicSchemaObjAbstractValue<?>> list = Lists.newArrayList();
			for (ParseTree val : ((ListValueContext) value).list().children) {
				if (val instanceof ValueContext) {
					list.add(inspectValue((ValueContext) val));
				}
			}
			ret = new BasicSchemaObjValueList(list);
		}
		else if (value instanceof ObjectValueContext) {
			Map<String, BasicSchemaObjAbstractValue<?>> map = Maps.newHashMap();
			for (ParseTree val : ((ObjectValueContext) value).object().children) {
				if (val instanceof OptionContext) {
					map.put(
						fixTextField(((OptionContext) val).TEXT().getText()),
						inspectValue(((OptionContext) val).value())
					);
				}
			}
			ret = new BasicSchemaObjValueObject(map);
		}
		logger.trace("inspectValue(ValueContext) - end");
		return ret;
	}
	
	/**
	 * As TEXT token includes a starting and ending double quote characters,
	 * it return the text without them. It also removes additional char "\" when not needed.
	 * @param text the TEXT token to fix
	 * @return the cleaned text
	 */
	protected String fixTextField(String text) {
		logger.trace("fixTextField(String) - start");
		String str = "\"id\":".equalsIgnoreCase(text) ?
			text.substring(1, text.length() - 2) :
			text.substring(1, text.length() - 1);
		String returnString = str.replace("\\\\", "\\").replace("\\/", "/").replace("\\\"", "\"");
		logger.trace("fixTextField(String) - end");
		return returnString;
	}
}
