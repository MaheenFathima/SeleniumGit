package qaAcadaemy.testComponents;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.manager.*;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import qaAcademy.pageObjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;

	@Test
	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//qaAcademy//resources//GlobalData.properties");
		prop.load(fis);
		String webbrowser = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		if (webbrowser.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if (webbrowser.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
		} else if (webbrowser.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> getJsonDatatoMap(String filepath) throws IOException {
		String jsonData = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonData,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage LaunchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ss = (TakesScreenshot) driver;
		File src = ss.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "/reports/" + testCaseName + ".png");
		FileUtils.copyFile(src, dest);
		return dest.getAbsolutePath();
	}

	@AfterMethod(alwaysRun = true)
	public void closeDriver() {
		driver.close();
	}

}
