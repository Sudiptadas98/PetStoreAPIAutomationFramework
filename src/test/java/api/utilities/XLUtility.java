package api.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class XLUtility {
	
	private String filePath;
    private Workbook workbook;
    private Sheet sheet;

    public XLUtility(String filePath) {
        this.filePath = filePath;
    }

    // Get total number of rows in a sheet
    public int getRowCount(String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        workbook.close();
        fis.close();
        return rowCount;
    }

    // Get total number of cells (columns) in a row
    public int getCellCount(String sheetName, int rowNum) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        workbook.close();
        fis.close();
        return cellCount;
    }

    // Get cell data
    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);

        String data;
        try {
            DataFormatter formatter = new DataFormatter();
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
            data = "";
        }

        workbook.close();
        fis.close();
        return data;
    }

    // Set cell data
    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);

        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }

        Cell cell = row.getCell(colNum);
        if (cell == null) {
            cell = row.createCell(colNum);
        }

        cell.setCellValue(data);

        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        workbook.close();
        fis.close();
        fos.close();
    }

}
