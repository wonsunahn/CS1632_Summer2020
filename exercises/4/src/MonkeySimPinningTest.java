import java.util.*;
import java.io.*;
import java.lang.reflect.*;

import org.junit.runner.RunWith;
import org.junit.*;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MonkeySimPinningTest {
	MonkeyWatcher mw;
	List<Monkey> ml;
	private ByteArrayOutputStream out = new ByteArrayOutputStream();
	private PrintStream stdout;
	
	@Before
	public void setUp() {
		// Back up the old output stream
		stdout = System.out;
		// Redirect the output stream
		System.setOut(new PrintStream(out));
		
		// Force reset the monkey counter to 0 before each test case
		try {
			Field f = Monkey.class.getDeclaredField("monkeyNum");
			f.setAccessible(true);
			f.set(null, 0);
		} catch (Exception e) {
			fail(e.toString());
		}

		ml = new LinkedList<Monkey>();
		
		int s = 5;
		Monkey tmpMonkey;
		Banana b = new Banana();
		mw = new MonkeyWatcher();

		for (int i = 0; i < s + 1; i++) {
			tmpMonkey = new Monkey();
			ml.add(tmpMonkey);
		}
		ml.get(s).throwBananaTo(b);
	}
	
	@After
	public void tearDown() {
		System.setOut(stdout);
	}
	
	@Test
	public void testGetFirstMonkey() {
		Monkey m = MonkeySim.getFirstMonkey(ml);
		assertNotNull("getFirstMonkey returns null", m);
		assertEquals("getFirstMonkey returns a monkey with monkey != 1", 1, m.getMonkeyNum());
	}
	
	@Test
	public void testNextMonkeyAndResizeTo16() {
		MonkeySim.nextMonkeyAndResize(ml.get(5), ml);
		assertEquals("Monkey list size not 17 after resizing to monkey 16", 17, ml.size());
	}
	
	@Test
	public void testStringifyResults() {
		String ret = MonkeySim.stringifyResults(5, ml.get(2), ml.get(1));
		assertEquals("Defect when stringifying round 5, monkey 2, monkey 1", "//Round 5: Threw banana from Monkey (#2 / ID 223494) to Monkey (#1 / ID 223493)", ret);
	}
	
	@Test
	public void testMonkeyWithBanana() {
		int ret = MonkeySim.monkeyWithBanana(ml);
		assertEquals(5, ret);
	}
	
	@Test
	public void testArgument5RunSimulation() {
		MonkeySim.runSimulation(ml, mw);
		String nl = System.getProperty("line.separator");
		assertEquals("Defect in the output for running simulation with argument 5", "//Round 1: Threw banana from Monkey (#5 / ID 223497) to Monkey (#16 / ID 223508)" + nl + 
				"//Round 2: Threw banana from Monkey (#16 / ID 223508) to Monkey (#8 / ID 223500)" + nl +
				"//Round 3: Threw banana from Monkey (#8 / ID 223500) to Monkey (#4 / ID 223496)" + nl +
				"//Round 4: Threw banana from Monkey (#4 / ID 223496) to Monkey (#2 / ID 223494)" + nl +
				"//Round 5: Threw banana from Monkey (#2 / ID 223494) to Monkey (#1 / ID 223493)" + nl +
				"First monkey has the banana!" + nl, out.toString());
	}
}