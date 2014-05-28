package eu.citadel.converter.schema.obj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * A HashMap of a string attribute and its value.
 * @author Leonardo Dal Zovo
 */
public class BasicSchemaObjAttributes extends LinkedHashMap<String, BasicSchemaObjAbstractValue<?>> {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(BasicSchemaObjAttributes.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -7839185434605205868L;
	
	/**
	 * String representation of the Class in Json format.
	 * @return a string containing the json representation of the object
	 */
	public String toJson() {
		logger.trace("toJson() - start");
		List<String> jsonAttributes = Lists.newArrayList();
		for (java.util.Map.Entry<String, BasicSchemaObjAbstractValue<?>> entry : entrySet()) {
			if (entry.getKey().startsWith("\"") && entry.getKey().endsWith("\"")) {
				jsonAttributes.add("" + entry.getKey() + ":" + entry.getValue().toJson());
			}
			else {
				jsonAttributes.add("\"" + entry.getKey() + "\":" + entry.getValue().toJson());
			}
		}
		String returnString = Joiner.on(",").join(jsonAttributes);
		logger.trace("toJson() - end");
		return returnString;
	}
}
