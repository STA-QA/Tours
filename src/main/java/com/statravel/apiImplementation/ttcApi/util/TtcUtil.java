package com.statravel.apiImplementation.ttcApi.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.statravel.apiImplementation.ttcApi.pojo.TourDetailsResponse;

public class TtcUtil {

	// NB get one SellingRegion in array and prices only for specified region
	private static final String GET_TOUR_DETAILS = "https://api.ttc.com/brands/%s/tours/%s?regions=%s";
	private static final String GET_TOURS = "https://api.ttc.com/brands/%s?regions=%s";

	public enum Brand {
		contiki
	}

	public enum Region {
		uk
	}

	private static String callTtc(String uri) {
		ClientConfig clientConfig = new ClientConfig();

		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("token",
				"6a11d300-e114-451a-87bc-3d9317850ffb");
		clientConfig.register(feature);

		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target(uri);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		String rs = response.readEntity(String.class);
		// System.out.println("-------- Response from TtcApi: " + rs);
		if (response.getStatus() != 200) {
			System.out.println("---- Error response from Api " + uri + "\n" + String.valueOf(response.getStatus())
					+ "\n" + response.getStatusInfo() + "\n" + rs);
			return null;
		}
		return rs;
	}

	// NB get one SellingRegion in array and prices only for specified region
	public static TourDetailsResponse getTourDetails(Brand brand, Region region, String idTour) {
		String rs = callTtc(String.format(GET_TOUR_DETAILS, brand, idTour, region));
		if (rs != null) {
			TourDetailsResponse tourResponse = new Gson().fromJson(rs, TourDetailsResponse.class);
			tourResponse.setRegion(region);
			tourResponse.setBrand(brand);
			return tourResponse;
		} else
			return null;

	}

	public static ArrayList<TourDetailsResponse> getTour(Brand brand, Region region) {
		Type collectionType = new TypeToken<ArrayList<TourDetailsResponse>>() {
		}.getType();
		ArrayList<TourDetailsResponse> tourResponse = new Gson()
				.fromJson(callTtc(String.format(GET_TOURS, brand, region)), collectionType);
		tourResponse.stream().forEach(t -> {
			t.setBrand(brand);
			t.setRegion(region);
		});
		return tourResponse;
	}

	public static TourDetailsResponse getRandomTourFromResponse(Brand brand, Region region) {
		ArrayList<TourDetailsResponse> tourResponse = getTour(brand, region);
		return tourResponse.get(new Random().nextInt(tourResponse.size()));
	}

}
