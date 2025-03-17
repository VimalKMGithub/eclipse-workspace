package day44;

import org.testng.annotations.Test;

public class PaymentTest {
	@Test(groups = { "regression", "sanity", "functional" })
	public void payInRupees() {
		System.out.println("Paying in rupees..");
	}

	@Test(groups = { "regression", "sanity", "functional" })
	public void payInDollar() {
		System.out.println("Paying in dollar..");
	}
}
