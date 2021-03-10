package utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
	public static ArrayList<ArrayList<Object>> readXL() throws IOException {

		ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
		int maxDataCount =0;

		FileInputStream file=new FileInputStream(new File(projectPath+"/src/main/resources/Practice Form.xlsx"));

		// Create Workbook instance holding reference to .xlsx file
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		// Get first/desired sheet from the workbook
		XSSFSheet sheet = workbook.getSheetAt(0);

		// Iterate through each rows one by one
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();

			//Skip the first row beacause it will be header
			if (row.getRowNum() == 0) {

				maxDataCount = row.getLastCellNum();
				continue;
			}

			/**
			 * if row is empty then break the loop,do not go further
			 */
			if(isRowEmpty(row,maxDataCount)){
				//Exit the processing
				break;
			}

			ArrayList<Object> singleRows = new ArrayList<Object>();

			// For each row, iterate through all the columns
			for(int cn=0; cn<maxDataCount; cn++) {

				Cell cell = row.getCell(cn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

				switch (cell.getCellType()) {

				case Cell.CELL_TYPE_NUMERIC:

					if(DateUtil.isCellDateFormatted(cell)){
						singleRows.add( new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue()) );
					}else
						singleRows.add(cell.getNumericCellValue());
					break;

				case Cell.CELL_TYPE_STRING:
					singleRows.add(cell.getStringCellValue());
					break;

				case Cell.CELL_TYPE_BLANK : singleRows.add(null);break;

				default : singleRows.add(cell.getStringCellValue());
				}

			}
			list.add(singleRows);
		}
		file.close();
		workbook.close();   


		return list;
	}

	public static boolean isRowEmpty(Row row,int lastcellno) {
		for (int c = row.getFirstCellNum(); c < lastcellno; c++) {
			Cell cell = row.getCell(c,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
				return false;
		}
		return true;
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
}