package me.academy.javaprogrammer.week04.workshop.step2;

/**
 *  * DateUtils : will contain only static methods
 *      - public static int getDaysPerMonth(int month, int year)
 *          returns the length of month in that year => here I decided to also build a separate method to test if an year is leap year.
 *      - public static String getNameOfMonth(int month)
 *          returns name of the month
 */
public class DateUtils {

    public static int getDaysPerMonth(int month, int year) {
        switch (month) {
            case 2:
                return isLeapYear(year) ? 29 : 28;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return -1;
        }
    }

    public static boolean isLeapYear(int year) {
        // Every year that is exactly divisible by four is a leap year,
        // except for years that are exactly divisible by 100,
        // but these centurial years are leap years if they are exactly divisible by 400.
        // For example, the years 1700, 1800, and 1900 are not leap years, but the years 1600 and 2000 are.
        // Source: https://en.wikipedia.org/wiki/Leap_year
        boolean leapYear = false;
        if (year % 4 == 0) leapYear = true;
        if (year % 100 == 0) leapYear = false;
        if (year % 400 == 0) leapYear = true;

        return leapYear;
    }

    public static String getNameOfMonth(int month) {
        switch (month) {
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return "Invalid month!";
        }
    }
}
