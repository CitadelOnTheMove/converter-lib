package eu.citadel.converter.transform;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.data.Data;
import eu.citadel.converter.data.dataset.CsvDataset;
import eu.citadel.converter.data.dataset.ExcelDataset;
import eu.citadel.converter.data.datatype.BasicDatatype;
import eu.citadel.converter.data.datatype.Datatype;
import eu.citadel.converter.data.metadata.BasicMetadata;
import eu.citadel.converter.exceptions.ConverterException;
import eu.citadel.converter.exceptions.TransformException;
import eu.citadel.converter.localization.MessageKey;
import eu.citadel.converter.transform.config.BasicTransformationConfig;
import eu.citadel.converter.transform.config.TransformationConfig;

/**
 * Abstract class implementing common useful methods.
 * @author Leonardo Dal Zovo
 */
public abstract class BasicTransform implements Transform {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(BasicTransform.class);
	
	/**
	 * The source Data
	 */
	protected Data<?, ?> data = null;
	
	/**
	 * The Datatype
	 */
	protected Datatype datatype = null;
	
	/**
	 * The TransformationConfig
	 */
	protected TransformationConfig transformationConfig = null;
	
	
	/**
	 * Default Constructor
	 */
	public BasicTransform() {
		this(null, null, null);
		logger.trace("BasicTransform() - start");
		logger.trace("BasicTransform() - end");
	}
	
	/**
	 * Constructor with all parameters
	 * @param data the source Datatype
	 * @param datatype the Datatype
	 * @param transformationConfig the TransformationConfig
	 */
	public BasicTransform(Data<?, ?> data, Datatype datatype, TransformationConfig transformationConfig) {
		super();
		logger.trace("BasicTransform(Data<?,?>, Datatype, TransformationConfig) - start");
		setSource(data);
		setDatatype(datatype);
		setTransformationConfig(transformationConfig);
		logger.trace("BasicTransform(Data<?,?>, Datatype, TransformationConfig) - end");
	}
	
	
	@Override
	public Data<?, ?> getSource() {
		logger.trace("getSource() - start");
		logger.trace("getSource() - end");
		return data;
	}

	@Override
	public boolean setSource(Data<?, ?> source) {
		logger.trace("setSource(Data<?,?>) - start");
		data = source;
		logger.trace("setSource(Data<?,?>) - end");
		return true;
	}

	@Override
	public Datatype getDatatype() {
		logger.trace("getDatatype() - start");
		logger.trace("getDatatype() - end");
		return datatype;
	}

	@Override
	public boolean setDatatype(Datatype datatype) {
		logger.trace("setDatatype(Datatype) - start");
		this.datatype = datatype;
		logger.trace("setDatatype(Datatype) - end");
		return true;
	}

	@Override
	public TransformationConfig getTransformationConfig() {
		logger.trace("getTransformationConfig() - start");
		logger.trace("getTransformationConfig() - end");
		return transformationConfig;
	}

	@Override
	public boolean setTransformationConfig(TransformationConfig transformationConfig) {
		logger.trace("setTransformationConfig(TransformationConfig) - start");
		this.transformationConfig = transformationConfig;
		logger.trace("setTransformationConfig(TransformationConfig) - end");
		return true;
	}
	
