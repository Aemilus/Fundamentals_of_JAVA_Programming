package me.academy.javaprogrammer.week12.exercise01;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class AgendaDataSource {
    private static final String SQL_SELECT_ALL_PERSONS_STATEMENT = "SELECT p_id, p_name, p_age FROM person_t";
    private static final String SQL_INSERT_PERSON_STATEMENT = "INSERT INTO person_t (p_name, p_age) VALUES (?, ?)";
    private static final String SQL_DELETE_PERSON_STATEMENT = "DELETE FROM person_t WHERE p_id = ?";

    MysqlDataSource agendaDbDataSource = new MysqlDataSource();
    Connection connection;

    AgendaDataSource() {
        agendaDbDataSource.setURL("jdbc:mysql://127.0.0.1:33306/agenda_db");
        agendaDbDataSource.setUser("agenda");
        agendaDbDataSource.setPassword("agenda..");
        try {
            connection = agendaDbDataSource.getConnection();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    void insertPerson(Person person) throws SQLException {
        if (person == null) throw new NullPointerException("Person is null.");

        PreparedStatement prepareStatement = connection.prepareStatement(SQL_INSERT_PERSON_STATEMENT);
        prepareStatement.setString(1, person.getName());
        prepareStatement.setInt(2, person.getAge());
        prepareStatement.executeUpdate();
        prepareStatement.close();
    }

    void deletePerson(Person person) throws SQLException {
        if (person == null) throw new NullPointerException("Person is null.");

        PreparedStatement prepareStatement = connection.prepareStatement(SQL_DELETE_PERSON_STATEMENT);
        prepareStatement.setInt(1, person.getId());
        prepareStatement.executeUpdate();
        prepareStatement.close();
    }

    List<Person> selectAllPersons() throws SQLException {
        List<Person> list = new ArrayList<>();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_PERSONS_STATEMENT);

        while (resultSet.next()) {
            int id = resultSet.getInt("p_id");
            String name = resultSet.getString("p_name");
            int age = resultSet.getInt("p_age");
            Person person = new Person(id, name, age);
            list.add(person);
        }

        statement.close();

        return list;
    }
}
