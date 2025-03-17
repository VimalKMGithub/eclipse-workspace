package day43;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AnnotationsDemo2 {
	@BeforeClass
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

	@AfterClass
	public void logout() {
		System.out.println("Logout..");
	}
}