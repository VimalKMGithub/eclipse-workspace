package day09;

import com.microsoft.playwright.*;

import java.time.Duration;

public class Practice {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the demo page
			page.navigate("https://ui.vision/demo/webtest/frames/");
			page.setViewportSize(1280, 720);

			page.waitForSelector("frame[src='frame_5.html']");

			// Loop through all frames and find the frame by its src
			Frame frame5 = null;
			for (Frame frame : page.frames()) {
				if (frame.url().contains("frame_5.html")) {
					frame5 = frame;
					break;
				}
			}
			if (frame5 != null) {
				// Interact with elements inside frame_5
				frame5.locator("//input[@name='mytext5']").fill("args");
				frame5.locator("//a[normalize-space()='https://a9t9.com']").click();

//				Locator logo = frame5.locator("//img[@alt='Ui.Vision by a9t9 software - Image-Driven Automation']");
//				logo.waitFor(new Locator.WaitForOptions().setTimeout(Duration.ofSeconds(5).toMillis()));
//				System.out.println(logo.isVisible());

				frame5.waitForSelector("//img[@alt='Ui.Vision by a9t9 software - Image-Driven Automation']");
				Locator logo = frame5.locator("//img[@alt='Ui.Vision by a9t9 software - Image-Driven Automation']");
//				logo.waitFor(new Locator.WaitForOptions().setTimeout(Duration.ofSeconds(5).toMillis()));
				System.out.println(logo.isVisible());
			}
//			page.pause();
			// Wait for the logo to appear after navigating to the link
//			Locator logo = page.locator("//img[@alt='Ui.Vision by a9t9 software - Image-Driven Automation']");
//			logo.waitFor(new Locator.WaitForOptions().setTimeout(Duration.ofSeconds(5).toMillis()));

			// Verify if the logo is displayed
//			System.out.println(logo.isVisible());

			// Close the browser
			browser.close();
		}
	}
}
