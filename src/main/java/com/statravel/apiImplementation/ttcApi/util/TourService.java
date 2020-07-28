package com.statravel.apiImplementation.ttcApi.util;

import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import com.statravel.apiImplementation.ttcApi.pojo.DepartureTtc;
import com.statravel.apiImplementation.ttcApi.pojo.Season;
import com.statravel.apiImplementation.ttcApi.pojo.TourDetailsResponse;
import com.statravel.apiImplementation.ttcApi.pojo.TourOption;
import com.statravel.apiImplementation.ttcApi.util.TtcUtil.Brand;
import com.statravel.apiImplementation.ttcApi.util.TtcUtil.Region;

public class TourService {

	private ArrayList<TourDetailsResponse> allTours;

	private TourDetailsResponse tour;

	private Brand brand;

	private Region region;

	public TourService(Region region) {
		super();
		this.brand = Brand.contiki;
		this.region = region;
		this.allTours = TtcUtil.getTour(brand, region);
	}

	public TourService(TourDetailsResponse tour) {
		super();
		this.brand = tour.getBrand();
		this.region = tour.getRegion();
		this.tour = TtcUtil.getTourDetails(brand, region, String.valueOf(tour.getId()));
	}

	public TourDetailsResponse getTour() {
		return tour;
	}

	public void getTourDetails(TourDetailsResponse tour) {
		this.tour = TtcUtil.getTourDetails(brand, region, String.valueOf(tour.getId()));
	}

	public void getTourDetails(String tourId) {
		this.tour = TtcUtil.getTourDetails(brand, region, tourId);
	}

	public List<CheapestTour> getAvailableCheapestToursFromJsonRS() {

		ArrayList<CheapestTour> cTours = new ArrayList<CheapestTour>();
		for (int i = 0; i < tour.getTourOptions().size(); i++) {
			for (int j = 0; j < tour.getTourOptions().get(i).getSeasons().size(); j++) {
				List<Double> prices = JsonPath.read(new Gson().toJson(tour, TourDetailsResponse.class), "$.tourOptions["
						+ i + "].seasons[" + j + "].departures[*].sellingRegions[ ?(@.sellingRegion == '" + region
						+ "')][?(@.onlineBookable == true)][?(@.availability == 'available')].prices[*].adultPrice.discounted");
				if (prices.size() > 0) {
					Float minPrice = Float.valueOf(Collections.min(prices).toString());
					Filter<?> filter = filter(where("sellingRegions[?(@.sellingRegion == '" + region
							+ "')][?(@.onlineBookable == true)][?(@.availability == 'available')].prices[*].adultPrice.discounted")
									.is(Collections.min(prices)));
					String departureId = JsonPath.read(new Gson().toJson(tour, TourDetailsResponse.class),
							"$.tourOptions[" + i + "].seasons[" + j + "].departures[?][0].id", filter);
					Filter<?> filterMinpr = filter(where("adultPrice.discounted").is(Collections.min(prices)));
					Integer full = JsonPath.read(new Gson().toJson(tour, TourDetailsResponse.class), "$.tourOptions["
							+ i + "].seasons[" + j + "].departures[?(@.id == '" + departureId
							+ "')].sellingRegions[?(@.sellingRegion == '" + region
							+ "')][?(@.onlineBookable == true)][?(@.availability == 'available')].prices[?][0].adultPrice.full",
							filterMinpr);
					Filter<?> filterSel = filter(where("prices[*].adultPrice.discounted").is(Collections.min(prices)));
					String availability = JsonPath.read(new Gson().toJson(tour, TourDetailsResponse.class),
							"$.tourOptions[" + i + "].seasons[" + j + "].departures[?(@.id == '" + departureId
									+ "')].sellingRegions[?][0].availability",
							filterSel);
					boolean bookableOnline = JsonPath.read(new Gson().toJson(tour, TourDetailsResponse.class),
							"$.tourOptions[" + i + "].seasons[" + j + "].departures[?(@.id == '" + departureId
									+ "')].sellingRegions[?][0].onlineBookable",
							filterSel);
					DepartureTtc dep = tour.getTourOptions().get(i).getSeasons().get(j).getDepartures().stream()
							.filter(d -> departureId.equals(d.getId())).findFirst().get();
					// check if StartDate > today
					// if(LocalDate.parse(dep.getOperatingStartDate()).isAfter(LocalDate.now())) {
					Season ses = tour.getTourOptions().get(i).getSeasons().get(j); // stream().filter(s ->
																					// s.getDepartures().contains(dep)).findFirst().get();
					CheapestTour cTour = new CheapestTour(brand, region, ses.getContent().get(0), dep);
					cTour.setDiscountedPrice(minPrice.floatValue());
					cTour.setFullPrice(full.floatValue());
					cTour.setName(tour.getName());
					cTour.setId(tour.getId());
					cTour.setAvailability(availability);
					cTour.setBookableOnline(bookableOnline);
					cTours.add(cTour);
				}
				// }
			}
		}

		// get the cheapest from tourOption list with equal content.trackingId
		Map<String, Optional<CheapestTour>> map = cTours.stream().collect(Collectors.groupingBy(
				CheapestTour::getTrackingId, Collectors.minBy(Comparator.comparing(CheapestTour::getDiscountedPrice))));
		List<CheapestTour> cc = map.values().stream().map(t -> t.get()).collect(Collectors.toList());
		cc.stream().forEach(it -> System.out.println(// "Expected TourResults: \n\t"
				"[TourId" + tour.getId() + "] " + it.getContent().getName() + ", "
						+ it.getDeparture().getOperatingStartDate() + ", " + it.getDiscountedPrice() + ", TrackingId="
						+ it.getContent().getTrackingId() + " " + it.getContent().isOnlineBookable()));

		return cc;
	}

