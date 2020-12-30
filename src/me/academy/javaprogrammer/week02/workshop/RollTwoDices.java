package me.academy.javaprogrammer.week02.workshop;

import java.util.Arrays;

public class RollTwoDices {
    /**
     * Roll two dices and print the result.
     * Print a message if you roll:
     * - a double (ex: 6 - 6; 3 - 3 ...)
     *   => print "Doubles!" or "Dubla!"
     * - or if the absolute difference between the two dices is 2 (ex: 1 - 3; 4 - 2 ...)
     *   => print "Home Anchor!" or "Poarta in casa!"
     */
    public static void main(String[] args) {
        int[] dices = rollTwoDices();
        System.out.println(Arrays.toString(dices));

        if (isDoubles(dices)) {
            System.out.println("Doubles!");
        } else if (isHomeAnchor(dices)) {
            System.out.println("Home Anchor!");
        }
    }

    private static int diceRoll() {
        return (byte)(Math.random() * 6) + 1;
    }

    private static int[] rollTwoDices() {
        return new int[]{diceRoll(), diceRoll()};
    }

    private static boolean isDoubles(int[] dices) {
        return dices[0] == dices[1];
    }

    private static boolean isHomeAnchor(int[] dices) {
        return Math.abs(dices[0] - dices[1]) == 2;
    }
}
