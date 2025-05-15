package qaAcademy.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qaAcademy.abstractComponents.abstractComponents;

public class CartPage extends abstractComponents{

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	@FindBy(css=".cart h3")
	List<WebElement> cartProducts;
	@FindBy(css=".subtotal button")
	WebElement confirmBtn;
	
	public boolean ProductCheck(String productname) {
		goToCart();
		boolean match=cartProducts.stream().anyMatch(p->p.getText().equals(productname));
		return match;
	}
	public CheckoutPage CartCheckOut() {
		confirmBtn.click();
		CheckoutPage checkOut=new CheckoutPage(driver);
		return checkOut;
	}
	

}
