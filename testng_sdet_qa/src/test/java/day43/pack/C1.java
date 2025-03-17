package day43.pack;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class C1 {
	@Test
	public void test1() {
		System.out.println("Test1 from c1");
	}

	@BeforeTest
	public void bt() {
		System.out.println("Before test");
	}
}
