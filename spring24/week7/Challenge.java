import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Challenge {
	
	/**
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
                sb.append(s.nextLine() + "\n");

            return sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	/**
     * Challenge 1:
     * Modify the split() method to account for multi-line input Strings.
     *
	 * Given an input String, store each token in an ArrayList.
	 * 
	 * A token is a single word, symbol, or character, surrounded on both sides by a space.
	 * 
	 * @param s
	 * @return
	 */
	public ArrayList<String>[] split(String s) {
		ArrayList<String> list = new ArrayList<String>();
        String[] tokens = s.split(" ");

        for(int i = 0; i < tokens.length; i++)
            list.add(tokens[i]);
		
		return null; // TODO: Return an ArrayList of tokens for each line in the input String.
	}
	
    //Stack utility class to be used in challenge 2.
    //This class is used to store the numbers and operations in the correct order to be evaluated.
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

	/**
     * Challenge 2:
     * Modify the calculate(), evaluate(), and isOperation() methods to ensure they calculate the result using correct order of operations, 
     * with additional allowed operations. Additionally, modify the calculate() method to account for an input array of ArrayLists of tokens.
     *
     * Assume the input ArrayList of tokens are all valid integers, doubles, or operations. The output should only contain 4 decimal places,
     * rounding up if the 5th place is greater than 5.
     *
     * The valid operations are: '+', '-', '*', '/', '%', '^', 'sin', 'cos', 'tan', 'log', 'ans', '(', and ')'.
     *
	 * @param tokens
	 */
	public double[] calculate(ArrayList<String>[] tokens) {
        // TODO: Use the commented code below to calculate the result of the expressions in the input array of ArrayList of tokens.
        /*
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
            // return Math.round(r * 10000.0) / 10000.0;
        */
        return null; // TODO: Return an array of the results of the expressions in the input array of ArrayList of tokens.
	}

    /**
     * Helper method for Challenge 2.
     *
     * Given a number, operation, and another number, evaluate the expression.
     *
     * @param a         the first number.
     * @param o         the operation.
     * @param b         the second number.
     * @return          the result of the expression.
     */
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

    /**
     * Helper method for Challenge 2.
     *
     * Given a token, determine if it is an operation symbol.
     *
     * @param token     the token to check.
     * @return          true if the token is an operation symbol, false otherwise.
     */
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

    // Not used in the final solution, but just for your reference.
	public boolean testReadFile(String s) {
		return s.equals("( ( 5 + 5 ) * 5 * 3 ) / ( 5 * 3 + ( 8 * 4 ) )\n" +
                "((5+5)*5*3)/(5*3+(8*4))\n" +
                "sin((19*3)^4+1)\n" +
                "cos((14*7)/14^2)\n" +
                "tan(3^3/3*3)\n" +
                "log(4739)+2\n" +
                "(4.239*192.4)-15.327\n" +
                "ans^2*3\n");
	}
	
	public boolean testChallenge1(ArrayList<String>[] list) {
        // The expected tokenized output of split() for the first ArrayList of tokens.
        ArrayList<String> expectedFirst = new ArrayList<>();
            expectedFirst.add("(");
            expectedFirst.add("(");
            expectedFirst.add("5");
            expectedFirst.add("+");
            expectedFirst.add("5");
            expectedFirst.add(")");
            expectedFirst.add("*");
            expectedFirst.add("5");
            expectedFirst.add("*");
            expectedFirst.add("3");
            expectedFirst.add(")");
            expectedFirst.add("/");
            expectedFirst.add("(");
            expectedFirst.add("5");
            expectedFirst.add("*");
            expectedFirst.add("3");
            expectedFirst.add("+");
            expectedFirst.add("(");
            expectedFirst.add("8");
            expectedFirst.add("*");
            expectedFirst.add("4");
            expectedFirst.add(")");
            expectedFirst.add(")");

        // The expected tokenized output of split() for the last ArrayList of tokens.
        ArrayList<String> expectedLast = new ArrayList<>();
            expectedLast.add("ans");
            expectedLast.add("^");
            expectedLast.add("2");
            expectedLast.add("*");
            expectedLast.add("3");

		if(expectedFirst.size() != list[0].size() || expectedLast.size() != list[list.length - 1].size()) {
			System.out.println("Your solution for Challenge 2 does not correctly tokenize! You have too few or too many elements.");
			return false;
		}
			
		for(int i = 0; i < expectedFirst.size(); i++)
			if(!(expectedFirst.get(i).equals(list[0].get(i)))) {
				System.out.println("Your solution for Challenge 2 does not correctly tokenize! On the first line, expected " + expectedFirst.get(i) + " but got " + list[0].get(i) + " at index " + i + ".");
				return false;
			}
		
		for(int i = 0; i < expectedLast.size(); i++)
			if(!(expectedLast.get(i).equals(list[list.length - 1].get(i)))) {
				System.out.println("Your solution for Challenge 2 does not correctly tokenize! On the last line, expected " + expectedLast.get(i) + " but got " + list[list.length - 1].get(i) + " at index " + i + ".");
				return false;
			}
		
		return true;
	}

    public boolean testChallenge2(double[] results) {
        double[] expected = {
            3.1915,
            3.1915,
            0.9903,
            1.0,
            0.5095,
            5.6757,
            800.2566,
            1921231.878
        };

        if(expected.length != results.length) {
            System.out.println("Your solution for Challenge 2 does not correctly calculate! You have too few or too many elements.");
            return false;
        }

        for(int i = 0; i < expected.length; i++)
            if(expected[i] != results[i]) {
                System.out.println("Your solution for Challenge 2 does not correctly calculate! Expected " + expected[i] + " but got " + results[i] + " at index " + i + ".");
                return false;
            }

        return true;
    }
	
	// Test driver for the three coding challenges.
	public static void main(String[] args) {
        Challenge c = new Challenge();

		File f = new File("Example.txt");
		String s = c.readFile(f);
        System.out.println("contents of the file: \n" + s);

        ArrayList<String>[] list = c.split(s);
        //double[] result = c.calculate(list);
		
        System.out.print("Challenge 1 - ");
		System.out.println(c.testChallenge1(list) ? "Correct!\n" : "Incorrect.\n");

		//System.out.print("Challenge 2 - ");
		//System.out.println(c.testChallenge2(result) ? "Correct!\n" : "Incorrect.\n");
	}
}
