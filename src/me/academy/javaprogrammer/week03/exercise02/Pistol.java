package me.academy.javaprogrammer.week03.exercise02;

/**
 * Extend Pistol class from Exercise 01.
 *
 * Build a separate class Magazine which allows magazines with different capacity.
 * Magazine class methods: isEmpty(), sendBullet(), getBullets().
 * Bullets field will be placed into Magazine class and will decrease with each fire shot.
 * There is a 20% chance the magazine can get blocked with each shot: Math.random() > 0.8
 * Modify replaceMagazine() method in Pistol class with new signature: replaceMagazine(Magazine m)
 */

public class Pistol {
    private final String[] FIRE_MESSAGES = new String[] {"Target Hit!", "Target missed."};

    private Magazine magazine;
    private boolean safety;

    public Pistol(Magazine magazine) {
        this.magazine = magazine;
        this.safety = true;

        System.out.printf("A new pistol with a magazine of %d bullets. Safety is %s.\n", this.magazine.getBullets(), this.safety ? "on" : "off");
    }

    public void safetyOn() {
        System.out.println("Pistol - Info: Safety set to ON.");
        safety = true;
    }

    public void safetyOff() {
        System.out.println("Pistol - Info: Safety set to OFF.");
        safety = false;
    }

    public void fire() {
        System.out.println("Pistol - Info: Pistol trigger pressed.");
        if (safety) {
            System.out.println("Pistol - Error: Can't fire while the safety is on.");
            return;
        }

        if (!magazine.sendBullet()) {
            System.out.println("Pistol - Error: Can't fire. No bullets received from magazine.");
        } else {
            int randomIndex = (int)(Math.random() * 2);
            System.out.printf("Pistol - Info: Bullet fired. %s\n", FIRE_MESSAGES[randomIndex]);
        }
    }

    public void replaceMagazine(Magazine m) {
        if (safety) {
            System.out.println("Pistol - Info: Removing current magazine.");
            magazine = m;
            System.out.printf("Pistol - Info: Inserted new magazine with %d bullets.\n", m.getBullets());
        } else {
            System.out.println("Pistol - Error: Can't remove the magazine while safety off.");
        }
    }

    public static void main(String[] args) {
        Pistol p = new Pistol(new Magazine(10));
        for(int i = 0; i < 20; i++) {
            System.out.println("**************************");
            p.fire();
            if (i % 11 == 0) {
                int randomBullets = (int)(Math.random() * 10 + 1);
                Magazine magazine = new Magazine(randomBullets);
                p.safetyOff();
                p.replaceMagazine(magazine);
                p.safetyOn();
                p.replaceMagazine(magazine);
                p.fire();
                p.safetyOff();
            }
            System.out.println("**************************");
        }
    }
}
