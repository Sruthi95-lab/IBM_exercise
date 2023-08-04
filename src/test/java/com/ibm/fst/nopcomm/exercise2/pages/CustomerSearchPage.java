package com.ibm.fst.nopcomm.exercise2.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CustomerSearchPage {
	
	WebDriver driver;

	public CustomerSearchPage(WebDriver rdriver) {

		driver = rdriver;
		PageFactory.initElements(rdriver, this);

	}
	
	// Web Elements Repository

		// ************************************************//

		@FindBy(xpath = "//div[@class='content-header']")
		WebElement dashBoradWTxt;

		@FindBy(xpath = "//i[@class='nav-icon far fa-user']//following-sibling::p")
		WebElement customerTab;

		@FindBy(xpath = "//p[text()=' Customers']")
		WebElement customerSubTab;

		@FindBy(id = "SearchEmail")
		WebElement txtSearchEmail;
		
		@FindBy(xpath="//input[@id='SearchFirstName']")
		WebElement txtSearchFName;
		
		@FindBy(xpath="//input[@id='SearchLastName']")
		WebElement txtSearchLName;

		@FindBy(css = "button#search-customers")
		WebElement btnSearch;

		@FindBy(xpath = "//a[text()='John Smith']")
		WebElement loginWTxt;

		@FindBy(xpath = "//table[@id='customers-grid']//tbody//tr")
		List<WebElement> rows;

		@FindBy(xpath = "//table[@id='customers-grid']//tbody//tr[1]//td")
		List<WebElement> cols;

		// ************************************************//

		// Actions or Methods on the login Page

		public void verifyOnHomePage(String expDBTxt) {

			// Verify Dashboard is visible on the screen after successful login

			Assert.assertEquals(dashBoradWTxt.getText(), expDBTxt);
			boolean isDisplay = loginWTxt.isDisplayed();
			Assert.assertTrue(isDisplay);

		}

		public void clickOnCustomerTab() {

			customerTab.click();
			customerSubTab.click();
		}

		public void searchCustomer(String criteria, String crtVal) throws InterruptedException {

			// search the Customer with specified criteria

			if (criteria.equals("email")) {

				txtSearchEmail.clear();
				txtSearchEmail.sendKeys(crtVal);
			}
			
			else if (criteria.equals("name")) {
				
				String[] strVals = crtVal.split("\\s");
				txtSearchFName.clear();
				txtSearchFName.sendKeys(strVals[0]);
				
				txtSearchLName.clear();
				txtSearchLName.sendKeys(strVals[1]);
			}

			// click on search button
			btnSearch.click();
			Thread.sleep(1000);

		}

		public void validateCustResult(String criteriaValue, String name) {

		
			int rowCount = rows.size();
			int colCount = cols.size();
			String emailVal = "";
			String nameVal = "";
			boolean flag = false;
			System.out.println(rowCount);
			System.out.println(colCount);
			for (int i = 1; i <= rowCount; i++) {
				
				emailVal =driver.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[2]")).getText(); 
				nameVal =driver.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[3]")).getText();
				if(emailVal.equalsIgnoreCase(name) && nameVal.equalsIgnoreCase(criteriaValue)) {
					flag = true;
				}
				else {
					flag = false;
				}
			Assert.assertTrue(flag);
		}

		}

}
