package day13;

import com.microsoft.playwright.*;

public class DatePickerDemo {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			// Launch browser
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			Page page = browser.newPage();

			// Navigate to the URL
			page.navigate("https://jqueryui.com/datepicker/");

			// Access the iframe using FrameLocator
			FrameLocator frameLocator = page.frameLocator("iframe.demo-frame");

			/* Direct method if allowed */
			frameLocator.locator("//input[@id='datepicker']").fill("12/11/2024");
			page.pause();
			/* Using date picker */
			String year = "2025", month = "May", day = "20";
			frameLocator.locator("//input[@id='datepicker']").click();

			/* Select month & year */
			while (true) {
				String dm = frameLocator.locator("//span[@class='ui-datepicker-month']").innerText();
				String dy = frameLocator.locator("//span[@class='ui-datepicker-year']").innerText();
				if (dm.equals(month) && dy.equals(year)) {
					break;
				}

				/* Clicking next if date is higher than present */
				frameLocator.locator("//span[@class='ui-icon ui-icon-circle-triangle-e']").click();
			}

			/* Selecting day */
//			frameLocator.locator("//table[@class='ui-datepicker-calendar']//tbody//tr//td//a").allInnerTexts().stream()
//					.filter(text -> text.equals(day)).findFirst().ifPresent(text -> {
//						frameLocator.locator(
//								"//table[@class='ui-datepicker-calendar']//tbody//tr//td//a[text()='" + day + "']")
//								.click();
//					});

			frameLocator.locator("//table[@class='ui-datepicker-calendar']//a[text()='" + day + "']").click();

			// Close the browser
			browser.close();
		}
	}
}
