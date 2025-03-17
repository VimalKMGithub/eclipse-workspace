package day07;

import com.microsoft.playwright.*;

public class ClosingSpecificWindow {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the URL
			page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

			// Click on the link to open a new window
			page.locator("text=OrangeHRM, Inc").click();

			// Add a delay to ensure new windows are opened
			page.waitForTimeout(2000);

			// Iterate through all pages in the context and close the one with the specified
			// title
			context.waitForPage(() -> {
				// Wait logic handled internally by Playwright
			});
			for (Page p : context.pages()) {
				// Wait for the page to load completely before getting the title

				String pageTitle = p.title();
				System.out.println("Found page with title: " + pageTitle);

				if (pageTitle.equals("Human Resources Management Software | OrangeHRM")) {
					System.out.println("Closing window with title: " + pageTitle);
					p.close(); // Close the specific window
				}
			}

			// Optionally, close the browser after operations
			browser.close();
		}
	}
}
