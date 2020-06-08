package com.statravel.ttcApi.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.statravel.ttcApi.pojo.Content;
import com.statravel.ttcApi.pojo.Departure;
import com.statravel.ttcApi.util.TtcUtil.Brand;
import com.statravel.ttcApi.util.TtcUtil.Region;

public class CheapestTour {

	private Brand brand;

	private Region region;

	private String name;

	private Content content = null;

	private Departure departure = null;

	private float discountedPrice;

	private float fullPrice;

	public CheapestTour(Brand brand, Region region, Content content, Departure departure) {
		super();
		this.brand = brand;
		this.region = region;
		this.content = content;
		this.departure = departure;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getDiscountedPrice() {
		return discountedPrice;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public Departure getDepartures() {
		return departure;
	}

	public Departure getDeparture() {
		return departure;
	}

	public void setDeparture(Departure departure) {
		this.departure = departure;
	}

	public String getTrackingId() {
		return content.getTrackingId();
	}

	public float getFullPrice() {
		return fullPrice;
	}

	public void setFullPrice(float fullPrice) {
		this.fullPrice = fullPrice;
	}

	public void setDiscountedPrice(float discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public String getCaptionName() {
		return this.getContent().getImages().get(0).getCaption().replaceAll(" +", " ").replaceAll("&", "and");
	}

	public String getFormattedName() {
		int count = StringUtils.countMatches(this.getContent().getName(), "(");
		if (count > 1) {
			int i = this.getContent().getName().lastIndexOf("(");
			return this.getContent().getName().substring(0, i).replaceAll(" +", " ").replaceAll("&", "and");
		} else {
			return this.getContent().getName().replaceAll(" +", " ").replaceAll("&", "and");
		}
	}

	public int getDuration() {
		String startDate = this.getDeparture().getOperatingStartDate();
		String endDate = this.getDeparture().getSellingRegions().stream().filter(s -> s.getSellingRegion().equals(region.toString()))
				.findFirst().get().getEndDate();
		Period period = Period.between(LocalDate.parse(startDate), LocalDate.parse(endDate));
		return period.getDays() + 1;
	}
	
	public LocalDate getStartDate() {
		return LocalDate.parse(this.getDeparture().getOperatingStartDate());
	}

	public String getStartCity() {
		return this.getContent().getStartPlace().getCity();
	}

	public String getEndCity() {
		return this.getContent().getEndPlace().getCity();
	}

	public List<String> getHightlights() {
		return this.getContent().getHighlights().stream().flatMap(h -> h.getItems().stream()).collect(Collectors.toList());
	}

	public Map<String, List<String>> getWhatsIncluded() {
		return this.getContent().getWhatsIncluded().stream().collect(Collectors.toMap(t -> t.getTitle(), t -> t.getItems()));
	}

	public List<ItineraryDay> getItineraryDays() {
		List<ItineraryDay> itineraryDay = this.getContent().getItinerary().stream()
				.map(it -> new ItineraryDay(it.getTitle(), it.getText().get(0), it.getMeals())).collect(Collectors.toList());
		return itineraryDay;
	}

	public List<String> getVisitedPlaces() {
		return this.getContent().getItinerary().stream()
				.flatMap(i -> i.getLocationsVisited().stream().map(l -> l.getName().replaceAll(" +", " "))).distinct()
				.collect(Collectors.toList());
	}

	public int getDiscount() {
		return Math.round((fullPrice - discountedPrice) / fullPrice * 100);
	}
}
