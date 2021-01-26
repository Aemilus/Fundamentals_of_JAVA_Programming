package me.academy.javaprogrammer.week04.workshop.step2;
/**
 * Build a Person class that contains the name and birthdate of a peron.
 * The birthdate is stored in 3 separate fields: year, month, day.
 * Invalid objects should not be created. Thus the constructor will be private and a public static method getInstance() will be created.
 * This method will validate input arguments and returns null if these are invalid or a new Person object.
 * For validation helper methods will be used.
 * The class will have a toString() which will pretty format the fields storing person details. We will use helper methods here also.
 * Program will read from keyboard a name and birthdate and if valid it will create a new Person object and display it's details using toString() method.
 *
 * Step 1 - basic functionality
 * ----------------------------
 * Class structure:
 *  * fields:
 *      - name
 *      - year
 *      - month
 *      - day
 *  * methods:
 *      - private constructor Person(String n, int y, int m, int d)
 *      - public static Person getInstance() : return a new Person object or null if input is invalid.
 *      - to validate input use two methods
 *          > private static boolean isValidName(String n) : return true if name is not null, length is greater than 3 and contains a white space
 *          > private static boolean isValidDate(String d) : return true if date is length 10, month is between 1 and 12, day is between 1 and 31
 *              Note: we assume date is in format dd-mm-yyyy
 *      - public String toString() : return a String as in below example
 *          "Mr. Doe John was born on 23.07.1990."
 *              Note: first name and last name are reversed as opposed to what is stored in name field.
 *      - to determine the person title "Mr." or "Ms." build below helper method
 *          > private static String getTitle(String n) :
 *              return "Mr." if last character in first name is a consonant
 *              return "Ms." if last character in first name is a vowel
 *              Note: we assume that name field stores a person's full name as "FirstName LastName".
 *      - main() :
 *          read from keyboard input the name and birthdate of a person and build a new Person object;
 *          print person details using toString() method or print an error message if the input was invalid.
 *
 * Step 2 - polishing
 * ------------------
 * Improve isValidName() method:
 *  - name length is at least 3
 *  - name must contain a single space and can't be on first or last index
 *  - the character before white space must be lowercase
 *  - the character after white space must be uppercase
 *
 * Improve isValidDate() method:
 *  - character "-" must exist on index 2 and 5
 *  - other characters must be digits
 *  - year must be in past
 *  - month to be between 1 and 12
 *  - day to be in valid days range of respective month
 *
 * Improve toString() method:
 *  - first and last name will be formatted so that they start with uppercase and followed by lowercase
 *  - month will be represented by its name and not by its number
 *  Example: "Mr. Doe John was born on 23 July 1991."
 *
 * To achieve the above build following helper classes.
 *  * StringUtils : will contain only static methods for parsing Strings
 *      - public static boolean hasOnlyLetters(String s)
 *          verify if the String is made only of letters (case insensitive);
 *          test Character.isLetter(char c) on each char in the string
 *      - public static boolean hasOnlyDigits(String s)
 *          test Character.isDigit(char c) on each char in the string
 *      - public static String toUpperFirst(String s)
 *          set to uppercase first letter in the string and to lowercase the rest
 *  * DateUtils : will contain only static methods
 *      - public static int getDaysPerMonth(int month, int year)
 *          returns the length of month in that year
 *      - public static String getNameOfMonth(int month)
 *          returns name of the month
 */

import java.time.LocalDate;
import java.util.Scanner;

public class Person {
    private final String name;
    private final int year;
    private final int month;
    private final int day;

