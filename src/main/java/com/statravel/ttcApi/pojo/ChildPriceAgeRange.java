package com.statravel.ttcApi.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChildPriceAgeRange {

    @SerializedName("min")
    @Expose
    private int min;
    @SerializedName("max")
    @Expose
    private int max;

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public ChildPriceAgeRange withMin(int min) {
        this.min = min;
        return this;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public ChildPriceAgeRange withMax(int max) {
        this.max = max;
        return this;
    }

}
