package tests;

import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;
import utilities.ExcelUtil;
import utilities.PropertiesUtil;

@Listeners(utilities.Listeners.class)

public class LoginPageTest extends BasePage{
	PropertiesUtil propUtil;
	Properties prop;
	
	
	
	@Test(dataProvider = "validlogincredintial" , description = "User enters valid credintial and clicks on login btn to enter homepage")
	public void validLogin(String user,String pass) {
		LoginPage loginpage = new LoginPage();
		loginpage.enterUsername(user);
		loginpage.enterPassword(pass);
		loginpage.clickLogInBtn();
		HomePage homepage = new HomePage();
		Assert.assertTrue(homepage.checkHomePageAppears());
	}
	
	@Test(dataProvider = "validlogincredintial" , description = "User enters valid credintial and Presses Enter Keyboard to enter homepage")
	public void loginKeyEnter(String user,String pass) {
		LoginPage loginpage = new LoginPage();
		loginpage.enterUsername(user);
		loginpage.enterPassword(pass);
		loginpage.logInUsingEnterKeyboard();
		HomePage homepage = new HomePage();
		Assert.assertTrue(homepage.checkHomePageAppears());
	}
	
	@Test(dataProvider = "invalidlogincredintial" , description = "User enters Invalid credintial and clicks on login btn then checking error message appeared")
	public void loginWithWrongCredintial(String user,String pass,String expectedmessage) {
		LoginPage loginpage = new LoginPage();
		loginpage.enterUsername(user);
		loginpage.enterPassword(pass);
		loginpage.clickLogInBtn();
		Assert.assertEquals(loginpage.checkWrongCredintialMessage(),expectedmessage);
	}
	
	@Test(dataProvider = "validlogincredintial" , description = "User enters valid credintial then refresh page to delete data in all textfield")
	public void RefreshPageAfterEnteringData(String user,String pass) {
		LoginPage loginpage = new LoginPage();
		loginpage.enterUsername(user);
		loginpage.enterPassword(pass);
		loginpage.reloadLoginPage();
		Assert.assertTrue(loginpage.checkIFLoginCredTextFieldEmpty());
	}
	
	@Test(description = "Check password written is masked")
	public void isPasswordMasked() {
		LoginPage loginpage = new LoginPage();
		loginpage.enterPassword("pass");
		Assert.assertTrue(loginpage.checkPasswordIsMasked());
	}
	
	@Test(description = "Check password written is not copied")
	public void checkPasswordNotCopied() {
		LoginPage loginpage = new LoginPage();
		loginpage.enterPassword("pass");
		loginpage.checkPasswordCantCopied();
		Assert.assertEquals(loginpage.checkUsername(),"");
	}
	
	/*@Test(dataProvider = "invalidlogincredintial" , description = "User enters valid credintial then refresh page to delete data in all textfield")
	public void unsuccessfulAttemptLoginLimitation(String user,String pass,String errormessage) {
		LoginPage loginpage = new LoginPage();
		for(int index = 0 ; index < 4 ; index++)
		{
			loginpage.enterUsername(user+"x");
			loginpage.enterPassword(pass);
			loginpage.clickLogInBtn();
			Assert.assertEquals(loginpage.checkWrongCredintialMessage(),errormessage);
			if(index == 3)
				Assert.assertEquals(loginpage.checkWrongCredintialMessage(),"limited attemp");
		}
		
	}*/
	
	@DataProvider(name="invalidlogincredintial")
	public Object[][] invalidLoginCredintials() throws IOException
	{
		propUtil = new PropertiesUtil();
		try {
			prop = propUtil.loadProperties("settings");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String USERDATAEXCELPATH = prop.getProperty("USERDATAEXCEL");
		String USERDATAEXCELSHEET = prop.getProperty("INVALIDLOGINSHEET");
		ExcelUtil excel = new ExcelUtil();
		return excel.getExcelData(USERDATAEXCELPATH,USERDATAEXCELSHEET);
	}
	
	@DataProvider(name="validlogincredintial")
	public Object[][] validLoginCredintials() throws IOException
	{
		propUtil = new PropertiesUtil();
		try {
			prop = propUtil.loadProperties("settings");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String USERDATAEXCELPATH = prop.getProperty("USERDATAEXCEL");
		String USERDATAEXCELSHEET = prop.getProperty("VALIDLOGINSHEET");
		ExcelUtil excel = new ExcelUtil();
		return excel.getExcelData(USERDATAEXCELPATH,USERDATAEXCELSHEET);
	}

}
