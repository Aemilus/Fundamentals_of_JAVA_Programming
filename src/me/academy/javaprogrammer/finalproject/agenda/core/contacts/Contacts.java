package me.academy.javaprogrammer.finalproject.agenda.core.contacts;

import me.academy.javaprogrammer.finalproject.agenda.core.contact.Contact;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public final class Contacts extends ArrayList<Contact> {
    // in order to assure that the ArrayList does not contain any duplicate elements
    // then we use the properties of a Set collection which guarantees no duplicates
    private final Set<Contact> contactsSet = new HashSet<>();

    @Override
    public boolean add(Contact contact) {
        // if the contact to be a added to the list is not seen as a duplicate in the set then we allow adding to list
        if (contactsSet.add(contact)) {
            return super.add(contact);
        } else {
            throw new IllegalArgumentException("Contact already exists.");
        }
    }

    @Override
    public void clear() {
        contactsSet.clear();
        super.clear();
    }
}
