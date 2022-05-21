package base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverActions extends DriverContext {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	// ************************************URLNavigation**********************************************
	// open url using navigate to track history
	public void openURL_History(String URL) {
		driver.navigate().to(URL);
	}

	// open url using get() and not care about history
	public void openURL(String URL) {
		driver.get(URL);
	}

	// refresh page
	public void reloadPage() {
		driver.navigate().refresh();
	}

	//Keyboard simulation
	public void keyboardPress(Keys key)
	{
		Actions action = new Actions(driver);
		action.sendKeys(key).perform();
	}
	// ****************************************Buttons************************************************
	// click on button
	public void clickOn(By element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		driver.findElement(element).click();
	}

	// ***************************************TextFields**********************************************
	// put text in field after clearing it
	public void textSet(By element, String text) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		driver.findElement(element).clear();
		driver.findElement(element).sendKeys(text);
	}

	// Get text from element
	public String textGet(By element) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		return driver.findElement(element).getText();
	}

	// **************************************WindowClosure***************************************
	public void closeCurrentWindow() {
		driver.close();
	}

	public void closeAllWindows() {
		driver.quit();
	}

	// ******************************************Cookies*****************************************
	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	// ***************************************CheckElements**************************************
	public boolean isDisplayed(By element) {
		boolean flag;
		if (driver.findElement(element).isDisplayed()) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public boolean isNotExist(By element) {
		boolean flag;
		if (driver.findElements(element).isEmpty()) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public boolean checkElementType(By element,String type)
	{
		boolean flag = false;
		if (driver.findElement(element).getAttribute("type").equals(type)){
			flag = true;
		}else {
			flag = false;
		}
		return flag;
	}
	// ************************************Screenshots***************************************
	public void takeScreenShot(String TestMethodName, WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String time = java.time.LocalTime.now().toString().replace(":", "-").substring(0, 5);
		String date = java.time.LocalDate.now().toString();
		String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + date + "_" + time + "\\"
				+ TestMethodName + "_" + ThreadLocalRandom.current().nextInt() + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
