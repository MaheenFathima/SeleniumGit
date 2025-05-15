package qaAcademy.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qaAcademy.abstractComponents.abstractComponents;

public class OrdersPage extends abstractComponents {
	WebDriver driver;
	public OrdersPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orders;

	public List<WebElement> getOrders() {
		driver.close();
		return orders;
	}

	public boolean verifyOrder(String product) {
		boolean orderMatch = orders.stream().anyMatch(o -> o.getText().equalsIgnoreCase(product));
		return orderMatch;
	}

}
