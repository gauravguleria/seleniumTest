package repos;


import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;




public class TagHandlers {
	//css=".post-body.entry-content.float-container div div div:nth-child(19) input"
	
	@FindBy(xpath="//input[@name='firstname']")public WebElement firstName;
	@FindBy(xpath="//input[@name='lastname']")public WebElement lastName;
	@FindBy(xpath="//input[@name='sex']")public List<WebElement> radio_gender;
	@FindBy(xpath="//input[@name='exp']")public List<WebElement> radio_yearOfExperience;
	@FindBy(xpath="//input[@id='datepicker']")public WebElement date;
	@FindBy(xpath="//input[@name='profession']")public List<WebElement>profession;
	@FindBy(xpath="//input[@name='tool']")public List<WebElement>automationTools;
	@FindBy(id="continents")public WebElement continents;
	@FindBy(xpath="//select[@id='selenium_commands']")public WebElement seleniumCommands;
	@FindBy(xpath="//input[@class='input-file']")public WebElement filePath;
	@FindBy(xpath="//button[@id='submit']")public WebElement submit;
	
	
	public void fillTextBox(WebElement element, String data) {
		element.clear();
		element.sendKeys(data);
		
	}
	
	public void selectRadio(List<WebElement> element, String data) {
		for(WebElement ref : element) {
			//System.out.println("Tag==="+ref.getAttribute("value"));
			if(ref.getAttribute("value").equalsIgnoreCase(data)) {
				ref.click();
				break;
			}
		}
		
	}
	public void selectRadio(List<WebElement> element, int data) {
		for(WebElement ref : element) {
			if(Integer.parseInt(ref.getAttribute("value"))==data) {
				ref.click();
				break;
			}
		}
		
	}
	
	public void selectItem(WebElement element, String data) {
		//System.out.println(element);
		Select select=new Select(element);				
		select.selectByVisibleText(data);;
		
	}

	public void click(WebElement element) {
		element.click();
		
	}
}
