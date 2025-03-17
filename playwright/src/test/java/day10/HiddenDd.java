package day10;

import com.microsoft.playwright.*;

import java.nio.channels.Pipe.SourceChannel;
import java.util.List;

public class HiddenDd {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the login page
			page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
			page.setViewportSize(1280, 720);

			// Login
			page.locator("//input[@placeholder='Username']").fill("Admin");
			page.locator("//input[@placeholder='Password']").fill("admin123");
			page.locator("//button[@type='submit']").click();

			// Navigate to the PIM section
			page.locator("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='PIM']")
					.click();

			// Wait for the dropdown or listbox to be visible
			page.locator(
					"//body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='oxd-table-filter']/div[@class='oxd-table-filter-area']/form[@class='oxd-form']/div[@class='oxd-form-row']/div[@class='oxd-grid-4 orangehrm-full-width-grid']/div[6]/div[1]/div[2]/div[1]/div[1]")
					.waitFor(new Locator.WaitForOptions().setTimeout(5000));
			page.locator(
					"//body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='oxd-table-filter']/div[@class='oxd-table-filter-area']/form[@class='oxd-form']/div[@class='oxd-form-row']/div[@class='oxd-grid-4 orangehrm-full-width-grid']/div[6]/div[1]/div[2]/div[1]/div[1]")
					.click();
			// Fetch all options (span elements inside listbox)
			Locator firstOption = page.locator("//div[@role='listbox']//span").first();
//			firstOption.waitFor(new Locator.WaitForOptions().setTimeout(5000));
//			List<Locator> optns = page.locator("//div[@role='listbox']//span").all();
//			System.out.println(optns.size()); // Should print the size if options are visible

			Locator ops = page.locator("//div[@role='listbox']//span");
			System.out.println(ops.count());
			for (int i = 0; i < ops.count(); i++) {
				System.out.println(ops.nth(i).textContent());
			}
//
//			// Optionally, print the text of the options to verify
//			for (Locator option : optns) {
//				System.out.println(option.textContent());
//			}

			// Close the browser
			browser.close();
		}
	}
}
