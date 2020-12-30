package me.academy.javaprogrammer.week02.exercise07;

import java.util.Arrays;
import java.util.Random;

/**
 * Generate a random array.
 * Insert a random value in this array at an input position.
 * Remove from this array an element at an input position.
 */

public class Exercise07 {
    // input positions controls
    public static int INSERT_AT_INDEX = 1;
    public static int DELETE_AT_INDEX = 2;
    // random array length controls
    public static int MIN_ARRAY_LENGTH = 5;
    public static int ARRAY_LENGTH_BOUND = 10;
    // used for generating natural numbers between 0 - 99
    public static int RANDOM_VALUE_BOUND = 100;

    public static void main(String[] args) {
        // generate and print random array
        int[] array = generateRandomArray();
        System.out.println(Arrays.toString(array));

        // insert a random value on a certain position
        Random rand = new Random();
        int randomValue = rand.nextInt();
        int[] insertArray = insertValueAtIndex(array, randomValue, INSERT_AT_INDEX);
        System.out.println(Arrays.toString(insertArray));

        // delete a value on a certain position
        int[] deleteArray = deleteValueAtIndex(array, DELETE_AT_INDEX);
        System.out.println(Arrays.toString(deleteArray));
    }

    public static int[] generateRandomArray() {
        Random rand = new Random();
        int size = rand.nextInt(ARRAY_LENGTH_BOUND) + MIN_ARRAY_LENGTH; // guarantee minimum array length
        int[] array = new int[size];
        for(int i = 0; i < size; i++) {
            array[i] = rand.nextInt(RANDOM_VALUE_BOUND); // fill the array with natural numbers
        }
        return array;
    }

    public static int[] insertValueAtIndex(int[] array, int value, int index) {
        int[] newArray = new int[array.length + 1];
        int[] leftArray = Arrays.copyOf(array,index);
        int[] rightArray = Arrays.copyOfRange(array, index, array.length);

        int j = 0;
        for(int i = 0; i < leftArray.length; i++) {
            newArray[j++] = leftArray[i];
        }
        newArray[j++] = value;
        for(int i = 0; i < rightArray.length; i++) {
            newArray[j++] = rightArray[i];
        }

        return newArray;
    }

    public static int[] deleteValueAtIndex(int[] array, int index) {
        int[] newArray = new int[array.length - 1];
        int[] leftArray = Arrays.copyOf(array, index);
        int[] rightArray = Arrays.copyOfRange(array, index + 1, array.length);

        int j = 0;
        for(int i = 0; i < leftArray.length; i++) {
            newArray[j++] = leftArray[i];
        }
        for (int i = 0; i < rightArray.length; i++) {
            newArray[j++] = rightArray[i];
        }

        return newArray;
    }
}
