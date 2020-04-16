package com.statravel.runners;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.github.mkolisnyk.cucumber.reporting.CucumberResultsOverview;
import com.statravel.base.Functions;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/java/com/statravel/featureFiles/PriceFilter.feature", plugin = {
		"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:", "rerun:target/rerun.txt",
		"json:target/cucumber-reports/cucumber.json" }, glue = "com.statravel.stepDefinitions")
public class TestRunner extends AbstractTestNGCucumberTests {

	public static String timeStamp = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss")
			.format(Calendar.getInstance().getTime());

	@BeforeSuite
	public static void setup() {

		Functions commonfunctions = new Functions();
		commonfunctions.reportfunction();
	}

	@AfterSuite(alwaysRun = true)
	public void setupp() {

		CucumberResultsOverview results = new CucumberResultsOverview();
		results.setOutputDirectory("target");
		results.setOutputName("cucumber-results");
		results.setSourceFile("target/cucumber-reports/cucumber.json");
		try {
			results.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

