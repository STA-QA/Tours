package com.statravel.stepDefinitions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;
import org.openqa.selenium.PageLoadStrategy;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
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
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import org.openqa.selenium.WebDriver;

public class Hook extends BaseUtil {
	 static ExtentReports extentReports;
	 static ExtentHtmlReporter htmlReporter;
	 static ITestResult iTestResult;

 private static WebDriver driver;
	
	@Before
	public void initializeTest(Scenario scenario) {
	   Capabilities cap = DesiredCapabilities.chrome(); // no need path of chrome exe
            cap.setBrowserName("chrome");
            cap.setPlatform(Platform.LINUX);
	   Capabilities chromeCapabilities = DesiredCapabilities.chrome();
		try {
		 driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver error");
			e.printStackTrace();
		}
	//	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/WebDrivers/chromedriver.exe");
		
	//	System.setProperty("wdm.proxy", "http://genproxy.corp.amdocs.com:8080");
     //  WebDriverManager.chromedriver().config().setProperties("webdrivermanager.properties");
  /*  	DriverManagerType chrome = DriverManagerType.CHROME;
    	ChromeDriverManager.getInstance(chrome).setup();
    	ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
	//	options.setBinary("/./bin/google-chrome");
	//	options.addArguments("start-maximized");
		//options.addArguments("--headless")
		driver = new ChromeDriver(options);*/
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

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
