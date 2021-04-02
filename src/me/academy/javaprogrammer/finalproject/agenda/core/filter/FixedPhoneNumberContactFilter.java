package me.academy.javaprogrammer.finalproject.agenda.core.filter;

import me.academy.javaprogrammer.finalproject.agenda.core.contact.Contact;
import me.academy.javaprogrammer.finalproject.agenda.core.filter.ContactFilter;
import me.academy.javaprogrammer.finalproject.agenda.core.phone.FixedPhoneNumber;

public final class FixedPhoneNumberContactFilter extends ContactFilter {
    public FixedPhoneNumberContactFilter() {
        super("fixed phones");
    }

    @Override
    public boolean test(Contact contact) {
        return contact.getPhoneNumber() instanceof FixedPhoneNumber;
    }
}
