package me.academy.javaprogrammer.week06.workshop;

public class Bear extends Animal implements Predator{
    public Bear(String name) {
        super(name);
    }

    public void hibernate() {
        String action = "The bear #bear is hibernating.";
        System.out.println(action.replace("#bear", getName()));
    }

    @Override
    public void feed() {
        String action = "The bear #bear is eating the honey.";
        System.out.println(action.replace("#bear", getName()));
    }

    @Override
    public void hunt() {
        String action = "The bear #bear is looking for the bee's hive.";
        System.out.println(action.replace("#bear", getName()));

        action = "The bear #bear has found the bee's hive.";
        System.out.println(action.replace("#bear", getName()));
    }
}
