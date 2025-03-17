package day06;

import com.microsoft.playwright.*;

public class ImplicitWait {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Set a maximum wait time (Playwright does not have a global implicit wait like
			// Selenium)
			page.setDefaultTimeout(5000); // Timeout in milliseconds

			// Navigate to the URL
			page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

			// Click on the link, Playwright will wait for the link element automatically
			page.locator("text=OrangeHRM, Inc").click();

			// Close the browser
			browser.close();
		}
	}
}
