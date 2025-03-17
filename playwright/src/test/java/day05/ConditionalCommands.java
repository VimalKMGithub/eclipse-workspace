package day05;

import com.microsoft.playwright.*;

public class ConditionalCommands {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the URL
			page.navigate("https://demo.nopcommerce.com/register");
			page.setViewportSize(1920, 1080);

			/* isDisplayed */
			boolean isLogoDisplayed = page.locator("//img[@alt='nopCommerce demo store']").isVisible();
			System.out.println("Is Logo displayed: " + isLogoDisplayed);

			/* isEnabled */
			boolean isFirstNameEnabled = page.locator("//input[@id='FirstName']").isEnabled();
			System.out.println("Is First Name field enabled: " + isFirstNameEnabled);

			/* isSelected */
			Locator genMale = page.locator("//input[@id='gender-male']");
			Locator genFemale = page.locator("//input[@id='gender-female']");

			System.out.println("Before selecting...");
			System.out.println("Male selected: " + genMale.isChecked());
			System.out.println("Female selected: " + genFemale.isChecked());

			genMale.check();
			System.out.println("\nAfter selecting male...");
			System.out.println("Male selected: " + genMale.isChecked());
			System.out.println("Female selected: " + genFemale.isChecked());

			genFemale.check();
			System.out.println("\nAfter selecting female...");
			System.out.println("Male selected: " + genMale.isChecked());
			System.out.println("Female selected: " + genFemale.isChecked());

			/* Checkbox */
			boolean isNewsletterSelected = page.locator("//input[@id='Newsletter']").isChecked();
			System.out.println("\nNewsletter selected: " + isNewsletterSelected);

			// Close the browser
			browser.close();
		}
	}
}
