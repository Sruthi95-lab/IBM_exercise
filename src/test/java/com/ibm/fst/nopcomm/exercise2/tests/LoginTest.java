package com.ibm.fst.nopcomm.exercise2.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ibm.fst.nopcomm.exercise2.utility.BaseTest;





public class LoginTest extends BaseTest {
	
	@DataProvider(name ="login")
	public Object[][] getTestData(){
		return new Object[][] {
			{"admin@yourstore.com","admin",false,"Your store. Login","Dashboard / nopCommerce administration"}
			
		};
	}
	
	@Test(dataProvider = "login")
	public void verifyLoginFunctionality(String username,String password,boolean remCheck,
			String beforeLog,String afterLog) throws Exception {
		
		
		 // Call the loginPage actions 
		loginPage.verifyApplicationTitle(beforeLog);
		
		
		
		// Call the loginPage actions
		loginPage.logIn(username, password, remCheck);
		
		
		// Call the loginPage actions
		loginPage.verifyApplicationTitle(afterLog);
		
	}

}
