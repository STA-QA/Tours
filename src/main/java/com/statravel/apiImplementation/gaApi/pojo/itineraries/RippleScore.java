
package com.statravel.apiImplementation.gaApi.pojo.itineraries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RippleScore {

    @SerializedName("score")
    @Expose
    private int score;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private Object endDate;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Object getEndDate() {
        return endDate;
    }

    public void setEndDate(Object endDate) {
        this.endDate = endDate;
    }

}
