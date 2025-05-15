package qaAcadaemy;

import org.testng.annotations.Test;

import qaAcadaemy.testComponents.Retry;
import qaAcadaemy.testComponents.BaseTest;

import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import qaAcademy.pageObjects.CartPage;
import qaAcademy.pageObjects.CheckoutPage;
import qaAcademy.pageObjects.LandingPage;
import qaAcademy.pageObjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	
	@Test(groups="ErrorHandling",retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {

		String productname = "ADIDAS ORIGINAL";
		String country = "India";

		ProductCatalogue productCatalogue = landingPage.Login("mahen@example.com", "Mahin@1116");

		AssertJUnit.assertEquals(landingPage.loginErrorMsg(), "Incorrect email or password.");

	}
	@Test
	public void ProductValidation() throws IOException, InterruptedException {

		String productname = "ADIDAS ORIGINAL";
		String country = "India";

		ProductCatalogue productCatalogue = landingPage.Login("mahin@example.com", "Mahin@1116");

		CartPage cartPage = productCatalogue.AddtoCart(productname);

		boolean productFound = cartPage.ProductCheck("Zara Coat");
		Assert.assertFalse(productFound);

	}

	

}
