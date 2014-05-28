package eu.citadel.converter.schema.obj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * An abstract and generic definition of a value with a factory.
 * @author Leonardo Dal Zovo
 */
public abstract class BasicSchemaObjAbstractValue<T> {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(BasicSchemaObjAbstractValue.class);

	/**
	 * The value of the Object
	 */
	protected T value = null;
	
	/**
	 * Default Constructor
	 */
	public BasicSchemaObjAbstractValue() {
		logger.trace("BasicSchemaObjAbstractValue() - start");
		logger.trace("BasicSchemaObjAbstractValue() - end");
	}
	
	/**
	 * Constructor using a value
	 * @param value
	 */
	public BasicSchemaObjAbstractValue(T value) {
		logger.trace("BasicSchemaObjAbstractValue(T) - start");
		this.value = value;
		logger.debug("BasicSchemaObjAbstractValue(T) - {}", value);
		logger.trace("BasicSchemaObjAbstractValue(T) - end");
	}
	
	/**
	 * Get value
	 * @return the value
	 */
	public T getValue() {
		logger.trace("getValue() - start");
		logger.trace("getValue() - end");
		return value;
	}
	
	/**
	 * Set value
	 */
	public void setValue(T value) {
		logger.trace("setValue(T) - start");
		this.value = value;
		logger.debug("setValue(T) - {}", value);
		logger.trace("setValue(T) - end");
	}
	
	/**
	 * A factory building a BasicSchemaObjAbstractValue<?> from the specified Object.
	 * @param obj the object to use to build a new BasicSchemaObjAbstractValue (null, int, double, boolean or String)
	 * @return a new object which is instance of BasicSchemaObjAbstractValue or 
	 * null if the specified Object is not mapped to a BasicSchemaObjAbstractValue subclass
	 */
	public static BasicSchemaObjAbstractValue<?> factory(Object obj) {
		Logger logger = LoggerFactory.getLogger(BasicSchemaObjAbstractValue.class.getName() + ".factory");
		logger.trace("factory(Object) - start");

		BasicSchemaObjAbstractValue<?> actualValue = null;
		if (obj == null) {
			actualValue = new BasicSchemaObjValueNull();
		}
		else if (obj instanceof Integer) {
			actualValue = new BasicSchemaObjValueInteger((Integer) obj);
		}
		else if (obj instanceof Double) {
			actualValue = new BasicSchemaObjValueDouble((Double) obj);
		}
		else if (obj instanceof String) {
			actualValue = new BasicSchemaObjValueString((String) obj);
		}
		else if (obj instanceof List) {
			// possible new BasicSchemaObjValueList, not implemented
			logger.warn("factory(Object) - List not implemented");
		}
		else if (obj instanceof Map) {
			// possible new BasicSchemaObjValueObject, not implemented
			logger.warn("factory(Object) - Map not implemented");
		}
		else {
			// not implemented
			logger.warn("factory(Object) - class not implemented");
		}

		logger.trace("factory(Object) - end");
		return actualValue;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		int returnint = prime * result + ((value == null) ? 0 : value.hashCode());
		return returnint;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BasicSchemaObjAbstractValue<?> other = (BasicSchemaObjAbstractValue<?>) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}

		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return value == null ? null : value.toString();
	}
	
	/**
	 * String representation of the Class in Json format.
	 * @return a string containing the json representation of the object
	 */
	public abstract String toJson();
}
