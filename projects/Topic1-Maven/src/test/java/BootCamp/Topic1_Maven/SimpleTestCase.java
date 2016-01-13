package BootCamp.Topic1_Maven;

import junit.framework.TestCase;

public class SimpleTestCase extends TestCase {

	public void testOK() {
		assertTrue(true);
	}
	
	public void testAdd(){
		int value1 = 1;
		int value2 = 2;
		assertEquals(value1+value2, Main.add(value1,value2));
	}
}
