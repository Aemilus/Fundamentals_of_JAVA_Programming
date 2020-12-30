/*
JAVA is a high-level, strongly typed, object-oriented programming language which aims to be platform-independent.

Write once, run anywhere.
Java source code (.java files) --javac compiler-> Bytecode (.class files) --Java Virtual Machine (JVM)-> machine code

JRE - Java Runtime Environment = JVM + core classes
JDK - Java Development Kit = JRE + development tools (compiler, archiver, documentation tools etc.)

IDE - Integrated Development Environment (NetBeans, Eclipse, IntelliJ IDEA)
 */

/*
The source code file will hold at least one class definition.
Usually class name starts with uppercase and filename must match class name (case sensitive).
 */
package me.academy.javaprogrammer.week01;

public class HelloAcademy {

    // in main() method we place the code to be executed at runtime
    // you do well to remember the main() method signature!
    public static void main(String[] args) {

        // print text to console
        System.out.println("Hello Academy!");

        // variable declaration
        int x;      // can't be used until a value it's assigned

        // variable initialization
        int y = 4;

        // Primitive Data Types
        // all numeric primitive types are signed (there is no unsigned like in C)
        // integral types
        byte    a = 127;        //  8 bits  |   -128...+127
        short   b = 30000;      // 16 bits  |   -32768...+32767
        int     c = 1024;       // 32 bits  |   -2147483648...+2147483647
        long    d = 1000000L;   // 64 bits  |   -2^63...+2^63-1             | "l" or "L" can be omitted
        long    dd = 1_000_000L;    // can use underscore delimiters to improve code readability.
        System.out.println(d);
        System.out.println(dd);
        // floating-point types
        float   e = 3.14f;      // 32 bits
        double  f = 41.3d;      // 64 bits  | "d" or "D" can be omitted
        // character type
        char    g = 'A';        // 16 bits  | unicode
        char    h = '\u0041';   // unicode value for capital letter 'A';
        System.out.println(g);
        System.out.println(h);
        // char is also seen as an unsigned integer with values ranging from 0 to 65535
        System.out.println((int)'A');   // prints decimal 65
        System.out.println('A' + 10);   // prints decimal 75
        System.out.println((char)75);   // prints 10th letter after A
        // boolean
        boolean p = true;
        boolean q = false;

        // void - this type is used to indicate that a method doesn't return anything; we can't declare void type variables

    }
}
