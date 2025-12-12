package base;

import java.time.Duration;
import utils.ConfigReader;
import utils.EnvironmentManager;
import utils.JenkinsConfig;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	String browser, url;
	int timeout;
	
	@BeforeSuite(alwaysRun = true)
	public void configureDriver() {
		ConfigReader.loadProperties();
//		browser = ConfigReader.get("browser");
		browser = JenkinsConfig.getBrowser();
		
		switch(browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			break;
		default:
		}
	}
	
	@BeforeMethod(alwaysRun = true)
	public void launchApp() {
		switch(browser) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
		}
//		url=ConfigReader.get("url");
		url = EnvironmentManager.getBaseUrl();
		timeout = Integer.parseInt(ConfigReader.get("timeout"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
		driver.get(url);
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
	}
}
