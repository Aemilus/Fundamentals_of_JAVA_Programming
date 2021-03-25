package me.academy.javaprogrammer.week12.exercise02;

import javax.swing.*;
import java.awt.*;

class PersonDialog extends JDialog {
    private final AgendaPersonTable agendaPersonTable;
    private final PersonGender[] personGenders = PersonGender.values();

    private final JPanel personPanel = new JPanel();
    private final JTextField personName = new JTextField();
    private final JTextField personAge = new JTextField();
    private final JComboBox<String> personGender = new JComboBox<>();
    private final JCheckBox personMarried = new JCheckBox("Married");
    private final JButton submitButton = new JButton("Submit");

    PersonDialog(AgendaFrame agendaFrame, AgendaPersonTable agendaPersonTable) {
        super(agendaFrame, "Insert person details", true);
        this.agendaPersonTable = agendaPersonTable;
        initComponents();
        initListeners();
        initDialog();
        pack();
        setLocationRelativeTo(null);
    }

    private void initDialog() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 350));
        add(personPanel, BorderLayout.CENTER);
    }

    private void initComponents() {
        personName.setBorder(BorderFactory.createTitledBorder("Name"));
        personAge.setBorder(BorderFactory.createTitledBorder("Age"));
        // init the personGender combo box
        for (PersonGender enumGender : personGenders) {
            personGender.addItem(enumGender.getDisplayGender());
        }
        personGender.setBorder(BorderFactory.createTitledBorder("Gender"));

        initPersonPanel();
    }

    private void initPersonPanel() {
        personPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        personPanel.setLayout(new GridLayout(5, 1, 10, 10));
        personPanel.add(personName);
        personPanel.add(personAge);
        personPanel.add(personGender);
        personPanel.add(personMarried);
        personPanel.add(submitButton);
    }

    private void initListeners() {
        submitButton.addActionListener( event -> {
            try {
                // create new Person object from user input
                String inputName = personName.getText();
                int inputAge = Integer.parseInt(personAge.getText());
                PersonGender inputGender = personGenders[personGender.getSelectedIndex()];
                boolean inputMarried = personMarried.isSelected();
                Person person = new Person(inputName, inputAge, inputGender, inputMarried);
                // insert into database table
                agendaPersonTable.insertPersonRow(person);
            } catch (Exception exception) {
                // warn if any error
                AgendaFrame.showException(this, exception);
                // don't close the dialog so as to let the user correct any input mistake
                return;
            }
            // close dialog box if no user input mistake
            this.dispose();
        });
    }
}
