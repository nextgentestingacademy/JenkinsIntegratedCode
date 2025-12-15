package base;

import java.time.Duration;
import utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver;
	public static String browser, url;
	public static int timeout;
	protected boolean headless;
	
	@BeforeSuite(alwaysRun = true)
	public void configureDriver() {
		ConfigReader.loadProperties();
		browser = System.getProperty("browser", "chrome");
		String env = System.getProperty("env", "QA");
		headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
		
	    url = ConfigReader.get(env + ".url");
		timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}
	
	@BeforeMethod(alwaysRun = true)
	public void launchApp() {
		switch(browser.trim().toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
            if (headless) {
                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--disable-gpu");
            }
            driver = new ChromeDriver(chromeOptions);
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
            if (headless) {
                edgeOptions.addArguments("--headless=new");
                edgeOptions.addArguments("--disable-gpu");
            }
            driver = new EdgeDriver(edgeOptions);
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			EdgeOptions fireOptions = new EdgeOptions();
            if (headless) {
                fireOptions.addArguments("--headless=new");
                fireOptions.addArguments("--disable-gpu");
            }
            driver = new EdgeDriver(fireOptions);
			break;
		default:
			WebDriverManager.chromedriver().setup();
			ChromeOptions defOptions = new ChromeOptions();
            if (headless) {
            	defOptions.addArguments("--headless=new");
            	defOptions.addArguments("--disable-gpu");
            }
            driver = new ChromeDriver(defOptions);
		}
		
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
