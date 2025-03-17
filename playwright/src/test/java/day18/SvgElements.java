package day18;

import com.microsoft.playwright.*;

public class SvgElements {
	public static void main(String[] args) {
		// Create a Playwright instance and browser context
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the URL
			page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

			// Maximize the window (Playwright handles this automatically)
			page.setViewportSize(1920, 1080);

			// Fill out the login form
			page.fill("//input[@placeholder='Username']", "Admin");
			page.fill("//input[@placeholder='Password']", "admin123");
			page.click("//button[@type='submit']");

			// Click on the SVG element
			page.click("(//*[name()='svg'][@role='presentation'])[5]");

			page.pause();
			// Close the browser after action
			browser.close();
		}
	}
}
