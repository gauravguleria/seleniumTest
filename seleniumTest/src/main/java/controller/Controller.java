package controller;

import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


import repos.TagHandlers;
import utilities.Utilities;

public class Controller {
	public static ArrayList<ArrayList<Object>> row=null;
	static WebDriver driver=null;
	static String projectPath=System.getProperty("user.dir");

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
		String firstName=(String)row.get(0).get(1);
		String lastName=(String)row.get(0).get(2);
		String gender=(String)row.get(0).get(3);
		double yearOfExperience=(Double)row.get(0).get(4);
		int yoe=(int)yearOfExperience;
		String date=(String)row.get(0).get(5);
		String profession=(String)row.get(0).get(6);
		String automationTool=(String)row.get(0).get(7);
		String continent=(String)row.get(0).get(8);
		String seleniumCommand=(String)row.get(0).get(9);
		String filePath=(String)row.get(0).get(10);

		//System.out.println(continent);
		//System.out.println(seleniumCommand);



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