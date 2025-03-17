//package day17;
//
//import com.microsoft.playwright.*;
//
//import java.nio.file.Paths;
//import java.util.Arrays;
//
//public class EnableExtensionsPlaywright {
//	public static void main(String[] args) {
//		try (Playwright playwright = Playwright.create()) {
//			BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(false)
//					.setArgs(Arrays.asList("--disable-extensions-except="
//							+ Paths.get(System.getProperty("user.dir"), "CrxFiles", "adblocker.crx")
//									.toString()/*
//												 * , "--load-extension=" + Paths.get(System.getProperty("user.dir"),
//												 * "CrxFiles", "adblocker.crx") .toString()
//												 */));
//
//			Browser browser = playwright.chromium().launch(launchOptions);
//			BrowserContext context = browser.newContext();
//			Page page = context.newPage();
//
//			/* navigate to the desired URL */
//			page.navigate("https://demo.nopcommerce.com/");
//		}
//	}
//}

//package day17;
//
//import java.nio.file.Paths;

//import java.util.Arrays;
//
//import com.microsoft.playwright.*;
//
//public class EnableExtensionsPlaywright {
//	public static void main(String[] args) {
//		try (Playwright playwright = Playwright.create()) {
//			String userDataDir = "user-data"; // Directory to store user data
//			String pathToExtension = "C:\\Users\\vimal\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Extensions\\ohahllgiabjaoigichmmfljhkcfikeof"; // Absolute
//																																							// path
//																																							// to
//																																							// the
//																																							// unpacked
//																																							// extension
//			String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"; // Path to your local
//			// Chrome executable
//
//            BrowserType.LaunchPersistentContextOptions options = new BrowserType.LaunchPersistentContextOptions(userDataDir)
//                    .setHeadless(false) // Extensions require non-headless mode
//                    .setExecutablePath(chromePath)
//                    .setArgs(new String[] {
//                        "--disable-extensions-except=" + pathToExtension,
//                        "--load-extension=" + pathToExtension
//                    });
//
//			BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(false)
//					.setArgs(Arrays.asList("--disable-extensions-except=" + Paths.get(pathToExtension).toString()));
//
//			Browser browser = playwright.chromium().launch(launchOptions);
//			BrowserContext context = browser.newContext();
//			Page page = context.newPage();
//			page.navigate("chrome://extensions/"); // Optional: Verify extensions are loaded
//			page.navigate("https://example.com"); // Open your target page
//
//			// Perform your tests...
//			page.pause();
////			context.close();
//		}
//	}
//}

package day17;

import com.microsoft.playwright.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class EnableExtensionsPlaywright {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			// Directory to store user data
			Path userDataDir = Paths.get(System.getProperty("user.dir") + "\\user-data");
			String pathToExtension = "C:\\Users\\vimal\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Extensions\\ohahllgiabjaoigichmmfljhkcfikeof\\4.0.60_0";

			// Set up Browser options for persistent context
			BrowserType.LaunchPersistentContextOptions options = new BrowserType.LaunchPersistentContextOptions()
					.setHeadless(false).setArgs(Arrays.asList("--disable-extensions-except=" + pathToExtension,
							"--load-extension=" + pathToExtension));

			BrowserContext context = playwright.chromium().launchPersistentContext(userDataDir, options);

			// Listen for new pages opening
			context.onPage(page -> {
				page.close();
			});

			// Optionally, navigate to a specific page on the first detected page
			Page mainPage = context.pages().get(0); // The initial blank page
			mainPage.navigate("https://example.com");

			mainPage.pause(); // Pause for debugging
		}
	}
}
