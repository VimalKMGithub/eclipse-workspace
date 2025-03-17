package day42;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class OrangeHrmTest {
	WebDriver driver;

	public void setUpDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--disable-gpu");
		options.addArguments("--window-size=1920x1080");
		driver = new ChromeDriver(options);
	}

	@Test(priority = 1)
	public void open() {
		setUpDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
	}

	@Test(priority = 2)
	public void verifyLogo() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		boolean isPresent = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='company-branding']")))
				.isDisplayed();
		System.out.println("Logo: " + isPresent);
	}

	@Test(priority = 3)
	public void login() {
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
	}

	@Test(priority = 4)
	public void quit() {
		driver.quit();
	}
}
