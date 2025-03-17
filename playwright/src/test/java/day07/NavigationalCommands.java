package day07;

import com.microsoft.playwright.*;

public class NavigationalCommands {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the first URL
			page.navigate("https://demo.nopcommerce.com/");
			System.out.println("Navigated to: " + page.url());

			// Navigate to the second URL
			page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
			System.out.println("Navigated to: " + page.url());

			// Navigate back
			page.goBack();
			System.out.println("After back navigation: " + page.url());

			// Navigate forward
			page.goForward();
			System.out.println("After forward navigation: " + page.url());

			// Refresh the page
			page.reload();
			System.out.println("After refresh: " + page.url());

			// Close the browser
			browser.close();
		}
	}
}
