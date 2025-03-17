package day04;

import com.microsoft.playwright.*;

public class Xpath {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the website
			page.navigate("https://demo.nopcommerce.com/");
			page.setViewportSize(1920, 1080);

			/* XPath with single attribute */
			// page.locator("//*[@id='small-searchterms']").fill("args");

			/* XPath with multiple attributes */
			// page.locator("//*[@id='small-searchterms'][@name='q']").fill("args");

			/* XPath with 'and' */
			// page.locator("//*[@id='small-searchterms' and @name='q']").fill("args");

			/* XPath with 'or' */
			// page.locator("//*[@id='small-searchterms' or @name='q1']").fill("args");

			/* XPath with text */
//			page.locator("//*[text()='Apple MacBook Pro 13-inch']").click();

			/* Get text */
			// Locator header = page.locator("//h2[text()='Welcome to our store']");
			// boolean isDisplayed = header.isVisible();
			// System.out.println(isDisplayed);
			// String headerText = header.textContent();
			// System.out.println(headerText);

			/* XPath with contains */
			// page.locator("//input[contains(@placeholder, 'Sea')]").fill("args");

			/* XPath with starts-with */
			// page.locator("//input[starts-with(@placeholder, 'Sea')]").fill("args");

			/* Chained XPath */
//			boolean isDisplayed = page.locator("//div[@class='header-logo']/a/img").isVisible();
//			System.out.println(isDisplayed);

			// Close the browser
//			page.pause();
			browser.close();
		}
	}
}
