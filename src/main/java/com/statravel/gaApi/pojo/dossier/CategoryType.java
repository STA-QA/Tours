
package com.statravel.gaApi.pojo.dossier;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryType {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("label")
    @Expose
    private String label;

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
