package me.academy.javaprogrammer.finalproject.agenda.gui.contacts.manage;

import me.academy.javaprogrammer.finalproject.AgendaFrame;
import me.academy.javaprogrammer.finalproject.agenda.core.contact.Contact;
import me.academy.javaprogrammer.finalproject.agenda.gui.contact.ContactDialog;

import javax.swing.*;

public final class AddContactDialog extends ContactDialog {
    private final AgendaFrame agendaFrame;

    public AddContactDialog(AgendaFrame agendaFrame) {
        super(agendaFrame, "Add contact");
        this.agendaFrame = agendaFrame;
    }

    private Contact getContact() {
        String firstName = this.getContactPanel().getFirstNameTextField().getText();
        String lastName = this.getContactPanel().getLastNameTextField().getText();
        String birthDate = this.getContactPanel().getBirthDateTextField().getText();
        String phoneNumber = this.getContactPanel().getPhoneNumberTextField().getText();
        boolean isMobilePhoneNumber = this.getContactPanel().getPhoneNumberTypeCheckBox().isSelected();
        return new Contact(firstName, lastName, birthDate, phoneNumber, isMobilePhoneNumber);
    }

    @Override
    public void initListeners() {
        getContactPanel().getSaveButton().addActionListener(event -> {
            try {
                Contact newContact = getContact();
                agendaFrame.getContactsAdministrationPanel().getContactsTableModel().addContact(newContact);
                this.dispose();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}
