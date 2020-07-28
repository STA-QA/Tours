package com.statravel.apiImplementation.gaApi.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.google.gson.Gson;
import com.statravel.apiImplementation.gaApi.pojo.departure.Departure;
import com.statravel.apiImplementation.gaApi.pojo.departure.DeparturesResponse;
import com.statravel.apiImplementation.gaApi.pojo.departureDetails.DepartureDetailsResponse;
import com.statravel.apiImplementation.gaApi.pojo.dossier.Result;
import com.statravel.apiImplementation.gaApi.pojo.dossier.TourDossierResponse;
import com.statravel.apiImplementation.gaApi.pojo.dossier.TourDossierResultsResponse;
import com.statravel.apiImplementation.gaApi.pojo.itineraries.ItinerariesResponse;

public class GaUtil {

	private static final String GET_TOUR_BY_PRODUCTLINE = "https://rest.gadventures.com/tour_dossiers/?product_line=%s";
	private static final String GET_DEPARTURES = "https://rest.gadventures.com/tour_dossiers/%s/departures%s";
	private static final String GET_TOURS = "https://rest.gadventures.com/tour_dossiers/%s";

	public static String callGaApi(String uri) {
		ClientConfig clientConfig = new ClientConfig();

		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target(uri);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header("X-Application-Key",
				"live_02c02f47d73f9bed8c9adcfb133ab452b9203384");
		Response response = invocationBuilder.get();

		String rs = response.readEntity(String.class);
		System.out.println("-------- RQ GAApi: " + uri);
		// System.out.println("-------- Response from GAApi: " + rs);
		if (response.getStatus() != 200) {
			System.out.println("Error response from Api " + String.valueOf(response.getStatus()) + "\n"
					+ response.getStatusInfo() + "\n" + rs);
			return null;
		}
		return rs;
	}

	public static List<Departure> getDeparturesOfTheTour(String tourId) {

		DeparturesResponse departures = null;
		List<Departure> allDeparture = new ArrayList<Departure>();
		int page = 1;
		do {
			departures = new Gson().fromJson(callGaApi(String.format(GET_DEPARTURES, tourId, "?page=" + page)),
					DeparturesResponse.class);
			allDeparture.addAll(departures.getResults());
			System.out.println("---- Page Tours departures " + page);
			page++;
		} while (departures.getLinks().stream().anyMatch(l -> "next".equals(l.getRel())));

		List<Departure> actualDeparture = new ArrayList<Departure>();
		for (Departure dep : allDeparture)
			if (LocalDate.parse(dep.getStartDate()).isAfter(LocalDate.now())) {
				actualDeparture.add(dep);
			}
		return actualDeparture;
	}

	public static Map<TourDossierResponse, List<Departure>> getToursWithDepartures(int pageStart, int pageEnd) {
		System.out.println("---- GA Tours Page " + pageStart);
		TourDossierResultsResponse tourResponse = null;
		List<Result> results = new ArrayList<Result>();

		do {
			// ?page=2
			tourResponse = new Gson().fromJson(callGaApi(String.format(GET_TOURS, "?page=" + pageStart)),
					TourDossierResultsResponse.class);
			results.addAll(tourResponse.getResults());
			System.out.println("---- Page Tours tour_dossiers " + pageStart);
			pageStart++;
		} while (tourResponse.getLinks().stream().anyMatch(l -> "next".equals(l.getRel())) && pageStart < pageEnd + 1);

		Map<TourDossierResponse, List<Departure>> actualDeparture = results.stream().collect(Collectors.toMap(
				res -> GaUtil.getTourDossierByTourId(res.getId()), res -> GaUtil.getDeparturesOfTheTour(res.getId())));
		return actualDeparture;
	}

	public static Map<TourDossierResponse, List<Departure>> getTourWithDepartures(String tourId) {
		Map<TourDossierResponse, List<Departure>> actualDeparture = new HashMap<TourDossierResponse, List<Departure>>();
		TourDossierResponse td = getTourDossierByTourId(tourId);
		actualDeparture.put(td, getDeparturesOfTheTour(tourId));
		return actualDeparture;
	}

	public static ItinerariesResponse getItineraries(String itinerariesUrl) {
		return new Gson().fromJson(GaUtil.callGaApi(itinerariesUrl), ItinerariesResponse.class);
	}

	public static DepartureDetailsResponse getDepartureDetails(String departureUrl) {
		return new Gson().fromJson(GaUtil.callGaApi(departureUrl), DepartureDetailsResponse.class);
	}

	public static TourDossierResponse getTourDossierByTourId(String tourId) {
		return new Gson().fromJson(callGaApi(String.format(GET_TOURS, tourId)), TourDossierResponse.class);
	}

	public static TourDossierResultsResponse getTourByProductLine(String productLine) {
		return new Gson().fromJson(callGaApi(String.format(GET_TOUR_BY_PRODUCTLINE, productLine)),
				TourDossierResultsResponse.class);
	}
}
