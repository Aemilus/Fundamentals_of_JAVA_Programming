package me.academy.javaprogrammer.week02.exercise05;

import java.util.Random;

/**
 * Generate an arithmetic progression. Keep the result in a byte variable.
 * WHat happens if the ratio is too big?
 */

public class Exercise05 {

    public static void main(String[] args) {
        byte sum = (byte) new Random().nextInt(Byte.MAX_VALUE); // first element in the progression will be a random byte
        int ratio = 50; // with a big ratio then in 10 steps we are sure to overflow the byte type several times
        int steps = 10;
        do {
            System.out.printf("%d ", sum);
            sum += ratio;
        } while (--steps > 0);
    }
}
