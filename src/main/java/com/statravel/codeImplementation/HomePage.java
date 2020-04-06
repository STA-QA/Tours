package com.statravel.codeImplementation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.statravel.base.BaseUtil;


public class HomePage extends BaseUtil{

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.ID, using ="Atoms/Icons/Card-View") 
	public WebElement GridViewIcon;
	
	@FindBy(how = How.XPATH, using ="") 
	public WebElement ListViewIcon;
	
	
	public void ClickGridViewIcon(){
		GridViewIcon.click();
	}
	
	public void ClickListViewIcon(){
		ListViewIcon.click();
	}
	
}
