package me.academy.javaprogrammer.week06.workshop;

public abstract class Animal {
    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    abstract void feed();
}
