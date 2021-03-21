package me.academy.javaprogrammer.week12.exercise01;

import javax.swing.table.DefaultTableModel;

public class AgendaTableModel extends DefaultTableModel {
    public AgendaTableModel(String[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
