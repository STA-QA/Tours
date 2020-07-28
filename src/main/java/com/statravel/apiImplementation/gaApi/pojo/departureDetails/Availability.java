
package com.statravel.apiImplementation.gaApi.pojo.departureDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Availability {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("male")
    @Expose
    private Object male;
    @SerializedName("female")
    @Expose
    private Object female;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getMale() {
        return male;
    }

    public void setMale(Object male) {
        this.male = male;
    }

    public Object getFemale() {
        return female;
    }

    public void setFemale(Object female) {
        this.female = female;
    }

}
