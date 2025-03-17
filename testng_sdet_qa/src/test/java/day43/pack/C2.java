package day43.pack;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class C2 {
	@Test
	public void test1() {
		System.out.println("Test1 from c2");
	}

	@AfterTest
	public void at() {
		System.out.println("After test");
	}
}
