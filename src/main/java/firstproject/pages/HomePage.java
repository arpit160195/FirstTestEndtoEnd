package firstproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage{
	
	public WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	By loginLink = By.cssSelector("a[href*='sign_in']");
	By homeTitle = By.cssSelector("div[class='text-center'] h2");
	By homeNavBar = By.cssSelector("ul[class='nav navbar-nav navbar-right']");
	
	public WebElement loginLink() {
		return driver.findElement(loginLink);		
	}
	public WebElement homeTitle() {
		return driver.findElement(homeTitle);
	}
	public WebElement homeNavBar() {
		return driver.findElement(homeNavBar);
	}
	
}
