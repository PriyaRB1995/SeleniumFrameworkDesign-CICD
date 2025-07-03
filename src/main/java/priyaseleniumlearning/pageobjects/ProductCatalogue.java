package priyaseleniumlearning.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import priyaseleniumlearning.common.AbstractComponent;
import priyaseleniumlearning.utilities.JavaScriptUtil;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;
	
	//WebElement userName = driver.findElement(By.id("userEmail"));
	@FindBy(css=".card-body")
	List<WebElement> products;
	@FindBy(css=".ng-animating")
	WebElement spinner;
	@FindBy(css="div[class*='ng-trigger']")
	WebElement loginMsg;
	

	By productsBy = By.cssSelector(".card-body");
	By CartBy = By.cssSelector(".btn.w-10.rounded");
	By successTxt = By.cssSelector("div[class*='toast-message']");
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String SuccessMsg() {
		waitForElementToBeVisibleWebElement(loginMsg);
		return loginMsg.getText();
	
	}

	public List<WebElement> getProductLst() throws InterruptedException {
		waitForInvisibilityOfElement(loginMsg);
		waitForElementToBeVisible(productsBy);
		return products;
	}

	public WebElement productsByName(String prdctSelctd) throws InterruptedException {
		WebElement products = getProductLst().stream()
				.filter(p -> p.getText().contains(prdctSelctd)).findFirst().orElse(null);
		return products;
	}

	public void addToCart(String prdctName) throws InterruptedException {
		WebElement prod = productsByName(prdctName);
		waitForElementToBeVisible(CartBy);
		waitForAddElementToBeClickable(CartBy);
		prod.findElement(CartBy).click();
		waitForElementToBeVisible(successTxt);
		waitForInvisibilityOfElementBy(successTxt);
		waitForInvisibilityOfElement(spinner);		
	}



}



