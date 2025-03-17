package day43;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HardVsSoftAssertion {
	// @Test
	public void test_hard() {
		System.out.println("Testing...");
		System.out.println("Testing...");

		Assert.assertEquals(1, 2); // will not execute bellow rows after fail

		System.out.println("Testing...");
		System.out.println("Testing...");
	}

	@Test
	public void test_soft() {
		System.out.println("Testing...");
		System.out.println("Testing...");

		SoftAssert as = new SoftAssert(); // soft assert only accessible from object of soft assert class
		as.assertEquals(1, 2); // will execute bellow rows even after fail

		System.out.println("Testing...");
		System.out.println("Testing...");

		as.assertAll(); // mandatory otherwise it will always pass
	}
}
