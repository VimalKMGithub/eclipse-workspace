package day05;

import com.microsoft.playwright.*;
import java.util.Set;

public class GetCommands {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the URL
			page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
			page.waitForTimeout(2000);

			/* get title */
			System.out.println("Page Title: " + page.title());

			/* get url */
			System.out.println("Current URL: " + page.url());

			/* get page source */
			// Playwright doesn't have a direct method for page source. Use evaluate() if
			// needed.

			/* get window handle */
			String mainWindow = context.pages().get(0).url();
			System.out.println("Main Window URL: " + mainWindow);

			// Click on the link to open a new tab
			page.locator("text=OrangeHRM, Inc").click();

			// Wait for the new tab to appear
			context.waitForPage(() -> {
				// Wait logic handled internally by Playwright
			});

			// Collect all page URLs
			Set<String> handles = context.pages().stream().map(Page::url).collect(java.util.stream.Collectors.toSet());
			System.out.println("Window URLs: " + handles);

			// Close the browser
			browser.close();
		}
	}
}
