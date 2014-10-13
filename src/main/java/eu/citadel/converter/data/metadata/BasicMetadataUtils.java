package eu.citadel.converter.data.metadata;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

import eu.citadel.converter.config.Config;
import eu.citadel.converter.schema.BasicSchemaUtils;

/**
 * Utilities for {@link BasicMetadata}.
 * @author Leonardo Dal Zovo
 * @see BasicSchemaUtils
 */
public class BasicMetadataUtils {
	/**
	 * Type Type
	 */
	public static final String TYPE = "type";
	
	/**
	 * Type First Row
	 */
	public static final String FIRST_ROW = "first_row";
	
	/**
	 * Type CSV Delimiter
	 */
	public static final String CSV_DELIMITER = "csv_delimiter";
	
	/**
	 * Type CSV Quote
	 */
	public static final String CSV_QUOTE = "csv_quote";
	
	/**
	 * Type CSV Newline
	 */
	public static final String CSV_NEWLINE = "csv_newline";
	
	/**
	 * Type Category
	 */
	public static final String CATEGORY = "category";
	
	/**
	 * Default category: not a real type
	 */
	public static final String DEFAULT_CATEGORY = "default_category";
	
	/**
	 * Type Context
	 */
	public static final String CONTEXT = "context";
	
	/**
	 * Get Type List
	 */
	public static List<String> getType() {
		Logger logger = LoggerFactory.getLogger(BasicMetadataUtils.class.getName() + ".getType");
		logger.trace("getType() - start");
		List<String> returnList = Arrays.asList(TYPE, FIRST_ROW, CSV_DELIMITER, CSV_QUOTE, CSV_NEWLINE, CATEGORY, CONTEXT);
		logger.trace("getType() - end");
		return returnList;
	}
	
	/**
	 * Get a map of all available settings with descriptions for the specified type.
	 * @param type one of the type from getType()
	 * @return the map of all available settings with descriptions for the specified type
	 */
	public static Map<String, String> getMap(String type) {
		Logger logger = LoggerFactory.getLogger(BasicMetadataUtils.class.getName() + ".getMap");
		logger.trace("getMap(String) - start");

		Map<String, String> map = Maps.newLinkedHashMap();
		switch (type) {
			case TYPE:
				map = Config.getDatasetType();
				break;
				
			case FIRST_ROW:
				map = Config.getSupportedFirstRow();
				break;
				
			case CSV_DELIMITER:
				map = Config.getCsvDelimiter();
				break;
			
			case CSV_QUOTE:
				map = Config.getCsvQuote();
				break;
				
			case CSV_NEWLINE:
				map = Config.getCsvNewline();
				break;
			
			case CATEGORY:
				map = BasicSchemaUtils.getMap(BasicSchemaUtils.CATEGORY);
				break;
			
			case DEFAULT_CATEGORY:
				map = Config.getDefaultCategory();
				break;
				
			case CONTEXT:
				map = Config.getContextMap();
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
