package priyaseleniumlearning.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	WebDriver driver;
	JavascriptExecutor js;

	public JavaScriptUtil(WebDriver driver) {
		this.driver=driver;
		js= (JavascriptExecutor)driver;
	}

	public void javaScriptScroll(By CartBtn) throws InterruptedException {
		WebElement element = driver.findElement(CartBtn);
		js.executeScript("arguments[0].scrollIntoView(true);",element);
	}

	public void javaScriptClick(By CartBtn) throws InterruptedException {
		WebElement element = driver.findElement(CartBtn);
		//js.executeScript("arguments[0].scrollIntoView(true);",element);
		js.executeScript("arguments[0].click()",element);
	}
	
	public void javaScriptClickWebElement(WebElement CartBtn) throws InterruptedException {
		//js.executeScript("arguments[0].scrollIntoView(true);",element);
		js.executeScript("arguments[0].click()",CartBtn);
	}

	public void javaScriptScroll(WebElement jsclick) throws InterruptedException {
		js.executeScript("arguments[0].scrollIntoView(true);",jsclick);	
	}

}
