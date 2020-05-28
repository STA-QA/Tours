
package com.statravel.ttcApi.pojo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Season {

	@SerializedName("content")
	@Expose
	private List<Content> content = null;
	@SerializedName("departures")
	@Expose
	private List<Departure> departures = null;
	@SerializedName("from")
	@Expose
	private String from;
	@SerializedName("to")
	@Expose
	private String to;
	@SerializedName("childPriceAgeRange")
	@Expose
	private ChildPriceAgeRange childPriceAgeRange;

	public List<Content> getContent() {
		return content;
	}

	public void setContent(List<Content> content) {
		this.content = content;
	}

	public Season withContent(List<Content> content) {
		this.content = content;
		return this;
	}

	public List<Departure> getDepartures() {
		return departures;
	}

	public void setDepartures(List<Departure> departures) {
		this.departures = departures;
	}

	public Season withDepartures(List<Departure> departures) {
		this.departures = departures;
		return this;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Season withFrom(String from) {
		this.from = from;
		return this;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Season withTo(String to) {
		this.to = to;
		return this;
	}

	public ChildPriceAgeRange getChildPriceAgeRange() {
		return childPriceAgeRange;
	}

	public void setChildPriceAgeRange(ChildPriceAgeRange childPriceAgeRange) {
		this.childPriceAgeRange = childPriceAgeRange;
	}

	public Season withChildPriceAgeRange(ChildPriceAgeRange childPriceAgeRange) {
		this.childPriceAgeRange = childPriceAgeRange;
		return this;
	}

}
