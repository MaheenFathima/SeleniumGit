package qaAcademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qaAcademy.abstractComponents.abstractComponents;

public class CheckoutPage extends abstractComponents{

	WebDriver driver;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement SelectCountry;
	
	@FindBy(css=".ta-results button")
	List<WebElement> options;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(css=".hero-primary")
	WebElement message;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void SelectCountry(String userCountry) {
		String entry=userCountry.substring(0, 3);
		SelectCountry.sendKeys(entry);
		options.stream().filter(opt -> opt.getText().equals(userCountry)).findFirst().orElse(null).click();
		
	}
	public void SubmitOrder(String country) {
		SelectCountry(country);
		submit.click();
	}
	public String getConfirmationMessage() {
		return message.getText();
	
	}
}
