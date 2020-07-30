
package com.statravel.gaApi.pojo.dossier;

import java.time.LocalDate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValidDuringRange {

	@SerializedName("start_date")
	@Expose
	private String startDate;
	@SerializedName("end_date")
	@Expose
	private String endDate;

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

	public LocalDate getEndLocalDate() {
    	if(endDate!=null) {
    		return LocalDate.parse(endDate);
    	}
		else return null;
	}

	public LocalDate getStartLocalDate() {
		return LocalDate.parse(startDate);
	}

}
