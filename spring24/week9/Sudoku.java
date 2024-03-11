package spring24.week9;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A Sudoku puzzle is a 9x9 grid of numbers, where each row, column, and 3x3 subgrid contains the numbers 1-9 exactly once.
 * 
 * The goal of this challenge is to write a program that can solve Sudoku puzzles.
 * 
 * Given an input .sdku file (a plain-text file containing the initial state of a Sudoku puzzle), find the solution to the puzzle and write the solution to a new .sdku file.
 * 
 * The input file will contain 9 lines, each containing 9 characters. Each character will be a digit (1-9) or a period (.), representing an empty cell.
 * 
 * The output file should contain the same 9x9 grid, with the empty cells filled in with the correct numbers.
 * 
 * For example, given the following input file:
 * 
 *      53..7....
 *      6..195...
 *      .98....6.
 *      8...6...3
 *      4..8.3..1
 *      7...2...6
 *      .6....28.
 *      ...419..5
 *      ....8..79
 * 
 * The output file should be:
 * 
 *     534678912
 *     672195348
 *     198342567
 *     859761423
 *     426853791
 *     713924856
 *     961537284
 *     287419635
 *     345286179
 * 
 * This challenge is broken up into parts. In Programming Club, we will split into teams of 3-4 people to work on each method.
 */
public class Sudoku {

    public static void main(String[] args) {
        System.out.println("Welcome to the Sudoku Solver!");
        System.out.println("Let's solve a Sudoku puzzle.");

        Scanner in = new Scanner(System.in);
            System.out.print("Enter the name of the input file (do not include the file extension): ");
            String inputFilename = in.nextLine();
            System.out.print("Enter the name of the output file (do not include the file extension): ");
            String outputFilename = in.nextLine();

        Sudoku app = new Sudoku();
            Cell[][] grid = app.readPuzzle(inputFilename);
            app.solve(grid);
            app.saveSolution(outputFilename, grid);
    }

    /**
     * Given an input filename, read the Sudoku puzzle from the file and return it as a 9x9 grid numbers, with no value for spaces in the file.
     * 
     * @param filename
     * @return
     */
    public Cell[][] readPuzzle(String filename) {
        System.out.println("TODO: Read sudoku puzzle solution from " + filename);
        // TODO: Account for possible invalid input file and handle an error.
        // TODO: Spaces in the file should be represented as '.' in the grid.
        // TOOD: Read the file's puzzle.
        return null;
    }
    
    /**
     * Given an output filename and a 9x9 grid of numbers, write the Sudoku puzzle to the file.
     * 
     * @param grid
     * @return
     */
    public void saveSolution(String filename, Cell[][] grid) {
        System.out.println("TODO: Write sudoku puzzle solution to " + filename);
        // TODO: Account for possible invalid solution from isSolved and handle an error.
        // TODO: Write the solution to the file.
    }

     /**
     * Determine what numbers are available to be placed in the given cell of the grid.
     * 
     * @param grid
     * @param row
     * @param col
     * @return an array of numbers that are available to be placed in the given cell
     */
    private void getAvailableNumbers(Cell[][] grid, int row, int col) {
        // TODO: Account for possible invalid inputs and handle an error.
        // TODO: Given the rules of Sudoku, determine what numbers are available to be placed in the given cell of the grid.
        // HINT: Use the getRowRemainingNumbers, getColRemainingNumbers, and getBoxRemainingNumbers methods and save to the Cell.
        System.out.println("TODO: Get available numbers for cell at row " + row + " and column " + col);
    }   

    /**
     * Solve the Sudoku puzzle.
     * 
     * @param grid
     */
    public void solve(Cell[][] grid) {
        // TODO: Account for possible invalid inputs and handle an error.
        // TODO: Given the rules of Sudoku, solve the puzzle.

        // HINT: Use the getAvailableNumbers method to find the available numbers for each cell.
        // HINT: Use the isSolved method to check if the puzzle is solved.
        // HINT: Assume the puzzle is solvable and has only one solution.
        System.out.println("TODO: Solve the Sudoku puzzle");
    }

    /**
     * Get any number between 1 and 9 that is not in the row.
     * 
     * @param grid
     * @param row
     * @return
     */
    private ArrayList<Integer> getRowRemainingNumbers(Cell[][] grid, int row) {
        ArrayList<Integer> remainingNumbers = new ArrayList<Integer>();
        for(int i = 1; i <= 9; i++) {
            boolean found = false;
            for(int j = 0; j < 9; j++) {
                if(grid[row][j].getValue() == i) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                remainingNumbers.add(i);
            }
        }

        return remainingNumbers;
    }

    /**
     * Get any number between 1 and 9 that is not in the column.
     * 
     * @param grid
     * @param col
     * @return
     */
    private ArrayList<Integer> getColRemainingNumbers(Cell[][] grid, int col) {
        ArrayList<Integer> remainingNumbers = new ArrayList<Integer>();
        for(int i = 1; i <= 9; i++) {
            boolean found = false;
            for(int j = 0; j < 9; j++) {
                if(grid[j][col].getValue() == i) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                remainingNumbers.add(i);
            }
        }

        return remainingNumbers;
    }
    
    /**
     * Get any number between 1 and 9 that is not in the box.
     * 
     * A box is a 3x3 subgrid of the 9x9 grid.
     * 
     * @param grid
     * @param box
     * @return
     */
    private ArrayList<Integer> getBoxRemainingNumbers(Cell[][] grid, int row, int col) {
        ArrayList<Integer> remainingNumbers = new ArrayList<Integer>();
        int boxRow = row % 3;
        int boxCol = col % 3;
        for(int i = 1; i <= 9; i++) {
            boolean found = false;
            for(int j = 0; j < 3; j++) {
                for(int k = 0; k < 3; k++) {
                    if(grid[boxRow * 3 + j][boxCol * 3 + k].getValue() == i) {
                        found = true;
                        break;
                    }
                }
            }
            if(!found) {
                remainingNumbers.add(i);
            }
        }

        return remainingNumbers;
    }

    /**
     * Given a list of numbers, return an array of the numbers.
     * 
     * A helper method to keep the code using arrays instead of lists whenever possible.
     * 
     * @param list
     * @return
     */
    private int[] arrayListToArray(ArrayList<Integer> list) {
        int[] array = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * Given a 9x9 grid of numbers, return true if the grid is a valid Sudoku puzzle solution, and false otherwise.
     * 
     * A valid Sudoku puzzle is one where each row, column, and 3x3 subgrid contains the numbers 1-9 exactly once.
     * 
     * @param grid a 9x9 grid of numbers
     * @return true if the grid is a valid Sudoku puzzle, and false otherwise
     */
    private boolean isSolved(Cell[][] grid) {
        return false; // TODO: I need to do this before club.
    }
}