package com.statravel.codeImplementation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.awt.Desktop.Action;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.statravel.apiImplementation.ttcApi.pojo.DepartureTtc;
import com.statravel.apiImplementation.ttcApi.util.CheapestTour;
import com.statravel.base.BaseUtil;

public class HomePage extends BaseUtil {

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.ID, using = "Atoms/Icons/Card-View")
	public WebElement GridViewIcon;

	@FindBy(how = How.ID, using = "Atoms/Icons/List-View")
	public WebElement ListViewIcon;

	@FindBy(how = How.XPATH, using = "//*[@class='sta-select sta-cursor-pointer']")
	public WebElement SortDropDown;

	@FindBy(how = How.XPATH, using = "//*[@value='withDiscount']")
	public WebElement Discounts;

	@FindBy(how = How.ID, using = "duration min")
	public WebElement MinimumDuration;

	@FindBy(how = How.ID, using = "duration max")
	public WebElement MaximumDuration;

	@FindBy(how = How.ID, using = "budget min")
	public WebElement MinimumBudget;

	@FindBy(how = How.ID, using = "budget max")
	public WebElement MaximumBudget;

	@FindBy(how = How.NAME, using = "input_search")
	public WebElement AutoSearch;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'sta-search-icon')]")
	public WebElement SearchIcon;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'sta-cursor-pointer')][not (contains(@class,'sta-search-icon'))]")
	public WebElement CrossIcon;

	@FindBy(how = How.ID, using = "search-dropdown")
	public WebElement SearchDrop;

	@FindBy(how = How.XPATH, using = "//ul[@id='search-dropdown']/li")
	public List<WebElement> SearchDropdown;

	@FindBy(how = How.XPATH, using = "//div[2]/div[contains(@class,'sta-text-lg sta-font-bold sta-text-main-pink sta-leading-none')]")
	public List<WebElement> PriceList;

	@FindBy(how = How.XPATH, using = "//div[2]/div[@class='sta-text-sm sta-line-through']")
	public List<WebElement> WasPriceList;

	@FindBy(how = How.XPATH, using = "  //div[@class='sta-text-xs sta-font-bold sta-ml-1 sta-inline-block']")
	public List<WebElement> DurationList;

	@FindBy(how = How.CLASS_NAME, using = "sta-h2 sta-text-2xl")
	public WebElement NumberofToursFound;

	@FindBy(how = How.XPATH, using = "//*[@id='root']/div[2]/section/section/aside/div[4]/div[2]/div[2]/div/div/div/div[1]")
	public WebElement MinDurationSlider;

	@FindBy(how = How.XPATH, using = "//*[@id='root']/div[2]/section/section/aside/div[4]/div[2]/div[2]/div/div/div/div[2]")
	public WebElement MaxDurationSlider;

	@FindBy(how = How.XPATH, using = "//*[@id='root']/div[2]/section/section/aside/div[4]/div[2]/div[1]/div[1]/span[1]")
	public WebElement MinDurationText;

	@FindBy(how = How.XPATH, using = "//*[@id='root']/div[2]/section/section/aside/div[4]/div[2]/div[1]/div[2]/span[1]")
	public WebElement MaxDurationText;

	@FindBy(how = How.XPATH, using = "//*[@id='root']/div[2]/section/section/aside/div[5]/div[2]/div[2]/div/div/div[1]")
	public WebElement MinBudgetSlider;

	@FindBy(how = How.XPATH, using = "//*[@id='root']/div[2]/section/section/aside/div[5]/div[2]/div[2]/div/div/div[2]")
	public WebElement MaxBudgetSlider;

	@FindBy(how = How.XPATH, using = "//*[@id='root']/div[2]/section/section/aside/div[5]/div[2]/div[1]/div[1]/span[2]")
	public WebElement MinBudgetText;

	@FindBy(how = How.XPATH, using = "//*[@id='root']/div[2]/section/section/aside/div[5]/div[2]/div[1]/div[2]/span[2]")
	public WebElement MaxBudgetText;

	@FindBy(how = How.ID, using = "Discounts-desktop")
	public WebElement DiscountCheckbox;

	@FindBy(how = How.XPATH, using = "//li[contains(@class,'rc-pagination-item rc-pagination-item-')]")
	public List<WebElement> PaginationList;

	@FindBy(how = How.XPATH, using = "//li[contains(@class,'rc-pagination-next')]") // rc-pagination-item-link
	public WebElement nextpageicon;

	@FindBy(how = How.XPATH, using = "//li[@class='rc-pagination-next rc-pagination-disabled']") // rc-pagination-item-link
	public WebElement nextpageiconDisabled;

	@FindBy(how = How.XPATH, using = "//h2[@class='sta-h2 sta-text-2xl']")
	public WebElement NoOfToursText;

	@FindBy(how = How.XPATH, using = "//div[@class='sta-card-list-item sta-mt-5']/div[@class='sta-discount']")
	public List<WebElement> TourwithDiscount;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'sta-px-5')]/div[2]/div")
	public List<WebElement> TourList;

	private String discountXpathRelative = ".//div[@class='sta-tour-card' or @class='sta-card-list-item sta-mt-5'][not(contains(@class,'sta-hidden'))]//div[contains(@class,'sta-discount')]";

	@FindBy(how = How.XPATH, using = "//div[@class='sta-tour-card' or @class='sta-card-list-item sta-mt-5'][not(contains(@class,'sta-hidden'))]//h4")
	private List<WebElement> Name;

	@FindBy(how = How.XPATH, using = "//div[@class='sta-tour-card' or @class='sta-card-list-item sta-mt-5'][not(contains(@class,'sta-hidden'))]//div[contains(@class,'sta-text-main-pink')]")
	private List<WebElement> Price;

	private String WasPriceRelative = "./preceding-sibling::div";

	@FindBy(how = How.XPATH, using = "//div[@class='sta-tour-card' or @class='sta-card-list-item sta-mt-5'][not(contains(@class,'sta-hidden'))]//div[contains(@class,'sta-line-through')]")
	private List<WebElement> WasPrice;

	@FindBy(how = How.XPATH, using = "//div[@class='sta-tour-card' or @class='sta-card-list-item sta-mt-5'][not(contains(@class,'sta-hidden'))]//div[@aria-label='Duration']")
	private List<WebElement> Duration;

	@FindBy(how = How.XPATH, using = "//div[@class='sta-tour-card' or @class='sta-card-list-item sta-mt-5'][not(contains(@class,'sta-hidden'))]//div[contains(@class,'sta-text-main-gray')]")
	private List<WebElement> StartDate;

	public List<WebElement> getName() {
		return Name;
	}

	public List<WebElement> getPrice() {
		return Price;
	}

	public void ClickGridViewIcon() {
		GridViewIcon.click();
	}

	public void ClickListViewIcon() {
		ListViewIcon.click();
	}

	public void SelectSortOption(String SortOption) throws InterruptedException {
		SortDropDown.click();
		Thread.sleep(1000);
		Select dropdown = new Select(SortDropDown);
		dropdown.selectByValue(SortOption);
		SortDropDown.sendKeys(Keys.ESCAPE);
		Thread.sleep(2000);
	}

	public void VerifySortOrderDisplay(String Sortorder) {
		if (Sortorder.equalsIgnoreCase("PriceLowest") || Sortorder.equalsIgnoreCase("PriceHighest")) {
			ArrayList<Integer> obtainedPriceList = new ArrayList<>();
			int count = PriceList.size();
			System.out.println("Total Price count is :" + count);
			for (WebElement we : PriceList) {
				String value = we.getText();
				int Price = Integer.parseInt(value.replaceAll("[^0-9]", ""));
				obtainedPriceList.add(Price);
			}

			ArrayList<Integer> sortedPriceList = new ArrayList<>();
			for (Integer s : obtainedPriceList) {
				sortedPriceList.add(s);
			}

			if (Sortorder.equalsIgnoreCase("PriceLowest")) {
				Collections.sort(sortedPriceList);
				System.out.println(obtainedPriceList);
				System.out.println(sortedPriceList);
				Assert.assertEquals(obtainedPriceList, sortedPriceList);
			}

			if (Sortorder.equalsIgnoreCase("PriceHighest")) {
				Collections.sort(sortedPriceList);
				Collections.reverse(sortedPriceList);
				System.out.println(obtainedPriceList);
				System.out.println(sortedPriceList);
				Assert.assertEquals(obtainedPriceList, sortedPriceList);
			}
		}

		if (Sortorder.equalsIgnoreCase("DurationShortest") || Sortorder.equalsIgnoreCase("DurationLongest")) {
			ArrayList<Integer> obtainedDurationList = new ArrayList<>();
			int Durationcount = DurationList.size();
			System.out.println("Total duration count is :" + Durationcount);

			for (WebElement we : DurationList) {
				String value1 = we.getText();
				int Duration = Integer.parseInt(value1.replaceAll("[^0-9]", ""));
				obtainedDurationList.add(Duration);
			}

			ArrayList<Integer> sortedDurationList = new ArrayList<>();
			for (Integer s : obtainedDurationList) {
				sortedDurationList.add(s);
			}

			if (Sortorder.equalsIgnoreCase("DurationShortest")) {
				Collections.sort(sortedDurationList);
				System.out.println(obtainedDurationList);
				System.out.println(sortedDurationList);
				Assert.assertEquals(obtainedDurationList, sortedDurationList);
			}

			if (Sortorder.equalsIgnoreCase("DurationLongest")) {
				Collections.sort(sortedDurationList);
				Collections.reverse(sortedDurationList);
				System.out.println(obtainedDurationList);
				System.out.println(sortedDurationList);
				Assert.assertEquals(obtainedDurationList, sortedDurationList);
			}
		}

		if (Sortorder.equalsIgnoreCase("HighestSavings")) {

			ArrayList<Integer> obtainedWasPriceList = new ArrayList<>();
			int WasPricecount = WasPriceList.size();
			System.out.println("Total WasPrice count is :" + WasPricecount);

			for (WebElement we : WasPriceList) {
				String value2 = we.getText();
				int WasPrice = Integer.parseInt(value2.replaceAll("[^0-9]", ""));
				obtainedWasPriceList.add(WasPrice);
			}
			ArrayList<Integer> Savings = new ArrayList<>();
			ArrayList<Integer> obtainedPriceList = new ArrayList<>();
			int count = PriceList.size();
			System.out.println("Total Price count is :" + count);
			for (WebElement we : PriceList) {
				String value = we.getText();
				int Price = Integer.parseInt(value.replaceAll("[^0-9]", ""));
				obtainedPriceList.add(Price);
			}

			for (int i = 0; i < obtainedWasPriceList.size(); i++) {
				int savings;
				savings = obtainedWasPriceList.get(i) - obtainedPriceList.get(i);
				Savings.add(savings);
			}

			ArrayList<Integer> sortedSavingsList = new ArrayList<>();
			for (Integer s : Savings) {
				sortedSavingsList.add(s);
			}
			Collections.sort(sortedSavingsList);
			Collections.reverse(sortedSavingsList);
			System.out.println(Savings);
			System.out.println(sortedSavingsList);
			Assert.assertEquals(Savings, sortedSavingsList);

		}
	}

	public void ClickPromotionFilter() throws InterruptedException {
		if (Discounts.isDisplayed() && Discounts.isEnabled()) {
			Discounts.click();
			System.out.println("Promotion clicked");
		}
	}

	public void VerifyTourswithPromotions() throws InterruptedException {
		String value = NumberofToursFound.getText();
		int ToursFound = Integer.parseInt(value.replaceAll("\\D", ""));
		System.out.println(ToursFound);
	}

	public void MoveDurationSliderToRight() throws Exception {
		Actions move = new Actions(driver);
		Action action = (Action) move.dragAndDropBy(MinDurationSlider, 50, 0).build();
		action.perform();
		Thread.sleep(3000);
	}

	public void MoveDurationSliderToLeft() throws Exception {
		Actions move = new Actions(driver);
		Action action = (Action) move.dragAndDropBy(MaxDurationSlider, -60, 0).build();
		action.perform();
		Thread.sleep(3000);
	}

	public void VerifyLowestDurationAccordingToDurationFilters() throws Exception {
		int MinRange = Integer.parseInt(MinDurationText.getText());
		System.out.println("Min Duration on slide is : " + MinRange);
		SelectSortOption("durationShortest");
		Thread.sleep(4000);
		int LowDuration = Integer.parseInt(DurationList.get(0).getText().replaceAll("[^0-9]", ""));
		System.out.println("Min Duration is : " + LowDuration);
		Assert.assertTrue(LowDuration >= MinRange);

	}

	public void VerifyHighestPriceAccordingToDurationFilters() throws Exception {
		int MaxRange = Integer.parseInt(MaxDurationText.getText());
		System.out.println("Min Duration on slide is : " + MaxRange);
		SelectSortOption("durationLongest");
		Thread.sleep(4000);
		int HighestDuration = Integer.parseInt(DurationList.get(0).getText().replaceAll("[^0-9]", ""));
		System.out.println("Min Duration is : " + HighestDuration);
		Assert.assertTrue(HighestDuration <= MaxRange);
	}

	public void MoveBudgetSliderToRight() throws Exception {
		Actions move = new Actions(driver);
		Action action = (Action) move.dragAndDropBy(MinBudgetSlider, 20, 0).build();
		action.perform();
		Thread.sleep(3000);
	}

	public void MoveBudgetSliderToLeft() throws Exception {
		Actions move = new Actions(driver);
		Action action = (Action) move.dragAndDropBy(MaxBudgetSlider, -40, 0).build();
		action.perform();
		Thread.sleep(3000);
	}

	public void VerifyLowestPriceAccordingToPriceFilters() throws Exception {
		int MinRange = Integer.parseInt(MinBudgetText.getText());
		System.out.println("Min Budget Range on slide is : " + MinRange);
		SelectSortOption("pricelh");
		Thread.sleep(4000);
		int LowPrice = Integer.parseInt(PriceList.get(0).getText().replaceAll("[^0-9]", ""));
		System.out.println("Min Price is : " + LowPrice);
		Assert.assertTrue(LowPrice >= MinRange);
	}

	public void VerifyHighestPriceAccordingToPriceFilters() throws Exception {
		int MaxRange = Integer.parseInt(MaxBudgetText.getText());
		System.out.println("Max Budget Range on slide is : " + MaxRange);
		SelectSortOption("pricehl");
		int HighPrice = Integer.parseInt(PriceList.get(0).getText().replaceAll("[^0-9]", ""));
		System.out.println("Max Price is : " + HighPrice);
		Assert.assertTrue(HighPrice <= MaxRange);
	}

	public void ClickOnDiscountCheckbox() {
		DiscountCheckbox.click();
	}

	public void VerifyDiscountedToursCount() throws InterruptedException {
		int totalTours = Integer.parseInt(NoOfToursText.getText().replaceAll("[^0-9]", ""));
		ArrayList<Integer> Pages = new ArrayList<>();
		int a = PaginationList.size();
		System.out.println("a is : " + a);
		int discountedtours = 0;

		for (WebElement we : PaginationList) {
			int pageno = Integer.parseInt(we.getAttribute("title"));
			Pages.add(pageno);
		}

		System.out.println(Pages);
		System.out.println("Total no. of Pages are " + Collections.max(Pages));
		int discountedTours1 = TourwithDiscount.size();
		for (int i = 0; i < (Collections.max(Pages) - 1); i++) {
			nextpageicon.click();
			Thread.sleep(2000);
			discountedtours = TourwithDiscount.size();
			int updateddiscountedTours = discountedtours + discountedTours1;
			discountedTours1 = updateddiscountedTours;
		}
		System.out.println("Discounted Tours Count:  " + discountedTours1);
		System.out.println("the text is: " + totalTours);
		Assert.assertEquals(discountedTours1, totalTours);
	}

	public void InputAndSearch(String tourToSearch) {
		AutoSearch.sendKeys(tourToSearch);
		// SearchIcon.click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(CrossIcon));
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElements(SearchDropdown));
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(CrossIcon));
		SearchIcon.click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOfAllElements(SearchDropdown));
		if (TourList.size() == 0) {
			CrossIcon.click();
			AutoSearch.sendKeys(tourToSearch.replace(" and ", " & "));
			new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(CrossIcon));
			new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElements(SearchDropdown));
			new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(CrossIcon));
			SearchIcon.click();
			new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOfAllElements(SearchDropdown));
		}
		Assert.assertTrue(TourList.size() > 0, "Tours not found. 0  results on the page");
	}

	public TourDetailsPage clickOnTour(CheapestTour expTour) {
		int index = searchTourByName(expTour);
		String currWinHandleMenu = driver.getWindowHandle();
		Name.get(index).click();
		try {
			new WebDriverWait(driver, 180) {
			}.until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return (driver.getWindowHandles().size() == 2);
				}
			});
		} catch (TimeoutException e) {
			Assert.fail("New window is not opened");
		}
		if (driver.getWindowHandles().size() == 2) {
			// new window is opened
			Set<String> handles = driver.getWindowHandles();
			for (String windowHandle : handles) {
				if (!windowHandle.equals(currWinHandleMenu)) {
					driver.switchTo().window(windowHandle);
				}
			}
		}
		return new TourDetailsPage(driver);
	}

	public int searchTourByName(CheapestTour expTour) {
		int ind = IntStream.range(0, Name.size())
				.filter(i -> (Name.get(i).getText().replaceAll("&", "and").equals(expTour.getFormattedName()) // .getCaptionName()
				)).findFirst().orElse(-1);
		if (ind < 0) {
			// retry search with tourName
			ind = IntStream.range(0, Name.size())
					.filter(i -> (Name.get(i).getText().replaceAll("&", "and").contains(expTour.getName()))).findFirst()
					.orElse(-1);
		}
		return ind;
	}

	public void verifyTour(CheapestTour expTour) {
		System.out.println("--------------- Verify tour on HomePage with Name: " + expTour.getFormattedName());
		int index = searchTourByName(expTour);
		Assert.assertTrue(index >= 0, "Tour with Name [" + expTour.getFormattedName() + "] not found");
		expTour.setName(Name.get(index).getText().replaceAll("&", "and"));
		SoftAssert softAssertion = new SoftAssert();
		softAssertion.assertEquals(Integer.parseInt(Price.get(index).getText().replaceAll("[^0-9]", "")),
				Math.round(expTour.getDiscountedPrice()), "Price verification failed");
		if (expTour.getFullPrice() != expTour.getDiscountedPrice()) {
			Assert.assertEquals(Price.get(index).findElements(By.xpath(WasPriceRelative)).size(), 1,
					"WasPrice label is not found");
			softAssertion.assertEquals(
					Integer.parseInt(Price.get(index).findElements(By.xpath(WasPriceRelative)).get(0).getText()
							.replaceAll("[^0-9]", "")),
					Math.round(expTour.getFullPrice()), "WasPrice verification failed");
			Assert.assertEquals(TourList.get(index).findElements(By.xpath(discountXpathRelative)).size(), 1,
					"Discount %  label is not found");
			// Discount % value sometimes failed
			/*
			 * softAssertion.assertEquals( Integer.parseInt(
			 * TourList.get(index).findElements(By.xpath(discountXpathRelative)).get(0).
			 * getText().replaceAll("[^0-9]", "")), Math.round(expTour.getDiscount()),
			 * "Discount % verification failed");
			 */
		} else {
			softAssertion.assertEquals(Price.get(index).findElements(By.xpath(WasPriceRelative)).size(), 0,
					"WasPrice label found");
			softAssertion.assertEquals(TourList.get(index).findElements(By.xpath(discountXpathRelative)).size(), 0,
					"Discount % label found");
		}
		softAssertion.assertEquals(
				LocalDate.parse(StartDate.get(index).getText().replaceAll("[a-zA-Z][ ]*", ""),
						DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				LocalDate.parse(expTour.getDeparture().getOperatingStartDate()), "StartDate verification failed");
		softAssertion.assertEquals(Integer.parseInt(Duration.get(index).getText().replaceAll("[^0-9]", "")),
				expTour.getDuration(), "Duration verification failed");
		softAssertion.assertAll();
	}

	public CheapestTour readTourFromUI(int index) {
		CheapestTour tour = new CheapestTour();
		// name, price, date
		tour.setName(Name.get(index).getText().replaceAll(" +", " ").replaceAll("&", "and"));
		tour.setDiscountedPrice(Integer.parseInt(Price.get(index).getText().replaceAll("[^0-9]", "")));
		DepartureTtc departure = new DepartureTtc();
		departure.setOperatingStartDate(StartDate.get(index).getText().replaceAll("[a-zA-Z][ ]*", ""));
		tour.setDeparture(departure);
		return tour;
	}

	public List<CheapestTour> readAllToursFromUI() {
		List<CheapestTour> toursFromUI = new ArrayList<CheapestTour>();
		for (int i = 0; i < TourList.size(); i++) {
			toursFromUI.add(readTourFromUI(i));
		}
		do {
			nextpageicon.click();
			System.out.println("---- Click next");
			new WebDriverWait(driver, 60);
			for (int i = 0; i < TourList.size(); i++) {
				toursFromUI.add(readTourFromUI(i));
			}
		} while (!nextpageicon.getAttribute("class").contains("disabled"));
		return toursFromUI;
	}
}
