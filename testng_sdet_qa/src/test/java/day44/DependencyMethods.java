package day44;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DependencyMethods {
//	@Test
//	public void openapp() {
//		System.out.println("Opening app..");
//		Assert.assertTrue(true);
//	}
//
//	@Test(dependsOnMethods = { "openapp" })
//	public void login() {
//		System.out.println("Login..");
//		Assert.assertTrue(true);
//	}
//
//	@Test(dependsOnMethods = { "login" })
//	public void search() {
//		System.out.println("Search..");
//		Assert.assertTrue(false);
//	}
//
//	@Test(dependsOnMethods = { "login" })
//	public void advancesearch() {
//		System.out.println("Advance search..");
//		Assert.assertTrue(true);
//	}
//
//	@Test(dependsOnMethods = { "login" }, priority = Integer.MAX_VALUE)
//	public void logout() {
//		System.out.println("Logout..");
//		Assert.assertTrue(true);
//	}

	@Test
	public void openapp() {
		System.out.println("Opening app..");
		Assert.assertTrue(true);
	}

	@Test(dependsOnMethods = { "openapp" })
	public void login() {
		System.out.println("Login..");
		Assert.assertTrue(true);
	}

	@Test(dependsOnMethods = { "login" })
	public void search() {
		System.out.println("Search..");
		Assert.assertTrue(false);
	}

	@Test(dependsOnMethods = { "login", "search" })
	public void advancesearch() {
		System.out.println("Advance search..");
		Assert.assertTrue(true);
	}

	@Test(dependsOnMethods = { "login" }, priority = Integer.MAX_VALUE)
	public void logout() {
		System.out.println("Logout..");
		Assert.assertTrue(true);
	}
}
