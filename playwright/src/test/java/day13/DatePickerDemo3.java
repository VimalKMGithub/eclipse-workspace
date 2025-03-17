package day13;

import com.microsoft.playwright.*;

public class DatePickerDemo3 {
	// Method to select a future date
	public static void selectFutureDate(FrameLocator frameLocator, String day, String month, String year) {
		while (true) {
			String currentMonth = frameLocator.locator("//span[@class='ui-datepicker-month']").textContent();
			String currentYear = frameLocator.locator("//span[@class='ui-datepicker-year']").textContent();
			if (currentMonth.equals(month) && currentYear.equals(year)) {
				break;
			}
			frameLocator.locator("//span[@class='ui-icon ui-icon-circle-triangle-e']").click();
		}

		frameLocator.locator("//table[@class='ui-datepicker-calendar']//a[text()='" + day + "']").click();
	}

	// Method to select a past date
	public static void selectPastDate(FrameLocator frameLocator, String day, String month, String year) {
		while (true) {
			String currentMonth = frameLocator.locator("//span[@class='ui-datepicker-month']").textContent();
			String currentYear = frameLocator.locator("//span[@class='ui-datepicker-year']").textContent();
			if (currentMonth.equals(month) && currentYear.equals(year)) {
				break;
			}
			frameLocator.locator("//span[@class='ui-icon ui-icon-circle-triangle-w']").click();
		}

		frameLocator.locator("//table[@class='ui-datepicker-calendar']//a[text()='" + day + "']").click();
	}

	public static void main(String[] args) {
		// Set up Playwright and browser
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the URL
			page.navigate("https://jqueryui.com/datepicker/");

			// Switch to the frame containing the date picker
			FrameLocator frameLocator = page.frameLocator("iframe.demo-frame");

			// Open the date picker input
			frameLocator.locator("//input[@id='datepicker']").click();

			// Select a future date
//			String futureYear = "2025", futureMonth = "May", futureDay = "20";
//			selectFutureDate(frameLocator, futureDay, futureMonth, futureYear);

			// Select a past date
			String pastYear = "2023", pastMonth = "April", pastDay = "15";
			selectPastDate(frameLocator, pastDay, pastMonth, pastYear);

			// Verify the selected date
			String selectedDate = frameLocator.locator("//input[@id='datepicker']").inputValue();
			System.out.println("Selected Date: " + selectedDate);

			// Close the browser
			page.pause();
			browser.close();
		}
	}
}
