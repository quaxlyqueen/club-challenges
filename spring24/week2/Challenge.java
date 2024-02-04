package spring24.week2;

/**
 * 
 * This weeks Coding Challenge is inspired by Leetcode problem 9.
 * 
 * A palindrome is defined as a String of characters which are the same forwards and backwards.
 * 		eg. 123321
 * 			asdfdsa
 * 			kayak
 * 
 * BONUS CHALLENGE: 
 * 
 * Allow for two modes, a "palindrome generator" and a "palindrome checker". The generator prompts the user to enter in any sequence of characters, and converts it into a palindrome.
 * The checker prompts the user to enter in any sequence of characters and then checks if it is a palindrome.
 * 
 */
public class Challenge {
	
	/**
	 * Determines if an input string is a palindrome.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isPalindrome(String input) {
		return false; // TODO
	}
	
	/**
	 * Tests for isPalindrome method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Challenge c = new Challenge();
		
		// Test 123321
		System.out.print("Palindrome 123321 answer is: ");
		System.out.println(c.isPalindrome("123321") ? "correct!" : "incorrect!");
	
		// Test asdfdsa
		System.out.print("Palindrome asdfdsa answer is: ");
		System.out.println(c.isPalindrome("asdfdsa") ? "correct!" : "incorrect!");
		
		// Test kayak
		System.out.print("Palindrome kayak answer is: ");
		System.out.println(c.isPalindrome("kayak") ? "correct!" : "incorrect!");
		
		// Test java
		System.out.print("Palindrome java answer is: ");
		System.out.println(c.isPalindrome("java") ? "incorrect!" : "correct!");
		
		// Test 1969
		System.out.print("Palindrome 1969 answer is: ");
		System.out.println(c.isPalindrome("1969") ? "incorrect!" : "correct!");
	}
}