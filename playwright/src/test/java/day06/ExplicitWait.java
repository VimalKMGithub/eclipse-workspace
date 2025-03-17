package day06;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

public class ExplicitWait {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the URL
			page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

			// Explicit waits
			Locator userName = page.locator("//input[@placeholder='Username']");
			userName.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
			userName.fill("Admin");

			Locator password = page.locator("//input[@placeholder='Password']");
			password.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
			password.fill("Admin");

			Locator loginBtn = page.locator("//button[@type='submit']");
			loginBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
			loginBtn.click();

			page.pause();
			// Optionally, close the browser
			// browser.close();
		}
	}
}
