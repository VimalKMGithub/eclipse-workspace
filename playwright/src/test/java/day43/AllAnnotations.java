package day43;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AllAnnotations {
	@BeforeSuite
	public void bs() {
		System.out.println("Before suite ...");
	}

	@AfterSuite
	public void as() {
		System.out.println("After suite ...");
	}

	@BeforeTest
	public void bt() {
		System.out.println("Before test ...");
	}

	@AfterTest
	public void at() {
		System.out.println("After test ...");
	}

	@BeforeClass
	public void bc() {
		System.out.println("Before class ...");
	}

	@AfterClass
	public void ac() {
		System.out.println("After class ...");
	}

	@BeforeMethod
	public void bm() {
		System.out.println("Before method ...");
	}

	@AfterMethod
	public void am() {
		System.out.println("After method ...");
	}

	@Test
	public void t1() {
		System.out.println("T1 ...");
	}

	@Test
	public void t2() {
		System.out.println("T2 ...");
	}
}