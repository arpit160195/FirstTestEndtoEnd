package firstproject.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Configuration {
	
	public WebDriver driver;
	public Properties prop;
	public static Logger log;
	
	public WebDriver browserInitialize() throws IOException {
		log = LogManager.getLogger(this.getClass().getName());
		prop = getProperties();
//		String browser = prop.getProperty("Browser");
		String browser = System.getProperty("Browser");
		if(browser.toUpperCase().equals("CHROMEHEADLESS")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			driver = new ChromeDriver(options);
		}if(browser.toUpperCase().equals("CHROME")) {
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
	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot tscr = (TakesScreenshot) driver;
		File source = tscr.getScreenshotAs(OutputType.FILE);
		String destinationFolder = System.getProperty("user.dir")+ "\\reports\\screenshots\\" + testCaseName + ".png";
		FileUtils.forceMkdir(new File(destinationFolder));
		FileUtils.copyFileToDirectory(source,new File(destinationFolder));
		return destinationFolder;
	}
	
}
