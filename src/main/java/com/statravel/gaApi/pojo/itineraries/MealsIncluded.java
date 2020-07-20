
package com.statravel.gaApi.pojo.itineraries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MealsIncluded {

    @SerializedName("breakfast")
    @Expose
    private int breakfast;
    @SerializedName("lunch")
    @Expose
    private int lunch;
    @SerializedName("dinner")
    @Expose
    private int dinner;

    public int getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(int breakfast) {
        this.breakfast = breakfast;
    }

    public int getLunch() {
        return lunch;
    }

    public void setLunch(int lunch) {
        this.lunch = lunch;
    }

    public int getDinner() {
        return dinner;
    }

    public void setDinner(int dinner) {
        this.dinner = dinner;
    }

}
