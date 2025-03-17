package day18;

import com.microsoft.playwright.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BrokenLinks {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

			// Create a new page and set timeout
			Page page = browser.newPage();
//			page.setDefaultTimeout(30000); // Timeout for finding elements

			// Navigate to the website
			page.navigate("http://www.deadlinkcity.com/");

			// Find all links on the page
			List<Locator> links = page.locator("a").all();

			System.out.println("Total links: " + links.size());

			int brokenCount = 0;

			// Base URL to resolve relative URLs
			String baseUrl = "http://www.deadlinkcity.com/";

			for (Locator link : links) {
				String href = link.getAttribute("href").toString();

				if (href == null || href.isEmpty()) {
					System.out.println("Null or empty can't check this link");
					continue;
				}

				// Handle relative URLs by appending the base URL
				if (!href.startsWith("http://") && !href.startsWith("https://")) {
					href = baseUrl + href;
				}

				try {
					// Open connection and check HTTP status
					URL url = new URL(href);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.connect();

					if (conn.getResponseCode() >= 400) {
						System.out.println(href + " => broken link");
						brokenCount++;
					} else {
						System.out.println(href + " => not a broken link");
					}

				} catch (java.net.MalformedURLException e) {
					System.out.println("Malformed URL: " + href);
				} catch (java.net.UnknownHostException e) {
					System.out.println("Unknown host: " + href);
				} catch (Exception e) {
					System.out.println("Error checking link: " + href);
					e.printStackTrace();
				}
			}

			System.out.println("Total broken links: " + brokenCount);

			// Close the browser
			browser.close();
		}
	}
}
