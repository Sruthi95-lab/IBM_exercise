package com.ibm.fst.nopcomm.exercise2.utility;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.ibm.fst.nopcomm.exercise2.pages.CustomerSearchPage;
import com.ibm.fst.nopcomm.exercise2.pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	WebDriver driver;
	Logger log;
	protected LoginPage loginPage;
	protected CustomerSearchPage custSearchPage;
	
	@BeforeTest
	public void launchApp() {
		
		log = Logger.getLogger("nopComm App");
		PropertyConfigurator.configure(".\\testdata\\log4j.properties");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		log.info("launching chrome browser");
		driver.manage().window().maximize();
		driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F"); 
		log.info("launching application: " + "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		loginPage = new LoginPage(driver);
		custSearchPage = new CustomerSearchPage(driver);
		
	}
	
	@AfterTest
	public void closeApp() throws Exception {
		
		Thread.sleep(6000);
		log.info("closing an application: " );
		driver.close();
		driver.quit();
		
	}
	
	  public void getScreenshot(String fileName) throws Exception {
	        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);              //base64 format
	        
	        File targetLocation = new File(".//ibm_screenshot//"+fileName+"_IBM.png");
	        
	        FileUtils.copyFile(screenshotFile, targetLocation);
	        
	    }

}
