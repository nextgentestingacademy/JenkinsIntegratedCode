package base;

import java.time.Duration;
import utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver;
	public static String browser, url;
	public static int timeout;
	
	@BeforeSuite(alwaysRun = true)
	public void configureDriver() {
		ConfigReader.loadProperties();
		browser = System.getProperty("browser", "chrome");
		String env = System.getProperty("env", "QA");
		System.out.println("===== JENKINS PARAM DEBUG =====");
	    System.out.println("Browser from Jenkins: " + browser);
	    System.out.println("Env from Jenkins: " + env);

	    url = ConfigReader.get(env + ".url");

	    System.out.println("Resolved URL: " + url);
	    System.out.println("================================");

		timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}
	
	@BeforeMethod(alwaysRun = true)
	public void launchApp() {
		switch(browser.trim().toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
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
