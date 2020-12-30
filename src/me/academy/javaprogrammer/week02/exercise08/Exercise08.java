package me.academy.javaprogrammer.week02.exercise08;

import java.util.Arrays;
import java.util.Random;

/**
 * Generate an array with 100 000 numbers between 0 and 30.
 * Print for each element the frequency of apparition (histogram).
 */

public class Exercise08 {
    public static int MAX_ARRAY_LENGTH = 100_000;
    public static int RANDOM_VALUE_BOUND = 31;

    public static void main(String[] args) {
        // generate array
        int[] array = generateArray();
        System.out.println("Random array:");
        System.out.println(Arrays.toString(array));

        // histogram
        int[][] histogram = getHistogramOfArray(array);
        System.out.println("Histogram:");
        System.out.println(Arrays.deepToString(histogram));
    }

    public static int[] generateArray() {
        int[] array = new int[MAX_ARRAY_LENGTH];
        Random rand = new Random();
        for (int i = 0; i < MAX_ARRAY_LENGTH; i++) {
            array[i] = rand.nextInt(RANDOM_VALUE_BOUND);
        }
        return array;
    }

    /**
     * returns a bi-dimensional array representing:
     * index 0: a value from input array
     * index 1: number of appearances of value at index 0
     */
    public static int[][] getHistogramOfArray(int[] array) {
        int[][] temp = new int[array.length][2];
        // clone input array and sort it
        int[] sorted = Arrays.copyOf(array, array.length);
        Arrays.sort(sorted);

        int j = 0; // cursor for moving from one distinct value to the next one in sorted array
        int k;
        // iterate over sorted array and at each step start from index k
        // at k position there is a different value than the current one on index i
        for(int i = 0; i < sorted.length; i=k) {
            // put in a temporary bi-dimensional array the current value and set it's appearance counter to 1
            temp[j][0] = sorted[i];
            temp[j][1] = 1;
            // increment the counter as long as the next value in sorted array is equal to current one
            for (k = i + 1; k < sorted.length; k++) {
                if (sorted[k] != sorted[i]) break;
                temp[j][1] ++;
            }
            j++;
        }

        // copy from temporary bi-dimensional array only the first j elements
        // there are j distinct elements in input array
        int[][] histogram = new int[j][2];
        for(int x = 0; x < histogram.length; x++) {
            for(int y = 0; y < histogram[x].length; y++) {
                histogram[x][y] = temp[x][y];
            }
        }

        return histogram;
    }
}
