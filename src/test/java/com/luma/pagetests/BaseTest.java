package com.luma.pagetests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.MediaEntityBuilder;

import utils.BaseUtils;
import utils.ReportUtils;

public class BaseTest {

	protected WebDriver driver;
	
	
	@BeforeSuite
	public void init() {
		ReportUtils.initReport();
	}

	@BeforeMethod
	protected void launchSite(Method method) throws InterruptedException, IOException {
		
		ReportUtils.createTest(method.getName());

		String browser = BaseUtils.getConfigValue("browser");

		switch (browser.toLowerCase()) {

		case "chrome":
			driver = new ChromeDriver();
			ReportUtils.log.info("Browser launched : Chrome");
			break;

		case "firefox":
			driver = new FirefoxDriver();
			ReportUtils.log.info("Browser launched : Firefox");
			break;

		case "safari":
			driver = new SafariDriver();
			ReportUtils.log.info("Browser launched : Safari");
			break;

		case "edge":
			driver = new EdgeDriver();
			ReportUtils.log.info("Browser launched : Edge");
			break;

		default:
			System.out.println("No driver found");
			ReportUtils.log.fail("No Browser was launched ! Test Failed");
			break;
		}

		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.valueOf(BaseUtils.getConfigValue("implicitwait"))));
		
		String url = BaseUtils.getConfigValue("url");
		driver.get(url);
		ReportUtils.log.info("Url Launched : " + url);

	}

	@AfterMethod
	protected void end(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			ReportUtils.log.fail(result.getThrowable(),
					MediaEntityBuilder.createScreenCaptureFromPath(BaseUtils.getScreenShotPath(driver,
							result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName()))
							.build());
		}

		driver.quit();
		ReportUtils.log.info("Browser Closed");
	}
	
	@AfterSuite
	protected void tearDown() {
		ReportUtils.generateReport();
	}
}
