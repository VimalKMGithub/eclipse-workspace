package day15;

import com.microsoft.playwright.*;

public class KeyBoardAction {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the website
			page.navigate("https://text-compare.com/", new Page.NavigateOptions().setTimeout(60000));

			// Type text into the first textarea
			page.locator("#inputText1").type("args");

			// Select all text (Ctrl + A)
			page.keyboard().press("Control+a");

			// Copy the text (Ctrl + C)
			page.keyboard().press("Control+c");

			// Move focus to the second textarea (Tab key)
			page.keyboard().press("Tab");

			// Paste the text (Ctrl + V)
			page.keyboard().press("Control+v");

			page.pause();
		}
	}
}
