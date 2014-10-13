package eu.citadel.converter.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.localization.MessageKey;

/**
 * TransformationConfig Exception of the Converter.
 * @author Leonardo Dal Zovo
 */
public class TransformationConfigException extends ConverterException {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(TransformationConfigException.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -2334223744728227163L;

	/**
	 * Default constructor
	 */
	public TransformationConfigException() {
		super();
		logger.trace("TransformationConfigException() - start");
		defaultKey = MessageKey.EXCEPTION_TRANSFORMATIONCONFIG;
		logger.trace("TransformationConfigException() - end");
	}

	/**
	 * Constructor with a messageKey
	 * @param message a string containing the messageKey to explain the exception
	 */
	public TransformationConfigException(String message) {
		super(message);
		logger.trace("TransformationConfigException(String) - start");
		defaultKey = MessageKey.EXCEPTION_TRANSFORMATIONCONFIG;
		logger.trace("TransformationConfigException(String) - end");
	}
	
	/**
	 * Constructor with a messageKey and arguments
	 * @param message a string containing the messageKey to explain the exception
	 * @param args arguments to parameterize the message
	 */
	public TransformationConfigException(String message, Object... args) {
		super(message, args);
		logger.trace("TransformationConfigException(String, Object...) - start");
		defaultKey = MessageKey.EXCEPTION_TRANSFORMATIONCONFIG;
		logger.trace("TransformationConfigException(String, Object...) - end");
	}
}
