package com.statravel.codeImplementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.statravel.base.BaseUtil;

public class HomePage extends BaseUtil {

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.ID, using = "Atoms/Icons/Card-View")
	public WebElement GridViewIcon;

	@FindBy(how = How.ID, using = "Atoms/Icons/List-View")
	public WebElement ListViewIcon;

	@FindBy(how = How.XPATH, using = "//*[@class='sta-select']")
	public WebElement SortDropDown;

	@FindBy(how = How.ID, using = "Discounts")
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

	@FindBy(how = How.XPATH, using = "//div[2]/div[contains(@class,'sta-text-lg sta-font-bold sta-text-main-pink sta-leading-none')]")
	public List<WebElement> PriceList;

	@FindBy(how = How.XPATH, using = "//div[2]/div[@class='sta-text-sm sta-line-through']")
	public List<WebElement> WasPriceList;

	@FindBy(how = How.XPATH, using = "  //div[@class='sta-text-xs sta-font-bold sta-ml-1 sta-inline-block']")
	public List<WebElement> DurationList;

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

	public void VerifySortOrderDisplay(String Sortorder){
		if (Sortorder.equalsIgnoreCase("PriceLowest")||Sortorder.equalsIgnoreCase("PriceHighest")){
		ArrayList<Integer> obtainedPriceList = new ArrayList<>();
		int count = PriceList.size();
		System.out.println("Total Price count is :" +count);
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
		
		if (Sortorder.equalsIgnoreCase("DurationShortest")||Sortorder.equalsIgnoreCase("DurationLongest")){
		ArrayList<Integer> obtainedDurationList = new ArrayList<>();
		int Durationcount = DurationList.size();
		System.out.println("Total duration count is :" +Durationcount);
		
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

	if(Sortorder.equalsIgnoreCase("HighestSavings")){

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

}
