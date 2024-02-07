package spring24.week4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Challenge {

	
	/**
	 * CHALLENGE 1:
	 * Read and store the contents of a file into a String array for further processing.
	 * 
	 * @param f
	 * @return
	 */
	public static String[] readFile(File f) {
		if(f == null) return null; // Catch if the File does not exist.
		
		// TODO: Create a variable, potentially an object, to store Strings that are read in from the file.
		
		try (Scanner s = new Scanner(f)){
			// TODO: Populate the variable from above with Strings from the file.
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return null; // TODO: Return the variable.
	}
	
	/**
	 * CHALLENGE 2:
	 * Given an input String array, store each token in an ArrayList.
	 * 
	 * A token is a single word, symbol, or character, surrounded on both sides by a space.
	 * 
	 * @param s
	 * @return
	 */
	public static ArrayList<String> split(String[] s) {
		ArrayList<String> list = new ArrayList<String>();
		
		// TODO: Take the input String array s and add each separate word (separated by a space) to the ArrayList.
		
		return list;
	}
	
	/**
	 * CHALLENGE 3:
	 * Given an input ArrayList of tokens (assume all are valid integers, doubles, or operations ('+','-','*','/','%','(', and ')')),
	 * calculate the result using correct order of operations.
	 * 
	 * HINT: Think of a data structure that might be useful for this.
	 * 
	 * @param tokens
	 */
	public static double calculate(ArrayList<String> tokens) {
		return 0.0; // TODO: Implement this method.
	}
	
	public static boolean testChallenge1(String[] s) {
		return false; // TODO
	}
	
	public static boolean testChallenge2(ArrayList<String> list) {
		return false; // TODO
	}
	
	public static boolean testChallenge3(double result) {
		return false; // TODO
	}
	
	public static void main(String[] args) {
		
	}
}