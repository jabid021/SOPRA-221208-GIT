package quest.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class DemoTest {

	@Test
	@Disabled
	void test2() {
		assertTrue(true);
	}

	@Test
	void test() {
		int a = 2;
		int b = 1;
		assertEquals(3, a + b);
	}

}
