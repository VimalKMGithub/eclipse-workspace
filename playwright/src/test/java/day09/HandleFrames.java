//package day09;
//
//import com.microsoft.playwright.*;
//
//public class HandleFrames {
//	public static void main(String[] args) {
//		// Create a Playwright instance
//		try (Playwright playwright = Playwright.create()) {
//			// Launch the browser (Chromium in this case)
//			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//			BrowserContext context = browser.newContext();
//			Page page = context.newPage();
//
////			// Navigate to the demo page
//			page.navigate("https://ui.vision/demo/webtest/frames/");
////
////			// Wait for the frames to load
////			page.waitForSelector("frame[src='frame_1.html']");
////
////			// Use frameLocator to interact with the elements inside the frame
////			Locator frame1Locator = page.frameLocator("frame[src='frame_1.html']").locator("input[name='mytext1']");
////			frame1Locator.fill("args");
////
////			// Switch back to the default content (main page)
////			page.bringToFront();
////
////			// Wait and interact with Frame 2
////			page.waitForSelector("frame[src='frame_2.html']");
////			Locator frame2Locator = page.frameLocator("frame[src='frame_2.html']").locator("input[name='mytext2']");
////			frame2Locator.fill("args");
////
////			// Switch back to the default content
////			page.bringToFront();
//
////			// Handle Frame 3 (frame inside a frame)
////			page.waitForSelector("frame[src='frame_3.html']");
////			Locator frame3Locator = page.frameLocator("frame[src='frame_3.html']").locator("input[name='mytext3']");
////			frame3Locator.fill("args");
//
//			// Handle Frame 3 (frame inside a frame)
//			page.waitForSelector("frame[src='frame_3.html']");
//			Frame pFrame = page.frame("frame[src='frame_3.html']");
//			pFrame.locator("input[name='mytext3']").fill("args");
//
//			pFrame.waitForSelector(
//					"iframe[src='https://docs.google.com/forms/d/1yfUq-GO9BEssafd6TvHhf0D6QLDVG3q5InwNE2FFFFQ/viewform?embedded=true']");
//			Frame fFrame = pFrame.childFrames().get(0);
//			fFrame.locator("//div[@id='i9']//div[@class='AB7Lab Id5V1']").click();
//
////			// Switch back to the default content
////			page.bringToFront();
////
////			// Execute JavaScript within the main page (outside the frames)
////			page.locator("//div[@id='i9']//div[@class='AB7Lab Id5V1']").click();
////
////			// Optionally, pause to inspect the browser state
//			page.pause();
////
////			// Close the browser after the operations
////			browser.close();
//		}
//	}
//}

//package day09;
//
//import com.microsoft.playwright.*;
//
//public class HandleFrames {
//    public static void main(String[] args) {
//        // Create a Playwright instance
//        try (Playwright playwright = Playwright.create()) {
//            // Launch the browser (Chromium in this case)
//            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//            BrowserContext context = browser.newContext();
//            Page page = context.newPage();
//
//            // Navigate to the demo page
//            page.navigate("https://ui.vision/demo/webtest/frames/");
//
//            // Wait for frame_3 to load
//            page.waitForSelector("frame[src='frame_3.html']");
//            FrameLocator pFrame = page.frameLocator("frame[src='frame_3.html']");
//
//            if (pFrame != null) {
//                // Interact with the first frame inside frame_3
//                pFrame.locator("input[name='mytext3']").fill("args");
//
//                // Wait for the iframe inside pFrame (Google Form iframe)
//                pFrame.waitForSelector("iframe[src='https://docs.google.com/forms/d/1yfUq-GO9BEssafd6TvHhf0D6QLDVG3q5InwNE2FFFFQ/viewform?embedded=true']");
//
//                // Access the child iframe
//                Frame childFrame = pFrame.childFrames().get(0); // Assuming the Google form iframe is the first child
//
//                if (childFrame != null) {
//                    // Interact with an element inside the child frame (Google form)
//                    childFrame.locator("//div[@id='i9']//div[@class='AB7Lab Id5V1']").click();
//                } else {
//                    System.out.println("Child frame (Google form) not found");
//                }
//            } else {
//                System.out.println("Parent frame (frame_3) not found");
//            }
//
//            // Optionally, pause to inspect the browser state
//            page.pause();
//
//            // Close the browser after the operations
//            browser.close();
//        }
//    }
//}

package day09;

import com.microsoft.playwright.*;

public class HandleFrames {
	public static void main(String[] args) {
		// Create a Playwright instance
		try (Playwright playwright = Playwright.create()) {
			// Launch the browser (Chromium in this case)
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to the demo page
			page.navigate("https://ui.vision/demo/webtest/frames/");

			// Wait for frame_3 to load
			page.waitForSelector("frame[src='frame_3.html']");

			// Loop through all frames and find the frame by its src
			Frame frame3 = null;
			for (Frame frame : page.frames()) {
				if (frame.url().contains("frame_3.html")) {
					frame3 = frame;
					break;
				}
			}

			if (frame3 != null) {
				// Interact with the first frame inside frame_3
				frame3.locator("input[name='mytext3']").fill("args");

				// Wait for the iframe inside frame_3 (Google Form iframe)
				frame3.waitForSelector(
						"iframe[src='https://docs.google.com/forms/d/1yfUq-GO9BEssafd6TvHhf0D6QLDVG3q5InwNE2FFFFQ/viewform?embedded=true']");

				// Access the child iframe (Google form iframe)
				Frame childFrame = frame3.childFrames().get(0); // Assuming the Google form iframe is the first child

				if (childFrame != null) {
					// Interact with an element inside the child frame (Google form)
//					childFrame.locator("//div[@id='i9']//div[@class='AB7Lab Id5V1']").click();
//					childFrame.evaluate("document.querySelector('div#i9 div.AB7Lab.Id5V1').click()");

					Locator chLocator = childFrame.locator("//div[@id='i9']//div[@class='AB7Lab Id5V1']");
					// Get the element handle for the locator
					ElementHandle chElement = chLocator.elementHandle();

					// Pass the element handle to evaluate and click on the element
					childFrame.evaluate("element => element.click()", chElement);
				} else {
					System.out.println("Child frame (Google form) not found");
				}
			} else {
				System.out.println("Parent frame (frame_3) not found");
			}

			// Optionally, pause to inspect the browser state
			page.pause();

			// Close the browser after the operations
			browser.close();
		}
	}
}
