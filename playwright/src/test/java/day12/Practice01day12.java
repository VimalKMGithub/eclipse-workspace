package day12;

import com.microsoft.playwright.*;

public class Practice01day12 {
	public static void main(String[] args) {
		// Create Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch browser
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the site
			page.navigate("https://testautomationpractice.blogspot.com/");
			page.setViewportSize(1280, 720);

			// Get the total number of pages
			int pages = page.locator("//ul[@id='pagination']//li//a").count();

			// Get the total number of rows on the first page
			int rows = page.locator("//table[@id='productTable']//tbody//tr").count();

			// Loop through each page
			for (int i = 1; i <= pages; i++) {
				// Loop through the rows on the current page
				for (int j = 1; j <= rows; j++) {
					String id = page.locator("//table[@id='productTable']//tbody//tr[" + j + "]//td[1]").textContent();
					String name = page.locator("//table[@id='productTable']//tbody//tr[" + j + "]//td[2]")
							.textContent();
					String price = page.locator("//table[@id='productTable']//tbody//tr[" + j + "]//td[3]")
							.textContent();
					page.locator("//table[@id='productTable']//tbody//tr[" + j + "]//input[@type='checkbox']").click();
					System.out.println(id + "\t" + name + "\t" + price);
				}
				// If it's not the last page, click the next page
				if (i != pages) {
					page.locator("//ul[@id='pagination']//a[normalize-space()='" + (i + 1) + "']").click();
					page.waitForTimeout(1000); // Wait for the next page to load
				}
				System.out.println();
			}

			// Close the browser
			browser.close();
		}
	}
}
