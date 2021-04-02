package me.academy.javaprogrammer.finalproject.agenda.core.filter;

import me.academy.javaprogrammer.finalproject.agenda.core.contact.Contact;
import me.academy.javaprogrammer.finalproject.agenda.core.filter.ContactFilter;
import me.academy.javaprogrammer.finalproject.agenda.core.phone.MobilePhoneNumber;

public final class MobilePhoneNumberContactFilter extends ContactFilter {
    public MobilePhoneNumberContactFilter() {
        super("mobile phones");
    }

    @Override
    public boolean test(Contact contact) {
        return contact.getPhoneNumber() instanceof MobilePhoneNumber;
    }
}
