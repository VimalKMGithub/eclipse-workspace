package day17;

import java.util.Arrays;

import com.microsoft.playwright.*;

public class HideTestMessage {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser with arguments to suppress the "automation" message
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
					.setArgs(Arrays.asList("--disable-blink-features=AutomationControlled")));

			// Create a new browser context and page
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the URL
			page.navigate("https://demo.opencart.com/");

			// Get the page title
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
			page.pause();
			// Close the browser
			browser.close();
		}
	}
}
