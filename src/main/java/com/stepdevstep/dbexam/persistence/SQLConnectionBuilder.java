package com.stepdevstep.dbexam.persistence;

import java.sql.*;
import java.util.ResourceBundle;


public class SQLConnectionBuilder {
    private static ResourceBundle pr = ResourceBundle.getBundle("global");
    private static String URL = pr.getString("url");
    private static String USERNAME = pr.getString("username");
    private static String PASSWORD = pr.getString("password");

    public static Connection getConnection() throws SQLException {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
