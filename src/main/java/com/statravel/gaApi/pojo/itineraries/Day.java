
package com.statravel.gaApi.pojo.itineraries;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Day {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("day")
    @Expose
    private int day;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("instructions")
    @Expose
    private String instructions;
    @SerializedName("start_location")
    @Expose
    private StartLocation_ startLocation;
    @SerializedName("end_location")
    @Expose
    private EndLocation_ endLocation;
    @SerializedName("meals")
    @Expose
    private List<Meal> meals = null;
    @SerializedName("components")
    @Expose
    private List<Component> components = null;
    @SerializedName("optional_activities")
    @Expose
    private List<Object> optionalActivities = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public StartLocation_ getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(StartLocation_ startLocation) {
        this.startLocation = startLocation;
    }

    public EndLocation_ getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(EndLocation_ endLocation) {
        this.endLocation = endLocation;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public List<Object> getOptionalActivities() {
        return optionalActivities;
    }

    public void setOptionalActivities(List<Object> optionalActivities) {
        this.optionalActivities = optionalActivities;
    }

}
