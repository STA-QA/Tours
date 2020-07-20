package com.statravel.gaApi.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.statravel.gaApi.pojo.CurrencyCode;
import com.statravel.gaApi.pojo.departure.Departure;
import com.statravel.gaApi.pojo.departureDetails.DepartureDetailsResponse;
import com.statravel.gaApi.pojo.departure.DeparturesResponse;
import com.statravel.gaApi.pojo.dossier.Result;
import com.statravel.gaApi.pojo.dossier.StructuredItinerary;
import com.statravel.gaApi.pojo.dossier.TourDossierResponse;
import com.statravel.gaApi.pojo.dossier.ValidDuringRange;
import com.statravel.gaApi.pojo.itineraries.ItinerariesResponse;
import com.statravel.newtours.pojo.NewTour;
import com.statravel.newtours.pojo.TourSummaryResponse;
import com.statravel.newtours.util.NewToursService;
import com.statravel.ttcApi.util.CheapestTour;

public class GAService {

	public void getAllDeparturesFromRespons() {
		List<Departure> departures = GaUtil.getDeparturesOfTheTour("24781");
		File csvOutputFile = new File("tourDeparturesGA.csv");
		List<String> listTour = new ArrayList<String>();
		listTour.add(
				"TourId,TourContentName,TrackingId,OperatingStartDate,Availability,OnlineBookable,DiscountedPrices");
		try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
			for (Departure tour : departures) {
				listTour.add(tour.getId() + "," // + escapeSpecialCharacters(tour.getContent().getName()) + ","
						+ tour.getStartDate() + "," + tour.getFinishDate() + "," + tour.getAvailability().getStatus()
						+ "," + tour.getGBPPrice() + "," + tour.getHref());
			}
			listTour.stream().forEach(pw::println);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void getAllDeparturesFromResponse() {
		Map<TourDossierResponse, List<Departure>> departures = GaUtil.getToursWithDepartures();
		File csvOutputFile = new File("allToursDeparturesGA.csv");
		List<String> listTour = new ArrayList<String>();
		listTour.add(
				"CheapestDeparture,TourDossiersId,ProductLine,Name,DepartureId,StartDate,FinishDate,Availability,Availability.Total,Price in GBP,IsCheapest,VisitedPlaces,"
						+ "AllPrices,Price in AUD,Price in USD,Price in NZD,Price in CHF,Price in EUR,ItinerariesUrl");
		try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
			for (TourDossierResponse r : departures.keySet()) {
				Optional<Departure> depMin = departures.get(r).stream()
						.filter(d -> "AVAILABLE".equals(d.getAvailability().getStatus()))
						.sorted(Comparator.comparing(Departure::getStartLocalDate))
						.min(Comparator.comparing(Departure::getGBPPrice));//
				String visitedPlaces = null;
				String itinerariesUrl = "";
				boolean isCheapest = false;
				int rooms=0;
				int prices=0;
				for (Departure tour : departures.get(r)) {
					if (depMin.isPresent() && tour.equals(depMin.get())) {
						isCheapest = true;
					}
					if ("AVAILABLE".equals(tour.getAvailability().getStatus())) {
					DepartureDetailsResponse details = GaUtil.getDepartureDetails(tour.getHref());
					if (details != null)	{
						rooms=details.getRooms().size();
						prices=details.getRooms().get(0).getPriceBands().size();
					}}
					String tmp = getItinerariesUrl(r, tour);
					if (tmp != null) {
						// if (itinerariesUrl != null && !itinerariesUrl.equals(tmp)) {
						if (!tmp.equals(itinerariesUrl)) {
							itinerariesUrl = tmp;
							visitedPlaces = escapeSpecialCharacters(
									GaUtil.getItineraries(itinerariesUrl).getVisitedPlacesToString());
						}
					} else {
						visitedPlaces = null;
						itinerariesUrl = null;
					}
					listTour.add(
							(isCheapest ? ("CheapestAvailable departureId=" + tour.getId() + " for tourId=" + r.getId())
									: (depMin.isPresent() ? "" : "no available tours for tourId=" + r.getId())) + ","
									+ r.getId() + "," + r.getProductLine() + "," + escapeSpecialCharacters(r.getName())
									+ "," + tour.getId() + "," + tour.getStartDate() + "," + tour.getFinishDate() + ","
									+ tour.getAvailability().getStatus() + "," + tour.getAvailability().getTotal() + ","
									+ tour.getGBPPrice() + "," + isCheapest + "," + visitedPlaces + ","
									+ tour.getPrices() + "," + tour.getPrice(CurrencyCode.AUD) + ","
									+ tour.getPrice(CurrencyCode.USD) + "," + tour.getPrice(CurrencyCode.NZD) + ","
									+ tour.getPrice(CurrencyCode.CHF) + "," + tour.getPrice(CurrencyCode.EUR) + ","
									+ itinerariesUrl +","+ rooms+","+prices);
					isCheapest = false;
				}
			}
			listTour.stream().forEach(pw::println);
			System.out.println("--------- Please find the data in allToursDeparturesGA.csv --------");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getItinerariesUrl(TourDossierResponse tour, Departure departure) {
		Optional<ValidDuringRange> opt = tour.getStructuredItineraries().stream()
				.flatMap(si -> si.getValidDuringRanges().stream())
				.filter(i -> (departure.getStartLocalDate().compareTo(i.getStartLocalDate()) >= 0
						&& ((i.getEndLocalDate() != null && departure.getFinishLocalDate() != null)
								? departure.getFinishLocalDate().compareTo(i.getEndLocalDate()) <= 0
								: true)))
				.findFirst();
		if (opt.isPresent()) {
			return tour.getStructuredItineraries().stream().filter(si -> si.getValidDuringRanges().contains(opt.get()))
					.findFirst().get().getHref();
		}

		return null;
	}

	public static String escapeSpecialCharacters(String data) {
		String escapedData = data.replaceAll("\\R", " ");
		if (data.contains(",") || data.contains("\"") || data.contains("'")) {
			data = data.replace("\"", "\"\"");
			escapedData = "\"" + data + "\"";
		}
		return escapedData;
	}

	public static String getVisitedPlacesToString(ItinerariesResponse r) {
		StringBuilder b = new StringBuilder();
		getVisitedPlaces(r).stream().forEach(l -> b.append(l).append(";"));
		return b.toString();

	}

	public static List<String> getVisitedPlaces(ItinerariesResponse r) {
		List<String> pl = new ArrayList<String>();
		r.getDays().stream().forEach(i -> {
			pl.add(i.getStartLocation().getName());
			pl.add(i.getEndLocation().getName());
		});
		return pl.stream().distinct().collect(Collectors.toList());

	}

}
