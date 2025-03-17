package day20;

import java.io.IOException;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

public class FDCalculator {
	public static void main(String[] args) throws IOException {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			page.navigate(
					"https://www.moneycontrol.com/fixed-income/calculator/state-bank-of-india-sbi/fixed-deposit-calculator-SBI-BSB001.html");

			// Handle popup if it exists
			if (page.isVisible("#wzrk-cancel")) {
				page.click("#wzrk-cancel");
			}

			String fname = "test_data_day20_2.xlsx";
			String fpath = System.getProperty("user.dir") + "\\src\\test\\resources\\xlsx_files\\" + fname;

			int rows = ExcelUtils.getRowCount(fpath, "Sheet1");
			for (int i = 1; i <= rows; i++) {
				// Clear previous input by clicking reset button
				page.click("//div[@class='cal_div']//a[2]");

				// Read data from Excel
				String principle = ExcelUtils.getCellData(fpath, "Sheet1", i, 0);
				String rate = ExcelUtils.getCellData(fpath, "Sheet1", i, 1);
				String period1 = ExcelUtils.getCellData(fpath, "Sheet1", i, 2);
				String period2 = ExcelUtils.getCellData(fpath, "Sheet1", i, 3);
				String fre = ExcelUtils.getCellData(fpath, "Sheet1", i, 4);
				String exvalue = ExcelUtils.getCellData(fpath, "Sheet1", i, 5);

				// Pass data to fields
				page.fill("#principal", principle);
				page.fill("#interest", rate);
				page.fill("#tenure", period1);

				// Select options from dropdowns
				page.selectOption("#tenurePeriod", new SelectOption().setLabel(period2));
				page.selectOption("#frequency", new SelectOption().setLabel(fre));

				// Click Calculate button
				page.click("//div[@class='cal_div']//a[1]");

				// Fetch result
				String res = page.textContent("#resp_matval strong").trim();

				if (Double.parseDouble(exvalue) == Double.parseDouble(res)) {
					System.out.println("Test Passed...");
					ExcelUtils.setCellData(fpath, "Sheet1", i, 7, "Pass");
					ExcelUtils.fillGreenColor(fpath, "Sheet1", i, 7);
				} else {
					System.out.println("Test Failed...");
					ExcelUtils.setCellData(fpath, "Sheet1", i, 7, "Fail");
					ExcelUtils.fillRedColor(fpath, "Sheet1", i, 7);
				}
			}

			browser.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
