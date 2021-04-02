package me.academy.javaprogrammer.finalproject.agenda.core.contact;

import me.academy.javaprogrammer.finalproject.agenda.core.phone.FixedPhoneNumber;
import me.academy.javaprogrammer.finalproject.agenda.core.phone.MobilePhoneNumber;
import me.academy.javaprogrammer.finalproject.agenda.core.phone.PhoneNumber;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public final class Contact implements Serializable {
    public static final String[] contactFields = new String[]{"First name", "Last name", "Birth date", "Phone number"};
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private PhoneNumber phoneNumber;

    public Contact(String firstName, String lastName, String birthDate, String phoneNumber) {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setPhoneNumber(phoneNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return firstName.equals(contact.firstName) && lastName.equals(contact.lastName) && birthDate.equals(contact.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthDate);
    }

    private boolean isNameValid(String name) {
        if (name == null) return false;
        if (name.trim().length() < 2) return false;

        for (char c : name.toCharArray()) {
            if (Character.isDigit(c)) return false;
        }

        return true;
    }

    private boolean isLocalDateValid(String localDate) {
        // verify if the string length is 10
        if (localDate.length() != 10) return false;
        // try to parse the string to a LocalDate object; if invalid then the parse method throws an exception
        LocalDate testLocalDate;
        try {
            testLocalDate = LocalDate.parse(localDate, dateTimeFormatter);
        } catch (DateTimeParseException exception) {
            return false;
        }

        // if the obtained date is valid then it can't be more than 125 years old
        int testYear = testLocalDate.getYear();
        if (testYear < Year.now().getValue() - 125) return false;

        // if the obtained date is valid then it can't be in future
        return !LocalDate.now().isBefore(testLocalDate);
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber == null) return false;
        if (phoneNumber.length() < 3) return false;
        return true;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getBirthDateString() {
        return birthDate.format(dateTimeFormatter);
    }

    public String getPhoneNumberString() {
        return phoneNumber.getPhoneNumber();
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setFirstName(String firstName) {
        if (isNameValid(firstName)) {
            this.firstName = capitalizeFirstLetterAndLowercaseRemaining(firstName);
        } else {
            throw new IllegalArgumentException("Invalid first name.");
        }
    }

    public void setLastName(String lastName) {
        if (isNameValid(lastName)) {
            this.lastName = capitalizeFirstLetterAndLowercaseRemaining(lastName);
        } else {
            throw new IllegalArgumentException("Invalid last name.");
        }
    }

    private String capitalizeFirstLetterAndLowercaseRemaining(String inputString) {
        return inputString.substring(0, 1).toUpperCase() + inputString.substring(1).toLowerCase();
    }

    public void setBirthDate(String birthDate) {
        if (isLocalDateValid(birthDate)) {
            this.birthDate = LocalDate.parse(birthDate, dateTimeFormatter);
        } else {
            throw new IllegalArgumentException("Invalid birth date.");
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        if (!isPhoneNumberValid(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number.");
        }
        switch (phoneNumber.substring(0,2)) {
            case "02":
            case "03":
                this.phoneNumber = new FixedPhoneNumber(phoneNumber);
                break;
            case "07":
                this.phoneNumber = new MobilePhoneNumber(phoneNumber);
                break;
            default:
                throw new IllegalArgumentException("Invalid phone number.");
        }
    }
}
