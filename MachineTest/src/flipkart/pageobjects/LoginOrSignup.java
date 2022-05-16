package flipkart.pageobjects;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import flipkart.util.Helper;

public class LoginOrSignup extends BasePage{
	WebDriver driver;
	Properties prop;
	ExtentTest extentTest;
	
	private By pageIdentifier = By.xpath("//span[text()='Login or Signup']");
	
	public LoginOrSignup(WebDriver driver, Properties prop, ExtentTest extentTest) {
		super(driver);
		this.driver = driver;
		this.prop = prop;
		this.extentTest = extentTest;
	}
	
	public void validateLoginorSignUpPageReached() {
		if(isElementPresent(pageIdentifier)) {
			extentTest.log(LogStatus.INFO, extentTest.addScreenCapture(Helper.captureScreenshot(driver, prop.getProperty("ssFilePath")+this.getClass().getSimpleName())));
			System.out.println("Reached to LoginOrSignup page");
			extentTest.log(LogStatus.PASS, "Reached to LoginOrSignup page");
		}
		else {
			System.err.println("Didnt reach to LoginOrSignup page");
			extentTest.log(LogStatus.FAIL, "Reached to LoginOrSignup page");
		}
	}
	
	
	
	
	
}
