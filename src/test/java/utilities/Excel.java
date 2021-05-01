package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	private Workbook wb;
	private Sheet sh;
	private Row row;
	private Cell cell;
	private int rowCount;
	private int colCount;
	private String[][] excelStringArray;
	private List<String> excelList;
	private HashMap<Integer, String> excelMap;
	private DataFormatter objDefaultFormat;
	private FormulaEvaluator objFormulaEvaluator;
	public String fileFormat = "";
	public File file;
	public FileInputStream fs;
	public boolean result;

	public Workbook getToFile(String filePath, String excelFileName) throws Exception {

		if (excelFileName != "") {
			if (excelFileName.indexOf(".") > 0) {
				fileFormat = excelFileName.substring(excelFileName.indexOf("."));
				if (fileFormat.equalsIgnoreCase(".xlsx") || fileFormat.equalsIgnoreCase(".xls")) {
					file = new File(filePath + excelFileName);
					fs = new FileInputStream(file);
					wb = createWorkBookInstance(fs);
				}
			}
		}

		return wb;
	}

	public Sheet getToSheet(String filePath, String excelFileName, String sheetName) throws Exception  {


		wb = getToFile(filePath, excelFileName);
		if (sheetName!="") {
			if (sheetPresent(sheetName)) {
				sh = wb.getSheet(sheetName);
			} 
		} 
		return sh;
	}

	private Workbook createWorkBookInstance(FileInputStream fs) throws IOException {

		if (fileFormat.equals(".xlsx")) {
			wb = new XSSFWorkbook(fs);
			
		} else if (fileFormat.equals(".xls")) {
			wb = new HSSFWorkbook(fs);
			
		} 
	
		
		return wb;
	}

	private boolean sheetPresent(String sheetName) {
		int sheets = wb.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			String tempName = wb.getSheetAt(i).getSheetName();
			if (tempName.equalsIgnoreCase(sheetName)) {
				result = true;
				break;
			} else {
				result = false;
			}
		}
		
		return result;
	}

	private String getCellValue(Cell cell){

		String cellValue = "";

		objDefaultFormat = new DataFormatter();
		if (fileFormat.equals(".xlsx")) {
			objFormulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
		} else if (fileFormat.equals(".xls")) {
			objFormulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) wb);
		} 
		objFormulaEvaluator.evaluate(cell);
		cellValue = objDefaultFormat.formatCellValue(cell, objFormulaEvaluator);
		return cellValue;
	}

	public Object[][] getTestDataAsTwoDimesionalObjectArray(String filePath, String excelFileName,
			String sheetName) throws Exception {

		
		Object[][] excelObjectArray = null;

		sh = getToSheet(filePath, excelFileName, sheetName);
		if (sh.getRow(0) != null) {
			rowCount = sh.getLastRowNum();
			if (rowCount != 0) {
				colCount = sh.getRow(0).getLastCellNum();
				excelObjectArray = new String[rowCount][colCount];
				int k = 0;
				String cellValue = "";
				for (int i = 1; i <= rowCount; i++, k++) {
					row = sh.getRow(i);
					int l = 0;
					for (int j = 0; j < colCount; j++, l++) {
						if (row.getCell(j) != null) {
							cell = row.getCell(j);
							cellValue = getCellValue(cell);
							excelObjectArray[k][l] = cellValue;
						} else {
							cellValue = "";
							excelObjectArray[k][l] = cellValue;
						}
					}
				}
			} 
		} 
		return excelObjectArray;
	}

	

	

	

}