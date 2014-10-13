package eu.citadel.converter.data.dataset;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import eu.citadel.converter.exceptions.DatasetException;
import eu.citadel.converter.exceptions.ExcelDatasetException;
import eu.citadel.converter.localization.MessageKey;

/**
 * Dataset in Excel format.
 * @author Leonardo Dal Zovo
 */
public class ExcelDataset extends Dataset {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(ExcelDataset.class);

	/**
	 * Content of Excel
	 */
	protected List<List<Object>> content = null;
	
	/**
	 * Excel Type
	 * @see eu.citadel.converter.data.dataset.ExcelType
	 */
	protected ExcelType excelType = null;
	
	/**
	 * Charset of Excel
	 */
	protected Charset charset = null;
	
	
	/**
	 * Create an empty ExcelDataset
	 */
	public ExcelDataset() {
		super();
		logger.trace("ExcelDataset() - start");
		logger.trace("ExcelDataset() - end");
	}

	/**
	 * Create a ExcelDataset using the provided path
	 * @param path
	 */
	public ExcelDataset(Path path) {
		super(path);
		logger.trace("ExcelDataset(Path) - start");
		logger.trace("ExcelDataset(Path) - end");
	}
	
	/**
	 * Create a ExcelDataset using the provided content
	 * @param content
	 */
	public ExcelDataset(List<List<Object>> content) {
		logger.trace("ExcelDataset(List<List<Object>>) - start");
		this.content = content;
		status.add(DatasetStatus.STATUS_CONTENT);
		logger.trace("ExcelDataset(List<List<Object>>) - content");
		logger.trace("ExcelDataset(List<List<Object>>) - end");
	}

	/**
	 * Create a ExcelDataset using the provided url
	 * @param url
	 */
	public ExcelDataset(URL url) {
		super(url);
		logger.trace("ExcelDataset(URL) - start");
		logger.trace("ExcelDataset(URL) - end");
	}
	

	/**
	 * Get the current ExcelType
	 * @return the excelType
	 */
	public ExcelType getExcelType() {
		logger.trace("getExcelType() - start");
		logger.trace("getExcelType() - end");
		return excelType;
	}

	/**
	 * Set the new ExcelType
	 * @param excelType the ExcelType to set
	 */
	public void setExcelType(ExcelType excelType) {
		logger.trace("setExcelType(ExcelType) - start");
		this.excelType = excelType;
		logger.trace("setExcelType(ExcelType) - end");
	}
	
	/**
	 * Get the current Charset
	 * @return the Charset
	 */
	public Charset getCharset() {
		logger.trace("getCharset() - start");
		logger.trace("getCharset() - end");
		return charset;
	}

	/**
	 * Set the new Charset
	 * @param charset the Charset to set
	 */
	public void setCharset(Charset charset) {
		logger.trace("setCharset(Charset) - start");
		this.charset = charset;
		logger.trace("setCharset(Charset) - end");
	}
	
	/**
	 * @see eu.citadel.converter.data.dataset.Dataset#getType()
	 */
	public String getType() {
		logger.trace("getType() - start");
		logger.trace("getType() - end");
		return DatasetType.TYPE_EXCEL;
	}
	
	/**
	 * Get the first row of the ExcelDataset.
	 * @return a list containing the values of the first row or an empty list if not present
	 * @throws IOException
	 * @throws ExcelDatasetException 
	 */
	public List<Object> getFirstRow() throws IOException, ExcelDatasetException {
		logger.trace("getFirstRow() - start");
		if (status.contains(DatasetStatus.STATUS_CONTENT)) {
			logger.trace("getFirstRow() - {}", DatasetStatus.STATUS_CONTENT);
			List<Object> returnList = content.size() > 0 ? content.get(0) : Lists.newArrayList();
			logger.debug("getFirstRow() - return: {}", returnList);
			logger.trace("getFirstRow() - end", returnList);
			return returnList;
		}
		else if (status.contains(DatasetStatus.STATUS_PATH) || status.contains(DatasetStatus.STATUS_TEMPPATH)) {
			logger.trace("getFirstRow() - {}", DatasetStatus.STATUS_PATH + " or " + DatasetStatus.STATUS_TEMPPATH);
			List<List<Object>> content = new ExcelDatasetContentBuilder()
				.setPath(status.contains(DatasetStatus.STATUS_PATH) ? path : tempPath)
				.setExcelType(excelType)
				.setLines(1)
				.build();
			List<Object> returnList = content.isEmpty() ? Lists.newArrayList() : content.get(0);
			logger.debug("getFirstRow() - return: {}", returnList);
			logger.trace("getFirstRow() - end", returnList);
			return returnList;
		}
		else {
			List<Object> returnList = Lists.newArrayList();
			logger.debug("getFirstRow() - return: {}", returnList);
			logger.trace("getFirstRow() - end", returnList);
			return returnList;
		}
	}
	
