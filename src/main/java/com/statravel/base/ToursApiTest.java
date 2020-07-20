package com.statravel.base;

import org.testng.annotations.Test;

import com.statravel.gaApi.util.GAService;
import com.statravel.newtours.util.NewToursService;
import com.statravel.ttcApi.util.TourService;
import com.statravel.ttcApi.util.TtcUtil.Brand;
import com.statravel.ttcApi.util.TtcUtil.Region;

public class ToursApiTest  {
	
	@Test
	public void getAllDeparturesGA() {
		GAService gaService = new GAService();
		gaService.getAllDeparturesFromResponse();
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
		 NewToursService.compareTour("");
	}
}
