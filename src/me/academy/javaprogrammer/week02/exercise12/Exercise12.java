package me.academy.javaprogrammer.week02.exercise12;

import java.util.Arrays;
import java.util.Random;

/**
 * Write a program to sum two matrix.
 * Matrix dimensions and elements are generated randomly
 */
public class Exercise12 {
    public static int MAX_DIMENSION_SIZE = 10;
    public static int MAX_VALUE = 10;

    public static void main(String[] args) {
        // generate two matrix
        int[][] matrix1 = generateMatrix();
        int[][] matrix2 = generateMatrix();
        System.out.println("Matrix 1:");
        System.out.println(Arrays.deepToString(matrix1));
        System.out.println("Matrix 2:");
        System.out.println(Arrays.deepToString(matrix2));

        // verify matrix are compatible
        boolean testMatrixCompatibility = validateMatrix(matrix1, matrix2);
        if (testMatrixCompatibility) {
            // sum two matrix
            int[][] sum = sumMatrix(matrix1, matrix2);
            System.out.println("Sum:");
            System.out.println(Arrays.deepToString(sum));
        }

    }

    public static int[][] generateMatrix() {
        Random rand = new Random();
        int rows = rand.nextInt(MAX_DIMENSION_SIZE);
        int cols = rand.nextInt(MAX_DIMENSION_SIZE);
        int[][] matrix = new int[rows][cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                matrix[i][j] = rand.nextInt(MAX_VALUE);
            }
        }

        return matrix;
    }

    public static boolean validateMatrix(int[][] matrix1, int[][] matrix2) {
        return matrix1.length > 0 &&
                matrix1.length == matrix2.length &&
                matrix1[0].length == matrix2[0].length &&
                matrix1[0].length > 0;
    }

    public static int[][] sumMatrix(int[][] matrix1, int[][] matrix2) {
        int rows = matrix1.length;
        int cols = matrix1[0].length;
        int[][] sum = new int[rows][cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                sum[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        return sum;
    }
}
