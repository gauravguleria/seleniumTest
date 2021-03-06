package controller;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import repos.TagHandlers;
import utilities.Utilities;

public class Controller {
	public static ArrayList<ArrayList<Object>> row=null;
	static WebDriver driver=null;
	
	//read one row from excel
	@BeforeSuite
	public void openUrl() throws IOException {
		row=Utilities.readXL();
		
		driver=Utilities.openBrowser((String)row.get(0).get(0));
	}
	

	//fill data in web form
	@Test
	public void fillData() {
		//System.out.println(row.get(0).get(0));
		TagHandlers.fillFirstName(driver, (String)row.get(0).get(1));
		TagHandlers.fillLastName(driver, (String)row.get(0).get(2));
		TagHandlers.fillGender(driver, (String)row.get(0).get(3));
		TagHandlers.yearOfExperience(driver, row.get(0).get(4));
		TagHandlers.fillDate(driver, (String)row.get(0).get(5));
		TagHandlers.fillProfession(driver, (String)row.get(0).get(6));
		TagHandlers.fillAutomationTools(driver, (String)row.get(0).get(7));
		TagHandlers.fillContinents(driver, (String)row.get(0).get(8));
		TagHandlers.fillSeleniumCommands(driver, (String)row.get(0).get(9));
		TagHandlers.uploadFile(driver, (String)row.get(0).get(10));
		TagHandlers.fillLastName(driver, (String)row.get(0).get(2));
		TagHandlers.fillLastName(driver, (String)row.get(0).get(2));
	}

	//if test passes write comments in excel
	@AfterMethod
	public void writeExcel() {

	}
	
	//close web browser
	@AfterSuite
	public void closeBrowser() {

	}


}