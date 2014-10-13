package eu.citadel.converter.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.localization.MessageKey;

/**
 * Tranform Exception of the Converter.
 * @author Leonardo Dal Zovo
 */
public class TransformException extends ConverterException {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(TransformException.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -4422745876649798257L;

	/**
	 * Default constructor
	 */
	public TransformException() {
		super();
		logger.trace("TransformException() - start");
		defaultKey = MessageKey.EXCEPTION_TRANSFORM;
		logger.trace("TransformException() - end");
	}

	/**
	 * Constructor with a messageKey
	 * @param message a string containing the messageKey to explain the exception
	 */
	public TransformException(String message) {
		super(message);
		logger.trace("TransformException(String) - start");
		defaultKey = MessageKey.EXCEPTION_TRANSFORM;
		logger.trace("TransformException(String) - end");
	}
	
	/**
	 * Constructor with a messageKey and arguments
	 * @param message a string containing the messageKey to explain the exception
	 * @param args arguments to parameterize the message
	 */
	public TransformException(String message, Object... args) {
		super(message, args);
		logger.trace("TransformException(String, Object...) - start");
		defaultKey = MessageKey.EXCEPTION_TRANSFORM;
		logger.trace("TransformException(String, Object...) - end");
	}
}
