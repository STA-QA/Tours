package com.statravel.stepDefinitions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.statravel.base.BaseUtil;
import com.statravel.codeImplementation.HomePage;
import com.statravel.codeImplementation.TourDetailsPage;
import com.statravel.gaApi.util.GAService;
import com.statravel.gaApi.util.GaUtil;
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
		// tour.setId(99);
		// tour.setName("The explorer");
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
		System.out.println("---------- Click on tour and Verify it on TourDetails page with Name: "
				+ randomTourToVerify.getFormattedName());
		Home.clickOnTour(randomTourToVerify);
	}

	@Then("^Tour details should display correctly$")
	public void tour_details_should_display_correctly() {
		System.out.println("---------- URL: " + driver.getCurrentUrl());
		new TourDetailsPage(driver).verifyTourDetails(randomTourToVerify);
	}

	@And("^User search tour by name (.+)$")
	public void user_search_tour_by_name(String name) {
		System.out.println("---------- Input tour Name to search: " + name);
		Home.InputAndSearch(name);
	}

	@And("^Get all available tours from API brand (.+) and region (.+)$")
	public void get_all_available_tours_from_api(Brand brand, Region region) {
		List<CheapestTour> toursFromUI = Home.readAllToursFromUI();
		toursFromUI.stream().forEach(it -> System.out.println(
				"[TourIdUI] " + it.getName() + ", " + it.getDeparture().getOperatingStartDate() + ", "
						+ it.getDiscountedPrice()));
		TourService service = new TourService(brand, region);
		List<CheapestTour> toursFromApi = service.getAllAvailableCheapestToursFromResponse();
		SoftAssert softAssertion = new SoftAssert();

		// try to find match toursFromApi with toursFromUI
		for (CheapestTour tApi : toursFromApi) {
			int count = -1;
			System.out.println(
					"------- Verify " + tApi.getContent().getName().replaceAll(" +", " ").replaceAll("&", "and") + " | "
							+ tApi.getFormattedName() + " | " + tApi.getName());
			Optional<CheapestTour> tUiOpt = toursFromUI.stream().filter(
					t -> t.getName().equals(tApi.getContent().getName().replaceAll(" +", " ").replaceAll("&", "and")))
					.findFirst();
			count = (int) toursFromUI.stream().filter(
					t -> t.getName().equals(tApi.getContent().getName().replaceAll(" +", " ").replaceAll("&", "and")))
					.count();

			if (!tUiOpt.isPresent()) {
				count = (int) toursFromUI.stream().filter(t -> t.getName().equals(tApi.getFormattedName())).count();
				if (count == 1) {
					//System.out.println("------Found with formattedName");
					tUiOpt = toursFromUI.stream().filter(t -> t.getName().equals(tApi.getFormattedName())).findFirst();
				} else if (count == 0) {
					//System.out.println("------Try Found with Name and date");
					tUiOpt = toursFromUI.stream()
							.filter(t -> (t.getName().contains(tApi.getName().replaceAll("&", "and")))).findFirst();// contains
					// && t.getFormattedStartDate().equals(tApi.getStartDate()))
					count = (int) toursFromUI.stream()
							.filter(t -> (t.getName().contains(tApi.getName().replaceAll("&", "and")))).count();// contains
					if (count != 1) {
						//System.out.println("----- Failed count =" + count);
					}
				}
			}
			if (tUiOpt.isPresent() && count == 1) {
				CheapestTour tUi = tUiOpt.get();
				// delete toursFromUI tours from ui
				/* toursFromUI.remove(tUi); */
				boolean ifPassed = true;
				try {
					Assert.assertEquals(Math.round(tApi.getDiscountedPrice()), Math.round(tUi.getDiscountedPrice()),
							"Price verification failed " + tUi.getName());
				} catch (AssertionError e) {
					ifPassed = false;
					System.out.println("--------- Price verification failed  " + tUi.getName() + "Exp on Api  "
							+ Math.round(tApi.getDiscountedPrice()) + ", but found on UI "
							+ Math.round(tUi.getDiscountedPrice()));
				}
				try {
					Assert.assertEquals(tApi.getStartDate(), tUi.getFormattedStartDate(),
							"StartDate verification failed " + tUi.getName());
				} catch (AssertionError e) {
					ifPassed = false;
					System.out.println("--------- StartDate verification failed  " + tUi.getName() + "Exp on Api  "
							+ tApi.getStartDate() + ", but found on UI " + tUi.getFormattedStartDate());
				}
				if (ifPassed) {
					System.out.println("--------- Verification PASSED " + tUi.getName());
				}
			} else {
				System.out.println("Tour not found on UI, count=" + count + " with tourApiName " + tApi.getName()
						+ " | " + tApi.getFormattedName());
				softAssertion.fail("Tour not found on UI, count=" + count + " with tourApiName " + tApi.getName()
						+ " | " + tApi.getFormattedName());
			}
		}
		// ToursFromUI left after deleting
		/*
		 * System.out.println("---- ToursFromUI not found in tours from Api");
		 * toursFromUI.stream().forEach(it -> System.out.println("[TourIdUI ] " +
		 * it.getName() + ", " + it.getDeparture().getOperatingStartDate() + ", " +
		 * it.getDiscountedPrice()));
		 */

		softAssertion.assertAll();
	}

	@And("^Get all departures from API brand (.+) and region (.+)$")
	public void get_all_departures_from_api(Brand brand, Region region) {
		TourService service = new TourService(brand, region);
		service.getAllDeparturesFromResponse();
	}

	@And("^Get all cheapest departures from API brand (.+) and region (.+)$")
	public void get_all_available_departures_from_api(Brand brand, Region region) {
		TourService service = new TourService(brand, region);
		service.getAllCheapestDeparturesFromResponse();
	}

}
