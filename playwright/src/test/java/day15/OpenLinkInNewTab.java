package day15;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.KeyboardModifier;

import java.util.*;

public class OpenLinkInNewTab {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the website
//			page.navigate("https://demo.nopcommerce.com/");
			page.navigate("https://demo.opencart.com/");

			// Open "Register" link in a new tab
			Locator reg = page.locator("//img[@title='MacBook']");
			Page newTab = context.waitForPage(() -> {
				reg.click(new Locator.ClickOptions().setModifiers(Collections.singletonList(KeyboardModifier.CONTROL)));
			});

			// Wait for the new tab to load
			newTab.waitForLoadState();

			// Fill "First Name" field in the new tab
			newTab.locator("//input[@placeholder='Search']").fill("Vimal");

			// Switch back to the original tab
			page.bringToFront();

			// Search for "T-Shirts" in the original tab
			page.locator("//input[@placeholder='Search']").fill("T-Shirts");
			page.pause();
		}
	}
}
