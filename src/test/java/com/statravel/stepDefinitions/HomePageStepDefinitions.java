package com.statravel.stepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;


import com.statravel.base.BaseUtil;
import com.statravel.codeImplementation.HomePage;


public class HomePageStepDefinitions extends BaseUtil {

	HomePage Home = new HomePage(driver);

	@And("^User selects (.+)$")
	public void user_selects(String SortOption) throws Throwable {
		System.out.println("Sort Order Test For : " + SortOption);
		Home.SelectSortOption(SortOption);
	}

	@Then("^Verify the tours are displayed by (.+)$")
	public void verify_the_tours_are_displayed_by(String Sortorder) throws Throwable {
		Home.VerifySortOrderDisplay(Sortorder);
	}

}