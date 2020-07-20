package com.statravel.newtours.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewTour {

	@SerializedName("provider")
	@Expose
	private String provider;
	@SerializedName("language")
	@Expose
	private String language;
	@SerializedName("code")
	@Expose
	private String code;
	@SerializedName("tourId")
	@Expose
	private String tourId;
	@SerializedName("title")
	@Expose
	private String title;
	@SerializedName("geography")
	@Expose
	private Geography geography;
	@SerializedName("minAge")
	@Expose
	private int minAge;
	@SerializedName("maxAge")
	@Expose
	private int maxAge;
	@SerializedName("lastTransformed")
	@Expose
	private String lastTransformed;
	@SerializedName("departureId")
	@Expose
	private String departureId;
	@SerializedName("startDate")
	@Expose
	private String startDate;
	@SerializedName("endDate")
	@Expose
	private String endDate;
	@SerializedName("isGuaranteed")
	@Expose
	private boolean isGuaranteed;
	@SerializedName("incaTrailAvailability")
	@Expose
	private String incaTrailAvailability;
	@SerializedName("roomType")
	@Expose
	private String roomType;
	@SerializedName("ageGrade")
	@Expose
	private String ageGrade;
	@SerializedName("roomCode")
	@Expose
	private String roomCode;
	@SerializedName("currency")
	@Expose
	private String currency;
	@SerializedName("salePrice")
	@Expose
	private int salePrice;
	@SerializedName("originalPrice")
	@Expose
	private int originalPrice;
	@SerializedName("discount")
	@Expose
	private int discount;
	@SerializedName("sku")
	@Expose
	private String sku;
	@SerializedName("sellingRegion")
	@Expose
	private String sellingRegion;
	@SerializedName("startDateTime")
	@Expose
	private String startDateTime;
	@SerializedName("endDateTime")
	@Expose
	private String endDateTime;
	@SerializedName("availability")
	@Expose
	private String availability;
	@SerializedName("deposit")
	@Expose
	private int deposit;
	@SerializedName("duration")
	@Expose
	private int duration;

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTourId() {
		return tourId;
	}

	public void setTourId(String tourId) {
		this.tourId = tourId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Geography getGeography() {
		return geography;
	}

	public void setGeography(Geography geography) {
		this.geography = geography;
	}

	public int getMinAge() {
		return minAge;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public String getLastTransformed() {
		return lastTransformed;
	}

	public void setLastTransformed(String lastTransformed) {
		this.lastTransformed = lastTransformed;
	}

	public String getDepartureId() {
		return departureId;
	}

	public void setDepartureId(String departureId) {
		this.departureId = departureId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean isIsGuaranteed() {
		return isGuaranteed;
	}

	public void setIsGuaranteed(boolean isGuaranteed) {
		this.isGuaranteed = isGuaranteed;
	}

	public String getIncaTrailAvailability() {
		return incaTrailAvailability;
	}

	public void setIncaTrailAvailability(String incaTrailAvailability) {
		this.incaTrailAvailability = incaTrailAvailability;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getAgeGrade() {
		return ageGrade;
	}

	public void setAgeGrade(String ageGrade) {
		this.ageGrade = ageGrade;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public int getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(int originalPrice) {
		this.originalPrice = originalPrice;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getSellingRegion() {
		return sellingRegion;
	}

	public void setSellingRegion(String sellingRegion) {
		this.sellingRegion = sellingRegion;
	}

	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
