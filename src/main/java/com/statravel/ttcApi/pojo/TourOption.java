
package com.statravel.ttcApi.pojo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TourOption {

	@SerializedName("id")
	@Expose
	private int id;
	@SerializedName("websiteUrls")
	@Expose
	private List<WebsiteUrl> websiteUrls = null;
	@SerializedName("seasons")
	@Expose
	private List<Season> seasons = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TourOption withId(int id) {
		this.id = id;
		return this;
	}

	public List<WebsiteUrl> getWebsiteUrls() {
		return websiteUrls;
	}

	public void setWebsiteUrls(List<WebsiteUrl> websiteUrls) {
		this.websiteUrls = websiteUrls;
	}

	public TourOption withWebsiteUrls(List<WebsiteUrl> websiteUrls) {
		this.websiteUrls = websiteUrls;
		return this;
	}

	public List<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}

	public TourOption withSeasons(List<Season> seasons) {
		this.seasons = seasons;
		return this;
	}

}
