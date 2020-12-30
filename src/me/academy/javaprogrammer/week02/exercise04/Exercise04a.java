package me.academy.javaprogrammer.week02.exercise04;

/**
 * Print numbers from 1 to 10 in three ways: for, while, do-while.
 */

public class Exercise04a {

    public static void main(String[] args) {
        // for loop
        for(int i = 1; i < 11; i++) {
            System.out.printf("%d ", i);
        }
        System.out.println();

        // while loop
        int i = 0;
        while (++i < 11) {
            System.out.printf("%d ", i);;
        }
        System.out.println();

        // do-while loop
        int j = 1;
        do {
            System.out.printf("%d ", j);
        } while (++j < 11);
        System.out.println();
    }
}
