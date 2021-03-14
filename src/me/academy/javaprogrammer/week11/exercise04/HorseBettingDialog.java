package me.academy.javaprogrammer.week11.exercise04;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

final class HorseBettingDialog extends JDialog {
    private final HorseRacingFrame frame;
    private final List<Horse> horses;

    private final JPanel rootPanel = new JPanel();

    private final String[] columnNames = new String[]{"Horse name", "Decimal odds"};
    private final DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
    private final JTable table = new JTable(tableModel);
    private final JScrollPane tablePanel = new JScrollPane(table);

    private final JButton placeBetButton = new JButton("Place Bet");

    HorseBettingDialog(HorseRacingFrame frame, List<Horse> horses) {
        super(frame, "Place your bet", true);
        this.frame = frame;
        this.horses = horses;
        initTable();
        initRootPanel();
        initDialog();
        initListeners();
        pack();
        setLocationRelativeTo(null); // must be called after pack() in order to display at center of screen
    }

    private void initTable() {
        tablePanel.setBorder(BorderFactory.createEmptyBorder());
        // setting the default editor like below disables the cell editing
        table.setDefaultEditor(Object.class, null);
        // fill table
        horses.forEach(horse -> tableModel.addRow(horse.getHorseAsTableRow()));
    }

    private void initDialog() {
        setPreferredSize(new Dimension(400, 200));
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        add(rootPanel, BorderLayout.CENTER);
    }

    private void initRootPanel() {
        rootPanel.setLayout(new BorderLayout());
        rootPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        rootPanel.add(tablePanel, BorderLayout.CENTER);
        rootPanel.add(placeBetButton, BorderLayout.SOUTH);
    }

    private void initListeners() {
        placeBetButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            // if no row is selected when button is pressed then do nothing
            if (selectedRow < 0) return;
            // else save the horse selected and close this dialog
            Horse selectedHorse = horses.get(selectedRow);
            frame.setBetHorse(selectedHorse);
            this.setVisible(false);
        });
    }
}
