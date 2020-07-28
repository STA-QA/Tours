package com.statravel.apiImplementation.awsApi.pojo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TourSummaryResponse {

	//@SerializedName("params")
	//@Expose
	//private Params params;
	
	@SerializedName("length")
	@Expose
	private int length;
	
	@SerializedName("data")
	@Expose
	private List<NewTour> data = null;

	/*public Params getParams() {
		return params;
	}

	public void setParams(Params params) {
		this.params = params;
	}*/

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public List<NewTour> getData() {
		return data;
	}

	public void setData(List<NewTour> data) {
		this.data = data;
	}
}
