package day08;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

public class CheckBoxes {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

			// Create a new context and page
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the test automation practice site
			page.navigate("https://testautomationpractice.blogspot.com/");

			// Select specific checkbox (e.g., Sunday checkbox)
			page.locator("//input[@id='sunday']").click();
			// Select multiple checkboxes
			Locator checkboxes = page.locator("//*[@class='form-check-input' and @type='checkbox']");
			int count = checkboxes.count();
			for (int i = 0; i < count; i++) {
				checkboxes.nth(i).click(); // Click each checkbox
			}
			// Select last 3 checkboxes
			for (int i = count - 3; i < count; i++) {
				checkboxes.nth(i).click();
			}

			// Select first 3 checkboxes
			for (int i = 0; i < 3; i++) {
				checkboxes.nth(i).click();
			}

			// Unselect selected checkboxes
			for (int i = 0; i < 3; i++) {
				if (checkboxes.nth(i).isChecked()) {
					checkboxes.nth(i).click(); // Uncheck if selected
				}
			}

			// Optionally, close the browser after the operations
			browser.close();
		}
	}
}
