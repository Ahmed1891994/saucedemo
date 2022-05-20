package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import base.DriverContext;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FireFoxDriverOptions extends DriverContext implements Browsers{

	public void SetupBrowser() {
		// TODO Auto-generated method stub
		WebDriverManager.firefoxdriver().setup();
		driver = (WebDriver) new FirefoxDriver();
	}
}