	/**
	 * Return the content of Excel, based on the status in order of importance:
	 * CONTENT: the content already present.
	 * PATH: as obtained after the last call to build()
	 * TEMPPATH: as obtained after the last call to build()
	 * @return the content of CSV
	 * @throws IOException
	 */
	public List<List<Object>> getContent() throws IOException {
		logger.trace("getContent() - start");
		if (status.contains(DatasetStatus.STATUS_CONTENT)) {
			logger.trace("getContent() - {}", DatasetStatus.STATUS_CONTENT);
			logger.trace("getContent() - end");
			return content;
		}
		else if (status.contains(DatasetStatus.STATUS_PATH)) {
			logger.trace("getContent() - {}", DatasetStatus.STATUS_PATH);
			logger.trace("getContent() - end");
			return content;
		}
		else if (status.contains(DatasetStatus.STATUS_TEMPPATH)) {
			logger.trace("getContent() - {}", DatasetStatus.STATUS_TEMPPATH);
			logger.trace("getContent() - end");
			return content;
		}

		logger.trace("getContent() - end");
		return content;
	}
	
	/**
	 * Build the content of Excel, based on the status:
	 * CONTENT: content already there, return false
	 * PATH: if path and excelType are provided build the content and return true otherwise false
	 * URL: if url and excelType are provide, store the file locally and then build the content and return true, otherwise false
	 * TEMPPATH: if tempPath and excelType are provided build the content and return true otherwise false
	 * other: false.
	 * The result of the last successful call can be obtained calling getContent().
	 * @return a boolean
	 * @throws IOException
	 * @throws ExcelDatasetException 
	 * @throws InvalidFormatException 
	 */
	public boolean buildContent() throws IOException, ExcelDatasetException {
		logger.trace("buildContent() - start");
		if (status.contains(DatasetStatus.STATUS_CONTENT)) {
			logger.trace("buildContent() - {}", DatasetStatus.STATUS_CONTENT);
			logger.trace("buildContent() - end - false");
			return false;
		}
		else if (status.contains(DatasetStatus.STATUS_PATH)) {
			logger.trace("buildContent() - {}", DatasetStatus.STATUS_PATH);
			if (path == null || excelType == null) {
				logger.trace("buildContent() - end - false");
				return false;
			}
			else {
				content = new ExcelDatasetContentBuilder()
					.setPath(path)
					.setExcelType(excelType)
					.build();
				logger.trace("buildContent() - end - true");
				return true;
			}
		}
		else if (status.contains(DatasetStatus.STATUS_URL) || status.contains(DatasetStatus.STATUS_TEMPPATH)) {
			logger.trace("buildContent() - {}", DatasetStatus.STATUS_URL + " or " + DatasetStatus.STATUS_TEMPPATH);
			if (url == null || excelType == null) {
				logger.debug("buildContent() - return: false");
				logger.trace("buildContent() - end");
				return false;
			}
			else {
				createTempFile("converter", ".excel.tmp");
				content = new ExcelDatasetContentBuilder()
					.setPath(tempPath)
					.setExcelType(excelType)
					.build();
				logger.debug("buildContent() - return: true");
				logger.trace("buildContent() - end");
				return true;
			}
		}
		else {
			logger.trace("buildContent() - end - false");
			return false;
		}
	}
	
	/**
	 * Return index and names of the sheets of the Excel file.
	 * If the path or tempPath is not provided, return null.
	 * @return a new map of index and names of the sheets
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public Map<Integer, String> getSheetMap() throws ExcelDatasetException, IOException {
		logger.trace("getSheetMap() - start");
		if (status.contains(DatasetStatus.STATUS_PATH) || status.contains(DatasetStatus.STATUS_TEMPPATH)) {
			logger.trace("getSheetMap() - {}", DatasetStatus.STATUS_PATH + " or " + DatasetStatus.STATUS_TEMPPATH);
			Map<Integer, String> sheetMap = Maps.newHashMap();
			Workbook wb = null;
			try {
				wb = WorkbookFactory.create(status.contains(DatasetStatus.STATUS_PATH) ? path.toFile() : tempPath.toFile());
			}
			catch (InvalidFormatException e) {
				throw new ExcelDatasetException(MessageKey.EXCEPTION_EXCEL_INVALID_FORMAT, e.getMessage());
			}
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				sheetMap.put(i, wb.getSheetName(i));
			}
			logger.debug("getSheetMap() - return: {}", sheetMap);
			logger.trace("getSheetMap() - end");
			return sheetMap;
		}
		logger.debug("getSheetMap() - return: null");
		logger.trace("getSheetMap() - end");
		return null;
	}

	@Override
	public void saveAs(Path path, boolean overwrite) throws DatasetException, IOException {
		logger.trace("saveAs(Path, boolean) - start");
		logger.debug("saveAs(Path, boolean) - {}, {}", path, overwrite);
		logger.error("saveAs(Path, boolean) - not implemented!");
		throw new DatasetException(MessageKey.EXCEPTION_NOT_IMPLEMENTED, "saveAs(Path, boolean)", getClass().getName());
	}
}
