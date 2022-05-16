package flipkart.pageobjects;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import flipkart.util.Helper;

public class ItemDetailsPage extends BasePage{
	WebDriver driver;
	Properties prop;
	ExtentTest extentTest;
	
	private By addTocart = By.xpath("//button[text()='ADD TO CART' or text()='GO TO CART']");
	
	
	public ItemDetailsPage(WebDriver driver, Properties prop, ExtentTest extentTest) {
		super(driver);
		this.driver = driver;
		this.prop = prop;
		this.extentTest = extentTest;
	}
	
	public void moveToCartPage() {
		jsScrollForElement(driver.findElement(addTocart));
		waitForElement(addTocart);
		extentTest.log(LogStatus.INFO, extentTest.addScreenCapture(Helper.captureScreenshot(driver, prop.getProperty("ssFilePath")+this.getClass().getSimpleName())));
		waitAndClick(addTocart);
	}
	
}
