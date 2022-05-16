package flipkart.pageobjects;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import flipkart.util.Helper;

public class HomePage extends BasePage{
	WebDriver driver;
	Properties prop;
	ExtentTest extentTest;
	
	private By loginPupup = By.linkText("New to Flipkart? Create an account");
	private By searchBox = By.xpath("//input[@name='q']");
	private By searchButton = By.xpath("//input[@name='q']/parent::div/following-sibling::button");
	
	
	
	
	
	public HomePage(WebDriver driver, Properties prop, ExtentTest extentTest) {
		super(driver);
		this.driver = driver;
		this.prop = prop;
		this.extentTest = extentTest;
	}
	
	private void closeLoginPopup() {
		if(isElementPresent(loginPupup)) {
			pressEsc();
		}
	}
	
	public void searchAnItem() {
		closeLoginPopup();
		extentTest.log(LogStatus.INFO, extentTest.addScreenCapture(Helper.captureScreenshot(driver, prop.getProperty("ssFilePath")+this.getClass().getSimpleName())));
		waitAndEnterText(searchBox, prop.getProperty("itemToSearch"));
		waitAndClick(searchButton);
	}
	
	
	
	

}
