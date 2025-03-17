package day46;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class OrangeHrmTestAgain {
	Playwright playwright;
	Browser browser;
	BrowserContext context;
	Page page;

	@BeforeClass
	public void open() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		context = browser.newContext();
		page = context.newPage();
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@Test
	public void testLogo() {
		// Check if the logo is visible
		boolean isLogoPresent = page.locator("//img[@alt='company-branding']").isVisible();
		Assert.assertTrue(isLogoPresent, "Company logo is not visible.");
	}

	@Test(dependsOnMethods = { "testUrl" })
	public void testTitle() {
		// Validate the page title
		Assert.assertEquals(page.title(), "OrangeHRM", "Page title does not match.");
	}

	@Test
	public void testUrl() {
		// Validate the current URL
		String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"; // Update as needed
		Assert.assertEquals(page.url(), expectedUrl, "URL does not match.");
	}

	@AfterClass
	public void close() {
		context.close();
		browser.close();
		playwright.close();
	}
}
