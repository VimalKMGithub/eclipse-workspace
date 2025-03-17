package day14;

import com.microsoft.playwright.*;

public class DragAndDropAction {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			Page page = browser.newPage();

			// Navigate to the drag-and-drop demo page
			page.navigate("http://www.dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html");

			// Get the source and destination elements
			Locator src = page.locator("#box2");
			Locator dst = page.locator("#box106");

			// Perform the drag and drop action
			src.dragTo(dst);

			// Wait for the action to complete
			page.waitForTimeout(1000); // Optional, wait for the visual effect
		}
	}
}
