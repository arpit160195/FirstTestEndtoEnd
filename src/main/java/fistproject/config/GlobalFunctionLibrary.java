package fistproject.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GlobalFunctionLibrary {
	
	public Object[][] excelDataExtractAllRows(String workBookName, String workSheetName) throws IOException {
		FileInputStream fin = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\" + workBookName + ".xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(fin);
		XSSFSheet workSheet = workBook.getSheet(workSheetName);

		int totalCols = 0;
		ArrayList<ArrayList<String>> excelData = new ArrayList<ArrayList<String>>();
		
		Iterator<Row> rows = workSheet.iterator();
		Row rowHeader = rows.next();
		Iterator<Cell> cells = rowHeader.cellIterator();
		while(cells.hasNext()) {
			cells.next();
			totalCols++;
		}
		
		Cell cell = null;
		Row row = null;
		int itrRowIndex = 0;
		int itrColIndex = 0;
		while(rows.hasNext()) {
			excelData.add(new ArrayList<String>());
			row = rows.next();
			cells = row.cellIterator();
			while(cells.hasNext()) {
				cell = cells.next();
				if(cell.getCellType()==CellType.STRING) {
					//excelData.add(itrIndex, cell.getStringCellValue());
					excelData.get(itrRowIndex).add(itrColIndex, cell.getStringCellValue());
				}
				else {
					excelData.get(itrRowIndex).add(itrColIndex, NumberToTextConverter.toText(cell.getNumericCellValue()));
				}
				itrColIndex++;
			}
			itrRowIndex++;
			itrColIndex = 0;
		}
		
		Object[][] data = new Object[excelData.size()][excelData.get(0).size()];
		for(int itrRows = 0;itrRows < excelData.size(); itrRows++) {
			for(int itrCols = 0;itrCols < excelData.get(0).size(); itrCols++) {
				data[itrRows][itrCols] = excelData.get(itrRows).get(itrCols);
			}
		}
		return data;
	}
	
}
