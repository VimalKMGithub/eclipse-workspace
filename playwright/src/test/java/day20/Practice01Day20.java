package day20;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Practice01Day20 {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			page.navigate("https://www.cit.com/cit-bank/resources/calculators/certificate-of-deposit-calculator");

			String fname = "practice_data_day20.xlsx";
			String fpath = System.getProperty("user.dir") + "\\src\\test\\resources\\xlsx_files\\" + fname;
			String shname = "Sheet1";

			int rows = ExcelUtils.getRowCount(fpath, shname);
			for (int i = 1; i <= rows; i++) {
				String pri = ExcelUtils.getCellData(fpath, shname, i, 0);
				String len = ExcelUtils.getCellData(fpath, shname, i, 1);
				String rate = ExcelUtils.getCellData(fpath, shname, i, 2);
				String comp = ExcelUtils.getCellData(fpath, shname, i, 3);
				String exval = ExcelUtils.getCellData(fpath, shname, i, 4);

				page.locator("#mat-input-0").click();
				page.locator("#mat-input-0").fill(""); // Clear previous value
				page.locator("#mat-input-0").fill(pri); // Enter new value

				page.locator("#mat-input-1").click();
				page.locator("#mat-input-1").fill(""); // Clear previous value
				page.locator("#mat-input-1").fill(len); // Enter new value

				page.locator("#mat-input-2").click();
				page.locator("#mat-input-2").fill(""); // Clear previous value
				page.locator("#mat-input-2").fill(rate); // Enter new value

				// Select compounding option
				page.click("#mat-select-value-1");
				List<ElementHandle> optns = page.querySelectorAll("#mat-select-0-panel .mat-option-text");

				for (ElementHandle optn : optns) {
					if (optn.innerText().equals(comp)) {
						optn.click();
						break;
					}
				}

				// Click on calculate button
				page.click("#CIT-chart-submit .mdc-button__ripple");

				// Get the calculated value
				String val = page.textContent("#displayTotalValue").trim();

				if (exval.equals(val)) {
					System.out.println("Test Passed...");
					ExcelUtils.setCellData(fpath, shname, i, 6, "Pass");
					ExcelUtils.fillGreenColor(fpath, shname, i, 6);
				} else {
					System.out.println("Test Failed...");
					ExcelUtils.setCellData(fpath, shname, i, 6, "Fail");
					ExcelUtils.fillRedColor(fpath, shname, i, 6);
				}
			}

			browser.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
