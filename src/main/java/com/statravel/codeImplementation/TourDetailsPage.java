package com.statravel.codeImplementation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.statravel.apiImplementation.ttcApi.util.CheapestTour;
import com.statravel.apiImplementation.ttcApi.util.ItineraryDay;
import com.statravel.base.BaseUtil;

public class TourDetailsPage extends BaseUtil {

	public TourDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.ID, using = "sta-cookie-save-all-button")
	public WebElement btnAcceptCookies;

	@FindBy(how = How.XPATH, using = "//h1")
	public WebElement labelTitle;

	@FindBy(how = How.XPATH, using = "//div[@aria-label='Duration']")
	private WebElement labelDuration;

	@FindBy(how = How.XPATH, using = "//div[@data-testid='highlights_item']")
	private List<WebElement> listHighlights;

	@FindBy(how = How.XPATH, using = "//div[@data-testid='inclusions_item']//div[@class='sta-text-base']")
	private List<WebElement> labelIncludedTitle;

	private String labelIncludedXpathRelative = "./following-sibling::div";

	@FindBy(how = How.XPATH, using = "//div[@data-testid='locations']/span/span[@class='sta-text-main-gray']")
	private List<WebElement> labelVisitedCity;

	@FindBy(how = How.XPATH, using = "//section[@data-testid='desc']")
	public WebElement labelDescription;

	@FindBy(how = How.XPATH, using = "//div[@data-testid='start_city']")
	public WebElement labelStartCity;

	@FindBy(how = How.XPATH, using = "//div[@data-testid='end_city']")
	public WebElement labelEndCity;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'sta-route')]//div[contains(@class,'sta-text-base')]")
	public WebElement labelRouteDuration;

	@FindBy(how = How.XPATH, using = "//section//button")
	public WebElement btnExpandDays;

	@FindBy(how = How.XPATH, using = "//li[contains(@class,'sta-itinerary')]")
	public List<WebElement> labelsDayTitle;

	private String labelDayTitleXpathRelative = ".//h3";

	private String labelDayDescrXpathRelative = ".//div[@data-testid='day_desc']";

	private String labelDayMealsXpathRelative = ".//div[@data-testid='day_meals']";

	@FindBy(how = How.XPATH, using = "//*[name()='nav']//div[@data-testid='price_now']/span")
	public WebElement labelPrice;

	@FindBy(how = How.XPATH, using = "//*[name()='nav']//span[@data-testid='old_price']")
	public WebElement labelWasPrice;

	private String labelWasPriceXpath = "//*[name()='nav']//span[@data-testid='old_price']";

	@FindBy(how = How.XPATH, using = "//*[name()='nav']//div[contains(@class,'sta-text-main-gray')]")
	public WebElement labelDate;

	@FindBy(how = How.XPATH, using = "//*[name()='nav']//span[@data-testid='price_percent']")
	public WebElement labelDiscount;

	private String labelDiscountXpath = "//*[name()='nav']//span[@data-testid='price_percent']";

	public List<ItineraryDay> getItineraryDays() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", btnExpandDays);
		btnExpandDays.click();
		List<ItineraryDay> itineraryDay = labelsDayTitle
				.stream().map(
						it -> new ItineraryDay(
								StringUtils.substringAfter(
										it.findElements(By.xpath(labelDayTitleXpathRelative)).get(0).getText(), "— \n"),
								StringUtils.substringBefore(it.findElements(
										By.xpath(labelDayDescrXpathRelative)).get(0).getText(), "\n"),
								it.findElements(By.xpath(labelDayMealsXpathRelative))
										.size() > 0
												? Arrays.asList(
														WordUtils
																.capitalize(StringUtils.substringAfter(it
																		.findElements(
																				By.xpath(labelDayMealsXpathRelative))
																		.get(0).getText().toLowerCase(), ": "))
																.split(", "))
												: new ArrayList<String>()))
				.collect(Collectors.toList());
		return itineraryDay;
	}

	public void verifyTourDetails(CheapestTour expTour) {
		// btnAcceptCookies.click();
		SoftAssert softAssertion = new SoftAssert();
		softAssertion.assertEquals(labelTitle.getText().replaceAll(" +", " ").replaceAll("&", "and"), expTour.getName(),
				"Name failed");
		List<String> str = listHighlights.stream().map(t -> t.getText().replaceAll("• ", ""))
				.collect(Collectors.toList());
		softAssertion.assertEquals(str, expTour.getHightlights(), "Hightlights verification failed");
		softAssertion.assertEquals(Integer.parseInt(labelPrice.getText().replaceAll("[^0-9]", "")),
				Math.round(expTour.getDiscountedPrice()), "Price verification failed");
		if (expTour.getFullPrice() != expTour.getDiscountedPrice()) {
			softAssertion.assertTrue(labelWasPrice.isDisplayed(), "WasPrice label is not found");
			if (labelWasPrice.isDisplayed()) {
				softAssertion.assertEquals(Integer.parseInt(labelWasPrice.getText().replaceAll("[^0-9]", "")),
						Math.round(expTour.getFullPrice()), "WasPrice verification failed");
			}
			softAssertion.assertTrue(labelDiscount.isDisplayed(), "Discount % label is not found");
			if (labelDiscount.isDisplayed()) {
				softAssertion.assertEquals(Integer.parseInt(labelDiscount.getText().replaceAll("[^0-9]", "")),
						Math.round(expTour.getDiscount()), "Discount % verification failed");
			}
		} else {
			softAssertion.assertTrue(driver.findElements(By.xpath(labelWasPriceXpath)).size() == 0,
					"WasPrice label is found");
			softAssertion.assertTrue(driver.findElements(By.xpath(labelDiscountXpath)).size() == 0,
					"Discount % label is found");
		}
		softAssertion.assertEquals(
				LocalDate.parse(labelDate.getText().replaceAll("[a-zA-Z][ ]*", ""),
						DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				LocalDate.parse(expTour.getDeparture().getOperatingStartDate()), "StartDate verification failed");

		Map<String, List<String>> incl = labelIncludedTitle.stream()
				.collect(Collectors.toMap(t -> t.getText(), t -> t.findElements(By.xpath(labelIncludedXpathRelative))
						.stream().map(tt -> tt.getText()).collect(Collectors.toList())));
		softAssertion.assertEquals(incl, expTour.getWhatsIncluded(), "WhatsIncluded verification failed");

		List<String> listCity = labelVisitedCity.stream().map(t -> t.getText()).collect(Collectors.toList());
		Collections.sort(listCity);
		List<String> expListCity = expTour.getVisitedPlaces();
		Collections.sort(expListCity);
		System.out.println("Actual VisitedPlaces: " + listCity);
		System.out.println("Expected VisitedPlaces: " + expListCity);
		softAssertion.assertEquals(listCity, expListCity, "VisitedPlaces verification failed");
		softAssertion.assertEquals(labelDescription.getText(), expTour.getContent().getDescription(),
				"Description verification failed");
		// start, end city
		softAssertion.assertEquals(labelStartCity.getText(), expTour.getStartCity(), "StartCity verification failed");
		softAssertion.assertEquals(labelEndCity.getText(), expTour.getEndCity(), "EndCity verification failed");
		softAssertion.assertEquals(Integer.parseInt(labelRouteDuration.getText().replaceAll("[^0-9]", "")),
				expTour.getDuration(), "RouteDuration verification failed");
		softAssertion.assertEquals(getItineraryDays(), expTour.getItineraryDays(), "ItineraryDays verification failed");
		softAssertion.assertAll();
	}

}
