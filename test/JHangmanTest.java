import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;
  
public class JHangmanTest {

	@Test(expected=Exception.class)
	public void testExceptionIfNoWordSupplied() throws Exception {
		JHangman.main(new String[] { "" });
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
		assertEquals("1 try", jHangman.getStatus());
		jHangman.tryLetter('y');
		assertEquals("2 tries", jHangman.getStatus());
	}
	
	@Test
	public void testGameOverIfMaxTriesReached() {
		JHangman jHangman = new JHangman("testing");
		jHangman.tryLetter('x');
		jHangman.tryLetter('y');
		jHangman.tryLetter('z');
		jHangman.tryLetter('p');
		assertEquals("game over", jHangman.getStatus());
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
		assertEquals("you win", jHangman.getStatus());
	}
	
}







