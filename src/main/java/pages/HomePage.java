package pages;

import org.openqa.selenium.By;

import base.DriverActions;

public class HomePage {
	// driverActions
	DriverActions driverActions;

	// title
	By menutitle = By.className("title");
	By sortcontainer = By.className("product_sort_container");
	
	// constructor
	public HomePage() {
		driverActions = new DriverActions();
	}
	
	public By getTitle()
	{
		return menutitle;
	}
	
	public boolean checkHomePageAppears()
	{
		return driverActions.isDisplayed(menutitle) && driverActions.isDisplayed(sortcontainer);
	}
}
