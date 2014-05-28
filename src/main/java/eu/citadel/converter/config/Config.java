package eu.citadel.converter.config;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * Configuration access, automatically reload when configuration changes.
 * @author Leonardo Dal Zovo
 */
public class Config {
	/**
	 * Config file
	 */
	private static final String BUNDLE_NAME = "config/main.xml";
	
	/**
	 * The configuration
	 */
	private static Configuration config = null;
	
	
	/**
	 * Default Constructor: do not instantiate.
	 */
	private Config() {
		
	}
	
	private static void init() {
		Logger logger = LoggerFactory.getLogger(Config.class.getName() + ".init");
		logger.trace("init() - start");
		if (config == null) {
			try {
				config = new XMLConfiguration(BUNDLE_NAME);
				((XMLConfiguration) config).setReloadingStrategy(new FileChangedReloadingStrategy());
				logger.info("init() - {} succesfully loaded with FileChangedReloadingStrategy", BUNDLE_NAME);
			}
			catch (ConfigurationException e) {
				logger.error("init() - {}", e);
			}
		}
		logger.trace("init() - end");
	}
	
	/**
	 * Return a map of category key and localization-key.
	 * @return the new map
	 */
	public static Map<String, String> getCategoryMap() {
		return getMap("categories.category.key", "categories.category.localization");
	}
	
	/**
	 * Return a map of category key and description-key.
	 * @return the new map
	 */
	public static Map<String, String> getCategoryMapDescription() {
		return getMap("categories.category.key", "categories.category.description");
	}
	
	/**
	 * Return a map of the default category key and localization-key.
	 * @return the new map
	 */
	public static Map<String, String> getDefaultCategory() {
		return getMap("categories.defaultcategory.key", "categories.defaultcategory.localization");
	}
	
	/**
	 * Return a map of context key and localization-key.
	 * @return the new map
	 */
	public static Map<String, String> getContextMap() {
		return getMap("contexts.context.key", "contexts.context.localization");
	}
	
	/**
	 * Return a set of the supported dataset type.
	 * @return the new set
	 */
	public static Set<String> getSupportedDatasetType() {
		return getSet("supported-dataset-formats.format.key");
	}
	
	/**
	 * Return a map of dataset type key and localization-key.
	 * @return the new map
	 */
	public static Map<String, String> getDatasetType() {
		return getMap("supported-dataset-formats.format.key", "supported-dataset-formats.format.localization");
	}
	
	/**
	 * Return the index-based dataset type key.
	 * @return the dataset type key in the provided index position or null if not defined or found
	 */
	public static String getSupportedDatasetType(int index) {
		Logger logger = LoggerFactory.getLogger(Config.class.getName() + ".getSupportedDatasetType");
		logger.trace("getSupportedDatasetType(int) - start");
		init();
		List<Object> nameList = config.getList("supported-dataset-formats.format.key");
		try {
			String returnString = nameList.get(index).toString();
			logger.trace("getSupportedDatasetType(int) - end");
			return returnString;
		}
		catch (Exception e) {
			logger.warn("getSupportedDatasetType(int) - index not found: {}", index);
			logger.trace("getSupportedDatasetType(int) - end");
			return null;
		}
	}
	
	/**
	 * Return a map of supported first row type key and localization-key.
	 * @return the new map
	 */
	public static Map<String, String> getSupportedFirstRow() {
		return getMap("format-shared-options.first-rows.first-row.key", "format-shared-options.first-rows.first-row.localization");
	}
	
	/**
	 * Return a map of csv delimiter key and localization-key.
	 * @return the new map
	 */
	public static Map<String, String> getCsvDelimiter() {
		return getMap("csv-options.delimiters.delimiter.key", "csv-options.delimiters.delimiter.localization");
	}
	
	/**
	 * Return a map of csv quote key and localization-key.
	 * @return the new map
	 */
	public static Map<String, String> getCsvQuote() {
		return getMap("csv-options.quotes.quote.key", "csv-options.quotes.quote.localization");
	}
	
	/**
	 * Return a map of csv newline key and localization-key.
	 * @return the new map
	 */
	public static Map<String, String> getCsvNewline() {
		return getMap("csv-options.new-lines.new-line.key", "csv-options.new-lines.new-line.localization");
	}
	
	/**
	 * Return a map of key and localization-key based on the provided string to read the configuration.
	 * @return the new map
	 */
	private static Map<String, String> getMap(String name, String localization) {
		Logger logger = LoggerFactory.getLogger(Config.class.getName() + ".getMap");
		logger.trace("getMap(String, String) - start");
		init();
		List<Object> nameList = config.getList(name);
		List<Object> locaList = config.getList(localization);
		Map<String, String> returnMap = Maps.newLinkedHashMap();
		for (int i = 0; i < nameList.size(); i++) {
			returnMap.put(nameList.get(i).toString(), locaList.get(i).toString());
		}
		logger.trace("getMap(String, String) - start");
		return returnMap;
	}
	
	/**
	 * Return a set of the key based on the provided string to read the configuration.
	 * @return the new set
	 */
	private static Set<String> getSet(String name) {
		Logger logger = LoggerFactory.getLogger(Config.class.getName() + ".getSet");
		logger.trace("getSet(String) - start");
		init();
		List<Object> nameList = config.getList(name);
		Set<String> returnSet = Sets.newLinkedHashSet();
		for (int i = 0; i < nameList.size(); i++) {
			returnSet.add(nameList.get(i).toString());
		}
		logger.trace("getSet(String) - end");
		return returnSet;
	}
}
