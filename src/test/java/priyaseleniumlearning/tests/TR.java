package priyaseleniumlearning.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import priyaseleniumlearning.pageobjects.CartPage;
import priyaseleniumlearning.pageobjects.CheckoutPage;
import priyaseleniumlearning.pageobjects.ConfirmationPage;
import priyaseleniumlearning.pageobjects.LandingPage;
import priyaseleniumlearning.pageobjects.OrdersPage;
import priyaseleniumlearning.pageobjects.ProductCatalogue;
import priyaseleniumlearning.testcomponents.BaseTest;
import priyaseleniumlearning.testcomponents.Retry;

public class TR {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://legal.thomsonreuters.com/en");
		driver.findElement(By.id("mega-menu-l2-nav-legal")).click();
		WebElement contextMenu = driver.findElement(By.xpath("//div[@class='bb-Megamenu-container is-open']//div[@class='bb-Megamenu-segmentArea'][1]"));
		contextMenu.findElement(By.cssSelector("[href*='generative']")).click();
		
	}
	}

		

