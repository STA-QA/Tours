
package com.statravel.gaApi.pojo.dossier;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdvertisedDeparture {

    @SerializedName("room")
    @Expose
    private Object room;
    @SerializedName("departure")
    @Expose
    private Departure departure;
    @SerializedName("previous_amount")
    @Expose
    private String previousAmount;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("promotion")
    @Expose
    private Promotion promotion;

    public Object getRoom() {
        return room;
    }

    public void setRoom(Object room) {
        this.room = room;
    }

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public String getPreviousAmount() {
        return previousAmount;
    }

    public void setPreviousAmount(String previousAmount) {
        this.previousAmount = previousAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

}
