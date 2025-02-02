package com.statravel.stepDefinitions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;
//import org.openqa.selenium.PageLoadStrategy;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.statravel.base.BaseUtil;
import com.statravel.base.Functions;
import com.vimalselvam.cucumber.listener.Reporter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import gherkin.formatter.model.Result;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

public class Hook extends BaseUtil {
	static ExtentReports extentReports;
	static ExtentHtmlReporter htmlReporter;
	static ITestResult iTestResult;

	@Before
	public void initializeTest(Scenario scenario) {
		// To run the tests in local

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/WebDrivers/chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("start-maximized");
		// options.addArguments("----headless");
		driver = new ChromeDriver(options);

		// To run tests in gitlab

		/*Capabilities cap = DesiredCapabilities.chrome();
		try {
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		} catch (MalformedURLException e) {
			System.out.println("Driver error");
			e.printStackTrace();
		}*/

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

	}

	@After
	public void tearDownTest(Scenario scenario) throws IOException {

		if (scenario.getStatus().equals(Result.FAILED)) {

			String screenShotPath = Functions.screenshot(driver, System.currentTimeMillis());
			Reporter.addScreenCaptureFromPath(screenShotPath);
			System.out.println(scenario.getName());

		}

		driver.quit();

	}

}
