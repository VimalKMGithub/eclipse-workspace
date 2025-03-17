package day06;

import com.microsoft.playwright.*;
import java.util.function.Predicate;

public class FluentWaitEx {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the URL
			page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

			// Fluent wait simulation
			Predicate<Page> isElementVisible = p -> p.locator("//input[@placeholder='Username']").isVisible();
			int timeoutInSeconds = 10;
			int pollingIntervalInMillis = 2000;

			long startTime = System.currentTimeMillis();
			while (System.currentTimeMillis() - startTime < timeoutInSeconds * 1000) {
				if (isElementVisible.test(page)) {
					break;
				}
				try {
					Thread.sleep(pollingIntervalInMillis);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}

			// Interact with the located element
			Locator userName = page.locator("//input[@placeholder='Username']");
			if (userName.isVisible()) {
				userName.fill("Admin");
			} else {
				System.out.println("Element not found within timeout.");
			}

			// Optionally, close the browser
			// browser.close();
		}
	}
}
