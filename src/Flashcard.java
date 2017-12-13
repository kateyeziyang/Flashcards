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
	
	private static int correctCounter;
	private static int incorrectCounter;
	private static boolean isPlural;
	private static int wordPerson;
	
	/** */
	private static String[] wordAnswer = new String[2];
	
	private static boolean isCorrect(String input) {
		if (input.equals(wordAnswer[1])) {
			correctCounter++;
			return true;
		} else {
			incorrectCounter++;
			return false;
		}
	}
	
	public Flashcard() {
		newWord();
		correctCounter = 0;
		incorrectCounter = 0;
	}
	
	public static String[] getWordAnswer(){
		return wordAnswer;
	}
	
	public static void newWord() {
		String[] wordAndLine = getRandomLine(verbs);
		int line = Integer.valueOf(wordAndLine[1]);
		wordAnswer[0] = wordAndLine[0];
				
		Random rand = new Random();
		/* Choose random number between 1 and 6. 1 is 1st person, 2 is 2nd person,
		 *  3 is third, 4 is 1st person plural. 5th is 2nd person plural, 6th is 3rd person plural. */
		wordPerson = rand.nextInt(5) + 1;
		if (wordPerson > 3 ) {
			isPlural = true;
		} else {
			isPlural = false;
		}
		if (!isPlural) {
			if (wordPerson == 1) {
				wordAnswer[1] = getLine(verb1stPerson, line);
			}
			if (wordPerson == 2) {
				wordAnswer[1] = getLine(verb2ndPerson, line);
			}
			if (wordPerson == 3) {
				wordAnswer[1] = getLine(verb3rdPerson, line);
			}
		} else {
			if (wordPerson == 4) {
				wordAnswer[1] = getLine(verb1stPersonPl, line);
			}
			if (wordPerson == 5) {
				wordAnswer[1] = getLine(verb2ndPersonPl, line);
			}
			if (wordPerson == 6) {
				wordAnswer[1] = getLine(verb3rdPersonPl, line);
			}
		}
	}
	
	public static int getPerson() {
		return wordPerson;
	}
	
	public static boolean getPlural() {
		return isPlural;
	}
	
	private static String getLine(String filename, int lineNumber){
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
	
	private static String[] getRandomLine(String filename){
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
	
	public static void printPrompt() {
		if (!isPlural) {
			if (wordPerson == 1) {
				System.out.println("1st person singular of \"" + wordAnswer[0] + "\":");
							}
			if (wordPerson == 2) {
				System.out.println("2nd person singular of \"" + wordAnswer[0] + "\":");
			}
			if (wordPerson == 3) {
				System.out.println("3rd person singular of \"" + wordAnswer[0] + "\":");
			}
		} else {
			if (wordPerson == 4) {
				System.out.println("1st person plural of \"" + wordAnswer[0] + "\":");
			}
			if (wordPerson == 5) {
				System.out.println("2nd person plural of \"" + wordAnswer[0] + "\":");
			}
			if (wordPerson == 6) {
				System.out.println("3rd person plural of \"" + wordAnswer[0] + "\":");
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//loops main function until user exits.
		System.out.println("This is a flashcard program for learning the conjugations of common Russian Verbs."
				+ " Enter q or Q at any time to quit.");
		Scanner inputScanner = new Scanner(System.in);
		String userInput = "";
		do {
			newWord();
			printPrompt();
			userInput = inputScanner.nextLine();
			if (isCorrect(userInput)) {
				System.out.println("Correct!");
			} else {
				System.out.println("Incorrect.");
			}
		} while (!userInput.contains("q") || userInput.contains("Q"));
			
		System.out.println("Goodbye!");
		inputScanner.close();
	}
}
	