package com.statravel.stepDefinitions;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.statravel.base.BaseUtil;

import cucumber.api.java.en.Given;

public class LaunchApp extends BaseUtil {

	@Test(description = "user_launches_uk_tours_search_page")
	@Given("^User launches UK tours search page$")
	public void user_launches_uk_tours_search_page() throws Throwable {
		System.out.println("App Launch");
		driver.navigate().to("https://tours.statravel.co.uk/search/");
		driver.manage().window().maximize();
		Thread.sleep(5000);

		if (driver.findElements(By.id("sta-cookie-save-all-button")).size() > 0) {
			driver.findElement(By.id("sta-cookie-save-all-button")).click();
		}

		/*
		 * Properties prop = new Properties(); FileInputStream input = new
		 * FileInputStream("URL.properties"); prop.load(input);
		 */

	}

}