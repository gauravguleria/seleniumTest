package repos;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class TagHandlers {

	public static void fillFirstName(WebDriver driver, String firstName) {
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstName);
	}


	public static void fillLastName(WebDriver driver, String lastName) {
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
	}


	public static void fillGender(WebDriver driver, String gender) {
		if(gender!="female") {
			driver.findElement(By.xpath("//input[@id='sex-0']")).click();
		}
		else {
			driver.findElement(By.xpath("//input[@id='sex-1']")).click();
		}
	}

	public static void yearOfExperience(WebDriver driver, Object object) {
		System.out.println(object);
		int key=Integer.valueOf(object.toString());
		//int key =Integer.parseInt(yearOfExperience);
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

	}
	public static void fillDate(WebDriver driver, String date) {
		driver.findElement(By.xpath("//input[@id='datepicker']")).sendKeys(date);
	}


	public static void fillProfession(WebDriver driver, String profession) {
		if(profession!="Automation Tester") {
			driver.findElement(By.xpath("//input[@id='profession-0']")).click();
		}
		else {
			driver.findElement(By.xpath("//input[@id='profession-1']")).click();
		}
	}

	public static void fillAutomationTools(WebDriver driver, String automationTools) {
		if(automationTools!="UFT"&&automationTools!="Protractor") {
			driver.findElement(By.xpath("//input[@id='tool-2']")).click();
		}else if(automationTools!="UFT"&&automationTools!="Selenium Webdriver"){
			driver.findElement(By.xpath("//input[@id='tool-1']")).click();
		}else if(automationTools!="Protractor"&&automationTools!="Selenium Webdriver"){
			driver.findElement(By.xpath("//input[@id='tool-0']")).click();
		}
	}

	public static void fillContinents(WebDriver driver, String continents) {
		Select continent= new Select(driver.findElement(By.id("continents")));
		continent.selectByVisibleText(continents);
	}

	public static void fillSeleniumCommands(WebDriver driver, String seleniumCommands) {
		Select selenium_commands= new Select(driver.findElement(By.id("selenium_commands")));
		selenium_commands.selectByVisibleText(seleniumCommands);
		selenium_commands.selectByIndex(1);
		selenium_commands.selectByIndex(2);
	}

	public static void uploadFile(WebDriver driver, String filePath) {
		driver.findElement(By.xpath("//input[@class='input-file']")).sendKeys(filePath);
	}




}
