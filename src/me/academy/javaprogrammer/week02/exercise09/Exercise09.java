package me.academy.javaprogrammer.week02.exercise09;

import java.util.Arrays;

/**
 * Generate a matrix where:
 * - the matrix will have 3 lines and 4 column
 * - every element on first line is equal with the column index
 * - every element on second line is equal with 2 * column index
 * ... etc.
 * Print the matrix elements two ways:
 * - iterate over lines
 * - iterate over columns
 */

public class Exercise09 {
    public static int ROWS = 3;
    public static int COLUMNS = 4;

    public static void main(String[] args) {
        // generate matrix and print it
        int[][] matrix = generateMatrix();
        System.out.println(Arrays.deepToString(matrix));

        System.out.println("Print matrix while iterating over rows:");
        printByRow(matrix);

        System.out.println("Print matrix while iterating over columns:");
        printByColumn(matrix);

    }

    public static int[][] generateMatrix() {
        int[][] matrix = new int[ROWS][COLUMNS];

        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLUMNS; j++) {
                matrix[i][j] = (j+1) * (i+1);
            }
        }

        return matrix;
    }

    public static void printByRow(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%d ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void printByColumn(int[][] matrix) {
        for(int j = 0; j < COLUMNS; j++) {
            for(int i = 0; i < ROWS; i++) {
                System.out.printf("%d ", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
