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
		log.info("Browser is initialised");
	}

	@Test(dataProvider = "getData")
	public void homePage(String emailId, String pwd) throws IOException {
		driver.get(prop.getProperty("HomeURL"));
		log.info("Navigated to Home Page");
		HomePage home = new HomePage(driver);
		home.loginLink().click();
		log.info("Clicked on Login Link");

		LoginPage login = new LoginPage(driver);
		login.getEmail().sendKeys(emailId);
		log.info("Entered Email");
		login.getPwd().sendKeys(pwd);
		log.info("Entered Password");
		login.getLogin().click();
		log.info("Clicked on login button");
	}

	@AfterTest
	public void afterTest() {
		driver.close();
		log.info("Browser is closed");
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		GlobalFunctionLibrary globe = new GlobalFunctionLibrary();
		prop = getProperties();
		String loginTestWorkBook = prop.getProperty("LoginTestWorkBook");
		String loginTestWorkSheet = prop.getProperty("LoginTestWorkSheet");

		Object[][] data = globe.excelDataExtractAllRows(loginTestWorkBook, loginTestWorkSheet);
		log.info("Excel data extracted");

		return data;
	}

}
