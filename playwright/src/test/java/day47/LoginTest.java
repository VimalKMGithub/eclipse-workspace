package day47;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
	private Playwright playwright;
	private Browser browser;
	private BrowserContext context;
	private Page page;

	@BeforeClass
	public void setup() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		context = browser.newContext();
		page = context.newPage();
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@Test
	public void testLogin() {
		// Using the Playwright-based LoginPage class
		LoginPage loginPage = new LoginPage(page);
		loginPage.setUsername("admin");
		loginPage.setPassword("admin123");
		loginPage.clickLogin();

		// Assertion for page title
		Assert.assertEquals(page.title(), "OrangeHRM", "Page title did not match!");
	}

	@AfterClass
	public void close() {
		context.close();
		browser.close();
		playwright.close();
	}
}
