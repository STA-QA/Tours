package com.statravel.ttcApi.util;

import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import com.statravel.ttcApi.pojo.Departure;
import com.statravel.ttcApi.pojo.Season;
import com.statravel.ttcApi.pojo.TourDetailsResponse;
import com.statravel.ttcApi.pojo.TourOption;
import com.statravel.ttcApi.util.TtcUtil.Brand;
import com.statravel.ttcApi.util.TtcUtil.Region;

public class TourService {

	private ArrayList<TourDetailsResponse> allTours;

	private TourDetailsResponse tour;

	private Brand brand;

	private Region region;

	public TourService(Brand brand, Region region) {
		super();
		this.brand = brand;
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
	
	public List<CheapestTour> getAvailableCheapestToursFromJsonRS() {

		ArrayList<CheapestTour> cTours = new ArrayList<CheapestTour>();
		for (int i = 0; i < tour.getTourOptions().size(); i++) {
			for (int j = 0; j < tour.getTourOptions().get(i).getSeasons().size(); j++) {
				List<Double> prices = JsonPath.read(new Gson().toJson(tour, TourDetailsResponse.class),
						"$.tourOptions[" + i + "].seasons[" + j
								+ "].departures[*].sellingRegions[ ?(@.sellingRegion == '" + region
								+ "')][?(@.onlineBookable == true)][?(@.availability == 'available')].prices[*].adultPrice.discounted");
				if (prices.size() > 0) {
					Float minPrice = Float.valueOf(Collections.min(prices).toString());
					Filter<?> filter = filter(where(
							"sellingRegions[?(@.sellingRegion == '" + region + "')][?(@.onlineBookable == true)][?(@.availability == 'available')].prices[*].adultPrice.discounted")
									.is(Collections.min(prices)));
					String departureId = JsonPath.read(new Gson().toJson(tour, TourDetailsResponse.class),
							"$.tourOptions[" + i + "].seasons[" + j + "].departures[?][0].id", filter);
					Filter<?> filterMinpr = filter(where("adultPrice.discounted").is(Collections.min(prices)));
					Integer full = JsonPath.read(new Gson().toJson(tour, TourDetailsResponse.class),
							"$.tourOptions[" + i + "].seasons[" + j + "].departures[?(@.id == '" + departureId
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
					Departure dep = tour.getTourOptions().get(i).getSeasons().get(j).getDepartures().stream()
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
				// [?(@.onlineBookable == true)][?(@.availability == 'available')]
				if (prices.size() > 0) {
					Float minPrice = Float.valueOf(Collections.min(prices).toString());
					Filter<?> filter = filter(where(
							"sellingRegions[?(@.sellingRegion == '" + region + "')].prices[*].adultPrice.discounted")
									.is(Collections.min(prices)));// [?(@.onlineBookable == true)][?(@.availability ==
																	// 'available')]
					String departureId = JsonPath.read(new Gson().toJson(tour, TourDetailsResponse.class),
							"$.tourOptions[" + i + "].seasons[" + j + "].departures[?][0].id", filter);
					Filter<?> filterMinpr = filter(where("adultPrice.discounted").is(Collections.min(prices)));
					Integer full = JsonPath.read(new Gson().toJson(tour, TourDetailsResponse.class),
							"$.tourOptions[" + i + "].seasons[" + j + "].departures[?(@.id == '" + departureId
									+ "')].sellingRegions[?(@.sellingRegion == '" + region
									+ "')].prices[?][0].adultPrice.full",
							filterMinpr);// [?(@.onlineBookable == true)][?(@.availability == 'available')]
					Filter<?> filterSel = filter(where("prices[*].adultPrice.discounted").is(Collections.min(prices)));// [?(@.onlineBookable
																														// ==
																														// true)][?(@.availability
																														// ==
																														// 'available')]
					String availability = JsonPath.read(new Gson().toJson(tour, TourDetailsResponse.class),
							"$.tourOptions[" + i + "].seasons[" + j + "].departures[?(@.id == '" + departureId
									+ "')].sellingRegions[?][0].availability",
							filterSel);
					boolean bookableOnline = JsonPath.read(new Gson().toJson(tour, TourDetailsResponse.class),
							"$.tourOptions[" + i + "].seasons[" + j + "].departures[?(@.id == '" + departureId
									+ "')].sellingRegions[?][0].onlineBookable",
							filterSel);
					Departure dep = tour.getTourOptions().get(i).getSeasons().get(j).getDepartures().stream()
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
			if(service.getTour()!=null) {
				tourList.addAll(service.getCheapestToursFromJsonRS());//getAvailableCheapestToursFromJsonRS());
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

	public void getAllDeparturesFromResponse() {
		File csvOutputFile = new File("allToursDeparturesContiki.csv");
		List<String> listTour = new ArrayList<String>();
		listTour.add(
				"TourId,TourContentName,TrackingId,OperatingStartDate,Availability,OnlineBookable,DiscountedPrices,SellingRegion");
		try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
			for (TourDetailsResponse allRs : this.allTours) {
				getTourDetails(allRs);
				listTour.addAll(getAllDeparturesToursFromJsonRS());
			}
			listTour.stream().forEach(pw::println);
			System.out.println("--------- Please find the data in allToursDeparturesContiki.csv --------");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void getAllCheapestDeparturesFromResponse() {
		File csvOutputFile = new File("allCheapestToursDeparturesContiki.csv");
		List<String> listTour = new ArrayList<String>();
		listTour.add(
				"TourId,TourContentName,TrackingId,OperatingStartDate,Availability,OnlineBookable,DiscountedPrices,VisitedPlaces");
		try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
			for (CheapestTour tour : getAllCheapestToursFromResponse()) {
				listTour.add(tour.getId() + "," + escapeSpecialCharacters(tour.getContent().getName()) + ","
						+ tour.getTrackingId() + "," + tour.getDeparture().getOperatingStartDate() + ","
						+ tour.getAvailability() + "," + tour.isBookableOnline() + "," + tour.getDiscountedPrice() +","+tour.getVisitedPlacesToString());
			}
			listTour.stream().forEach(pw::println);
			System.out.println("--------- Please find the data in allCheapestToursDeparturesContiki.csv --------");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public List<String> getAllDeparturesToursFromJsonRS() {

		ArrayList<String> tours = new ArrayList<String>();
		for (TourOption tourOpt : tour.getTourOptions()) {
			for (Season season : tourOpt.getSeasons()) {
				for (Departure departure : season.getDepartures()) {
					// TODO seasons - trackingId
					departure.getSellingRegions().stream().forEach(s -> tours.add(tour.getId() + ","
							+ escapeSpecialCharacters(season.getContent().get(0).getName()) + ","
							+ season.getContent().get(0).getTrackingId() + "," + departure.getOperatingStartDate() + ","
							+ s.getAvailability() + "," + s.isOnlineBookable() + ","
							+ s.getPrices().stream().map(pr -> String.valueOf(pr.getAdultPrice().getDiscounted()))
									.collect(Collectors.joining(";"))
							+ "," + s.getSellingRegion()
					// +","+s.getPrices().stream().map(pr ->
					// pr.getRoomType()).collect(Collectors.joining(";"))
					));
				}
			}
		}
		return tours;
	}

	public static String escapeSpecialCharacters(String data) {
		String escapedData = data.replaceAll("\\R", " ");
		if (data.contains(",") || data.contains("\"") || data.contains("'")) {
			data = data.replace("\"", "\"\"");
			escapedData = "\"" + data + "\"";
		}
		return escapedData;
	}

}
