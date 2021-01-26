package me.academy.javaprogrammer.week06.workshop;

public class Workshop6 {

    public static final String[] ANIMAL_NICKNAMES = {"Simba", "Martin", "Star", "Grizzly", "Fury", "Victory","Champ"};

    public static void main(String[] args) {
        // buld an array of animals
        Animal[] animals = new Animal[5];
        for (int i = 0; i < animals.length; i++) {
            animals[i] = generateRandomAnimal();
        }

        // for each animal do
        for (int i = 0; i < animals.length; i++) {
            Animal animal = animals[i];
            System.out.println("Animal #index:".replace("#index",String.valueOf(i)));
            // feed the animal - using an Animal reference
            animal.feed();
            // call the interface type methods
            if (animal instanceof Predator) {
                ((Predator) animal).hunt();
            }
            if (animal instanceof Hair) {
                ((Hair) animal).hairMotion();
            }
            // call specific methods
            if (animal instanceof Lion) {
                ((Lion) animal).roar();
            }
            if (animal instanceof Horse) {
                ((Horse) animal).neigh();
            }
            if (animal instanceof Bear) {
                ((Bear) animal).hibernate();
            }
        }
    }

    public static Animal generateRandomAnimal() {
        // generate random name
        int nameIndex = (int)(Math.random() * ANIMAL_NICKNAMES.length);
        String animalName = ANIMAL_NICKNAMES[nameIndex];

        // generate random Animal type
        int animalIndex = (int)(Math.random() * 3);
        switch (animalIndex) {
            case 0:
                return new Lion(animalName);
            case 1:
                return new Horse(animalName);
            case 2:
                return new Bear(animalName);
            default:
                return null;
        }
    }
}
