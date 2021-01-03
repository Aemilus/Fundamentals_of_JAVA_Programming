package me.academy.javaprogrammer.week05.workshop.step2;

import java.time.Duration;

public class ActivationUtils {

    public static String durationToDHMS(Duration d) {
        long days = d.toDays();
        Duration s = d.minus(Duration.ofDays(days));
        long hours = s.toHours();
        s = s.minus(Duration.ofHours(hours));
        long mins = s.toMinutes();
        long secs = s.minus(Duration.ofMinutes(mins)).getSeconds();

        return days + " zile, " +
                hours + " ore, " +
                mins + " minute si " +
                secs + " secunde";

    }

    public static void main(String[] args) {
        // test
        Duration d = Duration.ofDays(2).plusHours(3).plusMinutes(15).plusSeconds(35);
        System.out.println(durationToDHMS(d));
    }
}
