package day08;

import com.microsoft.playwright.*;

public class HandleAlerts {
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
//			page.onDialog(dialog -> {
//				System.out.println("Alert text: " + dialog.message());
//				dialog.accept(); // Accept the alert
//			});
//			page.locator("//button[@onclick='jsAlert()']").click();

			// Handle Confirmation Alert
//			page.onDialog(dialog -> {
//				System.out.println("Confirmation alert text: " + dialog.message());
//				dialog.dismiss(); // Dismiss the confirmation alert (click Cancel)
//			});
//			page.locator("//button[@onclick='jsConfirm()']").click();

			// Handle Prompt Alert
			page.onDialog(dialog -> {
				System.out.println("Prompt alert text: " + dialog.message());
				dialog.accept("Vimal Kumar Mishra"); // Send keys to prompt and accept
			});
			page.locator("//button[@onclick='jsPrompt()']").click();

			// Optionally, close the browser after the operations
			browser.close();
		}
	}
}
