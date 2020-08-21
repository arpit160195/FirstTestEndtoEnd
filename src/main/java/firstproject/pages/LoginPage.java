package firstproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	public WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By email = By.id("user_email");
	By pwd = By.id("user_password");
	By login = By.cssSelector("input[value='Log In']");
	
	public WebElement getEmail() {
		return driver.findElement(email);
	}
	public WebElement getPwd() {
		return driver.findElement(pwd);
	}
	public WebElement getLogin() {
		return driver.findElement(login);
	}
	
}
