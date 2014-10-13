package eu.citadel.converter.transform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercsv.io.ICsvListReader;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import eu.citadel.converter.data.Data;
import eu.citadel.converter.data.dataset.CsvDataset;
import eu.citadel.converter.data.dataset.Dataset;
import eu.citadel.converter.data.dataset.ExcelDataset;
import eu.citadel.converter.data.datatype.BasicDatatype;
import eu.citadel.converter.data.datatype.BasicDatatypeUtils;
import eu.citadel.converter.data.datatype.Datatype;
import eu.citadel.converter.data.metadata.BasicMetadata;
import eu.citadel.converter.data.metadata.BasicMetadataUtils;
import eu.citadel.converter.exceptions.ExcelDatasetException;
import eu.citadel.converter.exceptions.TransformException;
import eu.citadel.converter.localization.MessageKey;
import eu.citadel.converter.schema.obj.BasicSchemaObjAbstractValue;
import eu.citadel.converter.schema.obj.BasicSchemaObjAttributes;
import eu.citadel.converter.schema.obj.BasicSchemaObjElements;
import eu.citadel.converter.schema.obj.BasicSchemaObjValueBoolean;
import eu.citadel.converter.schema.obj.BasicSchemaObjValueDouble;
import eu.citadel.converter.schema.obj.BasicSchemaObjValueInteger;
import eu.citadel.converter.schema.obj.BasicSchemaObjValueList;
import eu.citadel.converter.schema.obj.BasicSchemaObjValueNull;
import eu.citadel.converter.schema.obj.BasicSchemaObjValueObject;
import eu.citadel.converter.schema.obj.BasicSchemaObjValueString;
import eu.citadel.converter.transform.config.BasicTransformationConfig;
import eu.citadel.converter.transform.config.BasicTransformationConfigUtils;
import eu.citadel.converter.transform.config.TransformationConfig;

/**
 * Trasform receiving CSV and Excel {@link Dataset} and creating
 * a My Neighbourhood Lisbon Case compatible CSV in a {@link eu.citadel.converter.data.dataset.CsvDataset}.
 * @author Leonardo Dal Zovo
 */
public class MyNLisbonCaseCsvTransform extends BasicTransform {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(MyNLisbonCaseCsvTransform.class);

	/**
	 * Default Constructor
	 */
	public MyNLisbonCaseCsvTransform() {
		super(null, null, null);
		logger.trace("MyNLisbonCaseCsvTransform() - start");
		logger.trace("MyNLisbonCaseCsvTransform() - end");
	}
	
