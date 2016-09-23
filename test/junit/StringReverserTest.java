package junit;

import static org.junit.Assert.*;
import junit.StringReverser;

import org.junit.Before;
import org.junit.Test;

public class StringReverserTest {

	private static final String STRING1 = "abcdefg";
	private static final String STRING1_REVERSE = "gfedcba";
	
	private static final String STRING2 = "This is a sentence.";
	private static final String STRING2_REVERSE = ".ecnetnes a si sihT";
	
	private static final String STRING3 = "";
	private static final String STRING3_REVERSE = "";
	
	private static final String STRING4 = " \n";
	private static final String STRING4_REVERSE = "\n ";
	
	private StringReverser stringReverser;
	
	@Before
	public void setUp() {
		this.stringReverser = new StringReverser();
	}
	
	@Test
	public void testReverse() {
		// test standard strings
		assertEquals("String 1 was not reversed properly", STRING1_REVERSE, stringReverser.reverse(STRING1));
		assertEquals("String 2 was not reversed properly", STRING2_REVERSE, stringReverser.reverse(STRING2));
		
		// test empty string.  NOTE that the MESSAGE is NOT necessary, 
		// unless you really want to put it in for your own benefit!
		assertEquals(STRING3_REVERSE, stringReverser.reverse(STRING3));
		
		// test whitespace
		assertEquals("Whitespace was not handled properly", STRING4_REVERSE, stringReverser.reverse(STRING4));
	}
	
	@Test
	public void testReverseNull() {
		try {
			stringReverser.reverse(null);
			fail("An IllegalArgumentException should have been thrown.");
		} catch (IllegalArgumentException e) {
			// we should have encountered this exception!
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testReverseNull1() {
		stringReverser.reverse(null);
	}
}
