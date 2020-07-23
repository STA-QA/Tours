
package com.statravel.ttcApi.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepartureTtc {

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

    public DepartureTtc withId(String id) {
        this.id = id;
        return this;
    }

    public String getOperatingStartDate() {
        return operatingStartDate;
    }

    public void setOperatingStartDate(String operatingStartDate) {
        this.operatingStartDate = operatingStartDate;
    }

    public DepartureTtc withOperatingStartDate(String operatingStartDate) {
        this.operatingStartDate = operatingStartDate;
        return this;
    }

    public List<SellingRegion> getSellingRegions() {
        return sellingRegions;
    }

    public void setSellingRegions(List<SellingRegion> sellingRegions) {
        this.sellingRegions = sellingRegions;
    }

    public DepartureTtc withSellingRegions(List<SellingRegion> sellingRegions) {
        this.sellingRegions = sellingRegions;
        return this;
    }

}