	/**
	 * Constructor with all parameters
	 * @param data the source Datatype
	 * @param datatype the Datatype
	 * @param transformationConfig the TransformationConfig
	 */
	public MyNLisbonCaseCsvTransform(Data<?, ?> data, Datatype datatype, TransformationConfig transformationConfig) {
		super(data, datatype, transformationConfig);
		logger.trace("MyNLisbonCaseCsvTransform(Data<?,?>, Datatype, TransformationConfig) - start");
		logger.trace("MyNLisbonCaseCsvTransform(Data<?,?>, Datatype, TransformationConfig) - end");
	}
	
	
	/**
	 * Build a map matching Metadata id to TransformationConfig id to know
	 * which transformation to use based on metadata id.
	 * Metadata id without a match are inserted or not inserted based on the boolean value.
	 * @param datatype the BasicDatatype object to use
	 * @param transformationConfig the BasicTransformationConfig object to use
	 * @param insertNullIfNoMatch should the metadata id without a match be inserted with value null?
	 * @return a new map
	 */
	protected static Map<Object, Object> getDatatypeIdInTransformationConfigIdMap(BasicDatatype datatype,
		BasicTransformationConfig transformationConfig, boolean insertNullIfNoMatch) {
		Logger logger = LoggerFactory.getLogger(MyNLisbonCaseCsvTransform.class.getName() + ".getDatatypeIdInTransformationConfigIdMap");
		logger.trace("getDatatypeIdInTransformationConfigIdMap(BasicDatatype, BasicTransformationConfig, boolean) - start");

		Map<Object, Object> datatypeIdInTransformationConfig = Maps.newHashMap();
		BasicSchemaObjElements datatypeElements = datatype.getValues();
		for (java.util.Map.Entry<BasicSchemaObjAbstractValue<?>, BasicSchemaObjAttributes> datatypeAttribs : datatypeElements.entrySet()) {
			BasicSchemaObjAbstractValue<?> datatypeId = datatypeAttribs.getKey();
			Object datatypeIdValue = datatypeId.getValue();
			if (datatypeIdValue != null) {
				BasicSchemaObjElements elems = transformationConfig.getValues();
				for (java.util.Map.Entry<BasicSchemaObjAbstractValue<?>, BasicSchemaObjAttributes> attribs : elems.entrySet()) {
					BasicSchemaObjAbstractValue<?> transformationConfigId = attribs.getKey();
					BasicSchemaObjAttributes attrib = attribs.getValue();
					BasicSchemaObjAbstractValue<?> source = attrib.get(BasicTransformationConfigUtils.SOURCE);
					if (source != null) {
						if (source instanceof BasicSchemaObjValueObject) {
							BasicSchemaObjValueObject sourceObj = (BasicSchemaObjValueObject) source;
							Map<String, BasicSchemaObjAbstractValue<?>> searchDatatype = Maps.newHashMap();
							searchDatatype.put(BasicTransformationConfigUtils.DATATYPE, datatypeAttribs.getKey());
							if (sourceObj.getValue().equals(searchDatatype)) {
								datatypeIdInTransformationConfig.put(datatypeIdValue, transformationConfigId.getValue());
							}
						}
					}
				}
			}
			if (insertNullIfNoMatch && !datatypeIdInTransformationConfig.containsKey(datatypeIdValue)) {
				datatypeIdInTransformationConfig.put(datatypeIdValue, null);
			}
		}
		logger.debug("getDatatypeIdInTransformationConfigIdMap(BasicDatatype, BasicTransformationConfig, boolean) - return: {}", datatypeIdInTransformationConfig);
		logger.trace("getDatatypeIdInTransformationConfigIdMap(BasicDatatype, BasicTransformationConfig, boolean) - end");
		return datatypeIdInTransformationConfig;
	}
	
	/**
	 * Return the BasicSchemaObjAbstractValue representing the value or operations 
	 * to get the actual value of the specified transformation configuration id.
	 * @param transformationConfigId 
	 * @param transformationConfig
	 * @return a new BasicSchemaObjAbstractValue
	 */
	protected static BasicSchemaObjAbstractValue<?> getResult(Object transformationConfigId, BasicTransformationConfig transformationConfig) {
		Logger logger = LoggerFactory.getLogger(MyNLisbonCaseCsvTransform.class.getName() + ".getResult");
		logger.trace("getResult(Object, BasicTransformationConfig) - start");
		BasicSchemaObjAbstractValue<?> returnBasicSchemaObjAbstractValue = transformationConfig.getValuesByIdAndType(transformationConfigId, BasicTransformationConfigUtils.TARGET);
		logger.trace("getResult(Object, BasicTransformationConfig) - end");
		return returnBasicSchemaObjAbstractValue;
	}
	
	/**
	 * Return a map matching metadata id to target values in the transformation config
	 * @param datatype the BasicDatatype object to use
	 * @param transformationConfig the BasicTransformationConfig object to use
	 * @param insertNullIfNoMatch should the metadata id without a match be inserted with value null?
	 * @return a new map
	 */
	protected static Map<Object, BasicSchemaObjAbstractValue<?>> getDatatypeIdAndBasicSchemaObjAbstractValueMap(
		BasicDatatype datatype,	BasicTransformationConfig transformationConfig, boolean insertNullIfNoMatch) {
		Logger logger = LoggerFactory.getLogger(MyNLisbonCaseCsvTransform.class.getName() + ".getDatatypeIdAndBasicSchemaObjAbstractValueMap");
		logger.trace("getDatatypeIdAndBasicSchemaObjAbstractValueMap(BasicDatatype, BasicTransformationConfig, boolean) - start");
		
		Map<Object, BasicSchemaObjAbstractValue<?>> datatypeToBasicSchemaObjValue = Maps.newHashMap();
		for (Entry<Object, Object> match : getDatatypeIdInTransformationConfigIdMap(datatype, transformationConfig, insertNullIfNoMatch).entrySet()) {
			Object valueObj = match.getValue();
			BasicSchemaObjAbstractValue<?> value = null;
			if (valueObj == null) {
				value = new BasicSchemaObjValueNull();
			}
			else {
				value = getResult(valueObj, transformationConfig);
			}
			
			datatypeToBasicSchemaObjValue.put(match.getKey(), value);
		}
		
		logger.debug("getDatatypeIdAndBasicSchemaObjAbstractValueMap(BasicDatatype, BasicTransformationConfig, boolean) - return: {}", datatypeToBasicSchemaObjValue);
		logger.trace("getDatatypeIdAndBasicSchemaObjAbstractValueMap(BasicDatatype, BasicTransformationConfig, boolean) - end");
		return datatypeToBasicSchemaObjValue;
	}
	
