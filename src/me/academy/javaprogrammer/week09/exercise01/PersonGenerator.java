package me.academy.javaprogrammer.week09.exercise01;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonGenerator {

    public static Person generatePerson() {
        String name = names[(int)(Math.random() * names.length)];
        int age = (int)(Math.random() * Person.MAX_AGE) + 1;
        String gender = ((int)(Math.random() * 2) < 1) ? "M" : "F";
        boolean married = (int)(Math.random() * 2) < 1;
        return new Person(name, age, gender, married);
    }

    public static List<Person> generatePersonList(int size) {
        return Stream.generate(PersonGenerator::generatePerson).limit(size).collect(Collectors.toList());
    }

    private static final String[] names = {
            "Joe",
            "John",
            "Mary",
            "Simon",
            "Alicia",
            "David",
            "Leo",
            "Colin",
            "Vasile",
            "Bogdan",
            "Catalin",
            "Diana",
            "Emil",
            "Florin",
            "Gheorghe",
            "George",
            "Georgiana",
            "Horia",
            "Ion",
            "Kevin",
            "Laura",
            "Marius",
            "Noe",
            "Oana",
            "Petru",
            "Pavel",
            "Raul",
            "Stefan",
            "Tim",
            "Victor"
    };
}
