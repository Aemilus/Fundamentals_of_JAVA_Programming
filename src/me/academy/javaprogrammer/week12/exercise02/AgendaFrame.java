package me.academy.javaprogrammer.week12.exercise02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class AgendaFrame extends JFrame {
    // offers connection to agenda database
    private AgendaDataSource agendaDataSource;

    private final JPanel rootPanel = new JPanel();
    // table related components
    private JScrollPane scrollPane;
    private AgendaPersonTableModel agendaPersonTableModel;
    private AgendaPersonTable agendaPersonTable;
    // buttons related components
    private final JPanel buttonsPanel = new JPanel();
    private final JButton insertButton = new JButton("Add contact");
    private final JButton deleteButton = new JButton("Delete");

    AgendaFrame() {
        initComponents();
        initFrame();
        initListeners();
        pack();
        setLocationRelativeTo(null);
    }

    private void initFrame() {
        setTitle("Agenda");
        setPreferredSize(new Dimension(500, 300));
        setLayout(new BorderLayout());
        add(rootPanel, BorderLayout.CENTER);
    }

    private void initComponents() {
        initTable();
        initScrollPane();
        initButtonsPanel();
        initRootPanel();
    }

    private void initTable() {
        try {
            agendaDataSource = new AgendaDataSource();
            agendaPersonTableModel = new AgendaPersonTableModel(agendaDataSource.getConnection());
        } catch (SQLException sqlException) {
            showException(this, sqlException);
        }
        agendaPersonTable = new AgendaPersonTable(agendaPersonTableModel);
    }

    private void initScrollPane() {
        scrollPane = new JScrollPane(agendaPersonTable);
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
            PersonDialog personDialog = new PersonDialog(this, agendaPersonTable);
            personDialog.setVisible(true);
        });

        deleteButton.addActionListener(event -> {
            try {
                agendaPersonTable.deleteSelectedRow();
            } catch (SQLException sqlException) {
                showException(this, sqlException);
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }
        });
    }

    public static void showException(Component parentComponent, Exception exception) {
        JOptionPane.showMessageDialog(parentComponent, exception.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
    }

    private void close() {
        try {
            agendaPersonTableModel.close();
            agendaDataSource.close();
            System.exit(0);
        } catch (SQLException sqlException) {
            showException(this, sqlException);
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        AgendaFrame af = new AgendaFrame();
        SwingUtilities.invokeLater(() -> af.setVisible(true));
    }
}
