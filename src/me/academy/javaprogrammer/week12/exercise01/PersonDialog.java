package me.academy.javaprogrammer.week12.exercise01;

import javax.swing.*;
import java.awt.*;

class PersonDialog extends JDialog {
    private final AgendaFrame agendaFrame;
    private final JPanel personPanel = new JPanel();
    private final JTextField personName = new JTextField();
    private final JTextField personAge = new JTextField();
    private final JButton submitButton = new JButton("Submit");

    PersonDialog(AgendaFrame agendaFrame) {
        super(agendaFrame, "Insert person details", true);
        this.agendaFrame = agendaFrame;
        initComponents();
        initListeners();
        initDialog();
        pack();
        setLocationRelativeTo(null);
    }

    private void initDialog() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 210));
        add(personPanel, BorderLayout.CENTER);
    }

    private void initComponents() {
        personName.setBorder(BorderFactory.createTitledBorder("Name"));
        personAge.setBorder(BorderFactory.createTitledBorder("Age"));
        initPersonPanel();
    }

    private void initPersonPanel() {
        personPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        personPanel.setLayout(new GridLayout(3, 1, 10, 10));
        personPanel.add(personName);
        personPanel.add(personAge);
        personPanel.add(submitButton);
    }

    private void initListeners() {
        submitButton.addActionListener( event -> {
            try {
                // create new Person object from user input
                String inputName = personName.getText();
                int inputAge = Integer.parseInt(personAge.getText());
                Person person = new Person(inputName, inputAge);
                // insert into database table
                agendaFrame.getAgendaDataSource().insertPerson(person);
            } catch (Exception exception) {
                // warn if any error
                JOptionPane.showMessageDialog(this, exception.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.dispose();
        });
    }
}
