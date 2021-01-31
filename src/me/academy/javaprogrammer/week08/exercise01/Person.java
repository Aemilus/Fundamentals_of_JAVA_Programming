package me.academy.javaprogrammer.week08.exercise01;

import java.util.Objects;

public class Person implements Comparable<Person> {
    // maximum age a person can reach
    public static final int MAX_AGE = 125;
    /**
     We need to be able to uniquely identify an object in the list because:
     - the order of objects can change in a list
     - there can be many equal objects
     - in a filtered list model the index/position of displayed Person object will not match the position in the unfiltered list.
     Thus with every new Person object created the id of this object will be set to COUNTER value
     and then the COUNTER is incremented and will be assigned to the next object when the time comes.

     See ActionListener for deletePersonButton!!
     */
    private static int COUNTER = 0;

    private final int id;
    private String name;
    private int age;
    private Gender gender;
    private boolean married;

    private Person(String name, int age, Gender gender, boolean married) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.married = married;

        this.id = COUNTER++;
    }

    public static Person getInstance(String name, int age, String gender, boolean married) {
        // return null if the input is invalid
        if (name == null) return null;
        if (age < 1) return null;
        if (age > MAX_AGE) return null;
        try {
            Gender.valueOf(gender);
        } catch (RuntimeException re) {
            return null;
        }
        // return new Person object if the input is valid
        return new Person(name, age, Gender.valueOf(gender), married);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public boolean isMarried() {
        return married;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    @Override
    public String toString() {
        return id + ". " + name.concat(" - ").concat(String.valueOf(age)).concat(" years");
    }

    // equals() and hash() not used in this exercise but good practice to get used to write them
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        if (getAge() != person.getAge()) return false;
        if (isMarried() != person.isMarried()) return false;
        if (!getName().equals(person.getName())) return false;
        if (getGender() != person.getGender()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getGender(), isMarried());
    }

    // "native-sorting" -  see ActionListener for sortByNameRadioButton
    @Override
    public int compareTo(Person p) {
        return getName().compareTo(p.getName());
    }

    // to be used as method references - see ActionListener for sortByAgeRadioButton
    public static int sortByAge(Person p1, Person p2) {
        return p1.getAge() - p2.getAge();
    }
}
