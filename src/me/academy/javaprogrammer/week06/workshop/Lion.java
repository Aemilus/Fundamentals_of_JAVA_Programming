package me.academy.javaprogrammer.week06.workshop;

public class Lion extends Animal implements Predator, Hair {

    public Lion(String name) {
        super(name);
    }

    public  void roar() {
        String action = "The lion #lion roars so powerful that other animals are frightened.";
        System.out.println(action.replace("#lion", getName()));
    }

    @Override
    public void feed() {
        String action = "The hungry lion #lion starts to eat his prey.";
        System.out.println(action.replace("#lion",getName()));
    }

    @Override
    public void hunt() {
        String action = "The lion #lion goes out for hunting.";
        System.out.println(action.replace("#lion", getName()));

        action = "The lion #lion has caught his prey.";
        System.out.println(action.replace("#lion", getName()));
    }

    @Override
    public void hairMotion() {
        String description = "When the lion #lion roars his hair vibrates.";
        System.out.println(description.replace("#lion", getName()));
    }
}
