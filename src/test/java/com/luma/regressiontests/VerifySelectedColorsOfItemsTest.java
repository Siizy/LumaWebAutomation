package com.luma.regressiontests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.luma.pages.CustomerLoginPage;
import com.luma.pages.HomePage;
import com.luma.pages.NewLumaYogaCollectionPage;
import com.luma.pagetests.BaseTest;

public class VerifySelectedColorsOfItemsTest extends BaseTest {
	
	
	@Test
	public void verifyColorsOfSelectedItems() {
		
		HomePage homePage = new HomePage(driver);
		homePage.clickSignin();
		Assert.assertEquals(homePage.getPageTitle(), "Customer Login");
		
		CustomerLoginPage customerloginPage = new CustomerLoginPage(driver);
		customerloginPage.enterUsername("connectwithcgupta@gmail.com");
		customerloginPage.enterPassword("Test@123");
		customerloginPage.clickSignIn();
		Assert.assertEquals(homePage.getPageTitle(), "Home Page");
		
		homePage.clickShopNewYoga();
		NewLumaYogaCollectionPage newLunaYogaPage = new NewLumaYogaCollectionPage(driver);
		Assert.assertEquals(newLunaYogaPage.getPageTitle(), "New Luma Yoga Collection");
		
		newLunaYogaPage.selectBlueColorFilter("Blue");		
		boolean result = newLunaYogaPage.isAllSelectedItemColorBlue("Blue");
		Assert.assertTrue(result);		
	}

}
