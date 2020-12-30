package me.academy.javaprogrammer.week02.workshop;

public class DiceRollVersion2 {
    public static void main(String[] args) {
        int z = diceRoll();
        printResult(z);
        paintDice(z);
    }

    public static int diceRoll() {
        return (int)(Math.random() * 6) + 1;
    }

    public static void printResult(int z) {
        System.out.println(new String[]{"one", "two", "three", "four", "five", "six"}[z-1]);
    }

    public static void paintDice(int z) {
        switch (z) {
            case 1:
                System.out.println("   ");
                System.out.println(" * ");
                System.out.println("   ");
                break;
            case 2:
                System.out.println("*  ");
                System.out.println("   ");
                System.out.println("  *");
                break;
            case 3:
                System.out.println("*  ");
                System.out.println(" * ");
                System.out.println("  *");
                break;
            case 4:
                System.out.println("* *");
                System.out.println("   ");
                System.out.println("* *");
                break;
            case 5:
                System.out.println("* *");
                System.out.println(" * ");
                System.out.println("* *");
                break;
            case 6:
                System.out.println("* *");
                System.out.println("* *");
                System.out.println("* *");
                break;
        }
    }
}
