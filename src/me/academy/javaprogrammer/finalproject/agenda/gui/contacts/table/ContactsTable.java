package me.academy.javaprogrammer.finalproject.agenda.gui.contacts.table;

import javax.swing.*;

public final class ContactsTable extends JTable {

    public ContactsTable(ContactsTableModel contactsTableModel) {
        super(contactsTableModel);
        contactsTableModel.addTableModelListener(this);
    }
}
