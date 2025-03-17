package day17;

import com.microsoft.playwright.*;

public class SSLHandle {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)); // Set
																												// headless
																												// mode
																												// if
																												// needed

			// Create a browser context that accepts insecure certificates
			BrowserContext context = browser.newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true)); // Ignore
																														// SSL
																														// errors

			// Open a new page
			Page page = context.newPage();

			// Navigate to the URL with an expired certificate
			page.navigate("https://expired.badssl.com/");

			// Print the page title
			System.out.println(page.title());
			page.pause();
			// Close the browser
			browser.close();
		}
	}
}
