package fall23.Week11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;

public class App {

    /**
     * The following iterative sequence is defined for the set of positive integers:
     *      n -> n/2 (if n is even)
     *      n -> 3n + 1 (if n is odd)
     * 
     * Using the rule above and starting with 13, we generate the following sequence:
     *      13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
     *
     * This sequence contains 10 terms. Although it hasn't been proven yet (see Collatz Problem),
     * it is thought that all starting numbers finish at 1.
     *
     * Which starting number, under 1 million, produces the longest chain?
     * NOTE: Once the chain starts, the terms may go above 1 million.
     *
     * CREDIT: Problem 14 of Project Euler
     *
     * @return starting number of the longest Collatz Sequence
     */
    private static int collatzSequence() {
        int n = 1;
        int maxChain = 0;
        int correctNum = 0;

        while (n < 1_000_000) {
            int chainLength = collatz(n, 0);

            if (chainLength > maxChain) {
                maxChain = chainLength;
                correctNum = n;
            }

            n--;
        }

        System.out.println(correctNum);
        return correctNum;
    }

    private static int collatz(int n, int chainLength) {
        if(n == 1) return ++chainLength; // Base case and final link.

        if(n % 2 == 0) n /= 2;
        else n = (3 * n) + 1;

        ++chainLength;

        return collatz(n, chainLength);
    }

    /**
     * Background:
     *      January 1 1900 was a Monday.
     *      April, June, September, and November have 30 days.
     *      January, March, May, July, August, October, and December have 31 days.
     *      February has 28 days and on leap years 29.
     *      A leap year occurs on any year evenly divisible by 4, but not on a century unless 
     *          it is divisble by 400.
     *
     * How many Sundays fell on the first of the month during the 20th century?
     *      (January 1 1901 to December 31 2000).
     *
     * CREDIT: Problem 19 of Project Euler
     *
     * @return the number of Sundays
     */
    private static int countingSundays() {
        return 1; // TODO
    }

    /**
     * Using names.txt, a text file containing more than five-thousand first names,
     * begin by sorting it in alphabetical order. Then work out the alphabetical value
     * of each name, multiply this value by its alphabetical position in the list to obtain
     * a name score.
     *
     * For example, when the list is sorted into alphabetical order, COLIN, which is worth
     *      3 + 15 + 12 + 9 + 14 = 53
     * is the 938th name in the list. So COLIN would have a score of
     *      938 x 53 = 49714
     *
     * What is the total of all the name scores in the file?
     * 
     * CREDIT: Problem 22 of Project Euler
     *
     * @return the total of the name scores.
     */
    private static int nameScores() {
        ArrayList<String> names = readNames();
        int sum = 0;

        Collections.sort(names);

        int counter = 1;
        for (String name : names) {
            int nameScore = 0;
            char[] n = name.toCharArray();
            for (char c : n) {
                nameScore += (c - 64);
            }

            nameScore *= counter;
            sum+= nameScore;

            counter++;
        }

        System.out.println("sum of name scores: " + sum);
        return sum;
    }

    private static ArrayList<String> readNames() {
        ArrayList<String> list = new ArrayList<>();

        File f = new File("names.txt");

        try (Scanner scan = new Scanner(f)) {
            String str = scan.nextLine();

            String[] strings = str.split(",");

            for (String s : strings)
                list.add(s.substring(1, s.length() - 1));

        } catch (Exception e) {
            System.out.println("Error reading file.");
        }

        return list;
    }

    private static void printNames(ArrayList<String> names) {
        for (String name : names)
            System.out.println(name);
    }

    public static void main(String[] args) {
        //System.out.println(collatz(13, 0));
        if(collatzSequence() != 837799) System.out.println("Your solution is incorrect...");
        else System.out.println("Your Collatz Sequence solution is correct!");

        /*
        if(countingSundays() != 171) System.out.println("Your solution is incorrect...");
        else System.out.println("Your Counting Sundays solution is correct!");

        if(nameScores() != 871198282) System.out.println("Your solution is incorrect...");
        else System.out.println("Your Name Scores solution is correct!");
        */
    }
}
