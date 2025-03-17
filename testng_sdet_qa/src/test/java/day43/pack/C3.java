package day43.pack;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class C3 {
	@BeforeSuite
	public void bs() {
		System.out.println("Before suite..");
	}

	@Test
	public void t1() {
		System.out.println("T1 method from c3");
	}

	@AfterSuite
	public void as() {
		System.out.println("After suite..");
	}
}
