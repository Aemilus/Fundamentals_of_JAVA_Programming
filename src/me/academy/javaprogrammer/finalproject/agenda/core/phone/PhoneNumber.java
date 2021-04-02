package me.academy.javaprogrammer.finalproject.agenda.core.phone;

import java.io.Serializable;
import java.util.Objects;

public abstract class PhoneNumber implements Comparable<PhoneNumber>, Serializable {
    private String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        setPhoneNumber(phoneNumber);
    }

    public final String getPhoneNumber() {
        return phoneNumber;
    }

    public final void setPhoneNumber(String phoneNumber) {
        if (isPhoneNumberValid(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Invalid phone number.");
        }
    }

    public static boolean isOnlyDigits(String phoneNumber) {
        for (char c : phoneNumber.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    @Override
    public int compareTo(PhoneNumber otherPhoneNumber) {
        return this.phoneNumber.compareTo(otherPhoneNumber.getPhoneNumber());
    }

    // writing your equals method is extremely important here!!
    // else your code will fail to identify equal PhoneNumber type objects
    // in Contacts class the HashSet used to prevent duplicate contacts in the list will fail if equals would not be written
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneNumber)) return false;
        PhoneNumber that = (PhoneNumber) o;
        return getPhoneNumber().equals(that.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhoneNumber());
    }

    public abstract boolean isPhoneNumberValid(String phoneNumber);
}
