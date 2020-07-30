package com.statravel.ttcApi.pojo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.statravel.ttcApi.util.TtcUtil.Brand;
import com.statravel.ttcApi.util.TtcUtil.Region;

public class TourDetailsResponse {
	
	private Brand brand;
	
	private Region region;

	@SerializedName("id")
	@Expose
	private int id;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("tourOptions")
	@Expose
	private List<TourOption> tourOptions = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TourDetailsResponse withId(int id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TourDetailsResponse withName(String name) {
		this.name = name;
		return this;
	}

	public List<TourOption> getTourOptions() {
		return tourOptions;
	}

	public void setTourOptions(List<TourOption> tourOptions) {
		this.tourOptions = tourOptions;
	}

	public TourDetailsResponse withTourOptions(List<TourOption> tourOptions) {
		this.tourOptions = tourOptions;
		return this;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}