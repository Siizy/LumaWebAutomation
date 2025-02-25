package com.luma.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ReportUtils;

public class CustomerLoginPage extends BasePage{
	
	//WebDriver driver;
	
	public CustomerLoginPage(WebDriver driver){
		super(driver);
	}
	
	//locators
	private By emailText = By.id("email"); 
	private By passwordText = By.id("pass");
	private By signInButton = By.xpath("//div[@class='login-container']//span[text()='Sign In']");
	
	
	public void enterUsername(String username) {
		
		driver.findElement(emailText).sendKeys(username);
		ReportUtils.getLog().info("Enter UserName : " + username);		
	}
	
	public void clearAndenterUsername(String username) {
		//clear()
		enterUsername(username)	;	
	}
	
	public void enterPassword(String password) {		
		//driver.findElement(passwordText).sendKeys(password);
		sendKeysToElement(driver, passwordText, "SuperSecret");
		//Actions action = new Actions(driver);		
		//action.moveToElement(driver.findElement(signInButton)).sendKeys(Keys.TAB).perform();
		
		ReportUtils.getLog().info("Enter Password : *********");
	}
	
	public void clickSignIn() {
		
		//driver.findElement(signInButton).click();
		clickElement(driver, signInButton);						
		ReportUtils.getLog().info("Clicked on Sign In Link");
	}
	
   

}
