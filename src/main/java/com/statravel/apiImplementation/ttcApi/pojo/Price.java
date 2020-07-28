
package com.statravel.apiImplementation.ttcApi.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price {

    @SerializedName("adultPrice")
    @Expose
    private AdultPrice adultPrice;
    @SerializedName("roomType")
    @Expose
    private String roomType;
    @SerializedName("maxChildDiscounts")
    @Expose
    private int maxChildDiscounts;

    public AdultPrice getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(AdultPrice adultPrice) {
        this.adultPrice = adultPrice;
    }

    public Price withAdultPrice(AdultPrice adultPrice) {
        this.adultPrice = adultPrice;
        return this;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Price withRoomType(String roomType) {
        this.roomType = roomType;
        return this;
    }

    public int getMaxChildDiscounts() {
        return maxChildDiscounts;
    }

    public void setMaxChildDiscounts(int maxChildDiscounts) {
        this.maxChildDiscounts = maxChildDiscounts;
    }

    public Price withMaxChildDiscounts(int maxChildDiscounts) {
        this.maxChildDiscounts = maxChildDiscounts;
        return this;
    }

}
