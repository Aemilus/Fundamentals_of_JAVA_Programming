package me.academy.javaprogrammer.finalproject.agenda.gui.contact;

import me.academy.javaprogrammer.finalproject.agenda.core.contact.Contact;

import javax.swing.*;
import java.awt.*;

public final class ContactPanel extends JPanel {
    private final JPanel contactFieldsPanel = new JPanel();
    private final JTextField firstNameTextField = new JTextField();
    private final JTextField lastNameTextField = new JTextField();
    private final JTextField birthDateTextField = new JTextField();
    private final JTextField phoneNumberTextField = new JTextField();

    private final JPanel buttonsPanel = new JPanel();
    private final JButton saveButton = new JButton("Save");

    public ContactPanel() {
        setLayout(new BorderLayout(10, 10));
        initComponents();
    }

    private void initComponents() {
        initContactFields();
        initContactFieldsPanel();
        initButtonsPanel();
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contactFieldsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void initContactFields() {
        firstNameTextField.setBorder(BorderFactory.createTitledBorder(Contact.contactFields[0]));
        lastNameTextField.setBorder(BorderFactory.createTitledBorder(Contact.contactFields[1]));
        birthDateTextField.setBorder(BorderFactory.createTitledBorder(Contact.contactFields[2]));
        phoneNumberTextField.setBorder(BorderFactory.createTitledBorder(Contact.contactFields[3]));
    }

    private void initContactFieldsPanel() {
        contactFieldsPanel.setLayout(new GridLayout(4, 1, 10, 10));
        contactFieldsPanel.add(firstNameTextField);
        contactFieldsPanel.add(lastNameTextField);
        contactFieldsPanel.add(birthDateTextField);
        contactFieldsPanel.add(phoneNumberTextField);
    }

    private void initButtonsPanel() {
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonsPanel.add(saveButton);
    }

    public JTextField getFirstNameTextField() {
        return firstNameTextField;
    }

    public JTextField getLastNameTextField() {
        return lastNameTextField;
    }

    public JTextField getBirthDateTextField() {
        return birthDateTextField;
    }

    public JTextField getPhoneNumberTextField() {
        return phoneNumberTextField;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
