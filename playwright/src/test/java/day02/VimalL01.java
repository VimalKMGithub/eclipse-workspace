package day02;

import com.microsoft.playwright.*;

public class VimalL01 {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the webpage
			page.navigate("https://www.opencart.com/index.php?route=cms/demo");

			// Maximize the window
			page.setViewportSize(1920, 1080);

			// Find all images on the page
			int imageCount = page.locator("img").count();
			System.out.println("Total images: " + imageCount);

			// Close the browser
			browser.close();
		}
	}
}