	public List<CheapestTour> getCheapestToursFromJsonRS() {

		ArrayList<CheapestTour> cTours = new ArrayList<CheapestTour>();
		for (int i = 0; i < tour.getTourOptions().size(); i++) {
			for (int j = 0; j < tour.getTourOptions().get(i).getSeasons().size(); j++) {
				List<Double> prices = JsonPath.read(new Gson().toJson(tour, TourDetailsResponse.class),
						"$.tourOptions[" + i + "].seasons[" + j
								+ "].departures[*].sellingRegions[ ?(@.sellingRegion == '" + region
								+ "')].prices[*].adultPrice.discounted");
				if (prices.size() > 0) {
					Float minPrice = Float.valueOf(Collections.min(prices).toString());
					Filter<?> filter = filter(where(
							"sellingRegions[?(@.sellingRegion == '" + region + "')].prices[*].adultPrice.discounted")
									.is(Collections.min(prices)));
					String departureId = JsonPath.read(new Gson().toJson(tour, TourDetailsResponse.class),
							"$.tourOptions[" + i + "].seasons[" + j + "].departures[?][0].id", filter);
					Filter<?> filterMinpr = filter(where("adultPrice.discounted").is(Collections.min(prices)));
					Integer full = JsonPath.read(new Gson().toJson(tour, TourDetailsResponse.class),
							"$.tourOptions[" + i + "].seasons[" + j + "].departures[?(@.id == '" + departureId
									+ "')].sellingRegions[?(@.sellingRegion == '" + region
									+ "')].prices[?][0].adultPrice.full",
							filterMinpr);
					Filter<?> filterSel = filter(where("prices[*].adultPrice.discounted").is(Collections.min(prices)));
					String availability = JsonPath.read(new Gson().toJson(tour, TourDetailsResponse.class),
							"$.tourOptions[" + i + "].seasons[" + j + "].departures[?(@.id == '" + departureId
									+ "')].sellingRegions[?][0].availability",
							filterSel);
					boolean bookableOnline = JsonPath.read(new Gson().toJson(tour, TourDetailsResponse.class),
							"$.tourOptions[" + i + "].seasons[" + j + "].departures[?(@.id == '" + departureId
									+ "')].sellingRegions[?][0].onlineBookable",
							filterSel);
					DepartureTtc dep = tour.getTourOptions().get(i).getSeasons().get(j).getDepartures().stream()
							.filter(d -> departureId.equals(d.getId())).findFirst().get();
					// check if StartDate > today
					// if(LocalDate.parse(dep.getOperatingStartDate()).isAfter(LocalDate.now())) {
					Season ses = tour.getTourOptions().get(i).getSeasons().get(j); // stream().filter(s ->
																					// s.getDepartures().contains(dep)).findFirst().get();
					CheapestTour cTour = new CheapestTour(brand, region, ses.getContent().get(0), dep);
					cTour.setDiscountedPrice(minPrice.floatValue());
					cTour.setFullPrice(full.floatValue());
					cTour.setName(tour.getName());
					cTour.setId(tour.getId());
					cTour.setAvailability(availability);
					cTour.setBookableOnline(bookableOnline);
					cTours.add(cTour);
				}
				// }
			}
		}

		// get the cheapest from tourOption list with equal content.trackingId
		Map<String, Optional<CheapestTour>> map = cTours.stream().collect(Collectors.groupingBy(
				CheapestTour::getTrackingId, Collectors.minBy(Comparator.comparing(CheapestTour::getDiscountedPrice))));
		List<CheapestTour> cc = map.values().stream().map(t -> t.get()).collect(Collectors.toList());
		cc.stream().forEach(it -> System.out.println(// "Expected TourResults: \n\t"
				"[TourId" + tour.getId() + "] " + it.getContent().getName() + ", "
						+ it.getDeparture().getOperatingStartDate() + ", " + it.getDiscountedPrice() + ", TrackingId="
						+ it.getContent().getTrackingId() + " " + it.getContent().isOnlineBookable()));

		return cc;
	}

