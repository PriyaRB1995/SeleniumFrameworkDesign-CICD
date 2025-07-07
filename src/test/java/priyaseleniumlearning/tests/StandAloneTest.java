package priyaseleniumlearning.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import priyaseleniumlearning.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String prdctSelctd = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.manage().window().maximize();
		
		//Checking for CICD
        //Browser setup
		driver.get("https://rahulshettyacademy.com/client/");
		//Login setup
		driver.findElement(By.id("userEmail")).sendKeys("rahini@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password2!");
		driver.findElement(By.id("login")).click();
		//products
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));
		System.out.println("All products loaded!");
		List<WebElement> products = driver.findElements(By.cssSelector(".card-body"));
		//selectproduct
		WebElement productsByName = products.stream()
				.filter(p -> p.getText().contains("ZARA COAT 3"))
				.findFirst().orElse(null);
		//Addtocartbutton
		wait.until(ExpectedConditions.elementToBeClickable(productsByName.findElement(By.cssSelector(".btn.w-10.rounded")))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		System.out.println("Product added to cart, navigating to cart...");
		//click on cart
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement cart = driver.findElement(By.cssSelector("[routerlink*='cart']"));
		//js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});",cart);
		//WebElement cart= wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink*='cart']")));
		js.executeScript("arguments[0].scrollIntoView(true);", cart);
		js.executeScript("arguments[0].click()", cart);
		//cart.click();
		
		//-----check for product in cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='My Cart']")));
		List<WebElement> cartPageItems = driver.findElements(By.cssSelector(".cartSection h3"));
		System.out.println("Cart items:");
		boolean productInCart = cartPageItems.stream().anyMatch(c -> c.getText().equalsIgnoreCase(prdctSelctd));
		System.out.println("Product added: " + productInCart);
		Assert.assertTrue(productInCart, "Product not found in cart!");
		// click on checkout button
		JavascriptExecutor js1 = (JavascriptExecutor)driver;
		WebElement checkoutBtn = driver.findElement(By.cssSelector(".totalRow button"));
		js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});",checkoutBtn);
		Thread.sleep(1000);
		// Click using JavaScript to bypass any interception
		js.executeScript("arguments[0].click()", checkoutBtn);
		
		//send keys for region
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector(".input.txt.text-validated:nth-child(1)")),"india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ng-star-inserted:nth-child(3)")));
		driver.findElement(By.cssSelector(".ng-star-inserted:nth-child(3)")).click();
	//submit
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		//getting text of success message
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-success")));
		String txt = driver.findElement(By.cssSelector(".toast-success")).getText();
		System.out.println(txt);
		Assert.assertTrue(txt.equalsIgnoreCase("Order Placed Successfully"));
		
		driver.close();
		} 


		
}
