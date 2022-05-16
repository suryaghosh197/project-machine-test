package flipkart.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;





public class BasePage {
	WebDriver driver;
	WebDriverWait wait;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10, 100);
		
	}
	
	
	public boolean isElementPresent(By element) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
			return true;
		}
		catch(Exception e) {
			return false;
		}
		
	}
	
	public void waitForElement(By element) {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
		
	}
	
	public void waitForElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public void waitAndClick(By element) {
			wait.until(ExpectedConditions.elementToBeClickable(element));
//			
			driver.findElement(element).click();
			
	}
	
	public void waitAndJsClick(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		
	}
	
	public void waitAndEnterText(By element, String text) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
		driver.findElement(element).sendKeys(text);
		
	}
	
	public void jsScrollForElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
		
	}
	
	public void pressEsc() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).perform();
	}
	
	public void pressEnter() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).perform();
	}
	
	
	

}
