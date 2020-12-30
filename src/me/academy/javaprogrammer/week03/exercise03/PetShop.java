package me.academy.javaprogrammer.week03.exercise03;

import java.util.Arrays;

/**
 * Build a class PetShop which emulates a collection of pets.
 * Properties:
 * - capacity is passed as argument in constructor
 * - create the illusion the collection is not limited by allocating a new array when needed: Arrays.copyOf()
 * - pets name to be assigned from a predefined array/list of names: String[]
 *
 * Add Methods for:
 * - you can add new pets in collection in two ways
 * - feeding pets
 * - removing pet
 * - search
 */
public class PetShop {
    private static final String[] PET_NAMES = new String[] {"Rex", "Max", "Thomas", "Lucky", "Lola", "Jack"};

    private Pet[] pets;
    private int nextPetIndex;

    public PetShop(int size) {
        pets = new Pet[size];
    }

    public void addPet(Pet pet) {
        addPet(pet, nextPetIndex);
    }

    public static Pet getRandomPet() {
        // generate a random pet
        int petNameIndex = (int)(Math.random() * PET_NAMES.length);
        String petName = PET_NAMES[petNameIndex];
        return new Pet(petName);
    }

    public void addPet(Pet pet, int index) {
        if (index < 0) {
            System.out.println("Error: Index can't be negative.");
            return;
        }
        if (index > nextPetIndex) {
            System.out.println("Error: Index can't be greater than the next index after last pet.");
            return;
        }
        // if full then extend the pet shop by a third
        if (nextPetIndex == pets.length) {
            pets = Arrays.copyOf(pets,3 * pets.length / 2);
        }
        // add pet tp collection
        if (index == nextPetIndex) {
            // add pet to end if index is after last pet in collection
            pets[nextPetIndex++] = pet;
        } else {
            // move current pet placed at index right after last pet in collection and put new pet at index
            pets[nextPetIndex++] = pets[index];
            pets[index] = pet;
        }
    }

    public void removePet(int index) {
        if (index < 0) {
            System.out.println("Error: Index can't be negative.");
            return;
        }
        if (index >= nextPetIndex) {
            System.out.println("Error: Index can't be greater than index of last pet.");
            return;
        }
        if (index == nextPetIndex - 1) {
            // if we want to remove last pet in collection
            pets[index] = null;
            nextPetIndex--;
        } else {
            // remove the pet at index and move last pet in it's place
            pets[index] = pets[--nextPetIndex];
            pets[nextPetIndex] = null;
        }
    }

    public void feedPets() {
        System.out.println("Feeding all pets:");
        for(int i = 0; i < nextPetIndex; i++) {
            pets[i].eat();
        }
    }

    public int[] searchPet(String name) {
        // return an array with indexes where pets name matches the search name string
        int[] searchResult = new int[nextPetIndex]; // in worst case all pets have the same name as the one searched
        int j = 0; // searchResult array cursor
        for(int i = 0; i < nextPetIndex; i++) {
            if (name.equals(pets[i].getName())) {
                searchResult[j++] = i;
            }
        }

        return Arrays.copyOfRange(searchResult,0,j);
    }

    @Override
    public String toString() {
        return Arrays.toString(pets);
    }

    public static void main(String[] args) {
        PetShop petShop = new PetShop(3);
        for(int i = 0; i < 5; i++) {
            petShop.addPet(getRandomPet());
        }
        System.out.println(petShop.toString());
        petShop.feedPets();
        petShop.addPet(getRandomPet(),0);
        System.out.println(petShop.toString());
        petShop.feedPets();
        // looking for Jack
        System.out.println("Search result: " + Arrays.toString(petShop.searchPet("Jack")));
        petShop.removePet(2);
        System.out.println(petShop.toString());
    }
}
