package qaAcademy.StepDefinitions;

import java.io.IOException;

import org.testng.Assert;
import org.testng.AssertJUnit;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import qaAcadaemy.testComponents.BaseTest;
import qaAcademy.pageObjects.CartPage;
import qaAcademy.pageObjects.CheckoutPage;
import qaAcademy.pageObjects.LandingPage;
import qaAcademy.pageObjects.ProductCatalogue;

public class Implementation extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public CheckoutPage checkOut;
	public String country="India";
	
	@Given("Land on the page")
	public void Land_On_Page() throws IOException {
		landingPage=LaunchApplication();
	}
	
	@Given("^Login to the site with (.+) and (.+)$")
	public void Login_to_Site(String username, String password) {
		productCatalogue = landingPage.Login(username,password);
	}
	
	@When("^Add product (.+) to cart$")
	public void Add_Product_toCart(String product) throws InterruptedException {
		cartPage = productCatalogue.AddtoCart(product);
	}
	
	//(.+) is used to make it generic
	@And("^CheckOut (.+) and submit the order$")
	public void CheckOut_and_Submit(String product) {
		boolean productFound = cartPage.ProductCheck(product);
		Assert.assertTrue(productFound);
		checkOut = cartPage.CartCheckOut();
		checkOut.SubmitOrder(country);
	}
	
	@Then("Verify the successful message {string} on confirmation page")
	public void Confirmation_Page(String message) {
		Assert.assertTrue(checkOut.getConfirmationMessage().equalsIgnoreCase(message));
		driver.close();
	}
	
	@Then("{string} appears")
	public void Incorrect_MsgAppears(String msg) {
		AssertJUnit.assertEquals(landingPage.loginErrorMsg(), msg);
		driver.close();
	}

}
