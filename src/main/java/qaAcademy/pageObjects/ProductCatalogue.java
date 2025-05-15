package qaAcademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import qaAcademy.abstractComponents.abstractComponents;

public class ProductCatalogue extends abstractComponents {

	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By AddtoCartBtn=By.cssSelector("button:last-of-type");
	
	public List<WebElement> getProducts() {
		visibilityOfElements(By.cssSelector(".mb-3"));
		return products;
	}

	public WebElement findProduct(String productname) {
		List<WebElement> products=getProducts();
		WebElement prod=products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productname))
				.findFirst().orElse(null);
		return prod;
	}
	
	public CartPage AddtoCart(String product) throws InterruptedException {
		WebElement prod=findProduct(product);
		prod.findElement(AddtoCartBtn).click();
		visibilityOfElements(By.cssSelector("#toast-container"));
		invisibilityofElements(spinner);
		CartPage cartPage=new CartPage(driver);
		return cartPage;
		
	}

}
