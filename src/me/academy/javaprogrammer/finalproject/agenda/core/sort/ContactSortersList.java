package me.academy.javaprogrammer.finalproject.agenda.core.sort;

import me.academy.javaprogrammer.finalproject.agenda.core.contact.Contact;

import java.util.ArrayList;
import java.util.Comparator;

public final class ContactSortersList extends ArrayList<Comparator<Contact>> {
    public ContactSortersList() {
        add((contact1, contact2) -> contact1.getFirstName().compareToIgnoreCase(contact2.getFirstName()));
        add((contact1, contact2) -> contact1.getLastName().compareToIgnoreCase(contact2.getLastName()));
        add((contact1, contact2) -> contact1.getBirthDate().compareTo(contact2.getBirthDate()));
        add((contact1, contact2) -> contact1.getPhoneNumber().compareTo(contact2.getPhoneNumber()));
    }

    public Comparator<Contact> getFirstNameContactComparator() {
        return get(0);
    }

    public Comparator<Contact> getLastNameContactComparator() {
        return get(1);
    }

    public Comparator<Contact> getBirthDateContactComparator() {
        return get(2);
    }

    public Comparator<Contact> getPhoneNumberContactComparator() {
        return get(3);
    }
}
