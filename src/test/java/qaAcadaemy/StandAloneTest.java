package qaAcadaemy;

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

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		
		String productname="ADIDAS ORIGINAL";
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		driver.findElement(By.id("userEmail")).sendKeys("mahin@example.com");
		driver.findElement(By.id("userPassword")).sendKeys("Mahin@1116");
		driver.findElement(By.id("login")).click();

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod=products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("ADIDAS ORIGINAL"))
				.findFirst().orElse(null);
		prod.findElement(By.cssSelector("button:last-of-type")).click();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("[routerlink*='cart']"))));
		
		Thread.sleep(1000);
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		

		boolean match=driver.findElements(By.cssSelector(".cart h3")).stream().anyMatch(p->p.getText().equals(productname));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".subtotal button")).click();
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("ind");
		String userCountry="India";
		
		List<WebElement> options=driver.findElements(By.cssSelector(".ta-results button"));
		WebElement reqCountry=options.stream().filter(opt->opt.getText().equals(userCountry)).findFirst().orElse(null);
		reqCountry.click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		String messageDisplayed=driver.findElement(By.cssSelector(".hero-primary")).getText();
		String message="Thankyou for the order.";
		System.out.println(messageDisplayed);
		Assert.assertTrue(messageDisplayed.equalsIgnoreCase(message));
		driver.close();
		
		
		
		
	}

}
