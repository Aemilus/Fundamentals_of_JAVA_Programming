package me.academy.javaprogrammer.week02.exercise06;

import java.util.Arrays;
import java.util.Random;

/**
 * Generate an array with random integers and find the first occurrence of a value in this array.
 * Extend the program that counts and prints all index occurrences in the array.
 */

public class Exercise06 {
    public static int MAX_SIZE = 20;
    public static int MAX_VALUE = 10; // helpful to set the bound for random numbers generated
    public static int SEARCHED_VALUE = 6;

    public static void main(String[] args) {
        // generate array and print
        int[]  array = generateArray(MAX_SIZE);
        System.out.println(Arrays.toString(array));

        // find an value in array and print the index where placed
        // indexes start from 0
        int index = findFirstOccurrence(array, SEARCHED_VALUE);
        System.out.printf("Value %d was first seen at index %d.\n", SEARCHED_VALUE, index);

        // find and count all occurrences of element in array
        int[] indexes = findAllOccurrences(array, SEARCHED_VALUE);
        System.out.printf("%d occurrences of value %d found in array:\n", indexes.length, SEARCHED_VALUE);
        System.out.println(Arrays.toString(indexes));

    }

    public static int[] generateArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for(int i = 0; i < size; i++) {
            array[i] = rand.nextInt(MAX_VALUE);
        }
        return array;
    }

    public static int findFirstOccurrence(int[] array, int value) {
        for(int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        // return -1 if searched value was not found in array
        return -1;
    }

    // return an array of length equal to the total occurrences of a value in the searched array
    // the elements in this new array represent the index where searched value was found
    public static int[] findAllOccurrences(int[] array, int value) {
        int[] indexes = new int[array.length];
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                indexes[j++] = i;
            }
        }
        return Arrays.copyOfRange(indexes,0, j);
    }
}
