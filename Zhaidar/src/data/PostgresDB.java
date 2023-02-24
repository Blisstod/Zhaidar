package data;

import data.interfaces.IDB;

import java.sql.*;

public class PostgresDB implements IDB {

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String connectionUrl = "jdbc:postgresql://localhost:5432/aviaticket";
        try {
            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection(connectionUrl, "postgres", "admin123");

            return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
