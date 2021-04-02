package me.academy.javaprogrammer.finalproject.agenda.gui.contacts.processing.sort;

import me.academy.javaprogrammer.finalproject.agenda.core.contact.Contact;
import me.academy.javaprogrammer.finalproject.agenda.core.sort.ContactSortersList;
import me.academy.javaprogrammer.finalproject.agenda.gui.contacts.table.ContactsTableModel;

import javax.swing.*;
import java.awt.*;

public final class SortContactsPanel extends JPanel {
    private ContactsTableModel contactsTableModel;

    private final JPanel sortingFieldsPanel = new JPanel();
    private final ButtonGroup sortingFieldsButtonGroup = new ButtonGroup();
    private final JRadioButton sortByFirstNameRadioButton = new JRadioButton(Contact.CONTACT_FIELDS[0]);
    private final JRadioButton sortByLastNameRadioButton = new JRadioButton(Contact.CONTACT_FIELDS[1]);
    private final JRadioButton sortByBirthDateRadioButton = new JRadioButton(Contact.CONTACT_FIELDS[2]);
    private final JRadioButton sortByPhoneNumberRadioButton = new JRadioButton(Contact.CONTACT_FIELDS[3]);
    private final JButton sortContactsButton = new JButton("Sort");

    public SortContactsPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Sort by"));
        initSortingFieldsButtonGroup();
        initSortingFieldsPanel();
        initComponents();
        initListeners();
    }

    private void initSortingFieldsButtonGroup() {
        sortingFieldsButtonGroup.add(sortByFirstNameRadioButton);
        sortingFieldsButtonGroup.add(sortByLastNameRadioButton);
        sortingFieldsButtonGroup.add(sortByBirthDateRadioButton);
        sortingFieldsButtonGroup.add(sortByPhoneNumberRadioButton);
        sortByLastNameRadioButton.setSelected(true);
    }

    private void initSortingFieldsPanel() {
        sortingFieldsPanel.setLayout(new GridLayout(1, 3, 10, 10));
        sortingFieldsPanel.add(sortByFirstNameRadioButton);
        sortingFieldsPanel.add(sortByLastNameRadioButton);
        sortingFieldsPanel.add(sortByBirthDateRadioButton);
        sortingFieldsPanel.add(sortByPhoneNumberRadioButton);
    }

    private void initComponents() {
        add(sortingFieldsPanel, BorderLayout.CENTER);
        add(sortContactsButton, BorderLayout.EAST);
    }

    private void initListeners() {
        ContactSortersList contactSortersList = new ContactSortersList();

        sortContactsButton.addActionListener(event -> {
            if (sortByFirstNameRadioButton.isSelected()) {
                contactsTableModel.setContactComparator(contactSortersList.getFirstNameContactComparator());
            }
            if (sortByLastNameRadioButton.isSelected()) {
                contactsTableModel.setContactComparator(contactSortersList.getLastNameContactComparator());
            }
            if (sortByBirthDateRadioButton.isSelected()) {
                contactsTableModel.setContactComparator(contactSortersList.getBirthDateContactComparator());
            }
            if (sortByPhoneNumberRadioButton.isSelected()) {
                contactsTableModel.setContactComparator(contactSortersList.getPhoneNumberContactComparator());
            }
            contactsTableModel.refreshModel();
        });
    }

    public void setContactsTableModel(ContactsTableModel contactsTableModel) {
        this.contactsTableModel = contactsTableModel;
    }
}
