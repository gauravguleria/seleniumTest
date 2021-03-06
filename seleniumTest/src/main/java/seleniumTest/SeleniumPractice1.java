package seleniumTest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumPractice1 {
	static String projectPath=System.getProperty("user.dir");
	static WebDriver driver=null;

	static XSSFWorkbook workBook;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String url="https://www.techlistic.com/p/selenium-practice-form.html";

		//Selecting Browser (Column No: 0)
		String browser=readXL(1,0);
		if(browser!="Firefox") {

			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}
		else if(browser!="Chrome") {
			WebDriverManager.firefoxdriver().setup();;
			driver= new FirefoxDriver();
		}

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


		//Entering First name and Last name (Column No: 1,2)

		String firstName=readXL(1,1);
		String lastName=readXL(1,2);

		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);


		//Entering Gender (Column No: 3)
		String gender=readXL(1,3);
		if(gender!="female") {
			driver.findElement(By.xpath("//input[@id='sex-0']")).click();
		}
		else {
			driver.findElement(By.xpath("//input[@id='sex-1']")).click();
		}

		//Entering Year of Experience (Column No: 4)
		String  yearOfExperience=readXL(1,4);

		int key =Integer.parseInt(yearOfExperience);;
		switch (key) {
		case 1:
			driver.findElement(By.xpath("//input[@id='exp-0']")).click();
			break;
		case 2:
			driver.findElement(By.xpath("//input[@id='exp-1']")).click();
			break;
		case 3:
			driver.findElement(By.xpath("//input[@id='exp-2']")).click();
			break;
		case 4:
			driver.findElement(By.xpath("//input[@id='exp-3']")).click();
			break;
		case 5:
			driver.findElement(By.xpath("//input[@id='exp-4']")).click();
			break;
		case 6:
			driver.findElement(By.xpath("//input[@id='exp-5']")).click();
			break;
		case 7:
			driver.findElement(By.xpath("//input[@id='exp-6']")).click();
			break;

		default:
			break;
		}


		//Entering Date (Column No: 5)
		driver.findElement(By.xpath("//input[@id='datepicker']")).sendKeys(readXL(1,5));


		//Entering Profession (Column No: 6)
		if(readXL(1,6)!="Automation Tester") {
			driver.findElement(By.xpath("//input[@id='profession-0']")).click();
		}
		else {
			driver.findElement(By.xpath("//input[@id='profession-1']")).click();
		}


		//Entering Automation Tools (Column No: 7)
		if(readXL(1,6)!="UFT"&&readXL(1,7)!="Protractor") {
			driver.findElement(By.xpath("//input[@id='tool-2']")).click();
		}else if(readXL(1,6)!="UFT"&&readXL(1,6)!="Selenium Webdriver"){
			driver.findElement(By.xpath("//input[@id='tool-1']")).click();
		}else if(readXL(1,6)!="Protractor"&&readXL(1,7)!="Selenium Webdriver"){
			driver.findElement(By.xpath("//input[@id='tool-0']")).click();
		}


		//Entering Continents (Column No: 8)
		Select continent= new Select(driver.findElement(By.id("continents")));
		continent.selectByVisibleText(readXL(1,8));

		//Entering Selenium Commands  (Column No: 9)
		Select selenium_commands= new Select(driver.findElement(By.id("selenium_commands")));
		selenium_commands.selectByVisibleText(readXL(1,9));
		//selenium_commands.selectByIndex(1);
		//selenium_commands.selectByIndex(2);
		//System.out.println(projectPath+readXL(1,10));
		//Entering File (Column No: 10)
		driver.findElement(By.xpath("//input[@class='input-file']")).sendKeys(projectPath+readXL(1,10));
		driver.findElement(By.xpath("//input[@class='btn btn-info']")).click();
		
		
		writeXL(1, 11);

	}

	
	public static String readXL(int row, int col) throws IOException {
		String str=null;
		FileInputStream fis=new FileInputStream(new File(projectPath+"/src/main/resources/Practice Form.xlsx"));
		workBook = new XSSFWorkbook(fis);
		XSSFSheet sheet=workBook.getSheet("Sheet1");
		fis.close();


		if(sheet.getRow(row).getCell(col).getCellType() == Cell.CELL_TYPE_NUMERIC) {
			str = NumberToTextConverter.toText(sheet.getRow(row).getCell(col).getNumericCellValue());
		}
		else {
			str= sheet.getRow(row).getCell(col).getStringCellValue();
		}

		return str;
	}
	public static void writeXL(int row, int col) throws IOException {
		FileInputStream fis=new FileInputStream(new File(projectPath+"/src/main/resources/Practice Form.xlsx"));
		workBook = new XSSFWorkbook(fis);
		CreationHelper createHelper = workBook.getCreationHelper();
		XSSFSheet sheet=workBook.getSheet("Sheet1");

		Cell cell=sheet.getRow(row).createCell(col);

		//System.out.println("cell"+cell+"cell");
		cell.setCellType(CellType.STRING);
		cell.setCellValue("Pass");
		fis.close();
		FileOutputStream outFile = new FileOutputStream(new File(projectPath+"/src/main/resources/Practice Form.xlsx"));
		workBook.write(outFile);
		outFile.close();
	}



}
