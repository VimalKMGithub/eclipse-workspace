package day16;

import com.microsoft.playwright.*;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUpload {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();
			page.setViewportSize(1920, 1080);

			page.navigate("https://davidwalsh.name/demo/multiple-file-upload.php");
			page.locator("body").waitFor(); // Ensures page is fully loaded
//			page.pause();
			// Maximize window (optional, depending on the environment)
//			page.evaluate("window.resizeTo(screen.width, screen.height);");

			/* single file */
			// Upload a single file
			// page.locator("//input[@id='filesToUpload']").setInputFiles(Paths.get("C:\\Users\\vimal\\Documents\\Cv\\ss.png"));

			/* validating single file */
			// if (page.locator("//ul[@id='fileList']//li").textContent().equals("ss.png"))
			// {
			// System.out.println("uploaded..");
			// }

			/* multiple file */
			/* 1st method: Uploading files without any options */
			// page.locator("//input[@id='filesToUpload']").setInputFiles(Paths.get("C:\\Users\\vimal\\Documents\\Cv\\ss.png"),
			// Paths.get("C:\\Users\\vimal\\Documents\\Cv\\ss2.png"));

			/* 2nd method: Use Locator.SetInputFilesOptions */
			String f1 = "C:\\Users\\vimal\\Documents\\Cv\\ss.png";
			String f2 = "C:\\Users\\vimal\\Documents\\Cv\\ss2.png";

			page.locator("//input[@id='filesToUpload']").setInputFiles(new Path[] { Paths.get(f1), Paths.get(f2) });

			int nf = page.locator("//ul[@id='fileList']//li").count();
			if (nf == 2) {
				System.out.println("All files uploaded");
			}

			/* validating file names */
			if (page.locator("//ul[@id='fileList']//li[1]").textContent().equals("ss.png")
					&& page.locator("//ul[@id='fileList']//li[2]").textContent().equals("ss2.png")) {
				System.out.println("Names are matching...");
			}

			// Close the browser
			browser.close();
		}
	}
}
