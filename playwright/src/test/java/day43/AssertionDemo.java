package day43;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AssertionDemo {
	@Test
	public void testTitle() {
		/* 1st method */
		String t1 = "Vimal", t2 = "Vimal";
//		Assert.assertEquals(t1, t2);

		/* 2nd method */
		if (t1.equals(t2)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}
}
