package day03;

import com.microsoft.playwright.*;

public class CssLocators {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the website
			page.navigate("https://demo.nopcommerce.com/");

			// Maximize the window
			page.setViewportSize(1920, 1080);

			// Using CSS selector with tag and class
			page.locator(".search-box-text[name='q']").fill("args");

			// Close the browser
			browser.close();
		}
	}
}
