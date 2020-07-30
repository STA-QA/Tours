
package com.statravel.gaApi.pojo.departureDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Addon {

    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("finish_date")
    @Expose
    private String finishDate;
    @SerializedName("min_days")
    @Expose
    private int minDays;
    @SerializedName("max_days")
    @Expose
    private int maxDays;
    @SerializedName("request_space_date")
    @Expose
    private String requestSpaceDate;
    @SerializedName("halt_booking_date")
    @Expose
    private String haltBookingDate;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public int getMinDays() {
        return minDays;
    }

    public void setMinDays(int minDays) {
        this.minDays = minDays;
    }

    public int getMaxDays() {
        return maxDays;
    }

    public void setMaxDays(int maxDays) {
        this.maxDays = maxDays;
    }

    public String getRequestSpaceDate() {
        return requestSpaceDate;
    }

    public void setRequestSpaceDate(String requestSpaceDate) {
        this.requestSpaceDate = requestSpaceDate;
    }

    public String getHaltBookingDate() {
        return haltBookingDate;
    }

    public void setHaltBookingDate(String haltBookingDate) {
        this.haltBookingDate = haltBookingDate;
    }

}
