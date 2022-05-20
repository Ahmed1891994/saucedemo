package pages;


import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.DriverActions;
import base.DriverContext;
import utilities.ChromeDriverOptions;
import utilities.FireFoxDriverOptions;
import utilities.PropertiesUtil;  

public class BasePage extends DriverContext{
	
	DriverActions driverActions;
	PropertiesUtil propUtil;
	
	@BeforeMethod(alwaysRun = true, description = "setup browser configuration")
	public void Init() throws IOException
	{
		propUtil = new PropertiesUtil();
		Properties prop = propUtil.loadProperties("settings");
		String browser = prop.getProperty("BROWSER");
		String website = prop.getProperty("WEBSITE");
		
		if(browser.contains("chrome"))
		{
			ChromeDriverOptions chrome = new ChromeDriverOptions();
			chrome.SetupBrowser();
		}
		else if(browser.contains("firefox"))
		{
			FireFoxDriverOptions firefox = new FireFoxDriverOptions();
			firefox.SetupBrowser();
		}
		driverActions = new DriverActions();
		
		// open specified website
		driverActions.openURL(website);
	}
	
	@AfterMethod
	public void Close()
	{
		driverActions.deleteAllCookies();
		driverActions.closeAllWindows();
	}
}
