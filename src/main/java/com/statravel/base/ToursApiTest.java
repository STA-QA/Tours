package com.statravel.base;

import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.statravel.gaApi.pojo.dossier.TourDossier;
import com.statravel.gaApi.util.GAService;
import com.statravel.newtours.util.NewToursService;
import com.statravel.ttcApi.util.TourService;
import com.statravel.ttcApi.util.TtcUtil.Brand;
import com.statravel.ttcApi.util.TtcUtil.Region;

public class ToursApiTest {

	@Test
	@Parameters({ "pageStart", "pageEnd" })
	public void getAllDeparturesGA(@Optional("1") String pageStart, @Optional("1") String pageEnd) {
		GAService.getAllDeparturesFromResponse(pageStart, pageEnd);
	}

	@Test
	@Parameters({ "tourId", "productLine" })
	public void getAllDeparturesGAByTourId(@Optional String tourId, @Optional String productLine) {
		if (productLine != null) {
			TourDossier tour = GAService.getTourByProductLine(productLine);
			Assert.assertTrue(tour.getResults().size() > 0, "Tour with productLine=" + productLine + " not found");
			tourId = tour.getResults().get(0).getId();
		}
		Assert.assertNotNull(tourId, "TourId not found");
		GAService.getAllDeparturesFromRespons(tourId);
	}

	@Test
	public void getAllCheapestDeparturesContiki() {
		TourService service = new TourService(Brand.contiki, Region.uk);
		service.getAllCheapestDeparturesFromResponse();
	}

	@Test
	public void getAllDeparturesContiki() {
		TourService service = new TourService(Brand.contiki, Region.uk);
		service.getAllDeparturesFromResponse();
	}

	@Test
	public void gaTour() {
		NewToursService.compareGATour("24781");
	}
}
