package eu.citadel.converter.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.localization.MessageKey;

/**
 * Dataset Exception of the Converter.
 * @author Leonardo Dal Zovo
 */
public class DatasetException extends ConverterException {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(DatasetException.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 2390973955730858L;

	/**
	 * Default constructor
	 */
	public DatasetException() {
		super();
		logger.trace("DatasetException() - start");
		defaultKey = MessageKey.EXCEPTION_DATASET;
		logger.trace("DatasetException() - end");
	}

	/**
	 * Constructor with a messageKey
	 * @param message a string containing the messageKey to explain the exception
	 */
	public DatasetException(String message) {
		super(message);
		logger.trace("DatasetException(String) - start");
		defaultKey = MessageKey.EXCEPTION_DATASET;
		logger.trace("DatasetException(String) - end");
	}

	/**
	 * Constructor with a messageKey and arguments
	 * @param message a string containing the messageKey to explain the exception
	 * @param args arguments to parameterize the message
	 */
	public DatasetException(String message, Object... args) {
		super(message, args);
		logger.trace("DatasetException(String, Object...) - start");
		defaultKey = MessageKey.EXCEPTION_DATASET;
		logger.trace("DatasetException(String, Object...) - end");
	}
}
