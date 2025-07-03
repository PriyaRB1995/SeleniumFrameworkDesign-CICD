package priyaseleniumlearning.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import priyaseleniumlearning.pageobjects.CartPage;
import priyaseleniumlearning.pageobjects.ProductCatalogue;
import priyaseleniumlearning.testcomponents.BaseTest;
import priyaseleniumlearning.testcomponents.Retry;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void submitOrder() {
		String invalidEmail = prop.getProperty("invalid.email");
		String invalidPassword = prop.getProperty("invalid.password");
		landingPage.loginApplication(invalidEmail, invalidPassword);
		Assert.assertEquals(landingPage.getErrorMessage(),"Incorrect eail or password."); 
		System.out.println("Error Handling is successful");
	}
	
	@Test(retryAnalyzer = Retry.class)
	public void ErrorValidation() throws InterruptedException {
	String prdctSelctd = "ZARA COAT 3";
	ProductCatalogue productCatalogue = landingPage.loginApplication("rahini@gmail.com", "Password2!");	
	List<WebElement> products = productCatalogue.getProductLst();
	productCatalogue.addToCart(prdctSelctd);
	CartPage cartPage = productCatalogue.goToCartPage();
	Boolean productInCartMatch =cartPage.CartPageListMatch(prdctSelctd);
	Assert.assertTrue(productInCartMatch, "Product not found in cart!");
}
}

