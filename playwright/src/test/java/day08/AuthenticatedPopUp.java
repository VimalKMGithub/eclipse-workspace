package day08;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.HttpCredentials;

public class AuthenticatedPopUp {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

			// Create a browser context with authentication credentials
			BrowserContext context = browser.newContext(
					new Browser.NewContextOptions().setHttpCredentials(new HttpCredentials("admin", "admin")));

			// Open a new page within the authenticated context
			Page page = context.newPage();

			// Navigate to the URL (authentication credentials are already set)
			page.navigate("https://the-internet.herokuapp.com/basic_auth");

			// Optionally, print the page content to confirm authentication
			System.out.println(page.content());

			// Close the browser after the operations
			browser.close();
		}
	}
}
