package eu.citadel.converter.data.dataset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.google.common.collect.Lists;

import eu.citadel.converter.exceptions.ExcelDatasetException;
import eu.citadel.converter.localization.MessageKey;

/**
 * Builder to read or get content of a {@link ExcelDataset}.
 * @author Leonardo Dal Zovo
 */
public class ExcelDatasetContentBuilder {
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(ExcelDatasetContentBuilder.class);

	/**
	 * Path: default to null. Mandatory field, use CsvDatasetContentBuilder.setPath
	 */
	private Path path = null;
	
	/**
	 * Logical lines to load: default all.
	 */
	private int lines = 0;
	
	/**
	 * ExcelType: default to ExcelType.DEFAULT.
	 */
	private ExcelType excelType = ExcelType.DEFAULT;
	
	/**
	 * Set the path to use to load the File. It's mandatory to set the path to get some content.
	 * @param path to use to load the File
	 * @return this
	 */
	public ExcelDatasetContentBuilder setPath(Path path) {
		logger.trace("setPath(Path) - start");
		this.path = path;
		logger.debug("setPath(Path) - {}", path);
		logger.trace("setPath(Path) - end");
		return this;
	}
	
	/**
	 * Set the number of rows to load.
	 * @param lines the number of logical lines of the file to consider, if o then all
	 * @return this
	 */
	public ExcelDatasetContentBuilder setLines(int lines) {
		logger.trace("setLines(int) - start");
		this.lines = lines;
		logger.debug("setLines(int) - {}", lines);
		logger.trace("setLines(int) - end");
		return this;
	}
	
	/**
	 * Set the ExcelType to use for decoding.
	 * @param excelType the ExcelType to use for decoding
	 * @return this
	 */
	public ExcelDatasetContentBuilder setExcelType(ExcelType excelType) {
		logger.trace("setExcelType(ExcelType) - start");
		this.excelType = excelType;
		logger.debug("setExcelType(ExcelType) - {}", excelType);
		logger.trace("setExcelType(ExcelType) - end");
		return this;
	}
	
	/**
	 * Return the content of the CSV. If the path or excelType is null the result is an empty content.
	 * @return the content of the CSV
	 * @throws IOException
	 * @throws ExcelDatasetException 
	 * @throws InvalidFormatException 
	 */
	public List<List<Object>> build() throws IOException, ExcelDatasetException {
		logger.trace("build() - start");
		List<List<Object>> content = Lists.newArrayList();
		if (path == null || excelType == null) {
			logger.debug("build() - return: {}", content);
			logger.trace("build() - end");
			return content;
		}
		
		logger.debug("build() - read");
		
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(path.toFile());
		}
		catch (InvalidFormatException e) {
			throw new ExcelDatasetException(MessageKey.EXCEPTION_EXCEL_INVALID_FORMAT, e.getMessage());
		}
		Sheet sheet = wb.getSheetAt(excelType.getIndexOfSheet());
		
	    int rowStart = Math.min(Integer.MAX_VALUE, sheet.getFirstRowNum());
	    int rowEnd = Math.max(Integer.MIN_VALUE, sheet.getLastRowNum() + 1);
	    int colStart = 0;
	    int colEnd = 0;

	    for (int rowNum = rowStart; rowNum < rowEnd && (rowNum < rowStart + lines || lines == 0); rowNum++) {
	    	Row r = sheet.getRow(rowNum);
	    	if (!isEmptyRow(r)) { // Possible to configure it into ExcelType
	    		
	    		List<Object> cellValueList = Lists.newArrayList();
	    		
		    	if (rowNum == rowStart) {
		    		// The first row "decide" the starting and ending column index.
		    		// This is so avoid different column count per row.
		    		colStart = Math.min(r.getFirstCellNum(), Integer.MAX_VALUE);
		    		colEnd = Math.max(r.getLastCellNum(), Integer.MIN_VALUE);
		    	}
	
		    	for (int colNum = colStart; colNum < colEnd; colNum++) {
		    		Object cellValue = null;
		    		
		    		Cell cell = r.getCell(colNum, Row.RETURN_BLANK_AS_NULL);
		    		if (cell == null) {
		    			// The spreadsheet is empty in this cell
		    		}
		    		else {
		    			switch (cell.getCellType()) {
			    			case Cell.CELL_TYPE_STRING:
			    				cellValue = cell.getRichStringCellValue().getString();
			    				break;
			    				
			    			case Cell.CELL_TYPE_NUMERIC:
			    				if (DateUtil.isCellDateFormatted(cell)) {
			    					cellValue = cell.getDateCellValue();
			    				}
			    				else {
			    					cellValue = cell.getNumericCellValue();
			    				}
			    				break;
			    				
			    			case Cell.CELL_TYPE_BOOLEAN:
			    				cellValue = cell.getBooleanCellValue();
			    				break;
			    				
			    			case Cell.CELL_TYPE_FORMULA:
			    				cellValue = cell.getCellFormula();
			    				break;
			    				
			    			default:
			    				break;
			    		}
		    		}
		    		cellValueList.add(cellValue);
		    	}
		    	content.add(cellValueList);
	    	}
	    }

		logger.trace("build() - end");
	    return content;
	}
	
	/**
	 * Return if the row contains only blank, empty or white spaces cells.
	 * @param row the row to examin
	 * @return true or false
	 */
	private boolean isEmptyRow(Row row) {
		logger.trace("isEmptyRow(Row) - start");
		boolean isEmptyRow = true;
		if (row != null) {
			for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++){
				Cell cell = row.getCell(cellNum);
				if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK &&
					(cell.toString() != null || cell.toString().trim().equalsIgnoreCase(""))) {
					isEmptyRow = false;
				}    
			}
		}
		logger.trace("isEmptyRow(Row) - end - {}", isEmptyRow);
		return isEmptyRow;
	}
}
