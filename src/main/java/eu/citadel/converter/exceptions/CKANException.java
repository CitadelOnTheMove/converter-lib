package eu.citadel.converter.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.localization.MessageKey;

/**
 * CKAN Exception of the Converter.
 * @author Leonardo Dal Zovo
 */
public class CKANException extends ConverterException {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(CKANException.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 2790973954730858L;

	/**
	 * Default constructor
	 */
	public CKANException() {
		super();
		logger.trace("CKANException() - start");
		defaultKey = MessageKey.EXCEPTION_DATASET;
		logger.trace("CKANException() - end");
	}

	/**
	 * Constructor with a messageKey
	 * @param message a string containing the messageKey to explain the exception
	 */
	public CKANException(String message) {
		super(message);
		logger.trace("CKANException(String) - start");
		defaultKey = MessageKey.EXCEPTION_DATASET;
		logger.trace("CKANException(String) - end");
	}

	/**
	 * Constructor with a messageKey and arguments
	 * @param message a string containing the messageKey to explain the exception
	 * @param args arguments to parameterize the message
	 */
	public CKANException(String message, Object... args) {
		super(message, args);
		logger.trace("CKANException(String, Object...) - start");
		defaultKey = MessageKey.EXCEPTION_DATASET;
		logger.trace("CKANException(String, Object...) - end");
	}
}
