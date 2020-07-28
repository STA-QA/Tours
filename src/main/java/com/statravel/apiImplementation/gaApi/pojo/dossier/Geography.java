
package com.statravel.apiImplementation.gaApi.pojo.dossier;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geography {

    @SerializedName("region")
    @Expose
    private Region region;
    @SerializedName("primary_country")
    @Expose
    private Object primaryCountry;
    @SerializedName("start_country")
    @Expose
    private StartCountry startCountry;
    @SerializedName("finish_country")
    @Expose
    private FinishCountry finishCountry;
    @SerializedName("visited_countries")
    @Expose
    private List<VisitedCountry> visitedCountries = null;
    @SerializedName("start_city")
    @Expose
    private StartCity startCity;
    @SerializedName("finish_city")
    @Expose
    private FinishCity finishCity;

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Object getPrimaryCountry() {
        return primaryCountry;
    }

    public void setPrimaryCountry(Object primaryCountry) {
        this.primaryCountry = primaryCountry;
    }

    public StartCountry getStartCountry() {
        return startCountry;
    }

    public void setStartCountry(StartCountry startCountry) {
        this.startCountry = startCountry;
    }

    public FinishCountry getFinishCountry() {
        return finishCountry;
    }

    public void setFinishCountry(FinishCountry finishCountry) {
        this.finishCountry = finishCountry;
    }

    public List<VisitedCountry> getVisitedCountries() {
        return visitedCountries;
    }

    public void setVisitedCountries(List<VisitedCountry> visitedCountries) {
        this.visitedCountries = visitedCountries;
    }

    public StartCity getStartCity() {
        return startCity;
    }

    public void setStartCity(StartCity startCity) {
        this.startCity = startCity;
    }

    public FinishCity getFinishCity() {
        return finishCity;
    }

    public void setFinishCity(FinishCity finishCity) {
        this.finishCity = finishCity;
    }

}
