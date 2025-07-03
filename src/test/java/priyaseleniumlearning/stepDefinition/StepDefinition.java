package priyaseleniumlearning.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import priyaseleniumlearning.pageobjects.CartPage;
import priyaseleniumlearning.pageobjects.CheckoutPage;
import priyaseleniumlearning.pageobjects.ConfirmationPage;
import priyaseleniumlearning.pageobjects.LandingPage;
import priyaseleniumlearning.pageobjects.ProductCatalogue;
import priyaseleniumlearning.testcomponents.BaseTest;

public class StepDefinition extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public ConfirmationPage confirmationPage ;
	
	public StepDefinition() {
	}

	@Given("User is landed on Ecommerce login page")
	public void user_is_landed_on_Ecommerce_login_page() throws IOException {
		landingPage = launchApplication();
	}

	@When("^user is logged in with (.+) and (.+)$")
	public void user_is_logged_in_with_valid_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username,password);	
	}

	@When("^user adds the (.+) to the cart$")
	public void user_adds_the_product_to_the_cart(String product) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductLst();
		productCatalogue.addToCart(product);	
	}

	@When("^checkout the (.+) and submit the order$")
	public void checkout_the_product_and_submit_the_order(String product) throws InterruptedException {
		cartPage = productCatalogue.goToCartPage();
		Boolean productInCartMatch =cartPage.CartPageListMatch(product);
		Assert.assertTrue(productInCartMatch, "Product not found in cart!");
		CheckoutPage checkoutPage = cartPage.CheckOut();
		checkoutPage.selctCountry("ind");
		confirmationPage = checkoutPage.placeOrder();	
	}

	@Then("^\"([^\"]*)\" message is displayed in confirmation page$")
	public void message_is_displayed_in_confirmation_page(String message) {
		String confirmMsg = confirmationPage.verifyConfirmationMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(message));
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" error message is displayed in confirmation page$")
	public void error_message_is_displayed_in_confirmation_page(String errorMsg) {
	Assert.assertEquals(landingPage.getErrorMessage(),errorMsg);
	driver.close();
	}
}
