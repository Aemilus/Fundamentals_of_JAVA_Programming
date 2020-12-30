package me.academy.javaprogrammer.week02.exercise13;

import java.util.Arrays;
import java.util.Random;

/**
 * sort an array of chars using bubble sort.
 */
public class Exercise13 {
    public static int MAX_SIZE = 20; // max length array of chars

    public static void main(String[] args) {
        // get a random array of chars
        char[] randomArrayOfChars = getRandomCharArray();
        System.out.println("Random array of chars:");
        System.out.println(Arrays.toString(randomArrayOfChars));

        // bubble sort the array fo chars
        char[] sortedArrayOfChars = bubbleSortArrayOfChars(randomArrayOfChars);
        System.out.println("Sorted:");
        System.out.println(Arrays.toString(sortedArrayOfChars));
    }

    public static char[] getRandomCharArray() {
        char[] arrayOfChars = new char[MAX_SIZE];
        // between U+0021 ('!' char) and U+007E ('~' char) there are most common used chars (alphabetic, numeric, special chars)
        // reference https://www.utf8-chartable.de/unicode-utf8-table.pl
        int charLowLimit = '!';
        int charHighLimit = '~';
        Random rand = new Random();
        char randomChar;

        // fill array with random chars
        for(int i = 0; i < arrayOfChars.length; i++) {
            // generate a char between ! and ~ inclusive
            randomChar = (char) (rand.nextInt( charHighLimit - charLowLimit + 1) + charLowLimit);
            // save generated char into array
            arrayOfChars[i] = randomChar;
        }

        return arrayOfChars;
    }

    public static char[] bubbleSortArrayOfChars(char[] unsortedChars) {
        char[] sortedChars = Arrays.copyOf(unsortedChars, unsortedChars.length);

        // bubble sort
        char tempChar;
        int size = sortedChars.length;
        for(int k = 0; k < size - 1; k++) {
            for(int i = 0; i < size - k - 1; i++)
            // swap chars
            if (sortedChars[i] > sortedChars[i+1]) {
                tempChar = sortedChars[i];
                sortedChars[i] = sortedChars[i+1];
                sortedChars[i+1] = tempChar;
            }
        }

        return sortedChars;
    }
}
