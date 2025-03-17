package day47;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
	private WebDriver driver;

	@BeforeClass
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
	}

	@Test
	public void testLogin() {
//		LoginPage loginPage = new LoginPage(driver);
		LoginPage2 loginPage = new LoginPage2(driver);
		loginPage.setUsername("admin");
		loginPage.setPassword("admin123");
		loginPage.clickLogin();

		Assert.assertEquals(driver.getTitle(), "OrangeHRM");
	}

	@AfterClass
	public void close() {
		driver.quit();
	}
}