	/**
	 * Return the string value to insert in the final json base on the parameter
	 * @param datatypeAttributes the BasicSchemaObjAttributes of the BasicDatatype id
	 * @param value the target values in the transformation config
	 * @param datasetValues the ordered list of values of the dataset to get the actual values
	 * @param datatypeName the name of the selected Datatype 
	 * @return a string
	 * @throws TransformException 
	 */
	protected static String getStringValue(BasicSchemaObjAttributes datatypeAttributes,
		BasicSchemaObjAbstractValue<?> value, List<Object> datasetValues, String datatypeName, boolean firstCall) throws TransformException {
		Logger logger = LoggerFactory.getLogger(MyNLisbonCaseCsvTransform.class.getName() + ".getStringValue");
		logger.trace("getStringValue(BasicSchemaObjAttributes, BasicSchemaObjAbstractValue<?>, List<Object>) - start");

		BasicSchemaObjAbstractValue<?> name = datatypeAttributes.get(BasicDatatypeUtils.NAME);
		BasicSchemaObjAbstractValue<?> label = datatypeAttributes.get(BasicDatatypeUtils.LABEL);
		BasicSchemaObjAbstractValue<?> datatype = datatypeAttributes.get(BasicDatatypeUtils.DATATYPE);
		BasicSchemaObjAbstractValue<?> format = datatypeAttributes.get(BasicDatatypeUtils.FORMAT);
		BasicSchemaObjAbstractValue<?> multiplicity = datatypeAttributes.get(BasicDatatypeUtils.MULTIPLICITY);
		
		String key = label.toString();
		if (firstCall) {
			return key;
		}

		String ret = inspectValue(value, datasetValues, datatype, format, multiplicity);
		
		// the value is null
		if (ret == null) {
			// is Mandatory?
			BasicSchemaObjAbstractValue<?> mandatory = datatypeAttributes.get(BasicDatatypeUtils.MANDATORY);
			
			if (mandatory == null || mandatory.getValue() == null || mandatory instanceof BasicSchemaObjValueNull) {
				logger.error("getStringValue(BasicSchemaObjAttributes, BasicSchemaObjAbstractValue<?>, List<Object>) - {} is null and it's not specified if it can be null", name.getValue());
				throw new TransformException(MessageKey.EXCEPTION_VALUE_NULL_UNSPECIFIED_IF_NULLABLE, name.getValue(), datatypeName);
			}
			else if (mandatory instanceof BasicSchemaObjValueBoolean) {
				boolean mandatoryValue = ((BasicSchemaObjValueBoolean) mandatory).getValue();
				if (mandatoryValue) {
					logger.error("getStringValue(BasicSchemaObjAttributes, BasicSchemaObjAbstractValue<?>, List<Object>) - {} can't be null", name.getValue());
					throw new TransformException(MessageKey.EXCEPTION_VALUE_CANNOT_BE_NULL, name.getValue());
				}
				else {
					// how to handle null value?
					BasicSchemaObjAbstractValue<?> empty = datatypeAttributes.get(BasicDatatypeUtils.EMPTY);
					
					if (empty == null || empty.getValue() == null || empty instanceof BasicSchemaObjValueNull) {
						logger.error("getStringValue(BasicSchemaObjAttributes, BasicSchemaObjAbstractValue<?>, List<Object>) - null handling not provided for {}", name.getValue());
						throw new TransformException(MessageKey.EXCEPTION_VALUE_NULL_EMPTY_POLICY_NULL, name.getValue(), datatypeName);
					}
					else if (empty instanceof BasicSchemaObjValueString) {
						String emptyPolicy = ((BasicSchemaObjValueString) empty).getValue();
						switch (emptyPolicy) {
							case "empty":
								if ("json".equalsIgnoreCase(datatype.toString())) {
									ret = "[]";
								}
								else {
									ret = "";
								}
								break;
								
							default:
								logger.error("getStringValue(BasicSchemaObjAttributes, BasicSchemaObjAbstractValue<?>, List<Object>) - null policy not supported - {}", emptyPolicy);
								throw new TransformException(MessageKey.EXCEPTION_VALUE_NULL_EMPTY_POLICY_STRING_UNSUPPORTED, name.getValue(), datatypeName, emptyPolicy);
						}
					}
					else {
						logger.error("getStringValue(BasicSchemaObjAttributes, BasicSchemaObjAbstractValue<?>, List<Object>) - null handling not supported for {}", name.getValue());
						throw new TransformException(MessageKey.EXCEPTION_VALUE_NULL_EMPTY_POLICY_TYPE_UNSUPPORTED, name.getValue(), datatypeName);
					}
				}
			}
			else {
				logger.error("getStringValue(BasicSchemaObjAttributes, BasicSchemaObjAbstractValue<?>, List<Object>) - {} is null and it's specified if it can be null in an unsupported way", name.getValue());
				throw new TransformException(MessageKey.EXCEPTION_VALUE_NULL_UNSUPPORTED_IF_NULLABLE, name.getValue(), datatypeName);
			}
		}
		
		logger.debug("getStringValue(BasicSchemaObjAttributes, BasicSchemaObjAbstractValue<?>, List<Object>) - return: {}", ret);
		logger.trace("getStringValue(BasicSchemaObjAttributes, BasicSchemaObjAbstractValue<?>, List<Object>) - end");
		return ret;
	}
	
