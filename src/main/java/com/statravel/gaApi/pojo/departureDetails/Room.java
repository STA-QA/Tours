
package com.statravel.gaApi.pojo.departureDetails;

import java.util.Arrays;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.statravel.gaApi.pojo.CurrencyCode;

public class Room {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("flags")
    @Expose
    private List<Object> flags = null;
    @SerializedName("availability")
    @Expose
    private Availability availability;
    @SerializedName("price_bands")
    @Expose
    private List<PriceBand> priceBands = null;
    @SerializedName("addons")
    @Expose
    private List<Addon> addons = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getFlags() {
        return flags;
    }

    public void setFlags(List<Object> flags) {
        this.flags = flags;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public List<PriceBand> getPriceBands() {
        return priceBands;
    }

    public void setPriceBands(List<PriceBand> priceBands) {
        this.priceBands = priceBands;
    }

    public List<Addon> getAddons() {
        return addons;
    }

    public void setAddons(List<Addon> addons) {
        this.addons = addons;
    }
    
}
