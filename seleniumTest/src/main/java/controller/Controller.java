package controller;

import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import repos.TagHandlers;
import utilities.Utilities;

public class Controller {
	public static Object[][] row=null;
	static WebDriver driver=null;
	static String projectPath=System.getProperty("user.dir");

	//read one row from excel
	@BeforeSuite
	public void openUrl() throws IOException {
		//row=Utilities.readXL();

		driver=Utilities.openBrowser("Chrome");
	}

	
	@DataProvider
	public Object[][] getData() throws IOException {
		row=Utilities.readXL();
		return row;
	}

	//fill data in web form
	@Test(dataProvider = "getData")
	public void fillData(String firstName,String lastName ,String gender, String yearOfExperience,String date,
			String profession, String automationTool, String continent, String seleniumCommand, String filePath ) throws Exception {
		
		double year=Double.parseDouble(yearOfExperience);
		int yoe=(int)year;

		TagHandlers tags=PageFactory.initElements(driver, TagHandlers.class);
		tags.fillTextBox(tags.firstName, firstName);
		tags.fillTextBox(tags.lastName, lastName);
		tags.selectRadio(tags.radio_gender, gender);
		tags.selectRadio(tags.radio_yearOfExperience, yoe);
		tags.fillTextBox(tags.date, date);
		tags.selectRadio(tags.profession, profession);
		tags.selectRadio(tags.automationTools, automationTool);
		tags.selectItem(tags.continents, continent);
		tags.selectItem(tags.seleniumCommands, seleniumCommand);
		tags.fillTextBox(tags.filePath, projectPath+filePath);
		tags.click(tags.submit);
		utilities.Utilities.takeSnapShot(driver, "/Users/gaurav.guleria/git/seleniumTest/seleniumTest/outputfile/test"+firstName+".png");
		
	}

	//if test passes write comments in excel
	@AfterMethod
	public void writeExcel() throws IOException {
		Utilities.writeXL(1, 11);

	}

	//close web browser
	@AfterSuite
	public void closeBrowser() {
		//driver.close();
		

	}


}