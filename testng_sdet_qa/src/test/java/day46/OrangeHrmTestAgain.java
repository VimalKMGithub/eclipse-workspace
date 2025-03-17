package day46;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(day46.OrangeTestListeners.class)
public class OrangeHrmTestAgain {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void open() {
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
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

	@Test(dependsOnMethods = { "testUrl" })
	public void tsetTitle() {
		Assert.assertEquals(driver.getTitle(), "OrangeHRM");
	}

	@Test
	public void testUrl() {
		String url = "toFail";
		Assert.assertEquals(driver.getCurrentUrl(), url);
	}

	@AfterClass
	public void close() {
		driver.quit();
	}
}
