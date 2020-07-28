
package com.statravel.apiImplementation.ttcApi.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Itinerary {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("startDay")
    @Expose
    private int startDay;
    @SerializedName("duration")
    @Expose
    private int duration;
    @SerializedName("text")
    @Expose
    private List<String> text = null;
    @SerializedName("accommodation")
    @Expose
    private String accommodation;
    @SerializedName("meals")
    @Expose
    private List<String> meals = null;
    @SerializedName("locationsVisited")
    @Expose
    private List<LocationsVisited> locationsVisited = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Itinerary withTitle(String title) {
        this.title = title;
        return this;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public Itinerary withStartDay(int startDay) {
        this.startDay = startDay;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Itinerary withDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public Itinerary withText(List<String> text) {
        this.text = text;
        return this;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    public Itinerary withAccommodation(String accommodation) {
        this.accommodation = accommodation;
        return this;
    }

    public List<String> getMeals() {
        return meals;
    }

    public void setMeals(List<String> meals) {
        this.meals = meals;
    }

    public Itinerary withMeals(List<String> meals) {
        this.meals = meals;
        return this;
    }

    public List<LocationsVisited> getLocationsVisited() {
        return locationsVisited;
    }

    public void setLocationsVisited(List<LocationsVisited> locationsVisited) {
        this.locationsVisited = locationsVisited;
    }

    public Itinerary withLocationsVisited(List<LocationsVisited> locationsVisited) {
        this.locationsVisited = locationsVisited;
        return this;
    }

}
