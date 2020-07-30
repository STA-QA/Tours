
package com.statravel.apiImplementation.gaApi.pojo.itineraries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Component {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("start_location")
    @Expose
    private Object startLocation;
    @SerializedName("end_location")
    @Expose
    private Object endLocation;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("instructions")
    @Expose
    private String instructions;
    @SerializedName("accommodation_dossier")
    @Expose
    private Object accommodationDossier;
    @SerializedName("activity_dossier")
    @Expose
    private ActivityDossier activityDossier;
    @SerializedName("transport_dossier")
    @Expose
    private Object transportDossier;
    @SerializedName("transport_leg_dossier")
    @Expose
    private Object transportLegDossier;
    @SerializedName("duration")
    @Expose
    private Object duration;
    @SerializedName("distance_km")
    @Expose
    private Object distanceKm;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("time_period")
    @Expose
    private String timePeriod;
    @SerializedName("is_overnight")
    @Expose
    private boolean isOvernight;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Object startLocation) {
        this.startLocation = startLocation;
    }

    public Object getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Object endLocation) {
        this.endLocation = endLocation;
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

    public Object getAccommodationDossier() {
        return accommodationDossier;
    }

    public void setAccommodationDossier(Object accommodationDossier) {
        this.accommodationDossier = accommodationDossier;
    }

    public ActivityDossier getActivityDossier() {
        return activityDossier;
    }

    public void setActivityDossier(ActivityDossier activityDossier) {
        this.activityDossier = activityDossier;
    }

    public Object getTransportDossier() {
        return transportDossier;
    }

    public void setTransportDossier(Object transportDossier) {
        this.transportDossier = transportDossier;
    }

    public Object getTransportLegDossier() {
        return transportLegDossier;
    }

    public void setTransportLegDossier(Object transportLegDossier) {
        this.transportLegDossier = transportLegDossier;
    }

    public Object getDuration() {
        return duration;
    }

    public void setDuration(Object duration) {
        this.duration = duration;
    }

    public Object getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(Object distanceKm) {
        this.distanceKm = distanceKm;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public boolean isIsOvernight() {
        return isOvernight;
    }

    public void setIsOvernight(boolean isOvernight) {
        this.isOvernight = isOvernight;
    }

}
