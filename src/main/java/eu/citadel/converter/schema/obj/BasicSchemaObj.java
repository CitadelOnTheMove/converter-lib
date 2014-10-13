package eu.citadel.converter.schema.obj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An object-oriented representation of a full BasicSchema object. 
 * @author Leonardo Dal Zovo
 */
public class BasicSchemaObj {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(BasicSchemaObj.class);
	
	/**
	 * Schema name
	 */
	protected String schemaName = null;
	
	/**
	 * The elements of the schema
	 */
	protected BasicSchemaObjElements elements = null;

	/**
	 * @return the schemaName
	 */
	public String getSchemaName() {
		logger.trace("getSchemaName() - start");
		logger.trace("getSchemaName() - end");
		return schemaName;
	}

	/**
	 * Default constructor
	 */
	public BasicSchemaObj() {
		logger.trace("BasicSchemaObj() - start");
		logger.trace("BasicSchemaObj() - end");
	}

	/**
	 * Constructor with parameters
	 * @param schemaName
	 * @param elements
	 */
	public BasicSchemaObj(String schemaName, BasicSchemaObjElements elements) {
		super();
		logger.trace("BasicSchemaObj(String, BasicSchemaObjElements) - start");
		setSchemaName(schemaName);
		setElements(elements);
		logger.trace("BasicSchemaObj(String, BasicSchemaObjElements) - end");
	}

	/**
	 * @param schemaName the schemaName to set
	 */
	public void setSchemaName(String schemaName) {
		logger.trace("setSchemaName(String) - start");
		this.schemaName = schemaName;
		logger.debug("setSchemaName(String) - {}", schemaName);
		logger.trace("setSchemaName(String) - end");
	}

	/**
	 * @return the elements
	 */
	public BasicSchemaObjElements getElements() {
		logger.trace("getElements() - start");
		logger.trace("getElements() - end");
		return elements;
	}

	/**
	 * @param elements the elements to set
	 */
	public void setElements(BasicSchemaObjElements elements) {
		logger.trace("setElements(BasicSchemaObjElements) - start");
		this.elements = elements;
		logger.debug("setElements(BasicSchemaObjElements) - {}", elements);
		logger.trace("setElements(BasicSchemaObjElements) - end");
	}
	
	/**
	 * String representation of the Class in Json format.
	 * @return a string containing the json representation of the object
	 */
	public String toJson() {
		logger.trace("toJson() - start");
		String returnString = "{" + (schemaName == null ? "" : ("\"" + schemaName + "\"" + ":" + (elements == null ? "[]" : elements.toJson()))) + "}";
		logger.trace("toJson() - end");
		return returnString;
	}
}
