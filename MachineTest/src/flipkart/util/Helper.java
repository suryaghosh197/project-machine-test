package flipkart.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Helper {

	
	public static String captureScreenshot(WebDriver driver, String filePath) {
		
		try {
			
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File dstFile = new File(filePath);
			FileUtils.copyFile(srcFile, dstFile);
			
			return dstFile.getAbsolutePath();
		}
		catch(Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
		
	}
	
	
	public static Properties readPropertiyFile(String filePath) {
		Properties prop = new Properties();
		FileInputStream file;
		try {
			file = new FileInputStream(new File(filePath));
			prop.load(file);
			return prop;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
