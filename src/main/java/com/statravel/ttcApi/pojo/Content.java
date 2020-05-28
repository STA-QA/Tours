package com.statravel.ttcApi.pojo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {

	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("startPlace")
	@Expose
	private StartPlace startPlace;
	@SerializedName("endPlace")
	@Expose
	private EndPlace endPlace;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("onlineBookable")
	@Expose
	private boolean onlineBookable;
	@SerializedName("images")
	@Expose
	private List<Image> images = null;
	@SerializedName("tripType")
	@Expose
	private String tripType;
	@SerializedName("description")
	@Expose
	private String description;
	@SerializedName("countriesVisited")
	@Expose
	private List<CountriesVisited> countriesVisited = null;
	@SerializedName("highlights")
	@Expose
	private List<Highlight> highlights = null;
	@SerializedName("whatsIncluded")
	@Expose
	private List<WhatsIncluded> whatsIncluded = null;
	@SerializedName("itinerary")
	@Expose
	private List<Itinerary> itinerary = null;
	@SerializedName("trackingId")
	@Expose
	private String trackingId;
	@SerializedName("tourStyle")
	@Expose
	private String tourStyle;
	@SerializedName("canSearchForFlights")
	@Expose
	private boolean canSearchForFlights;
	@SerializedName("sellingRegions")
	@Expose
	private List<String> sellingRegions = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Content withId(String id) {
		this.id = id;
		return this;
	}

	public StartPlace getStartPlace() {
		return startPlace;
	}

	public void setStartPlace(StartPlace startPlace) {
		this.startPlace = startPlace;
	}

	public Content withStartPlace(StartPlace startPlace) {
		this.startPlace = startPlace;
		return this;
	}

	public EndPlace getEndPlace() {
		return endPlace;
	}

	public void setEndPlace(EndPlace endPlace) {
		this.endPlace = endPlace;
	}

	public Content withEndPlace(EndPlace endPlace) {
		this.endPlace = endPlace;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Content withName(String name) {
		this.name = name;
		return this;
	}

	public boolean isOnlineBookable() {
		return onlineBookable;
	}

	public void setOnlineBookable(boolean onlineBookable) {
		this.onlineBookable = onlineBookable;
	}

	public Content withOnlineBookable(boolean onlineBookable) {
		this.onlineBookable = onlineBookable;
		return this;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Content withImages(List<Image> images) {
		this.images = images;
		return this;
	}

	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

	public Content withTripType(String tripType) {
		this.tripType = tripType;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Content withDescription(String description) {
		this.description = description;
		return this;
	}

	public List<CountriesVisited> getCountriesVisited() {
		return countriesVisited;
	}

	public void setCountriesVisited(List<CountriesVisited> countriesVisited) {
		this.countriesVisited = countriesVisited;
	}

	public Content withCountriesVisited(List<CountriesVisited> countriesVisited) {
		this.countriesVisited = countriesVisited;
		return this;
	}

	public List<Highlight> getHighlights() {
		return highlights;
	}

	public void setHighlights(List<Highlight> highlights) {
		this.highlights = highlights;
	}

	public Content withHighlights(List<Highlight> highlights) {
		this.highlights = highlights;
		return this;
	}

	public List<WhatsIncluded> getWhatsIncluded() {
		return whatsIncluded;
	}

	public void setWhatsIncluded(List<WhatsIncluded> whatsIncluded) {
		this.whatsIncluded = whatsIncluded;
	}

	public Content withWhatsIncluded(List<WhatsIncluded> whatsIncluded) {
		this.whatsIncluded = whatsIncluded;
		return this;
	}

	public List<Itinerary> getItinerary() {
		return itinerary;
	}

	public void setItinerary(List<Itinerary> itinerary) {
		this.itinerary = itinerary;
	}

	public Content withItinerary(List<Itinerary> itinerary) {
		this.itinerary = itinerary;
		return this;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public Content withTrackingId(String trackingId) {
		this.trackingId = trackingId;
		return this;
	}

	public String getTourStyle() {
		return tourStyle;
	}

	public void setTourStyle(String tourStyle) {
		this.tourStyle = tourStyle;
	}

	public Content withTourStyle(String tourStyle) {
		this.tourStyle = tourStyle;
		return this;
	}

	public boolean isCanSearchForFlights() {
		return canSearchForFlights;
	}

	public void setCanSearchForFlights(boolean canSearchForFlights) {
		this.canSearchForFlights = canSearchForFlights;
	}

	public Content withCanSearchForFlights(boolean canSearchForFlights) {
		this.canSearchForFlights = canSearchForFlights;
		return this;
	}

	public List<String> getSellingRegions() {
		return sellingRegions;
	}

	public void setSellingRegions(List<String> sellingRegions) {
		this.sellingRegions = sellingRegions;
	}

	public Content withSellingRegions(List<String> sellingRegions) {
		this.sellingRegions = sellingRegions;
		return this;
	}

}
