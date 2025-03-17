package day14;

import com.microsoft.playwright.*;

public class MouseHoverActions {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			Page page = browser.newPage();

			// Navigate to the website
			page.navigate("https://demo.opencart.com/");

			// Locate elements
			Locator desktops = page.locator("//a[normalize-space()='Desktops']");
			Locator mac = page.locator("//a[normalize-space()='Mac (1)']");

			// Hover over 'Desktops' and then 'Mac (1)', then click
			desktops.hover();
			mac.hover();
			mac.click(); // Click on 'Mac (1)'
			page.pause();
			// Wait for a while to observe the result
			page.waitForTimeout(2000); // Optional, for observing the result
		}
	}
}
