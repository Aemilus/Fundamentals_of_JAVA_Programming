package me.academy.javaprogrammer.week05.workshop.step3;

import java.time.*;
import java.time.format.DateTimeFormatter;
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
 *
 * Step 2
 * ------
 * Improve toString() method and return also the duration of the activated session in days, hours, minutes and seconds.
 * For achieving this then build a helper class ActivationUtils having static method:
 *      String durationToDHMS(Duration d)
 *  Note:
 *  + Duration class has methods for returning the entire duration amount into days, hours, minutes and seconds;
 *  + Because of above we will need to first get the amount of days then subtract it and then determine the rest of hours and so on;
 *
 *  Example of string returned:
 *      "2 days, 15 hours, 6 minutes and 35 seconds"
 *
 * Step 3
 * ------
 * Improve Activation class by:
 *  * modify getInstance() methods so that activations are not allowed to overlap Sundays (that is - the start, end and any of the days between can't be a Sunday)
 *  * add boolean coversCalendarDay() method to check if the activation will cover at least one calendar day (that is - from 12 AM to next day 12 AM)
 *      Example:
 *      + if activation starts Wednesday at 09:00 AM and ends on Friday at 2:00 PM then it will cover one calendar day and that is the Thursday
 *      + if activation starts Wednesday at 09:00 AM and ends on Thursday at 10:00 AM then method returns false; although there are 25 hours between start and end it did not cover a calendar day
 *
 * Add to ActivationUtils class:
 *  * Activation[] generateActivationSeries(LocalDateTime start, LocalDateTime end, Duration d)
 *      + it returns an array of consecutive Activation objects which cover the entire period between start and end;
 *      + every Activation will have duration equal to d minus one second - the next activation starts one second after the previous activation ended;
 *      + the last activation in the series will be less or equal the duration d
 *  * Activation[] generateRandomActivations(int count, Duration minDuration, Duration maxDuration, LocalDate minDate, LocalDate maxDate)
 *      - returns an array of count activations generated randomly as follows
 *          + each activation will have a duration randomly generated between minDuration and maxDuration
 *          + each activation must span between minDate and maxDate; that is between minDate 00:00:00 and maxDate 23:59:59
 *          + activations must not overlap
 *
 * In main method:
 *  * generate an random array of activations using generateRandomActivations
 *  * array will hold 6 activations with duration between 1 hour to 3 days, all in current month period (00:00:00 first day of month to 23:59:59 last day of month)
 *  * sort chronologically this activations
 *  * print details of each activation in the array using toString() method
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
        // prepare Locale, DateTimeFormatter with pattern
        Locale ro = new Locale("ro", "RO");
        String roPattern = "EEEE dd MMMM yyyy 'ora' HH:mm:ss";
        DateTimeFormatter roTimeFormatter = DateTimeFormatter.ofPattern(roPattern, ro);

        // prepare String representation of the session activation start and end times
        String startText = start.format(roTimeFormatter);
        String endText = end.format(roTimeFormatter);
        String sessionText = "Sesiunea se activeaza incepand cu " + startText + " si se termina " + endText + ".";

        // prepare String representation of duration between start and end time
        Duration sessionDuration = Duration.between(start, end);
        String durationText = "\nDurata: " + ActivationUtils.durationToDHMS(sessionDuration) + ".";

        return sessionText + durationText;
    }

    public boolean overlaps(Activation s) {
        return this.start.isBefore(s.getEnd()) && this.end.isAfter(s.getStart());
    }

    public boolean coversCalendarDay() {
        // a calendar day starts at 00:00:00 and ends at 23:59:59 same day

        // Note to self: I am using the logical not "!" as isAfter() and isBefore() methods return false if the two temporal units are equal
        // so to check if (day1 >= day2) then do !(day1 < day2)

        // is start the beginning of a calendar day?
        LocalDateTime firstSecondOfStartDate = start.withHour(0).withMinute(0).withSecond(0);
        if (!start.isAfter(firstSecondOfStartDate)) {
            // a) if yes - then if end is at or past 23:59:59 then return true else return false;
            LocalDateTime lastSecondOfStartDate = start.withHour(23).withMinute(59).withSecond(59);
            return !end.isBefore(lastSecondOfStartDate);
        } else {
            // b) if no - then if end is at or past 23:59:59 the next day then return true else return false;
            LocalDateTime endOfNextDay = start.plusDays(1).withHour(23).withMinute(59).withSecond(59);
            return !end.isBefore(endOfNextDay);
        }
    }

    private static boolean isSundayBetween(LocalDateTime start, LocalDateTime end) {
        int counter = 1;
        LocalDate day;
        while (true) {
            // looping through each next day after start date
            day = start.plusDays(counter++).toLocalDate();
            // loop terminates when we have got past the end date
            if (!day.isBefore(end.toLocalDate())) break;
            // exit from loop and return true as soon as we hit a Sunday
            if (day.getDayOfWeek() == DayOfWeek.SUNDAY) return true;
        }

        // we checked all the days between start and end date and didn't hit any Sunday
        return false;
    }

    public static Activation getInstance(LocalDateTime start, LocalDateTime end) {
        // validate input
        if (start == null) return null;
        if (end == null) return null;
        if (!start.isBefore(end)) return null;
        if (start.getDayOfWeek() == DayOfWeek.SUNDAY) return null;
        if (end.getDayOfWeek() == DayOfWeek.SUNDAY) return null;
        if (isSundayBetween(start, end)) return null;

        return new Activation(start, end);
    }

    public static Activation getInstance(LocalDateTime start, Duration d) {
        // validate input
        if (start == null) return null;
        if (d == null) return null;

        // determine the end time
        LocalDateTime end = start.plus(d);

        return getInstance(start, end);
    }

    public static Activation getInstance(LocalDateTime start, int days, int hours, int mins, int secs) {
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
        System.out.println("coversCalendarDay: " + s1.coversCalendarDay());

        Activation s2 = getInstance(testStartDateTime, testDuration);
        System.out.println(s2.toString());
        System.out.println("coversCalendarDay: " + s2.coversCalendarDay());

        Activation s3 = getInstance(testStartDateTime, days, hours, mins, secs);
        System.out.println(s3.toString());
        System.out.println("coversCalendarDay: " + s3.coversCalendarDay());

        Activation s4 = getInstance(startDate, startTime, endDate, endTime);
        System.out.println(s4.toString());
        System.out.println("coversCalendarDay: " + s4.coversCalendarDay());

        // test overlaps
        System.out.println(s1.overlaps(s2));
        System.out.println(s1.overlaps(s4));
    }
}
