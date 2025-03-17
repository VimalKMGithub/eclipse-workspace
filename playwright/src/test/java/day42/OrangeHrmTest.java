package day42;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class OrangeHrmTest {
	private Playwright playwright;
	private Browser browser;
	private Page page;

	public void setUpDriver() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)); // headless mode
		page = browser.newPage();
	}

	@Test(priority = 1)
	public void open() {
		setUpDriver();
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		page.setViewportSize(1920, 1080); // Equivalent to maximizing window in Selenium
	}

	@Test(priority = 2)
	public void verifyLogo() {
		// Wait for the logo to appear and be visible
		page.locator("img[alt='company-branding']").waitFor(new Locator.WaitForOptions().setTimeout(5000)); // Wait up
																											// to 5
																											// seconds
		boolean isLogoPresent = page.locator("img[alt='company-branding']").isVisible();
		System.out.println("Logo: " + isLogoPresent);
	}

	@Test(priority = 3)
	public void login() {
		page.locator("input[placeholder='Username']").fill("Admin");
		page.locator("input[placeholder='Password']").fill("admin123");
	}

	@Test(priority = 4)
	public void quit() {
		browser.close();
		playwright.close();
	}
}
