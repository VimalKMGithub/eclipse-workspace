package day17;

import com.microsoft.playwright.*;

public class HeadlessTesting {
	public static void main(String[] args) {
		// Initialize Playwright
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser in headless mode
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));

			// Create a new page
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the URL
			page.navigate("https://demo.opencart.com/");

			// Get the title of the page
			String title = page.title();

			// Validate the title
			if (title != null) {
				if (title.equals("Your Store")) {
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
