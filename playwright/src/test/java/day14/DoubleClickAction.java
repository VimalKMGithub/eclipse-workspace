package day14;

import com.microsoft.playwright.*;

public class DoubleClickAction {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			Page page = browser.newPage();

			// Navigate to the webpage
			page.navigate("https://demo.guru99.com/test/simple_context_menu.html");

			// Locate the element
			Locator ele = page.locator("button:has-text('Double-Click Me To See Alert')");

			// Handle alert
			page.onDialog(dialog -> {
				System.out.println("Alert text: " + dialog.message());
				dialog.accept(); // Accept the alert
			});
			// Double-click on the element
			ele.dblclick();

			// Optional: Wait to observe the action
			page.waitForTimeout(2000);
		}
	}
}
