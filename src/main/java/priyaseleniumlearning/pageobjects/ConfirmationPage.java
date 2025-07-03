package priyaseleniumlearning.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import priyaseleniumlearning.common.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{

	WebDriver driver;
	
	@FindBy(css=".toast-success")
	WebElement succesTxt;

	public ConfirmationPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}	
	
	public String verifyConfirmationMessage() {
		waitForElementToBeVisibleWebElement(succesTxt);
		return succesTxt.getText();
	}
	}

