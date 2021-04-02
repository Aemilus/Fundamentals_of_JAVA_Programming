package me.academy.javaprogrammer.finalproject.agenda.core.phone;

public final class FixedPhoneNumber extends PhoneNumber {
    public FixedPhoneNumber(String phoneNumber) {
        super(phoneNumber);
    }

    @Override
    public boolean isPhoneNumberValid(String phoneNumber) {
        // phone number must be 10 digits length
        if (phoneNumber.length() != 10) return false;
        if (!isOnlyDigits(phoneNumber)) return false;

        // a fixed phone number must start with 02 or 03
        switch (phoneNumber.substring(0,2)) {
            case "02":
            case "03":
                return true;
            default:
                return false;
        }
    }
}
