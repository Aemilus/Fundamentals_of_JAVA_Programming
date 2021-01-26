package me.academy.javaprogrammer.week06.workshop;

public class Horse extends Animal implements Hair{
    public Horse(String name) {
        super(name);
    }

    public void neigh() {
        // calul necheaza
        String action = "The horse #horse makes a powerful neigh sound.";
        System.out.println(action.replace("#horse", getName()));
    }

    @Override
    public void feed() {
        String action = "The horse #horse is eating the green grass.";
        System.out.println(action.replace("#horse",getName()));
    }

    @Override
    public void hairMotion() {
        String description = "When horse #horse is running his hair is dancing in the wind.";
        System.out.println(description.replace("#horse",getName()));
    }
}
