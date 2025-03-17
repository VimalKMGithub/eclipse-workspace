package day45;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class ParamTest {
	Playwright playwright;
	Browser browser;
	BrowserContext context;
	Page page;

	@BeforeClass
	@Parameters({ "browser", "url" })
	public void open(String br, String url) {
		playwright = Playwright.create();
		switch (br.toLowerCase()) {
		case "chrome":
			browser = playwright.chromium()
					.launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
			break;
		case "edge":
			browser = playwright.chromium()
					.launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false));
			break;
		case "firefox":
			browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		default:
			Assert.fail("Invalid browser specified");
			break;
		}
		context = browser.newContext();
		page = context.newPage();
		page.navigate(url);
	}

	@Test
	public void testLogo() {
		Locator logo = page.locator("img[alt='company-branding']");
		PlaywrightAssertions.assertThat(logo).isVisible();
	}

	@Test
	public void tsetTitle() {
		Assert.assertEquals(page.title(), "OrangeHRM");
	}

	@Test
	public void testUrl() {
		String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		Assert.assertEquals(page.url(), expectedUrl);
	}

	@AfterClass
	public void close() {
		if (playwright != null) {
			playwright.close();
		}
	}
}
