package fall23.Week13;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * Provides code for the input and testing of the SNAFU Challenge. This
 * challenge and input is from Day 25, year 2022 from AdventOfCode.com.
 */
public class SNAFU {
	/**
	 * 
	 * @return returns an Iterable form of the input.
	 */
	public static Iterable<String> iterableInput() {
		return new Input();
	}

	/**
	 * Classes that implement Iterable tell Java that the class can be trusted to
	 * have an Iterator. A very common and nice use is in for loops. See, Arrays and
	 * a number of other datastructures implement Iterable, and you can stick any
	 * Iterable class in the for-each loop.
	 */
	private static class Input implements Iterable<String> {
		/**
		 * This is the only method required by Iterable.
		 */
		@Override
		public Iterator<String> iterator() {
			return new SNAFUIterator();
		}

		/**
		 * This is the manual version of the for each loop. There are two methods
		 * inside. Next() gives the next input (and iterates). HasNext() is called
		 * before to check if Next() can be safely run. The for loop basically keeps
		 * calling Next() while HasNext() is true. Next() is actually the input given to
		 * the left half of the for each loop.
		 * 
		 * In this case, this Iterator keeps going until the next line it has ready is
		 * null.
		 */
		private class SNAFUIterator implements Iterator<String> {
			private final static String PATH = "Input.txt";

			private BufferedReader reader;
			private String currLine;

			private SNAFUIterator() {
				try {
					reader = new BufferedReader(new FileReader(PATH));
					iterateToNextLine();
				} catch (FileNotFoundException e) {
					System.out.println("File Not Found");
					e.printStackTrace();
				}
			}

			private void iterateToNextLine() {
				try {
					currLine = reader.readLine();
				} catch (IOException e) {
					System.out.println("Error With Reader");
					e.printStackTrace();
				}
			}

			@Override
			public boolean hasNext() {
				return (currLine != null);
			}

			@Override
			public String next() {
				String returnStr = currLine;
				iterateToNextLine();
				return returnStr;
			}
		}
	}
}
