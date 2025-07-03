package priyaseleniumlearning.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import priyaseleniumlearning.common.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//WebElement userName = driver.findElement(By.id("userEmail"));
	@FindBy(id="userEmail")
	WebElement userName;
	
	@FindBy(id="userPassword")
	WebElement passwrd;
	
	@FindBy(id="login")
	WebElement submitBtn;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement submitBtnError;
	
	public void launch() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrorMessage() {
		waitForElementToBeVisibleWebElement(submitBtnError);
		return submitBtnError.getText();
	}
	
	public ProductCatalogue loginApplication(String email, String password) {
		userName.sendKeys(email);
		passwrd.sendKeys(password);
		submitBtn.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	
}

