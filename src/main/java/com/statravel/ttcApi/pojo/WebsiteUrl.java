
package com.statravel.ttcApi.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WebsiteUrl {

    @SerializedName("sellingRegion")
    @Expose
    private String sellingRegion;
    @SerializedName("url")
    @Expose
    private String url;

    public String getSellingRegion() {
        return sellingRegion;
    }

    public void setSellingRegion(String sellingRegion) {
        this.sellingRegion = sellingRegion;
    }

    public WebsiteUrl withSellingRegion(String sellingRegion) {
        this.sellingRegion = sellingRegion;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public WebsiteUrl withUrl(String url) {
        this.url = url;
        return this;
    }

}
