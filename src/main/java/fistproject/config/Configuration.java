package fistproject.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Configuration {
	
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver browserInitialize() throws IOException {
		prop = getProperties();
		String browser = prop.getProperty("Browser");
		if(browser.toUpperCase().equals("CHROME")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browser.toUpperCase().equals("FIREFOX")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\geckodriver.exe");
			driver = new FirefoxDriver();
		}else if(browser.toUpperCase().equals("IE")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("GlobalTimeOut")), TimeUnit.SECONDS);
		return driver;
	}
	
	public Properties getProperties() throws IOException {
		prop = new Properties();
		FileInputStream fin = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\data.properties");
		prop.load(fin);
		return prop;
	}
}
