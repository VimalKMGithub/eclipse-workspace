package day43;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AnnotationsDemo1 {
	@BeforeMethod
	public void login() {
		System.out.println("Login..");
	}

	@Test(priority = 1)
	public void search() {
		System.out.println("Search..");
	}

	@Test(priority = 2)
	public void advanceSearch() {
		System.out.println("Advance search..");
	}

	@AfterMethod
	public void logout() {
		System.out.println("Logout..");
	}
}