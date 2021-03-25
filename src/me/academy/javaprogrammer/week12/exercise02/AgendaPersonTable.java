package me.academy.javaprogrammer.week12.exercise02;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableColumn;
import java.sql.SQLException;

public class AgendaPersonTable extends JTable {
    private final AgendaPersonTableModel agendaPersonTableModel;

    public AgendaPersonTable(AgendaPersonTableModel agendaPersonTableModel) {
        super(agendaPersonTableModel);
        this.agendaPersonTableModel = agendaPersonTableModel;
        // set combo box editor for gender column
        TableColumn genderColumn = this.getColumnModel().getColumn(3);
        // you can write your own cell editor or use the default one which has support for combo box
//        genderColumn.setCellEditor(new PersonGenderCellEditor());
        genderColumn.setCellEditor(new DefaultCellEditor(new JComboBox<>(PersonGender.values())));
    }

    public void deleteSelectedRow() throws SQLException {
        int selectedRow = this.getSelectedRow();
        if (selectedRow < 0) return;
        agendaPersonTableModel.deletePersonRecord(selectedRow);
        this.tableChanged(new TableModelEvent(agendaPersonTableModel));
    }

    public void insertPersonRow(Person person) throws SQLException {
        agendaPersonTableModel.insertPersonRecord(person);
        this.tableChanged(new TableModelEvent(agendaPersonTableModel));
    }
}
