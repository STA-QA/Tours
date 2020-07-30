
package com.statravel.gaApi.pojo.dossier;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("image_href")
    @Expose
    private String imageHref;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

}
