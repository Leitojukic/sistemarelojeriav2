package com.relojeria.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://<pioix2docuatrime>.mysql.pythonanywhere-services.com/<pioix2docuatrime$relojeria>";
    private static final String USER = "<pioixsegundocuatrime>";
    private static final String PASSWORD = "<Mario1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

