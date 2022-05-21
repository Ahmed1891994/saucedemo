package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import base.DriverContext;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FireFoxDriverOptions extends DriverContext implements Browsers{

	public void SetupBrowser() {
		FirefoxOptions options = new FirefoxOptions();
		WebDriverManager.firefoxdriver().setup();
		options.addArguments("--window-size=1024,768");
		driver = (WebDriver) new FirefoxDriver(options);
	}
}
