package eu.citadel.converter.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.localization.MessageKey;

/**
 * Metadatata Exception of the Converter.
 * @author Leonardo Dal Zovo
 */
public class MetadataException extends ConverterException {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(MetadataException.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 770719897131286850L;

	/**
	 * Default constructor
	 */
	public MetadataException() {
		super();
		logger.trace("MetadataException() - start");
		defaultKey = MessageKey.EXCEPTION_METADATA;
		logger.trace("MetadataException() - end");
	}

	/**
	 * Constructor with a messageKey
	 * @param message a string containing the messageKey to explain the exception
	 */
	public MetadataException(String message) {
		super(message);
		logger.trace("MetadataException(String) - start");
		defaultKey = MessageKey.EXCEPTION_METADATA;
		logger.trace("MetadataException(String) - end");
	}
	
	/**
	 * Constructor with a messageKey and arguments
	 * @param message a string containing the messageKey to explain the exception
	 * @param args arguments to parameterize the message
	 */
	public MetadataException(String message, Object... args) {
		super(message, args);
		logger.trace("MetadataException(String, Object...) - start");
		defaultKey = MessageKey.EXCEPTION_METADATA;
		logger.trace("MetadataException(String, Object...) - end");
	}
}
