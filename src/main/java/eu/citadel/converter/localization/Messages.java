package eu.citadel.converter.localization;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Localization of the messages.
 * @author Leonardo Dal Zovo
 */
public class Messages {
	/**
	 * Property file
	 */
    private static final String BUNDLE_NAME = "localization.messages";
    
    /**
     * Key to use in case of provided missing key
     */
    private static final String DEFAULT_KEY = MessageKey.EXCEPTION_CONVERTER;
    
    /**
     * The Locale to use to localize the messages, default is Locale.getDefault()
     */
    public static Locale MESSAGES_LOCALE = Locale.getDefault();
    
    /**
     * Default Constructor: do not instantiate.
     */
    private Messages() {
    	
    }
    
    /**
     * Return the localized key, based on DEFAULT_KEY, using the provided key or directly the key if no translation is provided.
     * @param key the key to get the localized key
     * @return the localized key or the provided key
     */
    public static String getString(String key) {
    	Logger logger = LoggerFactory.getLogger(Messages.class.getName() + ".getString.String");
    	logger.trace("getString(String) - start");
    	String returnString = getString(key, MESSAGES_LOCALE);
        logger.trace("getString(String) - end");
        return returnString;
    }
    
    /**
     * Return the localized key, based on the provided locale, using the provided key or directly the key if no translation is provided.
     * @param key the key to get the localized key
     * @param locale the locale to use to translate the key
     * @return the localized key or the provided key
     */
    public static String getString(String key, Locale locale) {
    	Logger logger = LoggerFactory.getLogger(Messages.class.getName() + ".getString.StringLocale");
    	logger.trace("getString(String, Locale) - start");
    	if (locale == null) {
    		locale = Locale.getDefault();
    	}
    	ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        try {
        	String returnString = resourceBundle.getString(key);
       		logger.trace("getString(String, Locale) - end");
            return returnString;
        }
        catch (MissingResourceException e) {
        	String returnString = key;
        	logger.trace("getString(String, Locale) - end");
            return returnString;
        }
    }
    
    /**
     * Return the localized key, based on DEFAULT_KEY, using the provided key and the parameters.
     * @param key the key to get the localized key
     * @param params the parameters to use in the key
     * @return the localized key
     */
    public static String getString(String key, Object... params) {
    	Logger logger = LoggerFactory.getLogger(Messages.class.getName() + ".getString.StringObject");
    	logger.trace("getString(String, Object...) - start");
    	String returnString = getString(key, MESSAGES_LOCALE, params);
        logger.trace("getString(String, Object...) - end");
       return returnString;
    }
    
    /**
     * Return the localized key, based on the provided locale, using the provided key and the parameters.
     * @param key the key to get the localized key
     * @param locale the locale to use to translate the key
     * @param params the parameters to use in the key
     * @return the localized key
     */
    public static String getString(String key, Locale locale, Object... params) {
    	Logger logger = LoggerFactory.getLogger(Messages.class.getName() + ".getString.StringLocaleObject");
    	logger.trace("getString(String, Object...) - start");
		
    	ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        try {
        	String returnString = MessageFormat.format(resourceBundle.getString(key), params);
       		logger.trace("getString(String, Object...) - end");
            return returnString;
        }
        catch (MissingResourceException e) {
        	logger.warn("getString(String) - {} is missing, using ", key, DEFAULT_KEY);
        	String returnString = resourceBundle.getString(DEFAULT_KEY);
        	logger.trace("getString(String, Object...) - end");
            return returnString;
        }
    }
}
