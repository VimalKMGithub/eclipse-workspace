package day44;

import org.testng.annotations.Test;

public class SignUpTest {
	@Test(groups = { "regression" })
	public void signUpByEmail() {
		System.out.println("Sign up by email..");
	}

	@Test(groups = { "regression" })
	public void signUpByFacebook() {
		System.out.println("Sign up by facebook..");
	}

	@Test(groups = { "regression" })
	public void signUpByTwitter() {
		System.out.println("Sign up by twitter..");
	}
}
