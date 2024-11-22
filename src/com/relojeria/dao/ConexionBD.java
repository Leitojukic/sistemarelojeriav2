package com.relojeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // Configuración de la base de datos en PythonAnywhere
    private static final String URL = "jdbc:mysql://<pioix2docuatrimestre>.mysql.pythonanywhere-services.com/<pioix2docuatrimestre$relojeria>";
    private static final String USER = "<pioix2docuatrimestre>";
    private static final String PASSWORD = "<Mario123>";

    /** NOTA IMPORTANTE: EL USUARIO Y CONTRASEÑA DE ARRIBA ES PARA ENTRAR EN PYTHONANYWHERE, sinembargo aparece como "pioixsegundocuatrime" y 
     *  tambien como Database host address:pioix2docuatrimestre.mysql.pythonanywhere-services.com
                     Username:pioix2docuatrime
                     
     * Método para obtener una conexión a la base de datos.
     *
     * @return Objeto Connection si la conexión es exitosa.
     * @throws SQLException si ocurre un error en la conexión.
     */
    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

