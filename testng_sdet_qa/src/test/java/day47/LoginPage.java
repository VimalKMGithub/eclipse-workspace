package day47;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	private WebDriver driver;

	/* constructor */
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	/* locators */
	private By txt_username_loc = By.xpath("//input[@placeholder='Username']");
	private By txt_password_loc = By.xpath("//input[@placeholder='Password']");
	private By button_login_loc = By.xpath("//button[normalize-space()='Login']");

	/* action methods */
	public void setUsername(String user) {
		WebElement ele = driver.findElement(txt_username_loc);
		ele.clear();
		ele.sendKeys(user);
	}

	public void setPassword(String pwd) {
		WebElement ele = driver.findElement(txt_password_loc);
		ele.clear();
		ele.sendKeys(pwd);
	}

	public void clickLogin() {
		driver.findElement(button_login_loc).click();
	}
}