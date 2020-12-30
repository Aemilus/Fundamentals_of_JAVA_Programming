package me.academy.javaprogrammer.week03.exercise02;

public class Magazine {
    private int bullets;
    private boolean blocked;

    Magazine(int bullets) {
        this.bullets = bullets;
        this.blocked = false;
    }

    public int getBullets() {
        return bullets;
    }

    public boolean isEmpty() {
        return bullets == 0;
    }

    public boolean sendBullet() {
        // check if is empty
        if (isEmpty()) {
            System.out.println("Magazine - Error: The magazine is empty.");
            return false;
        }
        // if it ain't empty then whenever a new bullet is to be sent check if the magazine has blocked previously
        if (!blocked) {
            // if it hadn't blocked previously then it might get blocked now
            blocked = (Math.random() > 0.8); // 20% chance for the magazine to block
        }
        // if it blocked now
        if (blocked) {
            System.out.println("Magazine - Error: The magazine has blocked. Can't send the bullet to pistol.");
            return false;
        }
        // if it didn't blocked now
        bullets--;
        System.out.printf("Magazine - Info: Bullet sent to pistol. %d bullets remaining.\n", bullets);
        return true;
    }
}
