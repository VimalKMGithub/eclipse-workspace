package day06;

import com.microsoft.playwright.*;

public class SleepCommand {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the URL
			page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

			// Instead of Thread.sleep(), use Playwright's waitForTimeout
			page.waitForTimeout(2000); // Wait for 2 seconds (or any required delay)

			// Click on the link, Playwright will automatically wait for the element to be
			// visible
			page.locator("text=OrangeHRM, Inc").click();

			// Close the browser
			browser.close();
		}
	}
}