	/**
	 * Get string value to place in json for the AbstractValue.
	 * @param value the values to convert in string
	 * @param datasetValues the dataset of values to use
	 * @param key string of the key to be added in all cases except lists
	 * @param datatype the datatype of the value
	 * @param format the format to use for the value
	 * @return a string or null
	 * @throws TransformException
	 */
	protected static String inspectValue(BasicSchemaObjAbstractValue<?> value, List<Object> datasetValues,
		BasicSchemaObjAbstractValue<?> datatype, BasicSchemaObjAbstractValue<?> format,
		BasicSchemaObjAbstractValue<?> multiplicity) throws TransformException {
		Logger logger = LoggerFactory.getLogger(MyNLisbonCaseCsvTransform.class.getName() + ".inspectValue");
		logger.trace("inspectValue(BasicSchemaObjAbstractValue<?>, List<Object>, String, BasicSchemaObjAbstractValue<?>, BasicSchemaObjAbstractValue<?>) - start");
		
		boolean isList = false;
		String datatypeString = new String();
		if (datatype == null || datatype instanceof BasicSchemaObjValueNull) {
			// now assume it's a single value... or exception should be raised?
			logger.info("inspectValue(BasicSchemaObjAbstractValue<?>, List<Object>, String, BasicSchemaObjAbstractValue<?>, BasicSchemaObjAbstractValue<?>) - datatype is null");
		}
		else if (datatype instanceof BasicSchemaObjValueString) {
			datatypeString = ((BasicSchemaObjValueString) datatype).getValue();
		}

		if (multiplicity instanceof BasicSchemaObjValueString && "n".equalsIgnoreCase(multiplicity.toString())) {
			isList = true;
		}
		
		String val = null;
		if (value == null) {
			val = null;
		}
		else if (value instanceof BasicSchemaObjValueNull) {
			val = null;
		}
		else if (value instanceof BasicSchemaObjValueBoolean || value instanceof BasicSchemaObjValueInteger ||
			value instanceof BasicSchemaObjValueDouble) {
			if (isList) {
				val = "[" + value.getValue() + "]";
			}
			else {
				val = value.getValue().toString();
			}
		}
		else if (value instanceof BasicSchemaObjValueString) {
			String curValEncoded = ((BasicSchemaObjValueString) value).getValue()
				.replace("\\", "\\\\").replace("/", "\\/").replace("\"", "\\\"")
				.replace("\b", "\\\\b").replace("\f", "\\\\f").replace("\n", "\\\\n")
				.replace("\r", "\\\\r").replace("\t", "\\\\t");
			if (isList) {
				// if there's a single value, try to split using "," to get a list of values
				logger.debug("inspectValue(BasicSchemaObjAbstractValue<?>, List<Object>, String, BasicSchemaObjAbstractValue<?>, BasicSchemaObjAbstractValue<?>) - split a string - {}", curValEncoded);
				List<String> splittedVal = Lists.newArrayList();
				for (String token : curValEncoded.split(",")) {
					String tokenTrim = token.trim();
					if (!tokenTrim.isEmpty()) {
						if ("json".equalsIgnoreCase(datatypeString)) {
							splittedVal.add("\"" + tokenTrim + "\"");
						}
						else {
							splittedVal.add(tokenTrim);
						}
					}
				}
				val = "[" + Joiner.on(",").join(splittedVal) + "]";
			}
			else {
				if ("json".equalsIgnoreCase(datatypeString)) {
					val = "\"" + curValEncoded + "\"";
				}
				else {
					val = curValEncoded;
				}
			}
		}
		else if (value instanceof BasicSchemaObjValueList) {
			List<String> vList = Lists.newArrayList();
			List<String> vListNoDQuote = Lists.newArrayList();
			List<String> vListDQuote = Lists.newArrayList();
			for (BasicSchemaObjAbstractValue<?> elem : ((BasicSchemaObjValueList) value).getValue()) {
				String inspectedValue = inspectValue(elem, datasetValues, new BasicSchemaObjValueString(datatypeString), format, null);
				if (inspectedValue != null) {
					vList.add(inspectedValue);
					if (inspectedValue.startsWith("\"") && inspectedValue.endsWith("\"")) {
						vListNoDQuote.add(inspectedValue.substring(1, inspectedValue.length() - 1));
						vListDQuote.add(inspectedValue);
					}
					else {
						vListNoDQuote.add(inspectedValue);
						vListDQuote.add("\"" + inspectedValue + "\"");
					}
				}
			}
			if (isList) { // a list is expected, so act differently...
				if (vList.size() == 0) {
					// warning: should be empty list, different from null.
					// At the moment empty list is considered not valid for mandatory request.
					logger.warn("inspectValue(BasicSchemaObjAbstractValue<?>, List<Object>, String, BasicSchemaObjAbstractValue<?>, BasicSchemaObjAbstractValue<?>) - empty list considered null");
					val = null;
				}
				else if (vList.size() == 1) {
					// if there's a single value, try to split using "," to get a list of values
					logger.debug("inspectValue(BasicSchemaObjAbstractValue<?>, List<Object>, String, BasicSchemaObjAbstractValue<?>, BasicSchemaObjAbstractValue<?>) - split a possible list - {}", vListNoDQuote.get(0));
					List<String> splittedVal = Lists.newArrayList();
					for (String token : vListNoDQuote.get(0).split(",")) {
						String tokenTrim = token.trim();
						if (!tokenTrim.isEmpty()) {
							if ("json".equalsIgnoreCase(datatypeString)) {
								splittedVal.add("\"" + tokenTrim + "\"");
							}
							else {
								splittedVal.add(tokenTrim);
							}
						}
					}
					val = "[" + Joiner.on(",").join(splittedVal) + "]";
				}
				else {
					// if there's a list of values, then use this list
					logger.debug("inspectValue(BasicSchemaObjAbstractValue<?>, List<Object>, String, BasicSchemaObjAbstractValue<?>, BasicSchemaObjAbstractValue<?>) - build a list from a list");
					val = "[" + Joiner.on(",").join(vListDQuote) + "]";
				}
			}
			else {
				// if a single element was expected, concat the values to get a single string
				logger.debug("inspectValue(BasicSchemaObjAbstractValue<?>, List<Object>, String, BasicSchemaObjAbstractValue<?>, BasicSchemaObjAbstractValue<?>) - string concatenation");
				val = Joiner.on("").join(vListNoDQuote);
			}
		}
		else if (value instanceof BasicSchemaObjValueObject) {
			// Case: object is a reference to a metadata id
			BasicSchemaObjAbstractValue<?> metadataVal = 
				((BasicSchemaObjValueObject) value).getValue().get(BasicTransformationConfigUtils.METADATA);
			if (metadataVal != null && !(metadataVal instanceof BasicSchemaObjValueNull)) {
				if (metadataVal instanceof BasicSchemaObjValueInteger) {
					int metadataIntVal = ((BasicSchemaObjValueInteger) metadataVal).getValue();
					if (datasetValues == null || datasetValues.size() < metadataIntVal) {
						logger.error("inspectValue(BasicSchemaObjAbstractValue<?>, List<Object>, String, BasicSchemaObjAbstractValue<?>, BasicSchemaObjAbstractValue<?>) - access to metadata value {} is unavailable", metadataIntVal);
						throw new TransformException(MessageKey.EXCEPTION_ACCESS_METADATA_UNAVAILABLE, metadataIntVal);
					}
					else {
						Object currentVal = datasetValues.get(metadataIntVal);
						if (currentVal == null) {
							val = null;
						}
						else {
							if (isList) {
								val = "[" + inspectValue(BasicSchemaObjAbstractValue.factory(currentVal), null, datatype, format, null) + "]";
							}
							else {
								val = inspectValue(BasicSchemaObjAbstractValue.factory(currentVal), null, datatype, format, null);
							}
						}
					}
				}
				else {
					logger.error("inspectValue(BasicSchemaObjAbstractValue<?>, List<Object>, String, BasicSchemaObjAbstractValue<?>, BasicSchemaObjAbstractValue<?>) - metadata {} is specified in an unsupported way", metadataVal.getValue());
					throw new TransformException(MessageKey.EXCEPTION_ACCESS_METADATA_UNSUPPORTED);
				}
			}
			
			//Case: object is an operation with parameters
			BasicSchemaObjAbstractValue<?> operationVal = 
				((BasicSchemaObjValueObject) value).getValue().get("operation");
			if (operationVal != null && !(operationVal instanceof BasicSchemaObjValueNull)) {
				if (operationVal instanceof BasicSchemaObjValueString) {
					//String operation = ((BasicSchemaObjValueString) operationVal).getValue();
					//BasicSchemaObjAbstractValue<?> parameterVal = 
					//	((BasicSchemaObjValueObject) value).getValue().get("parameter");
					logger.error("inspectValue(BasicSchemaObjAbstractValue<?>, List<Object>, String, BasicSchemaObjAbstractValue<?>, BasicSchemaObjAbstractValue<?>) - operation {} not supported", operationVal.getValue());
					throw new TransformException(MessageKey.EXCEPTION_TRANSFORM_OPERATION_UNSUPPORTED, operationVal.getValue());
				}
				else {
					logger.error("inspectValue(BasicSchemaObjAbstractValue<?>, List<Object>, String, BasicSchemaObjAbstractValue<?>, BasicSchemaObjAbstractValue<?>) - operation {} is specified in an unsupported way", operationVal.getValue());
					throw new TransformException(MessageKey.EXCEPTION_TRANSFORM_OPERATION_SPECIFIED_UNSUPPORTED);
				}
			}
		}

		logger.trace("inspectValue(BasicSchemaObjAbstractValue<?>, List<Object>, String, BasicSchemaObjAbstractValue<?>, BasicSchemaObjAbstractValue<?>) - end");
		return val;
	}
	
	
	/**
	 * CSV to MyN Lisbon case CSV
	 * @param dataset
	 * @param metadata
	 * @param datatype
	 * @param transformationConfig
	 * @return a new Data
	 * @throws IOException
	 * @throws TransformException
	 */
	protected Data<?, ?> getTarget(CsvDataset dataset, BasicMetadata metadata,
		BasicDatatype datatype, BasicTransformationConfig transformationConfig) throws IOException, TransformException {
		Logger logger = LoggerFactory.getLogger(MyNLisbonCaseCsvTransform.class.getName() + ".getTarget.CsvDataset");
		logger.trace("getTarget(CsvDataset, BasicMetadata, BasicDatatype, BasicTransformationConfig) - start");
		
		Map<Object, BasicSchemaObjAbstractValue<?>> datatypeIdAbstractValueMap = getDatatypeIdAndBasicSchemaObjAbstractValueMap(datatype, transformationConfig, true);

		Map<Object, BasicSchemaObjAttributes> datatypeIdAttributeMap = Maps.newHashMap();
		for (int i = 0; i < datatype.getValues().size() - 1; i++) {
    		datatypeIdAttributeMap.put(i, datatype.getValuesById(i));
    	}
		
		BasicSchemaObjAttributes metadataAttributes = metadata.getValuesById(null);
		
		String datatypeName = datatype.getName();
		if (datatypeName == null) {
			datatypeName = "the selected Datatype";
		}
		
		boolean firstCall = true;
		List<List<String>> targetContent = Lists.newArrayList();
		targetContent.add(getLoopContent(firstCall, datatypeIdAbstractValueMap, datatypeIdAttributeMap, null, datatypeName));
		firstCall = false;
		
		try (
			ICsvListReader listReader = dataset.getStream();
		) {
			if (listReader != null) {
				
				List<String> columnStringList = null;
				boolean firstRow = true;
			    while ((columnStringList = listReader.read()) != null) {
			    	if (!firstRow || (firstRow && "data".equalsIgnoreCase(metadataAttributes.get(BasicMetadataUtils.FIRST_ROW).getValue().toString()))) {
			    		List<Object> columnList = new ArrayList<Object>(columnStringList);
			    		targetContent.add(getLoopContent(firstCall, datatypeIdAbstractValueMap, datatypeIdAttributeMap, columnList, datatypeName));
			    	}
			    	firstRow = false;
			    }
			}
		}
		
		Data<?, ?> returnData = new Data<CsvDataset, BasicMetadata>(new CsvDataset(targetContent), null);
		logger.trace("getTarget(CsvDataset, BasicMetadata, BasicDatatype, BasicTransformationConfig) - end");
		return returnData;
	}
	
