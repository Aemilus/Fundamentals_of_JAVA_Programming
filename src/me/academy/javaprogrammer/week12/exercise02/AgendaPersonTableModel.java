package me.academy.javaprogrammer.week12.exercise02;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AgendaPersonTableModel implements TableModel {
    private static final String SQL_SELECT_ALL_PERSONS_STATEMENT = "SELECT p_id, p_name, p_age, p_gender, p_married FROM person_t";
    private final Statement statement;
    private final ResultSet resultSet;

    public AgendaPersonTableModel(Connection connection) throws SQLException {
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        resultSet = statement.executeQuery(SQL_SELECT_ALL_PERSONS_STATEMENT);
    }

    @Override
    public int getRowCount() {
        int rowCount = 0;
        try {
            resultSet.last();
            rowCount = resultSet.getRow();
        } catch (SQLException sqlException) {
            AgendaFrame.showException(null, sqlException);
        }
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        int columnCount = 0;
        try {
            columnCount = resultSet.getMetaData().getColumnCount();
        } catch (SQLException sqlException) {
            AgendaFrame.showException(null, sqlException);
        }
        return columnCount;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "ID";
            case 1:
                return "Name";
            case 2:
                return "Age";
            case 3:
                return "Gender";
            case 4:
                return "Married";
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        columnIndex++;
        switch (columnIndex) {
            case 1:
            case 3:
                return Integer.class;
            case 2:
                return String.class;
            case 4:
                return PersonGender.class;
            case 5:
                return Boolean.class;
            default:
                return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        rowIndex++;
        columnIndex++;
        Object value = null;
        try {
            resultSet.absolute(rowIndex);
            switch (columnIndex) {
                case 1:
                case 3:
                    value = resultSet.getInt(columnIndex);
                    break;
                case 2:
                    value = resultSet.getString(columnIndex);
                    break;
                case 4:
                    String dbGender = resultSet.getString(columnIndex);
                    value = PersonGender.valueOf(dbGender);
                    break;
                case 5:
                    value = resultSet.getBoolean(columnIndex);
                    break;
            }
        } catch (SQLException sqlException) {
            AgendaFrame.showException(null, sqlException);
        }
        return value;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        rowIndex++;
        columnIndex++;
        try {
            resultSet.absolute(rowIndex);
            switch (columnIndex) {
                case 1:
                case 3:
                    Integer iValue = (Integer) aValue;
                    resultSet.updateInt(columnIndex, iValue);
                    break;
                case 2:
                    String sValue = (String) aValue;
                    resultSet.updateString(columnIndex, sValue);
                    break;
                case 4:
                    String pgValue = aValue.toString();
                    resultSet.updateString(columnIndex, pgValue);
                    break;
                case 5:
                    Boolean bValue = (Boolean) aValue;
                    resultSet.updateBoolean(columnIndex, bValue);
                    break;
            }
            resultSet.updateRow();
        } catch (SQLException sqlException) {
            AgendaFrame.showException(null, sqlException);
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

    void deletePersonRecord(int rowId) throws SQLException {
        rowId++;
        resultSet.absolute(rowId);
        resultSet.deleteRow();
    }

    void insertPersonRecord(Person person) throws SQLException {
        resultSet.moveToInsertRow();
        resultSet.updateString("p_name", person.getName());
        resultSet.updateInt("p_age", person.getAge());
        resultSet.updateString("p_gender", person.getGender());
        resultSet.updateBoolean("p_married", person.isMarried());
        resultSet.insertRow();
    }

    void close() throws SQLException {
        resultSet.close();
        statement.close();
    }
}
