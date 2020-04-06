package com.statravel.stepDefinitions;

import com.statravel.base.BaseUtil;
import com.statravel.codeImplementation.HomePage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class TestStepDefinitions extends BaseUtil{

HomePage home = new HomePage(driver);
	    @Given("^User launches facebook facebook application$")
	    public void user_launches_facebook_facebook_application() throws Throwable {
	        driver.navigate().to("https://dev-tours.statravel.com/search/");
	        Thread.sleep(8000);
	    }

	    @Then("^Click on Login$")
	    public void click_on_login() throws Throwable {
	    
	        home.ClickGridViewIcon();
	    }

	    @And("^Enters username and password$")
	    public void enters_username_and_password() throws Throwable {
	        
	    }

	
	
}