	/**
	 * Excel to MyN Lisbon case CSV
	 * @param dataset
	 * @param metadata
	 * @param datatype
	 * @param transformationConfig
	 * @return a new Data
	 * @throws TransformException
	 * @throws IOException
	 * @throws InvalidFormatException 
	 */
	protected Data<?, ?> getTarget(ExcelDataset dataset, BasicMetadata metadata,
		BasicDatatype datatype, BasicTransformationConfig transformationConfig) throws TransformException, IOException {
		Logger logger = LoggerFactory.getLogger(MyNLisbonCaseCsvTransform.class.getName() + ".getTarget.ExcelDataset");
		logger.trace("getTarget(ExcelDataset, BasicMetadata, BasicDatatype, BasicTransformationConfig) - start");
		
		Map<Object, BasicSchemaObjAbstractValue<?>> datatypeIdAbstractValueMap = getDatatypeIdAndBasicSchemaObjAbstractValueMap(datatype, transformationConfig, true);
		
		Map<Object, BasicSchemaObjAttributes> datatypeIdAttributeMap = Maps.newHashMap();
		for (int i = 0; i < datatype.getValues().size() - 1; i++) {
    		datatypeIdAttributeMap.put(i, datatype.getValuesById(i));
    	}
		
		BasicSchemaObjAttributes metadataAttributes = metadata.getValuesById(null);
		
		boolean firstRow = true;
		boolean firstCall = true;
		try {
			dataset.buildContent();
		}
		catch (ExcelDatasetException e) {
			throw new TransformException(e.getMessage());
		}
		List<List<Object>> content = dataset.getContent();
		if (content == null) {
			logger.error("getTarget(ExcelDataset, BasicMetadata, BasicDatatype, BasicTransformationConfig) - no content for the Dataset");
			throw new TransformException(MessageKey.EXCEPTION_CANNOT_GET_CONTENT);
		}
		String datatypeName = datatype.getName();
		if (datatypeName == null) {
			datatypeName = "the selected Datatype";
		}
		List<List<String>> targetContent = Lists.newArrayList(); 
		targetContent.add(getLoopContent(firstCall, datatypeIdAbstractValueMap, datatypeIdAttributeMap, null, datatypeName));
		firstCall = false;
		for (List<Object> columnList : dataset.getContent()) {
			if (!firstRow || (firstRow && "data".equalsIgnoreCase(metadataAttributes.get(BasicMetadataUtils.FIRST_ROW).getValue().toString()))) {
				targetContent.add(getLoopContent(firstCall, datatypeIdAbstractValueMap, datatypeIdAttributeMap, columnList, datatypeName));
	    	}
	    	firstRow = false;
		}
		
		Data<?, ?> returnData = new Data<CsvDataset, BasicMetadata>(new CsvDataset(targetContent), null);
		
		logger.trace("getTarget(ExcelDataset, BasicMetadata, BasicDatatype, BasicTransformationConfig) - end");
		return returnData;
	}
	
