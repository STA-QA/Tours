
package com.statravel.gaApi.pojo.departureDetails;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepartureDetailsResponse {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_last_modified")
    @Expose
    private String dateLastModified;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("product_line")
    @Expose
    private String productLine;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("booking_companies")
    @Expose
    private List<BookingCompany> bookingCompanies = null;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("finish_date")
    @Expose
    private String finishDate;
    @SerializedName("date_cancelled")
    @Expose
    private Object dateCancelled;
    @SerializedName("flags")
    @Expose
    private List<String> flags = null;
    @SerializedName("start_address")
    @Expose
    private StartAddress startAddress;
    @SerializedName("finish_address")
    @Expose
    private FinishAddress finishAddress;
    @SerializedName("latest_arrival_time")
    @Expose
    private String latestArrivalTime;
    @SerializedName("earliest_departure_time")
    @Expose
    private String earliestDepartureTime;
    @SerializedName("nearest_start_airport")
    @Expose
    private NearestStartAirport nearestStartAirport;
    @SerializedName("nearest_finish_airport")
    @Expose
    private NearestFinishAirport nearestFinishAirport;
    @SerializedName("tour")
    @Expose
    private Tour tour;
    @SerializedName("tour_dossier")
    @Expose
    private TourDossier tourDossier;
    @SerializedName("rooms")
    @Expose
    private List<Room> rooms = null;
    @SerializedName("addons")
    @Expose
    private List<Object> addons = null;
    @SerializedName("availability")
    @Expose
    private Availability_ availability;
    @SerializedName("lowest_pp2a_prices")
    @Expose
    private List<LowestPp2aPrice> lowestPp2aPrices = null;
    @SerializedName("local_payments")
    @Expose
    private List<Object> localPayments = null;
    @SerializedName("requirements")
    @Expose
    private List<Requirement> requirements = null;
    @SerializedName("components")
    @Expose
    private Components components;
    @SerializedName("structured_itineraries")
    @Expose
    private List<StructuredItinerary> structuredItineraries = null;
    @SerializedName("program")
    @Expose
    private Object program;

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

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(String dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public List<BookingCompany> getBookingCompanies() {
        return bookingCompanies;
    }

    public void setBookingCompanies(List<BookingCompany> bookingCompanies) {
        this.bookingCompanies = bookingCompanies;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public Object getDateCancelled() {
        return dateCancelled;
    }

    public void setDateCancelled(Object dateCancelled) {
        this.dateCancelled = dateCancelled;
    }

    public List<String> getFlags() {
        return flags;
    }

    public void setFlags(List<String> flags) {
        this.flags = flags;
    }

    public StartAddress getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(StartAddress startAddress) {
        this.startAddress = startAddress;
    }

    public FinishAddress getFinishAddress() {
        return finishAddress;
    }

    public void setFinishAddress(FinishAddress finishAddress) {
        this.finishAddress = finishAddress;
    }

    public String getLatestArrivalTime() {
        return latestArrivalTime;
    }

    public void setLatestArrivalTime(String latestArrivalTime) {
        this.latestArrivalTime = latestArrivalTime;
    }

    public String getEarliestDepartureTime() {
        return earliestDepartureTime;
    }

    public void setEarliestDepartureTime(String earliestDepartureTime) {
        this.earliestDepartureTime = earliestDepartureTime;
    }

    public NearestStartAirport getNearestStartAirport() {
        return nearestStartAirport;
    }

    public void setNearestStartAirport(NearestStartAirport nearestStartAirport) {
        this.nearestStartAirport = nearestStartAirport;
    }

    public NearestFinishAirport getNearestFinishAirport() {
        return nearestFinishAirport;
    }

    public void setNearestFinishAirport(NearestFinishAirport nearestFinishAirport) {
        this.nearestFinishAirport = nearestFinishAirport;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public TourDossier getTourDossier() {
        return tourDossier;
    }

    public void setTourDossier(TourDossier tourDossier) {
        this.tourDossier = tourDossier;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Object> getAddons() {
        return addons;
    }

    public void setAddons(List<Object> addons) {
        this.addons = addons;
    }

    public Availability_ getAvailability() {
        return availability;
    }

    public void setAvailability(Availability_ availability) {
        this.availability = availability;
    }

    public List<LowestPp2aPrice> getLowestPp2aPrices() {
        return lowestPp2aPrices;
    }

    public void setLowestPp2aPrices(List<LowestPp2aPrice> lowestPp2aPrices) {
        this.lowestPp2aPrices = lowestPp2aPrices;
    }

    public List<Object> getLocalPayments() {
        return localPayments;
    }

    public void setLocalPayments(List<Object> localPayments) {
        this.localPayments = localPayments;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<Requirement> requirements) {
        this.requirements = requirements;
    }

    public Components getComponents() {
        return components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }

    public List<StructuredItinerary> getStructuredItineraries() {
        return structuredItineraries;
    }

    public void setStructuredItineraries(List<StructuredItinerary> structuredItineraries) {
        this.structuredItineraries = structuredItineraries;
    }

    public Object getProgram() {
        return program;
    }

    public void setProgram(Object program) {
        this.program = program;
    }

}
