package utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public  class Utilities {



	static XSSFWorkbook workBook;
	static String projectPath=System.getProperty("user.dir");
	static WebDriver driver=null;

	public static WebDriver openBrowser(String browser) throws IOException {
		String url="https://www.techlistic.com/p/selenium-practice-form.html";

		//Selecting Browser (Column No: 0)
		//System.out.println(browser.matches("Chrome"));

		if(browser.equalsIgnoreCase("Chrome")) {

			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver();
			driver= new FirefoxDriver();
		}

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	//========================================================================================================

	@SuppressWarnings("deprecation")
	public static Object [][]readXL() throws IOException {
		
		FileInputStream file=new FileInputStream(new File(projectPath+"/src/main/resources/Practice Form.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Object[][] data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0;i<sheet.getLastRowNum();i++) {
			for(int k=0;k<sheet.getRow(0).getLastCellNum();k++) {
				data[i][k]=sheet.getRow(i+1).getCell(k).toString();
			}
		}
		return data;
	}

	

	//======================================================================================================================


	public static void writeXL(int row, int col) throws IOException {
		FileInputStream fis=new FileInputStream(new File(projectPath+"/src/main/resources/Practice Form.xlsx"));
		workBook = new XSSFWorkbook(fis);
		CreationHelper createHelper = workBook.getCreationHelper();
		XSSFSheet sheet=workBook.getSheet("Sheet1");

		Cell cell=sheet.getRow(row).createCell(col);

		//System.out.println("cell"+cell+"cell");
		cell.setCellType(CellType.STRING);
		cell.setCellValue("Test passed Successfully");
		fis.close();
		FileOutputStream outFile = new FileOutputStream(new File(projectPath+"/src/main/resources/Practice Form.xlsx"));
		workBook.write(outFile);
		outFile.close();

	}
	
	
	
	public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file

                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination

                File DestFile=new File(fileWithPath);

                //Copy file at destination

                FileUtils.copyFile(SrcFile, DestFile);

    }

	
	
	
	
	
}