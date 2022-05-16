package flipkart.steps;

import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import flipkart.pageobjects.CartPage;
import flipkart.pageobjects.HomePage;
import flipkart.pageobjects.ItemDetailsPage;
import flipkart.pageobjects.ItemSearchPage;
import flipkart.pageobjects.LoginOrSignup;
import flipkart.util.Helper;

public class FlipkartSteps {
	 ExtentTest extentTest;
	private WebDriver driver;
	private Properties prop;
	
	private Map<String,String> itemAndPrice;
	
	
	
	private static final String COMMA = ",";

	
	private void initializeDriver() { 

		System.setProperty("webdriver.chrome.driver",prop.getProperty("chromeDriverPath"));
		
		driver = new ChromeDriver();
		
	}
	
	
	
	public FlipkartSteps initializeTestAndLaunchUrl(Properties prop, ExtentTest extentTest) {
		this.extentTest = extentTest;
		this.prop = prop;
		initializeDriver();
		driver.get(prop.getProperty("flipkartUrl"));
		driver.manage().window().maximize();
		return this;
	}
	
	
	public FlipkartSteps searchForAnItem() {
		HomePage homepage = new HomePage(driver,prop,extentTest);
		homepage.searchAnItem();
		return this;
	}
	
	public FlipkartSteps selectAnItem() {
		ItemSearchPage itemSearchPage = new ItemSearchPage(driver,prop,extentTest);
		itemAndPrice = itemSearchPage.selectAnItemAndGetPrice();
		System.out.println(itemAndPrice);
		return this;
	}
	
	
	public FlipkartSteps moveToCartPage() {
		ItemDetailsPage temDetailsPage = new ItemDetailsPage(driver,prop,extentTest);
		temDetailsPage.moveToCartPage();
		return this;
	}
	
	
	public FlipkartSteps validatePriceAndPlaceOrder() {
		CartPage cartPage = new CartPage(driver,prop,extentTest);
		String cartPagePrice = cartPage.getcartPageItemPrice(itemAndPrice.keySet().stream().collect(Collectors.toList()).get(0));
		cartPagePrice = cartPagePrice.replace(COMMA, "");
		cartPagePrice = cartPagePrice.substring(1,cartPagePrice.length());
		
		
		
		String itemSearchPagePrice = itemAndPrice.values().stream().collect(Collectors.toList()).get(0);
		itemSearchPagePrice = itemSearchPagePrice.replace(COMMA, "");
		itemSearchPagePrice = itemSearchPagePrice.substring(1,itemSearchPagePrice.length());
		
		
		if(cartPagePrice.equalsIgnoreCase(itemSearchPagePrice)) {
			System.out.println("PASS");
			extentTest.log(LogStatus.PASS, "Item Price Validation | SearchPagePrice: "+itemSearchPagePrice+" | CartPagePrice: "+cartPagePrice);
		}
		else {
			System.err.println("FAIL");
			extentTest.log(LogStatus.FAIL, "Item Price Validation | SearchPagePrice: "+itemSearchPagePrice+" | CartPagePrice: "+cartPagePrice);
		}
		
		
		cartPage.placeOrder();
		
		LoginOrSignup loginOrSignup = new LoginOrSignup(driver,prop,extentTest);
		loginOrSignup.validateLoginorSignUpPageReached();
		
		return this;
	}
	
	
	
	
	public FlipkartSteps closeBrowser() {
		driver.quit();
		return this;
	}
	
	
	

}
