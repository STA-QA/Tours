
package com.statravel.apiImplementation.ttcApi.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartPlace {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("airport")
    @Expose
    private String airport;
    @SerializedName("timezone")
    @Expose
    private String timezone;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public StartPlace withCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public StartPlace withCity(String city) {
        this.city = city;
        return this;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public StartPlace withAirport(String airport) {
        this.airport = airport;
        return this;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public StartPlace withTimezone(String timezone) {
        this.timezone = timezone;
        return this;
    }

}
