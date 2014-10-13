package eu.citadel.converter.schema.obj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * A HashMap of id and attributes.
 * @author Leonardo Dal Zovo
 */
public class BasicSchemaObjElements extends LinkedHashMap<BasicSchemaObjAbstractValue<?>, BasicSchemaObjAttributes> {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(BasicSchemaObjElements.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8679832412814857828L;
	
	/**
	 * String representation of the Class in Json format.
	 * @return a string containing the json representation of the object
	 */
	public String toJson() {
		logger.trace("toJson() - start");
		List<String> jsonAttributes = Lists.newArrayList();
		for (java.util.Map.Entry<BasicSchemaObjAbstractValue<?>, BasicSchemaObjAttributes> entry : entrySet()) {
			String attributes = entry.getValue().toJson();
			jsonAttributes.add("{\"id\":" + entry.getKey().toJson() + (attributes.isEmpty() ? "" : "," + attributes) + "}");
		}
		String returnString = "[" + Joiner.on(",").join(jsonAttributes) + "]";
		logger.trace("toJson() - end");
		return returnString;
	}
}
