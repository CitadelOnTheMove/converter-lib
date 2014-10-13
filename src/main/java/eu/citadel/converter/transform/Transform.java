package eu.citadel.converter.transform;

import java.io.IOException;

import eu.citadel.converter.data.Data;
import eu.citadel.converter.data.datatype.Datatype;
import eu.citadel.converter.exceptions.ConverterException;
import eu.citadel.converter.transform.config.TransformationConfig;

/**
 * Abstract definition of Transform with basic features.
 * @author Leonardo Dal Zovo
 */
public interface Transform {
	
	/**
	 * Return the source Data.
	 * @return the source Data
	 */
	public Data<?, ?> getSource();
	
	/**
	 * Set the source Data.
	 * @param source the source Data
	 * @return true if successful otherwise false
	 */
	public boolean setSource(Data<?, ?> source);
	
	/**
	 * Return the Datatype.
	 * @return the Datatype
	 */
	public Datatype getDatatype();
	
	/**
	 * Set the Datatype.
	 * @param datatype the Datatype
	 * @return true if successful otherwise false
	 */
	public boolean setDatatype(Datatype datatype);
	
	/**
	 * Return the TransformationConfig.
	 * @return the TransformationConfig
	 */
	public TransformationConfig getTransformationConfig();
	
	/**
	 * Set the TransformationConfig.
	 * @param transformationConfig the TransformationConfig
	 * @return true if successful otherwise false
	 */
	public boolean setTransformationConfig(TransformationConfig transformationConfig);
	
	/**
	 * Run the transformation.
	 * @return the converted Data
	 * @throws ConverterException
	 * @throws IOException
	 */
	public Data<?, ?> getTarget() throws ConverterException, IOException;
}