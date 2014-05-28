package eu.citadel.converter.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.localization.MessageKey;

/**
 * Schema Exception of the Converter.
 * @author Leonardo Dal Zovo
 */
public class SchemaException extends ConverterException {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(SchemaException.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 5365686440311035427L;

	/**
	 * Default constructor
	 */
	public SchemaException() {
		super();
		logger.trace("SchemaException() - start");
		defaultKey = MessageKey.EXCEPTION_SCHEMA;
		logger.trace("SchemaException() - end");
	}

	/**
	 * Constructor with a messageKey
	 * @param message a string containing the messageKey to explain the exception
	 */
	public SchemaException(String message) {
		super(message);
		logger.trace("SchemaException(String) - start");
		defaultKey = MessageKey.EXCEPTION_SCHEMA;
		logger.trace("SchemaException(String) - end");
	}
	
	/**
	 * Constructor with a messageKey and arguments
	 * @param message a string containing the messageKey to explain the exception
	 * @param args arguments to parameterize the message
	 */
	public SchemaException(String message, Object... args) {
		super(message, args);
		logger.trace("SchemaException(String, Object...) - start");
		defaultKey = MessageKey.EXCEPTION_SCHEMA;
		logger.trace("SchemaException(String, Object...) - end");
	}
}
