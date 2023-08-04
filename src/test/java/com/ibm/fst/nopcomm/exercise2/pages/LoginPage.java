package com.ibm.fst.nopcomm.exercise2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {
	
	WebDriver driver;
	
	//Intializing the webElements using page factory class
	
		public  LoginPage(WebDriver rdriver) {
			
			driver = rdriver;
			PageFactory.initElements(rdriver, this);
			
		}
		
		// Web Elements Repository
		
		//************************************************//
		@FindBy(xpath="//input[@id='Email']")
		WebElement txtEmail;
		
		@FindBy(xpath="//input[@id='Password']")
		WebElement txtPaswd;
		
		@FindBy(xpath="//input[@type='checkbox' and @name='RememberMe']")
		WebElement chkRem;
		
		@FindBy(xpath="//button[text()='Log in']")
		WebElement btnLog;
		
		@FindBy(linkText = "/logout")
		WebElement btnLogOut;
		
		
		//************************************************//
		
		
		//Actions or Methods on the login Page
		
		
		public void logIn(String usname,String pswd,boolean remCheck) {
			
			//Enter username
			txtEmail.clear();
			txtEmail.sendKeys(usname);
			
			//Enter password
			txtPaswd.clear();
			txtPaswd.sendKeys(pswd);
			
			//Click on CheckBox
			if(remCheck) {
				
				chkRem.click();
			}
			
			//Click on Login
			btnLog.click();
			
			
		}
		
		public void verifyApplicationTitle(String pagetitle) {
			
			// Verify title of the page
			Assert.assertEquals(driver.getTitle(),pagetitle);
			
			
		}
		

		public void logOut() {
			
			btnLogOut.click();
			
		}

}
