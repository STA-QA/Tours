package com.statravel.gaApi.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.testng.Assert;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.statravel.gaApi.pojo.departure.Departure;
import com.statravel.gaApi.pojo.departure.DeparturesResponse;
import com.statravel.gaApi.pojo.departureDetails.DepartureDetailsResponse;
import com.statravel.gaApi.pojo.dossier.Result;
import com.statravel.gaApi.pojo.dossier.TourDossier;
import com.statravel.gaApi.pojo.dossier.TourDossierResponse;
import com.statravel.gaApi.pojo.itineraries.ItinerariesResponse;
import com.statravel.ttcApi.pojo.TourDetailsResponse;
import com.statravel.ttcApi.util.CheapestTour;
import com.statravel.ttcApi.util.TtcUtil.Brand;
import com.statravel.ttcApi.util.TtcUtil.Region;
import com.sun.crypto.provider.DESedeParameters;

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
		// System.out.println("-------- RQ GAApi: " + uri);
		// System.out.println("-------- Response from GAApi: " + rs);
		if (response.getStatus() != 200) {
			// Assert.fail
			System.out.println("Error response from Api " + String.valueOf(response.getStatus()) + "\n"
					+ response.getStatusInfo() + "\n" + rs);
			return null;
		}
		return rs;
	}

	public static List<Departure> getDeparturesOfTheTour(String tourId) {

		DeparturesResponse departures = null;
		List<Departure> allDeparture = new ArrayList<Departure>();
		do {
			int page = 1;
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
		Departure depMin = actualDeparture.stream().filter(d -> "AVAILABLE".equals(d.getAvailability().getStatus()))
				.sorted(Comparator.comparing(Departure::getStartLocalDate))
				.min(Comparator.comparing(Departure::getGBPPrice)).get();//
		System.out.println(depMin.getGBPPrice() + depMin.getStartDate() + depMin.getAvailability().getStatus());
		return actualDeparture;
	}

	public static Map<TourDossierResponse, List<Departure>> getToursWithDepartures(int pageStart, int pageEnd) {
		System.out.println("---- GA Tours Page " + pageStart);
		TourDossier tourResponse = null;
		List<Result> results = new ArrayList<Result>();

		do {
			// ?page=2
			tourResponse = new Gson().fromJson(callGaApi(String.format(GET_TOURS, "?page=" + pageStart)),
					TourDossier.class);
			results.addAll(tourResponse.getResults());
			System.out.println("---- Page Tours tour_dossiers " + pageStart);
			pageStart++;
		} while (tourResponse.getLinks().stream().anyMatch(l -> "next".equals(l.getRel())) && pageStart < pageEnd + 1);

		Map<TourDossierResponse, List<Departure>> actualDeparture = new HashMap<TourDossierResponse, List<Departure>>();
		for (Result res : results) {
			TourDossierResponse td = getTourDossierByTourId(res.getId());
			actualDeparture.put(td, getDeparturesOfTheTour(res.getId()));
		}
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

	public static TourDossier getTourByProductLine(String productLine) {
		return new Gson().fromJson(callGaApi(String.format(GET_TOUR_BY_PRODUCTLINE, productLine)), TourDossier.class);
	}
}
