
package com.statravel.gaApi.pojo.dossier;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StructuredItinerary {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("variation_id")
    @Expose
    private String variationId;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("valid_during_ranges")
    @Expose
    private List<ValidDuringRange> validDuringRanges = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVariationId() {
        return variationId;
    }

    public void setVariationId(String variationId) {
        this.variationId = variationId;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<ValidDuringRange> getValidDuringRanges() {
        return validDuringRanges;
    }

    public void setValidDuringRanges(List<ValidDuringRange> validDuringRanges) {
        this.validDuringRanges = validDuringRanges;
    }

}
