package day42;

import org.testng.annotations.Test;

public class FirstTestCase {
	@Test(priority = 1)
	public void openApp() {
		System.out.println("Opening App..");
	}

	@Test(priority = 2)
	public void login() {
		System.out.println("Login..");
	}

	@Test(priority = 3)
	public void logout() {
		System.out.println("Logout..");
	}
}