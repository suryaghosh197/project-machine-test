package flipkart.pageobjects;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import flipkart.util.Helper;


public class ItemSearchPage extends BasePage{
	WebDriver driver;
	Properties prop;
	ExtentTest extentTest;
	
	
	private By pageIdentifier = By.xpath("//span[starts-with(text(),'Showing') and contains(text(),'results for')]");
	private By item = By.xpath("//span[contains(@id,'productRating')]/parent::div/preceding-sibling::div");
	private By itemPrice = By.xpath("//span[contains(@id,'productRating')]/parent::div/parent::div/following-sibling::div/div[1]//div[contains(text(),'₹')][1]");
	
	public ItemSearchPage(WebDriver driver, Properties prop, ExtentTest extentTest) {
		super(driver);
		this.driver = driver;
		this.prop = prop;
		this.extentTest = extentTest;
	}
	
	
	public Map<String,String> selectAnItemAndGetPrice() {
		Map<String,String> itemAndPrice = new HashMap<>();
		
		waitForElement(pageIdentifier);
		
		
		extentTest.log(LogStatus.INFO, extentTest.addScreenCapture(Helper.captureScreenshot(driver, prop.getProperty("ssFilePath")+this.getClass().getSimpleName())));
		
		List<WebElement> itemList = driver.findElements(item);
		List<WebElement> itemPriceList = driver.findElements(itemPrice);
//		for(int i=0; i<itemList.size();i++) {
//			
//		}
		
		
		jsScrollForElement(itemList.get(Integer.parseInt(prop.getProperty("itemToSelect"))));
		waitForElement(itemList.get(Integer.parseInt(prop.getProperty("itemToSelect"))));
		String itemName = itemList.get(Integer.parseInt(prop.getProperty("itemToSelect"))).getText();
		String itemPrice = itemPriceList.get(Integer.parseInt(prop.getProperty("itemToSelect"))).getText();
		itemAndPrice.put(itemName, itemPrice);
		
		
		waitAndJsClick(itemList.get(Integer.parseInt(prop.getProperty("itemToSelect"))));
		
		
		changeWindow();
		
		
		return itemAndPrice;
		
	}
	
	private void changeWindow() {
		Set<String> allWindows = driver.getWindowHandles();
		String mainWindow = driver.getWindowHandle();
		Iterator<String> windowIterator = allWindows.iterator();
		
		while(windowIterator.hasNext()) {
			String tempWindow = windowIterator.next();
			if(!tempWindow.equalsIgnoreCase(mainWindow)) {
				driver.switchTo().window(tempWindow);
			}
		}
		
	}
	
	
}
