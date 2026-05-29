package com.kauabiscotto.HotelSystem.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    public static final String username = "root";
    public static final String password = "1234";
    public static final String url = "jdbc:mysql://localhost:3306/hotel";

    public static Connection createConnectionToMySQL() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}
