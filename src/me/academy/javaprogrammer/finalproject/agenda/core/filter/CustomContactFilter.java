package me.academy.javaprogrammer.finalproject.agenda.core.filter;

import me.academy.javaprogrammer.finalproject.agenda.core.contact.Contact;

import javax.swing.*;

public final class CustomContactFilter extends ContactFilter {
    private final JTextField searchStringTextField;
    public CustomContactFilter(JTextField searchStringTextField) {
        super("custom");
        this.searchStringTextField = searchStringTextField;
    }

    @Override
    public boolean test(Contact contact) {
        String searchString = searchStringTextField.getText().toLowerCase();
        if (contact.getFirstName().toLowerCase().contains(searchString)) return true;
        if (contact.getLastName().toLowerCase().contains(searchString)) return true;
        if (contact.getPhoneNumberString().contains(searchString)) return true;

        return false;
    }
}
