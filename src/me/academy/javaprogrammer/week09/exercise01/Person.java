package me.academy.javaprogrammer.week09.exercise01;

import java.util.Objects;

public class Person implements Comparable<Person> {
    // maximum age a person can reach
    public static final int MAX_AGE = 125;
    /**
     We need to be able to uniquely identify an object in the list because:
     - the order of objects can change in a list
     - there can be equal objects
     - in a filtered list model the index/position of displayed Person object will not match the index in the unfiltered list.
     Thus with every new Person object created the COUNTER will be incremented and set to id of this object.

     See ActionListener for deletePersonButton!!
     */
    private static int COUNTER = 1;

    private final int id;
    private String name;
    private int age;
    private Gender gender;
    private boolean married;

    public Person(String name, int age, String gender, boolean married) {
        this(COUNTER, name, age, gender, married);
        // if the private constructor throws a runtime exception then the counter is not incremented
        COUNTER++;
    }

    private Person(int id, String name, int age, String gender, boolean married) {
        validateId(id);
        validateName(name);
        validateAge(age);
        validateGender(gender);

        this.id = id;
        this.name = name.trim();
        this.age = age;
        this.gender = Gender.valueOf(gender);
        this.married = married;
    }

    private static void validateId(int id) {
        if (id < 1) throw new IllegalArgumentException("Person: ID must be a positive integer.");
    }

    public static void validateName(String name) {
        if (name == null) throw new NullPointerException("Person: Name can't be null.");
        if (name.trim().equals("")) throw new IllegalArgumentException("Person: Name can't be empty.");
    }

    public static void validateAge(int age) {
        if (age < 1) throw new IllegalArgumentException("Person: Age must pe a positive integer.");
        if (age > MAX_AGE) throw new IllegalArgumentException("Person: Age can't be greater than maximum of " + MAX_AGE + ".");
    }

    public static void validateGender(String gender) {
        if (gender == null) throw new NullPointerException("Person: Gender can't be null.");
    }

    // getters
    public int getId() {
        return id;
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

    public boolean isMarried() {
        return married;
    }

    // setters
    public void setName(String name) {
        validateName(name);
        this.name = name.trim();
    }

    public void setAge(int age) {
        validateAge(age);
        this.age = age;
    }

    public void setGender(String gender) {
        validateGender(gender);
        this.gender = Gender.valueOf(gender);
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
        return getName().compareToIgnoreCase(p.getName());
    }

    // to be used as method references in Comparator object - see ActionListener for sortByAgeRadioButton
    public static int sortByAge(Person p1, Person p2) {
        return p1.getAge() - p2.getAge();
    }
}
