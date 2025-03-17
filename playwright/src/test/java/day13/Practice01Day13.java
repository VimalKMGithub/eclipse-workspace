package day13;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import java.util.List;

public class Practice01Day13 {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			Page page = browser.newPage();
			page.navigate("https://www.dummyticket.com/dummy-ticket-for-visa-application/");

			/* Select ticket type */
			page.locator("input#product_3186").click();

			/* Fill traveler details */
			page.locator("input#travname").fill("Vimal Kumar");
			page.locator("input#travlastname").fill("Mishra");
			page.locator("textarea#order_comments").fill("Jai Mata Di");

			/* Select DOB */
			page.locator("input#dob").click();
			selectDate(page, "Aug", "2003", "28");

			/* Select gender and travel type */
			page.locator("input#sex_1").click();
			page.locator("input#traveltype_1").click();

			/* Fill travel details */
			page.locator("input#fromcity").fill("Allahabad");
			page.locator("input#tocity").fill("Bengaluru");
			page.locator("input#departon").click();
			selectDate(page, "Dec", "2024", "31");

			/* Fill additional notes */
			page.locator("textarea#notes").fill("Jai Mata Di");

			/* Select reason for dummy ticket */
			page.locator("span#select2-reasondummy-container").click();
			selectFromList(page, "ul#select2-reasondummy-results li", "Car rental");

			/* Select delivery method */
			page.locator("input#deliverymethod_3").click();

			/* Fill billing details */
			page.locator("input#billname").fill("Vimal Kumar Mishra");
			page.locator("input#billing_phone").fill("1212451245");
			page.locator("input#billing_email").fill("vimal@db.com");

			page.locator("span#select2-billing_country-container").click();
			selectFromList(page, "ul#select2-billing_country-results li", "India");

			page.locator("input#billing_address_1").fill("Address line 1");
			page.locator("input#billing_address_2").fill("Address line 2");
			page.locator("input#billing_city").fill("Allahabad");

			page.locator("span#select2-billing_state-container").click();
			selectFromList(page, "ul#select2-billing_state-results li", "Uttar Pradesh");

			page.locator("input#billing_postcode").fill("212121");

			/* Submit the form */
			page.locator("button#place_order").click();

		}
	}

	private static void selectDate(Page page, String month, String year, String day) {
		/* Select year */
		page.locator("select[aria-label='Select year']").selectOption(year);

		/* Select month */
		page.locator("select[aria-label='Select month']").selectOption(month);

		/* Select day */
		page.locator("table.ui-datepicker-calendar a").filter(new Locator.FilterOptions().setHasText(day)).first()
				.click();
	}

	private static void selectFromList(Page page, String selector, String text) {
		Locator items = page.locator(selector);
		for (int i = 0; i < items.count(); i++) {
			if (items.nth(i).textContent().equals(text)) {
				items.nth(i).click();
				break;
			}
		}
	}
}
