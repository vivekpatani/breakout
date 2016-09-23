package junit;

/**
 * A simple class which has a single reverse method that takes
 * a String and reverses it.  It throws an
 * IllegalArgumentException if a null string is thrown.
 * 
 * @author Eric Westfall
 */
public class StringReverser {

	public String reverse(String string) {
		if (string == null) {
			throw new IllegalArgumentException("Cannot reverse a null String!");
		}
		StringBuffer buffer = new StringBuffer();
		for (int charIndex = 0; charIndex < string.length(); charIndex++) {
			buffer.insert(0, string.charAt(charIndex));
		}
		return buffer.toString();
	}
	
}
