package me.academy.javaprogrammer.week02.exercise11;

import java.util.Arrays;
import java.util.Random;

/**
 * Write a program that sums two 3x3 matrix.
 */

public class Exercise11 {
    public static int ROWS = 3;
    public static int COLUMNS = 3;
    public static int VALUE_BOUND = 10;

    public static void main(String[] args) {
        // generate two matrix
        int[][] matrix1 = generateMatrix();
        int[][] matrix2 = generateMatrix();

        // sum two matrix
        int[][] sum = sumMatrix(matrix1, matrix2);

        // print result
        System.out.println("Matrix 1:");
        System.out.println(Arrays.deepToString(matrix1));
        System.out.println("Matrix 2:");
        System.out.println(Arrays.deepToString(matrix2));
        System.out.println("Sum:");
        System.out.println(Arrays.deepToString(sum));
    }

    public static int[][] generateMatrix() {
        int[][] matrix = new int[ROWS][COLUMNS];
        Random rand = new Random();

        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = rand.nextInt(VALUE_BOUND);
            }
        }

        return matrix;
    }

    public static int[][] sumMatrix(int[][] matrix1, int[][] matrix2) {
        int[][] sum = new int[ROWS][COLUMNS];

        for(int i = 0; i < sum.length; i++) {
            for(int j = 0; j < sum[i].length; j++) {
                sum[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        return sum;
    }
}
