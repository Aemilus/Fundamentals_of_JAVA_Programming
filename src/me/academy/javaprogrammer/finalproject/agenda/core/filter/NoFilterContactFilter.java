package me.academy.javaprogrammer.finalproject.agenda.core.filter;

import me.academy.javaprogrammer.finalproject.agenda.core.contact.Contact;
import me.academy.javaprogrammer.finalproject.agenda.core.filter.ContactFilter;

public final class NoFilterContactFilter extends ContactFilter {
    public NoFilterContactFilter() {
        super("no filter");
    }

    @Override
    public boolean test(Contact contact) {
        return true;
    }
}
