
package com.statravel.apiImplementation.gaApi.pojo.departure;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.statravel.apiImplementation.gaApi.pojo.CurrencyCode;

public class Departure {

	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("href")
	@Expose
	private String href;
	@SerializedName("date_created")
	@Expose
	private String dateCreated;
	@SerializedName("date_last_modified")
	@Expose
	private String dateLastModified;
	@SerializedName("start_date")
	@Expose
	private String startDate;
	@SerializedName("finish_date")
	@Expose
	private String finishDate;
	@SerializedName("date_cancelled")
	@Expose
	private Object dateCancelled;
	@SerializedName("flags")
	@Expose
	private List<String> flags = null;
	@SerializedName("availability")
	@Expose
	private Availability availability;
	@SerializedName("lowest_pp2a_prices")
	@Expose
	private List<LowestPp2aPrice> lowestPp2aPrices = null;

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

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDateLastModified() {
		return dateLastModified;
	}

	public void setDateLastModified(String dateLastModified) {
		this.dateLastModified = dateLastModified;
	}

	public String getStartDate() {
		return startDate;
	}

	public LocalDate getStartLocalDate() {
		return LocalDate.parse(startDate);
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFinishDate() {
		return finishDate;
	}
	
	public LocalDate getFinishLocalDate() {
		return LocalDate.parse(finishDate);
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public Object getDateCancelled() {
		return dateCancelled;
	}

	public void setDateCancelled(Object dateCancelled) {
		this.dateCancelled = dateCancelled;
	}

	public List<String> getFlags() {
		return flags;
	}

	public void setFlags(List<String> flags) {
		this.flags = flags;
	}

	public Availability getAvailability() {
		return availability;
	}

	public void setAvailability(Availability availability) {
		this.availability = availability;
	}

	public List<LowestPp2aPrice> getLowestPp2aPrices() {
		return lowestPp2aPrices;
	}

	public void setLowestPp2aPrices(List<LowestPp2aPrice> lowestPp2aPrices) {
		this.lowestPp2aPrices = lowestPp2aPrices;
	}

	public float getGBPPrice() {
		return getPrice(CurrencyCode.GBP);
	}

	public float getPrice(CurrencyCode currency) {
		return Float.valueOf(
				lowestPp2aPrices.stream().filter(pr -> currency.toString().equals(pr.getCurrency())).findFirst().get().getAmount())
				.floatValue();
	}

	public String getPrices() {
		StringBuilder str = new StringBuilder();
		Arrays.stream(CurrencyCode.values()).forEach(c -> str.append(c + " " + getPrice(c)+";"));
		return str.toString();
	}

}
