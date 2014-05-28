package eu.citadel.converter.exceptions;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.localization.MessageKey;
import eu.citadel.converter.localization.Messages;

/**
 * General Exception of the Converter.
 * @author Leonardo Dal Zovo
 */
public class ConverterException extends Exception {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(ConverterException.class);

	/**
	 * The messageKey of the exception
	 */
	protected String messageKey = null;
	
	/**
	 * The list of arguments of the exception
	 */
	protected Object[] args = null;
	
	/**
	 * Default key for message localization
	 */
	protected String defaultKey = MessageKey.EXCEPTION_CONVERTER;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7197931636653589336L;
	
	/**
	 * Default constructor
	 */
	public ConverterException() {
		super();
		logger.trace("ConverterException() - start");
		logger.trace("ConverterException() - end");
	}
	
	/**
	 * Constructor with a messageKey
	 * @param messageKey a string containing the messageKey to explain the exception
	 */
	public ConverterException(String messageKey) {
		super();
		logger.trace("ConverterException(String) - start");
		this.messageKey = messageKey;
		logger.trace("ConverterException(String) - end");
	}
	
	/**
	 * Constructor with a messageKey
	 * @param messageKey a string containing the messageKey to explain the exception
	 * @param args arguments to parameterize the message
	 */
	public ConverterException(String messageKey, Object... args) {
		super();
		logger.trace("ConverterException(String) - start");
		this.messageKey = messageKey;
		this.args = args;
		logger.trace("ConverterException(String) - end");
	}

	/**
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		logger.trace("getMessage() - start");
		String returnString = null;
		if (messageKey == null) {
			returnString = Messages.getString(defaultKey);
		}
		else if (args == null) {
			returnString = Messages.getString(messageKey);
		}
		else {
			returnString = Messages.getString(messageKey, args);
		}
		logger.trace("getMessage() - end");
		return returnString;
	}
	
	/**
	 * Return the localized message or getMessage().
	 * @param locale the locale to use to get the message
	 * @return the localized message
	 */
	public String getLocalizedMessage(Locale locale) {
		logger.trace("getLocalizedMessage(Locale) - start");
		String returnString = null;
		if (messageKey == null) {
			returnString = Messages.getString(defaultKey, locale);
		}
		else if (args == null) {
			returnString = Messages.getString(messageKey, locale);
		}
		else {
			returnString = Messages.getString(messageKey, locale, args);
		}
		logger.trace("getLocalizedMessage(Locale) - end");
		return returnString;
	}
	
	/**
	 * Return the translation key to customize the message.
	 * @return the translation key
	 */
	public String getTranslationKey() {
		logger.trace("getTranslationKey() - start");
		logger.trace("getTranslationKey() - end");
		return messageKey;
	}
	
	/**
	 * Return the translation arguments to customize the message.
	 * @return the translation arguments
	 */
	public Object[] getTranslationArgs() {
		logger.trace("getTranslationArgs() - start");
		logger.trace("getTranslationArgs() - end");
		return args;
	}
}
