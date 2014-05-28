package eu.citadel.converter.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.localization.MessageKey;

/**
 * Custom subrule for a BasicSchema Exception of the Converter.
 * @author Leonardo Dal Zovo
 */
public class CustomSubruleSchemaException extends SchemaException {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(CustomSubruleSchemaException.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -6913409668862752674L;

	/**
	 * Default constructor
	 */
	public CustomSubruleSchemaException() {
		super();
		logger.trace("CustomSubruleSchemaException() - start");
		defaultKey = MessageKey.EXCEPTION_CUSTOMSUBRULESCHEMA;
		logger.trace("CustomSubruleSchemaException() - end");
	}
	
	/**
	 * Constructor with a messageKey
	 * @param message a string containing the messageKey to explain the exception
	 */
	public CustomSubruleSchemaException(String message) {
		super(message);
		logger.trace("CustomSubruleSchemaException(String) - start");
		defaultKey = MessageKey.EXCEPTION_CUSTOMSUBRULESCHEMA;
		logger.trace("CustomSubruleSchemaException(String) - end");
	}
	
	/**
	 * Constructor with a messageKey and arguments
	 * @param message a string containing the messageKey to explain the exception
	 * @param args arguments to parameterize the message
	 */
	public CustomSubruleSchemaException(String message, Object... args) {
		super(message, args);
		logger.trace("CustomSubruleSchemaException(String, Object...) - start");
		defaultKey = MessageKey.EXCEPTION_CUSTOMSUBRULESCHEMA;
		logger.trace("CustomSubruleSchemaException(String, Object...) - end");
	}
}
