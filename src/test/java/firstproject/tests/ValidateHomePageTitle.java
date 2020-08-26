package firstproject.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import firstproject.config.Configuration;
import firstproject.pages.HomePage;

public class ValidateHomePageTitle extends Configuration {
	public WebDriver driver;
	
	@BeforeTest
	public void beforeTest() throws IOException {
		driver = browserInitialize();
		log.info("Browser is initialised");
		driver.get(prop.getProperty("HomeURL"));
		log.info("Navigated to Home Page");
	}
	
	@Test
	public void validateHomePageTitle() {
		HomePage home = new HomePage(driver);
		Assert.assertEquals(home.homeTitle().getText(), "FEATURED COURSES");
		log.info("Checked title on the page");
	}
	
	@AfterTest
	public void afterTest() {
		driver.close();
		log.info("Browser is closed");
	}
	
}