	public List<CheapestTour> getAllCheapestToursFromResponse() {
		List<CheapestTour> tourList = new ArrayList<CheapestTour>();
		for (TourDetailsResponse allRs : this.allTours) {
			getTourDetails(allRs);
			TourService service = new TourService(allRs);
			if (service.getTour() != null) {
				tourList.addAll(service.getCheapestToursFromJsonRS());
			}
		}
		return tourList;
	}

	public List<CheapestTour> getAllAvailableCheapestToursFromResponse() {
		List<CheapestTour> tourList = new ArrayList<CheapestTour>();
		for (TourDetailsResponse allRs : this.allTours) {
			getTourDetails(allRs);
			TourService service = new TourService(allRs);
			tourList.addAll(service.getAvailableCheapestToursFromJsonRS());
		}
		return tourList;
	}

	public List<String> getAllDeparturesFromResponse() {
		// to search cheapest, to specify cheapest among all departures
		List<CheapestTour> cheapestTours = getAllCheapestToursFromResponse();

		List<String> listTour = new ArrayList<String>();
		for (TourDetailsResponse tour : this.allTours) {
			getTourDetails(tour);
			if (this.tour != null) {
				listTour.addAll(getAllDeparturesToursFromJsonRS(cheapestTours));
			} else {
				listTour.add(tour.getId() + ",Error while calling api fot TourDetails");
			}
		}
		return listTour;
	}

	public List<String> getAllCheapestDeparturesFromResponse() {
		List<String> listTour = new ArrayList<String>();
		for (CheapestTour tour : getAllCheapestToursFromResponse()) {
			listTour.add(tour.getId() + "," + escapeSpecialCharacters(tour.getContent().getName()) + ","
					+ tour.getTrackingId() + "," + tour.getDeparture().getOperatingStartDate() + ","
					+ tour.getEndDateString() + "," + tour.getAvailability() + "," + tour.isBookableOnline() + ","
					+ tour.getDiscountedPrice() + "," + tour.getRoomsString() + "," + tour.getVisitedPlacesToString());
		}
		return listTour;
	}

	public List<String> getAllDeparturesToursFromJsonRS(List<CheapestTour> cheapestTours) {

		ArrayList<String> tours = new ArrayList<String>();
		for (TourOption tourOpt : tour.getTourOptions()) {
			for (Season season : tourOpt.getSeasons()) {
				for (DepartureTtc departure : season.getDepartures()) {
					// TODO seasons - trackingId
					departure.getSellingRegions().stream().forEach(s -> tours.add(tour.getId() + ","
							+ escapeSpecialCharacters(season.getContent().get(0).getName()) + ","
							+ season.getContent().get(0).getTrackingId() + "," + departure
									.getOperatingStartDate()
							+ "," + s.getEndDate() + "," + s.getAvailability() + "," + s.isOnlineBookable() + ","
							+ s.getPrices().stream().map(pr -> String
									.valueOf(pr.getAdultPrice().getDiscounted())).collect(Collectors
											.joining(";"))
							+ "," + s.getPrices().stream().map(pr -> pr.getRoomType()).collect(Collectors.joining(";"))
							+ ","
							+ season.getContent().get(0).getItinerary().stream().flatMap(
									i -> i.getLocationsVisited().stream().map(l -> l.getName().replaceAll(" +", " ")))
									.distinct().collect(Collectors.joining(";"))
							+ ","
							+ cheapestTours.stream().filter(c -> (c.getDeparture().getId().equals(departure.getId())
									&& c.getEndDateString().equals(s.getEndDate())
									&& c.getDiscountedPrice() == Collections.min(s.getPrices().stream()
											.map(pr -> pr.getAdultPrice().getDiscounted()).collect(Collectors.toList()))
									&& c.getTrackingId().equals(season.getContent().get(0).getTrackingId()))).findAny()
									.isPresent()));
				}
			}
		}
		return tours;
	}

	public void writeToCsv(String fileName, List<String> departuresRecords) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss");
		fileName += "_" + LocalDateTime.now().format(formatter) + ".csv";
		departuresRecords.add(0,
				"TourId,TourContentName,TrackingId,StartDate,EndDate,Availability,OnlineBookable,DiscountedPrices,Rooms,VisitedPlaces,IsCheapest");
		try (PrintWriter pw = new PrintWriter(fileName)) {
			departuresRecords.stream().forEach(pw::println);
			System.out.println("--------- Please find the data in " + fileName);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public String escapeSpecialCharacters(String data) {
		String escapedData = data.replaceAll("\\R", " ");
		if (data.contains(",") || data.contains("\"") || data.contains("'")) {
			data = data.replace("\"", "\"\"");
			escapedData = "\"" + data + "\"";
		}
		return escapedData;
	}

}
