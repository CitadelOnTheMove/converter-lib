package eu.citadel.converter.schema.obj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * A List value of {@link BasicSchemaObjAbstractValue} for BasicSchemaObj.
 * @author Leonardo Dal Zovo
 */
public class BasicSchemaObjValueList extends BasicSchemaObjAbstractValue<List<BasicSchemaObjAbstractValue<?>>> {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(BasicSchemaObjValueList.class);

	/**
	 * Default constructor
	 */
	public BasicSchemaObjValueList() {
		super();
		logger.trace("BasicSchemaObjValueList() - start");
		logger.trace("BasicSchemaObjValueList() - end");
	}

	/**
	 * Constructor using an List of BasicSchemaObjAbstractValue
	 * @param value
	 */
	public BasicSchemaObjValueList(List<BasicSchemaObjAbstractValue<?>> value) {
		super(value);
		logger.trace("BasicSchemaObjValueList(List<BasicSchemaObjAbstractValue<?>>) - start");
		logger.trace("BasicSchemaObjValueList(List<BasicSchemaObjAbstractValue<?>>) - end");
	}

	@Override
	public String toJson() {
		logger.trace("toJson() - start");
		List<String> jsonValues = Lists.newArrayList();
		for (BasicSchemaObjAbstractValue<?> val : value) {
			jsonValues.add(val.toJson());
		}
		String returnString = "[" + Joiner.on(",").join(jsonValues) + "]";
		logger.trace("toJson() - end");
		return returnString;
	}
}
