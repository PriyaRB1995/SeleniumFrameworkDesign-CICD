package priyaseleniumlearning.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import priyaseleniumlearning.common.AbstractComponent;
import priyaseleniumlearning.utilities.JavaScriptUtil;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;
	JavaScriptUtil js;

	By countryInDrpdwn = By.cssSelector(".ng-star-inserted:nth-child(3)");
	@FindBy(css=".ng-star-inserted:nth-child(3)")
	WebElement countrySelctnFrmDrpDwn;
	@FindBy(css=".action__submit")
	WebElement placeOrderBtn;
	@FindBy(css=".input.txt.text-validated:nth-child(1)")
	WebElement countryField;
	@FindBy(css=".toast-success")
	WebElement successTxt;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js= new JavaScriptUtil(driver);
	}

	public void selctCountry(String countryName) throws InterruptedException {
		waitForElementToBeVisibleWebElement(countryField);
		//Actions a = new Actions(driver);
	//	a.sendKeys(countryField,countryName).build().perform();
		countryField.sendKeys(countryName); 
		waitForElementToBeVisible(countryInDrpdwn);
		js.javaScriptScroll(countrySelctnFrmDrpDwn);
		js.javaScriptClickWebElement(countrySelctnFrmDrpDwn);
	}
	
	public ConfirmationPage placeOrder() throws InterruptedException {
		//placeOrderBtn.click();;
		waitForAddElementToBeClickableElement(placeOrderBtn);
		 js.javaScriptScroll(placeOrderBtn);
		    js.javaScriptClickWebElement(placeOrderBtn);
		return new ConfirmationPage(driver);
	}
}
