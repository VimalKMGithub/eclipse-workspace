package day12;

import com.microsoft.playwright.*;

public class Practice02day12 {
	public static void main(String[] args) {
		// Create Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch browser
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Maximize window and navigate to the login page
			page.setViewportSize(1280, 720);
			page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

			// Fill in login credentials and submit the form
			page.fill("//input[@placeholder='Username']", "Admin");
			page.fill("//input[@placeholder='Password']", "admin123");
			page.click("//button[@type='submit']");

			// Handle any alert that may appear after login
			try {
				page.waitForTimeout(1000); // Waiting for potential alert to appear
				page.onDialog(dialog -> dialog.accept());
			} catch (Exception e) {
				// If no alert appears, continue
			}
			page.pause();
			// Click on the active menu item (employee menu)
			page.click("//a[@class='oxd-main-menu-item active']");

			// Get the total number of rows in the employee table
			int rows = page.locator(
					"//div[@class='oxd-table'][@role='table']//div[@class='oxd-table-body']//div[@class='oxd-table-card']")
					.count();

			System.out.println("Total rows: " + rows);

			// Close the browser
			browser.close();
		}
	}
}
