package me.academy.javaprogrammer.week02.workshop;

public class DiceRollVersion1 {
    /**
     * Simulate the result of a dice roll.
     * Try to "paint" the dice roll result using '*' and white spaces.
     */
    public static void main(String[] args) {
        // roll the dice
        int diceRoll = (byte)(Math.random() * 6) + 1;
        System.out.println("Dice roll result: " + diceRoll);

        // will use a 3x3 2D array to "paint" the face of dice
        char[][] diceFace = new char[3][3];
        // make the dice face empty
        for(char[] row : diceFace) {
            for(byte i = 0; i < row.length; i++) {
                row[i] = '\u0020';   // unicode for space
            }
        }

        // paint the dice face with '*' depending on the dice roll result
        // unicode for '*' is '\u002A'
        char asterisk = '\u002A';
        switch (diceRoll) {
            case 6:
                diceFace[0][1] = asterisk;
                diceFace[2][1] = asterisk;
            case 5:
            case 4:
                diceFace[0][0] = asterisk;
                diceFace[0][2] = asterisk;
                diceFace[2][0] = asterisk;
                diceFace[2][2] = asterisk;
                if (diceRoll != 5) break;
            case 3:
            case 1:
                diceFace[1][1] = asterisk;
                if (diceRoll == 1 || diceRoll == 5) break;
            case 2:
                diceFace[0][0] = asterisk;
                diceFace[2][2] = asterisk;
                break;
            default:
                // in case you generate something outside normal values; you can modify line 8 to achieve this
                System.out.println("This dice is broken!");
                System.exit(1); // exit now so you don't attempt to draw a broken dice
        }

        // draw the dice face on console
        for(char[] row : diceFace) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
