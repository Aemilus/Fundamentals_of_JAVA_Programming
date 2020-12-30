package me.academy.javaprogrammer.week04.workshop.step2;

/**
 *  * StringUtils : will contain only static methods for parsing Strings
 *      - public static boolean hasOnlyLetters(String s)
 *          verify if the String is made only of letters (case insensitive);
 *          test Character.isLetter(char c) on each char in the string
 *      - public static boolean hasOnlyDigits(String s)
 *          test Character.isDigit(char c) on each char in the string
 *      - public static String toUpperFirst(String s)
 *          set to uppercase first letter in the string and to lowercase the rest
 */
 public class StringUtils {

     public static boolean hasOnlyLetters(String s) {
         char[] chars = s.toCharArray();

         for (char c : chars) {
             if (!Character.isLetter(c)) return false;
         }

         return true;
     }

     public static boolean hasOnlyDigits(String s) {
         char[] chars = s.toCharArray();

         for(char c: chars) {
             if (!Character.isDigit(c)) return false;
         }

         return true;
     }

     public static String toUpperFirst(String s) {
         char[] chars = s.toCharArray();
         char[] out = new char[chars.length];

         out[0] = Character.toUpperCase(chars[0]);
         for (int i = 1; i < chars.length; i++) {
             out[i] = Character.toLowerCase(chars[i]);
         }

         return String.valueOf(out);
     }
}
