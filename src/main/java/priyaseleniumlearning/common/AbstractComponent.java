package priyaseleniumlearning.common;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import priyaseleniumlearning.pageobjects.CartPage;
import priyaseleniumlearning.pageobjects.OrdersPage;
import priyaseleniumlearning.utilities.JavaScriptUtil;

public class AbstractComponent {

	WebDriver driver; 
	WebDriverWait wait;
	JavaScriptUtil js;

	@FindBy(css="[routerlink*=myorders]")
	WebElement OrdersBtn;
	By Cart = By.cssSelector("[routerlink*='cart']");

	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		js= new JavaScriptUtil(driver);
		// TODO Auto-generated constructor stub
	}

	public void waitForElementToBeVisible(By cartBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(cartBy));			
	} 

	public void waitForElementToBeVisibleWebElement(WebElement ele) {
		wait.until(ExpectedConditions.visibilityOf(ele));			
	} 

	public void waitForAddElementToBeClickable(By cartBy) {
		wait.until(ExpectedConditions.elementToBeClickable(cartBy));		
	}

	public void waitForAddElementToBeClickableElement(WebElement ele) {
		wait.until(ExpectedConditions.elementToBeClickable(ele));		
	}

	public void waitForInvisibilityOfElement(WebElement ele) {
		wait.until(ExpectedConditions.invisibilityOf(ele));	
	}

	public void waitForInvisibilityOfElementBy(By cartBy) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(cartBy));	
	}

	public CartPage goToCartPage() throws InterruptedException {
		waitForElementToBeVisible(Cart);
		waitForAddElementToBeClickable(Cart);
		js.javaScriptScroll(Cart);
		js.javaScriptClick(Cart);
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

	public OrdersPage goToOrdersPage() {
		waitForElementToBeVisibleWebElement(OrdersBtn);
		OrdersBtn.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}
}

