package me.academy.javaprogrammer.week02.exercise10;

import java.util.Arrays;
import java.util.Random;

/**
 * Create a bi-dimensional array.
 * The length of each line will be randomly generated.
 */

public class Exercise10 {
    public static int ROWS = 3;
    public static int MAX_COLUMN_LENGTH = 10;
    public static int MAX_VALUE = 10;

    public static void main(String[] args) {
        // generate array
        int[][] array2D = generateArray2D();
        System.out.println("Random 2D array:");
        System.out.println(Arrays.deepToString(array2D));

        System.out.println("Iterate over rows:");
        printByRows(array2D);
    }

    public static int[][] generateArray2D() {
        Random rand = new Random();
        int[][] array2D = new int[ROWS][];

        for(int i = 0; i < ROWS; i++) {
            int[] column = new int[rand.nextInt(MAX_COLUMN_LENGTH)];
            array2D[i] = column;
            for (int j = 0; j < column.length; j++) {
                array2D[i][j] = rand.nextInt(MAX_VALUE);
            }
        }

        return array2D;
    }

    public static void printByRows(int[][] array2D) {
        for (int[] row : array2D) {
            for (int element : row) {
                System.out.printf("%d ", element);
            }
            System.out.println();
        }
    }
}
