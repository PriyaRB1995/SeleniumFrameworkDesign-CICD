package priyaseleniumlearning.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import priyaseleniumlearning.common.AbstractComponent;

public class OrdersPage extends AbstractComponent{
	
		WebDriver driver;
		
		@FindBy(css="tr td:nth-child(3)")
		List<WebElement> productsInOrderPage;
		
		By OrdersTxt = By.tagName("h1");
	

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	public List<WebElement> productsInOrders(String prdctSelctd) {
		waitForElementToBeVisible(OrdersTxt);	
	return productsInOrderPage;
	}
	
	public Boolean verifyProductsInOrdersList(String prdctSelctd) {
	Boolean orderPage = productsInOrders(prdctSelctd).stream().anyMatch(c->c.getText().equalsIgnoreCase(prdctSelctd));
		return orderPage;
	}
	
	
}
