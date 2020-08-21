package firstproject.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import firstproject.pages.HomePage;
import fistproject.config.Configuration;

public class ValidateHomePageNavigationBar extends Configuration {
	public WebDriver driver;
	
	@BeforeTest
	public void beforeTest() throws IOException {
		driver = browserInitialize();
		driver.get(prop.getProperty("HomeURL"));
	}
	
	@Test
	public void validateTitle() {		
		HomePage home = new HomePage(driver);
		Assert.assertTrue(home.homeNavBar().isDisplayed());
	}
	
	@AfterTest
	public void afterTest() {
		driver.close();
	}
	
}
