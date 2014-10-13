package eu.citadel.converter.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.localization.MessageKey;

/**
 * ExcelDataset Exception of the Converter.
 * @author Leonardo Dal Zovo
 */
public class ExcelDatasetException extends DatasetException {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(ExcelDatasetException.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6292648465641975793L;

	/**
	 * Default constructor
	 */
	public ExcelDatasetException() {
		super();
		logger.trace("ExcelDatasetException() - start");
		defaultKey = MessageKey.EXCEPTION_EXCELDATASET;
		logger.trace("ExcelDatasetException() - end");
	}

	/**
	 * Constructor with a messageKey
	 * @param message a string containing the messageKey to explain the exception
	 */
	public ExcelDatasetException(String message) {
		super(message);
		logger.trace("ExcelDatasetException(String) - start");
		defaultKey = MessageKey.EXCEPTION_EXCELDATASET;
		logger.trace("ExcelDatasetException(String) - end");
	}

	/**
	 * Constructor with a messageKey and arguments
	 * @param message a string containing the messageKey to explain the exception
	 * @param args arguments to parameterize the message
	 */
	public ExcelDatasetException(String message, Object... args) {
		super(message, args);
		logger.trace("ExcelDatasetException(String, Object...) - start");
		defaultKey = MessageKey.EXCEPTION_EXCELDATASET;
		logger.trace("ExcelDatasetException(String, Object...) - end");
	}
}
