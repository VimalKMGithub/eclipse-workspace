package day16;

import com.microsoft.playwright.*;

public class ZoomInOutMaxiMiniPlaywright {
	public static void main(String[] args) throws InterruptedException {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();
			page.navigate("https://demo.nopcommerce.com/");
			/* maximize window */
			page.setViewportSize(1280, 720);

			/* minimize window simulation - Playwright does not natively support minimize */
			System.out.println("Simulating minimize by resizing to very small dimensions");
			page.setViewportSize(300, 200);

			/* maximize again */
			page.setViewportSize(1280, 720);

			/* zoom in or out */
			page.evaluate("document.body.style.zoom = '50%'");
			page.pause();
		}
	}
}
