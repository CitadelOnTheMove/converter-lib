package eu.citadel.converter.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import eu.citadel.converter.data.datatype.BasicDatatype;
import eu.citadel.converter.data.datatype.Datatype;
import eu.citadel.converter.data.metadata.BasicMetadata;
import eu.citadel.converter.data.metadata.Metadata;
import eu.citadel.converter.exceptions.ConverterException;
import eu.citadel.converter.exceptions.DatatypeException;
import eu.citadel.converter.exceptions.MetadataException;
import eu.citadel.converter.localization.MessageKey;
import eu.citadel.converter.schema.BasicSchemaUtils;
import eu.citadel.converter.schema.obj.BasicSchemaObjAbstractValue;
import eu.citadel.converter.schema.obj.BasicSchemaObjAttributes;
import eu.citadel.converter.schema.obj.BasicSchemaObjElements;

/**
 * Utilities to automatically match Datatype and Metadata
 * to guess some TransformationConfig configurations.
 * @author Leonardo Dal Zovo
 */
public class Matching {
	/**
	 * Return a Map matching id of datatype and id of metadata if 
	 * it's the only possible matching based on the category of the element.
	 * @param datatype 
	 * @param metadata
	 * @return a new map
	 * @throws ConverterException
	 */
	public static Map<BasicSchemaObjAbstractValue<?>, BasicSchemaObjAbstractValue<?>> getSingleMatch(Datatype datatype, Metadata metadata) throws ConverterException {
		Logger logger = LoggerFactory.getLogger(Matching.class.getName() + ".getSingleMatch");
		logger.trace("getSingleMatch(Datatype, Metadata) - start");
		
		Map<BasicSchemaObjAbstractValue<?>, BasicSchemaObjAbstractValue<?>> matching = Maps.newHashMap();

		BasicDatatype basicDatatype = null;
		if (datatype instanceof BasicDatatype) {
			basicDatatype = (BasicDatatype) datatype;
		}
		else if (datatype != null) {
			logger.error("getSingleMatch(Datatype, Metadata) - unsupported Datatype");
			throw new DatatypeException(MessageKey.EXCEPTION_NOT_IMPLEMENTED,
				"Matching.getSingleMatch(Datatype, Metadata)", datatype.getClass().getName());
		}
		else {
			logger.debug("getSingleMatch(Datatype, Metadata) - datatype is null");
			logger.trace("getSingleMatch(Datatype, Metadata) - end");
			return matching;
		}
		
		BasicMetadata basicMetadata = null;
		if (metadata instanceof BasicMetadata) {
			basicMetadata = (BasicMetadata) metadata;
		}
		else if (metadata != null) {
			logger.error("getSingleMatch(Datatype, Metadata) - unsupported Metadata");
			throw new MetadataException(MessageKey.EXCEPTION_NOT_IMPLEMENTED,
				"Matching.getSingleMatch(Datatype, Metadata)", metadata.getClass().getName());
		}
		else {
			logger.debug("getSingleMatch(Datatype, Metadata) - metadata is null");
			logger.trace("getSingleMatch(Datatype, Metadata) - end");
			return matching;
		}
		
		BasicSchemaObjElements datatypeElements = basicDatatype.getValues();
		BasicSchemaObjElements metadataElements = basicMetadata.getValues();
		for (java.util.Map.Entry<BasicSchemaObjAbstractValue<?>, BasicSchemaObjAttributes> datatypeElement : datatypeElements.entrySet()) {
			BasicSchemaObjAbstractValue<?> datatypeKey = datatypeElement.getKey();
			BasicSchemaObjAbstractValue<?> categoryDatatypeValue = datatypeElement.getValue().get(BasicSchemaUtils.CATEGORY);
			if (categoryDatatypeValue != null) {
				List<BasicSchemaObjAbstractValue<?>> metadataValueList = Lists.newArrayList();
				for (java.util.Map.Entry<BasicSchemaObjAbstractValue<?>, BasicSchemaObjAttributes> metadataElement : metadataElements.entrySet()) {
					BasicSchemaObjAbstractValue<?> metadataKey = metadataElement.getKey();
					BasicSchemaObjAbstractValue<?> categoryMetadataValue = metadataElement.getValue().get(BasicSchemaUtils.CATEGORY);
					if (categoryMetadataValue != null && categoryMetadataValue.equals(categoryDatatypeValue)) {
						metadataValueList.add(metadataKey);
					}
				}
				if (metadataValueList.size() == 1) {
					matching.put(datatypeKey, metadataValueList.get(0));
				}
			}
		}

		logger.trace("getSingleMatch(Datatype, Metadata) - end");
		return matching;
	}
	
