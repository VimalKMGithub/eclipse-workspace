package day05;

import com.microsoft.playwright.*;

public class BrowserCommands {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the URL
			page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

			// Wait for the page to load (Playwright automatically waits for elements)
			page.waitForTimeout(2000);

			// Click the link
			page.locator("text=OrangeHRM, Inc").click();

			// Close all browser windows (Playwright closes all opened contexts by default)
			browser.close();
		}
	}
}
