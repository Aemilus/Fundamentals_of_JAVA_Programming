package me.academy.javaprogrammer.finalproject.agenda.gui.contacts;

import me.academy.javaprogrammer.finalproject.AgendaFrame;
import me.academy.javaprogrammer.finalproject.agenda.core.contacts.Contacts;
import me.academy.javaprogrammer.finalproject.agenda.gui.contacts.manage.ManageContactPanel;
import me.academy.javaprogrammer.finalproject.agenda.gui.contacts.processing.ProcessingContactsPanel;
import me.academy.javaprogrammer.finalproject.agenda.gui.contacts.table.ContactsTable;
import me.academy.javaprogrammer.finalproject.agenda.gui.contacts.table.ContactsTableModel;

import javax.swing.*;
import java.awt.*;

public class ContactsAdministrationPanel extends JPanel {
    // empty list of contacts
    private Contacts contacts = new Contacts();
    // setup panel where contacts are filtered and sorted
    private final ProcessingContactsPanel processingContactsPanel = new ProcessingContactsPanel();
    // setup table panel where contacts are displayed
    private final ContactsTableModel contactsTableModel = new ContactsTableModel(contacts);
    private final ContactsTable contactsTable = new ContactsTable(contactsTableModel);
    private final JScrollPane contactsTableScrollPane = new JScrollPane(contactsTable);
    // setup panel where contacts are managed
    private final ManageContactPanel manageContactPanel;

    public ContactsAdministrationPanel(AgendaFrame agendaFrame) {
        this.manageContactPanel = new ManageContactPanel(agendaFrame);
        initComponents();
        initProcessingContactsPanel();
        initPanel();
    }

    private void initPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(processingContactsPanel, BorderLayout.NORTH);
        add(contactsTableScrollPane, BorderLayout.CENTER);
        add(manageContactPanel, BorderLayout.SOUTH);
    }

    private void initComponents() {
        contactsTableScrollPane.setBorder(BorderFactory.createTitledBorder(""));
    }

    private void initProcessingContactsPanel() {
        processingContactsPanel.getFilterContactsPanel().setContactsTableModel(contactsTableModel);
        processingContactsPanel.getSortContactsPanel().setContactsTableModel(contactsTableModel);
    }

    public ContactsTableModel getContactsTableModel() {
        return contactsTableModel;
    }

    public ContactsTable getContactsTable() {
        return contactsTable;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public synchronized void setContacts(Contacts contacts) {
        this.contacts = contacts;
        contactsTableModel.setContacts(contacts);
    }
}
