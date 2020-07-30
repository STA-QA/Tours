
package com.statravel.gaApi.pojo.dossier;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingCompany {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("href")
    @Expose
    private String href;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
