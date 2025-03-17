package day07;

import com.microsoft.playwright.*;

public class HandleBrowserWindows {
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

			// Add a short delay to allow new windows to open
			page.waitForTimeout(2000);
			context.waitForPage(() -> {
				// Wait logic handled internally by Playwright
			});
			// Get all pages (open windows) in the context
			for (Page p : context.pages()) {
				// Print the title of each page
				String pageTitle = p.title();
				System.out.println("Window title: " + pageTitle);
			}

			// Switching between windows (pages) based on title
			for (Page p : context.pages()) {
				if (p.title().equals("Human Resources Management Software | OrangeHRM")) {
					System.out.println("Switching to window with title: " + p.title());
					// Perform actions on this window if needed
					p.close(); // Close the specific window
				}
			}

			// Optionally, close the browser after operations
			browser.close();
		}
	}
}
