package day12;

import com.microsoft.playwright.*;

public class DynamicTableWithPn {
	public static void main(String[] args) {
		// Create Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch browser
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the site
			page.navigate("https://demo.opencart.com/admin/");
			page.setViewportSize(1280, 720);

			// Login
			page.fill("//input[@id='input-username']", "demo");
			page.fill("//input[@id='input-password']", "demo");
			page.click("//button[@type='submit']");

			// Handle potential alert
			page.onDialog(dialog -> dialog.accept());

			// Navigate to Customers section
			page.click("//a[@class='parent collapsed'][normalize-space()='Customers']");
			page.click("//ul[@id='collapse-5']//a[contains(text(),'Customers')]");

			// Extract total pages
			String str = page.locator("//div[contains(text(),'Pages')]").textContent();
			String totalPgsStr = str.substring(str.indexOf("(") + 1, str.indexOf("Pages") - 1);
			int totalPgs = Integer.parseInt(totalPgsStr);

			// Loop through each page (uncomment to use pagination)
			// for (int i = 1; i <= totalPgs; i++) {
			// if (i > 1) {
			// page.click("//ul[@class='pagination']//a[normalize-space()='" + i + "']");
			// }
			// }

			// Get total rows on the current page
			int rows = page.locator("//table[@class='table table-bordered table-hover']//tbody//tr").count();

			// Loop through the rows and extract data
			for (int i = 1; i <= rows; i++) {
				String name = page
						.locator("//table[@class='table table-bordered table-hover']//tbody//tr[" + i + "]//td[2]")
						.textContent();
				String email = page
						.locator("//table[@class='table table-bordered table-hover']//tbody//tr[" + i + "]//td[3]")
						.textContent();
				System.out.println(name + "\t" + email);
			}

			// Close the browser
			browser.close();
		}
	}
}
