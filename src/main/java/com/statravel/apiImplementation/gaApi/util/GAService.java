package com.statravel.apiImplementation.gaApi.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.statravel.apiImplementation.gaApi.pojo.CurrencyCode;
import com.statravel.apiImplementation.gaApi.pojo.departure.Departure;
import com.statravel.apiImplementation.gaApi.pojo.departureDetails.DepartureDetailsResponse;
import com.statravel.apiImplementation.gaApi.pojo.departureDetails.Room;
import com.statravel.apiImplementation.gaApi.pojo.dossier.TourDossierResponse;
import com.statravel.apiImplementation.gaApi.pojo.dossier.TourDossierResultsResponse;
import com.statravel.apiImplementation.gaApi.pojo.dossier.ValidDuringRange;
import com.statravel.apiImplementation.gaApi.pojo.itineraries.ItinerariesResponse;

public class GAService {

	public static TourDossierResultsResponse getTourByProductLine(String productLine) {
		return GaUtil.getTourByProductLine(productLine);
	}

	public static Map<TourDossierResponse, List<Departure>> getAllDeparturesFromResponse(List<String> listIds) {
		Map<TourDossierResponse, List<Departure>> actualDeparture = listIds.stream().collect(
				Collectors.toMap(id -> GaUtil.getTourDossierByTourId(id), id -> GaUtil.getDeparturesOfTheTour(id)));

		return actualDeparture;
	}

	public static List<String> getDepartureRecords(TourDossierResponse r, List<Departure> departures) {
		Optional<Departure> depMin = departures.stream()
				.filter(d -> "AVAILABLE".equals(d.getAvailability().getStatus()))
				.sorted(Comparator.comparing(Departure::getStartLocalDate))
				.min(Comparator.comparing(Departure::getGBPPrice));//
		String visitedPlaces = null;
		List<String> listTour = new ArrayList<String>();
		String itinerariesUrl = "";
		boolean isCheapest = false;
		int rooms = 0;
		int prices = 0;
		String roomp = "";
		String price = "";
		for (Departure tour : departures) {
			price = "";
			if (depMin.isPresent() && tour.equals(depMin.get())) {
				isCheapest = true;
			}
			if ("AVAILABLE".equals(tour.getAvailability().getStatus())) {
				DepartureDetailsResponse details = GaUtil.getDepartureDetails(tour.getHref());
				if (details != null) {
					for (Room room : details.getRooms()) {
						price += room.getName() + ": " + room.getPriceBands().stream()
								.map(pr -> (pr.getName() + "-" + pr.getAllPrices())).collect(Collectors.joining("#"));
					}
					rooms = details.getRooms().size();
					roomp = details.getRooms().stream().map(pr -> pr.getName()).collect(Collectors.joining(";"));
					prices = details.getRooms().get(0).getPriceBands().size();
				}
			}
			String tmp = getItinerariesUrl(r, tour);
			if (tmp != null) {
				if (!tmp.equals(itinerariesUrl)) {
					itinerariesUrl = tmp;
					visitedPlaces = escapeSpecialCharacters(
							GaUtil.getItineraries(itinerariesUrl).getVisitedPlacesToString());
				}
			} else {
				visitedPlaces = null;
				itinerariesUrl = null;
			}
			listTour.add((isCheapest ? ("CheapestAvailable departureId=" + tour.getId() + " for tourId=" + r.getId())
					: (depMin.isPresent() ? "" : "no available tours for tourId=" + r.getId())) + "," + r.getId() + ","
					+ r.getProductLine() + "," + escapeSpecialCharacters(r.getName()) + "," + tour.getId() + ","
					+ tour.getStartDate() + "," + tour.getFinishDate() + "," + tour.getAvailability().getStatus() + ","
					+ tour.getAvailability().getTotal() + "," + tour.getGBPPrice() + "," + isCheapest + ","
					+ visitedPlaces + "," + tour.getPrices() + "," + tour.getPrice(CurrencyCode.AUD) + ","
					+ tour.getPrice(CurrencyCode.USD) + "," + tour.getPrice(CurrencyCode.NZD) + ","
					+ tour.getPrice(CurrencyCode.CHF) + "," + tour.getPrice(CurrencyCode.EUR) + "," + roomp + ","
					+ price + "," + itinerariesUrl);
			isCheapest = false;
		}
		return listTour;
	}

	public static Map<TourDossierResponse, List<Departure>> getAllDeparturesFromResponse(String pageStart,
			String pageEnd) {
		Map<TourDossierResponse, List<Departure>> departures = GaUtil.getToursWithDepartures(Integer.valueOf(pageStart),
				Integer.valueOf(pageEnd));
		return departures;
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

	public static void writeToCsv(String fileName, Map<TourDossierResponse, List<Departure>> actualDepartures) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss");
		fileName += "_" + LocalDateTime.now().format(formatter) + ".csv";
		List<String> departuresRecords = new ArrayList<String>();
		actualDepartures.keySet().stream()
				.forEach(td -> departuresRecords.addAll(GAService.getDepartureRecords(td, actualDepartures.get(td))));
		departuresRecords.add(0,
				"CheapestDeparture,TourDossiersId,ProductLine,Name,DepartureId,StartDate,FinishDate,Availability,Availability.Total,Price in GBP,IsCheapest,VisitedPlaces,"
						+ "AllPrices,Price in AUD,Price in USD,Price in NZD,Price in CHF,Price in EUR,Rooms,RoomsPrice,ItinerariesUrl");
		try (PrintWriter pw = new PrintWriter(fileName)) {
			departuresRecords.stream().forEach(pw::println);
			System.out.println("--------- Please find the data in " + fileName);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static void writeToCsv(String fileName, List<String> departuresRecords) {
		departuresRecords.add(0,
				"CheapestDeparture,TourDossiersId,ProductLine,Name,DepartureId,StartDate,FinishDate,Availability,Availability.Total,Price in GBP,IsCheapest,VisitedPlaces,"
						+ "AllPrices,Price in AUD,Price in USD,Price in NZD,Price in CHF,Price in EUR,Rooms,RoomsPrice,ItinerariesUrl");
		try (PrintWriter pw = new PrintWriter(fileName)) {
			departuresRecords.stream().forEach(pw::println);
			System.out.println("--------- Please find the data in " + fileName);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
