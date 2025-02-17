package com.luma.pagetests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.MediaEntityBuilder;

import manager.DriverManager;
import utils.BaseUtils;
import utils.DBUtils;
import utils.ReportUtils;
import utils.TestDataHolder;

public class BaseTest {

	@BeforeSuite
	public void init() throws SQLException {

		ReportUtils.initReport();
	}

	@BeforeMethod
	protected void setUp(Method method) throws InterruptedException, IOException, SQLException {
		DBUtils.establishConnection();
		TestDataHolder.setTestData(getDataMap(method));
		ReportUtils.createTest(method.getName());
		String browser = BaseUtils.getConfigValue("browser");

		switch (browser.toLowerCase()) {

		case "chrome":

			DriverManager.setDriver(new ChromeDriver());
			ReportUtils.getLog().info("Browser launched : Chrome");
			break;

		case "firefox":

			DriverManager.setDriver(new FirefoxDriver());
			ReportUtils.getLog().info("Browser launched : Firefox");
			break;

		case "safari":

			DriverManager.setDriver(new SafariDriver());
			ReportUtils.getLog().info("Browser launched : Safari");
			break;

		case "edge":

			DriverManager.setDriver(new EdgeDriver());
			ReportUtils.getLog().info("Browser launched : Edge");
			break;

		default:
			System.out.println("No driver found");
			ReportUtils.getLog().fail("No Browser was launched ! Test Failed");
			break;
		}

		DriverManager.getDriver().manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.valueOf(BaseUtils.getConfigValue("implicitwait"))));

		String url = BaseUtils.getConfigValue("url");
		DriverManager.getDriver().get(url);
		ReportUtils.getLog().info("Url Launched : " + url);

	}

	@AfterMethod
	protected void end(ITestResult result) throws IOException, SQLException {

		if (result.getStatus() == ITestResult.FAILURE) {
			ReportUtils.getLog().fail(result.getThrowable(), MediaEntityBuilder
					.createScreenCaptureFromPath(BaseUtils.getScreenShotPath(DriverManager.getDriver(),
							result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName()))
					.build());
		}

		DriverManager.quitDriver();
		ReportUtils.removeTest();
		TestDataHolder.clear();
		DBUtils.closeConnection();

	}

	@AfterSuite
	protected void tearDown() throws SQLException {

		ReportUtils.generateReport();
	}

	public synchronized Map<String, String> getDataMap(Method method) throws SQLException {

		String testCases = DBUtils.getTestDataForMethod(method.getName());

		if (testCases == null || testCases.isEmpty()) {
			throw new SkipException("No Data is available for this testcase : " + method.getName());
		}

		String[] keyValuePairs = testCases.split("\\|");

		Map<String, String> testDataMap = new HashMap<>();

		for (String pair : keyValuePairs) {
			String[] entry = pair.split(":");

			if (entry.length == 2 && !entry[0].trim().isEmpty() && !entry[1].trim().isEmpty()) {
				testDataMap.put(entry[0].trim(), entry[1].trim());
			} else {
				throw new SkipException("Invalid test data format. Expected 'key:value', but found: " + pair);
			}
		}

		return testDataMap;
	}
}
