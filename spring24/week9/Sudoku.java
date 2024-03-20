package spring24.week9;

import java.io.File;
import java.io.FileNotFoundException;
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

        //Scanner in = new Scanner(System.in);
            //System.out.print("Enter the name of the input file (do not include the file extension): ");
            //String inputFilename = in.nextLine();
            //System.out.print("Enter the name of the output file (do not include the file extension): ");
            //String outputFilename = in.nextLine();
        //in.close();

        Sudoku app = new Sudoku();
            Cell[][] grid = app.readPuzzle("input");
            app.solve(grid);
            System.out.println("The solution to the Sudoku puzzle is:");
            for(int i = 0; i < 9; i++) {
                for(int j = 0; j < 9; j++) {
                    System.out.print(grid[i][j].getValue());
                }
                System.out.println();
            }
            //app.saveSolution(outputFilename, grid);
    }

    /**
     * Given an input filename, read the Sudoku puzzle from the file and return it as a 9x9 grid numbers, with no value for spaces in the file.
     * 
     * @param filename
     * @return
     */
    public Cell[][] readPuzzle(String filename) {

        File f = new File("spring24/week9/" + filename + ".sdku");
        if(f == null || !f.exists() || f.isDirectory() || !f.canRead()) {
            System.out.println("The file " + filename + ".sdku does not exist or cannot be read.");
            System.out.println("The exact path to the file is: " + f.getAbsolutePath());
            return null;
        }

        try (Scanner in = new Scanner(f)) {
            Cell[][] grid = new Cell[9][9];
            for(int i = 0; i < 9; i++) {
                String line = in.nextLine();
                for(int j = 0; j < 9; j++) {
                    if(line.charAt(j) == '.') {
                        grid[i][j] = new Cell(i, j, 0);
                        continue;
                    }
                    
                    int value = Character.getNumericValue(line.charAt(j));
                    grid[i][j] = new Cell(i, j, value);
                }
            }

            return grid;
        } catch (FileNotFoundException e) {
            System.err.println("An error occurred while reading the file " + filename + ".sdku.");
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Given an output filename and a 9x9 grid of numbers, write the Sudoku puzzle to the file.
     * 
     * @param grid
     * @return
     */
    public void saveSolution(String filename, Cell[][] grid) {
        System.out.println("TODO: Write sudoku puzzle solution to " + filename + ".sdku");
        // TODO: Account for possible invalid solution from isSolved and handle an error.
        // TODO: Write the solution to the file.
    }

    /**
     * Given two arrays of numbers, return the intersection of the two arrays.
     * 
     * @param a
     * @param b
     * @return
     */
    private ArrayList<Integer> intersection(ArrayList<Integer> a, ArrayList<Integer> b) {
        ArrayList<Integer> intersection = new ArrayList<Integer>();
        for(int i = 0; i < a.size(); i++) {
            if(b.contains(a.get(i))) {
                intersection.add(a.get(i));
            }
        }

        return intersection;
    }

     /**
     * Determine what numbers are available to be placed in the given cell of the grid.
     * This is effectively an intersection of the numbers available in the row, column, and box of the cell.
     * 
     * @param grid
     * @param row
     * @param col
     * @return an array of numbers that are available to be placed in the given cell
     */
    private void getAvailableNumbers(Cell[][] grid, int row, int col) {
        ArrayList<Integer> intersection = intersection(getRowRemainingNumbers(grid, row), getColRemainingNumbers(grid, col));
        intersection = intersection(intersection, getBoxRemainingNumbers(grid, row, col));

        if(intersection.size() == 0) {
            return;
        } else if(intersection.size() == 1) {
            int value = intersection.get(0);

            grid[row][col].setValue(value);
            updatePossibleValues(grid, row, col);
            return;
        } else {
            // Join the arrays and find the intersection of the three arrays.
            ArrayList<Integer> availableNumbers = new ArrayList<Integer>();
            for(int i = 0; i < intersection.size(); i++) {
                availableNumbers.add(intersection.get(i));
            }

            grid[row][col].setPossibleValues(arrayListToArray(availableNumbers));
        }
    }   

    /**
     * Update the possible values for cells in the same row, column, and box as the given cell.
     * This should always be called once a cell's value has been set, to remove that value from the possible values of other cells.
     * 
     * @param grid
     * @param row
     * @param col
     */
    private void updatePossibleValues(Cell[][] grid, int row, int col) {
        int value = grid[row][col].getValue();

        for(int i = 0; i < 9; i++) {
            if(grid[row][i].getValue() == 0) {
                grid[row][i].removePossibleValue(value);
                if(grid[row][i].getPossibleValues().length == 1) {
                    grid[row][i].setValue(grid[row][i].getPossibleValues()[0]);
                    updatePossibleValues(grid, row, i);
                }
            }
            if(grid[i][col].getValue() == 0) {
                grid[i][col].removePossibleValue(value);
                if(grid[i][col].getPossibleValues().length == 1) {
                    grid[i][col].setValue(grid[i][col].getPossibleValues()[0]);
                    updatePossibleValues(grid, i, col);
                }
            }
        }

        int boxRow = row / 3;
        int boxCol = col / 3;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(grid[boxRow * 3 + i][boxCol * 3 + j].getValue() == 0) {
                    grid[boxRow * 3 + i][boxCol * 3 + j].removePossibleValue(value);
                }
            }
        }
    }

    /**
     * Solve the Sudoku puzzle.
     * 
     * @param grid
     */
    public void solve(Cell[][] grid) {
        // Continue until a valid solution is reached.
        while(!isValidSolution(grid)) {
            for(int i = 0; i < 9; i++) {
                for(int j = 0; j < 9; j++) {
                    if(grid[i][j].getValue() == 0) {
                        getAvailableNumbers(grid, i, j);
                    }
                }
            }
        }
    }

    // 

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
        int boxRow = row / 3;
        int boxCol = col / 3;
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
    private boolean isValidSolution(Cell[][] grid) {
        // Check that every cell has a value between 1 and 9.
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(grid[i][j].getValue() < 1 || grid[i][j].getValue() > 9) {
                    System.out.println("Cell at row " + i + " and column " + j + " has an invalid value.");
                    return false;
                }
            }
        }
        
        // Check that every row contains the numbers 1-9 exactly once.
        for(int i = 0; i < 9; i++) {
            int[] row = new int[9];
            for(int j = 0; j < 9; j++) {
                row[j] = grid[i][j].getValue();
            }
            if(!isValidSet(row)) {
                System.out.println("Row " + i + " is invalid.");
                return false;
            }
        }

        // Check that every column contains the numbers 1-9 exactly once.
        for(int i = 0; i < 9; i++) {
            int[] col = new int[9];
            for(int j = 0; j < 9; j++) {
                col[j] = grid[j][i].getValue();
            }
            if(!isValidSet(col)) {
                System.out.println("Column " + i + " is invalid.");
                return false;
            }
        }

        // Check that every 3x3 subgrid contains the numbers 1-9 exactly once.
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                int[] box = new int[9];
                for(int k = 0; k < 3; k++) {
                    for(int l = 0; l < 3; l++) {
                        box[k * 3 + l] = grid[i * 3 + k][j * 3 + l].getValue();
                    }
                }
                if(!isValidSet(box)) {
                    System.out.println("Box at row " + i + " and column " + j + " is invalid.");
                    return false;
                }
            }
        }

        return true;
    }
    
    /**
     * Given an array of 9 numbers, return true if the array contains the numbers 1-9 exactly once, and false otherwise.
     * 
     * @param set an array of 9 numbers
     * @return true if the array contains the numbers 1-9 exactly once, and false otherwise
     */
    private boolean isValidSet(int[] set) {
        boolean[] found = new boolean[9];
        for(int i = 0; i < 9; i++) {
            if(set[i] < 1 || set[i] > 9) {
                return false;
            } else if(found[set[i] - 1]) {
                return false;
            } else {
                found[set[i] - 1] = true;
            }
        }
        return true;
    }
}