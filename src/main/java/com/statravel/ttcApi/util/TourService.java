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
	
	public void getTourDetails(TourDetailsResponse tour) {
		this.tour = TtcUtil.getTourDetails(brand, region, String.valueOf(tour.getId()));
	}

	public List<CheapestTour> getCheapestToursFromJsonRS() {

		ArrayList<CheapestTour> cTours = new ArrayList<CheapestTour>();
		for (int i = 0; i < tour.getTourOptions().size(); i++) {
			List<Double> prices = JsonPath.read(new Gson().toJson(tour, TourDetailsResponse.class), "$.tourOptions[" + i
					+ "].seasons[*].departures[*].sellingRegions[ ?(@.sellingRegion == '" + region + "')][?(@.onlineBookable == true)][?(@.availability == 'available')].prices[*].adultPrice.discounted");
			if(prices.size()>0) {
			Float minPrice = Float.valueOf(Collections.min(prices).toString());
			Filter<?> filter = filter(where("sellingRegions[?(@.sellingRegion == '" + region + "')][?(@.onlineBookable == true)][?(@.availability == 'available')].prices[*].adultPrice.discounted")
					.is(Collections.min(prices)));
			String departureId = JsonPath.read(new Gson().toJson(tour, TourDetailsResponse.class),
					"$.tourOptions[" + i + "].seasons[*].departures[?][0].id", filter);
			Filter<?> filterMinpr = filter(where("adultPrice.discounted").is(Collections.min(prices)));
			Integer full = JsonPath.read(
					new Gson().toJson(tour, TourDetailsResponse.class), "$.tourOptions[" + i + "].seasons[*].departures[?(@.id == '"
							+ departureId + "')].sellingRegions[?(@.sellingRegion == '" + region + "')][?(@.onlineBookable == true)][?(@.availability == 'available')].prices[?][0].adultPrice.full",
					filterMinpr);
			Departure dep = tour.getTourOptions().get(i).getSeasons().stream().flatMap(s -> s.getDepartures().stream())
					.filter(d -> departureId.equals(d.getId())).findFirst().get();
			//check if StartDate > today
			if(LocalDate.parse(dep.getOperatingStartDate()).isAfter(LocalDate.now())) {
				Season ses = tour.getTourOptions().get(i).getSeasons().stream().filter(s -> s.getDepartures().contains(dep)).findFirst().get();
				CheapestTour cTour = new CheapestTour(brand, region, ses.getContent().get(0), dep);
				cTour.setDiscountedPrice(minPrice.floatValue());
				cTour.setFullPrice(full.floatValue());
				cTour.setName(tour.getName());
				cTours.add(cTour);
				}
			}
		}

		// tour.getContent().getTrackingId()
		// get the cheapest from tourOption list with equal content.trackingId
		Map<String, Optional<CheapestTour>> map = cTours.stream().collect(Collectors.groupingBy(CheapestTour::getTrackingId,
				Collectors.minBy(Comparator.comparing(CheapestTour::getDiscountedPrice))));
		List<CheapestTour> cc = map.values().stream().map(t -> t.get()).collect(Collectors.toList());
		cc.stream()
				.forEach(it -> System.out
						.println(//"Expected TourResults: \n\t" 
				"[" +tour.getId()+"] "+ it.getContent().getName() + ", " + it.getDeparture().getOperatingStartDate()
								+ ", " + it.getDiscountedPrice() + ", TrackingId=" + it.getContent().getTrackingId()+ " "+it.getContent().isOnlineBookable()));

		return cc;
	}
	
	public  void getAllCheapestToursFromResponse() {
		for(TourDetailsResponse allRs : this.allTours) {
			getTourDetails(allRs);
			TourService service = new TourService(allRs);
			service.getCheapestToursFromJsonRS();
		}
	}
	
	public void getAllDeparturesFromResponse() {
		 File csvOutputFile = new File("allToursDepartures.csv");
		 List<String> listTour = new ArrayList<String>();
		 listTour.add("TourId,TourContentName,TrackingId,OperatingStartDate,Availability,OnlineBookable,DiscountedPrices,SellingRegion");
		 try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
			 for(TourDetailsResponse allRs : this.allTours) {
				getTourDetails(allRs);
				listTour.addAll(getAllDeparturesToursFromJsonRS());
			}
			listTour.stream().forEach(pw::println);
		  } catch (FileNotFoundException e) {
			  throw new RuntimeException(e);
		}
	}
	
	public List<String> getAllDeparturesToursFromJsonRS() {

		ArrayList<String> tours = new ArrayList<String>();
		for (TourOption tourOpt: tour.getTourOptions()) {
			List<Departure> departuresList = tourOpt.getSeasons().stream().flatMap(s -> s.getDepartures().stream()).collect(Collectors.toList());
			for(Departure departure : 	departuresList) {
			departure.getSellingRegions().stream().forEach(s ->  tours.add
					(tour.getId()+","+escapeSpecialCharacters( tourOpt.getSeasons().get(0).getContent().get(0).getName())+"," +tourOpt.getSeasons().get(0).getContent().get(0).getTrackingId()+ ","+departure.getOperatingStartDate()+","
							+ s.getAvailability()+","+s.isOnlineBookable()+","+ s.getPrices().stream().map(pr -> String.valueOf(pr.getAdultPrice().getDiscounted())).collect(Collectors.joining(";"))+"," + s.getSellingRegion()
							//+","+s.getPrices().stream().map(pr -> pr.getRoomType()).collect(Collectors.joining(";"))
						));
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
