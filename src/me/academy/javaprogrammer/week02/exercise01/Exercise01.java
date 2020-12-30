package me.academy.javaprogrammer.week02.exercise01;

import java.util.Arrays;

public class Exercise01 {
    /**
     * Fill an array with 100 random natural numbers.
     * - how many unique elements are in array?
     * - create a new array containing only these unique elements (kill duplicates but keep one)
     * - create a 2D array; in 2nd dimension put:
     *      * on index 0 will be each unique element of random array
     *      * at index 1 save the number of occurrences for that element in the random array
     *   => histogram
     */

    public static int MAX_VALUE = 10; // natural numbers will be generated between interval (0:MAX_VALUE]
    public static int MAX_SIZE = 100; // set size of random array

    public static void main(String[] args) {
        // generate and print the random array
        int[] randomArray = generateArray(MAX_SIZE);
        System.out.println("Random array:");
        System.out.println(Arrays.toString(randomArray));

        // count and print unique elements
        int uniques = countUnique(randomArray);
        int[] uniqueArray = new int[uniques];
        fillUniqueArray(uniqueArray, randomArray);
        System.out.println("Array holding only unique elements of random array:");
        System.out.println(Arrays.toString(uniqueArray));

        // generate histogram of the random array (frequency of each element in random array)
        System.out.println("Histogram of random array:");
        int[][] histogramArray = createHistogramOfArray(randomArray, uniqueArray);
        System.out.println(Arrays.deepToString(histogramArray));
    }

    public static int[] generateArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * MAX_VALUE) + 1;
        }
        return array;
    }

    /**
     * count how many times input number appears in input array
     */
    public static int countAppearance(int[] array, int number) {
        int count = 0;
        for (int element : array) {
            if (element == number) {
                count++;
            }
        }
        return count;
    }

    /**
     * Iterate through input array and if the current element appears only once in rest of array
     * starting from current position then increment the uniques counter.
     */
    public static int countUnique(int[] array) {
        int uniques = 0;
        for (int i = 0; i < array.length; i++) {
            if (countAppearance(Arrays.copyOfRange(array, i, array.length), array[i]) == 1) {
                uniques++;
            }
        }
        return uniques;
    }

    /**
     * Iterate through randomArray and if the current element appears only once in rest of array
     * starting from current position then copy element to uniqueArray.
     */
    public static void fillUniqueArray(int[] uniqueArray, int[] randomArray) {
        int u = 0; // counter for uniqueArray
        for (int i = 0; i < randomArray.length; i++) {
            if (countAppearance(Arrays.copyOfRange(randomArray, i, randomArray.length), randomArray[i]) == 1) {
                uniqueArray[u++] = randomArray[i];
            }
        }
    }

    /**
     * create a histogram of input array (each pair is made of an element and it's frequency in input array)
     */
    public static int[][] createHistogramOfArray(int[] array, int[] uniqueArray) {
        int[][] histogramArray = new int[uniqueArray.length][2];
        for (int i = 0; i < histogramArray.length; i++) {
            int[] pair = new int[2];
            pair[0] = uniqueArray[i];
            pair[1] = countAppearance(array, pair[0]);
            histogramArray[i] = pair;
        }
        return histogramArray;
    }
}
