package eu.citadel.converter.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.localization.MessageKey;

/**
 * Datatype Exception of the Converter.
 * @author Leonardo Dal Zovo
 */
public class DatatypeException extends ConverterException {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(DatatypeException.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -965409716423803313L;

	/**
	 * Default constructor
	 */
	public DatatypeException() {
		super();
		logger.trace("DatatypeException() - start");
		defaultKey = MessageKey.EXCEPTION_DATATYPE;
		logger.trace("DatatypeException() - end");
	}

	/**
	 * Constructor with a messageKey
	 * @param message a string containing the messageKey to explain the exception
	 */
	public DatatypeException(String message) {
		super(message);
		logger.trace("DatatypeException(String) - start");
		defaultKey = MessageKey.EXCEPTION_DATATYPE;
		logger.trace("DatatypeException(String) - end");
	}
	
	/**
	 * Constructor with a messageKey and arguments
	 * @param message a string containing the messageKey to explain the exception
	 * @param args arguments to parameterize the message
	 */
	public DatatypeException(String message, Object... args) {
		super(message, args);
		logger.trace("DatatypeException(String, Object...) - start");
		defaultKey = MessageKey.EXCEPTION_DATATYPE;
		logger.trace("DatatypeException(String, Object...) - end");
	}
}
