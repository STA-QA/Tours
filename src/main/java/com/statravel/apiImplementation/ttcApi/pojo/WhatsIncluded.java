
package com.statravel.apiImplementation.ttcApi.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WhatsIncluded {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("items")
    @Expose
    private List<String> items = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public WhatsIncluded withTitle(String title) {
        this.title = title;
        return this;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public WhatsIncluded withItems(List<String> items) {
        this.items = items;
        return this;
    }

}
