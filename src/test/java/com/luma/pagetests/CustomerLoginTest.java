package com.luma.pagetests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.luma.pages.CustomerLoginPage;
import com.luma.pages.HomePage;

public class CustomerLoginTest extends BaseTest{
	
	
	@Test
	public void verifySuccessfullLogin() {
		
		
		HomePage homePage = new HomePage(driver);
		homePage.clickSignin();

		CustomerLoginPage csLoginPage = new CustomerLoginPage(driver);
		csLoginPage.enterUsername("connectwithcgupta@gmail.com");
		csLoginPage.enterPassword("Test@123");
		csLoginPage.clickSignIn();
	
		Assert.assertEquals(csLoginPage.getPageTitle(), "Home Page");
		csLoginPage.clickSignIn();

	}
	
	
	@Test
	public void verifySuccessfullLogin2() {
		
		
		HomePage homePage = new HomePage(driver);
		homePage.clickSignin();

		CustomerLoginPage csLoginPage = new CustomerLoginPage(driver);
		csLoginPage.enterUsername("connectwithcgupta@gmail.com");
		csLoginPage.enterPassword("Test@1233");
		csLoginPage.clickSignIn();
	
		Assert.assertEquals(csLoginPage.getPageTitle(), "Home Page");

	}
}
