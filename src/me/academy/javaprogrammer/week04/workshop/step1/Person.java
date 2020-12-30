package me.academy.javaprogrammer.week04.workshop.step1;
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
 */

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

    public static Person getInstance(String fullName, String birthdate) {
        // return null if invalid input
        if (!isValidName(fullName)) return null;
        if (!isValidDate(birthdate)) return null;

        // input has been validated
        // create new object
        Scanner sc = new Scanner(birthdate);
        sc.useDelimiter("-");
        String day = sc.next();
        String month = sc.next();
        String year = sc.next();
        return new Person(fullName, Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
    }

    private static boolean isValidName(String name) {
        // name must not be null
        if (name == null) return false;
        // length must be greater than 3
        if (!(name.length() > 3)) return false;
        // name must contain a white space between first and last name
        Scanner sc = new Scanner(name);
        String firstName = sc.next();
        String lastName = sc.next();
        if (firstName.isEmpty()) return false;
        if (lastName.isEmpty()) return false;
        return true;
    }

    private static boolean isValidDate(String date) {
        if (date.length() != 10) return false;

        // parse date
        Scanner sc = new Scanner(date);
        sc.useDelimiter("-");
        int day = Integer.parseInt(sc.next());
        int month = Integer.parseInt(sc.next());
        sc.close();

        // validate day and month
        if (day < 1 || day > 31) return false;
        if (month < 1 || month > 12) return false;
        return true;
    }

    private static String getTitle(String name) {
        Scanner sc = new Scanner(name);
        String firstName = sc.next();

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
                .concat(String.valueOf(100 + day).substring(1,3)).concat(".")
                .concat(String.valueOf(100 + month).substring(1,3)).concat(".")
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

        // if input was invalid then Peron object is null
        if (p == null) {
            System.out.println("Invalid input!");
            System.exit(1);
        }

        // if input was valid then print person details.
        System.out.println(p.toString());
    }
}
