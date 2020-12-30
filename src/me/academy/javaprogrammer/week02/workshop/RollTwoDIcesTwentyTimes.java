package me.academy.javaprogrammer.week02.workshop;

import java.util.Arrays;

public class RollTwoDIcesTwentyTimes {
    /**
     * Roll two dices for twenty times and save the results in a 2D array (20x2).
     * Determine and print:
     * - count and percentage of "doubles"
     * - count and percentage of "home anchors"
     */
    public static int MAX_SIZE = 20;
    public static void main(String[] args) {
        // roll 2 dices 20 times; print the 2d array;
        int[][] dicesSeries = new int[MAX_SIZE][2];
        for (int i=0; i<dicesSeries.length; i++) {
            dicesSeries[i] = rollTwoDices();
        }
        System.out.println(Arrays.deepToString(dicesSeries));

        // count "doubles" and "home anchors"
        int countDoubles = 0;
        int countHomeAnchors = 0;
        for (int[] dices : dicesSeries) {
            if (isDoubles(dices))
                countDoubles++;
            else if (isHomeAnchor(dices))
                countHomeAnchors++;
        }
        // print "doubles" and "home anchors"
        System.out.println("Total doubles: " + countDoubles);
        System.out.println("Total home anchors: " + countHomeAnchors);

        // print percentage of "doubles" and "home anchors"
        System.out.printf("Percentage doubles: %.2f %s\n", countDoubles*100.0/dicesSeries.length, "%");
        System.out.printf("Percentage home anchors: %.2f %s\n", countHomeAnchors*100.0/dicesSeries.length, "%");
    }

    public static int diceRoll() {
        return (byte)(Math.random() * 6) + 1;
    }

    public static int[] rollTwoDices() {
        return new int[]{diceRoll(), diceRoll()};
    }

    public static boolean isDoubles(int[] dices) {
        return dices[0] == dices[1];
    }

    public static boolean isHomeAnchor(int[] dices) {
        return Math.abs(dices[0] - dices[1]) == 2;
    }
}
