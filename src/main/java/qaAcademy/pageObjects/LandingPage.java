package qaAcademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qaAcademy.abstractComponents.abstractComponents;

public class LandingPage extends abstractComponents {

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userMail;
	
	@FindBy(id="userPassword")
	WebElement passwordElement;
	
	@FindBy(id="login")
	WebElement LoginBtn;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public ProductCatalogue Login(String mailId,String password) {
		userMail.sendKeys(mailId);
		passwordElement.sendKeys(password);
		LoginBtn.click();
		ProductCatalogue productCatalogue=new ProductCatalogue(driver);
		return productCatalogue;
	}
	public String loginErrorMsg() {
		visibilityOfWebElements(errorMsg);
		return errorMsg.getText();
	}

}
