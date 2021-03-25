package me.academy.javaprogrammer.week12.exercise02;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class AgendaDataSource {
    private final Connection connection;

    AgendaDataSource() throws SQLException {
        MysqlDataSource agendaDbDataSource = new MysqlDataSource();
        agendaDbDataSource.setURL("jdbc:mysql://127.0.0.1:33306/agenda_db");
        agendaDbDataSource.setUser("agenda");
        agendaDbDataSource.setPassword("agenda..");
        connection = agendaDbDataSource.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() throws SQLException {
        connection.close();
    }
}
