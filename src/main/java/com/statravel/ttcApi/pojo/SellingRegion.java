
package com.statravel.ttcApi.pojo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SellingRegion {

	@SerializedName("startDate")
	@Expose
	private String startDate;
	@SerializedName("startTime")
	@Expose
	private String startTime;
	@SerializedName("startTimeLocal")
	@Expose
	private String startTimeLocal;
	@SerializedName("endDate")
	@Expose
	private String endDate;
	@SerializedName("endTime")
	@Expose
	private String endTime;
	@SerializedName("endTimeLocal")
	@Expose
	private String endTimeLocal;
	@SerializedName("prices")
	@Expose
	private List<Price> prices = null;
	@SerializedName("currency")
	@Expose
	private String currency;
	@SerializedName("priceIsIndicative")
	@Expose
	private boolean priceIsIndicative;
	@SerializedName("definiteDeparture")
	@Expose
	private boolean definiteDeparture;
	@SerializedName("discounts")
	@Expose
	private List<Discount> discounts = null;
	@SerializedName("availability")
	@Expose
	private String availability;

	@SerializedName("sellingRegion")
	@Expose
	private String sellingRegion;
	@SerializedName("onlineBookable")
	@Expose
	private boolean onlineBookable;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public SellingRegion withStartDate(String startDate) {
		this.startDate = startDate;
		return this;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public SellingRegion withStartTime(String startTime) {
		this.startTime = startTime;
		return this;
	}

	public String getStartTimeLocal() {
		return startTimeLocal;
	}

	public void setStartTimeLocal(String startTimeLocal) {
		this.startTimeLocal = startTimeLocal;
	}

	public SellingRegion withStartTimeLocal(String startTimeLocal) {
		this.startTimeLocal = startTimeLocal;
		return this;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public SellingRegion withEndDate(String endDate) {
		this.endDate = endDate;
		return this;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public SellingRegion withEndTime(String endTime) {
		this.endTime = endTime;
		return this;
	}

	public String getEndTimeLocal() {
		return endTimeLocal;
	}

	public void setEndTimeLocal(String endTimeLocal) {
		this.endTimeLocal = endTimeLocal;
	}

	public SellingRegion withEndTimeLocal(String endTimeLocal) {
		this.endTimeLocal = endTimeLocal;
		return this;
	}

	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

	public SellingRegion withPrices(List<Price> prices) {
		this.prices = prices;
		return this;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public SellingRegion withCurrency(String currency) {
		this.currency = currency;
		return this;
	}

	public boolean isPriceIsIndicative() {
		return priceIsIndicative;
	}

	public void setPriceIsIndicative(boolean priceIsIndicative) {
		this.priceIsIndicative = priceIsIndicative;
	}

	public SellingRegion withPriceIsIndicative(boolean priceIsIndicative) {
		this.priceIsIndicative = priceIsIndicative;
		return this;
	}

	public boolean isDefiniteDeparture() {
		return definiteDeparture;
	}

	public void setDefiniteDeparture(boolean definiteDeparture) {
		this.definiteDeparture = definiteDeparture;
	}

	public SellingRegion withDefiniteDeparture(boolean definiteDeparture) {
		this.definiteDeparture = definiteDeparture;
		return this;
	}

	public List<Discount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}

	public SellingRegion withDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
		return this;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public SellingRegion withAvailability(String availability) {
		this.availability = availability;
		return this;
	}

	public String getSellingRegion() {
		return sellingRegion;
	}

	public void setSellingRegion(String sellingRegion) {
		this.sellingRegion = sellingRegion;
	}

	public SellingRegion withSellingRegion(String sellingRegion) {
		this.sellingRegion = sellingRegion;
		return this;
	}

	public boolean isOnlineBookable() {
		return onlineBookable;
	}

	public void setOnlineBookable(boolean onlineBookable) {
		this.onlineBookable = onlineBookable;
	}

	public SellingRegion withOnlineBookable(boolean onlineBookable) {
		this.onlineBookable = onlineBookable;
		return this;
	}

}
