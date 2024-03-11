package spring24.week4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Challenge {

	
	/**
	 * CHALLENGE 1:
	 * Read and store the contents of a file into a String or other type for further processing.
	 * 
	 * @param f
	 * @return
	 */
	public String readFile(File f) {
		if(f == null) return null; // Catch if the File does not exist.
        StringBuilder sb = new StringBuilder();
		
		try (Scanner s = new Scanner(f)){
            while(s.hasNextLine())
                sb.append(s.nextLine());

            return sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return "";
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
	public ArrayList<String> split(String s) {
		ArrayList<String> list = new ArrayList<String>();
        String[] tokens = s.split(" ");

        for(int i = 0; i < tokens.length; i++)
            list.add(tokens[i]);
		
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
	public double calculate(ArrayList<String> tokens) {
        Stack numbers = new Stack();
        Stack operations = new Stack();
        double r = 0.0;

        for(int i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);

            // Add the token to the number or operation stack.
            if(!token.equals("(") && !token.equals(")")) {
                if(isOperation(token)) operations.insert(token);
                else numbers.insert(token);

                continue;
            } 

            // If the token is an opening parenthesis, evaluate the expression inside the matching closing parenthesis.
            if(token.equals("(")) {
                int deepness = 1;
                int startIndex = i + 1;

                // Find the matching closing parenthesis.
                for(int j = startIndex; j < tokens.size(); j++) {
                    if(tokens.get(j).equals("(")) deepness++;
                    if(tokens.get(j).equals(")")) deepness--;

                    if(deepness == 0) {
                        // Evaluate the expression inside the parenthesis.
                        double result = calculate(new ArrayList<String>(tokens.subList(startIndex, j)));
                        numbers.insert(Double.toString(result));

                        // Skip the rest of the parenthesis.
                        i = j;
                        break;
                    }
                }
            } 
        }

        // Evaluate the remaining operations and numbers.
        while(numbers.isNotEmpty() && operations.isNotEmpty()) {
            r = evaluate(numbers.pop(), operations.pop(), numbers.pop());
            numbers.insert(Double.toString(r));
        }

        // Return the result, rounded to 4 decimal places.
        return Math.round(r * 10000.0) / 10000.0;
	}

    private double evaluate(String a, String o, String b) {
        double x = Double.parseDouble(a);
        double y = Double.parseDouble(b);
        char op = o.charAt(0);

        switch(op) {
            case '+':
                return x + y;
            case '-':
                return x - y;
            case '*':
                return x * y;
            case '/':
                return x / y;
            case '%':
                return x % y;
        }

        return 0.0;
    }

    private boolean isOperation(String token) {
        String[] allowedOperations = {
            "+",
            "-",
            "*",
            "/",
            "%"
        };

        // Determine if the token is an operation symbol.
        for(int i = 0; i < allowedOperations.length; i++)
            if(token.equals(allowedOperations[i])) return true;

        return false;
    }
	
    private class Stack {
        private Node head;
        private Node tail;

        private int size;

        public Stack() {
            head = null;
            tail = null;
            size = 0;
        }

        public Stack(String value) {
            head = new Node(value);
            tail = null;
            size = 1;
        }

        public int size() {
            return size;
        }

        public void insert(String value) {
            Node next = new Node(value);
            if(head == null) {
                head = next;
                size++;
                return;
            }

            if(tail == null) {
                tail = next;
                head.next = tail;
                size++;
                return;
            }

            tail.next = next;
            tail = next;
            tail.next = null;
            size++;
        }

        public void add(String value) {
            Node next = new Node(value);
            if(head == null) {
                head = next;
                size++;
                return;
            }

            if(tail == null) {
                tail = next;
                head.next = tail;
                size++;
                return;
            }

            tail.next = next;
            tail = next;
            tail.next = null;
            size++;
        }

        public boolean isNotEmpty() {
            return size > 0;
        }

        public String pop() {
            if(head == null) {
                System.out.println("the stack has ran out of elements!");
                return "0";
            }

            Node next = head;
            if(head.next == null) {
                size --;
                head = null;
                tail = null;
                return next.value;
            }

            head = head.next;
            size--;

            return next.value;
        }

        public void print() {
            Node current = head;

            while(current != null) {
                System.out.println(current.value);
                current = current.next;
            }
        }

        private class Node {
            public String value;
            public Node next;

            public Node(String value) {
                this.value = value;
            }
        }
    }

	public boolean testChallenge1(String s) {
		return s.equals("( ( 5 + 5 ) * 5 * 3 ) / ( 5 * 3 + ( 8 * 4 ) )");
	}
	
	public boolean testChallenge2(ArrayList<String> list) {
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
			expected.add(")");
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
	
	public boolean testChallenge3(double result) {
		return result == 3.1915;
	}
	
	// Test driver for the three coding challenges.
	public static void main(String[] args) {
        Challenge c = new Challenge();

		File f = new File("Example.txt");
		String s = c.readFile(f);
        //System.out.println(s);
		ArrayList<String> list = c.split(s);
		double result = c.calculate(list);
		
		System.out.print("Challenge 1 - ");
		System.out.println(c.testChallenge1(s) ? "Correct!\n" : "Incorrect.\n");
		
		System.out.print("Challenge 2 - ");
		System.out.println(c.testChallenge2(list) ? "Correct!\n" : "Incorrect.\n");
		
		System.out.print("Challenge 3 - ");
		System.out.println(c.testChallenge3(result) ? "Correct!\n" : "Incorrect.\n");
	}
}
