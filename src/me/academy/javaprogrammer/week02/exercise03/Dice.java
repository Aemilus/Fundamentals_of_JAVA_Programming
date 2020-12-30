package me.academy.javaprogrammer.week02.exercise03;

/**
 * Given the code below, use the call 'r.nextInt(6)' which returns an integer value between 0 and 5 inclusive
 * and add the required code to roll a dice and print the result (either a string or ASCII art).
 * Extend the program for two roll dices and print a message for "doubles" or "house anchor".
 *
 * import java.util.Random;
 * public class Dice {
 *      public static void main(String[] args) {
 * 		Random r=new Random();
 *      // insert your code here
 *      }
 * }
 */

import java.util.Random;

public class Dice {

    public static void main(String[] args) {
        Random r = new Random();
        // roll a dice and print result
        int dice = r.nextInt(6) + 1;
        System.out.println("Rolled: " + dice);

        // roll two dices and print result
        int dice1 = r.nextInt(6) + 1;
        int dice2 = r.nextInt(6) + 1;
        System.out.printf("Rolled: %d-%d\n", dice1, dice2);
        // display a message for special cases
        switch (dice1 - dice2) {
            case -2:
            case 2:
                System.out.println("Home Anchor!");
                break;
            case 0:
                System.out.println("Doubles!");
        }
    }
}