	@Override
	public Data<?, ?> getTarget() throws ConverterException, IOException {
		logger.trace("getTarget() - start");
		if (data == null) {
			logger.error("getTarget() - no Data");
			throw new TransformException(MessageKey.EXCEPTION_NOT_PROVIDED, "Data");
		}
		else {
			// Datatype
			BasicDatatype basicDatatype = null;
			if (datatype == null) {
				logger.error("getTarget() - no Datatype");
				throw new TransformException(MessageKey.EXCEPTION_NOT_PROVIDED, "Datatype");
			}
			else if (datatype instanceof BasicDatatype) {
				basicDatatype = (BasicDatatype) datatype;
			}
			else {
				logger.error("getTarget() - unsupported Datatype - {}", datatype.getClass().getName());
				throw new TransformException(MessageKey.EXCEPTION_NOT_IMPLEMENTED,
					"BasicTransform.getTarget()", datatype.getClass().getName());
			}
			
			// Transformation
			BasicTransformationConfig basicTransformationConfig = null;
			if (transformationConfig == null) {
				logger.error("getTarget() - no TransformationConfig");
				throw new TransformException(MessageKey.EXCEPTION_NOT_PROVIDED, "TransformationConfig");
			}
			else if (transformationConfig instanceof BasicTransformationConfig) {
				basicTransformationConfig = (BasicTransformationConfig) transformationConfig;
			}
			else {
				logger.error("getTarget() - unsupported TransformationConfig - {}", transformationConfig.getClass().getName());
				throw new TransformException(MessageKey.EXCEPTION_NOT_IMPLEMENTED,
					"BasicTransform.getTarget()", transformationConfig.getClass().getName());
			}
			
			// Metadata
			Object metadata = data.getMetadata();
			BasicMetadata basicMetadata = null;
			if (metadata == null) {
				logger.error("getTarget() - no Metadata");
				throw new TransformException(MessageKey.EXCEPTION_NOT_PROVIDED, "Metadata");
			}
			if (metadata instanceof BasicMetadata) {
				basicMetadata = (BasicMetadata) metadata;
			}
			else {
				logger.error("getTarget() - unsupported Metadata - {}", metadata.getClass().getName());
				throw new TransformException(MessageKey.EXCEPTION_NOT_IMPLEMENTED,
					"BasicTransform.getTarget()", metadata.getClass().getName());
			}
			
			// Dataset
			Object dataset = data.getDataset();
			if (dataset == null) {
				logger.error("getTarget() - no Dataset");
				throw new TransformException(MessageKey.EXCEPTION_NOT_PROVIDED, "Dataset");
			}
			
			if (dataset instanceof CsvDataset) {
				if (((CsvDataset) dataset).getCsvType() == null) {
					logger.error("getTarget() - no CsvType");
					throw new TransformException(MessageKey.EXCEPTION_NOT_PROVIDED, "CsvType");
				}
				Data<?, ?> returnData = getTarget((CsvDataset) dataset, basicMetadata, basicDatatype, basicTransformationConfig);
				logger.trace("getTarget() - end");
				return returnData;
			}
			else if (dataset instanceof ExcelDataset) {
				if (((ExcelDataset) dataset).getExcelType() == null) {
					logger.error("getTarget() - no ExcelType");
					throw new TransformException(MessageKey.EXCEPTION_NOT_PROVIDED, "ExcelType");
				}
				Data<?, ?> returnData = getTarget((ExcelDataset) dataset, basicMetadata, basicDatatype, basicTransformationConfig);
				logger.trace("getTarget() - end");
				return returnData;
			}
			else {
				logger.error("getTarget() - unsupported Dataset - {}", dataset.getClass().getName());
				throw new TransformException(MessageKey.EXCEPTION_NOT_IMPLEMENTED,
					"BasicTransform.getTarget()", dataset.getClass().getName());
			}
		}
	}
	
	protected Data<?, ?> getTarget(CsvDataset dataset, BasicMetadata metadata,
		BasicDatatype datatype, BasicTransformationConfig transformationConfig) throws IOException, TransformException {
		Logger logger = LoggerFactory.getLogger(BasicTransform.class.getName() + ".getTarget.CsvDataset");
		logger.trace("getTarget(CsvDataset, BasicMetadata, BasicDatatype, BasicTransformationConfig) - start");
		logger.error("getTarget(CsvDataset, BasicMetadata, BasicDatatype, BasicTransformationConfig) - not implemented!");
		throw new TransformException(MessageKey.EXCEPTION_NOT_IMPLEMENTED, "getTarget(CsvDataset, BasicMetadata, BasicDatatype, BasicTransformationConfig)", BasicTransform.class.getName());
	}
	
	protected Data<?, ?> getTarget(ExcelDataset dataset, BasicMetadata metadata,
		BasicDatatype datatype, BasicTransformationConfig transformationConfig) throws TransformException, IOException {
		Logger logger = LoggerFactory.getLogger(BasicTransform.class.getName() + ".getTarget.ExcelDataset");
		logger.trace("getTarget(ExcelDataset, BasicMetadata, BasicDatatype, BasicTransformationConfig) - start");
		logger.error("getTarget(ExcelDataset, BasicMetadata, BasicDatatype, BasicTransformationConfig) - not implemented!");
		throw new TransformException(MessageKey.EXCEPTION_NOT_IMPLEMENTED, "getTarget(ExcelDataset, BasicMetadata, BasicDatatype, BasicTransformationConfig)", BasicTransform.class.getName());
	}
}
