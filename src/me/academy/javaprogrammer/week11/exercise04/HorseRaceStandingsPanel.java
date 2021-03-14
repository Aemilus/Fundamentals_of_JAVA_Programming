package me.academy.javaprogrammer.week11.exercise04;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

final class HorseRaceStandingsPanel extends JPanel {
    private final List<Horse> horses;

    private final String[] columnNames = new String[]{"Rank", "Horse"};
    private final DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
    private final JTable table = new JTable(tableModel);
    private final JScrollPane tableScrollPane = new JScrollPane(table);
    private final JLabel title = new JLabel("Race Standings");
    private final JLabel betResult = new JLabel(" ");

    HorseRaceStandingsPanel(List<Horse> horses) {
        this.horses = horses;
        initComponents();
        initPanel();
    }

    private void initPanel() {
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(10,10,50,10));
        add(title, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(betResult, BorderLayout.SOUTH);
    }

    private void initComponents() {
        initTable();
        initLabels();
    }

    private void initLabels() {
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        betResult.setFont(new Font(betResult.getFont().getName(), Font.BOLD, 30));
        betResult.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void initTable() {
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder());
        table.setEnabled(false);
        refreshTableModel();
    }

    synchronized void refreshTableModel() {
        System.out.println(Thread.currentThread().getName() + " refreshing table model");
        tableModel.setRowCount(0);
        for (int i = 0; i < horses.size(); i++) {
            String[] row = new String[]{String.valueOf(i + 1), horses.get(i).getName()};
            tableModel.addRow(row);
        }
        System.out.println(Thread.currentThread().getName() + " refreshed table model");
    }

    void setBetResult(String result) {
        switch (result) {
            case "WINNER":
                betResult.setForeground(new Color(0, 150, 0));
                break;
            case "LOSE":
                betResult.setForeground(Color.RED);
                break;
            default:
                betResult.setForeground(Color.BLACK);
                break;
        }
        betResult.setText(result);
    }
}
