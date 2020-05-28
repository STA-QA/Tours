package com.statravel.stepDefinitions;

import java.util.List;
import java.util.Random;

import org.testng.asserts.SoftAssert;

import com.statravel.base.BaseUtil;
import com.statravel.codeImplementation.HomePage;
import com.statravel.codeImplementation.TourDetailsPage;
import com.statravel.ttcApi.pojo.TourDetailsResponse;
import com.statravel.ttcApi.util.CheapestTour;
import com.statravel.ttcApi.util.TourService;
import com.statravel.ttcApi.util.TtcUtil;
import com.statravel.ttcApi.util.TtcUtil.Brand;
import com.statravel.ttcApi.util.TtcUtil.Region;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class TourResultsDefinitions extends BaseUtil {

	HomePage Home = new HomePage(driver);

	private TourDetailsResponse tour;

	private CheapestTour randomTourToVerify;

	@And("^User search random tour name from API brand (.+) and region (.+)$")
	public void user_search_random_tour_name_from_api(Brand brand, Region region) {
		tour = TtcUtil.getRandomTourFromResponse(brand, region);
		System.out.println("---------- Input tour Name to search: " + tour.getName());
		Home.InputAndSearch(tour.getName());
	}

	@Then("^Tours should display correctly")
	public void tours_should_display_correctly() {
		TourService service = new TourService(tour);
		List<CheapestTour> listTour = service.getCheapestToursFromJsonRS();
		SoftAssert softAssertion = new SoftAssert();
		softAssertion.assertTrue(Home.getName().size() >= listTour.size(), "Amount of tours");
		softAssertion.assertAll();
		listTour.stream().forEach(c -> Home.verifyTour(c));
		// verify tour details
		randomTourToVerify = listTour.get(new Random().nextInt(listTour.size()));
	}

	@And("^User click on random tour to open details$")
	public void user_click_on_tour() {
		System.out
				.println("---------- Click on tour and Verify it on TourDetails page with Name: " + randomTourToVerify.getFormattedName());
		Home.clickOnTour(randomTourToVerify);
	}

	@Then("^Tour details should display correctly$")
	public void tour_details_should_display_correctly() {
		System.out.println("---------- URL: " + driver.getCurrentUrl());
		new TourDetailsPage(driver).verifyTourDetails(randomTourToVerify);
	}
}
