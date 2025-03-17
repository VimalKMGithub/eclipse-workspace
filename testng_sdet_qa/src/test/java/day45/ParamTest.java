package day45;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParamTest {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	@Parameters({ "browser", "url" })
	public void open(String br, String url) {
		switch (br.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		default:
			Assert.fail();
			break;
		}
		driver.get(url);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test
	public void testLogo() {
		boolean isLogoPresent = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='company-branding']")))
				.isDisplayed();
		Assert.assertTrue(isLogoPresent);
	}

	@Test
	public void tsetTitle() {
		Assert.assertEquals(driver.getTitle(), "OrangeHRM");
	}

	@Test
	public void testUrl() {
		String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		Assert.assertEquals(driver.getCurrentUrl(), url);
	}

	@AfterClass
	public void close() {
		driver.quit();
	}
}
