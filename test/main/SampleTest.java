package main;

import javax.swing.JButton;

import org.junit.Assert;
import org.junit.Test;

public class SampleTest extends Assert {
	@Test
    public void testSuccess() {
        assertTrue(true);
    }
	
	@Test
	public void swingTest() {
		JButton b = new JButton("Test...");
		assertEquals(b.getText(), "Test...");
	}
}
