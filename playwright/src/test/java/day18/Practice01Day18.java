package day18;

import com.microsoft.playwright.*;

public class Practice01Day18 {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			Page page = browser.newPage();

			// Navigate to the page
			page.navigate("https://dev.automationtesting.in/shadow-dom");
			page.setViewportSize(1280, 800);

			// Locate elements inside nested shadow DOM using Playwright chaining
			// First Shadow DOM host: #shadow-root
			Locator shadowHost1 = page.locator("#shadow-root");

			// Second Shadow DOM host: #inner-shadow-dom
			Locator shadowHost2 = shadowHost1.locator("#inner-shadow-dom");

			// Third Shadow DOM host: #nested-shadow-dom
			Locator shadowHost3 = shadowHost2.locator("#nested-shadow-dom");

			// Find the element inside the deepest shadow DOM: #multi-nested-shadow-element
			Locator shadowElement = shadowHost3.locator("#multi-nested-shadow-element");

			// Get the text from the element
			String txt = shadowElement.textContent();
			System.out.println(txt);

			browser.close();
		}
	}
}
