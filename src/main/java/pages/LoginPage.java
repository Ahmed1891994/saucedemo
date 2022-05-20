package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import base.DriverActions;
import utilities.ExcelUtil;

public class LoginPage {
	// driverActions
	DriverActions driverActions;
	ExcelUtil exilreader;
	
	// text fields
	By username_txtfld_signin = By.id("user-name");
	By password_txtfld_signin = By.id("password");

	// buttons
	By login_btn_signin = By.id("login-button");

	//Errors
	By wrongcredintial = By.className("error-message-container");
	
	// constructor
	public LoginPage() {
		driverActions = new DriverActions();
		exilreader = new ExcelUtil();
	}

	public void enterUsername(String Username) {
		if(Username!=null)
			driverActions.textSet(username_txtfld_signin, Username);
	}

	public void enterPassword(String password) {
		if(password!=null)
			driverActions.textSet(password_txtfld_signin, password);
	}

	public void clickLogInBtn() {
		driverActions.clickOn(login_btn_signin);
	}
	
	public void logInUsingEnterKeyboard() {
		driverActions.keyboardPress(Keys.ENTER);
	}
	
	public void reloadLoginPage() {
		driverActions.reloadPage();
	}
	
	public String checkWrongCredintialMessage()
	{
		return driverActions.textGet(wrongcredintial);
	}
	
	public boolean checkIFLoginCredTextFieldEmpty()
	{
		return driverActions.textGet(username_txtfld_signin).isEmpty();
	}
	
}
