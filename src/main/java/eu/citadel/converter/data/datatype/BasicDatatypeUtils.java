package eu.citadel.converter.data.datatype;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import eu.citadel.converter.config.Config;
import eu.citadel.converter.schema.BasicSchemaUtils;

/**
 * Utilities for {@link BasicDatatype}.
 * @author Leonardo Dal Zovo
 * @see BasicSchemaUtils
 */
public class BasicDatatypeUtils {
	/**
	 * Type Type
	 */
	public static final String TYPE = "type";
	
	/**
	 * Type Schema
	 */
	public static final String SCHEMA = "schema";
	
	/**
	 * Type Mandatory
	 */
	public static final String NAME = "name";
	
	/**
	 * Type Mandatory
	 */
	public static final String LABEL = "label";
	
	/**
	 * Type Mandatory
	 */
	public static final String DESCRIPTION = "description";
	
	/**
	 * Type Mandatory
	 */
	public static final String MANDATORY = "mandatory";
	
	/**
	 * Type Mandatory
	 */
	public static final String DATATYPE = "datatype";
	
	/**
	 * Type Format
	 */
	public static final String FORMAT = "format";
	
	/**
	 * Type Mandatory
	 */
	public static final String EMPTY = "empty";
	
	/**
	 * Type Format
	 */
	public static final String DEFAULT = "default";
	
	/**
	 * Type Multiplicity
	 */
	public static final String MULTIPLICITY = "multiplicity";
	
	/**
	 * Type Category
	 */
	public static final String CATEGORY = "category";
	
	/**
	 * Get Type List
	 */
	public static List<String> getType() {
		Logger logger = LoggerFactory.getLogger(BasicDatatypeUtils.class.getName() + ".getType");
		logger.trace("getType() - start");
		List<String> returnList = Arrays.asList(TYPE, SCHEMA, NAME, LABEL, DESCRIPTION, 
			MANDATORY, DATATYPE, FORMAT, EMPTY, DEFAULT, MULTIPLICITY, CATEGORY);
		logger.trace("getType() - end");
		return returnList;
	}
	
	/**
	 * Get a map of all available settings with descriptions for the specified type.
	 * @param type one of the type from getType()
	 * @return the map of all available settings with descriptions for the specified type
	 */
	public static Map<String, String> getMap(String type) {
		Logger logger = LoggerFactory.getLogger(BasicDatatypeUtils.class.getName() + ".getMap");
		logger.trace("getMap(String) - start");

		Map<String, String> map = Maps.newLinkedHashMap();
		List<String> tempList = Lists.newArrayList();
		switch (type) {
			case TYPE:
				map = Config.getDatasetType();
				break;
				
			case SCHEMA:
				break;
				
			case NAME:
				break;
				
			case LABEL:
				break;
				
			case DESCRIPTION:
				break;
				
			case MANDATORY:
				tempList = Arrays.asList("true", "false", "null");
				for (String entry : tempList) {
					map.put(entry, entry);
				}
				break;
				
			case DATATYPE:
				tempList = Arrays.asList("string", "integer", "double", "boolean", "date", "null");
				for (String entry : tempList) {
					map.put(entry, entry);
				}
				break;
				
			case FORMAT:
				break;
				
			case EMPTY:
				break;
				
			case DEFAULT:
				break;
			
			case MULTIPLICITY:
				tempList = Arrays.asList("1", "n");
				for (String entry : tempList) {
					map.put(entry, entry);
				}
				break;
				
			case CATEGORY:
				map = BasicSchemaUtils.getMap(BasicSchemaUtils.CATEGORY);
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
