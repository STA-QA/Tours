
package com.statravel.ttcApi.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Departure {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("operatingStartDate")
    @Expose
    private String operatingStartDate;
    @SerializedName("sellingRegions")
    @Expose
    private List<SellingRegion> sellingRegions = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Departure withId(String id) {
        this.id = id;
        return this;
    }

    public String getOperatingStartDate() {
        return operatingStartDate;
    }

    public void setOperatingStartDate(String operatingStartDate) {
        this.operatingStartDate = operatingStartDate;
    }

    public Departure withOperatingStartDate(String operatingStartDate) {
        this.operatingStartDate = operatingStartDate;
        return this;
    }

    public List<SellingRegion> getSellingRegions() {
        return sellingRegions;
    }

    public void setSellingRegions(List<SellingRegion> sellingRegions) {
        this.sellingRegions = sellingRegions;
    }

    public Departure withSellingRegions(List<SellingRegion> sellingRegions) {
        this.sellingRegions = sellingRegions;
        return this;
    }

}
