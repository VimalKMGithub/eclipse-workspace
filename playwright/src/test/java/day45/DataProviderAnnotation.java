package day45;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class DataProviderAnnotation {
	Playwright playwright;
	Browser browser;
	BrowserContext context;
	Page page;

	@BeforeClass
	public void setup() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		context = browser.newContext();
		page = context.newPage();
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
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
		// Locate username field
		page.locator("//input[@placeholder='Username']").fill(username);
		// Locate password field
		page.locator("//input[@placeholder='Password']").fill(password);
		// Click login button
		page.locator("//button[@type='submit']").click();

		if (expectedResult) {
			boolean isDashboardDisplayed = page.locator("//h6[text()='Dashboard']").isVisible();
			Assert.assertTrue(isDashboardDisplayed, "Login failed for valid credentials.");
		} else {
			boolean isErrorDisplayed = page.locator("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")
					.textContent().equals("Invalid credentials");
			Assert.assertTrue(isErrorDisplayed, "Login succeeded for invalid credentials.");
		}
	}

	@AfterMethod
	public void resetState() {
		try {
			// Check if already on login page
			if (page.url().contains("login")) {
				System.out.println("Already on the login page, no need to logout.");
				return;
			}
			// Logout process
			page.locator("//span[@class='oxd-userdropdown-tab']").click();
			page.locator("//a[text()='Logout']").click();
			page.waitForURL("**/login");
			page.locator("//input[@placeholder='Username']").waitFor();
		} catch (Exception e) {
			System.out.println("Error during reset state: " + e.getMessage());
		}
	}

	@AfterClass
	public void tearDown() {
		context.close();
		browser.close();
		playwright.close();
	}
}
