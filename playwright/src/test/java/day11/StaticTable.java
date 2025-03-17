package day11;

import com.microsoft.playwright.*;
import java.time.Duration;

public class StaticTable {
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

			// Counting table rows from a specific table
			int rows = page.locator("//table[@name='BookTable']//tr").count();
			// System.out.println(rows);

			// Counting table columns from a specific table
			int cols = page.locator("//table[@name='BookTable']//th").count();
			// System.out.println(cols);

//			 Reading data from specific row & column
//			String data = page.locator("//table[@name='BookTable']//tr[5]//td[1]").textContent();
//			System.out.println(data);

			// Reading all data
//			for (int i = 2; i <= rows; i++) {
//				for (int j = 1; j <= cols; j++) {
//					System.out.print(
//							page.locator("//table[@name='BookTable']//tr[" + i + "]//td[" + j + "]").textContent()
//									+ "\t");
//				}
//				System.out.println();
//			}

			// Print books whose author is Mukesh
//			for (int i = 2; i <= rows; i++) {
//				String author = page.locator("//table[@name='BookTable']//tr[" + i + "]//td[2]").textContent();
//				if (author.equals("Mukesh")) {
//					System.out.print(author + ": ");
//					System.out.println(page.locator("//table[@name='BookTable']//tr[" + i + "]//td[1]").textContent());
//				}
//			}

			// Print price of all books
//			for (int i = 2; i <= rows; i++) {
//				String price = page.locator("//table[@name='BookTable']//tr[" + i + "]//td[4]").textContent();
//				System.out.println(price);
//			}

			// Sum of prices
//			int total = 0;
//			for (int i = 2; i <= rows; i++) {
//				String priceText = page.locator("//table[@name='BookTable']//tr[" + i + "]//td[4]").textContent();
//				int price = Integer.parseInt(priceText);
//				total += price;
//			}
//			System.out.println("Total: " + total);

			// Close the browser
			browser.close();
		}
	}
}
