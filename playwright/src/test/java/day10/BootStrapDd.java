package day10;

import com.microsoft.playwright.*;

import java.time.Duration;
import java.util.List;

public class BootStrapDd {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the page
			page.navigate("https://www.jquery-az.com/boots/demo.php?ex=63.0_2");
			page.setViewportSize(1280, 720);

			// Open the dropdown menu by clicking the button
			Locator dropdownButton = page.locator("//button[contains(@class,'multiselect')]");
			dropdownButton.click();

			// Select a single option (Java)
			Locator javaOption = page.locator("//input[@value='Java']");
			javaOption.click();

			// Select multiple options (Java, Python, MySQL)
			List<Locator> options = page.locator("//label[@class='checkbox']").all();
			System.out.println("Size: " + options.size());

			// Select specific items
			for (Locator option : options) {
				String optionText = option.locator("text=" + option.innerText()).textContent().trim();
				if (" Java".equals(optionText) || "Python".equals(optionText) || "MySQL".equals(optionText)) {
					option.click();
				}
			}
			page.pause();
			// Close the browser
			browser.close();
		}
	}
}
