/**
 * This project is a quizzing program for Russian language verb conjugations. It loads the verbs
 * and conjugations from a text file assembled by a python script that scrapes the English language wikitionary.
 * Each conjugation is in a separate file with aligned line numbers such that the nth element of i.e. the file 
 * "russ 1pl.txt" is the 1st person plural conjugation of the nth element of "Russian Verbs.txt".
 * 
 * @author Alan & Ziyang
 */
import java.io.File;
import java.net.URI;
import java.security.InvalidParameterException;
import java.util.Random;
import java.util.Scanner;
/**
 * This class is the main class.
 */
public class Flashcard {
	/** File with verbs in dictionary form. **/
	private static final String verbs = "Russian Verbs.txt";
	/** File with verbs in 1st person singular. **/
	private static final String verb1stPerson = "russ 1.txt";
	/** File with verbs in 1st person plural. **/
	private static final String verb1stPersonPl = "russ 1pl.txt";
	/** File with verbs in 2nd person singular. **/
	private static final String verb2ndPerson = "russ 2.txt";
	/** File with verbs in 2nd person plural. **/
	private static final String verb2ndPersonPl = "russ 2pl.txt";
	/** File with verbs in 3rd person singular. **/
	private static final String verb3rdPerson = "russ 3.txt";
	/** File with verbs in 3rd person plural. **/
	private static final String verb3rdPersonPl = "russ 3pl.txt";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//loops main function until user exits.
		while(showFlashcard()) {
		}
		System.out.println("Goodbye!");
	}
	
	/**
	 * Returns a random line from a file specified in a 2 element array. 
	 * The 2nd element is the line number in the file.
	 * @param filename
	 * @return Returns a random line and line number  from a file specified.
	 */
//TO DO
	public static String[] getRandomLine(String filename){
        String contentsFilePath;
        String[] dictionary;
		try {
			contentsFilePath = Flashcard.class.getClassLoader().getResource(filename).getFile();
			contentsFilePath = new URI(contentsFilePath).getPath();
            File contentsFile = new File(contentsFilePath);
            Scanner contentsScanner = new Scanner(contentsFile, "UTF-8");
            dictionary = contentsScanner.useDelimiter("\\A").next().split("\\s+");
            contentsScanner.close();
		} catch (Exception e) {
			throw new InvalidParameterException("Bad file path" + e);
		}
		int lineNum = (int)(Math.random()*dictionary.length);
		String ln = Integer.toString(lineNum + 1);
		return new String[]{dictionary[lineNum], ln};
	}
	
	/**
	 * Returns a line from a file specified.
	 * @param filename file to open
	 * @param line line number to grab
	 * @return Returns a random line from a file specified. Null if error.
	 */
//TO DO
	public static String getLine(String filename, int lineNumber){
        String contentsFilePath;
        String[] dictionary;
		try {
			contentsFilePath = Flashcard.class.getClassLoader().getResource(filename).getFile();
			contentsFilePath = new URI(contentsFilePath).getPath();
            File contentsFile = new File(contentsFilePath);
            Scanner contentsScanner = new Scanner(contentsFile, "UTF-8");
            dictionary = contentsScanner.useDelimiter("\\A").next().split("\\s+");
            contentsScanner.close();
		} catch (Exception e) {
			throw new InvalidParameterException("Bad file path" + e);
		}
		return dictionary[lineNumber - 1];
	}
	
	/**
	 * This is the main driver function. It should return false if the user quits or there is an error 
	 * (file not found).
	 * @return true if not error or user quit.
	 */
//TO DO - input scanner part.
	public static boolean showFlashcard() {
		//word to quiz on
		String[] wordAndLine = getRandomLine(verbs);
		String word = wordAndLine[0];
		String correctAnswer = word;
		int line = Integer.valueOf(wordAndLine[1]);
		boolean plural;
		Random rand = new Random();
		/* Choose random number between 1 and 6. 1 is 1st person, 2 is 2nd person,
		 *  3 is third, 4 is 1st person plural. 5th is 2nd person plural, 6th is 3rd person plural. */
		int person = rand.nextInt(5) + 1;
		if (person > 3 ) {
			plural = true;
		} else {
			plural = false;
		}
		
		//Prompt
		if (!plural) {
			if (person == 1) {
				System.out.println("1st person singular of \"" + word + "\":");
				correctAnswer = getLine(verb1stPerson, line);
			}
			if (person == 2) {
				System.out.println("2nd person singular of \"" + word + "\":");
				correctAnswer = getLine(verb2ndPerson, line);
			}
			if (person == 3) {
				System.out.println("3rd person singular of \"" + word + "\":");
				correctAnswer = getLine(verb3rdPerson, line);
			}
		} else {
			if (person == 4) {
				System.out.println("1st person plural of \"" + word + "\":");
				correctAnswer = getLine(verb1stPersonPl, line);
			}
			if (person == 5) {
				System.out.println("2nd person plural of \"" + word + "\":");
				correctAnswer = getLine(verb2ndPersonPl, line);
			}
			if (person == 6) {
				System.out.println("3rd person plural of \"" + word + "\":");
				correctAnswer = getLine(verb3rdPersonPl, line);
			}
		}
		//input scanner goes here:
		Scanner inputScanner = new Scanner(System.in);
		String userInput = inputScanner.nextLine();
		inputScanner.close();
		if (userInput.contains("q") || userInput.contains("Q")) {
			return false;
		}
		if (correctAnswer.equals(userInput)) {
			System.out.println("Correct!");
		} else {
			System.out.println("Incorrect.");
		}
		return true;
	}
	
}

