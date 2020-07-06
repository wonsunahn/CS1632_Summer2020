import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class StringOpsTest {
	
	/**
	 * Property to test: If lengths of s1 and s2 are different,
	 *                   StringOps.equals(s1, s2) returns false. 
	 * 
	 * @param s1 First string
	 * @param s2 Second string
	 */
	@Property(trials = 1000)
	public void testEquals(String s1, String s2) {
		// System.out.println("testEquals s1='" + s1 + "', s2='" + s2 + "'");
		// TODO: Fill in.
	}

	/**
	 * Property to test: If StringOps.isValidHTML(s) returns true,
	 *                   the number of <b> tags and </b> tags identical,
	 *                   and the number of <i> tags and </i> tags identical,
	 * 
	 * @param s1 First string
	 * @param s2 Second string
	 */
	@Property(trials = 1000)
	public void testIsValidHTML(String s) {
		// System.out.println("testIsValidHTML s='" + s + "'");
		// TODO: Fill in.
	}
}
