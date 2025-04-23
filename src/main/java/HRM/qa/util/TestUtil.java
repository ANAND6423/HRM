package HRM.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;
import org.apache.poi.ss.usermodel.*;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.Table.Cell;

import HRM.qa.base.Testbase;

public class TestUtil  extends Testbase{
	
	public static long page_load_timeout = 10;
	public static long implicit_wait = 10;

	//===========Explicit wait
	public static void PresenceOfElement(WebElement element,int time)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	//=======capture screenshot
	 public static String captureScreenshot(String methodName) {
	        String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
	        String screenshotDir = System.getProperty("user.dir") + "/screenshots/";
	        new File(screenshotDir).mkdirs(); // Make sure directory exists

	        String screenshotPath = screenshotDir + methodName + "_" + timestamp + ".png";
	        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	        try {
	            FileUtils.copyFile(scrFile, new File(screenshotPath));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return screenshotPath;
	    }
	 
	 //=====Read dynamic data from excel

	 public static String TESTDATA_SHEET_PATH =  System.getProperty("user.dir") + "/src/main/resources/TestData.xlsx";

		static Workbook book;
		static Sheet sheet;
		static JavascriptExecutor js;
		

		public static Object[][] getTestData(String sheetName) {
			FileInputStream file = null;
			try {
				file = new FileInputStream(TESTDATA_SHEET_PATH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				book = WorkbookFactory.create(file);
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			sheet = book.getSheet(sheetName);
			Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				
				}
			}
			return data;
		}
		
		
		//================= write Dynamic data into Excel 
		
//		public static String TESTSheet_PATH =  System.getProperty("user.dir") + "/src/main/resources/TestCase.xlsx";
//
//	    public static void writeTestResultsToExcel(String filePath, String sheetName) {
//	        try (
//	            FileInputStream fis = new FileInputStream(filePath);
//	            Workbook workbook = new XSSFWorkbook(fis)
//	        ) {
//	            Sheet sheet = workbook.getSheet(sheetName);
//	            Random random = new Random();
//
//	            // Loop through rows (skip header, start from row 1)
//	            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//	                Row row = sheet.getRow(i);
//	                if (row == null) continue;
//
//	                // Simulate test logic — randomly pass or fail
//	                boolean testPassed = random.nextBoolean(); // You can replace this with real test condition
//
//	                // Write result to column D (index 3)
//	                Cell resultCell = (Cell) row.getCell(3);
//	                if (resultCell == null) {
//	                    resultCell = (Cell) row.createCell(3);
//	                }
//
//	                if (testPassed) {
//	                    ((org.apache.poi.ss.usermodel.Cell) resultCell).setCellValue("Pass");
//	                    System.out.println("Row " + i + ": Test Passed");
//	                } else {
//	                    ((org.apache.poi.ss.usermodel.Cell) resultCell).setCellValue("Fail");
//	                    System.out.println("Row " + i + ": Test Failed");
//	                }
//	            }
//
//	            // Save updated Excel file
//	            try (FileOutputStream fos = new FileOutputStream(filePath)) {
//	                workbook.write(fos);
//	                System.out.println("✅ Test results updated in Excel.");
//	            }

//	        } catch (IOException e) {
//	            System.err.println("❌ Error accessing the Excel file: " + e.getMessage());
//	        }
//	    }
//		
		
		
		
		}


