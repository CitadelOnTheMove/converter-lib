package eu.citadel.converter.schema.obj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * An Object value represented as a Map of String and {@link BasicSchemaObjAbstractValue} for BasicSchemaObj.
 * @author Leonardo Dal Zovo
 */
public class BasicSchemaObjValueObject extends BasicSchemaObjAbstractValue<Map<String, BasicSchemaObjAbstractValue<?>>> {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(BasicSchemaObjValueObject.class);

	/**
	 * Default constructor
	 */
	public BasicSchemaObjValueObject() {
		super();
		logger.trace("BasicSchemaObjValueObject() - start");
		logger.trace("BasicSchemaObjValueObject() - end");
	}

	/**
	 * Constructor using an Map of String and BasicSchemaObjAbstractValue
	 * @param value
	 */
	public BasicSchemaObjValueObject(Map<String, BasicSchemaObjAbstractValue<?>> value) {
		super(value);
		logger.trace("BasicSchemaObjValueObject(Map<String,BasicSchemaObjAbstractValue<?>>) - start");
		logger.trace("BasicSchemaObjValueObject(Map<String,BasicSchemaObjAbstractValue<?>>) - end");
	}

	@Override
	public String toJson() {
		logger.trace("toJson() - start");
		List<String> jsonFields = Lists.newArrayList();
		for (Entry<String, BasicSchemaObjAbstractValue<?>> entry : value.entrySet()) {
			jsonFields.add("\"" + entry.getKey() + "\":" + entry.getValue().toJson());
		}
		String returnString = "{" + Joiner.on(",").join(jsonFields) + "}";
		logger.trace("toJson() - end");
		return returnString;
	}
}
