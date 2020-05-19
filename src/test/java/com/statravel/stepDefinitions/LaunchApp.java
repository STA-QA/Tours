package com.statravel.stepDefinitions;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.Test;

import com.statravel.base.BaseUtil;
import cucumber.api.java.en.Given;

public class LaunchApp extends BaseUtil {
	
	@Test(description ="user_launches_uk_tours_search_page")
	@Given("^User launches UK tours search page$")
	public void user_launches_uk_tours_search_page() throws Throwable {
		System.out.println("App Launch");
		driver.navigate().to("https://tours.statravel.co.uk/search/");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		/*
		 * Properties prop = new Properties(); 
		 * FileInputStream input = new FileInputStream("URL.properties"); prop.load(input);
		 */
		
	}
}