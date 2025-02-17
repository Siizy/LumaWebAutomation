package com.luma.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	WebDriver driver;
	WebDriverWait wait;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public String getPageTitle() {

		return driver.getTitle();
	}
	
	public void clickElement(WebDriver driver, By locator) {
		//wait = new WebDriverWait(driver, Duration.ofSeconds(10));		
		WebElement enabledText = wait.until(ExpectedConditions.elementToBeClickable(locator));
		enabledText.click();
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
	}
	
	public void sendKeysToElement(WebDriver driver, By locator, String value) {
		//wait = new WebDriverWait(driver, Duration.ofSeconds(10));		
		WebElement enabledText = wait.until(ExpectedConditions.elementToBeClickable(locator));
		enabledText.sendKeys(value);
	}

}
