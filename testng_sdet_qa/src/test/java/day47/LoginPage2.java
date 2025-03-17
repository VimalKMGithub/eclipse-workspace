package day47;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage2 {
	/* constructor */
	public LoginPage2(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/* locators */
	@FindBy(xpath = "//input[@placeholder='Username']")
	private WebElement txt_username;
	@FindBy(xpath = "//input[@placeholder='Password']")
	private WebElement txt_password;
	@FindBy(xpath = "//button[normalize-space()='Login']")
	private WebElement button_login;

	/* action methods */
	public void setUsername(String user) {
		txt_username.clear();
		txt_username.sendKeys(user);
	}

	public void setPassword(String pwd) {
		txt_password.clear();
		txt_password.sendKeys(pwd);
	}

	public void clickLogin() {
		button_login.click();
	}
}