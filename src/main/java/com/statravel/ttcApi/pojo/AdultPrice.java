package com.statravel.ttcApi.pojo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdultPrice {

	@SerializedName("full")
	@Expose
	private int full;
	@SerializedName("base")
	@Expose
	private int base;
	@SerializedName("discounted")
	@Expose
	private float discounted;
	@SerializedName("included")
	@Expose
	private List<Object> included = null;

	public int getFull() {
		return full;
	}

	public void setFull(int full) {
		this.full = full;
	}

	public AdultPrice withFull(int full) {
		this.full = full;
		return this;
	}

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	public AdultPrice withBase(int base) {
		this.base = base;
		return this;
	}

	public float getDiscounted() {
		return discounted;
	}

	public void setDiscounted(int discounted) {
		this.discounted = discounted;
	}

	public AdultPrice withDiscounted(int discounted) {
		this.discounted = discounted;
		return this;
	}

	public List<Object> getIncluded() {
		return included;
	}

	public void setIncluded(List<Object> included) {
		this.included = included;
	}

	public AdultPrice withIncluded(List<Object> included) {
		this.included = included;
		return this;
	}

}
