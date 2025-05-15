package qaAcadaemy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import qaAcadaemy.testComponents.BaseTest;

import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import qaAcademy.pageObjects.OrdersPage;
import qaAcademy.pageObjects.ProductCatalogue;

public class ecommerceOrderTest extends BaseTest {

	@Test(dataProvider="getData",groups= {"SubmitOrder"})
	public void placeOrder(HashMap<String,String> input) throws IOException, InterruptedException {

		String country = "India";

		ProductCatalogue productCatalogue = landingPage.Login(input.get("email"),input.get("password"));

		CartPage cartPage = productCatalogue.AddtoCart(input.get("product"));

		boolean productFound = cartPage.ProductCheck(input.get("product"));
		Assert.assertTrue(productFound);
		CheckoutPage checkOut = cartPage.CartCheckOut();

		checkOut.SubmitOrder(country);
		Assert.assertTrue(checkOut.getConfirmationMessage().equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dependsOnMethods = { "placeOrder" })
	public void OrderHistoryTest() {
		String productname = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue = landingPage.Login("mahin@example.com", "Mahin@1116");
		OrdersPage ordersPage = productCatalogue.goToOrders();
		Assert.assertTrue(ordersPage.verifyOrder(productname));

	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data=getJsonDatatoMap(System.getProperty("user.dir")+"//src//test//java//qaAcademy//testData//testdata.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};

	}
	
//	HashMap<String,String> map=new HashMap<String,String>();
//	map.put("email", "mahin@example.com");
//	map.put("password", "Mahin@1116");
//	map.put("product", "ADIDAS ORIGINAL");
//	
//	HashMap<String,String> map1=new HashMap<String,String>();
//	map1.put("email", "maheen@example.com");
//	map1.put("password", "Mahin@1116");
//	map1.put("product", "ZARA COAT 3");
//	return new Object[][] {{map},{map1}};
	
	
	
//	@DataProvider
//	public Object[][] getData() {
//		Object obj[][]= {
//				{"mahin@example.com","Mahin@1116","ADIDAS ORIGINAL"},
//				{"maheen@example.com","Mahin@1116","ZARA COAT 3"}
//		};
//		return obj;
//	}

}
