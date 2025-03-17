package day45;

import java.time.Duration;
import java.time.LocalDateTime;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class DataProviderAnnotation {
	WebDriver driver;

	public void setUpDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--disable-gpu");
		options.addArguments("--window-size=1920x1080");
		driver = new ChromeDriver(options);
	}

	@BeforeClass
	public void setup() {
		setUpDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@DataProvider(name = "loginData")
	public Object[][] provideData() {
		return new Object[][] { { "admin", "admin123", true }, { "ADMIN", "admin123", true },
				{ "Admin", "admin123", true }, { "admin123", "admin123", false }, { "admin", "admin1234", false },
				{ "admin", "admin12", false }, { "admin", "123admin", false }, { "admin", "admin12345", false },
				{ "admin", "password", false }, { "admin", "1234", false }, { "admin", "wrongpassword", false },
				{ "admin", "admin1234", false }, { "ADMIN", "wrongpassword", false }, { "Admin1", "admin123", false },
				{ "admin", "admin1234", false }, { "admin", "admin12", false }, { "Admin", "wrongpassword", false },
				{ "Admin", "123", false }, { "admin", "admin", false }, { "admin", "12345", false },
				{ "Admin", "password123", false }, { "Admin", "admin098", false },
				{ "Admin", "wrongpassword123", false }, { "Admin", "testadmin123", false },
				{ "ADMIN", "admin1234", false }, { "Admin", "admin12345", false }, { "admin", "pass1234", false },
				{ "admin", "admin5678", false }, { "admin", "admin9876", false }, { "Admin", "hello1234", false },
				{ "admin", "testadmin", false }, { "Admin", "admin321", false }, { "admin123", "Admin", false },
				{ "Admin", "12345admin", false }, { "Admin", "Admin1234", false }, { "admin", "user123", false },
				{ "Admin", "wrongadmin", false }, { "admin", "admin123ABC", false }, { "admin", "admin1234!@#", false },
				{ "admin", "admin123456", false }, { "Admin", "abc123", false }, { "admin", "abcd1234", false },
				{ "Admin", "adminpass", false }, { "admin", "abcde123", false }, { "admin", "admin123xyz", false },
				{ "Admin", "admin9999", false } };
	}

	@Test(dataProvider = "loginData")
	public void loginTest(String username, String password, boolean expectedResult) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement usernameField = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Username']")));
		WebElement passwordField = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Password']")));
		WebElement loginButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
		usernameField.clear();
		passwordField.clear();
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();

		if (expectedResult) {
			boolean isDashboardDisplayed = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")))
					.isDisplayed();
			Assert.assertTrue(isDashboardDisplayed, "Login failed for valid credentials.");
		} else {
			System.out.println("Checking error message at: " + LocalDateTime.now());
			boolean isErrorDisplayed = wait
					.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")))
					.getText().equals("Invalid credentials");
			System.out.println("Error message checked at: " + LocalDateTime.now());

			Assert.assertTrue(isErrorDisplayed, "Login succeeded for invalid credentials.");
		}
	}

	@AfterMethod
	public void resetState() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			String currentUrl = driver.getCurrentUrl();
			boolean isLoginPage = currentUrl.contains("login");
			if (isLoginPage) {
				System.out.println("Already on the login page, no need to logout.");
				return;
			}
			WebElement profileDropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='oxd-userdropdown-tab']")));
			profileDropdown.click();
			WebElement logoutButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Logout']")));
			logoutButton.click();
			wait.until(ExpectedConditions.urlContains("login"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Username']")));
		} catch (Exception e) {
			System.out.println("Error during reset state: " + e.getMessage());
		}
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}