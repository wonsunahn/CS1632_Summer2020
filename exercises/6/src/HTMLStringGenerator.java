import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.pholser.junit.quickcheck.generator.*;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class HTMLStringGenerator extends Generator<String> {
	public HTMLStringGenerator() {
		super(String.class);
	}

	/**
	 * Overridden generate method. Generates a string that is a random sequence of
	 * "<b>", "</b>", "<i>", and "</i>" tags. The number of tags in the sequence is
	 * equal to status.size(). The next tag in the sequence is chosen using the
	 * method call random.nextInt(0, 3). A tag is chosen depending on the random
	 * number.
	 * 
	 * @param random QuickCheck random number generator (similar to
	 *               java.util.Random)
	 * @param status An object that can be used to influence the generated value.
	 *               For example, status.size() returns a value that
	 *               (probabilistically) increases for every successful string
	 *               generation. By using status.size() as the generated string
	 *               length, the generator starts with simple strings and
	 *               progressively tests longer strings.
	 * @return Generated random string
	 */
	@Override
	public String generate(SourceOfRandomness random, GenerationStatus status) {
		// TODO: Fill in.
		return "";
	}

	/**
	 * Overridden doShrink method. Returns a list of smaller strings to test, in the
	 * event that the larger string results in a test failure. If any of the smaller
	 * strings fail, QuickCheck shrinks the strings further by recursively calling
	 * the doShrink method. These are the two smaller strings to return in the list:
	 * 1. If there exists a <b> tag and also a </b> tag in larger, add a smaller
	 * string with the first occurrences of <b> and </b> tags removed respectively.
	 * 2. If there exists a <i> tag and also a </i> tag in larger, add a smaller
	 * string with the first occurrences of <i> and </i> tags removed respectively.
	 * 
	 * @param random QuickCheck random number generator (similar to
	 *               java.util.Random)
	 * @param larger The original larger string that you want to shrink to one or
	 *               more smaller strings
	 * @return List of shrunk smaller strings
	 */
	@Override
	public List<String> doShrink(SourceOfRandomness random, String larger) {
		// TODO: Fill in.
		// These are the two smaller strings to return in the list:
		// 1. If there exists a <b> tag and also a </b> tag in "larger", add a smaller
		// string with the first occurrences of <b> and </b> tags removed respectively.
		// 2. If there exists a <i> tag and also a </i> tag in "larger", add a smaller
		// string with the first occurrences of <i> and </i> tags removed respectively.
		return Collections.emptyList();
	}
}