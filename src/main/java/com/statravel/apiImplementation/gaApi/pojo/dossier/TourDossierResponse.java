
package com.statravel.apiImplementation.gaApi.pojo.dossier;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TourDossierResponse {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("product_line")
    @Expose
    private String productLine;
    @SerializedName("departures_start_date")
    @Expose
    private String departuresStartDate;
    @SerializedName("departures_end_date")
    @Expose
    private String departuresEndDate;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("booking_companies")
    @Expose
    private List<BookingCompany> bookingCompanies = null;
    @SerializedName("structured_itineraries")
    @Expose
    private List<StructuredItinerary> structuredItineraries = null;
    @SerializedName("details")
    @Expose
    private List<Detail> details = null;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("advertised_departures")
    @Expose
    private List<AdvertisedDeparture> advertisedDepartures = null;
    @SerializedName("geography")
    @Expose
    private Geography geography;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("site_links")
    @Expose
    private List<SiteLink> siteLinks = null;
    @SerializedName("tour")
    @Expose
    private Tour tour;
    @SerializedName("departures")
    @Expose
    private Departures departures;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getDeparturesStartDate() {
        return departuresStartDate;
    }

    public void setDeparturesStartDate(String departuresStartDate) {
        this.departuresStartDate = departuresStartDate;
    }

    public String getDeparturesEndDate() {
        return departuresEndDate;
    }

    public void setDeparturesEndDate(String departuresEndDate) {
        this.departuresEndDate = departuresEndDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BookingCompany> getBookingCompanies() {
        return bookingCompanies;
    }

    public void setBookingCompanies(List<BookingCompany> bookingCompanies) {
        this.bookingCompanies = bookingCompanies;
    }

    public List<StructuredItinerary> getStructuredItineraries() {
        return structuredItineraries;
    }

    public void setStructuredItineraries(List<StructuredItinerary> structuredItineraries) {
        this.structuredItineraries = structuredItineraries;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<AdvertisedDeparture> getAdvertisedDepartures() {
        return advertisedDepartures;
    }

    public void setAdvertisedDepartures(List<AdvertisedDeparture> advertisedDepartures) {
        this.advertisedDepartures = advertisedDepartures;
    }

    public Geography getGeography() {
        return geography;
    }

    public void setGeography(Geography geography) {
        this.geography = geography;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<SiteLink> getSiteLinks() {
        return siteLinks;
    }

    public void setSiteLinks(List<SiteLink> siteLinks) {
        this.siteLinks = siteLinks;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Departures getDepartures() {
        return departures;
    }

    public void setDepartures(Departures departures) {
        this.departures = departures;
    }

}
