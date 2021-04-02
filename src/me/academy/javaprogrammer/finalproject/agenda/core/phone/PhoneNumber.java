package me.academy.javaprogrammer.finalproject.agenda.core.phone;

import java.io.Serializable;

public abstract class PhoneNumber implements Comparable<PhoneNumber>, Serializable {
    private String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        if (isPhoneNumberValid(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Invalid phone number.");
        }
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

    public abstract boolean isPhoneNumberValid(String phoneNumber);
}
