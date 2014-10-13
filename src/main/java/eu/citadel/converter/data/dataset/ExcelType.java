package eu.citadel.converter.data.dataset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.commons.Type;

/**
 * Type of Excel format.
 * @author Leonardo Dal Zovo
 */
public class ExcelType implements Type {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(ExcelType.class);

	/**
	 * The index of the sheet.
	 */
	protected int indexOfSheet = 0;
	
	/**
	 * Predefined ExcelType with sheet index = 0.
	 */
	public static final ExcelType DEFAULT = new ExcelType();


	/**
	 * Default constructor with index of sheet = 0.
	 */
	public ExcelType() {
		logger.trace("ExcelType() - start");
		logger.trace("ExcelType() - end");
	}
	
	/**
	 * Constructor
	 * @param indexOfSheet the index of the sheet
	 */
	public ExcelType(int indexOfSheet) {
		logger.trace("ExcelType(int) - start");
		this.indexOfSheet = indexOfSheet;
		logger.debug("ExcelType(int) - {}", indexOfSheet);
		logger.trace("ExcelType(int) - end");
	}

	/**
	 * Return the index of sheet.
	 * @return the indexOfSheet
	 */
	public int getIndexOfSheet() {
		logger.trace("getIndexOfSheet() - start");
		logger.trace("getIndexOfSheet() - end");
		return indexOfSheet;
	}
	
	@Override
	public String toString() {
		return String.valueOf(indexOfSheet);
	}
}
