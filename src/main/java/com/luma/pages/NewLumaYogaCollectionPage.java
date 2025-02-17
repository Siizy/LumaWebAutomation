package com.luma.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.ReportUtils;

public class NewLumaYogaCollectionPage extends BasePage {

	public NewLumaYogaCollectionPage(WebDriver driver) {

		super(driver);		
		
	}

   // String color;

	private By colorFilterDropDown = By.xpath("//div[@class='filter-options']//div[text()='Color']");
	//private By blueColorFilter = By.xpath("//div[@class='filter-options']//div[@option-label='" + this.color + "']");
	private By blueColorFilter = By.xpath("//div[@class='filter-options']//div[@option-label='Blue']");
	private By selectedItemColor = By.xpath("//div[@class='swatch-option color selected']");

	public void selectBlueColorFilter(String color) {
		//this.color = color;
		driver.findElement(colorFilterDropDown).click();
		driver.findElement(blueColorFilter).click();
		ReportUtils.getLog().info("Expanded Color Filter from the left panel and select Blue");
	}

	public boolean isAllSelectedItemColorBlue(String color) {
		//this.color = color;
		
		List<WebElement> selectedElements = driver.findElements(selectedItemColor);
		
		for (WebElement ele : selectedElements) {
			String colour = ele.getDomAttribute("option-label");

			if (!colour.equals("Blue")) {
				ReportUtils.getLog().info("All the listed items are not blue");
				return false;
			}
		}
		ReportUtils.getLog().info("All the listed items are Blue");

		return true;
	}
}
