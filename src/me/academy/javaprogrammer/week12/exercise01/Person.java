package me.academy.javaprogrammer.week12.exercise01;

public class Person {
    private int id;
    private String name;
    private int age;

    public Person(String name, int age) {
        if (name == null) throw new NullPointerException("Name is null.");
        if (name.trim().isEmpty()) throw new IllegalArgumentException("Name is empty.");
        if (age < 1 || age > 125) throw new IllegalArgumentException("Invalid age.");

        this.name = name;
        this.age = age;
    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
