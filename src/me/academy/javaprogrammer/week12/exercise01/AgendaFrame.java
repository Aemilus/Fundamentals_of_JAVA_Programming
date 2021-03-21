package me.academy.javaprogrammer.week12.exercise01;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class AgendaFrame extends JFrame {
    // handles database side operations
    private final AgendaDataSource agendaDataSource = new AgendaDataSource();

    private final JPanel rootPanel = new JPanel();
    // table related components
    private List<Person> personList;
    private final String[] columnNames = new String[]{"Name", "Age"};
    private final DefaultTableModel tableModel = new AgendaTableModel(columnNames, 0);
    private final JTable table = new JTable(tableModel);
    private final JScrollPane scrollPane = new JScrollPane(table);
    // buttons related components
    private final JPanel buttonsPanel = new JPanel();
    private final JButton insertButton = new JButton("Add contact");
    private final JButton deleteButton = new JButton("Delete");

    AgendaDataSource getAgendaDataSource() {
        return this.agendaDataSource;
    }

    AgendaFrame() {
        initComponents();
        initFrame();
        initListeners();
        pack();
        setLocationRelativeTo(null);
    }

    private void initFrame() {
        setTitle("Agenda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 300));
        setLayout(new BorderLayout());
        add(rootPanel, BorderLayout.CENTER);
    }

    private void initComponents() {
        initButtonsPanel();
        initTable();
        initRootPanel();
    }

    private void initTable() {
        refreshTableModel();
    }

    private void refreshTableModel() {
        tableModel.setRowCount(0);
        try {
            personList = agendaDataSource.selectAllPersons();
            personList.forEach(person -> {
                String[] row = new String[]{person.getName(), String.valueOf(person.getAge())};
                tableModel.addRow(row);
            });
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(this, sqlException.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initButtonsPanel() {
        buttonsPanel.setLayout(new GridLayout(1, 2, 10, 10));
        buttonsPanel.add(insertButton);
        buttonsPanel.add(deleteButton);
    }

    private void initRootPanel() {
        rootPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(scrollPane, BorderLayout.CENTER);
        rootPanel.add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void initListeners() {
        insertButton.addActionListener(event -> {
            PersonDialog personDialog = new PersonDialog(this);
            personDialog.setVisible(true);
            refreshTableModel();
        });
        deleteButton.addActionListener(event -> {
            int id = table.getSelectedRow();
            if (id < 0) return;
            Person person = personList.get(id);
            try {
                agendaDataSource.deletePerson(person);
            } catch (SQLException sqlException) {
                JOptionPane.showMessageDialog(this, sqlException.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
            }
            refreshTableModel();
        });
    }

    public static void main(String[] args) {
        AgendaFrame af = new AgendaFrame();
        SwingUtilities.invokeLater(() -> af.setVisible(true));
    }
}
