package day17;

import java.nio.file.Path;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class TakeScreenshot {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

			// Create a new page
			Page page = browser.newPage();

			// Navigate to the URL
			page.navigate("https://demo.nopcommerce.com/");

			// Full-page screenshot
			Path fullScreenshotPath = Path.of(System.getProperty("user.dir"), "full.png");
			page.screenshot(new Page.ScreenshotOptions().setPath(fullScreenshotPath).setFullPage(true));

			// Specific section screenshot
			// Locate the element (similar to the XPath in Selenium)
			Locator element = page.locator("//div[@class='product-grid home-page-product-grid']");
			Path specificScreenshotPath = Path.of(System.getProperty("user.dir"), "specific.png");
			element.screenshot(new Locator.ScreenshotOptions().setPath(specificScreenshotPath));

			// Close the browser
			browser.close();

			System.out.println("Screenshots taken successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
