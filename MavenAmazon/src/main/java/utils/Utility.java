package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;


public class Utility {

	
	
	public static void captureScreenShot(WebDriver driver, String id) throws IOException {
		
     String timestamp = new SimpleDateFormat("dd-MM-yyyy hh mm ss").format(new Date());	
     File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
     File dest = new File("test-output\\ScreenShots\\" + id + timestamp + ".jpeg");
     FileHandler.copy(source, dest);
 
	}

    public static String getDatafromExcelSheet(int row, int cell) throws EncryptedDocumentException, IOException {
		String data;
		String path = "src\\main\\resources\\testdata\\testdata.xlsx";
		FileInputStream file = new FileInputStream(path);
        Workbook book = WorkbookFactory.create(file);
        try { 
	          data = book.getSheet("abc").getRow(row).getCell(cell).getStringCellValue();
		    }
		catch(IllegalStateException e) {
			double value = book.getSheet("abc").getRow(1).getCell(0).getNumericCellValue();
			data = Double.toString(value);
			}
           return data;
		}
}

