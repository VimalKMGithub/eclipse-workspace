package day13;

import com.microsoft.playwright.*;

public class DatePickerDemo2 {
	// Method to select a specific month and year
	public static void selectMoAndYe(FrameLocator page, String month, String year) {
		while (true) {
			String currentMonth = page.locator("//span[@class='ui-datepicker-month']").textContent();
			String currentYear = page.locator("//span[@class='ui-datepicker-year']").textContent();

			if (currentMonth.equals(month) && currentYear.equals(year)) {
				break;
			}
			// Navigate forward if the desired date is in the future
			page.locator("//span[@class='ui-icon ui-icon-circle-triangle-e']").click();
		}
	}

	// Method to select a specific day
	public static void selectDay(FrameLocator page, String day) {
		Locator days = page.locator("//table[@class='ui-datepicker-calendar']//a");
		for (int i = 0; i < days.count(); i++) {
			if (days.nth(i).textContent().equals(day)) {
				days.nth(i).click();
				break;
			}
		}
	}

	public static void main(String[] args) {
		// Set up Playwright and the browser
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the URL
			page.navigate("https://jqueryui.com/datepicker/");

			// Switch to the frame containing the date picker
			FrameLocator frameLocator = page.frameLocator("iframe.demo-frame");
			frameLocator.locator("//input[@id='datepicker']").click();

			// Desired date details
			String year = "2025", month = "May", day = "20";
			page.pause();
			// Select the desired month and year
			selectMoAndYe(frameLocator, month, year);

			// Select the desired day
			selectDay(frameLocator, day);

			// Optional: Verify selected date
			String selectedDate = frameLocator.locator("//input[@id='datepicker']").inputValue();
			System.out.println("Selected Date: " + selectedDate);

			// Close the browser
			browser.close();
		}
	}
}
