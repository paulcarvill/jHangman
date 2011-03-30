import org.junit.*;
import java.io.*;
import static org.junit.Assert.*;

import java.util.*;
  
public class JHangmanTest {

	// writing the sysout to an output stream means
	// i can test the output directly, rather than having to call
	// a separate getStatus method to test that my code
	// has had the required effect (e.g. changing the status)
	
	// BUT what does this mean for people using the class in another
	// non-command-line application?
	private static final String EOL = System.getProperty("line.separator");
	private ByteArrayOutputStream bytes;
	private PrintStream console;

	@Before
	public void setUp() {
		bytes = new ByteArrayOutputStream();
		console = System.out;
		System.setOut(new PrintStream(bytes));
	}

	@After
	public void tearDown() {
		System.setOut(console);
	}

	@Test(expected=Exception.class)
	public void testExceptionIfNoWordSupplied() throws Exception {
		JHangman.main("");
	}
	
	@Test
	public void testSuppliedWordIsObfuscatedCorrectly()  {
		JHangman jHangman = new JHangman("testing");
		assertEquals("???????", jHangman.getWorkingWord());
		
		JHangman anotherHangman = new JHangman("anothertest");
		assertEquals("???????????", anotherHangman.getWorkingWord());
	}
	
	@Test
	public void testWePopulateWordWithCorrectlyGuessedLetters() {
		JHangman jHangman = new JHangman("testing");
		jHangman.tryLetter('t');
		assertEquals("t??t???", jHangman.getWorkingWord());
		jHangman.tryLetter('g');
		assertEquals("t??t??g", jHangman.getWorkingWord());
	}
	
	@Test
	public void testIncorrectGuessIncrementsTryCount() {
		JHangman jHangman = new JHangman("testing");
		
		jHangman.tryLetter('x');
		assertEquals(String.format("???????%n1 try%n", EOL, EOL), bytes.toString());
		jHangman.tryLetter('x');
		assertEquals(String.format("???????%n1 try%n2 tries%n", EOL, EOL, EOL), bytes.toString());
	}
	
	@Test
	public void testGameOverIfMaxTriesReached() {
		JHangman jHangman = new JHangman("testing");
		jHangman.tryLetter('x');
		jHangman.tryLetter('y');
		jHangman.tryLetter('z');
		jHangman.tryLetter('p');
		assertEquals(String.format("???????%n1 try%n2 tries%ngame over%ngame over%n", EOL, EOL, EOL, EOL, EOL), bytes.toString());
	}
	
	@Test
	public void testGameWonIfwordGuessed() {
		JHangman jHangman = new JHangman("testing");
		jHangman.tryLetter('t');
		jHangman.tryLetter('e');
		jHangman.tryLetter('s');
		jHangman.tryLetter('i');
		jHangman.tryLetter('n');
		jHangman.tryLetter('g');
		assertEquals(String.format("???????%n0 tries%n0 tries%n0 tries%n0 tries%n0 tries%nyou win%n", EOL, EOL, EOL, EOL, EOL, EOL, EOL), bytes.toString());
	}
	
}







