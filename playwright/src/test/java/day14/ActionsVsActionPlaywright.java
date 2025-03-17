package day14;

import com.microsoft.playwright.*;

public class ActionsVsActionPlaywright {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			Page page = browser.newPage();

			// Navigate to the target URL
			page.navigate("http://www.dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html");

			// Get the source and destination elements
			Locator src = page.locator("#box2");
			Locator dst = page.locator("#box106");

			// Perform drag and drop action
			src.dragTo(dst);

			// Wait for the action to complete
			page.waitForTimeout(1000);
		}
	}
}
