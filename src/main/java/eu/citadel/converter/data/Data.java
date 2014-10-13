package eu.citadel.converter.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.citadel.converter.data.dataset.Dataset;
import eu.citadel.converter.data.metadata.Metadata;

/**
 * Data combine a {@link eu.citadel.converter.data.dataset.Dataset Dataset} and a
 * {@link eu.citadel.converter.data.metadata.Metadata Metadata}.
 * @author Leonardo Dal Zovo
 */
public class Data<D extends Dataset, M extends Metadata> {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(Data.class);

	/**
	 * Dataset
	 */
	protected D dataset = null;
	
	/**
	 * Metadata
	 */
	protected M metadata = null;
	
	/**
	 * Default constructor
	 */
	public Data() {
		logger.trace("Data() - start");

		logger.trace("Data() - end");
	}
	
	/**
	 * Constructor with the specified parameters
	 * @param dataset the dataset to set
	 * @param metadata the metadata to set
	 */
	public Data(D dataset, M metadata) {
		logger.trace("Data(D, M) - start");

		this.dataset = dataset;
		this.metadata = metadata;

		logger.trace("Data(D, M) - end");
	}

	/**
	 * @return the dataset
	 */
	public D getDataset() {
		logger.trace("getDataset() - start");

		logger.trace("getDataset() - end");
		return dataset;
	}

	/**
	 * @param dataset the dataset to set
	 */
	public void setDataset(D dataset) {
		logger.trace("setDataset(D) - start");

		this.dataset = dataset;

		logger.trace("setDataset(D) - end");
	}

	/**
	 * @return the metadata
	 */
	public M getMetadata() {
		logger.trace("getMetadata() - start");

		logger.trace("getMetadata() - end");
		return metadata;
	}

	/**
	 * @param metadata the metadata to set
	 */
	public void setMetadata(M metadata) {
		logger.trace("setMetadata(M) - start");

		this.metadata = metadata;

		logger.trace("setMetadata(M) - end");
	}
}

