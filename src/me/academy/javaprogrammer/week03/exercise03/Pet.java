package me.academy.javaprogrammer.week03.exercise03;

public class Pet {
    private final String name;

    Pet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void eat() {
        System.out.println(name + " is eating.");
    }

    @Override
    public String toString() {
        return "{".concat(name).concat("}");
    }
}
