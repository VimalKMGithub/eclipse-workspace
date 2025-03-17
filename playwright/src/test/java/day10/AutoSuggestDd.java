package day10;

import com.microsoft.playwright.*;

import java.util.List;

public class AutoSuggestDd {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the Google page
			page.navigate("https://www.google.com/");
			page.setViewportSize(1280, 720);

			// Select the search box and input text
			Locator searchBox = page.locator("[name='q']");
			searchBox.fill("selenium");

			// Wait for the suggestions to appear
			Locator suggestions = page.locator("//ul[@role='listbox']//li//div[@role='option']");
			suggestions.first().waitFor(new Locator.WaitForOptions().setTimeout(5000)); // Timeout in milliseconds

			// Wait for options to appear and iterate through them
			List<Locator> options = suggestions.all();
			for (Locator option : options) {
				String optionText = option.textContent().trim();
				if (optionText.equalsIgnoreCase("selenium")) {
					option.click();
					break;
				}
			}

			page.pause(); // Pause to observe the result
			// Close the browser
			browser.close();
		}
	}
}
