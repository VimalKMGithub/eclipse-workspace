package day15;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.BoundingBox;

public class SliderDemo {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the website
			page.navigate("https://www.jqueryscript.net/demo/Price-Range-Slider-jQuery-UI/");
//			page.pause();
			// Locate the minimum slider
			Locator minSlider = page.locator("//*[@id=\"slider-range\"]//span[1]");

			// Get the bounding box of the minimum slider
			BoundingBox minBox = minSlider.boundingBox();
			if (minBox != null) {
				double minStartX = minBox.x + minBox.width / 2; // Start position (center of the handle)
				double minStartY = minBox.y + minBox.height / 2;

				// Drag the slider 100px to the right
				page.mouse().move(minStartX, minStartY); // Move mouse to the slider handle
				page.mouse().down(); // Press mouse button
				page.mouse().move(minStartX + 100, minStartY); // Drag horizontally by 100px
				page.mouse().up(); // Release mouse button

				System.out.println("Moved Min Slider: " + minSlider.boundingBox());
			}

			// Locate the maximum slider
			Locator maxSlider = page.locator("//*[@id=\"slider-range\"]//span[2]");

			// Get the bounding box of the maximum slider
			BoundingBox maxBox = maxSlider.boundingBox();
			if (maxBox != null) {
				double maxStartX = maxBox.x + maxBox.width / 2; // Start position (center of the handle)
				double maxStartY = maxBox.y + maxBox.height / 2;

				// Drag the slider 100px to the left
				page.mouse().move(maxStartX, maxStartY); // Move mouse to the slider handle
				page.mouse().down(); // Press mouse button
				page.mouse().move(maxStartX - 100, maxStartY); // Drag horizontally by 100px to the left
				page.mouse().up(); // Release mouse button

				System.out.println("Moved Max Slider: " + maxSlider.boundingBox());
			}
			page.pause();
		}
	}
}