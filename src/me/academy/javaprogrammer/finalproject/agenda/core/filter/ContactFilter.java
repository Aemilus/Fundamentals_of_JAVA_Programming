package me.academy.javaprogrammer.finalproject.agenda.core.filter;

import me.academy.javaprogrammer.finalproject.agenda.core.contact.Contact;

import java.util.function.Predicate;

public abstract class ContactFilter implements Predicate<Contact> {
    private final String filterName;
    public ContactFilter(String filterName) {
        this.filterName = filterName;
    }

    public final String getFilterName() {
        return filterName;
    }
}
