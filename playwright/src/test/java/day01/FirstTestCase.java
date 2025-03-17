package day01;

import com.microsoft.playwright.*;

public class FirstTestCase {
	public static void main(String[] args) {
		// Set up Playwright
		try (Playwright playwright = Playwright.create()) {
			// Launch a new browser
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			// Open a new browser context and page
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the URL
			page.navigate("https://www.opencart.com/index.php?route=cms/demo");

			// Get the page title
			String title = page.title();

			// Verify the title
			if (title != null) {
				if (title.equals("OpenCart - Demo")) {
					System.out.println("Passed...");
				} else {
					System.out.println("Failed...");
				}
			} else {
				System.out.println("No title");
			}

			// Close the browser
			browser.close();
		}
	}
}
