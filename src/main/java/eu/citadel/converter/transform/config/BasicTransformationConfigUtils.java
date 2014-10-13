package eu.citadel.converter.transform.config;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

import eu.citadel.converter.schema.BasicSchemaUtils;

/**
 * Utilities for {@link BasicTransformationConfig}
 * @author Leonardo Dal Zovo
 * @see BasicSchemaUtils
 */
public class BasicTransformationConfigUtils {
	/**
	 * Type Metadata
	 */
	public static final String METADATA = "metadata";
	
	/**
	 * Type Mandatory
	 */
	public static final String DATATYPE = "datatype";
	
	/**
	 * Type Source
	 */
	public static final String SOURCE = "source";
	
	/**
	 * Type Target
	 */
	public static final String TARGET = "target";
	
	/**
	 * Get Type List
	 */
	public static List<String> getType() {
		Logger logger = LoggerFactory.getLogger(BasicTransformationConfigUtils.class.getName() + ".getType");
		logger.trace("getType() - start");
		List<String> returnList = Arrays.asList(METADATA, DATATYPE, SOURCE, TARGET);
		logger.trace("getType() - end");
		return returnList;
	}
	
	/**
	 * Get a map of all available settings with descriptions for the specified type.
	 * @param type one of the type from getType()
	 * @return the map of all available settings with descriptions for the specified type
	 */
	public static Map<String, String> getMap(String type) {
		Logger logger = LoggerFactory.getLogger(BasicTransformationConfigUtils.class.getName() + ".getMap");
		logger.trace("getMap(String) - start");
		
		Map<String, String> map = Maps.newLinkedHashMap();
		switch (type) {
			case METADATA:
				break;
				
			case DATATYPE:
				break;
				
			case SOURCE:
				break;
			
			case TARGET:
				break;
				
			default:
				logger.warn("getMap(String) - unknown type: {}", type);
				break;
		}
		logger.debug("getMap(String) - return: {}", map);
		logger.trace("getMap(String) - end");
		return map;
	}
}
