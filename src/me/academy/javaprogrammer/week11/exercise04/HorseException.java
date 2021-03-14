package me.academy.javaprogrammer.week11.exercise04;

final class HorseException extends RuntimeException {
    HorseException() {
        super("Invalid Horse object.");
    }

    HorseException(String message) {
        super(message);
    }
}
