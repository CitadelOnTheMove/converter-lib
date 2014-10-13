package eu.citadel.converter.data.datatype;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;

import eu.citadel.converter.schema.BasicSchema;

/**
 * Datatype implementation using {@link BasicSchema}.
 * @author Leonardo Dal Zovo
 */
public class BasicDatatype extends BasicSchema implements Datatype {
	/**
	 * Logger for this class
	 */
	private Logger logger = LoggerFactory.getLogger(BasicDatatype.class);

	/**
	 * Text to check for validation of the ParseTree
	 */
	public static final String PARSE_TREE_VALIDATOR_TEXT = "basic_datatype";

	/**
	 * Create an empty BasicDatatype
	 */
	public BasicDatatype() {
		super();
		logger.trace("BasicDatatype() - start");
		logger.trace("BasicDatatype() - end");
	}

	/**
	 * Create a BasicDatatype using the provided text
	 * @param basicDatatype the text used to build the BasicDatatype
	 */
	public BasicDatatype(String basicDatatype) {
		super(basicDatatype);
		logger.trace("BasicDatatype(String) - start");
		logger.trace("BasicDatatype(String) - end");
	}

	/**
	 * Create a BasicDatatype using the provided ParseTree
	 * @param basicDatatype the ParseTree to use
	 */
	public BasicDatatype(ParseTree basicDatatype) {
		super(basicDatatype);
		logger.trace("BasicDatatype(ParseTree) - start");
		logger.trace("BasicDatatype(ParseTree) - end");
	}
	
	/**
	 * Create a BasicDatatype using the provided BasicDatatypeObj
	 * @param basicDatatypeObj the BasicDatatypeObj to use
	 */
	public BasicDatatype(BasicDatatypeObj basicDatatypeObj) {
		super(basicDatatypeObj);
		logger.trace("BasicDatatype(BasicDatatypeObj) - start");
		logger.trace("BasicDatatype(BasicDatatypeObj) - end");
	}
	
	/**
	 * @see eu.citadel.converter.schema.BasicSchema#getParseTreeValidatorText()
	 */
	@Override
	protected String getParseTreeValidatorText() {
		logger = LoggerFactory.getLogger(BasicDatatype.class); // called in a Constructor, so it's not assigned at this point
		logger.trace("getParseTreeValidatorText() - start");
		logger.trace("getParseTreeValidatorText() - end");
		return PARSE_TREE_VALIDATOR_TEXT;
	}

	/**
	 * @see eu.citadel.converter.data.datatype.Datatype#getType()
	 */
	public String getType() {
		logger.trace("getType() - start");
		logger.trace("getType() - end");
		return DatatypeType.TYPE_BASICDATATYPE;
	}
	
	public static List<BasicDatatype> getAvailableBasicDatatype() {
		Logger logger = LoggerFactory.getLogger(BasicDatatype.class.getName() + ".getAvailableBasicDatatype");
		logger.trace("getAvailableBasicDatatype() - start");
		List<BasicDatatype> list = Lists.newArrayList(getCitadelJson(), getMyNeighborhoodLisbonCaseCsv());
		logger.debug("getAvailableBasicDatatype() - return: {}", list);
		logger.trace("getAvailableBasicDatatype() - end");
		return list;
	}
	
	public static BasicDatatype getCitadelJson() {
		Logger logger = LoggerFactory.getLogger(BasicDatatype.class.getName() + ".getCitadelJson");
		logger.trace("getCitadelJson() - start");
		try {
	        URL url = BasicDatatype.class.getResource("/data/datatype/citadel_general.json");
			BasicDatatype returnBasicDatatype = new BasicDatatype(Resources.toString(url, Charsets.UTF_8));
			logger.debug("getCitadelJson() - return: {}", returnBasicDatatype);
			logger.trace("getCitadelJson() - end");
	        return returnBasicDatatype;
		}
		catch (IOException e) {
			logger.error("getCitadelJson() - resource not found", e);
		}
		logger.debug("getCitadelJson() - return: null");
		logger.trace("getCitadelJson() - end");
		return null;
	}
	
	public static BasicDatatype getMyNeighborhoodLisbonCaseCsv() {
		Logger logger = LoggerFactory.getLogger(BasicDatatype.class.getName() + ".getMyNeighborhoodLisbonCaseCsv");
		logger.trace("getMyNeighborhoodLisbonCaseCsv() - start");
		try {
	        URL url = BasicDatatype.class.getResource("/data/datatype/myneighborhood_lisboncase.json");
			BasicDatatype returnBasicDatatype = new BasicDatatype(Resources.toString(url, Charsets.UTF_8));
			logger.debug("getMyNeighborhoodLisbonCaseCsv() - return: {}", returnBasicDatatype);
			logger.trace("getMyNeighborhoodLisbonCaseCsv() - end");
	        return returnBasicDatatype;
		}
		catch (IOException e) {
			logger.error("getMyNeighborhoodLisbonCaseCsv() - resource not found", e);
		}
		logger.debug("getMyNeighborhoodLisbonCaseCsv() - return: null");
		logger.trace("getMyNeighborhoodLisbonCaseCsv() - end");
		return null;
	}
}
