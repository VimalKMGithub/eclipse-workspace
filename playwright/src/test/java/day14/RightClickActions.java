package day14;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.MouseButton;

public class RightClickActions {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			Page page = browser.newPage();

			// Navigate to the target webpage
			page.navigate("https://swisnl.github.io/jQuery-contextMenu/demo.html");

			// Locate the element to right-click on
			Locator ele = page.locator("//span[@class='context-menu-one btn btn-neutral']");

			// Listen for the dialog and handle it (accept the alert)
			page.onDialog(dialog -> {
				System.out.println("Alert text: " + dialog.message());
				dialog.accept();
			});

			// Right-click the element (context click)
			ele.click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));

			// Select the 'Paste' option from the context menu
			page.locator("//span[normalize-space()='Paste']").click();

			// Optionally, wait for a while to observe the result
			page.waitForTimeout(2000);
		}
	}
}
