package manager;

import org.openqa.selenium.WebDriver;

import utils.ReportUtils;

public class DriverManager {
	
	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	private DriverManager() {

	}

	public static WebDriver getDriver() {

		return driver.get();
	}

	public static void setDriver(WebDriver driverInstance) {

		driver.set(driverInstance);
	}

	public static void quitDriver() {

		if (getDriver() != null) {
			getDriver().quit();
			driver.remove();
			ReportUtils.getLog().info("Browser Closed ! Hurray !!");
		}
	}

}
