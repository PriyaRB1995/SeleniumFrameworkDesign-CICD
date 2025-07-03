package priyaseleniumlearning.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import priyaseleniumlearning.common.AbstractComponent;
import priyaseleniumlearning.utilities.JavaScriptUtil;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;	
	JavaScriptUtil js;
	
	By cartPageLoad = By.xpath("//h1[text()='My Cart']");
	@FindBy(css=".cartSection h3")
	List<WebElement> cartPageItems;
	@FindBy(css=".totalRow button")
	WebElement ChckOutBtn;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		js= new JavaScriptUtil(driver);
	}

	public List<WebElement> CartPageList(String prdctSelctd) throws InterruptedException {
		waitForElementToBeVisible(cartPageLoad);
		return cartPageItems;
	}
	
	public Boolean CartPageListMatch(String prdctSelctd) throws InterruptedException {
		Thread.sleep(2000);
	Boolean productInCart = CartPageList(prdctSelctd).stream().anyMatch(c -> c.getText().equalsIgnoreCase(prdctSelctd));
	return productInCart;	
	} 	
	
	public CheckoutPage CheckOut() throws InterruptedException {
		js.javaScriptScroll(ChckOutBtn);
		js.javaScriptClickWebElement(ChckOutBtn);
		return new CheckoutPage(driver);

	}
}