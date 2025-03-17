package day15;

import com.microsoft.playwright.*;

public class TabsAndWindows {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

			// Open the first page in a new context (default tab)
			BrowserContext context = browser.newContext();
			Page page1 = context.newPage();
			page1.navigate("https://demo.nopcommerce.com/");

			// Open a new page (tab) within the same context
//			Page page2 = context.newPage();
//			page2.navigate("https://www.orangehrm.com/");

			// open in new window
			BrowserContext context2 = browser.newContext();
			Page page2 = context2.newPage();
			page2.navigate("https://www.orangehrm.com/");

			// Optional: Pause to inspect
			page2.pause();

			// Close browser
			browser.close();
		}
	}
}
