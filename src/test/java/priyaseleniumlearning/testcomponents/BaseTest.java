package priyaseleniumlearning.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import priyaseleniumlearning.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	public Properties prop;
	WebDriverWait wait;

	//properties class
	public WebDriver initializeDriver() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/priyaseleniumlearning/resources/GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!= null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");
		if (browserName.contains("chrome"))	 {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if (browserName.contains("headless")) {
			options.addArguments("headless");
			};			
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
			
			
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		}else if (browserName.equalsIgnoreCase("edge")) {
			//edge
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return driver;		
	}

	public void InvalidCredentials() throws IOException {
		Properties prop1 = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/priyaseleniumlearning/resources/GlobalData.properties");
		prop1.load(fis);	
		prop1.getProperty("invalid.email");
		prop1.getProperty("invalid.password");
	}
	
	public List<HashMap<String, String>> getJsonDataTomap(String filepath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);	
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});
		return data;
	}
	
	public String getScreenshot(String testName, WebDriver driver) throws IOException {
	TakesScreenshot ts = (TakesScreenshot) driver;
	File src = ts.getScreenshotAs(OutputType.FILE);
	File file = new File(System.getProperty("user.dir")+"//reports//" + testName + ".png");
	FileUtils.copyFile(src,file);
	return System.getProperty("user.dir")+"//reports//" + testName + ".png";
	}
			
		@BeforeMethod(alwaysRun = true)
		public LandingPage launchApplication() throws IOException {
			driver = initializeDriver();
			if(driver!=null) {
			landingPage = new LandingPage(driver);
			landingPage.launch();	
			return landingPage;
		}
	    return null;
		}

		@AfterMethod(alwaysRun = true)
		public void tearDown() {
			driver.close();
		}

	}

