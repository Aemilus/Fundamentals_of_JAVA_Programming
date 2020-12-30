package me.academy.javaprogrammer.week03.exercise01;

/**
 * Build a simple class Pistol.
 *
 * Fields:
 * - MAX_AMMUNITION_CAPACITY (final field initialized in constructor)
 * - bullets -> counts the number of bullets and is initialized at replaceMagazine() method call
 *
 * Methods:
 * - safetyOn()
 * - safetyOff()
 * - fire() -> display "target hit" or "missed target"; message is generated randomly
 * - replaceMagazine()
 */

public class Pistol {
    private final int MAX_AMMUNITION_CAPACITY;
    private final String[] FIRE_MESSAGES = new String[]{"Target Hit!", "Missed Target."};

    private int bullets;
    private boolean safety;

    public Pistol(int maxAmmo){
        MAX_AMMUNITION_CAPACITY = maxAmmo;
        bullets = MAX_AMMUNITION_CAPACITY;
        safetyOn();
    }

    public void safetyOn() {
        safety = true;
    }

    public void safetyOff() {
        safety = false;
    }

    public void replaceMagazine() {
        if (!safety) {
            System.out.println("Error: Can't replace the magazine while the safety is off.");
        }
        bullets = MAX_AMMUNITION_CAPACITY;
        System.out.println("Magazine replaced.");
    }

    public void fire() {
        if (safety) {
            System.out.println("Error: Can't fire when safety is on.");
            return;
        }
        if (bullets < 1) {
            System.out.println("Error: No more ammo.");
            return;
        }

        System.out.println("Fire.");
        bullets--;
        int rand = (int) (Math.random() * 2);
        System.out.println(FIRE_MESSAGES[rand]);
        System.out.printf("%d bullets remaining.\n", bullets);
    }

    public static void main(String[] args) {
        Pistol p = new Pistol(10);
        for(int i = 0; i < 20; i++) {
            p.fire();
            // as i is 0 the magazine is replaced at first iteration
            // and then at 11th when the magazine has no more bullets
            if (i % 11 == 0) {p.safetyOn();
                p.replaceMagazine();
                p.safetyOff();
            }
        }
    }

}
