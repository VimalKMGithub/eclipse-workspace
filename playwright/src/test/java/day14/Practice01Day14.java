package day14;

import com.microsoft.playwright.*;

public class Practice01Day14 {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			// Launch a browser
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = browser.newPage();

			// Navigate to the website
			page.navigate("https://testautomationpractice.blogspot.com/");

			// Find the input element and enter text
			Locator ele2 = page.locator("#field1");
			ele2.fill("args");

			// Find the button element
			Locator ele = page.locator("//button[normalize-space()='Copy Text']");

			// Perform a double-click action on the button
			ele.dblclick();

			// Optionally, wait for a while to observe the result
			page.waitForTimeout(2000);
		}
	}
}
