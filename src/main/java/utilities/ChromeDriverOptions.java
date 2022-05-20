package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import base.DriverContext;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverOptions extends DriverContext implements Browsers{

	public void SetupBrowser() {
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		//options.addArguments("");
		options.addArguments("--window-size=1024,768");
		driver = (WebDriver) new ChromeDriver(options);
	}

}
