package com.statravel.ttcApi.util;

import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;

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
import com.statravel.ttcApi.pojo.Departure;
import com.statravel.ttcApi.pojo.Season;
import com.statravel.ttcApi.pojo.TourDetailsResponse;
import com.statravel.ttcApi.util.TtcUtil.Brand;
import com.statravel.ttcApi.util.TtcUtil.Region;

public class TourService {

	private TourDetailsResponse tour;

	private Brand brand;

	private Region region;

	public TourService(TourDetailsResponse tour) {
		super();
		this.brand = tour.getBrand();
		this.region = tour.getRegion();
		this.tour = TtcUtil.getTourDetails(brand, region, String.valueOf(tour.getId()));
	}

	public List<CheapestTour> getCheapestToursFromJsonRS() {

		ArrayList<CheapestTour> cTours = new ArrayList<CheapestTour>();
		for (int i = 0; i < tour.getTourOptions().size(); i++) {
			List<Double> prices = JsonPath.read(new Gson().toJson(tour, TourDetailsResponse.class), "$.tourOptions[" + i
					+ "].seasons[*].departures[*].sellingRegions[ ?(@.sellingRegion == '" + region + "')][?(@.availability == 'available')].prices[*].adultPrice.discounted");
			Float minPrice = Float.valueOf(Collections.min(prices).toString());
			Filter<?> filter = filter(where("sellingRegions[?(@.sellingRegion == '" + region + "')][?(@.availability == 'available')].prices[*].adultPrice.discounted")
					.is(Collections.min(prices)));
			String departureId = JsonPath.read(new Gson().toJson(tour, TourDetailsResponse.class),
					"$.tourOptions[" + i + "].seasons[*].departures[?][0].id", filter);
			Filter<?> filterMinpr = filter(where("adultPrice.discounted").is(Collections.min(prices)));
			Integer full = JsonPath.read(
					new Gson().toJson(tour, TourDetailsResponse.class), "$.tourOptions[" + i + "].seasons[*].departures[?(@.id == '"
							+ departureId + "')].sellingRegions[?(@.sellingRegion == '" + region + "')][?(@.availability == 'available')].prices[?][0].adultPrice.full",
					filterMinpr);
			Departure dep = tour.getTourOptions().get(i).getSeasons().stream().flatMap(s -> s.getDepartures().stream())
					.filter(d -> departureId.equals(d.getId())).findFirst().get();
			Season ses = tour.getTourOptions().get(i).getSeasons().stream().filter(s -> s.getDepartures().contains(dep)).findFirst().get();
			CheapestTour cTour = new CheapestTour(brand, region, ses.getContent().get(0), dep);
			cTour.setDiscountedPrice(minPrice.floatValue());
			cTour.setFullPrice(full.floatValue());
			cTour.setName(tour.getName());
			cTours.add(cTour);
		}

		// tour.getContent().getTrackingId()
		// get the cheapest from tourOption list with equal content.trackingId
		Map<String, Optional<CheapestTour>> map = cTours.stream().collect(Collectors.groupingBy(CheapestTour::getTrackingId,
				Collectors.minBy(Comparator.comparing(CheapestTour::getDiscountedPrice))));
		List<CheapestTour> cc = map.values().stream().map(t -> t.get()).collect(Collectors.toList());
		cc.stream()
				.forEach(it -> System.out
						.println("Expected TourResults: \n\t" + it.getContent().getName() + ", " + it.getDeparture().getOperatingStartDate()
								+ ", " + it.getDiscountedPrice() + ", TrackingId=" + it.getContent().getTrackingId()));

		return cc;
	}

}
