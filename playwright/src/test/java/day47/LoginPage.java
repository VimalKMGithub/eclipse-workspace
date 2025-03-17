package day47;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {
	private Page page;

	/* Constructor */
	public LoginPage(Page page) {
		this.page = page;
	}

	/* Locators */
	private String txt_username_loc = "//input[@placeholder='Username']";
	private String txt_password_loc = "//input[@placeholder='Password']";
	private String button_login_loc = "//button[normalize-space()='Login']";

	/* Action Methods */
	public void setUsername(String user) {
		Locator usernameField = page.locator(txt_username_loc);
		usernameField.fill(""); // Clear the field
		usernameField.fill(user); // Input username
	}

	public void setPassword(String pwd) {
		Locator passwordField = page.locator(txt_password_loc);
		passwordField.fill(""); // Clear the field
		passwordField.fill(pwd); // Input password
	}

	public void clickLogin() {
		page.locator(button_login_loc).click();
	}
}
