package day07;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.MouseButton;

public class TestPractice {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the test automation practice site
			page.navigate("https://testautomationpractice.blogspot.com/");

			// Search for "selenium"
			page.locator("//input[@id='Wikipedia1_wikipedia-search-input']").fill("selenium");
			page.locator("//input[@type='submit']").click();

			// Wait for search results to appear
			page.waitForTimeout(2000); // Simple wait to ensure results are loaded

			// Get all the links to be clicked
			Locator links = page.locator("div.wikipedia-search-results a");
			int count = links.count();

			// Create a list to store the newly opened pages
			for (int i = 0; i < count; i++) {
				// Open each link in a new tab using middle mouse button
				links.nth(i).click(new Locator.ClickOptions().setButton(MouseButton.MIDDLE));
			}

			// Wait for the pages to fully load and process them one by one
			for (Page p : context.pages()) {
				try {
					p.waitForLoadState(LoadState.LOAD); // Ensure the page is fully loaded
					String title = p.title();
					System.out.println("Page title: " + title);

					// Close pages that do not match the desired title
					if (!title.equals("Selenium (software) - Wikipedia")) {
						p.close();
					}
				} catch (Exception e) {
					System.out.println("Error processing page: " + e.getMessage());
				}
			}

			// Close the browser after operations
			browser.close();
		}
	}
}