    private Person(String name, int year, int month, int day) {
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public static Person getInstance(String inputName, String birthdate) {
        // return null if invalid input
        if (!isValidName(inputName)) return null;
        if (!isValidDate(birthdate)) return null;

        // input has been validated
        // create new object
        Scanner scDate = new Scanner(birthdate);
        scDate.useDelimiter("-");
        int day = Integer.parseInt(scDate.next());
        int month = Integer.parseInt(scDate.next());
        int year = Integer.parseInt(scDate.next());
        scDate.close();

        Scanner scName = new Scanner(inputName);
        String firstName = scName.next();
        String lastName = scName.next();
        scName.close();

        // format name
        String fullName = StringUtils.toUpperFirst(firstName)
                .concat(" ")
                .concat(StringUtils.toUpperFirst(lastName));

        return new Person(fullName, year, month, day);
    }

    private static boolean isValidName(String name) {
        // name must not be null
        if (name == null) return false;
        // length must be 3 at least
        if (!(name.length() > 2)) return false;
        // name must contain a single white space between first and last name
        // if index of first white space is equal with index of last white space then there is a single whitespace
        // whitespace can't be on first or last positions in name
        int indexFirstWhiteSpace = name.indexOf('\u0020');
        int indexLastWhiteSpace = name.lastIndexOf('\u0020');
        if (indexFirstWhiteSpace == 0) return false;
        if (indexLastWhiteSpace == name.length() - 1) return false;
        if (indexFirstWhiteSpace != indexLastWhiteSpace) return false;

        // the character before whitespace must be lowercase
        char charBeforeWhiteSpace = name.charAt(indexFirstWhiteSpace - 1);
        if (!Character.isLowerCase(charBeforeWhiteSpace)) return false;

        // the character after whitespace must be uppercase
        char charAfterWhiteSpace = name.charAt(indexFirstWhiteSpace + 1);
        if (!Character.isUpperCase(charAfterWhiteSpace)) return false;

        // first and last name must be made of only letters
        Scanner sc = new Scanner(name);
        String firstName = sc.next();
        String lastName = sc.next();
        sc.close();
        if (!StringUtils.hasOnlyLetters(firstName)) return false;
        if (!StringUtils.hasOnlyLetters((lastName))) return false;

        return true;
    }

    private static boolean isValidDate(String date) {
        if (date.length() != 10) return false;

        // check if on indexes 2 and 5 there is hyphen
        if (date.charAt(2) != '-') return false;
        if (date.charAt(5) != '-') return false;

        // parse date
        Scanner sc = new Scanner(date);
        sc.useDelimiter("-");
        String d = sc.next();
        String m = sc.next();
        String y = sc.next();
        sc.close();
        // verify date is made of digits
        if (!StringUtils.hasOnlyDigits(d)) return false;
        if (!StringUtils.hasOnlyDigits(m)) return false;
        if (!StringUtils.hasOnlyDigits(y)) return false;
        // convert to integers
        int day = Integer.parseInt(d);
        int month = Integer.parseInt(m);
        int year = Integer.parseInt(y);

        // year must be in past
        if (!(year < LocalDate.now().getYear())) return false;

        // validate day and month
        if (month < 1 || month > 12) return false;
        if (day < 1 || day > DateUtils.getDaysPerMonth(month, year)) return false;

        return true;
    }

    private static String getTitle(String name) {
        Scanner sc = new Scanner(name);
        String firstName = sc.next();
        sc.close();

        boolean isVowel = firstName.endsWith("a") || firstName.endsWith("e") || firstName.endsWith("i") || firstName.endsWith("o") || firstName.endsWith("u");

        return isVowel ? "Ms." : "Mr.";
    }

    public String toString() {
        String title = getTitle(name);
        Scanner sc = new Scanner(name);
        String firstName = sc.next();
        String lastName = sc.next();
        sc.close();

        return title.concat(" ")
                .concat(lastName).concat(" ")
                .concat(firstName).concat(" was born on ")
                .concat(String.valueOf(100 + day).substring(1,3)).concat(" ")
                .concat(DateUtils.getNameOfMonth(month)).concat(" ")
                .concat(String.valueOf(year)).concat(".");
    }

    public static void main(String[] args) {
        // input from keyboard
        Scanner sc = new Scanner(System.in);
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Date: ");
        String date = sc.nextLine();
        sc.close();

        // build new person from input
        Person p = Person.getInstance(name, date);

        // if input was invalid then Person object is null
        if (p == null) {
            System.out.println("Invalid input!");
            System.exit(1);
        }

        // if input was valid then print person details.
        System.out.println(p.toString());
    }
}
