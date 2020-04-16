package com.statravel.stepDefinitions;

import com.statravel.base.BaseUtil;
import com.statravel.codeImplementation.HomePage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class FiltersDefinitions extends BaseUtil{
	HomePage Home = new HomePage(driver);
	
    @And("^User Slides the Duration for Minimum range$")
    public void user_slides_the_duration_for_minimum_range() throws Throwable {
        Home.MoveDurationSliderToRight();
    }

    @And("^User Slides the Duration for Maximum range$")
    public void user_slides_the_duration_for_maximum_range() throws Throwable {
        Home.MoveDurationSliderToLeft();
    }
    
    @Then("^Lowest Duration should be adjusted$")
    public void lowest_duration_should_be_adjusted() throws Throwable {
        Home.VerifyLowestDurationAccordingToDurationFilters();
    }

    @Then("^Highest Duration should be adjusted$")
    public void highest_duration_should_be_adjusted() throws Throwable {
        Home.VerifyHighestPriceAccordingToDurationFilters();
    }
    
    @And("^User Slides the Budget for Minimum range$")
    public void user_slides_the_budget_for_minimum_range() throws Throwable {
        Home.MoveBudgetSliderToRight();
    }

    @And("^User Slides the Budget for Maximum range$")
    public void user_slides_the_budget_for_maximum_range() throws Throwable {
        Home.MoveBudgetSliderToLeft();
    }
    
    @Then("^Lowest price tour should be adjusted$")
    public void lowest_price_tour_should_be_adjusted() throws Throwable {
        Home.VerifyLowestPriceAccordingToPriceFilters();
    }

    @Then("^Highest price tour should be adjusted$")
    public void highest_price_tour_should_be_adjusted() throws Throwable {
     Home.VerifyHighestPriceAccordingToPriceFilters();   
    }

}
