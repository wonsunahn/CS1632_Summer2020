import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.*;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Rule;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@RunWith(JUnitQuickcheck.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MonkeySimTest {

	private ByteArrayOutputStream out;
	private PrintStream stdout;

	@Rule
    public Timeout globalTimeout = Timeout.millis(10000);
	
	@Before
	public void setUp() {
		out = new ByteArrayOutputStream();
		stdout = System.out;
		System.setOut(new PrintStream(out, false));
	}

	@After
	public void tearDown() {
		System.setOut(stdout);
	}

	/**
	 * Preconditions: MonkeySim.verbose == true
	 * Execution steps: Call MonkeySim.main("5")
	 * Postconditions: System output is equal to:
	 *                 //Round 1: Threw banana from Monkey (#5 / ID 223497) to Monkey (#16 / ID 223508)\n
	 *                 //Round 2: Threw banana from Monkey (#16 / ID 223508) to Monkey (#8 / ID 223500)\n
	 *                 //Round 3: Threw banana from Monkey (#8 / ID 223500) to Monkey (#4 / ID 223496)\n
	 *                 //Round 4: Threw banana from Monkey (#4 / ID 223496) to Monkey (#2 / ID 223494)\n
	 *                 //Round 5: Threw banana from Monkey (#2 / ID 223494) to Monkey (#1 / ID 223493)\n
	 *                 First monkey has the banana!\n
	 *                 Completed in 5 rounds.\n
	 */
	@Test
	public void testMain5() throws InfiniteLoopException {
		MonkeySim.verbose = true;
		
		MonkeySim.main(new String[] { Integer.toString(5) });

		String nl = System.getProperty("line.separator");
		assertEquals("Defect in the output for running simulation with argument 5",
				"//Round 1: Threw banana from Monkey (#5 / ID 223497) to Monkey (#16 / ID 223508)" + nl
						+ "//Round 2: Threw banana from Monkey (#16 / ID 223508) to Monkey (#8 / ID 223500)" + nl
						+ "//Round 3: Threw banana from Monkey (#8 / ID 223500) to Monkey (#4 / ID 223496)" + nl
						+ "//Round 4: Threw banana from Monkey (#4 / ID 223496) to Monkey (#2 / ID 223494)" + nl
						+ "//Round 5: Threw banana from Monkey (#2 / ID 223494) to Monkey (#1 / ID 223493)" + nl
						+ "First monkey has the banana!" + nl
						+ "Completed in 5 rounds." + nl,
				out.toString());
	}
	
	/**
	 * Preconditions: MonkeySim.verbose == false
	 * Execution steps: Call MonkeySim.main("1000000")
	 * Postconditions: Method returns before timeout of 100 ms.
	 */
	@Test(timeout=100)
	public void testMain1000000() throws InfiniteLoopException {
		MonkeySim.verbose = false;
		
		MonkeySim.main(new String[] { Integer.toString(1000000) });
	}

	/**
	 * Preconditions: MonkeySim.verbose == false
	 * Execution steps: Call MonkeySim.main("s")
	 * Postconditions: Call terminates without the InfiniteLoopException thrown 
	 * 
	 * @param s Integer argument to pass to MonkeySim.main
	 */
	@Property(trials = 100)
	public void testMainStochastic(@InRange(minInt = 1) int s) {
		MonkeySim.verbose = false;
		
		// TODO: implement by using a try/catch block and failing with:
		// fail("Error: Infinite loop exception thrown");
		// when the InfiniteLoopException is thrown.
		// It is important that you use that exact fail message for grading purposes.
	}
	
	/**
	 * Preconditions: MonkeySim.verbose == false
	 * Execution steps: Call MonkeySim.main("<FILL_IN_YOUR_NUMBER>")
	 * Postconditions: Call terminates without the InfiniteLoopException thrown 
	 */
	@Test
	public void testMainInfiniteLoop() {
		MonkeySim.verbose = false;
		
		// TODO: implement by using a try/catch block and failing with:
		// fail("Error: Infinite loop exception thrown");
		// when the InfiniteLoopException is thrown.
		// It is important that you use that exact fail message for grading purposes.
	}
	
}