package day13;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import java.util.List;

public class DatePickerDemo4 {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			Page page = browser.newPage();

			/* Navigate to the test automation practice page */
			page.navigate("https://testautomationpractice.blogspot.com/");

			/* Input for desired date */
			String day = "20", month = "June", year = "2025";

			/* Switch to frame containing the date picker */
			FrameLocator frame = page.frameLocator("#frame-one1434677811"); // Update with actual frame selector

			/* Click on the calendar icon */
			frame.locator("input#datepicker").click();

			/* Select year from the dropdown */
			Locator yearDropdown = frame.locator("select.ui-datepicker-year");
			yearDropdown.selectOption(year);

			/* Handle month navigation */
			while (true) {
				String displayedMonth = frame.locator("span.ui-datepicker-month").textContent().trim();
				if (displayedMonth.equalsIgnoreCase(month)) {
					break;
				}
				frame.locator("a.ui-datepicker-next").click();
			}

			/* Select the day from the calendar */
			Locator days = frame.locator("table.ui-datepicker-calendar a");
			for (int i = 0; i < days.count(); i++) {
				if (days.nth(i).textContent().equals(day)) {
					days.nth(i).click();
					break;
				}
			}

			/* Close the browser */
			browser.close();
		}
	}
}
