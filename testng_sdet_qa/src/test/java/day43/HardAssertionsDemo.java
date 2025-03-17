package day43;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HardAssertionsDemo {
	@Test
	public void test() {
//		Assert.assertEquals("Abc", "Abc"); // pass
//		Assert.assertEquals(12 + 5, 17); // pass
//		Assert.assertEquals("123", 123); // fail
//		Assert.assertEquals(false, false); // pass
//
//		Assert.assertNotEquals(123, 123); // fail
//		Assert.assertNotEquals(123, 125); // pass
//
//		Assert.assertTrue(true); // pass
//		Assert.assertTrue(false); // fail
//		Assert.assertTrue(1 == 2); // fail
//
//		Assert.assertFalse(false); // pass
//		Assert.assertFalse(true); // fail

		/* directly fail */
		Assert.fail();
	}
}
