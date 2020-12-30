package me.academy.javaprogrammer.week02.exercise02;

public class Exercise02 {
    /**
     * Write a program that generates a natural number and checks if it's divided by 2, 3 or 5.
     * Use modulo.
     */

    public static int MAX_VALUE = 100;

    public static void main(String[] args) {
        int rand = randomNumber();
        System.out.println("Random number: " + rand);

        for (int div : new int[]{2, 3, 5}) {
            System.out.printf("Divisible by %d: %s\n", div, isDivisible(rand, div));
        }
    }

    public static int randomNumber() {
        return (int)(Math.random() * MAX_VALUE) + 1; // random natural number > 0
    }

    public static boolean isDivisible(int number, int div) {
        return number % div == 0;
    }
}
