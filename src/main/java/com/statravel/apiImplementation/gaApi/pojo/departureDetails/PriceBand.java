
package com.statravel.apiImplementation.gaApi.pojo.departureDetails;

import java.util.Arrays;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.statravel.apiImplementation.gaApi.pojo.CurrencyCode;

public class PriceBand {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("min_travellers")
    @Expose
    private int minTravellers;
    @SerializedName("max_travellers")
    @Expose
    private int maxTravellers;
    @SerializedName("min_age")
    @Expose
    private int minAge;
    @SerializedName("max_age")
    @Expose
    private int maxAge;
    @SerializedName("prices")
    @Expose
    private List<Price> prices = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinTravellers() {
        return minTravellers;
    }

    public void setMinTravellers(int minTravellers) {
        this.minTravellers = minTravellers;
    }

    public int getMaxTravellers() {
        return maxTravellers;
    }

    public void setMaxTravellers(int maxTravellers) {
        this.maxTravellers = maxTravellers;
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

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }
    public float getPrice(CurrencyCode currency) {
		return Float.valueOf(
				prices.stream().filter(pr -> currency.toString().equals(pr.getCurrency())).findFirst().get().getAmount())
				.floatValue();
	}

	public String getAllPrices() {
		StringBuilder str = new StringBuilder();
		Arrays.stream(CurrencyCode.values()).forEach(c -> str.append(c + " " + getPrice(c)+";"));
		return str.toString();
	}
}
