package me.academy.javaprogrammer.finalproject.agenda.gui.contacts.manage;

import me.academy.javaprogrammer.finalproject.AgendaFrame;
import me.academy.javaprogrammer.finalproject.agenda.core.contact.Contact;

import javax.swing.*;
import java.awt.*;

public final class ManageContactPanel extends JPanel {
    private final AgendaFrame agendaFrame;

    private final JButton addContactButton = new JButton("Add");
    private final JButton deleteContactButton = new JButton("Delete");
    private final JButton editContactButton = new JButton("Edit");

    public ManageContactPanel(AgendaFrame agendaFrame) {
        this.agendaFrame = agendaFrame;
        initPanel();
        initListeners();
    }

    private void initPanel() {
        setBorder(BorderFactory.createTitledBorder(""));
        setLayout(new GridLayout(1, 3, 10, 10));
        add(addContactButton);
        add(deleteContactButton);
        add(editContactButton);
    }

    private void initListeners() {
        addContactButton.addActionListener(event -> {
            AddContactDialog addContactDialog = new AddContactDialog(agendaFrame);
            addContactDialog.setVisible(true);
        });

        deleteContactButton.addActionListener(event -> {
            int selectedRow = agendaFrame.getContactsAdministrationPanel().getContactsTable().getSelectedRow();
            if (selectedRow < 0) return;
            int option = JOptionPane.showConfirmDialog(
                    agendaFrame,
                    "Do you want to delete this contact?",
                    "Delete",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
            // if user didn't confirm then do nothing
            if (option > 0) return;
            // else remove selected contact
            agendaFrame.getContactsAdministrationPanel().getContactsTableModel().deleteContact(selectedRow);
        });

        editContactButton.addActionListener(event -> {
            int selectedRow = agendaFrame.getContactsAdministrationPanel().getContactsTable().getSelectedRow();
            if (selectedRow < 0) return;
            Contact editContact = agendaFrame.getContactsAdministrationPanel().getContactsTableModel().getContactsModel().get(selectedRow);
            EditContactDialog editContactDialog = new EditContactDialog(agendaFrame, editContact);
            editContactDialog.setVisible(true);
            // reselect the row
            agendaFrame.getContactsAdministrationPanel().getContactsTable().setRowSelectionInterval(selectedRow, selectedRow);
        });
    }
}
