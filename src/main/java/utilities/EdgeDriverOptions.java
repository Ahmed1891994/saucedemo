package utilities;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import base.DriverContext;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EdgeDriverOptions extends DriverContext implements Browsers{
	
	@Override
	public void SetupBrowser() {
		EdgeOptions options= new EdgeOptions();
		WebDriverManager.edgedriver().setup();
		options.addArguments("--window-size=1024,768");
		driver = (WebDriver) new EdgeDriver(options);
	}
	
}
