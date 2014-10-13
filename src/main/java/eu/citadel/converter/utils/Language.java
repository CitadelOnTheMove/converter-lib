package eu.citadel.converter.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.tika.language.LanguageIdentifier;

/**
 * Language utilities.
 * @author Leonardo Dal Zovo
 */
public class Language {
	/**
	 * Return the language of the specified text
	 * @param text the text to examine
	 * @return the language
	 */
	public static String getLanguage(String text) {
		Logger logger = LoggerFactory.getLogger(Language.class.getName() + ".getLanguage");
		logger.trace("getLanguage(String) - start");
		String returnString = new LanguageIdentifier(text).getLanguage();
		logger.debug("getLanguage(String) - return: {}", returnString);
		logger.trace("getLanguage(String) - end");
	    return returnString;
	}
}
