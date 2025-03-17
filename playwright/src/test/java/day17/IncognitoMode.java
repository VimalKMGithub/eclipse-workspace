package day17;

import com.microsoft.playwright.*;
import java.nio.file.Paths;
import java.util.Arrays;

public class IncognitoMode {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			// Path to store temporary user data (ensures incognito behavior)
			String tempUserDataDir = System.getProperty("user.dir") + "\\user-data";

			// Launch a persistent context in incognito mode
			BrowserContext context = playwright.chromium().launchPersistentContext(Paths.get(tempUserDataDir),
					new BrowserType.LaunchPersistentContextOptions().setHeadless(false)
							.setArgs(Arrays.asList("--incognito")) // Explicit incognito argument
			);

			// Open a new page
			Page page = context.pages().get(0);

			// Navigate to the URL
			page.navigate("https://demo.opencart.com/");

			// Get the page title
			String title = page.title();

			// Validate the title
			if (title != null) {
				if (title.equals("Your Store")) {
					System.out.println("Passed...");
				} else {
					System.out.println("Failed...");
				}
			} else {
				System.out.println("No title");
			}

			page.pause();
			// Close the browser
			context.close();
		}
	}
}