	/**
	 * Return a Map matching id of datatype and the list of id of metadata
	 * with a matching based on the category of the element.
	 * @param datatype 
	 * @param metadata
	 * @return a new map
	 * @throws ConverterException
	 */
	public static Map<BasicSchemaObjAbstractValue<?>, List<BasicSchemaObjAbstractValue<?>>> getAllPossibleMatch(Datatype datatype, Metadata metadata) throws ConverterException {
		Logger logger = LoggerFactory.getLogger(Matching.class.getName() + ".getAllPossibleMatch");
		logger.trace("getAllPossibleMatch(Datatype, Metadata) - start");
		
		Map<BasicSchemaObjAbstractValue<?>, List<BasicSchemaObjAbstractValue<?>>> matching = Maps.newHashMap();

		BasicDatatype basicDatatype = null;
		if (datatype instanceof BasicDatatype) {
			basicDatatype = (BasicDatatype) datatype;
		}
		else if (datatype != null) {
			logger.error("getAllPossibleMatch(Datatype, Metadata) - unsupported Datatype");
			throw new DatatypeException(MessageKey.EXCEPTION_NOT_IMPLEMENTED,
				"Matching.getAllPossibleMatch(Datatype, Metadata)", datatype.getClass().getName());
		}
		else {
			logger.debug("getAllPossibleMatch(Datatype, Metadata) - datatype is null");
			logger.trace("getAllPossibleMatch(Datatype, Metadata) - end");
			return matching;
		}
		
		BasicMetadata basicMetadata = null;
		if (metadata instanceof BasicMetadata) {
			basicMetadata = (BasicMetadata) metadata;
		}
		else if (metadata != null) {
			logger.error("getAllPossibleMatch(Datatype, Metadata) - unsupported Metadata");
			throw new MetadataException(MessageKey.EXCEPTION_NOT_IMPLEMENTED,
				"Matching.getAllPossibleMatch(Datatype, Metadata)", metadata.getClass().getName());
		}
		else {
			logger.debug("getAllPossibleMatch(Datatype, Metadata) - metadata is null");
			logger.trace("getAllPossibleMatch(Datatype, Metadata) - end");
			return matching;
		}
		
		BasicSchemaObjElements datatypeElements = basicDatatype.getValues();
		BasicSchemaObjElements metadataElements = basicMetadata.getValues();
		for (java.util.Map.Entry<BasicSchemaObjAbstractValue<?>, BasicSchemaObjAttributes> datatypeElement : datatypeElements.entrySet()) {
			BasicSchemaObjAbstractValue<?> datatypeKey = datatypeElement.getKey();
			BasicSchemaObjAbstractValue<?> categoryDatatypeValue = datatypeElement.getValue().get(BasicSchemaUtils.CATEGORY);
			if (categoryDatatypeValue != null) {
				List<BasicSchemaObjAbstractValue<?>> metadataValueList = Lists.newArrayList();
				for (java.util.Map.Entry<BasicSchemaObjAbstractValue<?>, BasicSchemaObjAttributes> metadataElement : metadataElements.entrySet()) {
					BasicSchemaObjAbstractValue<?> metadataKey = metadataElement.getKey();
					BasicSchemaObjAbstractValue<?> categoryMetadataValue = metadataElement.getValue().get(BasicSchemaUtils.CATEGORY);
					if (categoryMetadataValue != null && categoryMetadataValue.equals(categoryDatatypeValue)) {
						metadataValueList.add(metadataKey);
					}
				}
				if (metadataValueList.size() > 0) {
					matching.put(datatypeKey, metadataValueList);
				}
			}
		}

		logger.trace("getAllPossibleMatch(Datatype, Metadata) - end");
		return matching;
	}
}
