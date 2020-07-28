package com.statravel.apiImplementation.gaApi.pojo.dossier;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TourDossierResultsResponse {

	@SerializedName("count")
	@Expose
	private int count;
	@SerializedName("max_per_page")
	@Expose
	private int maxPerPage;
	@SerializedName("current_page")
	@Expose
	private int currentPage;
	@SerializedName("links")
	@Expose
	private List<Link> links = null;
	@SerializedName("results")
	@Expose
	private List<Result> results = null;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getMaxPerPage() {
		return maxPerPage;
	}

	public void setMaxPerPage(int maxPerPage) {
		this.maxPerPage = maxPerPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

}
