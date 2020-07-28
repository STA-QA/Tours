package com.statravel.stepDefinitions.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.statravel.apiImplementation.gaApi.pojo.departure.Departure;
import com.statravel.apiImplementation.gaApi.pojo.dossier.TourDossierResponse;
import com.statravel.apiImplementation.gaApi.pojo.dossier.TourDossierResultsResponse;
import com.statravel.apiImplementation.gaApi.util.GAService;
import com.statravel.apiImplementation.ttcApi.util.TourService;
import com.statravel.apiImplementation.ttcApi.util.TtcUtil.Region;

import cucumber.api.java.en.And;

public class TourApiDefinitions {

	@And("^Get all departures from Contiki API region (.+)$")
	public void get_all_departures_from_contiki_api(Region region) {
		TourService service = new TourService(region);
		List<String> tourList = service.getAllDeparturesFromResponse();
		service.writeToCsv("allToursDeparturesContiki", tourList);
	}

	@And("^Get all cheapest departures from Contiki API region (.+)$")
	public void get_all_available_departures_from_contiki_api(Region region) {
		TourService service = new TourService(region);
		List<String> tourList = service.getAllCheapestDeparturesFromResponse();
		service.writeToCsv("allCheapestToursDeparturesContiki", tourList);
	}

	@And("^Get GA all departures from API from page (.+) to page (.+)$")
	public void get_all_departures_ga(String pageStart, String pageEnd) {
		Map<TourDossierResponse, List<Departure>> actualDepartures = GAService.getAllDeparturesFromResponse(pageStart,
				pageEnd);
		GAService.writeToCsv("allToursDeparturesGA_Pages" + pageStart + "-" + pageEnd, actualDepartures);
	}

	@And("^Get GA all departures from API for tours (.+)$")
	public void get_all_departures_ga_for_tours(String tourIds) {
		// tourIds = tourId or productLine
		Map<TourDossierResponse, List<Departure>> actualDepartures = new HashMap<TourDossierResponse, List<Departure>>();
		if (tourIds.matches(".*[a-zA-Z].*")) {
			List<String> listIds = new ArrayList<String>();
			for (String prod : tourIds.split(",")) {
				TourDossierResultsResponse tour = GAService.getTourByProductLine(prod);
				Assert.assertTrue(tour.getResults().size() > 0, "Tour with productLine=" + prod + " not found");
				if (tour.getResults().stream().filter(r -> r.getProductLine().equalsIgnoreCase(prod)).count() != 1) {
					System.out.println("----- Tour with productLine=" + prod + " not found");
				}
				tour.getResults().stream().filter(r -> r.getProductLine().equalsIgnoreCase(prod))
						.forEach(r -> listIds.add(r.getId()));
				actualDepartures = GAService.getAllDeparturesFromResponse(listIds);
			}
		} else {
			Assert.assertNotNull(tourIds, "Please set TourId in parameters");
			List<String> listIds = Arrays.asList(tourIds.split(","));
			actualDepartures = GAService.getAllDeparturesFromResponse(listIds);
		}
		GAService.writeToCsv("toursDeparturesGA_" + tourIds, actualDepartures);

	}
}
