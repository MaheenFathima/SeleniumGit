package qaAcademy.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qaAcademy.pageObjects.OrdersPage;

public class abstractComponents {
	WebDriver driver;
	public abstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cart;
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersPage;
	

	public void visibilityOfElements(By productsBy) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(productsBy));
	}
	public void visibilityOfWebElements(WebElement ele) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void invisibilityofElements(WebElement element) throws InterruptedException {
		Thread.sleep(1000);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	
	public void goToCart() {
		cart.click();
	}
	public OrdersPage goToOrders() {
		ordersPage.click();
		OrdersPage ordersPage=new OrdersPage(driver);
		return ordersPage;
	}

}
//6a56cc7083f34100b85b68523825b9be