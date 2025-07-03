package priyaseleniumlearning.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import priyaseleniumlearning.pageobjects.CartPage;
import priyaseleniumlearning.pageobjects.CheckoutPage;
import priyaseleniumlearning.pageobjects.ConfirmationPage;
import priyaseleniumlearning.pageobjects.LandingPage;
import priyaseleniumlearning.pageobjects.OrdersPage;
import priyaseleniumlearning.pageobjects.ProductCatalogue;
import priyaseleniumlearning.testcomponents.BaseTest;
import priyaseleniumlearning.testcomponents.Retry;

public class SubmitOrderTest extends BaseTest {
	String prdctSelctd = "ZARA COAT 3" ;

	@Test(dataProvider = "getData", groups = {"Purchase"},retryAnalyzer = Retry.class)
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		//ProductCatalogue productCatalogue = new ProductCatalogue(driver); Instead of this in landing page last step create object directly
		List<WebElement> products = productCatalogue.getProductLst();
		//productCatalogue.productsByName(prdctSelctd);
		productCatalogue.addToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
	
		//CartPage cartPage = new CartPage(driver);
		//List<WebElement> CartPageList = cartPage.CartPageList(prdctSelctd);
		Boolean productInCartMatch =cartPage.CartPageListMatch(input.get("product"));
		Assert.assertTrue(productInCartMatch, "Product not found in cart!");
	//	Assert.assertFalse(productInCartMatch);
		
		//Checkout page
		CheckoutPage checkoutPage = cartPage.CheckOut();
		checkoutPage.selctCountry("ind");
		ConfirmationPage confirmationPage = checkoutPage.placeOrder();
		
		String confirmMsg = confirmationPage.verifyConfirmationMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("Order Placed Successfully"));
		} 

		@Test (dependsOnMethods = "submitOrder")
		public void orderHistory() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("rahini@gmail.com", "Password2!");	
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		ordersPage.productsInOrders(prdctSelctd);
		Assert.assertTrue(ordersPage.verifyProductsInOrdersList(prdctSelctd));
		}
		
		
		@DataProvider
		public Object[][] getData() throws IOException {
			
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "rahini@gmail.com");
//		map.put("password", "Password2!");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "best@gmail.com");
//		map1.put("password", "Password1!");
//		map1.put("product", "ADIDAS ORIGINAL");
		
			List<HashMap<String,String>> data = getJsonDataTomap(System.getProperty("user.dir")
				+"//src//test//java//priyaseleniumlearning//testdata//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	//	return new Object[][] {{"rahini@gmail.com","Password2!","ZARA COAT 3"},{"best@gmail.com","Password1!","ADIDAS ORIGINAL"}};
		}
		}
		

