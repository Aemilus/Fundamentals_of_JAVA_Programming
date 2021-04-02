package me.academy.javaprogrammer.finalproject.agenda.gui.contact;

import me.academy.javaprogrammer.finalproject.agenda.core.contact.Contact;

import javax.swing.*;
import java.awt.*;

public final class ContactPanel extends JPanel {
    private final JPanel contactFieldsPanel = new JPanel();
    private final JPanel firstNamePanel = new JPanel(new BorderLayout());
    private final JTextField firstNameTextField = new JTextField();
    private final JPanel lastNamePanel = new JPanel(new BorderLayout());
    private final JTextField lastNameTextField = new JTextField();
    private final JPanel birthDatePanel = new JPanel(new BorderLayout());
    private final JTextField birthDateTextField = new JTextField();

    private final JPanel phoneNumberPanel = new JPanel();
    private final JTextField phoneNumberTextField = new JTextField();
    private final JCheckBox phoneNumberTypeCheckBox = new JCheckBox(Contact.CONTACT_FIELDS[4], true);

    private final JPanel buttonsPanel = new JPanel();
    private final JButton saveButton = new JButton("Save");

    public ContactPanel() {
        setLayout(new BorderLayout(10, 10));
        initContactFieldsPanels();
        initPhoneNumberPanel();
        initContactFieldsPanel();
        initButtonsPanel();
        initPanel();
    }

    private void initPanel() {
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contactFieldsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void initContactFieldsPanels() {
        initFirstNamePanel();
        initLastNamePanel();
        initBirthDatePanel();
        initPhoneNumberPanel();
    }

    private void initFirstNamePanel() {
        firstNamePanel.setBorder(BorderFactory.createTitledBorder(Contact.CONTACT_FIELDS[0]));
        firstNamePanel.add(firstNameTextField, BorderLayout.CENTER);
    }

    private void initLastNamePanel() {
        lastNamePanel.setBorder(BorderFactory.createTitledBorder(Contact.CONTACT_FIELDS[1]));
        lastNamePanel.add(lastNameTextField, BorderLayout.CENTER);
    }

    private void initBirthDatePanel() {
        birthDatePanel.setBorder(BorderFactory.createTitledBorder(Contact.CONTACT_FIELDS[2]));
        birthDatePanel.add(birthDateTextField, BorderLayout.CENTER);
    }

    private void initPhoneNumberPanel() {
        phoneNumberPanel.setLayout(new BorderLayout(10, 10));
        phoneNumberPanel.setBorder(BorderFactory.createTitledBorder(Contact.CONTACT_FIELDS[3]));
        phoneNumberPanel.add(phoneNumberTextField, BorderLayout.CENTER);
        phoneNumberPanel.add(phoneNumberTypeCheckBox, BorderLayout.EAST);
    }

    private void initContactFieldsPanel() {
        contactFieldsPanel.setLayout(new GridLayout(4, 1, 10, 10));
        contactFieldsPanel.add(firstNamePanel);
        contactFieldsPanel.add(lastNamePanel);
        contactFieldsPanel.add(birthDatePanel);
        contactFieldsPanel.add(phoneNumberPanel);
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

    public JCheckBox getPhoneNumberTypeCheckBox() {
        return phoneNumberTypeCheckBox;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
