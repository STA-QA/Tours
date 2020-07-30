
package com.statravel.gaApi.pojo.dossier;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail {

    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("detail_type")
    @Expose
    private DetailType detailType;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public DetailType getDetailType() {
        return detailType;
    }

    public void setDetailType(DetailType detailType) {
        this.detailType = detailType;
    }

}
