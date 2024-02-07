package spring24.week4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Challenge {

	
	/**
	 * CHALLENGE 1:
	 * Read and store the contents of a file into a String or other type for further processing.
	 * 
	 * @param f
	 * @return
	 */
	public static String readFile(File f) {
		if(f == null) return null; // Catch if the File does not exist.
		
		// TODO: Create a variable, potentially an object like ArrayList or StringBuilder or even the humble String, to store Strings that are read in from the file.
		
		try (Scanner s = new Scanner(f)){
			// TODO: Populate the variable from above with Strings from the file.
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// TODO: Convert your variable to a String variable.
		
		return null; // TODO: Return the variable.
	}
	
	/**
	 * CHALLENGE 2:
	 * Given an input String, store each token in an ArrayList.
	 * 
	 * A token is a single word, symbol, or character, surrounded on both sides by a space.
	 * 
	 * @param s
	 * @return
	 */
	public static ArrayList<String> split(String s) {
		ArrayList<String> list = new ArrayList<String>();
		
		// TODO: Take the input String array s and add each separate word (separated by a space) to the ArrayList.
		
		return list;
	}
	
	/**
	 * CHALLENGE 3:
	 * Given an input ArrayList of tokens (assume all are valid integers, doubles, or operations ('+','-','*','/','%','(', and ')')),
	 * calculate the result using correct order of operations. The output should only contain 4 decimal places, rounding up if the 5th place
	 * is greater than 5.
	 * 
	 * HINT: Think of a data structure that might be useful for this.
	 * 
	 * @param tokens
	 */
	public static double calculate(ArrayList<String> tokens) {
		return 0.0; // TODO: Implement this method.
	}
	
	public static boolean testChallenge1(String s) {
		return s.equals("( ( 5 + 5 ) * 5 * 3 ) / ( 5 * 3 + ( 8 * 4 ) )");
	}
	
	public static boolean testChallenge2(ArrayList<String> list) {
		// Don't do this to answer Challenge 2. This is the only way to not give away a solution.
		ArrayList<String> expected = new ArrayList<>();
			expected.add("(");
			expected.add("(");
			expected.add("5");
			expected.add("+");
			expected.add("5");
			expected.add(")");
			expected.add("*");
			expected.add("5");
			expected.add("*");
			expected.add("3");
			expected.add("/");
			expected.add("(");
			expected.add("5");
			expected.add("*");
			expected.add("3");
			expected.add("+");
			expected.add("(");
			expected.add("8");
			expected.add("*");
			expected.add("4");
			expected.add(")");
			expected.add(")");
			
		if(expected.size() != list.size()) {
			System.out.println("Your solution for Challenge 2 does not correctly tokenize! You have too few or too many elements.");
			return false;
		}
			
		for(int i = 0; i < expected.size(); i++)
			if(!(expected.get(i).equals(list.get(i)))) {
				System.out.println("Your solution for Challenge 2 does not correctly tokenize! At index " + i + " (and maybe others) your output does not match the expected output.");
				return false;
			}
		
		return true;
	}
	
	public static boolean testChallenge3(double result) {
		return result == 3.1915;
	}
	
	// Test driver for the three coding challenges.
	public static void main(String[] args) {
		File f = new File("Example.txt");
		String s = readFile(f);
		ArrayList<String> list = split(s);
		double result = calculate(list);
		
		System.out.print("Challenge 1 - ");
		System.out.println(testChallenge1(s) ? "Correct!\n" : "Incorrect.\n");
		
		System.out.print("Challenge 2 - ");
		System.out.println(testChallenge2(list) ? "Correct!\n" : "Incorrect.\n");
		
		System.out.print("Challenge 3 - ");
		System.out.println(testChallenge3(result) ? "Correct!\n" : "Incorrect.\n");
	}
}