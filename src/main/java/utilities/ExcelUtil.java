package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class ExcelUtil {		
		static FileInputStream fis = null;

	    public FileInputStream getFileInputStream(String path) {
	        File srcFile = new File(path);
	        try {
	            fis = new FileInputStream(srcFile);
	        } catch (FileNotFoundException e) {
	            System.out.println("Test Data file not found !!");
	            System.exit(0);
	        }
	        return fis;
	    }

	    public Object[][] getExcelData(String excelname,String sheetname) throws IOException {
	    	
	    	fis = getFileInputStream(System.getProperty("user.dir")+"/src/test/resources/"+(excelname)+".xlsx");
	        XSSFWorkbook wb = new XSSFWorkbook(fis);
	        XSSFSheet sheet = wb.getSheet(sheetname);

	        int TotalNumberOfRows = (sheet.getLastRowNum());
	        int TotalNumberOfCols = sheet.getRow(1).getLastCellNum();

	        String[][] arrayExcelData = new String[TotalNumberOfRows][TotalNumberOfCols];
	        for (int i = 1; i <= TotalNumberOfRows; i++) {
	            for (int j = 0; j <= TotalNumberOfCols; j++) {
	                XSSFRow row = sheet.getRow(i);
	                if(row.getCell(j) != null)
	                	arrayExcelData[i-1][j] = row.getCell(j).toString();
	            }
	        }
	        wb.close();
	        return arrayExcelData;
	    }

}
