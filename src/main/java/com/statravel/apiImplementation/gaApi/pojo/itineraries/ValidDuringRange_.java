
package com.statravel.apiImplementation.gaApi.pojo.itineraries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValidDuringRange_ {

    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