	/**
	 * Shared function for CSV and Excel: it unifies the code for every row of the source dataset
	 * @param firstCall
	 * @param dateFormat
	 * @param datatypeIdAbstractValueMap
	 * @param datatypeIdAttributeMap
	 * @param columnList
	 * @return a string
	 * @throws TransformException
	 */
	private static List<String> getLoopContent(boolean firstCall, Map<Object, BasicSchemaObjAbstractValue<?>> datatypeIdAbstractValueMap,
		Map<Object, BasicSchemaObjAttributes> datatypeIdAttributeMap, List<Object> columnList, String datatypeName) throws TransformException {
		Logger logger = LoggerFactory.getLogger(MyNLisbonCaseCsvTransform.class.getName() + ".getLoopContent");
		logger.trace("getLoopContent(boolean, Map<Object,BasicSchemaObjAbstractValue<?>>, Map<Object,BasicSchemaObjAttributes>, List<Object>) - start");
		
		List<String> row = Lists.newArrayList();

		String field_poi_id = getStringValue(datatypeIdAttributeMap.get(0), datatypeIdAbstractValueMap.get(0), columnList, datatypeName, firstCall);
		row.add(field_poi_id);
    	String field_neighborhood = getStringValue(datatypeIdAttributeMap.get(1), datatypeIdAbstractValueMap.get(1), columnList, datatypeName, firstCall);
    	row.add(field_neighborhood);
    	String field_title = getStringValue(datatypeIdAttributeMap.get(2), datatypeIdAbstractValueMap.get(2), columnList, datatypeName, firstCall);
    	row.add(field_title);
    	String field_category_places = getStringValue(datatypeIdAttributeMap.get(3), datatypeIdAbstractValueMap.get(3), columnList, datatypeName, firstCall);
    	row.add(field_category_places);
    	String field_body = getStringValue(datatypeIdAttributeMap.get(4), datatypeIdAbstractValueMap.get(4), columnList, datatypeName, firstCall);
    	row.add(field_body);
    	String field_photographs = getStringValue(datatypeIdAttributeMap.get(5), datatypeIdAbstractValueMap.get(5), columnList, datatypeName, firstCall);
    	row.add(field_photographs);
    	String field_website = getStringValue(datatypeIdAttributeMap.get(6), datatypeIdAbstractValueMap.get(6), columnList, datatypeName, firstCall);
    	row.add(field_website);
    	String field_email = getStringValue(datatypeIdAttributeMap.get(7), datatypeIdAbstractValueMap.get(7), columnList, datatypeName, firstCall);
    	row.add(field_email);
    	String field_phone = getStringValue(datatypeIdAttributeMap.get(8), datatypeIdAbstractValueMap.get(8), columnList, datatypeName, firstCall);
    	row.add(field_phone);
    	String field_location_latitude = getStringValue(datatypeIdAttributeMap.get(9), datatypeIdAbstractValueMap.get(9), columnList, datatypeName, firstCall);
    	row.add(field_location_latitude);
    	String field_location_longitude = getStringValue(datatypeIdAttributeMap.get(10), datatypeIdAbstractValueMap.get(10), columnList, datatypeName, firstCall);
    	row.add(field_location_longitude);
    	String field_location_address_first_line = getStringValue(datatypeIdAttributeMap.get(11), datatypeIdAbstractValueMap.get(11), columnList, datatypeName, firstCall);
    	row.add(field_location_address_first_line);
    	String field_location_address_second_line = getStringValue(datatypeIdAttributeMap.get(12), datatypeIdAbstractValueMap.get(12), columnList, datatypeName, firstCall);
    	row.add(field_location_address_second_line);
    	String field_location_city = getStringValue(datatypeIdAttributeMap.get(13), datatypeIdAbstractValueMap.get(13), columnList, datatypeName, firstCall);
    	row.add(field_location_city);
    	String field_location_country = getStringValue(datatypeIdAttributeMap.get(14), datatypeIdAbstractValueMap.get(14), columnList, datatypeName, firstCall);
    	row.add(field_location_country);
    	
		logger.trace("getLoopContent(boolean, SimpleDateFormat, Map<Object,BasicSchemaObjAbstractValue<?>>, Map<Object,BasicSchemaObjAttributes>, List<Object>) - end");
		return row;
	}
}
