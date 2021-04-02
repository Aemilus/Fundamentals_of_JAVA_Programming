package me.academy.javaprogrammer.finalproject.agenda.gui.contacts.table;

import me.academy.javaprogrammer.finalproject.agenda.core.contacts.Contacts;
import me.academy.javaprogrammer.finalproject.agenda.core.contact.Contact;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public final class ContactsTableModel implements TableModel {
    private Contacts contacts;
    private final Contacts contactsModel = new Contacts();
    private final List<TableModelListener> tableModelListeners = new ArrayList<>();
    private Predicate<Contact> contactFilter = contact -> true;
    private Comparator<Contact> contactComparator = (contact1, contact2) -> contact1.getLastName().compareToIgnoreCase(contact2.getLastName());

    public ContactsTableModel(Contacts contacts) {
        this.contacts = contacts;
        refreshModel();
    }

    public void refreshModel() {
        contactsModel.clear();
        contacts.stream().filter(contactFilter).sorted(contactComparator).forEach(contactsModel::add);
        notifyListeners();
    }

    @Override
    public int getRowCount() {
        return contactsModel.size();
    }

    @Override
    public int getColumnCount() {
        return Contact.CONTACT_FIELDS.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return Contact.CONTACT_FIELDS[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 4) return Boolean.class;
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex < 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Contact contact = contactsModel.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return contact.getFirstName();
            case 1:
                return contact.getLastName();
            case 2:
                return contact.getBirthDateString();
            case 3:
                return contact.getPhoneNumberString();
            case 4:
                return contact.isMobilePhoneNumber();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Contact contact = contactsModel.get(rowIndex);
        String sValue = (String) aValue;
        try {
            switch (columnIndex) {
                case 0:
                    contact.setFirstName(sValue);
                    break;
                case 1:
                    contact.setLastName(sValue);
                    break;
                case 2:
                    contact.setBirthDate(sValue);
                    break;
                case 3:
                    contact.setPhoneNumber(sValue, contact.isMobilePhoneNumber());
                    break;
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        tableModelListeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        tableModelListeners.remove(l);
    }

    private void notifyListeners() {
        for (TableModelListener l : tableModelListeners) {
            l.tableChanged(null);
        }
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        refreshModel();
    }

    public void deleteContact(int selectedRow) {
        Contact deleteContact = contactsModel.get(selectedRow);
        contacts.remove(deleteContact);
        refreshModel();
    }

    public Contacts getContactsModel() {
        return contactsModel;
    }

    public void setContactFilter(Predicate<Contact> contactFilter) {
        this.contactFilter = contactFilter;
    }

    public void setContactComparator(Comparator<Contact> contactComparator) {
        this.contactComparator = contactComparator;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
        refreshModel();
    }
}
