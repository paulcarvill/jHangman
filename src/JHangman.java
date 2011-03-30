import java.io.*;

public class JHangman {

	private static String word;
	private static String workingWord;
	private static String status;
	private static int numberOfTries;
	private static int MAX_NUMBER_OF_TRIES = 3;
	
	// constructor
	JHangman(String w) {
		word = w;
		workingWord = obfuscateWord();
		numberOfTries = 0;
		status = "";
		System.out.println(workingWord);
	}
	
	public void setWord(String w) {
		word = w;
	}
	
	public static void main(String... args) // String argv[]
		throws java.lang.Exception {
		
		if (args.length == 0) {
			throw new java.lang.Exception();
		}
		
		if (args[0] == "") {
			throw new java.lang.Exception();
		}
		
		final JHangman myInstance = new JHangman(args[0]);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inp;
		char letter;
		
		while(myInstance.getStatus() != "you win" & myInstance.getStatus() != "game over") {
			try {
				inp = br.readLine();
				try {
					letter = inp.charAt(0);
				}
				catch (StringIndexOutOfBoundsException oobe) {
					System.out.println("Oops, enter a character, please!");
					continue;
				}
				myInstance.tryLetter(letter);
				System.out.println(myInstance.getWorkingWord());
			} 
			catch (IOException ioe) {
				System.out.println("IO error trying to read your name!");
				System.exit(1);
			}
		}
		System.out.println("The word was " + word + "!");
	}
	
	private String obfuscateWord() {
		String j = "";
		for (int i = 0; i < word.length(); i++) {
			j += "?";
		}
		return j;
	}
	
	public static String getWorkingWord() {
		return workingWord;
	}
	
	public static String replaceCharAt(String s, int pos, char c) {
		StringBuffer buf = new StringBuffer( s );
		buf.setCharAt( pos, c );
		return buf.toString( );
	}

	public static void tryLetter(char letter) {
		Boolean tries = true;
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == letter) {
				tries = false;
				workingWord = replaceCharAt(workingWord, i, letter);
			}
		}
		if (tries) {
			numberOfTries++;
		}
		
		if (workingWord.indexOf('?') == -1) {
			numberOfTries = -1;
		}
		
		updateStatus();
	}
	
	private static void updateStatus() {
		String message = " try";

		if (numberOfTries == -1) {			
			status = "you win";
		} 
		else if (numberOfTries == 1) {
			status = Integer.toString(numberOfTries) + message;
		}
		else if (numberOfTries >= MAX_NUMBER_OF_TRIES) {
			status = "game over";
		}
		else {	
			message = " tries";
			status = Integer.toString(numberOfTries) + message;
		}
		System.out.println(status);
	}
	
	public static String getStatus() {
		return status;
	}

}

