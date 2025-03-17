package day47;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

public class LoginPage2 {
	private Page page;

	/* constructor */
	public LoginPage2(Page page) {
		this.page = page;
	}

	/* locators */
	private Locator txt_username() {
		return page.locator("//input[@placeholder='Username']");
	}

	private Locator txt_password() {
		return page.locator("//input[@placeholder='Password']");
	}

	private Locator button_login() {
		return page.locator("//button[normalize-space()='Login']");
	}

	/* action methods */
	public void setUsername(String user) {
		txt_username().fill(user);
	}

	public void setPassword(String pwd) {
		txt_password().fill(pwd);
	}

	public void clickLogin() {
		button_login().click();
	}
}
