package day08;

import com.microsoft.playwright.*;

import java.time.Duration;

public class HandleAlerts2 {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the JavaScript Alerts demo page
			page.navigate("https://the-internet.herokuapp.com/javascript_alerts");

			// Handle Simple Alert
			page.onDialog(dialog -> {
				System.out.println(dialog.message()); // Print alert message
				dialog.accept(); // Accept the alert
			});

			// Trigger the simple alert by clicking the button
			page.locator("//button[@onclick='jsAlert()']").click();

			// Optionally, close the browser after the operations
			browser.close();
		}
	}
}
