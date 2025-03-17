package day10;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

import java.time.Duration;
import java.util.List;

public class DropDown {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the page
			page.navigate("https://testautomationpractice.blogspot.com/");
			page.setViewportSize(1280, 720);

			// Get the dropdown element
			Locator drpCtry = page.locator("#country");

			// Capture all options in the dropdown
//            List<Locator> options = drpCtry.locator("option").all();
//            System.out.println(options.size());

			// Loop through and print the options
//            for (Locator option : options) {
//                System.out.println(option.textContent());
//            }

			// Optionally, you can select by visible text, or index
			// Select by visible text
//			drpCtry.selectOption(new SelectOption().setLabel("India"));

			// Select by index (index is 0-based)
			drpCtry.selectOption(new SelectOption().setIndex(2));

			// Close the browser after the operations
			page.pause();
			browser.close();
		}
	}
}
