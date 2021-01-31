package me.academy.javaprogrammer.week08.exercise01;

public enum Gender {
    M("Male"),
    F("Female");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
