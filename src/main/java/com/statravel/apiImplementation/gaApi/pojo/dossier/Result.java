package com.statravel.apiImplementation.gaApi.pojo.dossier;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("href")
	@Expose
	private String href;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("product_line")
	@Expose
	private String productLine;
	@SerializedName("departures_start_date")
	@Expose
	private String departuresStartDate;
	@SerializedName("departures_end_date")
	@Expose
	private String departuresEndDate;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public String getDeparturesStartDate() {
		return departuresStartDate;
	}

	public void setDeparturesStartDate(String departuresStartDate) {
		this.departuresStartDate = departuresStartDate;
	}

	public String getDeparturesEndDate() {
		return departuresEndDate;
	}

	public void setDeparturesEndDate(String departuresEndDate) {
		this.departuresEndDate = departuresEndDate;
	}

}
