package com.statravel.codeImplementation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.statravel.base.BaseUtil;

public class HomePage extends BaseUtil{

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.ID, using ="Atoms/Icons/Card-View") 
	public WebElement GridViewIcon;
	
	@FindBy(how = How.ID, using ="Atoms/Icons/List-View") 
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
	
	//Atoms/Search-Icon
	
		
	
	
	public void ClickGridViewIcon(){
		GridViewIcon.click();
	}
	
	public void ClickListViewIcon(){
		ListViewIcon.click();
	}
		
	public String SelectSortOption(String SortOption) throws InterruptedException {
		SortDropDown.click();
		Thread.sleep(4000);
		Select dropdown = new Select(SortDropDown);
		dropdown.selectByValue(SortOption);
		Thread.sleep(7000);
		return SortOption;
}
}
