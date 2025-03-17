package day16;

import com.microsoft.playwright.*;

public class ScrollPagePlaywright {
	public static void main(String[] args) throws InterruptedException {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();
			page.navigate("https://www.smashingmagazine.com/2017/05/long-scrolling/");
			page.setViewportSize(1280, 720);

			/* scroll by pixel */
//			page.evaluate("window.scrollBy(0, 1000)");

			/* scroll till element is visible */
//			Locator element = page.locator("//h3[@id='7-don-t-hijack-scrolling']");
//			element.scrollIntoViewIfNeeded();
//			System.out.println(page.evaluate("window.pageYOffset"));

			/* scroll till bottom */
//			page.waitForTimeout(4000);
//			page.evaluate("window.scrollBy(0, document.body.scrollHeight)");
//			System.out.println(page.evaluate("window.pageYOffset"));

			/* scroll upto initial */
//			page.waitForTimeout(4000);
//			page.evaluate("window.scrollBy(0, -document.body.scrollHeight)");
//			System.out.println(page.evaluate("window.pageYOffset"));
			page.pause();
		}
	}
}
