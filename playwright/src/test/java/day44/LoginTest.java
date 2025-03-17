package day44;

import org.testng.annotations.Test;

public class LoginTest {
	@Test(groups = { "sanity" })
	public void loginByEmail() {
		System.out.println("Login by email..");
	}

	@Test(groups = { "sanity" })
	public void loginByFacebook() {
		System.out.println("Login by facebook..");
	}

	@Test(groups = { "sanity" })
	public void loginByTwitter() {
		System.out.println("Login by twitter..");
	}
}
