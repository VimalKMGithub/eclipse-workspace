package day18;

import com.microsoft.playwright.*;

public class ShadowDom {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			/* Page -> Dom -> ShadowDom -> element */
//			page.navigate("https://books-pwakit.appspot.com/");
//			Locator loc = page.locator("book-app[apptitle='BOOKS']");
//			loc.locator("#input").fill("Selenium");
//			String str = loc.locator(".books-desc").textContent();
//			System.out.println(str);

			page.navigate("https://books-pwakit.appspot.com/");
//			Locator loc = page.locator("book-app[apptitle='BOOKS']");
			page.locator("#input").fill("Selenium");
			String str = page.locator(".books-desc").textContent();
			System.out.println(str);

			/* Page -> iframe -> Dom -> ShadowDom -> element */
//			page.navigate("https://selectorshub.com/shadow-dom-in-iframe/");
//			FrameLocator fr = page.frameLocator("#pact");
//			Locator rootLoc = fr.locator("div#snacktime");
//			Locator ele = rootLoc.locator("#tea");
//			ele.fill("Vimal's Tea");

			page.pause();
		}
	}
}