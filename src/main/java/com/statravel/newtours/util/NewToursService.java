package com.statravel.newtours.util;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.testng.asserts.SoftAssert;
import org.testng.log4testng.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvocationType;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.google.gson.Gson;
import com.statravel.base.ToursApiTest;
import com.statravel.gaApi.pojo.CurrencyCode;
import com.statravel.gaApi.pojo.departure.Departure;
import com.statravel.gaApi.pojo.departure.DeparturesResponse;
import com.statravel.gaApi.pojo.departureDetails.DepartureDetailsResponse;
import com.statravel.gaApi.pojo.departureDetails.PriceBand;
import com.statravel.gaApi.pojo.departureDetails.Room;
import com.statravel.gaApi.util.GaUtil;
import com.statravel.newtours.pojo.NewTour;
import com.statravel.newtours.pojo.TourSummaryResponse;

public class NewToursService {
	
	public static void compareTour(String tourId) {
		
		List<Departure> departures = GaUtil.getDeparturesOfTheTour(tourId);
		List<NewTour> tours = TourSummaryUtil.getNewTours();

		SoftAssert softAssertion = new SoftAssert();

		for (Departure deparure : departures) {
			System.out.println("--------- Verification of departures "+ deparure.getId());
			int count = (int) tours.stream().filter(t -> t.getDepartureId().equals(deparure.getId())).count();
			if ("AVAILABLE".equals(deparure.getAvailability().getStatus())) {
				softAssertion.assertEquals(count, 1, "DepartureId not found " + deparure.getId());
			} else {
				softAssertion.assertEquals(count, 0, "DepartureId  found " + deparure.getId());
			}
			if (count > 0) {// ==1
				DepartureDetailsResponse details = GaUtil.getDepartureDetails(deparure.getHref());
				Room room =details.getRooms().get(0);
				PriceBand priceBand= room.getPriceBands().get(0);
				//TODO roomsCount>1?
				System.out.println("------ RoomsCount="+ details.getRooms().size());
				System.out.println("------ Room0_PriceBands="+ room.getPriceBands().size());
				NewTour tour = tours.stream().filter(t -> t.getDepartureId().equals(deparure.getId())).findFirst()
						.get();
				softAssertion.assertEquals(tour.getSalePrice(), (int) deparure.getPrice(CurrencyCode.GBP), "Price verification failed ");
				softAssertion.assertEquals(LocalDate.parse(tour.getStartDate(), DateTimeFormatter.ISO_DATE_TIME),
						deparure.getStartLocalDate(), "StartDate ");
				// "startDate": "2020-10-11T00:00:00.000Z",
				if(deparure.getAvailability().getTotal()<7) {
					softAssertion.assertEquals(tour.getAvailability().toLowerCase(), "SellingFast",
							"Availability ");
				}else {
				softAssertion.assertEquals(tour.getAvailability().toLowerCase(), deparure.getAvailability().getStatus().toLowerCase(),
						"Availability ");
				}
				//softAssertion.assertEquals(tour.getRoomType(), room.getPriceBands().get(0).getPrice(CurrencyCode.GBP), "Room ");
				softAssertion.assertEquals(tour.getRoomType(), room.getName(), "RoomName");
				softAssertion.assertEquals(tour.getRoomCode(), room.getCode(), "RoomCode ");
				softAssertion.assertEquals(tour.getAgeGrade(), priceBand.getName(), "AgeGrade");
				softAssertion.assertEquals(tour.getMinAge(),priceBand.getMinAge(), "MinAge ");
				softAssertion.assertEquals(tour.getMaxAge(), priceBand.getMaxAge(), "MaxAge ");
			}
		}
		//TODO AssertUtil, try/catch testng assert
		softAssertion.assertAll();
	}

	public static void main(String[] args) {
		 NewToursService.compareTour("24781");
		 
	}

}
