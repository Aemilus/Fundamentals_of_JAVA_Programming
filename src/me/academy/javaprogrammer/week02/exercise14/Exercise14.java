package me.academy.javaprogrammer.week02.exercise14;

import java.util.Arrays;
import java.util.Random;

/**
 * Generate an array with random natural numbers.
 * Iterate over array and print how many are even and how many are odd.
 * Make two arrays: one containing all odd numbers and the other all even numbers.
 */

public class Exercise14 {
    public static int MAX_SIZE = 30; // max length of array
    public static int MAX_VALUE = 100; // max value in array exclusive

    public static void main(String[] args) {
        // generate array with random natural numbers
        int[] array = generateArrayWithNaturalNumbers();
        System.out.println("Random array:");
        System.out.println(Arrays.toString(array));

        // count how many values are odd and how many are even
        int countOdds = countOddValuesInArray(array);
        System.out.printf("Total odd values:\t%d\n", countOdds);

        int countEvens = countEvenValuesInArray(array);
        System.out.printf("Total even values:\t%d\n", countEvens);

        // print all odd and even values; each saved in different array
        int[] odds = parseOddValuesInArray(array);
        System.out.println("All odd values:");
        System.out.println(Arrays.toString(odds));

        int[] evens = parseEvenValuesInArray(array);
        System.out.println("All even values:");
        System.out.println(Arrays.toString(evens));
    }

    public static int[] generateArrayWithNaturalNumbers() {
        int[] array = new int[MAX_SIZE];
        Random rand = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(MAX_VALUE);
        }

        return array;
    }

    public static boolean isOdd(int value) {
        return value % 2 == 1;
    }

    public static boolean isEven(int value) {
        return value % 2 == 0;
    }

    public static int countOddValuesInArray(int[] array) {
        int count = 0;

        for (int i : array) {
            if (isOdd(i)) count++;
        }

        return count;
    }

    public static int countEvenValuesInArray(int[] array) {
        int count = 0;

        for (int i : array) {
            if (isEven(i)) count++;
        }

        return count;
    }

    public static int[] parseOddValuesInArray(int[] array) {
        int[] odds = new int[countOddValuesInArray(array)];
        int j = 0;
        for (int i : array) {
            if (isOdd(i)) {
                odds[j++] = i;
            }
        }

        return odds;
    }

    public static int[] parseEvenValuesInArray(int[] array) {
        int[] evens = new int[countEvenValuesInArray(array)];
        int j = 0;
        for (int i : array) {
            if (isEven(i)) {
                evens[j++] = i;
            }
        }

        return evens;
    }
}
