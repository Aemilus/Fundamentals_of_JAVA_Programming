package me.academy.javaprogrammer.finalproject.agenda.core.phone;

public final class MobilePhoneNumber extends PhoneNumber {
    public MobilePhoneNumber(String phoneNumber) {
        super(phoneNumber);
    }

    @Override
    public boolean isPhoneNumberValid(String phoneNumber) {
        // phone number must be 10 digits length
        if (phoneNumber.length() != 10) return false;
        if (!isOnlyDigits(phoneNumber)) return false;
        // mobile phone number must start with 07
        return phoneNumber.startsWith("07");
    }
}
