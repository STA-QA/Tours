package com.statravel.stepDefinitions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
public class Hook extends BaseUtil {
	 static ExtentReports extentReports;
	 static ExtentHtmlReporter htmlReporter;
	 static ITestResult iTestResult;

	// private static WebDriver driver;
	
	@Before
	public void initializeTest(Scenario scenario) {
	//	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/WebDrivers/chromedriver.exe");
		//WebDriverManager.chromedriver().setup();
		//System.setProperty("wdm.proxy", "http://genproxy.corp.amdocs.com:8080");
		
		DriverManagerType chrome = DriverManagerType.CHROME;
    	ChromeDriverManager.getInstance(chrome).setup();
    ChromeOptions options = new ChromeOptions();
	options.setExperimentalOption("useAutomationExtension", false);
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		//options.addArguments("start-maximized");
		//options.addArguments("--headless")
		driver = new ChromeDriver(options);
		/*
		 * WebDriver driver = new ChromeDriver(); driver.manage().window().maximize();
		 */
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
System.out.println("opened driver");
	}

	@After
	public void tearDownTest(Scenario scenario) throws IOException{

		if (scenario.getStatus().equals(Result.FAILED)) {

			String screenShotPath = Functions.screenshot(driver, System.currentTimeMillis());
			Reporter.addScreenCaptureFromPath(screenShotPath);
			System.out.println(scenario.getName());

		}
		
	
		
		driver.quit();

	}

}
