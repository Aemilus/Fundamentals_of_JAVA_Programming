package me.academy.javaprogrammer.week02.exercise04;

/**
 * Same problem as Exercise04a but add code to skip number 5.
 * Use a different way to skip number 5 for each loop method.
 */

public class Exercise04b {

    public static void main(String[] args) {
        // for loop
        for(int i = 1; i < 11; i++) {
            if (i == 5) continue;
            System.out.printf("%d ", i);
        }
        System.out.println();

        // while loop
        int i = 0;
        while (++i < 11) {
            switch (i) {
                case 5:
                    break;
                default:
                    System.out.printf("%d ", i);
            }
        }
        System.out.println();

        // do-while loop
        int j = 1;
        do {
            if (j != 5) {
                System.out.printf("%d ", j);
            }
        } while (++j < 11);
        System.out.println();
    }
}

