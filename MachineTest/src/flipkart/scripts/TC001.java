package flipkart.scripts;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import flipkart.steps.FlipkartSteps;
import flipkart.util.Helper;

public class TC001 {

	ExtentReports report;
	ExtentTest extentTest;
	Properties prop;
	
	@BeforeTest
	public void startReporting() {
		prop = Helper.readPropertiyFile(System.getProperty("user.dir")+"//src//flipkart//util//config.properties");
		report = new ExtentReports(prop.getProperty("extentreportPath"));
	}
	
	@Test
	public void LoginAndValidateItemPrice() {
		extentTest = report.startTest("LoginAndValidateItemPrice");
		new FlipkartSteps().initializeTestAndLaunchUrl(prop,extentTest).searchForAnItem().selectAnItem().moveToCartPage().validatePriceAndPlaceOrder().closeBrowser();
	}
	
	@AfterTest
	public void stopReporting() {
		report.endTest(extentTest);
		report.flush();
	}
}
