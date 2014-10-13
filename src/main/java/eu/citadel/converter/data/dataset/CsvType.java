package eu.citadel.converter.data.dataset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.supercsv.prefs.CsvPreference;

import eu.citadel.converter.commons.Type;


/**
 * Type of CSV format.
 * @author Leonardo Dal Zovo
 */
public class CsvType implements Type {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(CsvType.class);

	/**
	 * The quote character (used when a cell contains special characters,
	 * such as the delimiter char, a quote char, or spans multiple lines).
	 */
	protected String quoteChar = new String();
	
	/**
	 * The delimiter character (separates each cell in a row).
	 */
	protected String delimiterChar = new String();
	
	/**
	 * The end of line symbols to use when writing (Windows, Mac and Linux style line breaks
	 * are all supported when reading, so this preference won't be used at all for reading).
	 */
	protected String endOfLineSymbols = new String();
	
	/**
	 * Quote: "
	 */
	public static final String QUOTE_DQUOTE = "\"";
	
	/**
	 * Delimiter: ,
	 */
	public static final String DEL_COMMA = ",";
	
	/**
	 * Delimiter: ;
	 */
	public static final String DEL_SEMICOLON = ";";
	
	/**
	 * End of Line: \r\n
	 */
	public static final String EOL_RN = "\r\n";
	
	/**
	 * End of Line: \n
	 */
	public static final String EOL_N = "\n";
	
	/**
	 * Predefined CsvType with Quote '"', Delimiter ',' and End of Line "\r\n".
	 */
	public static final CsvType CSV_DQUOTE_COMMA_RN = new CsvType(QUOTE_DQUOTE, DEL_COMMA, EOL_RN);
	
	/**
	 * Predefined CsvType with Quote '"', Delimiter ';' and End of Line "\r\n".
	 */
	public static final CsvType CSV_DQUOTE_SEMICOLON_RN = new CsvType(QUOTE_DQUOTE, DEL_SEMICOLON, EOL_RN);
	
	/**
	 * Default Predefined CsvType is CSV_DQUOTE_COMMA_RN.
	 */
	public static final CsvType CSV_DEFAULT_TYPE = CSV_DQUOTE_COMMA_RN;
	
	/**
	 * Constructor
	 * @param quoteChar the quote character
	 * @param delimiterChar the delimiter character
	 * @param endOfLineSymbols the end of line symbols to use when writing
	 */
	public CsvType(String quoteChar, String delimiterChar, String endOfLineSymbols) {
		logger.trace("CsvType(String, String, String) - start");
		this.quoteChar = quoteChar;
		this.delimiterChar = delimiterChar;
		this.endOfLineSymbols = endOfLineSymbols;
		logger.debug("CsvType(String, String, String) - {}", toString());
		logger.trace("CsvType(String, String, String) - end");
	}
	
	/**
	 * Return the CsvPreference according to the current settings.
	 * @return the CsvPreference
	 */
	CsvPreference toCsvPreference() {
		logger.trace("toCsvPreference() - start");
		if (quoteChar.isEmpty() || delimiterChar.isEmpty() || endOfLineSymbols.isEmpty()) {
			logger.warn("toCsvPreference() - no preferences");
			logger.trace("toCsvPreference() - end");
			return null;
		}
		CsvPreference returnCsvPreference = new CsvPreference.Builder(quoteChar.charAt(0), delimiterChar.charAt(0), endOfLineSymbols).build();
		logger.debug("toCsvPreference() - {}", toString());
		logger.trace("toCsvPreference() - end");
		return returnCsvPreference;
	}
	
	@Override
	public String toString() {
		return quoteChar + delimiterChar;
	}
}
