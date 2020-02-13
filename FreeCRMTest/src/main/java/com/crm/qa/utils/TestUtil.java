package com.crm.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {

	// Here you can maintain all the common method that is not different and
	// can be used everywhere necessary.

	public static long PAGE_LOAD_TIMEOUT = 15;

	public static long IMPLICIT_WAIT = 20;

	public static String SHEET_PATH = "D:\\SELENIUM WORKSPACES\\SEL0220\\FreeCRMTest\\"
			+ "src\\main\\java\\com\\crm\\qa\\testdatas\\CRM_Test_Datas.xlsx";

	public static XSSFWorkbook wb;
	public static XSSFSheet ws;

	public void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
	}

	// everything should be static inside the util page. if no static then you cant
	// call it-
	// into the main method where you want it to be used.
	// variables book and sheet are not declared public because it can be called
	// through this method-
	// if those variables where to be called directly then you have to mention these
	// also as statc.

	public static Object[][] getTestData(String shName) throws Exception {

		/*
		 * FileInputStream file = null;
		 * 
		 * try { file = new FileInputStream(SHEET_PATH);
		 * 
		 * } catch (FileNotFoundException e) { e.printStackTrace(); }
		 * 
		 * try { book = WorkbookFactory.create(file); } catch (IOException e) {
		 * e.printStackTrace(); }
		 * 
		 * 
		 * file = new FileInputStream(SHEET_PATH); book = WorkbookFactory.create(file);
		 * sheet = book.getSheet(shName);
		 * 
		 * Object[][] data = new
		 * Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		 * 
		 * for (int i = 0; i < sheet.getLastRowNum(); i++) { for (int j = 0; j <
		 * sheet.getRow(0).getLastCellNum(); j++) { data[i][j] =
		 * sheet.getRow(i+1).getCell(j).toString(); } } return data;
		 */

		// very very important note, you can get nullpointer exception-
		// without any reason, it is probably because it didnt took rowsize correctly-
		// its taking way more that seen, it can happen when cell which
		// look blank is formated or was edited before. it counts those too.
		// so check for the rowsize during the iteration
		// also the getstringcellvalue dont take numeric values. have to ormat cell
		// in the excel cell to the text type.
		// without delay delete that sheet and creat a new sheet. use that!

		FileInputStream fis = new FileInputStream(SHEET_PATH);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws = wb.getSheet(shName);

//		int rowsize=ws.getPhysicalNumberOfRows();
//		int colsize=ws.getRow(0).getLastCellNum();

		Object[][] data = new Object[ws.getLastRowNum()][ws.getRow(0).getLastCellNum()];
		for (int i = 0; i < ws.getLastRowNum(); i++) {
			for (int j = 0; j < ws.getRow(0).getLastCellNum(); j++) {
				data[i][j] = ws.getRow(i + 1).getCell(j).getStringCellValue();
			}
		}
		wb.close();
		return data;
	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
//		Calendar calendar = Calendar.getInstance(); **not working
//		create new folder name screenshot yourself and place it as the parent folders for all ss.*

		FileHandler.copy(scrFile, new File(currentDir + "\\ScreenShoots\\" + System.currentTimeMillis() + ".png"));
//		FileHandler.copy(scrFile, new File(currentDir+"\\ScreenShoots\\"+calendar.getTime()+".png")); **not working

		// FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" +
		// System.currentTimeMillis() + ".png")); **in mac
	}

}
