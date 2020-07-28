
package com.statravel.apiImplementation.ttcApi.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Discount {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("byPassenger")
    @Expose
    private boolean byPassenger;
    @SerializedName("applied")
    @Expose
    private boolean applied;
    @SerializedName("appliesUntil")
    @Expose
    private String appliesUntil;
    @SerializedName("discountType")
    @Expose
    private String discountType;
    @SerializedName("percent")
    @Expose
    private int percent;
    @SerializedName("combinesWith")
    @Expose
    private List<Object> combinesWith = null;
    @SerializedName("type")
    @Expose
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Discount withName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Discount withCode(String code) {
        this.code = code;
        return this;
    }

    public boolean isByPassenger() {
        return byPassenger;
    }

    public void setByPassenger(boolean byPassenger) {
        this.byPassenger = byPassenger;
    }

    public Discount withByPassenger(boolean byPassenger) {
        this.byPassenger = byPassenger;
        return this;
    }

    public boolean isApplied() {
        return applied;
    }

    public void setApplied(boolean applied) {
        this.applied = applied;
    }

    public Discount withApplied(boolean applied) {
        this.applied = applied;
        return this;
    }

    public String getAppliesUntil() {
        return appliesUntil;
    }

    public void setAppliesUntil(String appliesUntil) {
        this.appliesUntil = appliesUntil;
    }

    public Discount withAppliesUntil(String appliesUntil) {
        this.appliesUntil = appliesUntil;
        return this;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Discount withDiscountType(String discountType) {
        this.discountType = discountType;
        return this;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public Discount withPercent(int percent) {
        this.percent = percent;
        return this;
    }

    public List<Object> getCombinesWith() {
        return combinesWith;
    }

    public void setCombinesWith(List<Object> combinesWith) {
        this.combinesWith = combinesWith;
    }

    public Discount withCombinesWith(List<Object> combinesWith) {
        this.combinesWith = combinesWith;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Discount withType(String type) {
        this.type = type;
        return this;
    }

}
