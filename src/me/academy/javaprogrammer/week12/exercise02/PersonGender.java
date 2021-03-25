package me.academy.javaprogrammer.week12.exercise02;

enum PersonGender {
    NA("Not specified"),
    M("Male"),
    F("Female");

    private final String displayGender;

    PersonGender(String displayGender) {
        this.displayGender = displayGender;
    }

    String getDisplayGender() {
        return displayGender;
    }
}
