package day02;

import com.microsoft.playwright.*;

import java.util.List;

public class Locators01 {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch a new browser instance
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

			// Create a new browser context and page
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the URL
			page.navigate("https://demo.opencart.com");

			// Maximize window is not directly available in Playwright
			// But you can set the viewport size manually if needed
			page.setViewportSize(1920, 1080);

			/* by name */
//			 page.locator("input[name='search']").fill("Mac");

			/* by id */
//			 boolean isLogoDisplayed = page.locator("#logo").isVisible();
//			 System.out.println(isLogoDisplayed);

			/* linkText & partialLinkText */
//			 page.locator("text=Tablets").click();
//			 page.locator("text=Table").click();

			/* by classname */
//			 List<ElementHandle> headerLinks =
//			 page.locator(".list-inline-item").elementHandles();
//			 System.out.println(headerLinks.size());

//			Locator optns = page.locator(".list-inline-item");
//			int count = optns.count();
//			System.out.println(count);
//			optns.allInnerTexts().forEach(System.out::println); // Prints inner text of all elements
//			for (int i = 0; i < optns.count(); i++) {
//				System.out.println(optns.nth(i).textContent());
//			}

			/* by tagname */
//			List<ElementHandle> links = page.locator("a").elementHandles();
//			System.out.println("Number of links: " + links.size());

//			Locator l = page.locator("a");
//			System.out.println(l.count());

//			page.locator("a").nth(0).click();

			/* all images in webpage */
//			 List<ElementHandle> images = page.locator("img").elementHandles();
//			 System.out.println("Total images: " + images.size());

			// Close the browser
//			page.pause();
			browser.close();
		}
	}
}
