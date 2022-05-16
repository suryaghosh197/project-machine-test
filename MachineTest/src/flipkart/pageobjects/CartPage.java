package flipkart.pageobjects;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import flipkart.util.Helper;

public class CartPage extends BasePage{
	WebDriver driver;
	Properties prop;
	ExtentTest extentTest;
	
	private By pageIdentifier = By.xpath("//div[contains(text(),'My Cart')]");
	private By itemName = By.xpath("//div[contains(text(),'Seller:')]/preceding-sibling::div[2]");
	private By itemPrice = By.xpath("//div[contains(text(),'Seller:')]/following-sibling::span[1]");
	private By placeOrder = By.xpath("//span[text()='Place Order']/parent::button");
	
	public CartPage(WebDriver driver, Properties prop, ExtentTest extentTest) {
		super(driver);
		this.driver = driver;
		this.prop = prop;
		this.extentTest = extentTest;
	}
	
	
	public String getcartPageItemPrice(String itemNameInput) {
		System.out.println("****"+itemNameInput);
		String itemPriceFromScreen = null;
		waitForElement(pageIdentifier);
		extentTest.log(LogStatus.INFO, extentTest.addScreenCapture(Helper.captureScreenshot(driver, prop.getProperty("ssFilePath")+this.getClass().getSimpleName())));
		List<WebElement> itemNames = driver.findElements(itemName);
		
		for(int i=0;i<itemNames.size();i++) {
			System.out.println(itemNames.get(i).getText());
			if(itemNames.get(i).getText().equalsIgnoreCase(itemNameInput)) {
				itemPriceFromScreen = driver.findElements(itemPrice).get(i).getText();
			}
		}
		return itemPriceFromScreen;
	}
	
	
	public void placeOrder() {
		waitForElement(placeOrder);
		waitAndClick(placeOrder);
	}
}
