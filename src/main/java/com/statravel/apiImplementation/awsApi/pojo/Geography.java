package com.statravel.apiImplementation.awsApi.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geography {

	@SerializedName("startRegion")
	@Expose
	private String startRegion;
	@SerializedName("startCountry")
	@Expose
	private String startCountry;
	@SerializedName("startCity")
	@Expose
	private String startCity;
	@SerializedName("endRegion")
	@Expose
	private String endRegion;
	@SerializedName("endCountry")
	@Expose
	private String endCountry;
	@SerializedName("endCity")
	@Expose
	private String endCity;

	public String getStartRegion() {
		return startRegion;
	}

	public void setStartRegion(String startRegion) {
		this.startRegion = startRegion;
	}

	public String getStartCountry() {
		return startCountry;
	}

	public void setStartCountry(String startCountry) {
		this.startCountry = startCountry;
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getEndRegion() {
		return endRegion;
	}

	public void setEndRegion(String endRegion) {
		this.endRegion = endRegion;
	}

	public String getEndCountry() {
		return endCountry;
	}

	public void setEndCountry(String endCountry) {
		this.endCountry = endCountry;
	}

	public String getEndCity() {
		return endCity;
	}

	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}

}
