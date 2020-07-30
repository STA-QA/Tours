
package com.statravel.apiImplementation.gaApi.pojo.itineraries;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItinerariesResponse {

	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("variation_id")
	@Expose
	private String variationId;
	@SerializedName("href")
	@Expose
	private String href;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("tour_dossier")
	@Expose
	private TourDossier tourDossier;
	@SerializedName("flags")
	@Expose
	private List<Object> flags = null;
	@SerializedName("duration")
	@Expose
	private int duration;
	@SerializedName("start_location")
	@Expose
	private StartLocation startLocation;
	@SerializedName("end_location")
	@Expose
	private EndLocation endLocation;
	@SerializedName("meals_included")
	@Expose
	private MealsIncluded mealsIncluded;
	@SerializedName("meals_budget")
	@Expose
	private Object mealsBudget;
	@SerializedName("details")
	@Expose
	private List<Detail> details = null;
	@SerializedName("packing_lists")
	@Expose
	private List<PackingList> packingLists = null;
	@SerializedName("images")
	@Expose
	private List<Image> images = null;
	@SerializedName("media")
	@Expose
	private Media media;
	@SerializedName("highlights")
	@Expose
	private Highlights highlights;
	@SerializedName("valid_during_ranges")
	@Expose
	private List<ValidDuringRange> validDuringRanges = null;
	@SerializedName("variations")
	@Expose
	private List<Variation> variations = null;
	@SerializedName("days")
	@Expose
	private List<Day> days = null;
	@SerializedName("site_links")
	@Expose
	private List<SiteLink> siteLinks = null;
	@SerializedName("booking_companies")
	@Expose
	private List<BookingCompany> bookingCompanies = null;
	@SerializedName("publish_state")
	@Expose
	private String publishState;
	@SerializedName("ripple_score")
	@Expose
	private RippleScore rippleScore;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVariationId() {
		return variationId;
	}

	public void setVariationId(String variationId) {
		this.variationId = variationId;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TourDossier getTourDossier() {
		return tourDossier;
	}

	public void setTourDossier(TourDossier tourDossier) {
		this.tourDossier = tourDossier;
	}

	public List<Object> getFlags() {
		return flags;
	}

	public void setFlags(List<Object> flags) {
		this.flags = flags;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public StartLocation getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(StartLocation startLocation) {
		this.startLocation = startLocation;
	}

	public EndLocation getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(EndLocation endLocation) {
		this.endLocation = endLocation;
	}

	public MealsIncluded getMealsIncluded() {
		return mealsIncluded;
	}

	public void setMealsIncluded(MealsIncluded mealsIncluded) {
		this.mealsIncluded = mealsIncluded;
	}

	public Object getMealsBudget() {
		return mealsBudget;
	}

	public void setMealsBudget(Object mealsBudget) {
		this.mealsBudget = mealsBudget;
	}

	public List<Detail> getDetails() {
		return details;
	}

	public void setDetails(List<Detail> details) {
		this.details = details;
	}

	public List<PackingList> getPackingLists() {
		return packingLists;
	}

	public void setPackingLists(List<PackingList> packingLists) {
		this.packingLists = packingLists;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public Highlights getHighlights() {
		return highlights;
	}

	public void setHighlights(Highlights highlights) {
		this.highlights = highlights;
	}

	public List<ValidDuringRange> getValidDuringRanges() {
		return validDuringRanges;
	}

	public void setValidDuringRanges(List<ValidDuringRange> validDuringRanges) {
		this.validDuringRanges = validDuringRanges;
	}

	public List<Variation> getVariations() {
		return variations;
	}

	public void setVariations(List<Variation> variations) {
		this.variations = variations;
	}

	public List<Day> getDays() {
		return days;
	}

	public void setDays(List<Day> days) {
		this.days = days;
	}

	public List<SiteLink> getSiteLinks() {
		return siteLinks;
	}

	public void setSiteLinks(List<SiteLink> siteLinks) {
		this.siteLinks = siteLinks;
	}

	public List<BookingCompany> getBookingCompanies() {
		return bookingCompanies;
	}

	public void setBookingCompanies(List<BookingCompany> bookingCompanies) {
		this.bookingCompanies = bookingCompanies;
	}

	public String getPublishState() {
		return publishState;
	}

	public void setPublishState(String publishState) {
		this.publishState = publishState;
	}

	public RippleScore getRippleScore() {
		return rippleScore;
	}

	public void setRippleScore(RippleScore rippleScore) {
		this.rippleScore = rippleScore;
	}

	public String getVisitedPlacesToString() {
		StringBuilder b = new StringBuilder();
		getVisitedPlaces().stream().forEach(l -> b.append(l).append(";"));
		return b.toString();

	}

	public List<String> getVisitedPlaces() {
		List<String> pl = new ArrayList<String>();
		days.stream().forEach(i -> {
			pl.add(i.getStartLocation().getName());
			pl.add(i.getEndLocation().getName());
		});
		return pl.stream().distinct().collect(Collectors.toList());

	}

}
