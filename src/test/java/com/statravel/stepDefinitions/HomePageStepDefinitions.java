package com.statravel.stepDefinitions;

//import cucumber.api.PendingException;
//import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

import com.statravel.base.BaseUtil;
import com.statravel.codeImplementation.HomePage;

@RunWith(Cucumber.class)
public class HomePageStepDefinitions extends BaseUtil{

	HomePage Home = new HomePage(driver);
	
	@And ("^User selects (.+)$")
    public void user_selects(String SortOption) throws Throwable {
    	System.out.println("Sort Order Test For : "+SortOption);
		Thread.sleep(2000);
		Home.SelectSortOption(SortOption);
		Thread.sleep(5000);
    }

    @Then("^Verify the tours are displayed by (.+)$")
    public void verify_the_tours_are_displayed_by_lowest_price() throws Throwable {
            }

  }