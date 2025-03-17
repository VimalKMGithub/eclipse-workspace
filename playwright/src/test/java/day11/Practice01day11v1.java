package day11;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

public class Practice01day11v1 {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the website
			page.navigate("https://blazedemo.com/");
			page.setViewportSize(1280, 720);

			// Select "From" dropdown
			page.selectOption("//select[@name='fromPort']", new SelectOption().setLabel("Philadelphia"));

			// Select "To" dropdown
			page.selectOption("//select[@name='toPort']", new SelectOption().setLabel("Rome"));

			// Click "Find Flights"
			page.click("//input[@value='Find Flights']");

			// Count rows
			int rows = page.locator("//tbody//tr").count();

			// Find the lowest price
			double lp = Double.MAX_VALUE;
			for (int i = 1; i <= rows; i++) {
				String pri = page.locator("//tbody//tr[" + i + "]//td[6]").textContent();
				double pd = Double.parseDouble(pri.replace("$", "").trim());
				if (pd < lp) {
					lp = pd;
				}
			}

			// Click the row with the lowest price
			for (int i = 1; i <= rows; i++) {
				String pri = page.locator("//tbody//tr[" + i + "]//td[6]").textContent();
				double pd = Double.parseDouble(pri.replace("$", "").trim());
				if (lp == pd) {
					page.locator("//tbody//tr[" + i + "]//td[1]").click();
					break;
				}
			}

			// Fill out the form
			page.fill("//input[@id='inputName']", "Vimal Kumar Mishra");
			page.fill("//input[@id='address']", "Prayagraj UP");
			page.fill("//input[@id='city']", "Prayagraj");
			page.fill("//input[@id='state']", "UP");
			page.fill("//input[@id='zipCode']", "212106");

			// Select card type
			page.selectOption("//select[@id='cardType']", new SelectOption().setLabel("Visa"));

			// Fill out credit card details
			page.fill("//input[@id='creditCardNumber']", "123456789012");
			page.fill("//input[@id='creditCardMonth']", "25");
			page.fill("//input[@id='creditCardYear']", "2025");
			page.fill("//input[@id='nameOnCard']", "Vimal Kumar Mishra");

			// Click "Remember Me"
			page.check("//input[@id='rememberMe']");

			// Click "Purchase Flight"
			page.click("//input[@value='Purchase Flight']");

			// Close the browser
			page.pause();
			browser.close();
		}
	}
}
