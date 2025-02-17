package com.luma.pagetests;

import java.util.Map;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import com.luma.pages.CustomerLoginPage;
import com.luma.pages.HomePage;

import manager.DriverManager;
import utils.TestDataHolder;

public class CustomerLoginTest extends BaseTest{
	
	
	@Test
	public void verifySuccessfullLogin() {
			
		Map <String, String> data = TestDataHolder.getTestData();
		
		HomePage homePage = new HomePage(DriverManager.getDriver());
		homePage.clickSignin();

		CustomerLoginPage csLoginPage = new CustomerLoginPage(DriverManager.getDriver());
		csLoginPage.enterUsername(data.get("UserName"));
		csLoginPage.enterPassword(data.get("Password"));
		csLoginPage.clickSignIn();
	
		Assert.assertEquals(csLoginPage.getPageTitle(), "Home Page");
		csLoginPage.clickSignIn();

	}
	
	
	@Test
	public void verifySuccessfullLogin2() {
		
		Map <String, String> data = TestDataHolder.getTestData();
				
		HomePage homePage = new HomePage(DriverManager.getDriver());
		homePage.clickSignin();

		CustomerLoginPage csLoginPage = new CustomerLoginPage(DriverManager.getDriver());
		csLoginPage.enterUsername(data.get("UserName"));
		csLoginPage.enterPassword(data.get("Password"));
		csLoginPage.clickSignIn();
	
		Assert.assertEquals(csLoginPage.getPageTitle(), "Home Page");

	}
}
