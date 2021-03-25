package me.academy.javaprogrammer.week12.exercise02;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class PersonGenderCellEditor extends AbstractCellEditor implements TableCellEditor {
    private PersonGender gender;

    @Override
    public Object getCellEditorValue() {
        return this.gender;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value instanceof PersonGender) {
            gender = (PersonGender) value;
        }
        JComboBox<PersonGender> genderComboBox = new JComboBox<>(PersonGender.values());

        genderComboBox.setSelectedItem(gender);

        genderComboBox.addActionListener(event -> gender = (PersonGender) genderComboBox.getSelectedItem());

        return genderComboBox;
    }
}
