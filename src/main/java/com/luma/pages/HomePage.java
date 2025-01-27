package com.luma.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ReportUtils;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver){
		super(driver);
	}
	
	private By singInLink = By.xpath("//div[@class='panel header']//a[contains(.,'Sign In')]");	
	private By shopNewYogaLink = By.xpath("//span[text()='Shop New Yoga']");
	
	public void clickSignin() {
		driver.findElement(singInLink).click();
		ReportUtils.log.info("Clicked Sign In");
	}
	
	public void clickShopNewYoga() {
		driver.findElement(shopNewYogaLink).click();
        ReportUtils.log.info("Clicked Shop New Yoga link");
	}

}
