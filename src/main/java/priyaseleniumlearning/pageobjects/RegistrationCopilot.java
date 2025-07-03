package priyaseleniumlearning.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import priyaseleniumlearning.common.AbstractComponent;

public class RegistrationCopilot extends AbstractComponent {

    WebDriver driver;

    @FindBy(id = "firstName")
    WebElement firstNameField;

    @FindBy(id = "lastName")
    WebElement lastNameField;

    @FindBy(id = "userEmail")
    WebElement emailField;

    @FindBy(id = "userPassword")
    WebElement passwordField;

    @FindBy(id = "confirmPassword")
    WebElement confirmPasswordField;

    @FindBy(id = "register")
    WebElement registerButton;

    @FindBy(css = "[class*='error-message']")
    WebElement errorMessage;

    public RegistrationCopilot(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void launchRegistrationPage() {
        driver.get("https://rahulshettyacademy.com/client/register");
    }

    public void fillRegistrationForm(String firstName, String lastName, String email, String password, String confirmPassword) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void submitRegistration() {
        registerButton.click();
    }

    public String getErrorMessage() {
        waitForElementToBeVisibleWebElement(errorMessage);
        return errorMessage.getText();
    }
}
