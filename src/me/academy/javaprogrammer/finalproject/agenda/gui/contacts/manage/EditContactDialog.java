package me.academy.javaprogrammer.finalproject.agenda.gui.contacts.manage;

import me.academy.javaprogrammer.finalproject.AgendaFrame;
import me.academy.javaprogrammer.finalproject.agenda.core.contact.Contact;
import me.academy.javaprogrammer.finalproject.agenda.gui.contact.ContactDialog;

import javax.swing.*;

public final class EditContactDialog extends ContactDialog {
    private final AgendaFrame agendaFrame;
    private final Contact editContact;

    public EditContactDialog(AgendaFrame agendaFrame, Contact editContact) {
        super(agendaFrame, "Edit contact");
        this.agendaFrame = agendaFrame;
        this.editContact = editContact;
        initContactFields();
    }

    private void initContactFields() {
        this.getContactPanel().getFirstNameTextField().setText(editContact.getFirstName());
        this.getContactPanel().getLastNameTextField().setText(editContact.getLastName());
        this.getContactPanel().getBirthDateTextField().setText(editContact.getBirthDateString());
        this.getContactPanel().getPhoneNumberTextField().setText(editContact.getPhoneNumberString());
        this.getContactPanel().getPhoneNumberTypeCheckBox().setSelected(editContact.isMobilePhoneNumber());
    }

    @Override
    public void initListeners() {
        this.getContactPanel().getSaveButton().addActionListener(event -> {
            // get user input
            String firstName = this.getContactPanel().getFirstNameTextField().getText();
            String lastName = this.getContactPanel().getLastNameTextField().getText();
            String birthDate = this.getContactPanel().getBirthDateTextField().getText();
            String phoneNumber = this.getContactPanel().getPhoneNumberTextField().getText();
            boolean isMobilePhoneNumber = this.getContactPanel().getPhoneNumberTypeCheckBox().isSelected();
            // try to update the contact under edit
            try {
                // build a test contact from user input; in case there is an exception then the contact under edit is not affected
                Contact testContact = new Contact(firstName, lastName, birthDate, phoneNumber, isMobilePhoneNumber);
                // if no exception then update the contact under edit
                editContact.setFirstName(testContact.getFirstName());
                editContact.setLastName(testContact.getLastName());
                editContact.setBirthDate(testContact.getBirthDateString());
                editContact.setPhoneNumber(testContact.getPhoneNumberString(), isMobilePhoneNumber);
                // notify table that the data in model has changed
                agendaFrame.getContactsAdministrationPanel().getContactsTableModel().refreshModel();
                // close dialog box
                this.dispose();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}
