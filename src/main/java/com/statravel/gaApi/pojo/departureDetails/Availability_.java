
package com.statravel.gaApi.pojo.departureDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Availability_ {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("total")
    @Expose
    private int total;

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

}
