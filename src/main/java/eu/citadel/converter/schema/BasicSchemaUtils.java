package eu.citadel.converter.schema;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

import eu.citadel.converter.config.Config;

/**
 * Utilities for {@link BasicSchema}
 * @author Leonardo Dal Zovo
 */
public class BasicSchemaUtils {
	/**
	 * Type Name
	 */
	public static final String NAME = "name";
	
	/**
	 * Type Category
	 */
	public static final String CATEGORY = "category";
	
	/**
	 * Type Category Description
	 */
	public static final String CATEGORY_DESCRIPTION = "category_description";

	/**
	 * Get a map of all available settings with descriptions for the specified type.
	 * @param type one of the type from getType()
	 * @return the map of all available settings with descriptions for the specified type
	 */
	public static Map<String, String> getMap(String type) {
		Logger logger = LoggerFactory.getLogger(BasicSchemaUtils.class.getName() + ".getMap");
		logger.trace("getMap(String) - start");

		Map<String, String> map = Maps.newLinkedHashMap();
		switch (type) {
			case CATEGORY:
				map = Config.getCategoryMap();
				break;
				
			case CATEGORY_DESCRIPTION:
				map = Config.getCategoryMapDescription();
				
			default:
				logger.warn("getMap(String) - unknown type: {}", type);
				break;
		}
		logger.debug("getMap(String) - return: {}", map);
		logger.trace("getMap(String) - end");
		return map;
	}
}
