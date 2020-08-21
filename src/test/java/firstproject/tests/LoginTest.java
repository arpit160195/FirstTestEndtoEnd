package firstproject.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import firstproject.pages.HomePage;
import firstproject.pages.LoginPage;
import fistproject.config.Configuration;
import fistproject.config.GlobalFunctionLibrary;

public class LoginTest extends Configuration {

	@BeforeTest
	public void beforeTest() throws IOException {
		driver = browserInitialize();
	}

	@Test(dataProvider = "getData")
	public void homePage(String emailId, String pwd) throws IOException {
		driver.get(prop.getProperty("HomeURL"));
		HomePage home = new HomePage(driver);
		home.loginLink().click();

		LoginPage login = new LoginPage(driver);
		login.getEmail().sendKeys(emailId);
		login.getPwd().sendKeys(pwd);
		login.getLogin().click();
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		GlobalFunctionLibrary globe = new GlobalFunctionLibrary();
		prop = getProperties();
		String loginTestWorkBook = prop.getProperty("LoginTestWorkBook");
		String loginTestWorkSheet = prop.getProperty("LoginTestWorkSheet");

		Object[][] data = globe.excelDataExtractAllRows(loginTestWorkBook, loginTestWorkSheet);

		return data;
	}

}
