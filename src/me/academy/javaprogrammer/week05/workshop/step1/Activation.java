package me.academy.javaprogrammer.week05.workshop.step1;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

/**
 * Build a class Activation corresponding to activation of an exam session.
 * An exam session has a begin and end time and has methods for creating a new object in different ways
 * and to verify any overlapping active sessions.
 *
 * Step 1
 * ------
 *  * fields
 *      - LocalDateTime start
 *      - LocalDateTime end
 *  * methods
 *      - private constructor : new objects are created with static getInstance() methods
 *      - getters
 *      - overloaded static method getInstance() which validates the input and returns a new object or null if invalid
 *          > validation rules
 *              + start and end must not be null
 *              + start must be before end
 *              + activations can't start or end on Sundays
 *          > overloaded methods
 *              + Activation getInstance(LocalDateTime start, LocalDateTime end)
 *              + Activation getInstance(LocalDateTime start, Duration d)
 *              + Activation getInstance(LocalDateTime start, int days, int hours, int mins, int secs)
 *              + Activation getInstance(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime)
 *      - boolean overlaps(Activation s) : verify if this activation session will overlap with the one as argument
 *          Note: simplest way to check is (start1 <= end2) && (end1 >= start2)
 *      - String toString() : returns the start and end moments of an activation session formatted as in below example
 *          "Session will be activated starting with Wednesday 06.01.2021 at 11:00:00 and closes on Thursday 07.01.2021 at 13:00:00."
 *          Note:
 *              + use format() method of LocalDateTime class
 *              + use Locale for Romania
 *              + use ofPattern() method of DateTimeFormatter class
 */
public class Activation {
    private final LocalDateTime start;
    private final LocalDateTime end;

    private Activation(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String toString() {
        Locale ro = new Locale("ro", "RO");
        String roPattern = "EEEE dd MMMM yyyy 'ora' HH:mm:ss";
        DateTimeFormatter roTimeFormatter = DateTimeFormatter.ofPattern(roPattern, ro);

        String startText = start.format(roTimeFormatter);
        String endText = end.format(roTimeFormatter);

        return "Sesiunea se activeaza incepand cu " +
                startText +
                " si se termina " +
                endText + ".";
    }

    public boolean overlaps(Activation s) {
        return this.start.isBefore(s.getEnd()) && this.end.isAfter(s.getStart());
    }

    public static Activation getInstance(LocalDateTime start, LocalDateTime end) {
        // validate input
        if (start == null) return null;
        if (end == null) return null;
        if (!start.isBefore(end)) return null;
        if (start.getDayOfWeek() == DayOfWeek.SUNDAY) return null;
        if (end.getDayOfWeek() == DayOfWeek.SUNDAY) return null;

        return new Activation(start, end);
    }

    public static Activation getInstance(LocalDateTime start, Duration d) {
        // validate input
        if (start == null) return null;
        if (start.getDayOfWeek() == DayOfWeek.SUNDAY) return null;
        if (d == null) return null;
        // determine the end time
        LocalDateTime end = start.plus(d);
        // validate end
        if (!start.isBefore(end)) return null;
        if (end.getDayOfWeek() == DayOfWeek.SUNDAY) return null;

        return new Activation(start, end);
    }

    public static Activation  getInstance(LocalDateTime start, int days, int hours, int mins, int secs) {
        Duration d = Duration.ofDays(days).plusHours(hours).plusMinutes(mins).plusSeconds(secs);

        return getInstance(start, d);
    }

    public static Activation getInstance(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        if (startDate == null) return null;
        if (startTime == null) return null;
        if (endDate == null) return null;
        if (endTime == null) return null;

        LocalDateTime start = LocalDateTime.of(startDate, startTime);
        LocalDateTime end = LocalDateTime.of(endDate, endTime);

        return getInstance(start, end);
    }

    public static void main(String[] args) {
        // testing the getInstance() methods
        LocalDateTime testStartDateTime = LocalDateTime.of(2001, 12,31, 23, 59);
        LocalDateTime testEndDateTime = LocalDateTime.of(2002, 1, 1, 1, 0);
        Duration testDuration = Duration.ofHours(3);
        int days = 1;
        int hours = 2;
        int mins = 30;
        int secs = 300;
        LocalDate startDate = LocalDate.now();
        LocalTime startTime = LocalTime.now().minusHours(1);
        LocalDate endDate = LocalDate.now();
        LocalTime endTime = LocalTime.now().plusHours(2);

        Activation s1 = getInstance(testStartDateTime, testEndDateTime);
        System.out.println(s1.toString());

        Activation s2 = getInstance(testStartDateTime, testDuration);
        System.out.println(s2.toString());

        Activation s3 = getInstance(testStartDateTime, days, hours, mins, secs);
        System.out.println(s3.toString());

        Activation s4 = getInstance(startDate, startTime, endDate, endTime);
        System.out.println(s4.toString());

        // test overlaps
        System.out.println(s1.overlaps(s2));
        System.out.println(s1.overlaps(s4));
    }
}
