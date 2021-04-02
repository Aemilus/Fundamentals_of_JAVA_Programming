package me.academy.javaprogrammer.finalproject.agenda.gui.contacts.processing.filter;

import me.academy.javaprogrammer.finalproject.agenda.core.filter.ContactFilter;
import me.academy.javaprogrammer.finalproject.agenda.core.filter.CustomContactFilter;
import me.academy.javaprogrammer.finalproject.agenda.core.filters.ContactFiltersMap;
import me.academy.javaprogrammer.finalproject.agenda.gui.contacts.table.ContactsTableModel;

import javax.swing.*;
import java.awt.*;

public final class FilterContactsPanel extends JPanel {
    private final JComboBox<String> filterContactsComboBox = new JComboBox<>();
    private final JTextField searchStringTextField = new JTextField();
    private final JButton filterContactsButton = new JButton("Filter");

    private final ContactFiltersMap contactFiltersMap = new ContactFiltersMap(searchStringTextField);

    private ContactsTableModel contactsTableModel;
    private ContactFilter selectedContactFilter;

    public FilterContactsPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Filter by"));
        initComboBox();
        initPanel();
        initListeners();
    }

    private void initComboBox() {
        // populate combo box with contact filters
        for (String filterName : contactFiltersMap.keySet()) {
            filterContactsComboBox.addItem(filterName);
        }
        setupContactFilter();
    }

    private void initPanel() {
        add(filterContactsComboBox, BorderLayout.WEST);
        add(searchStringTextField, BorderLayout.CENTER);
        add(filterContactsButton, BorderLayout.EAST);
    }

    private void initListeners() {
        filterContactsComboBox.addActionListener(event -> setupContactFilter());

        filterContactsButton.addActionListener(event -> {
            contactsTableModel.setContactFilter(selectedContactFilter);
            contactsTableModel.refreshModel();
        });
    }

    private void setupContactFilter() {
        // change the contact filter to the value selected in combo box
        String selectedItem = (String) filterContactsComboBox.getSelectedItem();
        selectedContactFilter = contactFiltersMap.get(selectedItem);
        // if custom filter is selected then user is able to enter a search string
        if (selectedContactFilter instanceof CustomContactFilter) {
            searchStringTextField.setEnabled(true);
        } else {
            searchStringTextField.setText(null);
            searchStringTextField.setEnabled(false);
        }
    }

    public void setContactsTableModel(ContactsTableModel contactsTableModel) {
        this.contactsTableModel = contactsTableModel;
    }
}
