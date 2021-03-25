package me.academy.javaprogrammer.week12.exercise02;

class Person {
    private final String name;
    private final int age;
    private final PersonGender gender;
    private final boolean married;

    Person(String name, int age, PersonGender gender, boolean married) {
        if (name == null) throw new NullPointerException("Name is null.");
        if (name.trim().isEmpty()) throw new IllegalArgumentException("Name is empty.");
        if (age < 1 || age > 125) throw new IllegalArgumentException("Invalid age.");
        if (gender == null) throw new NullPointerException("Gender is null.");

        this.name = name;
        this.age = age;
        this.gender = gender;
        this.married = married;
    }

    String getName() {
        return name;
    }

    int getAge() {
        return age;
    }

    boolean isMarried() {
        return married;
    }

    String getGender() {
        return gender.toString();
    }
}
