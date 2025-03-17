package day16;

import com.microsoft.playwright.*;

public class JsExecutorPlaywright {
	public static void main(String[] args) {
		// Uncomment the desired method to use it.

		/* 1st method */
//		try (Playwright playwright = Playwright.create()) {
//			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//			BrowserContext context = browser.newContext();
//			Page page = context.newPage();
//			page.navigate("https://testautomationpractice.blogspot.com/");
//
//			page.fill("//input[@id='name']", "Vimal Kumar");
//			System.out.println("Value set using the 1st method.");
//			page.pause();
//		}

		/* 2nd method */
//		try (Playwright playwright = Playwright.create()) {
//			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//			BrowserContext context = browser.newContext();
//			Page page = context.newPage();
//			page.navigate("https://testautomationpractice.blogspot.com/");
//
//			JSHandle element = page.waitForSelector("//input[@id='name']");
//			element.evaluate("(ele) => ele.setAttribute('value', 'Vimal Kumar')");
//			System.out.println("Value set using the 2nd method.");
//			page.pause();
//		}

		/* 3rd method */
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();
			page.navigate("https://testautomationpractice.blogspot.com/");

			// Set value using JavaScript Executor equivalent
			JSHandle nameInput = page.waitForSelector("//input[@id='name']");
			nameInput.evaluate("(ele) => ele.setAttribute('value', 'Vimal Kumar')");

			// Perform click action using JavaScript Executor equivalent
			JSHandle maleRadio = page.waitForSelector("//input[@id='male']");
			maleRadio.evaluate("(ele) => ele.click()");

			System.out.println("Actions performed using the 3rd method.");
			page.pause();
		}
	}
}
